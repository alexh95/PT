package ptlab.as2.presentation.view.operation.product;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import ptlab.as2.presentation.view.operation.JNotEditableTable;
import ptlab.as2.presentation.view.operation.OrderManagementOperationPanel;

public class OrderManagementEditProductPanel extends OrderManagementOperationPanel {
	private static final long serialVersionUID = -3960565455664191642L;

	private JNotEditableTable productTable;

	private JTextField productOldNameTextField;
	private JTextField productNameTextField;
	private JButton productConfirmButton;

	public OrderManagementEditProductPanel() {
		super(new GridLayout(1, 2));

		productTable = new JNotEditableTable();
		add(new JScrollPane(productTable));

		JPanel editProductPanel = new JPanel(new GridLayout(5, 1));

		JLabel productOldNameLabel = new JLabel("Current Product name:");
		editProductPanel.add(productOldNameLabel);

		productOldNameTextField = new JTextField();
		productOldNameTextField.setEditable(false);
		editProductPanel.add(productOldNameTextField);

		JLabel productNameLabel = new JLabel("New product name:");
		editProductPanel.add(productNameLabel);

		productNameTextField = new JTextField();
		editProductPanel.add(productNameTextField);

		productConfirmButton = new JButton("Confirm and edit product");
		productConfirmButton.setEnabled(false);
		editProductPanel.add(productConfirmButton);

		add(editProductPanel);
	}
	
	public void addProductTableListSelectionListener(ListSelectionListener l) {
		productTable.getSelectionModel().addListSelectionListener(l);
	}
	
	public void setProductTableModel(TableModel tm) {
		productTable.setModel(tm);
	}
	
	public int getProductSelectedTableRow() {
		return productTable.getSelectedRow();
	}
	
	public long getSelectedProductId() {
		int selectedRow = productTable.getSelectedRow();
		return Long.parseLong((String) productTable.getValueAt(selectedRow, 0));
	}

	public String getSelectedProductName() {
		int selectedRow = productTable.getSelectedRow();
		return (String) productTable.getValueAt(selectedRow, 1);
	}
	
	public int getSelectedProductQuantity() {
		int selectedRow = productTable.getSelectedRow();
		return Integer.parseInt((String) productTable.getValueAt(selectedRow, 2));
	}

	public void setProductOldName(String name) {
		productOldNameTextField.setText(name);
	}
	
	public void resetProductName() {
		productNameTextField.setText("");
	}

	public String getProductName() {
		return productNameTextField.getText();
	}

	public void addProductConfirmButtonActionListener(ActionListener l) {
		productConfirmButton.addActionListener(l);
	}
	
	public void setProductConfirmButtonEnabled(boolean enabled) {
		productConfirmButton.setEnabled(enabled);
	}
}
