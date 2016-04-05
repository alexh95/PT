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

public class OrderManagementRemoveProductPanel extends OrderManagementOperationPanel {
	private static final long serialVersionUID = -1114964817237155898L;

	private JNotEditableTable productTable;

	private JTextField productNameTextField;
	private JButton productConfirmButton;
	private JTextField productResultTextField;

	public OrderManagementRemoveProductPanel() {
		super(new GridLayout(1, 2));

		productTable = new JNotEditableTable();
		add(new JScrollPane(productTable));

		JPanel editProductPanel = new JPanel(new GridLayout(4, 1));

		JLabel productOldNameLabel = new JLabel("Selected product name:");
		editProductPanel.add(productOldNameLabel);

		productNameTextField = new JTextField();
		productNameTextField.setEditable(false);
		editProductPanel.add(productNameTextField);

		productConfirmButton = new JButton("Confirm and delete product");
		productConfirmButton.setEnabled(false);
		editProductPanel.add(productConfirmButton);
		
		productResultTextField = new JTextField();
		productResultTextField.setEditable(false);
		editProductPanel.add(productResultTextField);

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

	public void setProductName(String name) {
		productNameTextField.setText(name);
	}
	
	public void setProductResult(String name) {
		productResultTextField.setText(name);
	}

	public void addProductConfirmButtonActionListener(ActionListener l) {
		productConfirmButton.addActionListener(l);
	}
	
	public void setProductConfirmButtonEnabled(boolean enabled) {
		productConfirmButton.setEnabled(enabled);
	}
}
