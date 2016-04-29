package com.alexh95.model.task;

public class Task {
	private int arrivalTime;
	private int processTime;

	public Task(int arrivalTime, int processTime) {
		this.arrivalTime = arrivalTime;
		this.processTime = processTime;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public int getProcessTime() {
		return processTime;
	}

	@Override
	public String toString() {
		return "<Task: A:" + Integer.toString(arrivalTime) + "; P:" + Integer.toString(processTime) + ">";
	}
}
