package ptlab.as2.presentation.view.operation;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import ptlab.as2.presentation.controller.OperationSelectionListener;

public abstract class OrderManagementOperationPanel extends JPanel {
	private static final long serialVersionUID = -6455325577631563817L;
	
	private List<OperationSelectionListener> selectionListeners; 
	
	public OrderManagementOperationPanel(LayoutManager layoutManager) {
		super(layoutManager);
		selectionListeners = new ArrayList<OperationSelectionListener>();
	}
	
	public OrderManagementOperationPanel() {
		this(new FlowLayout());
	}
	
	public void addOperationSelectionListener(OperationSelectionListener l) {
		selectionListeners.add(l);
	}
	
	public void operationPanelSelected() {
		for (OperationSelectionListener l : selectionListeners) {
			l.operationPanelSelected();
		}
	}
}
