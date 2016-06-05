package ptlab.as4.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import ptlab.as4.model.Account;
import ptlab.as4.model.Bank;
import ptlab.as4.model.Person;
import ptlab.as4.model.SavingAccount;
import ptlab.as4.model.SpendingAccount;
import ptlab.as4.services.BankSaveLoader;
import ptlab.as4.services.TableModelFactory;
import ptlab.as4.view.BankFrame;

public class BankController {
	private BankFrame frame;
	private Bank bank;

	public BankController() {
		try {
			bank = BankSaveLoader.instance().loadBank();
		} catch (ClassNotFoundException | IOException e) {
			bank = new Bank();
		}
		frame = new BankFrame();
		frame.addPersonAddButtonActionListener(new PersonAddButtonActionListener());
		frame.addPersonRemoveButtonActionListener(new PersonRemoveButtonActionListener());
		frame.addPersonTableListSelectionListener(new PersonTableListSelectionListener());
		frame.setPersonRemoveButtonEnabled(false);
		populatePersonTable();

		frame.addAccountAddButtonActionListener(new AccountAddButtonActionListener());
		frame.addAccountRemoveButtonActionListener(new AccountRemoveButtonActionListener());
		frame.addAccountTableListSelectionListener(new AccountTableListSelectionListener());
		frame.setAccountAddButtonEnabled(false);
		frame.setAccountRemoveButtonEnabled(false);

		frame.addTransactionDepositButtonActionListener(new TransactionDepositButtonActionListener());
		frame.addTransactionWithdrawButtonActionListener(new TransactionWithdrawButtonActionListener());
		frame.setTransactionDepositButtonEnabled(false);
		frame.setTransactionWithdrawButtonEnabled(false);
	}

	// Person Panel
	private void populatePersonTable() {
		frame.setPersonPanelTableModel(TableModelFactory.instance().createTableModel(bank.getPersons(), 1));
	}

	private class PersonTableListSelectionListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (frame.getSelectedPersonTableRow() != -1) {
				frame.setPersonRemoveButtonEnabled(true);
				frame.setAccountAddButtonEnabled(true);
				populateAccountTable();
			}
		}
	}

	private class PersonAddButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String personName = frame.getPersonName();
			if (personName.length() > 0) {
				Person person = new Person(bank.getNextAvailablePersonId(), personName);
				Account account = new SavingAccount(0, 0);
				bank.addAccountForPerson(person, account);
				frame.resetPersonName();
			}

			saveBank();
			populatePersonTable();
		}
	}

	private class PersonRemoveButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			bank.removePerson(bank.getPersonAtIndex(frame.getSelectedPersonTableRow()));
			saveBank();
			populatePersonTable();
			frame.setPersonRemoveButtonEnabled(false);
			frame.setAccountAddButtonEnabled(false);
			frame.setAccountRemoveButtonEnabled(false);
			frame.setTransactionDepositButtonEnabled(false);
			frame.setTransactionWithdrawButtonEnabled(false);
			frame.setAccountPanelTableModel(new DefaultTableModel());
		}
	}

	// Account Panel
	private void populateAccountTable() {
		Person selectedPerson = bank.getPersonAtIndex(frame.getSelectedPersonTableRow());
		frame.setAccountPanelTableModel(
				TableModelFactory.instance().createTableModel(bank.getAccounts(selectedPerson), 3));
	}

	private class AccountTableListSelectionListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (frame.getSelectedAccountTableRow() != -1) {
				frame.setAccountRemoveButtonEnabled(true);
				frame.setTransactionDepositButtonEnabled(true);
				frame.setTransactionWithdrawButtonEnabled(true);
			}
		}
	}

	private class AccountAddButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int accountType = frame.getAccountTypeSelection();
			Person selectedPerson = bank.getPersonAtIndex(frame.getSelectedPersonTableRow());
			long accountId = bank.getNextAvalilableAccountId(selectedPerson);
			Account account;
			if (accountType == 0) {
				account = new SavingAccount(accountId, 0);
			} else {
				account = new SpendingAccount(accountId, 0);
			}
			bank.addAccountForPerson(selectedPerson, account);

			saveBank();
			populateAccountTable();
			frame.setAccountRemoveButtonEnabled(false);
		}
	}

	private class AccountRemoveButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Person selectedPerson = bank.getPersonAtIndex(frame.getSelectedPersonTableRow());
			Account selectedAccount = bank.getAccountAtIndex(selectedPerson, frame.getSelectedAccountTableRow());
			bank.removeAccount(selectedPerson, selectedAccount);

			saveBank();
			populateAccountTable();
			frame.setAccountRemoveButtonEnabled(false);
			frame.setTransactionDepositButtonEnabled(false);
			frame.setTransactionWithdrawButtonEnabled(false);
		}
	}

	// Transaction Panel
	private class TransactionDepositButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String sum = frame.getTransactionDepositSum();
			double msum = 0.0;
			try {
				msum = Double.parseDouble(sum);
				if (msum < 0.0) {
					return;
				}
			} catch (Exception ex) {
				return;
			}
			
			Person selectedPerson = bank.getPersonAtIndex(frame.getSelectedPersonTableRow());
			Account selectedAccount = bank.getAccountAtIndex(selectedPerson, frame.getSelectedAccountTableRow());
			long selectedAccountId = selectedAccount.getAccountId();
			bank.depositMoney(selectedAccountId, msum, selectedPerson);
			frame.setTransactionDepositButtonEnabled(false);
			frame.setTransactionWithdrawButtonEnabled(false);
			frame.resetTransactionDepositSum();
			populateAccountTable();
			saveBank();
		}
	}

	private class TransactionWithdrawButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String sum = frame.getTransactionWithdrawSum();
			double msum = 0.0;
			try {
				msum = Double.parseDouble(sum);
				if (msum < 0.0) {
					return;
				}
			} catch (Exception ex) {
				return;
			}
			Person selectedPerson = bank.getPersonAtIndex(frame.getSelectedPersonTableRow());
			Account selectedAccount = bank.getAccountAtIndex(selectedPerson, frame.getSelectedAccountTableRow());
			long selectedAccountId = selectedAccount.getAccountId();
			bank.withdrawMoney(selectedAccountId, msum, selectedPerson);
			frame.setTransactionDepositButtonEnabled(false);
			frame.setTransactionWithdrawButtonEnabled(false);
			frame.resetTransactionWithdrawSum();
			populateAccountTable();
			saveBank();
		}
	}

	// General
	private void saveBank() {
		BankSaveLoader.instance().saveBank(bank);
	}
}
