package com.alexh95.model.simulator;

import com.alexh95.controller.ControllerObservable;
import com.alexh95.controller.ControllerObserver;
import com.alexh95.controller.TimerObservable;
import com.alexh95.controller.TimerObserver;
import com.alexh95.model.Server;
import com.alexh95.model.scheduler.Scheduler;
import com.alexh95.model.task.Task;
import com.alexh95.model.task.TaskFactory;
import com.alexh95.services.StatTraker;
import com.alexh95.services.Timer;

public class Simulator implements Runnable, SimulatorObserver {

	private int finishTime;

	private int minTaskDelay;
	private int maxTaskDelay;

	private Scheduler scheduler;
	private TaskFactory taskFactory;

	private ControllerObservable controllerObservable;
	private TimerObservable timerObservable;
	
	private volatile boolean simulationRunning;

	public Simulator(int simulationTime, int minTaskDelay, int maxTaskDelay, int minProcessTime,
			int maxProcessTime, int minServerCount, int maxServerCount, int maxServerWaitingTime) {
		assert (simulationTime > 0);
		assert (minTaskDelay > 0);
		assert (maxTaskDelay > 0 && maxTaskDelay >= minTaskDelay);
		assert (minProcessTime > 0);
		assert (maxProcessTime > 0 && maxProcessTime >= minProcessTime);
		assert (minServerCount > 0);
		assert (maxServerCount > 0 && maxServerCount >= minServerCount);
		assert (maxServerWaitingTime > 0);

		this.finishTime = simulationTime;

		this.minTaskDelay = minTaskDelay;
		this.maxTaskDelay = maxTaskDelay;

		scheduler = new Scheduler(minServerCount, maxServerCount, maxServerWaitingTime);
		scheduler.addSimulatorObserver(this);
		taskFactory = new TaskFactory(minProcessTime, maxProcessTime);

		controllerObservable = new ControllerObservable();
		timerObservable = new TimerObservable();
		
		simulationRunning = true;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (simulationRunning) {
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Timer.instance().addAndGet(20);
				}
			}
		}).start();
	}

	public void addTaskObserver(ControllerObserver controllerObserver) {
		controllerObservable.addControllerObserver(controllerObserver);
	}

	public void addTimerObserver(TimerObserver timerObserver) {
		timerObservable.addTimerObserver(timerObserver);
	}

	private int getTaskDelay() {
		return (int) (Math.random() * (maxTaskDelay - minTaskDelay + 1) + minTaskDelay);
	}

	@Override
	public synchronized void updateSimulator() {
		Task[][] tasks = scheduler.getTasks();
		String[][] taskStrings = new String[tasks.length][];
		for (int i = 0; i < tasks.length; i++) {
			taskStrings[i] = new String[tasks[i].length];
			for (int j = 0; j < tasks[i].length; j++) {
				taskStrings[i][j] = "" + tasks[i][j];
			}
		}

		Server[] servers = scheduler.getServers();
		String[] waitingTimeStrings = new String[scheduler.getServerCount()];
		for (int i = 0; i < scheduler.getServerCount(); i++) {
			waitingTimeStrings[i] = Integer.toString(servers[i].getWaitingTime());
		}

		controllerObservable.notifyController(taskStrings, waitingTimeStrings);
		
		int currentTime = Timer.instance().get();
		String textTimer = "Progress: " + currentTime + " / " + finishTime;
		timerObservable.notifyTimerObservers(textTimer, finishTime, currentTime);
	}

	public void stopSimulation() {
		scheduler.closeServers();
		simulationRunning = false;
	}

	@Override
	public void run() {
		Timer.instance().startTimer();
		int tickIncrement = 0;
		int clients = 0;
		while (Timer.instance().get() < finishTime && !scheduler.allStopped()) {
			clients++;
			tickIncrement = getTaskDelay();
			Task task = taskFactory.createRandomTask(Timer.instance().get());
			scheduler.dispatchTask(task);
			try {
				Thread.sleep(tickIncrement);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		while (!scheduler.allEmpty() && !scheduler.allStopped()) {
			try {
				Thread.sleep(18);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		stopSimulation();
		StatTraker.instance().report(10);
		System.out.println(">>no of clients " + clients);
	}
}
