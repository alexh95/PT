package com.alexh95.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SimulatorPanel extends JPanel {
	private static final long serialVersionUID = -8230078643487910594L;

	private int maxServerCount;

	private ArrayList<ServerDisplayPanel> serverDisplayPanels;

	private JPanel serverPanel;
	private JPanel taskPanel;
	private ArrayList<JList<String>> taskList;

	public SimulatorPanel(int currentServerCount, int maxServerCount) {
		super(new BorderLayout());

		this.maxServerCount = maxServerCount;

		serverPanel = new JPanel(new GridLayout(1, maxServerCount));
		serverDisplayPanels = new ArrayList<>();
		for (int i = 0; i < maxServerCount; i++) {
			ServerDisplayPanel panel = new ServerDisplayPanel();
			if (i < currentServerCount) {
				panel.setStatusText("ON");
			} else {
				panel.setStatusText("OFF");
			}
			panel.setNameText(String.format("Server %d", i));
			panel.setWaitingTimeText("0");
			serverDisplayPanels.add(panel);
			serverPanel.add(panel);
		}
		add(serverPanel, BorderLayout.NORTH);

		taskPanel = new JPanel(new GridLayout(1, maxServerCount));
		taskList = new ArrayList<>();
		for (int i = 0; i < maxServerCount; i++) {
			JList<String> list = new JList<>();
			taskList.add(list);
			JScrollPane sp = new JScrollPane(list);
			taskPanel.add(sp);
		}
		add(taskPanel, BorderLayout.CENTER);
	}

	public synchronized void display(String[][] tasks, String[] waitingTimes) {
		for (int i = 0; i < waitingTimes.length; i++) {
			serverDisplayPanels.get(i).setStatusText("ON");
			serverDisplayPanels.get(i).setWaitingTimeText(waitingTimes[i]);
		}

		for (int i = 0; i < tasks.length; i++) {
			DefaultListModel<String> listModel = new DefaultListModel<>();
			for (int j = 0; j < tasks[i].length; j++) {
				listModel.addElement(tasks[i][j].toString());
			}
			taskList.get(i).setModel(listModel);
		}
	}
	
	public void forceStop() {
		for (int i = 0; i < maxServerCount; i++) {
			serverDisplayPanels.get(i).setStatusText("STOPPED");
			serverDisplayPanels.get(i).setWaitingTimeText("STOPPED");
			
			DefaultListModel<String> listModel = new DefaultListModel<>();
			listModel.addElement("STOPPED");
			taskList.get(i).setModel(listModel);
		}
	}
}
