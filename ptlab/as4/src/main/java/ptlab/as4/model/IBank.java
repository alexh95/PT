package ptlab.as4.model;

public interface IBank {
	/**
	 * @param person
	 * @param account
	 * @Precondition (person != null)&&(account != null)
	 * @Postcondition (preAccountSize + 1 = postAccountSize)
	 */
	void addAccountForPerson(Person person, Account account);

	/**
	 * @param accountId
	 * @param sum
	 * @param person
	 * @Precondition (accountId >= 0)
	 * @Precondition (person != null)
	 * @Precondition (person has accounts)
	 * @Precondition (sum > 0)
	 * @Postcondition (preAccountMoneh + sum = postAccountMoneh)
	 */
	void depositMoney(long accountId, double sum, Person person);

	/**
	 * @param accountId
	 * @param sum
	 * @param person
	 * @Precondition (accountId >= 0)
	 * @Precondition (person != null)
	 * @Precondition (person has accounts)
	 * @Precondition (sum > 0)
	 * @Postcondition (preAccountMoneh - sum = postAccountMoneh)
	 */
	void withdrawMoney(long accountId, double sum, Person person);

	/**
	 * Invariant
	 * 
	 * @return boolean
	 */
	boolean isWellFormed();
}
