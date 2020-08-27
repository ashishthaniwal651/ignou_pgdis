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
		<h1>Register New User</h1>
		<form action="register" method="post" id="registerForm">
			<label for="userid">User - id:</label> 
			<input type="text" name="userid" size="30" /><br><br> 
			<label for="password">Password:</label> 
			<input type="password" name="password" size="30" /><br><br> 
			<label for="fullname">Full Name:</label> 
			<input type="text" name="fullname" size="30" /><br><br> 
			<br>${message} <br><br> 
			<button type="submit">Register</button>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#registerForm").validate({
			rules : {
				userid : "required",
				password : "required",
				fullname : "required"
			},
			messages : {
				userid : "Please enter User-id",
				password : "Please enter Password",
				fullname : "Please enter Full Name"
			}
		});
	});
</script>
</html>