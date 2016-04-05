package ptlab.as2.presentation.controller.order;

import ptlab.as2.presentation.view.operation.selection.OrderManagementOperationSelectionPanel;

public class OrderManagementOrderController {

	private OrderManagementAddOrderController addOrderController;
	private OrderManagementEditOrderController editOrderController;
	private OrderManagementRemoveOrderController removeOrderController;

	public OrderManagementOrderController(OrderManagementOperationSelectionPanel selectionPanel) {

		addOrderController = new OrderManagementAddOrderController(selectionPanel.getAddOrderPanel());
		editOrderController = new OrderManagementEditOrderController(selectionPanel.getEditOrderPanel());
		removeOrderController = new OrderManagementRemoveOrderController(selectionPanel.getRemoveOrderPanel());
	}
}
