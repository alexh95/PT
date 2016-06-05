package ptlab.as4.model;

public class SpendingAccount extends Account {
	private static final long serialVersionUID = -6316692782600972469L;

	public SpendingAccount(long accountId, double money) {
		super(accountId, money, "Spending Account");
	}

	@Override
	public void addMoney(double sum) {
		setMoney(getMoney() + sum);
		notifyObservers("Added " + sum + " from the account " + getClass());
	}

	@Override
	public void subtractMoney(double sum) {
		setMoney(getMoney() - (sum * (1 + SPENDING_WITHDRAW_CUT)));
		notifyObservers("Subtracted " + sum + " from the account " + getClass());
	}

}
