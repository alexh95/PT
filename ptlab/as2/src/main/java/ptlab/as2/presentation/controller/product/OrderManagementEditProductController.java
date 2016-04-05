package ptlab.as2.presentation.controller.product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ptlab.as2.bll.ProductBusinessLogic;
import ptlab.as2.model.Product;
import ptlab.as2.presentation.controller.OperationSelectionListener;
import ptlab.as2.presentation.view.operation.product.OrderManagementEditProductPanel;

public class OrderManagementEditProductController {

	private OrderManagementEditProductPanel panel;

	public OrderManagementEditProductController(OrderManagementEditProductPanel panel) {
		this.panel = panel;

		this.panel.addOperationSelectionListener(new EditProductsOperationSelectionListener());
		this.panel.addProductTableListSelectionListener(new EditProductTableListener());
		this.panel.addProductConfirmButtonActionListener(new EditProductConfirmActionListener());
	}

	// Edit product listeners
	private void initEditProduct() {
		ArrayList<Product> products = ProductBusinessLogic.getInstance().get();

		String[] keys = ProductBusinessLogic.getInstance().getKeys();
		String[][] data = new String[products.size()][keys.length];
		int it = 0;
		for (Product product : products) {
			data[it][0] = "" + product.getId();
			data[it][1] = product.getName();
			data[it][2] = "" + product.getQuantity();
			it++;
		}

		TableModel tm = new DefaultTableModel(data, keys);
		panel.setProductTableModel(tm);

		String message = "Select a product from the table";
		if (products.isEmpty()) {
			message = "Must add products before they can be edited";
		}
		panel.setProductOldName(message);
		panel.resetProductName();
		panel.setProductConfirmButtonEnabled(false);
	}

	private class EditProductsOperationSelectionListener implements OperationSelectionListener {

		public void operationPanelSelected() {
			initEditProduct();
		}
	}

	private class EditProductTableListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (panel.getProductSelectedTableRow() != -1) {

				String productName = panel.getSelectedProductName();
				panel.setProductOldName(productName);
				panel.setProductConfirmButtonEnabled(true);
			}
		}
	}

	private class EditProductConfirmActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			long id = panel.getSelectedProductId();
			String productName = panel.getProductName();
			int quantity = panel.getSelectedProductQuantity();
			Product product = new Product(id, productName, quantity);
			ProductBusinessLogic.getInstance().update(product);
			initEditProduct();
		}
	}
}
