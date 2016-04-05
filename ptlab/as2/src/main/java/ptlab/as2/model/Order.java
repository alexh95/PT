package ptlab.as2.model;

public class Order implements ModelElement {

	private long id;
	private long customerId;

	public Order(long id, long customerId) {
		this.id = id;
		this.customerId = customerId;
	}

	public long getId() {
		return id;
	}

	public long getCustomerId() {
		return customerId;
	}
}
