<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View All Orders</title>
<style>
table, th, td {
	  border: 1px solid black;
	  padding: 3px;
	}
button{
color: blue;
}
.pagination {
  display: inline-block;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
}
.pagination a.active {
  background-color: #4CAF50;
  color: white;
}

.pagination a:hover:not(.active) {background-color: #ddd;}
</style>
</head>
<body>
<h2>All Orders</h2>
<table>
<tr>
<th>Order Id</th>
<th>Customer ID</th>
<th>Customer Name</th>
<th>Order Date</th>
<th>Order Items</th>
<th>Order Cost</th>
</tr>
<c:forEach var="element" items="${allOrders}">
		<tr>
         <td>${element.getOrderId()}</td>
         <td>${element.getCustomer().getCustomerId()} </td>
         <td>${element.getCustomer().getUser().getFname()} ${element.getCustomer().getUser().getLname()}</td>
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
 <br><br>
 <form action="/restaurant/getPdf" method="get">
<button type="submit">Orders PDF</button>
</form> 
<br>
 <br>
<a href="http://localhost:8080/HomePage.htm">Click here to go back to Home Menu</a>
<%-- <footer>
<center>
<div class="pagination">
  <a href="#">Previous</a>
  <a href="#">1</a>
  <a href="#">2</a>
  <a href="#">3</a>
  <a href="#">Next</a>
</div>
</center>
</footer> --%>
</body>
</html>