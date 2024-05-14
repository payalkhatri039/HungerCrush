<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Home Page</title>
</head>
<body>
<form action="/customer/menu" method="post">
<h2>Home Page</h2>
<!-- <button type="submit" name="restaurantAction" value="addFood">Add Food Item</button>
<br><br> -->
<button type="submit" name="customerAction" value="viewFood">View Food Menu</button>
</form> 
<br><br> 
<form action="/customer/cart/viewCart" method="get">
<button type="submit" name="customerAction" value="viewCart">View Cart</button> 
</form>
<br><br>
<form action="/customer/viewPastOrders" method="get">
<button type="submit" name="customerAction" value="viewPastOrders">View Orders</button>
</form>
<br><br>
<form action="/customer/viewProfile" method="get">
<button type="submit" name="customerAction" value="viewProfile">View Profile</button> 
</form>
<br><br>
<form action="/logout" method="get">
<button type="submit" name="logout" value="logout">Logout</button>
</form>
<br>
<a href="http://localhost:8080/home">Click here to go back to Home</a>
</body>
</html>