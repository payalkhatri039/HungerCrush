<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Food Item</title>
</head>
<body>
<h3>Update Food Item</h3>
<p>Current food details are as follows</p>
<ul>
<li>Food Name: ${sessionScope.updateitem.getFoodName()}</li>
<li>Food Description: ${sessionScope.updateitem.getDescription()}</li>
<li>Food Price: ${sessionScope.updateitem.getPrice()}</li>
</ul>
 <input type="hidden" name="id" value="${updateitem.getFoodId()}"/> 
<p>Enter the details to be updated below</p>
<form action="/restaurant/menu/updatedetails" method="post">
Enter New Item Name<input type="text" name="newFoodName" >
 <br>
Enter New Item Description<input type="text" name="newDescription">
 <br>
Enter New Item Price <input type="number" name="newPrice"> 
<br>
 <input type="hidden" name="id" value="${sessionScope.updateitem.getFoodId()}"/> 
<button type="submit" name="Update Item"> Update Item</button>
</form>
<br>
<h4>${errorMessage}</h4>
 <a href="http://localhost:8080/HomePage.htm">Click here to go back to Home Menu</a>
</body>
</html>