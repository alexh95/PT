package ptlab.as2.model;

public class Customer implements ModelElement {

	private long id;
	private String name;

	public Customer(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
