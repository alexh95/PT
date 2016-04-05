package ptlab.as2.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author alexh
 * This singleton connects to the database and executes a specified query
 */
public class OrderManagementDB {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DATABASE_NAME = "orderdb";
	private static final String DB_URL = "jdbc:mysql://localhost/orderdb";

	private static final String USER = "root";
	private static final String PASS = "";

	private static OrderManagementDB instance;

	public static OrderManagementDB getInstance() {
		if (instance == null) {
			instance = new OrderManagementDB();
		}
		return instance;
	}

	// private constructor - this class is a singleton
	private OrderManagementDB() {
	}

	/**
	 * @param query - query to execute MUST be valid
	 * @param keys - if null, a modifying execute will take place, else
	 * it will call a select execute, in this case it must represent the column names of the returned table
	 * @return null in keys is null, String[][] containing the table, excluding the keys given
	 */
	public String[][] executeQuery(String query, String[] keys) {
		System.out.println(query);

		String[][] resultTable = null;

		Connection connection = null;
		Statement statement = null;
		try {
			// Connect to the database
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.createStatement();

			// extract each row into a list of string arrays
			if (keys != null) {
				ResultSet rs = statement.executeQuery(query);

				ArrayList<String[]> resultList = new ArrayList<String[]>();
				while (rs.next()) {
					String[] row = new String[keys.length];
					for (int i = 0; i < keys.length; i++) {
						String rowElement = rs.getString(keys[i]);
						row[i] = rowElement;
					}
					resultList.add(row);
				}

				// convert the list of arrays into a 2d table
				resultTable = new String[resultList.size()][keys.length];
				int row = 0;
				for (String[] tableRow : resultList) {
					resultTable[row] = tableRow;
					row++;
				}

				rs.close();
			} else {
				statement.execute(query);
			}

			// close all connections
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException se2) {
			}

			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return resultTable;
	}
}
