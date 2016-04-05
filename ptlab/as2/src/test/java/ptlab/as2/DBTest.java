package ptlab.as2;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import ptlab.as2.bll.CustomerBusinessLogic;
import ptlab.as2.bll.OrderBusinessLogic;
import ptlab.as2.bll.OrderProductsBusinessLogic;
import ptlab.as2.bll.ProductBusinessLogic;
import ptlab.as2.model.Customer;
import ptlab.as2.model.Order;
import ptlab.as2.model.OrderProducts;
import ptlab.as2.model.Product;

/**
 * Test for database operations This completely deletes all data in the database
 */
public class DBTest extends TestCase {

	public DBTest() {
		clean();
	}

	/**
	 * Purges the tables of all data
	 */
	private void clean() {
		ArrayList<OrderProducts> orderProducts = OrderProductsBusinessLogic.getInstance().get();
		for (OrderProducts op : orderProducts) {
			OrderProductsBusinessLogic.getInstance().delete(op);
		}

		ArrayList<Order> orders = OrderBusinessLogic.getInstance().get();
		for (Order o : orders) {
			OrderBusinessLogic.getInstance().delete(o);
		}

		ArrayList<Product> products = ProductBusinessLogic.getInstance().get();
		for (Product p : products) {
			ProductBusinessLogic.getInstance().delete(p);
		}

		ArrayList<Customer> customers = CustomerBusinessLogic.getInstance().get();
		for (Customer c : customers) {
			CustomerBusinessLogic.getInstance().delete(c);
		}
	}

	@Test
	public void testCustomer() {
		clean();

		CustomerBusinessLogic.getInstance().insert(new Customer(0, "c1"));
		Customer c1 = CustomerBusinessLogic.getInstance().get().get(0);
		assertTrue("CustomerInsertion", c1.getName().equals("c1"));

		CustomerBusinessLogic.getInstance().update(new Customer(c1.getId(), "c1u"));
		Customer c1u = CustomerBusinessLogic.getInstance().get().get(0);
		assertTrue("CustomerUpdating", c1u.getId() == c1.getId() && c1u.getName().equals("c1u"));

		CustomerBusinessLogic.getInstance().delete(new Customer(c1.getId(), ""));
		List<Customer> cl = CustomerBusinessLogic.getInstance().get();
		assertTrue("CustomerDeletion", cl.size() == 0);

		clean();
	}

	@Test
	public void testProduct() {
		clean();

		ProductBusinessLogic.getInstance().insert(new Product(0, "p1", 16));
		Product p1 = ProductBusinessLogic.getInstance().get().get(0);
		assertTrue("ProductInsertion", p1.getName().equals("p1") && p1.getQuantity() == 16);

		ProductBusinessLogic.getInstance().update(new Product(p1.getId(), "p1u", 61));
		Product p1u = ProductBusinessLogic.getInstance().get().get(0);
		assertTrue("ProductUpdating",
				p1u.getId() == p1.getId() && p1u.getName().equals("p1u") && p1u.getQuantity() == 61);

		ProductBusinessLogic.getInstance().delete(new Product(p1.getId(), "", 0));
		List<Product> pl = ProductBusinessLogic.getInstance().get();
		assertTrue("ProductDeletion", pl.size() == 0);

		clean();
	}

	@Test
	public void testOrder() {
		clean();

		CustomerBusinessLogic.getInstance().insert(new Customer(0, "c"));
		Customer c = CustomerBusinessLogic.getInstance().get().get(0);
		ProductBusinessLogic.getInstance().insert(new Product(0, "p", 10));
		Product p = ProductBusinessLogic.getInstance().get().get(0);

		OrderBusinessLogic.getInstance().insert(new Order(0, c.getId()));
		Order o = OrderBusinessLogic.getInstance().getLatestOrder();
		assertTrue("OrderInsertion", o.getCustomerId() == c.getId());

		OrderProductsBusinessLogic.getInstance().insert(new OrderProducts(o.getId(), p.getId(), p.getQuantity()));
		OrderProducts op = OrderProductsBusinessLogic.getInstance().get().get(0);
		assertTrue("OrderProductsInsertion",
				op.getIdOrder() == o.getId() && op.getIdProduct() == p.getId() && op.getQuantity() == p.getQuantity());

		OrderProductsBusinessLogic.getInstance().delete(new OrderProducts(o.getId(), p.getId(), 0));
		List<OrderProducts> opl = OrderProductsBusinessLogic.getInstance().get();
		assertTrue("OrderProductsDeletion", opl.size() == 0);

		OrderBusinessLogic.getInstance().delete(new Order(o.getId(), 0));
		List<Order> ol = OrderBusinessLogic.getInstance().get();
		assertTrue("OrderDeletion", ol.size() == 0);

		clean();
	}
}
