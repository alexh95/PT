package ptlab.as2.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ptlab.as2.presentation.controller.customer.OrderManagementCustomerController;
import ptlab.as2.presentation.controller.order.OrderManagementOrderController;
import ptlab.as2.presentation.controller.product.OrderManagementProductController;
import ptlab.as2.presentation.controller.stock.OperationManagementStockController;
import ptlab.as2.presentation.view.OrderManagemenFrame;
import ptlab.as2.presentation.view.operation.selection.OperationSelectionToggleButton;

public class OrderManagementController {

	private OrderManagementCustomerController customerController;
	private OrderManagementProductController productController;
	private OrderManagementOrderController orderController;
	private OperationManagementStockController stockController;

	private OrderManagemenFrame frame;

	public OrderManagementController() {
		frame = new OrderManagemenFrame();
		frame.getSelectionPanel().addSelectionButtonsActionListener(new SelectionButtonActionListener());

		customerController = new OrderManagementCustomerController(frame.getSelectionPanel());
		productController = new OrderManagementProductController(frame.getSelectionPanel());
		orderController = new OrderManagementOrderController(frame.getSelectionPanel());
		stockController = new OperationManagementStockController(frame.getSelectionPanel());
	}

	private class SelectionButtonActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			OperationSelectionToggleButton pressedButton = (OperationSelectionToggleButton) e.getSource();
			frame.getOperationPanel().setSelectedOperationPanel(pressedButton.getOperationPanel());
		}
	}
}
