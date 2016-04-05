package ptlab.as2.model.table;

public class CustomerTableElement implements TableElement {
	
	public final static String TABLE_NAME = "Customer";
	
	private long id;
	private String name;

	public CustomerTableElement(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return String.format("(id: '%s', name: '%s')", id, name);
	}
}
