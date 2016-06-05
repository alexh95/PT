package ptlab.as4.model;

import java.io.Serializable;

public class Person implements BankObserver, Serializable {
	private static final long serialVersionUID = 1906175148081996794L;

	private long personId;
	private String name;

	public Person(long personId, String name) {
		super();
		this.personId = personId;
		this.name = name;
	}

	public long getPersonId() {
		return personId;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (personId ^ (personId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (personId != other.personId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(name=" + name + ";personId=" + personId + ")";
	}

	@Override
	public void update(BankObservable arg0, Object arg1) {
		System.out.println("updated: " + arg1);
	}
}
