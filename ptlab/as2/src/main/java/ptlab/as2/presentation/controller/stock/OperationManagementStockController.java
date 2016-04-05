package ptlab.as2.presentation.controller.stock;

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
import ptlab.as2.presentation.view.operation.selection.OrderManagementOperationSelectionPanel;

public class OperationManagementStockController {

	private OrderManagementOperationSelectionPanel selectionPanel;

	public OperationManagementStockController(OrderManagementOperationSelectionPanel selectionPanel) {
		this.selectionPanel = selectionPanel;

		this.selectionPanel.getEditStockPanel()
				.addOperationSelectionListener(new EditStockOperationSelectionListener());
		this.selectionPanel.getEditStockPanel().addProductTableListSelectionListener(new EditStockTableListener());
		this.selectionPanel.getEditStockPanel()
				.addProductConfirmButtonActionListener(new EditStockConfirmActionListener());

		initEditStock();
	}

	// Edit stock listeners
	private void initEditStock() {
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
		selectionPanel.getEditStockPanel().setStockTableModel(tm);

		String message = "Select a product from the table";
		if (products.isEmpty()) {
			message = "Must add products before the stock can be edited";
		}
		selectionPanel.getEditStockPanel().setProductOldName(message);
		selectionPanel.getEditStockPanel().setProductOldQuantity("");
		selectionPanel.getEditStockPanel().setProductConfirmButtonEnabled(false);
		selectionPanel.getEditStockPanel().resetProductQuantity();
		selectionPanel.getEditStockPanel().setStockResult("");
	}

	private class EditStockOperationSelectionListener implements OperationSelectionListener {

		public void operationPanelSelected() {
			initEditStock();
		}
	}

	private class EditStockTableListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (selectionPanel.getEditStockPanel().getStockSelectedTableRow() != -1) {

				String productName = selectionPanel.getEditStockPanel().getSelectedProductName();
				selectionPanel.getEditStockPanel().setProductOldName(productName);
				int productQuantity = selectionPanel.getEditStockPanel().getSelectedProductQuantity();
				selectionPanel.getEditStockPanel().setProductOldQuantity("" + productQuantity);
				selectionPanel.getEditStockPanel().setProductConfirmButtonEnabled(true);
			}
		}
	}

	private class EditStockConfirmActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			long id = selectionPanel.getEditStockPanel().getSelectedProductId();
			String productName = selectionPanel.getEditStockPanel().getSelectedProductName();
			String quantityString = selectionPanel.getEditStockPanel().getProductQuantity();

			try {
				int quantity = Integer.parseInt(quantityString);
				if (quantity <= 0) {
					selectionPanel.getEditStockPanel().setStockResult("Quantity must be a positive numer");
				}
				Product product = new Product(id, productName, quantity);
				ProductBusinessLogic.getInstance().update(product);
				initEditStock();
			} catch (Exception ex) {
				selectionPanel.getEditStockPanel().resetProductQuantity();
				selectionPanel.getEditStockPanel().setStockResult("Must enter a valid quantity");
			}
		}
	}
}
