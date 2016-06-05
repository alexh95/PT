package ptlab.as4.model;

public class SavingAccount extends Account {
	private static final long serialVersionUID = 5229764563565652708L;

	public SavingAccount(long accountId, double money) {
		super(accountId, money, "Saving Account");
	}

	@Override
	public void addMoney(double sum) {
		setMoney(getMoney() + sum);
		notifyObservers("Added " + sum + " to the account " + getClass());
	}

	@Override
	public void subtractMoney(double sum) {
		setMoney(getMoney() - sum);
		notifyObservers("Subtracted " + sum + " from the account " + getClass());
	}
}
