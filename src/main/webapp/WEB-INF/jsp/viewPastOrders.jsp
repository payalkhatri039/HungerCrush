<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Past Orders</title>
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
<h2>Past Orders</h2>
<table>
<tr>
<th>Order Id</th>
<th>Order Date</th>
<th>Order Items</th>
<th>Order Cost</th>
</tr>
<c:forEach var="element" items="${pastOrders}">
		<tr>
         <td>${element.getOrderId()}</td>
         <td>${element.getDate()}</td>
         <td>
         <table>
         <tr>
         <th>Food Name</th>
         <th>Food Price</th>
         <th>Food Quantity</th>
         <th>Food Cost</th>
         </tr>
         <c:forEach var="element1" items="${element.getOrderitems()}">
         <tr>
         <td>${element1.getFoodName()} </td>
          <td>${element1.getCost()} </td>
          <td>${element1.getQuantity()} </td>
          <td>${element1.getQuantity()*element1.getCost()} </td>
         </tr>
          </c:forEach> 
         </table>
         </td>
         <td>${element.getTotalCost()}</td>
        </tr>   
 </c:forEach> 
 </table>
 <br>
  <a href="http://localhost:8080/HomePage.htm">Click here to go back to Home Page</a>
</body>
</html>