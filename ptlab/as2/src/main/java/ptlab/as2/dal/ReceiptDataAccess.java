package ptlab.as2.dal;

import java.util.List;

import ptlab.as2.model.table.CustomerTableElement;
import ptlab.as2.model.table.OrderProductsTableElement;
import ptlab.as2.model.table.OrderTableElement;
import ptlab.as2.model.table.ProductTableElement;

public class ReceiptDataAccess {

	private static ReceiptDataAccess instance = null;

	public static ReceiptDataAccess getInstance() {
		if (instance == null) {
			instance = new ReceiptDataAccess();
		}
		return instance;
	}

	private ReceiptDataAccess() {
	}

	public CustomerTableElement getCustomer(OrderTableElement order) {
		List<CustomerTableElement> customers = CustomerDataAccess.getInstance().getCustomers();

		CustomerTableElement cte = null;
		for (CustomerTableElement customer : customers) {
			if (customer.getId() == order.getCustomerId()) {
				cte = new CustomerTableElement(customer.getId(), customer.getName());
			}
		}
		return cte;
	}

	public ProductTableElement getProduct(OrderProductsTableElement orderProducts) {
		List<ProductTableElement> products = ProductDataAccess.getInstance().getProducts();

		ProductTableElement pte = null;
		for (ProductTableElement product : products) {
			if (product.getId() == orderProducts.getIdProduct()) {
				pte = new ProductTableElement(product.getId(), product.getName(), product.getQuantity());
			}
		}

		return pte;
	}
}
