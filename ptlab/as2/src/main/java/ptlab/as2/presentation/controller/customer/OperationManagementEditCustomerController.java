package ptlab.as2.presentation.controller.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ptlab.as2.bll.CustomerBusinessLogic;
import ptlab.as2.model.Customer;
import ptlab.as2.presentation.controller.OperationSelectionListener;
import ptlab.as2.presentation.view.operation.customer.OrderManagementEditCustomerPanel;

public class OperationManagementEditCustomerController {

	private OrderManagementEditCustomerPanel panel;

	public OperationManagementEditCustomerController(OrderManagementEditCustomerPanel panel) {
		this.panel = panel;

		this.panel.addOperationSelectionListener(new EditCustomersOperationSelectionListener());
		this.panel.addCustomerTableListSelectionListener(new EditCustomerTableListener());
		this.panel.addCustomerConfirmButtonActionListener(new EditCustomerConfirmActionListener());
	}

	// Edit customer listeners
	private void initEditCustomer() {
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

		String message = "Select a customer from the table";
		if (customers.isEmpty()) {
			message = "Must add customers before they can be edited";
		}
		panel.setCustomerOldName(message);
		panel.resetCustomerName();
		panel.setCustomerConfirmButtonEnabled(false);
	}

	private class EditCustomersOperationSelectionListener implements OperationSelectionListener {

		public void operationPanelSelected() {
			initEditCustomer();
		}
	}

	private class EditCustomerTableListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (panel.getCustomerSelectedTableRow() != -1) {

				String customerName = panel.getSelectedCustomerName();
				panel.setCustomerOldName(customerName);
				panel.setCustomerConfirmButtonEnabled(true);
			}
		}
	}

	private class EditCustomerConfirmActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			long id = panel.getSelectedCustomerId();
			String customerName = panel.getCustomerName();
			Customer customer = new Customer(id, customerName);
			CustomerBusinessLogic.getInstance().update(customer);
			initEditCustomer();
		}
	}
}
