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
import ptlab.as2.presentation.view.operation.product.OrderManagementRemoveProductPanel;

public class OrderManagementRemoveProductController {

	private OrderManagementRemoveProductPanel panel;

	public OrderManagementRemoveProductController(OrderManagementRemoveProductPanel panel) {
		this.panel = panel;

		this.panel.addOperationSelectionListener(new RemoveProductOperationSelectionListener());
		this.panel.addProductTableListSelectionListener(new RemoveProductTableListener());
		this.panel.addProductConfirmButtonActionListener(new RemoveProductConfirmActionListener());
	}

	// Remove product listeners
	private void initRemoveProduct() {
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
			message = "Must add products before they can be deleted";
		}
		panel.setProductName(message);
		panel.setProductResult("");
		panel.setProductConfirmButtonEnabled(false);
	}

	private class RemoveProductOperationSelectionListener implements OperationSelectionListener {

		public void operationPanelSelected() {
			initRemoveProduct();
		}
	}

	private class RemoveProductTableListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (panel.getProductSelectedTableRow() != -1) {

				String productName = panel.getSelectedProductName();
				panel.setProductName(productName);
				panel.setProductConfirmButtonEnabled(true);
			}
		}
	}

	private class RemoveProductConfirmActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			long id = panel.getSelectedProductId();
			String productName = panel.getSelectedProductName();
			int quantity = panel.getSelectedProductQuantity();
			Product product = new Product(id, productName, quantity);

			int dependencies = ProductBusinessLogic.getInstance().delete(product);
			if (dependencies > 0) {
				panel.setProductResult(
						"Cannot delete the product! " + dependencies + " dependecies still exist in `OrderProduct`");
			} else {
				initRemoveProduct();
				panel.setProductResult("Product deleted successfully!");
			}
		}
	}
}
