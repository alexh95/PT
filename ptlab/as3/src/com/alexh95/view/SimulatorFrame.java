package com.alexh95.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimulatorFrame extends JFrame {

	private static final long serialVersionUID = -7033020011880822250L;

	private JPanel panelHolder;
	private SimulatorOptionsPanel optionsPanel;
	private JPanel simulatorPanelHolder;
	private SimulatorPanel simulatorPanel;

	public SimulatorFrame(String title, double widthOfScreen, double heightOfScreen) {
		super(title);
		assert (widthOfScreen >= 0 && widthOfScreen <= 1);
		assert (heightOfScreen >= 0 && heightOfScreen <= 1);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension screenDimension = getToolkit().getScreenSize();
		int width = (int) (screenDimension.width * widthOfScreen);
		int height = (int) (screenDimension.height * heightOfScreen);
		int x = (screenDimension.width - width) / 2;
		int y = (screenDimension.height - height) / 2;
		setBounds(x, y, width, height);

		setLayout(new BorderLayout());
		panelHolder = new JPanel(new BorderLayout());
		optionsPanel = new SimulatorOptionsPanel();
		panelHolder.add(optionsPanel, BorderLayout.NORTH);
		simulatorPanelHolder = new JPanel(new BorderLayout());
		panelHolder.add(simulatorPanelHolder, BorderLayout.CENTER);
		add(panelHolder, BorderLayout.CENTER);

		setVisible(true);
	}

	public void newSimulatorPanel(int currentServerCount, int maxServerCount) {
		simulatorPanelHolder.removeAll();
		simulatorPanelHolder.revalidate();

		simulatorPanel = new SimulatorPanel(currentServerCount, maxServerCount);
		simulatorPanelHolder.add(simulatorPanel, BorderLayout.CENTER);

		simulatorPanelHolder.revalidate();
		simulatorPanelHolder.repaint();
	}
	
	public void displayTimer(String timerText, int maxValueProgressBar, int valueProgressBar) {
		optionsPanel.setTimerTextField(timerText);
		optionsPanel.setTimerProgressBar(maxValueProgressBar, valueProgressBar);
	}

	public void display(String[][] tasks, String[] waitingTimes) {
		simulatorPanel.display(tasks, waitingTimes);
	}
	
	public void forceStop() {
		simulatorPanel.forceStop();
	}

	public SimulatorOptionsPanel getOptionPanel() {
		return optionsPanel;
	}
}
