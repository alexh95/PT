package com.alexh95.model.task;

public class TaskFactory {

	private int minProcessTime;
	private int maxProcessTime;
	
	public TaskFactory(int minProcessTime, int maxProcessTime) {
		this.minProcessTime = minProcessTime;
		this.maxProcessTime = maxProcessTime;
	}
	
	public Task createRandomTask(int arrivalTime) {
		assert (arrivalTime >= 0);
		
		int processTime = (int) (Math.random() * (maxProcessTime - minProcessTime + 1) + minProcessTime);
		Task task = new Task(arrivalTime, processTime);
		return task;
	}
}
