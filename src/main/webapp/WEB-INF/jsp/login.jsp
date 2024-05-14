<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
<h2>Login</h2>
<form action="HomePage.htm" method="post">
 Enter Email<input type="email" name="email" required>
 <br>
 Enter Password<input type="password" name="password" required>
 <br>
<input type="submit" value="login">
<br>
<a href="http://localhost:8080/home">Click here to go back to Home</a>
</form>
<h4>${errorMessage}</h4>
</body>
</html>