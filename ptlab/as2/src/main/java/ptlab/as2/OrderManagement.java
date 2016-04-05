package ptlab.as2;

import javax.swing.SwingUtilities;

import ptlab.as2.presentation.controller.OrderManagementController;

/**
 * Hello world!
 *
 */
public class OrderManagement {
	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				 new OrderManagementController();
			}
		});
		SwingUtilities.invokeLater(t);
		
		/*
		
//		ProductTableElement pr = new ProductTableElement(2, "p2", 22);
//		ProductDataAccess.getInstance().insertProduct(pr);
//		ProductDataAccess.getInstance().updateProduct(new ProductTableElement(1, "p1", 30));
//		ProductDataAccess.getInstance().deleteProduct(pr);
		for(ProductTableElement p : ProductDataAccess.getInstance().getProducts()) {
			System.out.println(p);
		}

//		CustomerTableElement cu = new CustomerTableElement(2, "c2");
//		CustomerDataAccess.getInstance().insertCustomer(cu);
//		CustomerDataAccess.getInstance().updateCustomer(new CustomerTableElement(1, "c1"));
//		CustomerDataAccess.getInstance().deleteCustomer(cu);
		for (CustomerTableElement c : CustomerDataAccess.getInstance().getCustomers()) {
			System.out.println(c);
		}
		
//		OrderTableElement or = new OrderTableElement(5, 1);
//		OrderDataAccess.getInstance().insertOrder(or);
//		OrderDataAccess.getInstance().updateOrder(new OrderTableElement(1, 1));
//		OrderDataAccess.getInstance().deleteOrder(or);
		for (OrderTableElement c : OrderDataAccess.getInstance().getOrders()) {
			System.out.println(c);
		}

//		OrderProductsTableElement os = new OrderProductsTableElement(6, 1, 55);
//		OrderProductsDataAccess.getInstance().insertOrderProducts(os);
//		OrderProductsDataAccess.getInstance().updateOrderProducts(new OrderProductsTableElement(3, 1, 25));
//		OrderProductsDataAccess.getInstance().deleteOrderProducts(os);
		for (OrderProductsTableElement c : OrderProductsDataAccess.getInstance().getOrderProducts()) {
			System.out.println(c);
		}
		
		*/
	}
}
