package com.alexh95.model.scheduler;

import java.util.ArrayList;

import com.alexh95.model.Server;
import com.alexh95.model.simulator.SimulatorObservable;
import com.alexh95.model.simulator.SimulatorObserver;
import com.alexh95.model.task.Task;
import com.alexh95.services.DataFunc;
import com.alexh95.services.Logger;
import com.alexh95.services.StatTraker;

public class Scheduler implements SchedulerObserver {

	private ArrayList<Server> servers;
	private ArrayList<Thread> threads;

	private SimulatorObservable simulatorObservable;

	private int minServerCount;
	private int maxServerCount;

	private int maxWaitingTime;

	public Scheduler(int minServerCount, int maxServerCount, int maxWaitingTime) {
		Logger.instance().startLogging();
		this.minServerCount = minServerCount;
		this.maxServerCount = maxServerCount;
		this.maxWaitingTime = maxWaitingTime;

		StatTraker.instance().startTracking();

		servers = new ArrayList<>();
		threads = new ArrayList<>();
		for (int i = 0; i < this.minServerCount; i++) {
			addNewServer();
		}

		simulatorObservable = new SimulatorObservable();
	}

	public void addSimulatorObserver(SimulatorObserver o) {
		simulatorObservable.addSimulatorObserver(o);
	}

	@Override
	public void updateScheduler() {
		rebalance();
		simulatorObservable.notifySimulatorObservers();
	}

	private synchronized void addNewServer() {
		Logger.instance().log("Added new Server: " + getServerCount());
		Server server = new Server(getServerCount());
		server.addSchedulerObserver(this);
		servers.add(server);

		Thread serverThread = new Thread(server);
		serverThread.start();
		threads.add(serverThread);
		Logger.instance().log("Started new server thread for server " + (getServerCount() - 1));
	}

	public void closeServers() {
		Logger.instance().log("Closing servers");
		for (Server server : servers) {
			server.stopRunning();
		}

		for (Thread serverThread : threads) {
			try {
				serverThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println(">> ended");
		Logger.instance().endLogging();
	}

	public int getServerCount() {
		return servers.size();
	}

	private int getMinWaitingTimeServerIndex() {
		int minWaitingIndex = -1;
		for (int i = 0; i < getServerCount(); i++) {
			if (minWaitingIndex == -1
					|| servers.get(minWaitingIndex).getWaitingTime() > servers.get(i).getWaitingTime()) {
				minWaitingIndex = i;
			}
		}
		return minWaitingIndex;
	}

	private Server getMinWaitingTimeServer() {
		return servers.get(getMinWaitingTimeServerIndex());
	}

	private int getMaxWaitingTimeServerIndex() {
		int maxWaitingIndex = -1;
		for (int i = 0; i < getServerCount(); i++) {
			if (maxWaitingIndex == -1
					|| servers.get(maxWaitingIndex).getWaitingTime() < servers.get(i).getWaitingTime()) {
				maxWaitingIndex = i;
			}
		}
		return maxWaitingIndex;
	}

	private Server getMaxWaitingTimeServer() {
		return servers.get(getMaxWaitingTimeServerIndex());
	}

	public void dispatchTask(Task task) {
		getMinWaitingTimeServer().addTask(task);
	}

	private Task[] getTasksFromServer(int serverIndex) {
		return servers.get(serverIndex).getTasks();
	}

	public Task[][] getTasks() {
		Task[][] tasks = new Task[getServerCount()][];
		for (int i = 0; i < servers.size(); i++) {
			tasks[i] = getTasksFromServer(i);
		}
		return tasks;
	}

	private boolean isEmpty(int serverIndex) {
		return servers.get(serverIndex).getWaitingTime() == 0;
	}

	private boolean isRunning(int serverIndex) {
		return servers.get(serverIndex).isRunning();
	}

	public boolean allEmpty() {
		boolean result = true;
		for (int i = 0; i < getServerCount(); i++) {
			result = result && isEmpty(i);
		}
		// System.out.println("AllEmpty: " + result);
		return result;
	}

	public boolean allStopped() {
		boolean result = true;
		for (int i = 0; i < getServerCount(); i++) {
			result = result && !isRunning(i);
		}
		// System.out.println("AllStopped: " + result);
		return result;
	}

	public Server[] getServers() {
		Server[] result = new Server[getServerCount()];
		servers.toArray(result);
		return result;
	}

	private double getAverageWaitingTime() {
		int sumWaitingTime = 0;
		for (Server server : servers) {
			sumWaitingTime += server.getWaitingTime();
		}
		return sumWaitingTime / (double) getServerCount();
	}

	private boolean hasRebalancingServersPotential() {
		return getAverageWaitingTime() > maxWaitingTime && getServerCount() < maxServerCount;
	}

	private boolean hasRebalancingTasksPotential() {
		return getServerCount() > 1;
	}

	private synchronized void rebalance() {
		// System.out.println("Rebalancing");

		while (hasRebalancingServersPotential()) {
			// System.out.println("Rebalancing Potential");
			addNewServer();
		}

		if (hasRebalancingTasksPotential()) {
			// System.out.println("Rebalancing Tasks");
			try {
				rebalanceTasks();
			} catch (NullPointerException e) {
			}
		}
	}

	private synchronized void rebalanceTasks() throws NullPointerException {
		boolean performedRebalance = true;
		while (performedRebalance && getMaxWaitingTimeServer().getSize() > 0) {
			performedRebalance = false;

			double[] dataSet = new double[getServerCount()];
			for (int i = 0; i < getServerCount(); i++) {
				dataSet[i] = servers.get(i).getWaitingTime();
			}
			// System.out.print("Before: ");
			double standardDeviationBefore = DataFunc.getStandardDeviation(dataSet);

			int minServer = getMinWaitingTimeServerIndex();
			// System.out.println("m: " + minServer);
			int maxServer = getMaxWaitingTimeServerIndex();
			// System.out.println("M:" + maxServer);
			// System.out.println("ss: " + servers.get(maxServer).getSize());
			double maxServerHeadTaskProcessTime = servers.get(maxServer).peek().getProcessTime();
			// System.out.println("V:" + maxServerHeadTaskProcessTime);

			double[] dataSetSwap = new double[getServerCount()];
			for (int i = 0; i < getServerCount(); i++) {
				dataSetSwap[i] = dataSet[i];
			}
			dataSetSwap[maxServer] -= maxServerHeadTaskProcessTime;
			dataSetSwap[minServer] += maxServerHeadTaskProcessTime;
			// System.out.print("After: ");
			double standardDeviationAfter = DataFunc.getStandardDeviation(dataSetSwap);

			// if the swap would decrease the standard deviation
			// then perform perform the move on the servers
			if (standardDeviationAfter < standardDeviationBefore) {
				performedRebalance = true;
			}

			if (performedRebalance) {
				// System.out.println("Rebalanced");
				Task task = servers.get(maxServer).take();
				servers.get(minServer).addTask(task);
			}
		}
	}
}
