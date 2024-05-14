<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body>
<h2>Customer Profile</h2>
<p> First Name: ${sessionScope.loggedCustomer.user.getFname()}</p> 
<p> Last Name: ${sessionScope.loggedCustomer.user.getLname()}</p>
<p> Address: ${sessionScope.loggedCustomer.user.getAddress()}
<!-- <form action="/customer/editProfile" method="get">
<button type="submit" name="customerAction" value="editAddress">Edit Address</button> 
</form> </p> -->
<p> Contact Number: ${sessionScope.loggedCustomer.user.getContactNo()}</p>
<p> Email Address: ${sessionScope.loggedCustomer.user.getEmail()}</p>

<br><br>
  <a href="http://localhost:8080/HomePage.htm">Click here to go back to Home Page</a>
</body>
</html>