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

public class OrderManagementEditCustomerPanel extends OrderManagementOperationPanel {
	private static final long serialVersionUID = 3132958000251584429L;

	private JNotEditableTable customerTable;

	private JTextField customerOldNameTextField;
	private JTextField customerNameTextField;
	private JButton customerConfirmButton;

	public OrderManagementEditCustomerPanel() {
		super(new GridLayout(1, 2));

		customerTable = new JNotEditableTable(); 
		add(new JScrollPane(customerTable));

		JPanel editCustomerPanel = new JPanel(new GridLayout(5, 1));

		JLabel customerOldNameLabel = new JLabel("Current customer name:");
		editCustomerPanel.add(customerOldNameLabel);

		customerOldNameTextField = new JTextField();
		customerOldNameTextField.setEditable(false);
		editCustomerPanel.add(customerOldNameTextField);

		JLabel customerNameLabel = new JLabel("New customer name:");
		editCustomerPanel.add(customerNameLabel);

		customerNameTextField = new JTextField();
		editCustomerPanel.add(customerNameTextField);

		customerConfirmButton = new JButton("Confirm and edit customer");
		customerConfirmButton.setEnabled(false);
		editCustomerPanel.add(customerConfirmButton);

		add(editCustomerPanel);
	}

	public void addCustomerTableListSelectionListener(ListSelectionListener l) {
		customerTable.getSelectionModel().addListSelectionListener(l);
	}
	
	public int getCustomerSelectedTableRow() {
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

	public void setCustomerOldName(String name) {
		customerOldNameTextField.setText(name);
	}
	
	public void resetCustomerName() {
		customerNameTextField.setText("");
	}

	public String getCustomerName() {
		return customerNameTextField.getText();
	}

	public void addCustomerConfirmButtonActionListener(ActionListener l) {
		customerConfirmButton.addActionListener(l);
	}
	
	public void setCustomerConfirmButtonEnabled(boolean enabled) {
		customerConfirmButton.setEnabled(enabled);
	}
}
