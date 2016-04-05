package ptlab.as2.presentation.controller.product;

import ptlab.as2.presentation.view.operation.selection.OrderManagementOperationSelectionPanel;

public class OrderManagementProductController {

	private OrderManagementAddProductController addProductController;
	private OrderManagementEditProductController editProductController;
	private OrderManagementRemoveProductController removeProductController;

	public OrderManagementProductController(OrderManagementOperationSelectionPanel selectionPanel) {
		addProductController = new OrderManagementAddProductController(selectionPanel.getAddProductPanel());
		editProductController = new OrderManagementEditProductController(selectionPanel.getEditProductPanel());
		removeProductController = new OrderManagementRemoveProductController(selectionPanel.getRemoveProductPanel());
	}
}
