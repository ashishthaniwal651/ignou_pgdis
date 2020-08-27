<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Login</title>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous">
	</script>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js">
	</script>
</head>
<body>
	<div style="text-align: center">
		<h1>Secure Login</h1>
		<form action="login" method="post" id="loginForm">
			<label for="userid">User - id:</label> 
			<input type="text" name="userid" size="30" /><br><br> 
			<label for="password">Password:</label>
			<input type="password" name="password" size="30" /><br><br> 
			<br>${message} <br><br> 
			<button type="submit">Login</button>
		</form>
		<p>
			New user? <a href="register.jsp">Register Here</a>.
		</p>
		<p>
			To test vulnerable login, click <a href="insecure-login.html">Insecure-Login</a>.
		</p>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#loginForm").validate({
			rules : {
				userid : "required",
				password : "required",
			},
			messages : {
				userid : "Please enter User-id",
				password : "Please enter Password"
			}
		});
	});
</script>
</html>