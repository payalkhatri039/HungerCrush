<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Edit Profile</title>
</head>
<body>
<h2>Edit Profile</h2>
<ul>
<li>Current customer details are as follows</li>
<li> Address: ${sessionScope.loggedCustomer.user.getAddress()}</li>
<%-- <li> Contact Number: ${sessionScope.loggedCustomer.user.getContactNo()}</li>
 --%></ul>
<p>Enter the details to be updated below</p>
<form action="/customer/editProfile" method="post">
Enter New Address: <input type="text" name="newAddress">
<br>
Enter New Contact Number
<button type="submit" name="customerAction" value="editProfile">Edit</button> 
</form>
</body>
</html>