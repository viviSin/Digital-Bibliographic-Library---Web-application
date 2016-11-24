package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//done
public class BaseDao {
	//info needed to access the db
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/comp9321Ass2?autoReconnect=true&useSSL=false";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "813546";

	public static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return dbConnection;

	}
}
