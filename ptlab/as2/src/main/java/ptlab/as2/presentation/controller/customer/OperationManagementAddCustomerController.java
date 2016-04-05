package ptlab.as2.presentation.controller.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ptlab.as2.bll.CustomerBusinessLogic;
import ptlab.as2.model.Customer;
import ptlab.as2.presentation.controller.OperationSelectionListener;
import ptlab.as2.presentation.view.operation.customer.OrderManagementAddCustomerPanel;

public class OperationManagementAddCustomerController {

	private OrderManagementAddCustomerPanel panel;

	public OperationManagementAddCustomerController(OrderManagementAddCustomerPanel panel) {
		this.panel = panel;

		this.panel.addOperationSelectionListener(new AddCustomersOperationSelectionListener());
		this.panel.addCustomerConfirmButtonActionListener(new AddCustomerConfirmActionListener());
	}

	// Add customer listeners
	private void initAddCustomer() {
		ArrayList<Customer> customers = CustomerBusinessLogic.getInstance().get();

		String[] keys = CustomerBusinessLogic.getInstance().getKeys();
		String[][] data = new String[customers.size()][keys.length];
		int it = 0;
		for (Customer customer : customers) {
			data[it][0] = "" + customer.getId();
			data[it][1] = customer.getName();
			it++;
		}

		TableModel tm = new DefaultTableModel(data, keys);
		panel.setCustomerTableModel(tm);

		panel.resetCustomerName();
	}

	private class AddCustomersOperationSelectionListener implements OperationSelectionListener {

		public void operationPanelSelected() {
			initAddCustomer();
		}
	}

	private class AddCustomerConfirmActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String customerName = panel.getCustomerName();
			Customer customer = new Customer(0, customerName);
			CustomerBusinessLogic.getInstance().insert(customer);
			initAddCustomer();
		}
	}
}
