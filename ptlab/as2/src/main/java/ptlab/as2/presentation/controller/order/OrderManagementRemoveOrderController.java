package ptlab.as2.presentation.controller.order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ptlab.as2.bll.OrderBusinessLogic;
import ptlab.as2.bll.OrderProductsBusinessLogic;
import ptlab.as2.bll.ReceiptBusinessLogic;
import ptlab.as2.model.Order;
import ptlab.as2.model.OrderProducts;
import ptlab.as2.presentation.controller.OperationSelectionListener;
import ptlab.as2.presentation.view.operation.order.OrderManagementRemoveOrderPanel;

public class OrderManagementRemoveOrderController {

	private OrderManagementRemoveOrderPanel panel;

	public OrderManagementRemoveOrderController(OrderManagementRemoveOrderPanel panel) {
		this.panel = panel;

		panel.addOperationSelectionListener(new RemoveOrderOperationSelectionListener());
		panel.addOrderTableListSelectionListener(new RemoveOrderOrderTableListSelectionListener());
		panel.addOrderConfirmDeleteActionListener(new RemoveOrderConfirmDeleteActionListener());
		panel.addPrintReceiptActionListener(new PrintReceiptButtonActionListener());
	}

	// Remove order listeners
	private void initRemoveOrder() {
		ArrayList<Order> orders = OrderBusinessLogic.getInstance().get();

		String[] keys = OrderBusinessLogic.getInstance().getKeys();
		String[][] data = new String[orders.size()][keys.length];
		int it = 0;
		for (Order order : orders) {
			data[it][0] = "" + order.getId();
			data[it][1] = "" + order.getCustomerId();
			it++;
		}

		TableModel tm = new DefaultTableModel(data, keys);
		panel.setProductTableModel(tm);

		String[] opKeys = OrderProductsBusinessLogic.getInstance().getKeys();
		TableModel tmop = new DefaultTableModel(opKeys, 0);
		panel.setOrderProductsTableModel(tmop);

		panel.setOrderConfirmDeleteButtonEnabled(false);
		panel.setOrderDeleteResultText("");
		panel.setPrintReceiptEnabled(false);
	}

	private class RemoveOrderOperationSelectionListener implements OperationSelectionListener {

		public void operationPanelSelected() {
			initRemoveOrder();
		}
	}

	private class RemoveOrderOrderTableListSelectionListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			int selectedRow = panel.getOrderTableSelectedRow();
			if (selectedRow != -1) {
				long selectedOrderId = panel.getSelectedOrderId();
				long selectedCustomerId = panel.getSelectedOrderCustomerId();
				Order order = new Order(selectedOrderId, selectedCustomerId);
				ArrayList<OrderProducts> orderProducts = OrderProductsBusinessLogic.getInstance()
						.getOrderProductsInOrder(order);

				String[] keys = OrderProductsBusinessLogic.getInstance().getKeys();
				String[][] data = new String[orderProducts.size()][keys.length];
				int it = 0;
				for (OrderProducts op : orderProducts) {
					data[it][0] = "" + op.getIdOrder();
					data[it][1] = "" + op.getIdProduct();
					data[it][2] = "" + op.getQuantity();
					it++;
				}

				TableModel tm = new DefaultTableModel(data, keys);
				panel.setOrderProductsTableModel(tm);
				panel.setOrderConfirmDeleteButtonEnabled(true);
				panel.setPrintReceiptEnabled(true);
			}
		}
	}

	private class RemoveOrderConfirmDeleteActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			long selectedOrderId = panel.getSelectedOrderId();
			long selectedCustomerId = panel.getSelectedOrderCustomerId();
			Order order = new Order(selectedOrderId, selectedCustomerId);

			ArrayList<OrderProducts> orderProducts = OrderProductsBusinessLogic.getInstance()
					.getOrderProductsInOrder(order);
			for (OrderProducts op : orderProducts) {
				OrderProductsBusinessLogic.getInstance().delete(op);
			}

			OrderBusinessLogic.getInstance().delete(order);

			initRemoveOrder();
			panel.setOrderDeleteResultText("Order successfully deleted");
		}
	}

	private class PrintReceiptButtonActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int selectedRow = panel.getOrderTableSelectedRow();
			if (selectedRow != -1) {
				long selectedOrderId = panel.getSelectedOrderId();
				long selectedCustomerId = panel.getSelectedOrderCustomerId();
				Order order = new Order(selectedOrderId, selectedCustomerId);
				ArrayList<OrderProducts> orderProducts = OrderProductsBusinessLogic.getInstance()
						.getOrderProductsInOrder(order);

				ReceiptBusinessLogic.getInstance().printReceipt(order, orderProducts);
				panel.setOrderDeleteResultText("Receipt successfully printed");
			}
		}
	}
}
