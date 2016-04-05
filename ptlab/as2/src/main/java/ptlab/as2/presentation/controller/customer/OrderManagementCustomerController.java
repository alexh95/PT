package ptlab.as2.presentation.controller.customer;

import ptlab.as2.presentation.view.operation.selection.OrderManagementOperationSelectionPanel;

public class OrderManagementCustomerController {

	private OperationManagementAddCustomerController addCustomerController;
	private OperationManagementEditCustomerController editCustomerController;
	private OperationManagementRemoveCustomerController removeCustomerController;

	public OrderManagementCustomerController(OrderManagementOperationSelectionPanel selectionPanel) {

		addCustomerController = new OperationManagementAddCustomerController(selectionPanel.getAddCustomerPanel());
		editCustomerController = new OperationManagementEditCustomerController(selectionPanel.getEditCustomerPanel());
		removeCustomerController = new OperationManagementRemoveCustomerController(
				selectionPanel.getRemoveCustomerPanel());
	}
}
