package ptlab.as2.bll;

import java.util.ArrayList;

import ptlab.as2.dal.OrderDataAccess;
import ptlab.as2.model.Order;
import ptlab.as2.model.table.OrderTableElement;

public class OrderBusinessLogic extends OrderManagementBusinessLogic<Order> {

	private static OrderBusinessLogic instance;

	public static OrderBusinessLogic getInstance() {
		if (instance == null) {
			instance = new OrderBusinessLogic();
		}
		return instance;
	}

	private OrderBusinessLogic() {
	}

	@Override
	public String[] getKeys() {
		return OrderDataAccess.getInstance().getKeys();
	}

	@Override
	public void insert(Order order) {
		long id = order.getId();
		long customerId = order.getCustomerId();
		OrderTableElement ote = new OrderTableElement(id, customerId);

		OrderDataAccess.getInstance().insertOrder(ote);
	}

	@Override
	public ArrayList<Order> get() {
		ArrayList<OrderTableElement> orderTableElements = OrderDataAccess.getInstance().getOrders();

		ArrayList<Order> orders = new ArrayList<Order>();
		for (OrderTableElement ote : orderTableElements) {
			long id = ote.getId();
			long customerId = ote.getCustomerId();
			Order o = new Order(id, customerId);
			orders.add(o);
		}

		return orders;
	}

	@Override
	public void update(Order order) {
		long id = order.getId();
		long customerId = order.getCustomerId();
		OrderTableElement ote = new OrderTableElement(id, customerId);

		OrderDataAccess.getInstance().updateOrder(ote);
	}

	@Override
	public int delete(Order order) {
		int dependencies = orderDependencies(order);
		if (dependencies == 0) {
			long id = order.getId();
			long customerId = order.getCustomerId();
			OrderTableElement ote = new OrderTableElement(id, customerId);

			OrderDataAccess.getInstance().deleteOrder(ote);
		}

		return dependencies;
	}

	private int orderDependencies(Order order) {
		long id = order.getId();
		long customerId = order.getCustomerId();
		OrderTableElement ote = new OrderTableElement(id, customerId);

		return OrderDataAccess.getInstance().orderDependencies(ote);
	}

	public Order getLatestOrder() {
		ArrayList<Order> orders = get();

		Order maxOrder = null;
		for (Order order : orders) {
			if (maxOrder == null || maxOrder.getId() < order.getId()) {
				maxOrder = order;
			}
		}
		return maxOrder;
	}
}
