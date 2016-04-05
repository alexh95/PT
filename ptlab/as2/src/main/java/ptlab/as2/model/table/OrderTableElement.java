package ptlab.as2.model.table;

public class OrderTableElement implements TableElement {

	public final static String TABLE_NAME = "Order";
	
	private long id;
	private long customerId;

	public OrderTableElement(long id, long customerId) {
		this.id = id;
		this.customerId = customerId;
	}

	public long getId() {
		return id;
	}

	public long getCustomerId() {
		return customerId;
	}
	
	@Override
	public String toString() {
		return String.format("(id: '%s', customerId: '%s')", id, customerId);
	}
}
