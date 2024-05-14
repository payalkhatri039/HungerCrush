<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<h2>Final Cart Details</h2>
<table>
<tr>
<th>Food Name</th>
<th>Food Description</th>
<th>Food Price</th>
<th>Food Quantity</th>
</tr>
<c:forEach var="element" items="${sessionScope.cartlines}">
		<tr>
         <td>${element.fooditem.getFoodName()}</td>
         <td>${element.fooditem.getDescription()}</td>
         <td>${element.fooditem.getPrice()}</td>
         <td>${element.getQuantity()}</td>
        </tr>   
 </c:forEach> 
 </table>
 <h4>Total Bill:${sessionScope.cartCost}</h4>
<form action="/customer/cart/viewCart" method="get">
 <button type="submit" name="viewCartAction" value="viewCart">Edit Cart</button>
 </form> 
 <p>Click here to finally place an order</p>
 <form action="/customer/ordered" method="post">
 <button type="submit" name="order" value="order">Order</button>
 </form>
 <br>
</body>
</html>