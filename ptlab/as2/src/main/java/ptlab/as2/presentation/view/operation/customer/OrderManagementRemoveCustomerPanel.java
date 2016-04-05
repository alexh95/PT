package ptlab.as2.presentation.view.operation.customer;

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

public class OrderManagementRemoveCustomerPanel extends OrderManagementOperationPanel {
	private static final long serialVersionUID = 2779658688557277535L;

	private JNotEditableTable customerTable;

	private JTextField customerNameTextField;
	private JButton customerConfirmButton;
	private JTextField customerResultTextField;

	public OrderManagementRemoveCustomerPanel() {
		super(new GridLayout(1, 2));

		customerTable = new JNotEditableTable();
		add(new JScrollPane(customerTable));

		JPanel editCustomerPanel = new JPanel(new GridLayout(4, 1));

		JLabel customerOldNameLabel = new JLabel("Selected customer name:");
		editCustomerPanel.add(customerOldNameLabel);

		customerNameTextField = new JTextField();
		customerNameTextField.setEditable(false);
		editCustomerPanel.add(customerNameTextField);

		customerConfirmButton = new JButton("Confirm and delete customer");
		customerConfirmButton.setEnabled(false);
		editCustomerPanel.add(customerConfirmButton);
		
		customerResultTextField = new JTextField();
		customerResultTextField.setEditable(false);
		editCustomerPanel.add(customerResultTextField);

		add(editCustomerPanel);
	}

	public void addCustomerTableListSelectionListener(ListSelectionListener l) {
		customerTable.getSelectionModel().addListSelectionListener(l);
	}
	
	public void setCustomerTableModel(TableModel tm) {
		customerTable.setModel(tm);
	}
	
	public int getCustomerSelectedTableRow() {
		return customerTable.getSelectedRow();
	}
	
	public long getSelectedCustomerId() {
		int selectedRow = customerTable.getSelectedRow();
		return Long.parseLong((String) customerTable.getValueAt(selectedRow, 0));
	}
	
	public String getSelectedCustomerName() {
		int selectedRow = customerTable.getSelectedRow();
		return (String) customerTable.getValueAt(selectedRow, 1);
	}

	public void setCustomerName(String name) {
		customerNameTextField.setText(name);
	}
	
	public void setCustomerResult(String name) {
		customerResultTextField.setText(name);
	}

	public void addCustomerConfirmButtonActionListener(ActionListener l) {
		customerConfirmButton.addActionListener(l);
	}
	
	public void setCustomerConfirmButtonEnabled(boolean enabled) {
		customerConfirmButton.setEnabled(enabled);
	}
}
