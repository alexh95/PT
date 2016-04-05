package ptlab.as2.dal;

import java.util.ArrayList;

import ptlab.as2.model.table.CustomerTableElement;

public class CustomerDataAccess extends OrderManagementDataAccess {

	private static CustomerDataAccess instance;

	public static CustomerDataAccess getInstance() {
		if (instance == null) {
			instance = new CustomerDataAccess();
		}
		return instance;
	}

	// private constructor - this class is a singleton
	private CustomerDataAccess() {
		super(CustomerTableElement.class);
	}

	public void insertCustomer(CustomerTableElement customer) {
		String[] values = new String[1];
		values[0] = customer.getName();
		insertTableElement(values, false);
	}

	public ArrayList<CustomerTableElement> getCustomers() {
		String[][] customerTable = getTable();

		ArrayList<CustomerTableElement> customerList = new ArrayList<CustomerTableElement>();

		for (String[] tableRow : customerTable) {
			long id = Long.parseLong(tableRow[0]);
			String name = tableRow[1];
			CustomerTableElement cust = new CustomerTableElement(id, name);
			customerList.add(cust);
		}

		return customerList;
	}

	public void updateCustomer(CustomerTableElement customer) {
		String[] values = new String[2];
		values[0] = "" + customer.getId();
		values[1] = customer.getName();
		updateTableElement(values, 1);
	}

	public void deleteCustomer(CustomerTableElement customer) {
		String[] values = new String[2];
		values[0] = "" + customer.getId();
		values[1] = customer.getName();
		deleteTableElement(values, 1);
	}
	
	public int customerDependencies(CustomerTableElement customer) {
		String[] values = new String[2];
		values[0] = "" + customer.getId();
		values[1] = customer.getName();
		
		String key = OrderDataAccess.getInstance().getKeys()[1];
		String tableFrom = OrderDataAccess.getInstance().getTableName();
		
		return getDependencies(values, 0, key, tableFrom);
	}
}
