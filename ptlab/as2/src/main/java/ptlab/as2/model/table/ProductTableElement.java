package ptlab.as2.model.table;

public class ProductTableElement implements TableElement {
	
	public final static String TABLE_NAME = "Product";
	
	private long id;
	private String name;
	private int quantity;
	
	public ProductTableElement(long id, String name, int quantity) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	@Override
	public String toString() {
		return String.format("(id: '%s', name: '%s', quantity: '%s')", id, name, quantity);
	}
}
