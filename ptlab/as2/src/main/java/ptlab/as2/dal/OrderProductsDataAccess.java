package ptlab.as2.dal;

import java.util.ArrayList;

import ptlab.as2.model.table.OrderProductsTableElement;

public class OrderProductsDataAccess extends OrderManagementDataAccess {

	private static OrderProductsDataAccess instance;

	public static OrderProductsDataAccess getInstance() {
		if (instance == null) {
			instance = new OrderProductsDataAccess();
		}
		return instance;
	}

	// private constructor - this class is a singleton
	protected OrderProductsDataAccess() {
		super(OrderProductsTableElement.class);
	}

	public void insertOrderProducts(OrderProductsTableElement orderProducts) {
		String[] values = new String[3];
		values[0] = "" + orderProducts.getIdOrder();
		values[1] = "" + orderProducts.getIdProduct();
		values[2] = "" + orderProducts.getQuantity();
		insertTableElement(values, true);
	}

	public ArrayList<OrderProductsTableElement> getOrderProducts() {
		String[][] customerTable = getTable();

		ArrayList<OrderProductsTableElement> productList = new ArrayList<OrderProductsTableElement>();

		for (String[] tableRow : customerTable) {
			long idOrder = Long.parseLong(tableRow[0]);
			long idProduct = Long.parseLong(tableRow[1]);
			int quantity = Integer.parseInt(tableRow[2]);
			OrderProductsTableElement ordprod = new OrderProductsTableElement(idOrder, idProduct, quantity);
			productList.add(ordprod);
		}

		return productList;
	}

	public void updateOrderProducts(OrderProductsTableElement order) {
		String[] values = new String[3];
		values[0] = "" + order.getIdOrder();
		values[1] = "" + order.getIdProduct();
		values[2] = "" + order.getQuantity();
		updateTableElement(values, 2);
	}

	public void deleteOrderProducts(OrderProductsTableElement order) {
		String[] values = new String[3];
		values[0] = "" + order.getIdOrder();
		values[1] = "" + order.getIdProduct();
		values[2] = "" + order.getQuantity();
		deleteTableElement(values, 2);
	}
}
