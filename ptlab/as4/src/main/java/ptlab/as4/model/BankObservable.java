package ptlab.as4.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class BankObservable implements Serializable {
	private static final long serialVersionUID = 2765880308547832164L;

	private List<BankObserver> observers;

	public BankObservable() {
		observers = new LinkedList<>();
	}

	public boolean addObserver(BankObserver e) {
		return observers.add(e);
	}

	public void notifyObservers(Object arg) {
		for (BankObserver bo : observers) {
			bo.update(this, arg);
		}
	}
}
