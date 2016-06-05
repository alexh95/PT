package ptlab.as4.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import ptlab.as4.model.Bank;

public class BankSaveLoader {

	private static final String BANK_SAVE_FILE = "bank.bk";

	private static BankSaveLoader instance;

	public static BankSaveLoader instance() {
		if (instance == null) {
			instance = new BankSaveLoader();
		}
		return instance;
	}

	private BankSaveLoader() {
	}

	public void saveBank(Bank bank) {
		OutputStream os;
		ObjectOutputStream oos;
		try {
			os = new FileOutputStream(BANK_SAVE_FILE);
			oos = new ObjectOutputStream(os);
			oos.writeObject(bank);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Bank loadBank() throws IOException, ClassNotFoundException {
		InputStream is = new FileInputStream(BANK_SAVE_FILE);
		ObjectInputStream ois = new ObjectInputStream(is);
		Bank bank = (Bank) ois.readObject();
		ois.close();
		return bank;
	}
}
