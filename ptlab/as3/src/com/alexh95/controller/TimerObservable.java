package com.alexh95.controller;

import java.util.ArrayList;

public class TimerObservable {
	private ArrayList<TimerObserver> observers;
	
	public TimerObservable() {
		observers = new ArrayList<>();
	}

	public void addTimerObserver(TimerObserver e) {
		observers.add(e);
	}
	
	public void notifyTimerObservers(String textTimer, int maxValueProgressBar, int valueProgressBar) {
		for (TimerObserver observer : observers) {
			observer.updateTimer(textTimer, maxValueProgressBar, valueProgressBar);
		}
	}
}
