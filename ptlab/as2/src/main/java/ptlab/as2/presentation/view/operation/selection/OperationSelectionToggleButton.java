package ptlab.as2.presentation.view.operation.selection;

import javax.swing.JToggleButton;

import ptlab.as2.presentation.view.operation.Operation;
import ptlab.as2.presentation.view.operation.OrderManagementOperationPanel;

public class OperationSelectionToggleButton extends JToggleButton {
	private static final long serialVersionUID = -3002733569687417704L;

	private OrderManagementOperationPanel operationPanel;
	
	public OperationSelectionToggleButton(Operation op, OrderManagementOperationPanel operationPanel) {
		super(op.getDisplayName());
		this.operationPanel = operationPanel;
	}
	
	public OrderManagementOperationPanel getOperationPanel() {
		return operationPanel;
	}
}
