package ptlab.as2.presentation.controller.order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ptlab.as2.bll.CustomerBusinessLogic;
import ptlab.as2.bll.OrderBusinessLogic;
import ptlab.as2.bll.OrderProductsBusinessLogic;
import ptlab.as2.bll.ProductBusinessLogic;
import ptlab.as2.dal.ProductDataAccess;
import ptlab.as2.model.Customer;
import ptlab.as2.model.Order;
import ptlab.as2.model.OrderProducts;
import ptlab.as2.model.Product;
import ptlab.as2.presentation.controller.OperationSelectionListener;
import ptlab.as2.presentation.view.operation.order.OrderManagementAddOrderPanel;

public class OrderManagementAddOrderController {

	private OrderManagementAddOrderPanel panel;

	public OrderManagementAddOrderController(OrderManagementAddOrderPanel panel) {
		this.panel = panel;

		this.panel.addOperationSelectionListener(new AddOrderOperationSelectionListener());
		this.panel.addCustomerTableListSelectionListener(new AddOrderCustomerTableListener());
		this.panel.addProductTableListSelectionListener(new AddOrderProductTableListener());
		this.panel.addConfirmAddProductToOrderButtonActionListener(new AddOrderAddProductToOrderActionListener());
		this.panel.addConfirmOrderButtonActionListener(new AddOrderConfirmActionListener());
	}

	// Add order listeners
	private void initAddOrder() {
		// set customer table
		ArrayList<Customer> customers = CustomerBusinessLogic.getInstance().get();

		String[] keys = CustomerBusinessLogic.getInstance().getKeys();
		String[][] data = new String[customers.size()][keys.length];
		int it = 0;
		for (Customer customer : customers) {
			data[it][0] = "" + customer.getId();
			data[it][1] = customer.getName();
			it++;
		}

		TableModel tm = new DefaultTableModel(data, keys);
		panel.setCustomerTableModel(tm);

		ArrayList<Product> products = ProductBusinessLogic.getInstance().get();

		keys = ProductBusinessLogic.getInstance().getKeys();
		data = new String[products.size()][keys.length];
		it = 0;
		for (Product product : products) {
			data[it][0] = "" + product.getId();
			data[it][1] = product.getName();
			data[it][2] = "" + product.getQuantity();
			it++;
		}

		tm = new DefaultTableModel(data, keys);
		panel.setProductTableModel(tm);

		panel.resetProductQuantityText();
		panel.setProductSelectedQuantityText("");
		panel.setConfirmAddProductToOrderButtonEnabled(false);
		panel.setConfirmOrderButtonEnabled(false);
		panel.setTesultText("");

		currentOrder = null;
		productSelected = false;

		initOrderProductsTable();
	}

	private void initOrderProductsTable() {
		String[] keys = ProductDataAccess.getInstance().getKeys();
		TableModel tm = new DefaultTableModel(keys, 0);
		panel.setOrderProductsTableModel(tm);
	}

	private class AddOrderOperationSelectionListener implements OperationSelectionListener {

		public void operationPanelSelected() {
			initAddOrder();
		}
	}

	private Order currentOrder;

	private class AddOrderCustomerTableListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (panel.getCustomerTableSelectedRow() != -1) {
				long customerId = panel.getSelectedCustomerId();
				currentOrder = new Order(0, customerId);
				if (productSelected) {
					panel.setConfirmAddProductToOrderButtonEnabled(true);
					String quantity = "" + panel.getSelectedProductQuantity();
					panel.setProductSelectedQuantityText(quantity);
				}
				initOrderProductsTable();
			}
		}
	}

	private boolean productSelected;

	private class AddOrderProductTableListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (panel.getProductTableSelectdRow() != -1) {
				productSelected = true;
				if (currentOrder != null) {
					panel.setConfirmAddProductToOrderButtonEnabled(true);
					String quantity = "" + panel.getSelectedProductQuantity();
					panel.setProductSelectedQuantityText(quantity);
				}
			}
		}
	}

	private class AddOrderAddProductToOrderActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			int rows = panel.getOrderProductsRowCount();
			String[] keys = ProductDataAccess.getInstance().getKeys();
			String[][] data = new String[rows + 1][keys.length];
			if (rows > 0) {
				TableModel tmop = panel.getOrderProductsTableModel();
				for (int row = 0; row < tmop.getRowCount(); row++) {
					for (int col = 0; col < tmop.getColumnCount(); col++) {
						data[row][col] = (String) tmop.getValueAt(row, col);
					}
				}
			}

			long productId = panel.getSelectedProductId();
			String name = panel.getSelectedProductName();
			int quantity;
			try {
				quantity = Integer.parseInt(panel.getProductQuantityText());
				if (quantity > 0 && quantity > panel.getSelectedProductQuantity()) {
					panel.setTesultText("Quantity must be positive and lower than the amount in stock");
					panel.resetProductQuantityText();
					return;
				}
			} catch (Exception ex) {
				panel.setTesultText("Must enter a valid number");
				panel.resetProductQuantityText();
				return;
			}

			for (int i = 0; i < rows; i++) {
				if (productId == Long.parseLong(data[i][0])) {
					panel.setTesultText("Product already exists in this order");
					panel.resetProductQuantityText();
					return;
				}
			}

			data[rows][0] = "" + productId;
			data[rows][1] = "" + name;
			data[rows][2] = "" + quantity;

			TableModel tm = new DefaultTableModel(data, keys);
			panel.setOrderProductsTableModel(tm);
			panel.resetProductQuantityText();
			panel.setConfirmOrderButtonEnabled(true);
		}
	}

	private class AddOrderConfirmActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			long customerId = panel.getSelectedCustomerId();
			Order order = new Order(0, customerId);
			OrderBusinessLogic.getInstance().insert(order);

			Order latestOrder = OrderBusinessLogic.getInstance().getLatestOrder();
			long orderId = latestOrder.getId();

			TableModel tm = panel.getOrderProductsTableModel();
			for (int i = 0; i < tm.getRowCount(); i++) {
				long idProduct = Long.parseLong((String) tm.getValueAt(i, 0));
				int quantity = Integer.parseInt((String) tm.getValueAt(i, 2));
				OrderProducts orderProducts = new OrderProducts(orderId, idProduct, quantity);
				OrderProductsBusinessLogic.getInstance().insert(orderProducts);
			}
			initAddOrder();
			panel.setTesultText("Added order successfully");
		}
	}
}
