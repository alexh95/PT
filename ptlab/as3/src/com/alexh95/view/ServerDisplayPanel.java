package com.alexh95.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ServerDisplayPanel extends JPanel {
	private static final long serialVersionUID = 935338560234255940L;

	private JTextField status;
	private JTextField name;
	private JTextField waitingTime;

	public ServerDisplayPanel() {
		super(new GridLayout(3, 1));

		JPanel statusPanel = new JPanel(new BorderLayout());
		JLabel statusLabel = new JLabel("S:");
		statusPanel.add(statusLabel, BorderLayout.WEST);
		status = new JTextField();
		status.setEditable(false);
		statusPanel.add(status, BorderLayout.CENTER);
		add(statusPanel);
		
		JPanel namePanel = new JPanel(new BorderLayout());
		JLabel nameLabel = new JLabel("N:");
		namePanel.add(nameLabel, BorderLayout.WEST);
		name = new JTextField();
		name.setEditable(false);
		namePanel.add(name, BorderLayout.CENTER);
		add(namePanel);
		
		JPanel waitingTimePanel = new JPanel(new BorderLayout());
		JLabel waitingTimeLabel = new JLabel("WT:");
		waitingTimePanel.add(waitingTimeLabel, BorderLayout.WEST);
		waitingTime = new JTextField();
		waitingTime.setEditable(false);
		waitingTimePanel.add(waitingTime, BorderLayout.CENTER);
		add(waitingTimePanel);

		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
	}

	public void setStatusText(String text) {
		status.setText(text);
	}
	
	public void setNameText(String text) {
		name.setText(text);
	}
	
	public void setWaitingTimeText(String text) {
		waitingTime.setText(text);
	}
}
