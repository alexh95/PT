package com.alexh95.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.LinkedBlockingQueue;

import com.alexh95.model.task.Task;

public class StatTraker {

	private static StatTraker instance;

	private LinkedBlockingQueue<TaskStats> finishedTasks;

	private StatTraker() {
	}

	public static StatTraker instance() {
		if (instance == null) {
			instance = new StatTraker();
		}
		return instance;
	}

	public void startTracking() {
		finishedTasks = new LinkedBlockingQueue<>();
	}

	public void track(Task finishedTask, int serverIndex, int finishedTime) {
		finishedTasks.add(
				new TaskStats(finishedTask.getArrivalTime(), finishedTask.getProcessTime(), serverIndex, finishedTime));
	}

	private int serverCount() {
		int serverCount = -1;
		for (TaskStats ts : finishedTasks) {
			if (serverCount == -1 || serverCount < ts.getServerIndex()) {
				serverCount = ts.getServerIndex();
			}
		}
		return serverCount + 1;
	}

	private int lastProcessed() {
		int lastProcessed = -1;
		for (TaskStats ts : finishedTasks) {
			if (lastProcessed == -1 || lastProcessed < ts.getFinishedTime()) {
				lastProcessed = ts.getFinishedTime();
			}
		}
		return lastProcessed;
	}

	public void report(int divisionCount) {
		int lastProcessed = lastProcessed();
		int serverCount = serverCount();
		if (serverCount < 1) {
			return;
		}

		try {
			PrintWriter pw = new PrintWriter(new File("report.txt"));

			int sumWaitingTime = 0;
			int[] sumWaitingTimes = new int[serverCount];
			int[] tasksPerServer = new int[serverCount];
			for (int i = 0; i < serverCount; i++) {
				sumWaitingTimes[i] = 0;
				tasksPerServer[i] = 0;
			}

			int divisionWidth = lastProcessed / divisionCount;
			int[] peakArrived = new int[divisionCount];
			int[] peakProcessed = new int[divisionCount];
			for (int i = 0; i < divisionCount; i++) {
				peakArrived[i] = 0;
				peakProcessed[i] = 0;
			}
			for (TaskStats ts : finishedTasks) {
				int waitingTime = ts.getFinishedTime() - ts.getArrivalTime();
				sumWaitingTime += waitingTime;
				sumWaitingTimes[ts.getServerIndex()] += waitingTime;
				tasksPerServer[ts.getServerIndex()]++;

				int divisionArrived = ts.getArrivalTime() / divisionWidth;
				if (divisionArrived == divisionCount) {
					divisionArrived--;
				}
				peakArrived[divisionArrived]++;
				int divisionFinished = ts.getFinishedTime() / divisionWidth;
				if (divisionFinished == divisionCount) {
					divisionFinished--;
				}
				peakProcessed[divisionFinished]++;
			}

			double avgWaitingTime = sumWaitingTime / (double) finishedTasks.size();
			double[] avgWaitingTimes = new double[serverCount];
			for (int i = 0; i < serverCount; i++) {
				avgWaitingTimes[i] = sumWaitingTimes[i] / (double) tasksPerServer[i];
			}

			pw.println("Simulation Report");
			pw.println("Average Waiting Time = " + avgWaitingTime);
			for (int i = 0; i < serverCount; i++) {
				pw.println("Average Waiting Time On Server " + i + " = " + avgWaitingTimes[i]);
			}
			pw.println("Arrived Hours");
			for (int i = 0; i < divisionCount; i++) {
				pw.println("H" + i + " [" + divisionWidth * i + "->" + divisionWidth * (i + 1) + "]: " + peakArrived[i]);
			}
			pw.println("Processed Hours");
			for (int i = 0; i < divisionCount; i++) {
				pw.println("H" + i + " [" + divisionWidth * i + "->" + divisionWidth * (i + 1) + "]: " + peakProcessed[i]);
			}
			pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
