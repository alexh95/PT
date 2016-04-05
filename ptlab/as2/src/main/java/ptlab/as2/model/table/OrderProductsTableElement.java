package ptlab.as2.model.table;

public class OrderProductsTableElement implements TableElement {

	public final static String TABLE_NAME = "OrderProducts";
	
	private long idOrder;
	private long idProduct;
	private int quantity;
	
	public OrderProductsTableElement(long idOrder, long idProduct, int quantity) {
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
	
	@Override
	public String toString() {
		return String.format("(idOrder: '%s', idProduct: '%s', quantity: '%s')", idOrder, idProduct, quantity);
	}
}
