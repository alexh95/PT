package ptlab.as2.bll;

import java.util.ArrayList;

import ptlab.as2.dal.ProductDataAccess;
import ptlab.as2.model.Product;
import ptlab.as2.model.table.ProductTableElement;

public class ProductBusinessLogic extends OrderManagementBusinessLogic<Product> {

	private static ProductBusinessLogic instance;

	public static ProductBusinessLogic getInstance() {
		if (instance == null) {
			instance = new ProductBusinessLogic();
		}
		return instance;
	}

	private ProductBusinessLogic() {
	}

	@Override
	public String[] getKeys() {
		return ProductDataAccess.getInstance().getKeys();
	}

	@Override
	public void insert(Product product) {
		long id = product.getId();
		String name = product.getName();
		int quantity = product.getQuantity();
		ProductTableElement pte = new ProductTableElement(id, name, quantity);

		ProductDataAccess.getInstance().insertProduct(pte);
	}

	@Override
	public ArrayList<Product> get() {
		ArrayList<ProductTableElement> productTableElements = ProductDataAccess.getInstance().getProducts();

		ArrayList<Product> products = new ArrayList<Product>();
		for (ProductTableElement pte : productTableElements) {
			long id = pte.getId();
			String name = pte.getName();
			int quantity = pte.getQuantity();
			Product c = new Product(id, name, quantity);
			products.add(c);
		}

		return products;
	}

	@Override
	public void update(Product product) {
		long id = product.getId();
		String name = product.getName();
		int quantity = product.getQuantity();
		ProductTableElement pte = new ProductTableElement(id, name, quantity);

		ProductDataAccess.getInstance().updateProduct(pte);
	}

	@Override
	public int delete(Product product) {
		int dependencies = productDependencies(product);
		if (dependencies == 0) {
			long id = product.getId();
			String name = product.getName();
			int quantity = product.getQuantity();
			ProductTableElement cte = new ProductTableElement(id, name, quantity);

			ProductDataAccess.getInstance().deleteProduct(cte);
		}

		return dependencies;
	}

	private int productDependencies(Product product) {
		long id = product.getId();
		String name = product.getName();
		int quantity = product.getQuantity();
		ProductTableElement pte = new ProductTableElement(id, name, quantity);

		return ProductDataAccess.getInstance().productDependencies(pte);
	}
}
