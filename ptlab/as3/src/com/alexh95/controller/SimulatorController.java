package com.alexh95.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.alexh95.model.simulator.Simulator;
import com.alexh95.services.Logger;
import com.alexh95.view.SimulatorFrame;

public class SimulatorController implements ControllerObserver, TimerObserver {

	private static final String TITLE = "Queue Simulator";
	private static final double WIDTH_OF_SCREEN = 0.5;
	private static final double HEIGHT_OF_SCREEN = 0.5;
	private SimulatorFrame frame;

	private Simulator simulator;

	public SimulatorController() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				frame = new SimulatorFrame(TITLE, WIDTH_OF_SCREEN, HEIGHT_OF_SCREEN);
				frame.getOptionPanel()
						.addStartSimulationButtonActionListener(new StartSimulationButtonActionListener());
				frame.getOptionPanel().addStopSimulationButtonActionListener(new StopSimulatorButtonActionListener());
			}
		}).start();
	}

	@Override
	public void updateController(String[][] tasks, String[] watingTimes) {
		frame.display(tasks, watingTimes);
	}

	@Override
	public void updateTimer(String textTimer, int maxValueProgressBar, int valueProgressBar) {
		frame.displayTimer(textTimer, maxValueProgressBar, valueProgressBar);
	}

	private class StartSimulationButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(">>Started Simulation");
			try {
				int simulationTime = Integer.parseInt(frame.getOptionPanel().getSimulationTimeTextField());

				int minTaskDelay = Integer.parseInt(frame.getOptionPanel().getMinTaskDelayTextField());
				int maxTaskDelay = Integer.parseInt(frame.getOptionPanel().getMaxTaskDelayTextField());

				int minProcessTime = Integer.parseInt(frame.getOptionPanel().getMinProcessTimeTextField());
				int maxProcessTime = Integer.parseInt(frame.getOptionPanel().getMaxProcessTimeTextField());

				int minServerCount = Integer.parseInt(frame.getOptionPanel().getMinServerCountTextField());
				int maxServerCount = Integer.parseInt(frame.getOptionPanel().getMaxServerCountTextField());

				int maxServerWaitingTime = Integer.parseInt(frame.getOptionPanel().getMaxServerWaitingTimeTextField());

				frame.newSimulatorPanel(minServerCount, maxServerCount);

				simulator = new Simulator(simulationTime, minTaskDelay, maxTaskDelay, minProcessTime, maxProcessTime,
						minServerCount, maxServerCount, maxServerWaitingTime);
				simulator.addTaskObserver(SimulatorController.this);
				simulator.addTimerObserver(SimulatorController.this);
				Thread simulatorThread = new Thread(simulator);
				simulatorThread.start();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}

	private class StopSimulatorButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(">>Stopped Simulation");
			if (simulator != null) {
				Logger.instance().log("STOPPED FORCEFULLY");
				simulator.stopSimulation();
				frame.forceStop();
			}
		}
	}
}
