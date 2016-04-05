package ptlab.as2.dal;

import java.util.ArrayList;

import ptlab.as2.model.table.ProductTableElement;

public class ProductDataAccess extends OrderManagementDataAccess {

	private static ProductDataAccess instance;

	public static ProductDataAccess getInstance() {
		if (instance == null) {
			instance = new ProductDataAccess();
		}
		return instance;
	}

	// private constructor - this class is a singleton
	private ProductDataAccess() {
		super(ProductTableElement.class);
	}

	public void insertProduct(ProductTableElement product) {
		String[] values = new String[2];
		values[0] = product.getName();
		values[1] = "" + product.getQuantity();
		insertTableElement(values, false);
	}

	public ArrayList<ProductTableElement> getProducts() {
		String[][] productTable = getTable();

		ArrayList<ProductTableElement> productList = new ArrayList<ProductTableElement>();

		for (String[] tableRow : productTable) {
			long id = Long.parseLong(tableRow[0]);
			String name = tableRow[1];
			int quantity = Integer.parseInt(tableRow[2]);
			ProductTableElement prod = new ProductTableElement(id, name, quantity);
			productList.add(prod);
		}

		return productList;
	}

	public void updateProduct(ProductTableElement product) {
		String[] values = new String[3];
		values[0] = "" + product.getId();
		values[1] = product.getName();
		values[2] = "" + product.getQuantity();
		updateTableElement(values, 1);
	}

	public void deleteProduct(ProductTableElement product) {
		String[] values = new String[3];
		values[0] = "" + product.getId();
		values[1] = product.getName();
		values[2] = "" + product.getQuantity();
		deleteTableElement(values, 1);
	}

	public int productDependencies(ProductTableElement product) {
		String[] values = new String[2];
		values[0] = "" + product.getId();
		values[1] = product.getName();

		String key = OrderProductsDataAccess.getInstance().getKeys()[1];
		String tableFrom = OrderProductsDataAccess.getInstance().getTableName();

		return getDependencies(values, 0, key, tableFrom);
	}
}
