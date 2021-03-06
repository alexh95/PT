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
import ptlab.as2.presentation.view.operation.customer.OrderManagementRemoveCustomerPanel;

public class OperationManagementRemoveCustomerController {

	private OrderManagementRemoveCustomerPanel panel;

	public OperationManagementRemoveCustomerController(OrderManagementRemoveCustomerPanel panel) {
		this.panel = panel;

		this.panel.addOperationSelectionListener(new RemoveCustomersOperationSelectionListener());
		this.panel.addCustomerTableListSelectionListener(new RemoveCustomerTableListener());
		this.panel.addCustomerConfirmButtonActionListener(new RemoveCustomerConfirmActionListener());

	}

	// Remove customer listeners
	private void initRemoveCustomer() {
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
			message = "Must add customers before they can be deleted";
		}
		panel.setCustomerName(message);
		panel.setCustomerResult("");
		panel.setCustomerConfirmButtonEnabled(false);
	}

	private class RemoveCustomersOperationSelectionListener implements OperationSelectionListener {

		public void operationPanelSelected() {
			initRemoveCustomer();
		}
	}

	private class RemoveCustomerTableListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (panel.getCustomerSelectedTableRow() != -1) {

				String customerName = panel.getSelectedCustomerName();
				panel.setCustomerName(customerName);
				panel.setCustomerConfirmButtonEnabled(true);
			}
		}
	}

	private class RemoveCustomerConfirmActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			long id = panel.getSelectedCustomerId();
			String customerName = panel.getSelectedCustomerName();
			Customer customer = new Customer(id, customerName);

			int dependencies = CustomerBusinessLogic.getInstance().delete(customer);
			if (dependencies > 0) {
				panel.setCustomerResult(
						"Cannot delete the customer! " + dependencies + " dependecies still exist in `Order`");
			} else {
				initRemoveCustomer();
				panel.setCustomerResult("Customer deleted successfully!");
			}
		}
	}
}
