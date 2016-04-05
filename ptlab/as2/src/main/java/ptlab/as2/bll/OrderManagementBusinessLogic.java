package ptlab.as2.bll;

import java.util.ArrayList;

import ptlab.as2.model.ModelElement;

public abstract class OrderManagementBusinessLogic<T extends ModelElement> {

	public abstract String[] getKeys();

	public abstract void insert(T element);
	
	public abstract ArrayList<T> get();

	public abstract void update(T element);

	public abstract int delete(T element);
}
