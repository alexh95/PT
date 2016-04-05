package ptlab.as2.presentation.view.operation;

public enum Operation {
	
	AddCustomer("Add a Customer"),
	EditCustomer("Edit a Customer"),
	RemoveCustomer("Remove a Customer"),
	
	AddProduct("Add a Product"),
	EditProduct("Edit a Product"),
	RemoveProduct("Remove a Product"),
	
	AddOrder("Add a Order"),
	EditOrder("Edit a Order"),
	RemoveOrder("Remove a Order"),
	
	EditStock("Edit Stock");
	
	private String displayName;
	
	
	private Operation(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
}
