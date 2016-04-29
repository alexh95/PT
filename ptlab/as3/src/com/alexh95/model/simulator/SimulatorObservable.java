package com.alexh95.model.simulator;

import java.util.ArrayList;

public class SimulatorObservable {
	private ArrayList<SimulatorObserver> simulatorObservers;
	
	public SimulatorObservable() {
		simulatorObservers = new ArrayList<>();
	}

	public void addSimulatorObserver(SimulatorObserver o) {
		simulatorObservers.add(o);
	}
	
	public void notifySimulatorObservers() {
		for (SimulatorObserver o : simulatorObservers) {
			o.updateSimulator();
		}
	}
}
