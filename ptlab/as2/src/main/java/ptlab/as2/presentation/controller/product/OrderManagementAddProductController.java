package ptlab.as2.presentation.controller.product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ptlab.as2.bll.ProductBusinessLogic;
import ptlab.as2.model.Product;
import ptlab.as2.presentation.controller.OperationSelectionListener;
import ptlab.as2.presentation.view.operation.product.OrderManagementAddProductPanel;

public class OrderManagementAddProductController {

	private OrderManagementAddProductPanel panel;

	public OrderManagementAddProductController(OrderManagementAddProductPanel panel) {
		this.panel = panel;

		this.panel.addOperationSelectionListener(new AddProductOperationSelectionListener());
		this.panel.addProductConfirmButtonActionListener(new AddProductConfirmActionListener());

	}

	// Add product listeners
	private void initAddProduct() {
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
		
		panel.resetProductName();
	}

	private class AddProductOperationSelectionListener implements OperationSelectionListener {

		public void operationPanelSelected() {
			initAddProduct();
		}
	}

	private class AddProductConfirmActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String productName = panel.getProductName();
			Product product = new Product(0, productName, 0);
			ProductBusinessLogic.getInstance().insert(product);
			initAddProduct();
		}
	}
}
