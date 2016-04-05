package ptlab.as2.dal;

import java.util.ArrayList;

import ptlab.as2.model.table.OrderTableElement;

public class OrderDataAccess extends OrderManagementDataAccess {

	private static OrderDataAccess instance;

	public static OrderDataAccess getInstance() {
		if (instance == null) {
			instance = new OrderDataAccess();
		}
		return instance;
	}

	// private constructor - this class is a singleton
	private OrderDataAccess() {
		super(OrderTableElement.class);
	}

	public void insertOrder(OrderTableElement order) {
		String[] values = new String[1];
		values[0] = "" + order.getCustomerId();
		insertTableElement(values, false);
	}

	public ArrayList<OrderTableElement> getOrders() {
		String[][] customerTable = getTable();

		ArrayList<OrderTableElement> productList = new ArrayList<OrderTableElement>();

		for (String[] tableRow : customerTable) {
			long id = Long.parseLong(tableRow[0]);
			long customerId = Long.parseLong(tableRow[1]);
			OrderTableElement ord = new OrderTableElement(id, customerId);
			productList.add(ord);
		}

		return productList;
	}

	public void updateOrder(OrderTableElement order) {
		String[] values = new String[2];
		values[0] = "" + order.getId();
		values[1] = "" + order.getCustomerId();
		updateTableElement(values, 1);
	}

	public void deleteOrder(OrderTableElement order) {
		String[] values = new String[2];
		values[0] = "" + order.getId();
		values[1] = "" + order.getCustomerId();
		deleteTableElement(values, 1);
	}
	
	public int orderDependencies(OrderTableElement order) {
		String[] values = new String[2];
		values[0] = "" + order.getId();
		values[1] = "" + order.getCustomerId();
		
		String key = OrderProductsDataAccess.getInstance().getKeys()[0];
		String tableFrom = OrderProductsDataAccess.getInstance().getTableName();
		
		return getDependencies(values, 0, key, tableFrom);
	}
}
