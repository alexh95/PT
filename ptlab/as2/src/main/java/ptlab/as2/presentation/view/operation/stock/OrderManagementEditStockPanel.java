package ptlab.as2.presentation.view.operation.stock;

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

public class OrderManagementEditStockPanel extends OrderManagementOperationPanel {
	private static final long serialVersionUID = 1379985223055701614L;

	private JNotEditableTable stockTable;

	private JTextField stockOldNameTextField;
	private JTextField stockOldQuantityTextField;
	private JTextField stockQuantityTextField;
	private JButton stockConfirmButton;
	private JTextField stockResultTextField;

	public OrderManagementEditStockPanel() {
		super(new GridLayout(1, 2));

		stockTable = new JNotEditableTable();
		add(new JScrollPane(stockTable));

		JPanel stockProductPanel = new JPanel(new GridLayout(8, 1));

		JLabel stockOldNameLabel = new JLabel("Current product name:");
		stockProductPanel.add(stockOldNameLabel);

		stockOldNameTextField = new JTextField();
		stockOldNameTextField.setEditable(false);
		stockProductPanel.add(stockOldNameTextField);

		JLabel stockOldQuantityLabel = new JLabel("Current product quantity:");
		stockProductPanel.add(stockOldQuantityLabel);

		stockOldQuantityTextField = new JTextField();
		stockOldQuantityTextField.setEditable(false);
		stockProductPanel.add(stockOldQuantityTextField);
		
		JLabel stockQuantityLabel = new JLabel("Enter new product quantity:");
		stockProductPanel.add(stockQuantityLabel);
		
		stockQuantityTextField = new JTextField();
		stockProductPanel.add(stockQuantityTextField);

		stockConfirmButton = new JButton("Confirm and edit product quantity");
		stockConfirmButton.setEnabled(false);
		stockProductPanel.add(stockConfirmButton);
		
		stockResultTextField = new JTextField();
		stockResultTextField.setEditable(false);
		stockProductPanel.add(stockResultTextField);

		add(stockProductPanel);
	}
	
	public void addProductTableListSelectionListener(ListSelectionListener l) {
		stockTable.getSelectionModel().addListSelectionListener(l);
	}
	
	public int getStockSelectedTableRow() {
		return stockTable.getSelectedRow();
	}
	
	public int getStockTableRowCount() {
		return stockTable.getRowCount();
	}

	public void setStockTableModel(TableModel tm) {
		stockTable.setModel(tm);
	}
	
	public long getSelectedProductId() {
		int selectedRow = stockTable.getSelectedRow();
		return Long.parseLong((String) stockTable.getValueAt(selectedRow, 0));
	}

	public String getSelectedProductName() {
		int selectedRow = stockTable.getSelectedRow();
		return (String) stockTable.getValueAt(selectedRow, 1);
	}
	
	public int getSelectedProductQuantity() {
		int selectedRow = stockTable.getSelectedRow();
		return Integer.parseInt((String) stockTable.getValueAt(selectedRow, 2));
	}

	public void setProductOldName(String name) {
		stockOldNameTextField.setText(name);
	}
	
	public void setProductOldQuantity(String quantity) {
		stockOldQuantityTextField.setText(quantity);
	}

	public String getProductQuantity() {
		return stockQuantityTextField.getText();
	}
	
	public void resetProductQuantity() {
		stockQuantityTextField.setText("");
	}
	
	public void setStockResult(String result) {
		stockResultTextField.setText(result);
	}

	public void addProductConfirmButtonActionListener(ActionListener l) {
		stockConfirmButton.addActionListener(l);
	}
	
	public void setProductConfirmButtonEnabled(boolean enabled) {
		stockConfirmButton.setEnabled(enabled);
	}
}
