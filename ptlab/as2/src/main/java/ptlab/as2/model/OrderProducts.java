package ptlab.as2.model;

public class OrderProducts implements ModelElement {

	private long idOrder;
	private long idProduct;
	private int quantity;

	public OrderProducts(long idOrder, long idProduct, int quantity) {
		this.idOrder = idOrder;
		this.idProduct = idProduct;
		this.quantity = quantity;
	}

	public long getIdOrder() {
		return idOrder;
	}

	public long getIdProduct() {
		return idProduct;
	}

	public int getQuantity() {
		return quantity;
	}
}
