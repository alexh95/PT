package ptlab.as2.presentation.view.operation.order;

import java.awt.BorderLayout;
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

public class OrderManagementAddOrderPanel extends OrderManagementOperationPanel {
	private static final long serialVersionUID = 2504846898228831631L;

	private JNotEditableTable customerTable;	
	private JNotEditableTable productTable;
	
	private JTextField productSelectedQuantity;
	private JTextField productQuantity;
	private JButton confirmAddProductToOrderButton;
	private JTextField resultTextField;
	
	private JNotEditableTable orderProductsTable;
	
	private JButton confirmOrderButton;	
	
	public OrderManagementAddOrderPanel() {
		super(new BorderLayout());
		
		JPanel orderPanel = new JPanel(new GridLayout(2, 1));
		// Customer panel
		JPanel customerSelectionPanel = new JPanel(new BorderLayout());
		// instruction label
		JLabel customerSelectionLabel = new JLabel("Select a customer to begin");
		customerSelectionPanel.add(customerSelectionLabel, BorderLayout.NORTH);
		// customer table
		customerTable = new JNotEditableTable();
		customerSelectionPanel.add(new JScrollPane(customerTable), BorderLayout.CENTER);
		orderPanel.add(customerSelectionPanel);
		
		// Order products panel
		JPanel orderProductsPanel = new JPanel(new GridLayout(1, 3));
		// products table
		productTable = new JNotEditableTable();
		orderProductsPanel.add(new JScrollPane(productTable));
		
		// orderProducts table
		JPanel productToOrderPanel = new JPanel(new GridLayout(7, 1));
		// selected quantity label
		JLabel productSelectedQuantityLabel = new JLabel("Selected product stock");
		productToOrderPanel.add(productSelectedQuantityLabel);
		// selected quantity text field
		productSelectedQuantity = new JTextField();
		productSelectedQuantity.setEditable(false);
		productToOrderPanel.add(productSelectedQuantity);
		// quantity label
		JLabel productQuantityLabel = new JLabel("Enter product quantity");
		productToOrderPanel.add(productQuantityLabel);
		// quantity text field
		productQuantity = new JTextField();
		productToOrderPanel.add(productQuantity);
		// add to order label
		JLabel confirmAddProductToOrderLabel = new JLabel("Add product to order");
		productToOrderPanel.add(confirmAddProductToOrderLabel);
		// add to order button
		confirmAddProductToOrderButton = new JButton("Add product to order");
		productToOrderPanel.add(confirmAddProductToOrderButton);
		// add result text field
		resultTextField = new JTextField();
		resultTextField.setEditable(false);
		productToOrderPanel.add(resultTextField);
		orderProductsPanel.add(productToOrderPanel);
		
		// order products table
		orderProductsTable = new JNotEditableTable();
		orderProductsPanel.add(new JScrollPane(orderProductsTable));
		
		orderPanel.add(orderProductsPanel);
		
		add(orderPanel, BorderLayout.CENTER);
		
		// confirm button
		confirmOrderButton = new JButton("Confirm and add order");
		confirmOrderButton.setEnabled(false);
		add(confirmOrderButton, BorderLayout.SOUTH);
	}
	
	public void addCustomerTableListSelectionListener(ListSelectionListener l) {
		customerTable.getSelectionModel().addListSelectionListener(l);
	}
	
	public int getCustomerTableSelectedRow() {
		return customerTable.getSelectedRow();
	}
	
	public void setCustomerTableModel(TableModel tm) {
		customerTable.setModel(tm);
	}
	
	public long getSelectedCustomerId() {
		int selectedRow = customerTable.getSelectedRow();
		return Long.parseLong((String) customerTable.getValueAt(selectedRow, 0));
	}
	
	public String getSelectedCustomerName() {
		int selectedRow = customerTable.getSelectedRow();
		return (String) customerTable.getValueAt(selectedRow, 1);
	}
	
	public void addProductTableListSelectionListener(ListSelectionListener l) {
		productTable.getSelectionModel().addListSelectionListener(l);
	}
	
	public void setProductTableModel(TableModel tm) {
		productTable.setModel(tm);
	}
	
	public int getProductTableSelectdRow() {
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
	
	public void setProductSelectedQuantityText(String quantity) {
		productSelectedQuantity.setText(quantity);
	}
	
	public void resetProductQuantityText() {
		productQuantity.setText("");
	}
	
	public String getProductQuantityText() {
		return productQuantity.getText();
	}
	
	public int getOrderProductsRowCount() {
		return orderProductsTable.getRowCount();
	}
	
	public TableModel getOrderProductsTableModel() {
		return orderProductsTable.getModel();
	}
	
	public void setOrderProductsTableModel(TableModel tm) {
		orderProductsTable.setModel(tm);
	}
	
	public void addConfirmAddProductToOrderButtonActionListener(ActionListener l) {
		confirmAddProductToOrderButton.addActionListener(l);
	}
	
	public void setConfirmAddProductToOrderButtonEnabled(boolean b) {
		confirmAddProductToOrderButton.setEnabled(b);
	}
	
	public void addConfirmOrderButtonActionListener(ActionListener l) {
		confirmOrderButton.addActionListener(l);
	}
	
	public void setConfirmOrderButtonEnabled(boolean b) {
		confirmOrderButton.setEnabled(b);
	}
	
	public void setTesultText(String result) {
		resultTextField.setText(result);
	}
}
