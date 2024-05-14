<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurant Home Page</title>
</head>
<body>
<form action="restaurant/menu" method="get">
<button type="submit" name="restaurantAction" value="addFood">Add Food Item</button>
<br><br>
<button type="submit" name="restaurantAction" value="viewFood">View Menu</button>
<br><br>
<button type="submit" name="restaurantAction" value="deleteUpdateFood">Delete/Update Food Item</button>
</form> 
<br>
<form action="restaurant/viewAllOrders" method="get">
<button type="submit" name="restaurantAction" value="viewOrders">View All Orders</button>
</form>
<br><br>
<form action="/logout" method="get">
<button type="submit" name="logout" value="logout">Logout</button>
</form>
</body>
</html>