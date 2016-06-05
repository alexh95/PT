package ptlab.as4.model;

import java.io.Serializable;

public abstract class Account implements Serializable {
	private static final long serialVersionUID = -2877131209176027312L;
	protected static final double SPENDING_WITHDRAW_CUT = 0.05;
	private BankObservable bankObservable;

	private long accountId;
	private double money;
	private String type;

	public Account(long accountId, double money, String type) {
		super();
		bankObservable = new BankObservable();
		this.accountId = accountId;
		this.money = money;
		this.type = type;
	}

	public void addObserver(BankObserver o) {
		bankObservable.addObserver(o);
	}

	public void notifyObservers(Object o) {
		bankObservable.notifyObservers(o);
	}

	public long getAccountId() {
		return accountId;
	}

	public double getMoney() {
		return money;
	}

	protected void setMoney(double money) {
		this.money = money;
	}

	public abstract void addMoney(double sum);

	public abstract void subtractMoney(double sum);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountId ^ (accountId >>> 32));
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
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(accountId=" + accountId + ";money=" + money + ";type= + " + type + ")";
	}
}
