package ptlab.as4.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Bank implements IBank, Serializable {
	private static final long serialVersionUID = 4939879604090332994L;

	private Map<Person, Set<Account>> data;

	public Bank() {
		data = new HashMap<>();
	}

	public Collection<Object> getPersons() {
		Collection<Object> col = new LinkedList<>();
		for (Person p : data.keySet()) {
			col.add((Object) p);
		}
		return col;
	}
	
	public Collection<Object> getAccounts(Person person) {
		Collection<Object> col = new LinkedList<>();
		for (Account a : data.get(person)) {
			col.add((Object) a);
		}
		return col;
	}
	
	public long getNextAvailablePersonId() {
		long id = -1;
		for (Person p : data.keySet()) {
			if (id < p.getPersonId()) {
				id = p.getPersonId();
			}
		}
		return id + 1;
	}
	
	public long getNextAvalilableAccountId(Person person) {
		long id = -1;
		for (Account a : data.get(person)) {
			if (id < a.getAccountId()) {
				id = a.getAccountId();
			}
		}
		return id + 1;
	}

	public Person getPersonAtIndex(int index) {
		int i = 0;
		for (Person p : data.keySet()) {
			if (i++ == index) {
				return p;
			}
		}

		return null;
	}

	public void removePerson(Person person) {
		data.remove(person);
	}
	
	public Account getAccountAtIndex(Person person, int index) {
		int i = 0;
		for (Account a : data.get(person)) {
			if (i++ == index) {
				return a;
			}
		}

		return null;
	}
	
	public void removeAccount(Person person, Account account) {
		data.get(person).remove(account);
	}

	/**
	 * Invariant
	 * 
	 * @return boolean
	 */
	public boolean isWellFormed() {
		boolean wellFormed = true;

		int personCount = data.keySet().size();
		int pc = 0;
		for (Person p : data.keySet()) {
			pc++;
			int accountCount = data.get(p).size();
			int ac = 0;
			for (Account c : data.get(p)) {
				wellFormed &= c.getAccountId() >= 0;
				wellFormed &= c.getMoney() >= 0.0d;
				ac++;
			}
			wellFormed &= accountCount == ac;
		}

		wellFormed &= personCount == pc;

		return wellFormed;
	};

	/**
	 * @param person
	 * @param account
	 * @Precondition (person != null)&&(account != null)
	 * @Postcondition (preAccountSize + 1 = postAccountSize)
	 */
	@Override
	public void addAccountForPerson(Person person, Account account) {
		assert isWellFormed() : "invariant failed";
		assert person != null : "null person";
		assert account != null : "null account";

		int preSizeAccount = -1;

		account.addObserver(person);
		if (data.containsKey(person)) {
			preSizeAccount = data.get(person).size();
			data.get(person).add(account);
		} else {
			Set<Account> accountSet = new HashSet<>();
			data.put(person, accountSet);
			preSizeAccount = data.get(person).size();
			data.get(person).add(account);
		}

		int postSizeAccount = data.get(person).size();

		assert preSizeAccount + 1 == postSizeAccount : "account size not increased";
		assert isWellFormed() : "invariant failed";
	}

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
	@Override
	public void depositMoney(long accountId, double sum, Person person) {
		assert isWellFormed() : "invariant failed";
		assert accountId >= 0 : "accountId is negative";
		assert person != null : "person is null";
		assert data.containsKey(person) : "person not in bank";
		assert data.get(person).size() > 0 : "person has no accounts";
		assert sum > 0 : "moneh not good";

		if (data.containsKey(person)) {
			Set<Account> accounts = data.get(person);
			for (Account account : accounts) {
				if (account.getAccountId() == accountId) {
					double preMoneh = account.getMoney();
					account.addMoney(sum);
					assert preMoneh + sum == account.getMoney() : "transaction problem";
				}
			}
		}

		assert isWellFormed() : "invariant failed";
	}

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
	@Override
	public void withdrawMoney(long accountId, double sum, Person person) {
		assert isWellFormed() : "invariant failed";
		assert accountId >= 0 : "accountId is negative";
		assert person != null : "person is null";
		assert data.containsKey(person) : "person not in bank";
		assert data.get(person).size() > 0 : "person has no accounts";
		assert sum > 0 : "moneh not good";

		if (data.containsKey(person)) {
			Set<Account> accounts = data.get(person);
			for (Account account : accounts) {
				if (account.getAccountId() == accountId) {
					double preMoneh = account.getMoney();
					account.subtractMoney(sum);
					assert preMoneh + sum == account.getMoney() : "transaction problem";
				}
			}
		}
		assert isWellFormed() : "invariant failed";
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Size: " + data.size());
		for (Person p : data.keySet()) {
			sb.append("\nP: " + p.getName() + "; id = " + p.getPersonId() + "; A:");
			Set<Account> acc = data.get(p);
			for (Account a : acc) {
				sb.append(String.format("[id:%d;money:%f]", a.getAccountId(), a.getMoney()));
			}
		}
		return sb.toString();
	}
}
