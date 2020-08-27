<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hybrid Cryptography</title>
</head>
<body>
	<div style="text-align: center">
		<h1>Welcome to IGNOU PGDIS Hybrid Cryptography Project</h1>
		<b>${user.fullname}</b>
		<br><br>
		<a href="logout">Logout</a>
	</div>
	<p>
	<div style="text-align: center">
		<form action="init" method="post">
			<button type="submit" name="button" value="generateKeys">Generate
				Public - Private Keys</button>
			<button type="submit" name="button" value="generateSecretKey">Generate Symmetric Key</button>
			<button type="submit" name="button" value="encryptData">Encrypt Message</button>
			<button type="submit" name="button" value="decryptData">Decrypt Message</button>
		</form>
	</div>
</body>
</html>