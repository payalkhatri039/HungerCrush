<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login/Signup Page</title>
</head>
<body>
<h2>Welcome!</h2>
<form action="signup" method="get">
<button name="signup">signup</button>
</form>

<form action="login" method="get">
<button name="login">Login</button>
</form>
${userRegisteredMessage}
</body>
</html>