package ptlab.as4.services;

import java.lang.reflect.Field;
import java.util.Collection;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TableModelFactory {

	private static TableModelFactory instance;

	public static TableModelFactory instance() {
		if (instance == null) {
			instance = new TableModelFactory();
		}
		return instance;
	}

	private TableModelFactory() {
	}

	public TableModel createTableModel(Collection<Object> data, int firstFieldOffset) {
		if (data.size() == 0) {
			return new DefaultTableModel();
		}

		String[] tableHeader = null;
		String[][] tableColumns = new String[data.size()][];
		int columnIndex = 0;

		boolean firstObject = true;
		for (Object o : data) {
			if (firstObject) {
				firstObject = false;
				Field[] fields = o.getClass().getDeclaredFields();
				int len = fields.length - firstFieldOffset;
				if (len < 0) {
					fields = o.getClass().getSuperclass().getDeclaredFields();
				}
				tableHeader = new String[fields.length - firstFieldOffset];
				for (int i = 0; i < fields.length - firstFieldOffset; i++) {
					tableHeader[i] = fields[i + firstFieldOffset].getName();
				}
			}

			Field[] fields = o.getClass().getDeclaredFields();
			int len = fields.length - firstFieldOffset;
			if (len < 0) {
				fields = o.getClass().getSuperclass().getDeclaredFields();
			}
			tableColumns[columnIndex] = new String[fields.length - firstFieldOffset];
			for (int i = 0; i < fields.length - firstFieldOffset; i++) {
				try {
					fields[i + firstFieldOffset].setAccessible(true);
					tableColumns[columnIndex][i] = "" + fields[i + firstFieldOffset].get(o);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			columnIndex++;
		}

		return new DefaultTableModel(tableColumns, tableHeader);
	}
}
