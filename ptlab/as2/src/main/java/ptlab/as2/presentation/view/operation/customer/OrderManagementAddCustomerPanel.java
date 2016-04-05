package ptlab.as2.presentation.view.operation.customer;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import ptlab.as2.presentation.view.operation.JNotEditableTable;
import ptlab.as2.presentation.view.operation.OrderManagementOperationPanel;

public class OrderManagementAddCustomerPanel extends OrderManagementOperationPanel {
	private static final long serialVersionUID = 6483178971722550319L;

	private JNotEditableTable customerTable;
	
	private JTextField customerNameTextField;
	private JButton customerConfirmButton;
	
	public OrderManagementAddCustomerPanel() {
		super(new GridLayout(1, 2));
		
		customerTable = new JNotEditableTable(); 
		add(new JScrollPane(customerTable));
		
		JPanel addCustomerPanel = new JPanel(new GridLayout(3, 1));
		JLabel clientNameLabel = new JLabel("Customer name:");
		addCustomerPanel.add(clientNameLabel);
		customerNameTextField = new JTextField();
		addCustomerPanel.add(customerNameTextField);
		customerConfirmButton = new JButton("Confirm and add Customer");
		addCustomerPanel.add(customerConfirmButton);
		add(addCustomerPanel);
	}
	
	public void setCustomerTableModel(TableModel tm) {
		customerTable.setModel(tm);
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
}
