package org.ignou.pgdis.auth;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserRegisterServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userid = request.getParameter("userid").trim();
		String password = request.getParameter("password").trim();
		String fullname = request.getParameter("fullname").trim();

		String destPage = "register.jsp";

		try {
			int responseCode = submitUser(userid, password, fullname);
			if (responseCode == 1) {
				String message = "Registered successfully ! Please login.";
				request.setAttribute("message", message);
				destPage = "login.jsp";
			} else {
				String message = "Oops, could not register ! Please try again.";
				request.setAttribute("message", message);
			}

			forwardToDestPage(request, response, destPage);
		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			String message = "Duplicate entry for the User id = " + userid;
			request.setAttribute("message", message);
			forwardToDestPage(request, response, destPage);
		} catch (Exception e) {
			String message = "Oops, could not register ! Please try again.";
			request.setAttribute("message", message);
			forwardToDestPage(request, response, destPage);
			e.printStackTrace();
		}

	}

	private void forwardToDestPage(HttpServletRequest request, HttpServletResponse response, String destPage)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
		dispatcher.forward(request, response);
	}

	private int submitUser(String userid, String password, String fullname)
			throws SQLException, ClassNotFoundException {
		try (Connection connection = DBConnection.getDBConnection()) {
			String sql = "insert into users(userid,password,fullname)values(?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userid);
			statement.setString(2, password);
			statement.setString(3, fullname);
			int responseCode = statement.executeUpdate();
			return responseCode;
		}
	}
}
