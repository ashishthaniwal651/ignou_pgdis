package org.ignou.pgdis.auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection connection;

	public DBConnection() {
		super();
	}

	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/pgdis?" + "autoReconnect=true&useSSL=false";
		String dbUser = "root";
		String dbPassword = "root";

		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
		return connection;
	}
}
