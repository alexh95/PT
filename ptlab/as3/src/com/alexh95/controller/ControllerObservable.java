package com.alexh95.controller;

import java.util.ArrayList;

public class ControllerObservable {
	private ArrayList<ControllerObserver> controllerObservers;

	public ControllerObservable() {
		controllerObservers = new ArrayList<>();
	}

	public void addControllerObserver(ControllerObserver controllerObserver) {
		controllerObservers.add(controllerObserver);
	}

	public void notifyController(String[][] tasks, String[] waitingTimes) {
		for (ControllerObserver controllerObserver : controllerObservers) {
			controllerObserver.updateController(tasks, waitingTimes);
		}
	}
}
