package ptlab.as2.dal;

import java.lang.reflect.Field;

import ptlab.as2.model.table.TableElement;

/**
 * @author alexh
 * This is the abstract class connecting directly to the DB class, executing queries
 * This assembles the queries and calls OMDB execute
 * 
 * Its subclasses will inherit the resources necessary to build queries by just passing 
 */
public abstract class OrderManagementDataAccess {

	private String tableName;
	private String[] keys;
	private String selectQuery;

	protected OrderManagementDataAccess(Class<? extends TableElement> tableElementClass) {
		// Check if tableElementClass implements TableElement
		assert (tableElementClass.getInterfaces().length == 1);
		assert (tableElementClass.getInterfaces()[0].equals(TableElement.class));

		// use reflection to get the fields in the class
		Field[] fields = tableElementClass.getDeclaredFields();

		// use the name of the fields to assemble the key array and the query
		keys = new String[fields.length - 1];
		StringBuilder customerQuerySB = new StringBuilder();
		customerQuerySB.append("SELECT ");
		for (int i = 1; i < fields.length; i++) {
			keys[i - 1] = fields[i].getName();

			customerQuerySB.append(fields[i].getName());
			if (i < fields.length - 1) {
				customerQuerySB.append(", ");
			}
		}
		customerQuerySB.append(" FROM ");
		try {
			tableName = fields[0].get(tableElementClass).toString();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		customerQuerySB.append("`" + tableName + "`");
		selectQuery = customerQuerySB.toString();
	}

	protected void insertTableElement(String[] values, boolean includeId) {
		int keyStartIndex = 1;
		if (includeId) {
			keyStartIndex = 0;
		}
		assert (values != null);
		assert (values.length == keys.length - keyStartIndex);
		// INSERT INTO `orderdb`.`orderproducts` (`idOrder`, `idProduct`,
		// `quantity`) VALUES ('1', '1', '10');
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("INSERT INTO `%s` (", tableName));
		for (int i = keyStartIndex; i < keys.length; i++) {
			sb.append(String.format("`%s`", keys[i]));
			if (i < keys.length - 1) {
				sb.append(", ");
			}
		}
		sb.append(") VALUES (");
		for (int i = 0; i < values.length; i++) {
			sb.append(String.format("'%s'", values[i]));
			if (i < values.length - 1) {
				sb.append(", ");
			}
		}
		sb.append(")");

		OrderManagementDB.getInstance().executeQuery(sb.toString(), null);
	}

	protected String[][] getTable() {
		return OrderManagementDB.getInstance().executeQuery(selectQuery, keys);
	}

	protected void updateTableElement(String[] values, int noPrimaryKeys) {
		assert (values != null);
		assert (values.length == keys.length);
		// UPDATE `orderdb`.`product` SET `name`='h' WHERE `id`='5';
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("UPDATE `%s` SET ", tableName));
		for (int i = noPrimaryKeys; i < keys.length; i++) {
			sb.append(String.format("`%s`='%s'", keys[i], values[i]));
			if (i < keys.length - 1) {
				sb.append(", ");
			}
		}
		sb.append(" WHERE ");
		for (int i = 0; i < noPrimaryKeys; i++) {
			sb.append(String.format("`%s`='%s'", keys[i], values[i]));
			if (i < noPrimaryKeys - 1) {
				sb.append(" and ");
			}
		}

		OrderManagementDB.getInstance().executeQuery(sb.toString(), null);
	}

	protected void deleteTableElement(String[] values, int noPrimariKeys) {
		assert (values != null);
		assert (values.length == keys.length);
		// DELETE FROM `orderdb`.`product` WHERE `id`='4';
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("DELETE FROM `%s` WHERE ", tableName));
		for (int i = 0; i < noPrimariKeys; i++) {
			sb.append(String.format("`%s`='%s'", keys[i], values[i]));
			if (i < noPrimariKeys - 1) {
				sb.append(" and ");
			}
		}
		OrderManagementDB.getInstance().executeQuery(sb.toString(), null);
	}

	protected int getDependencies(String[] values, int noSelectedField, String key, String tableFrom) {
		// SELECT COUNT(`key`) FROM `order` WHERE
		// `customerName`='value[noSelectedField]'
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("SELECT COUNT(`%s`) AS count FROM `%s` WHERE `%s`='%s'", key, tableFrom, key,
				values[noSelectedField]));

		String[] countKey = new String[] { "count" };

		String[][] result = OrderManagementDB.getInstance().executeQuery(sb.toString(), countKey);
		int dependencies = Integer.parseInt(result[0][0]);
		System.out.println("dep:" + dependencies);
		return dependencies;
	}

	public String getTableName() {
		return tableName;
	}

	public String[] getKeys() {
		return keys;
	}
}
