<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer </title>
<style>
table, th, td {
	  border: 1px solid black;
	  padding: 3px;
	}
button{
color: blue;
}
</style>
</head>
<body>
<h2>Menu Details</h2>
<table>
<tr>
<th>Food Name</th>
<th>Food Description</th>
<th>Food Price</th>
<th>Add Item</th>
</tr>
<c:forEach var="element" items="${sessionScope.foodItemList}">
		<tr>
         <td>${element.getFoodName()}</td>
         <td>${element.getDescription()}</td>
         <td>${element.getPrice()}</td>
  		 <td>
   <form action="/customer/menu/add" method="post">
        <input type="number" name="quantity" value="1">
        <button type="submit" name="itemAction" value="add">Add</button>
        <input type="hidden" name="id" value="${element.getFoodId()}"/> 
   </form>
   		</td>
        </tr>   
 </c:forEach>
 </table>
 <br>
 <form action="/customer/cart/viewCart" method="get">
 <button type="submit" name="viewCartAction" value="viewCart">View Cart</button>
 </form>
 <p>${itemAddedMessage}</p>
 <a href="http://localhost:8080/HomePage.htm">Click here to go back to Home Page</a>
</body>
</html>