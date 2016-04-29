package com.alexh95.model.scheduler;

import java.util.ArrayList;

public class SchedulerObservable {
	private ArrayList<SchedulerObserver> schedulerObservers;
	
	public SchedulerObservable() {
		schedulerObservers = new ArrayList<>();
	}
	
	public void addSchedulerObserver(SchedulerObserver o) {
		schedulerObservers.add(o);
	}
	
	public void notifySchedulerObservers() {
		for (SchedulerObserver o : schedulerObservers) {
			o.updateScheduler();
		}
	}
}
