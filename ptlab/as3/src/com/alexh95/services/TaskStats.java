package com.alexh95.services;

import com.alexh95.model.task.Task;

public class TaskStats extends Task {

	private int serverIndex;
	private int finishedTime;
	
	public TaskStats(int arrivalTime, int processTime, int serverIndex, int finishedTime) {
		super(arrivalTime, processTime);
		this.serverIndex = serverIndex;
		this.finishedTime = finishedTime;
	}

	public int getServerIndex() {
		return serverIndex;
	}
	
	public int getFinishedTime() {
		return finishedTime;
	}
}
