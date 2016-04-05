package ptlab.as2.bll;

import java.util.ArrayList;

import ptlab.as2.dal.OrderProductsDataAccess;
import ptlab.as2.dal.ProductDataAccess;
import ptlab.as2.model.Order;
import ptlab.as2.model.OrderProducts;
import ptlab.as2.model.table.OrderProductsTableElement;
import ptlab.as2.model.table.ProductTableElement;

public class OrderProductsBusinessLogic extends OrderManagementBusinessLogic<OrderProducts> {
	private static OrderProductsBusinessLogic instance;

	public static OrderProductsBusinessLogic getInstance() {
		if (instance == null) {
			instance = new OrderProductsBusinessLogic();
		}
		return instance;
	}

	private OrderProductsBusinessLogic() {
	}

	public String[] getKeys() {
		return OrderProductsDataAccess.getInstance().getKeys();
	}

	@Override
	public void insert(OrderProducts orderProducts) {
		long idOrder = orderProducts.getIdOrder();
		long idProduct = orderProducts.getIdProduct();
		int quantity = orderProducts.getQuantity();
		OrderProductsTableElement opte = new OrderProductsTableElement(idOrder, idProduct, quantity);

		OrderProductsDataAccess.getInstance().insertOrderProducts(opte);

		// Decrement from stock
		ArrayList<ProductTableElement> products = ProductDataAccess.getInstance().getProducts();
		for (ProductTableElement p : products) {
			if (p.getId() == idProduct) {
				ProductTableElement product = new ProductTableElement(p.getId(), p.getName(),
						p.getQuantity() - quantity);
				ProductDataAccess.getInstance().updateProduct(product);
			}
		}
	}

	@Override
	public ArrayList<OrderProducts> get() {
		ArrayList<OrderProductsTableElement> orderProductsTableElements = OrderProductsDataAccess.getInstance()
				.getOrderProducts();

		ArrayList<OrderProducts> orderProducts = new ArrayList<OrderProducts>();
		for (OrderProductsTableElement opte : orderProductsTableElements) {
			long idOrder = opte.getIdOrder();
			long idProduct = opte.getIdProduct();
			int quantity = opte.getQuantity();
			OrderProducts op = new OrderProducts(idOrder, idProduct, quantity);
			orderProducts.add(op);
		}

		return orderProducts;
	}

	@Override
	public void update(OrderProducts orderProducts) {
		long idOrder = orderProducts.getIdOrder();
		long idProduct = orderProducts.getIdProduct();
		int quantity = orderProducts.getQuantity();
		OrderProductsTableElement opte = new OrderProductsTableElement(idOrder, idProduct, quantity);

		OrderProductsDataAccess.getInstance().updateOrderProducts(opte);
	}

	@Override
	public int delete(OrderProducts orderProducts) {
		long idOrder = orderProducts.getIdOrder();
		long idProduct = orderProducts.getIdProduct();
		int quantity = orderProducts.getQuantity();
		OrderProductsTableElement opte = new OrderProductsTableElement(idOrder, idProduct, quantity);

		OrderProductsDataAccess.getInstance().deleteOrderProducts(opte);

		// increment to stock
		ArrayList<ProductTableElement> products = ProductDataAccess.getInstance().getProducts();
		for (ProductTableElement p : products) {
			if (p.getId() == idProduct) {
				ProductTableElement product = new ProductTableElement(p.getId(), p.getName(),
						p.getQuantity() + quantity);
				ProductDataAccess.getInstance().updateProduct(product);
			}
		}

		return 0;
	}

	public ArrayList<OrderProducts> getOrderProductsInOrder(Order order) {
		ArrayList<OrderProducts> result = new ArrayList<OrderProducts>();

		ArrayList<OrderProducts> orderProducts = get();
		for (OrderProducts orderProduct : orderProducts) {
			if (order.getId() == orderProduct.getIdOrder()) {
				result.add(orderProduct);
			}
		}

		return result;
	}
}
