<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Congratulations! Your food is ordered and on its way!!</h2>
<br>
<p> Food will be delivered in a few minutes at the following address </p>
<p>Address: ${sessionScope.loggedCustomer.user.getAddress()}</p>
<br>
<p>Please pay the Delivery Agent ${sessionScope.cartCost} dollars when they arrive</p>
<br>
<p>Have a yummy day!</p>
<br>
 <a href="http://localhost:8080/HomePage.htm">Click here to go back to Home Page</a>
</body>
</html>