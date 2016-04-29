package com.alexh95.model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import com.alexh95.model.scheduler.SchedulerObservable;
import com.alexh95.model.scheduler.SchedulerObserver;
import com.alexh95.model.task.Task;
import com.alexh95.services.Logger;
import com.alexh95.services.StatTraker;
import com.alexh95.services.Timer;

public class Server implements Runnable {

	private BlockingQueue<Task> bq;
	private AtomicInteger waitingTime;

	private SchedulerObservable schedulerObservable;
	private int serverIndex;

	private volatile boolean keepRunning;

	public Server(int serverIndex) {
		this.serverIndex = serverIndex;
		bq = new LinkedBlockingQueue<>();
		waitingTime = new AtomicInteger(0);
		schedulerObservable = new SchedulerObservable();
		keepRunning = true;
	}

	public void addSchedulerObserver(SchedulerObserver o) {
		schedulerObservable.addSchedulerObserver(o);
	}

	public Task[] getTasks() {
		Task[] tasks = new Task[bq.size()];
		bq.toArray(tasks);
		return tasks;
	}

	public void addTask(Task task) {
		assert (task != null);

		bq.add(task);
		if (keepRunning) {
			waitingTime.addAndGet(task.getProcessTime());
			schedulerObservable.notifySchedulerObservers();
			Logger.instance().log("Added task " + task + " to server " + serverIndex);
		}
	}

	public Task peek() {
		return bq.peek();
	}

	public Task take() {
		try {
			Task task = bq.take();
			if (keepRunning) {
				waitingTime.addAndGet(-task.getProcessTime());
				schedulerObservable.notifySchedulerObservers();
				Logger.instance().log("Removed task " + task + " from server " + serverIndex);
			}
			return task;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getSize() {
		return bq.size();
	}

	public boolean isEmpty() {
		return bq.isEmpty();
	}

	public int getWaitingTime() {
		return waitingTime.get();
	}

	public void stopRunning() {
		keepRunning = false;
		addTask(new Task(0, 0));
	}

	public boolean isRunning() {
		return keepRunning;
	}

	@Override
	public void run() {
		while (keepRunning) {
			Task task;
			try {
				task = bq.take();
				if (keepRunning) {
					schedulerObservable.notifySchedulerObservers();
					Logger.instance().log("Processing task " + task + " in server " + serverIndex);
					Thread.sleep(task.getProcessTime());
					Logger.instance().log("Processed task " + task + " in server " + serverIndex);
					StatTraker.instance().track(task, serverIndex, Timer.instance().get());
					waitingTime.addAndGet(-task.getProcessTime());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// ended, update one more time
			schedulerObservable.notifySchedulerObservers();
		}
	}
}
