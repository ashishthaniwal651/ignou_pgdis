package org.ignou.pgdis.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	public User checkLogin(String userid, String password) throws SQLException, ClassNotFoundException {
		try (Connection connection = DBConnection.getDBConnection()) {
			String sql = "SELECT * FROM users WHERE userid = ? and password = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userid);
			statement.setString(2, password);

			ResultSet result = statement.executeQuery();

			User user = null;
			if (result.next()) {
				user = new User();
				user.setFullname(result.getString("fullname"));
			}
			return user;
		}
	}
}
