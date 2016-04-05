package ptlab.as2.presentation.view.operation;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class OrderManagementOperationPanelHolder extends JPanel {
	private static final long serialVersionUID = 7311603117513158993L;

	OrderManagementOperationPanel selectedOperationPanel;

	public OrderManagementOperationPanelHolder(OrderManagementOperationPanel selectedOperationPanel) {
		super(new BorderLayout());

		this.selectedOperationPanel = selectedOperationPanel;
		add(selectedOperationPanel, BorderLayout.CENTER);

		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
	}

	public void setSelectedOperationPanel(OrderManagementOperationPanel selectedOperationPanel) {
		selectedOperationPanel.operationPanelSelected();
		remove(this.selectedOperationPanel);
		this.selectedOperationPanel = selectedOperationPanel;
		add(this.selectedOperationPanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
}
