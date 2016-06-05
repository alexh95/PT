package ptlab.as4;

import java.io.IOException;
import java.util.Collection;

import org.junit.Test;

import junit.framework.TestCase;
import ptlab.as4.model.Account;
import ptlab.as4.model.Bank;
import ptlab.as4.model.Person;
import ptlab.as4.model.SavingAccount;
import ptlab.as4.model.SpendingAccount;
import ptlab.as4.services.BankSaveLoader;

public class BankTest extends TestCase {

	@Test
	public void testBank() {
		// save the old bank in memory
		Bank oldBank = null;
		try {
			oldBank = BankSaveLoader.instance().loadBank();
		} catch (ClassNotFoundException | IOException e) {
		}

		// run code
		Bank bank = new Bank();

		Person p1 = new Person(0, "bob");
		Account a11 = new SavingAccount(0, 1);
		Account a12 = new SpendingAccount(1, 2.3);
		bank.addAccountForPerson(p1, a11);
		bank.addAccountForPerson(p1, a12);

		bank.depositMoney(0, 10, p1);
		bank.depositMoney(1, 5, p1);
		bank.withdrawMoney(0, 2, p1);

		Person p2 = new Person(1, "alice");
		Account a21 = new SavingAccount(0, 3.4);
		Account a22 = new SavingAccount(1, 4.5);
		Account a23 = new SavingAccount(2, 5.6);
		bank.addAccountForPerson(p2, a21);
		bank.addAccountForPerson(p2, a22);
		bank.addAccountForPerson(p2, a23);

		bank.depositMoney(0, 1, p2);
		bank.depositMoney(1, 2, p2);
		bank.withdrawMoney(1, 3, p1);
		bank.withdrawMoney(2, 4, p1);

		// test the results
		Collection<Object> prs = bank.getPersons();
		assertTrue("correct number of persons", prs.size() == 2);

		int i = 0;
		for (Object obj : prs) {
			Collection<Object> acs = bank.getAccounts((Person) obj);

			if (i == 0) {
				assertTrue("correct number of accounts 1", acs.size() == 2);
				int c = 0;
				for (Object o : acs) {
					Account a = (Account) o;
					if (c == 0) {

					} else {

					}
					c++;
				}

			} else {
				assertTrue("correct number of accounts 2", acs.size() == 3);
				int c = 0;
				for (Object o : acs) {
					if (c == 0) {

					} else if (c == 1) {

					} else {

					}
					c++;
				}
			}
			i++;
		}

		// assertTrue("", );

		// testing finished, restore the old bank
		BankSaveLoader.instance().saveBank(oldBank);
	}
}
