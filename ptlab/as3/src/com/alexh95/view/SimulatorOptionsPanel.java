package com.alexh95.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class SimulatorOptionsPanel extends JPanel {
	private static final long serialVersionUID = -8387440912861330345L;

	private JTextField simulationTimeTextField;

	private JTextField minTaskDelayTextField;
	private JTextField maxTaskDelayTextField;
	
	private JTextField minProcessTimeTextField;
	private JTextField maxProcessTimeTextField;

	private JTextField minServerCountTextField;
	private JTextField maxServerCountTextField;

	private JTextField maxServerWaitingTimeTextField;

	private JButton startSimulationButton;
	private JButton stopSimulationButton;
	
	private JTextField timerTextField;
	private JProgressBar timerProgresBar;

	public SimulatorOptionsPanel() {
		super(new BorderLayout());

		JPanel optionsPanel = new JPanel(new GridLayout(1, 6));
		JPanel timePanel = new JPanel(new GridLayout(1, 2));
		JLabel simulationTimeLabel = new JLabel("Simulation Time(ms):");
		timePanel.add(simulationTimeLabel);
		simulationTimeTextField = new JTextField("20000");
		timePanel.add(simulationTimeTextField);
		timePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
		optionsPanel.add(timePanel);
		
		JPanel taskDelayPanel = new JPanel(new GridLayout(2, 2));
		JLabel minTaskDelayLabel = new JLabel("Min Task Delay(ms):");
		taskDelayPanel.add(minTaskDelayLabel);
		minTaskDelayTextField = new JTextField("250");
		taskDelayPanel.add(minTaskDelayTextField);
		JLabel maxTaskDelayLabel = new JLabel("Max Task Delay(ms):");
		taskDelayPanel.add(maxTaskDelayLabel);
		maxTaskDelayTextField = new JTextField("500");
		taskDelayPanel.add(maxTaskDelayTextField);
		taskDelayPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
		optionsPanel.add(taskDelayPanel);

		JPanel processTimePanel = new JPanel(new GridLayout(2, 2));
		JLabel minProcessTimeLabel = new JLabel("Min Process Time(ms):");
		processTimePanel.add(minProcessTimeLabel);
		minProcessTimeTextField = new JTextField("500");
		processTimePanel.add(minProcessTimeTextField);
		JLabel maxProcessTimeLabel = new JLabel("Max Process Time(ms):");
		processTimePanel.add(maxProcessTimeLabel);
		maxProcessTimeTextField = new JTextField("2500");
		processTimePanel.add(maxProcessTimeTextField);
		processTimePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
		optionsPanel.add(processTimePanel);

		JPanel serverCountPanel = new JPanel(new GridLayout(2, 2));
		JLabel minServerCountLabel = new JLabel("Min Server Count:");
		serverCountPanel.add(minServerCountLabel);
		minServerCountTextField = new JTextField("1");
		serverCountPanel.add(minServerCountTextField);
		JLabel maxServerCountLabel = new JLabel("Max Server Count:");
		serverCountPanel.add(maxServerCountLabel);
		maxServerCountTextField = new JTextField("4");
		serverCountPanel.add(maxServerCountTextField);
		serverCountPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
		optionsPanel.add(serverCountPanel);

		JPanel serverWaitingTimePanel = new JPanel(new GridLayout(1, 2));
		JLabel maxServerWaitingTimeLabel = new JLabel("Max Server Waiting Time(ms):");
		serverWaitingTimePanel.add(maxServerWaitingTimeLabel);
		maxServerWaitingTimeTextField = new JTextField("15000");
		serverWaitingTimePanel.add(maxServerWaitingTimeTextField);
		serverWaitingTimePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
		optionsPanel.add(serverWaitingTimePanel);

		JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
		startSimulationButton = new JButton("Start Simulation");
		buttonPanel.add(startSimulationButton);
		stopSimulationButton = new JButton("Stop Simulation");
		buttonPanel.add(stopSimulationButton);
		buttonPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
		optionsPanel.add(buttonPanel);
		add(optionsPanel, BorderLayout.CENTER);
		
		JPanel timerPanel = new JPanel(new BorderLayout());
		timerTextField = new JTextField();
		timerTextField.setEditable(false);
		timerPanel.add(timerTextField, BorderLayout.NORTH);
		timerProgresBar = new JProgressBar();
		timerPanel.add(timerProgresBar, BorderLayout.CENTER);
		timerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
		add(timerPanel, BorderLayout.SOUTH);
		
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
	}

	public String getSimulationTimeTextField() {
		return simulationTimeTextField.getText();
	}

	public void setSimulationTimeTextField(String text) {
		simulationTimeTextField.setText(text);
	}

	public String getMinTaskDelayTextField() {
		return minTaskDelayTextField.getText();
	}

	public void setMinTaskDelayTextField(String text) {
		minTaskDelayTextField.setText(text);
	}

	public String getMaxTaskDelayTextField() {
		return maxTaskDelayTextField.getText();
	}

	public void setMaxTaskDelayTextField(String text) {
		maxTaskDelayTextField.setText(text);
	}

	public String getMinProcessTimeTextField() {
		return minProcessTimeTextField.getText();
	}

	public void setMinProcessTimeTextField(String text) {
		minProcessTimeTextField.setText(text);
	}

	public String getMaxProcessTimeTextField() {
		return maxProcessTimeTextField.getText();
	}

	public void setMaxProcessTimeTextField(String text) {
		maxProcessTimeTextField.setText(text);
	}

	public String getMinServerCountTextField() {
		return minServerCountTextField.getText();
	}

	public void setMinServerCountTextField(String text) {
		minServerCountTextField.setText(text);
	}

	public String getMaxServerCountTextField() {
		return maxServerCountTextField.getText();
	}

	public void setMaxServerCountTextField(String text) {
		maxServerCountTextField.setText(text);
	}

	public String getMaxServerWaitingTimeTextField() {
		return maxServerWaitingTimeTextField.getText();
	}

	public void setMaxServerWaitingTimeTextField(String text) {
		maxServerWaitingTimeTextField.setText(text);
	}
	
	public void setTimerTextField(String text) {
		timerTextField.setText(text);
	}
	
	public void setTimerProgressBar(int maxValue, int value) {
		timerProgresBar.setMaximum(maxValue);
		timerProgresBar.setValue(value);
	}

	public void addStartSimulationButtonActionListener(ActionListener l) {
		startSimulationButton.addActionListener(l);
	}

	public void addStopSimulationButtonActionListener(ActionListener l) {
		stopSimulationButton.addActionListener(l);
	}
}
