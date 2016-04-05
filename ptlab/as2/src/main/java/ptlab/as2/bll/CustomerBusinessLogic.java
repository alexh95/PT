package ptlab.as2.bll;

import java.util.ArrayList;

import ptlab.as2.dal.CustomerDataAccess;
import ptlab.as2.model.Customer;
import ptlab.as2.model.table.CustomerTableElement;

public class CustomerBusinessLogic extends OrderManagementBusinessLogic<Customer> {

	private static CustomerBusinessLogic instance;

	public static CustomerBusinessLogic getInstance() {
		if (instance == null) {
			instance = new CustomerBusinessLogic();
		}
		return instance;
	}

	private CustomerBusinessLogic() {
	}

	@Override
	public String[] getKeys() {
		return CustomerDataAccess.getInstance().getKeys();
	}

	@Override
	public void insert(Customer customer) {
		long id = customer.getId();
		String name = customer.getName();
		CustomerTableElement cte = new CustomerTableElement(id, name);

		CustomerDataAccess.getInstance().insertCustomer(cte);
	}

	@Override
	public ArrayList<Customer> get() {
		ArrayList<CustomerTableElement> customerTableElements = CustomerDataAccess.getInstance().getCustomers();

		ArrayList<Customer> customers = new ArrayList<Customer>();
		for (CustomerTableElement cte : customerTableElements) {
			long id = cte.getId();
			String name = cte.getName();
			Customer c = new Customer(id, name);
			customers.add(c);
		}

		return customers;
	}

	@Override
	public void update(Customer customer) {
		long id = customer.getId();
		String name = customer.getName();
		CustomerTableElement cte = new CustomerTableElement(id, name);

		CustomerDataAccess.getInstance().updateCustomer(cte);
	}

	@Override
	public int delete(Customer customer) {
		int dependencies = customerDependencies(customer);
		if (dependencies == 0) {
			long id = customer.getId();
			String name = customer.getName();
			CustomerTableElement cte = new CustomerTableElement(id, name);

			CustomerDataAccess.getInstance().deleteCustomer(cte);
		}

		return dependencies;
	}

	private int customerDependencies(Customer customer) {
		long id = customer.getId();
		String name = customer.getName();
		CustomerTableElement cte = new CustomerTableElement(id, name);

		return CustomerDataAccess.getInstance().customerDependencies(cte);
	}
}
