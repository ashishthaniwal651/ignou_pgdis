<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*,java.util.*"%>
<%
	String userid = "";
String password = "";
if (request.getParameter("userid") != null) {
	userid = request.getParameter("userid");
}
if (request.getParameter("password") != null) {
	password = request.getParameter("password");
}
String jdbcURL = "jdbc:mysql://localhost:3306/pgdis?" + "autoReconnect=true&useSSL=false";
String dbUser = "root";
String dbPassword = "root";

Class.forName("com.mysql.jdbc.Driver");
Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

try {
	// query will execute and fetch the data from users table when sample data -
	// userid --> ' or ''=' & password --> ' or ''='
		
	String sql = "select * from users where userid='" + userid + "' and password='" + password + "'";
	Statement statement= connection.createStatement();

	ResultSet result = statement.executeQuery(sql);
	
	System.out.println ("Vulnerable Query - " + sql);
	
	while(result.next()){
		System.out.println (result.getString("userid"));
	}
 
	if (result.next() && (result.getString("userid").equalsIgnoreCase(userid) && result.getString("password").equalsIgnoreCase(password))) {
		String destPage = "index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
		dispatcher.forward(request, response); 
	}else {
		out.println("Login SUCCESSFULLY but not authorized to page ! Please <a href='login.jsp'>Login</a> securely or <a href='register.jsp'>Register Here</a> to continue.");
	}	
} catch (Exception e) {
	e.printStackTrace();
} finally {
	if (connection != null) {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
%>