<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout Page</title>
<style>
table, th, td {
	  border: 1px solid black;
	  padding: 3px;
	}
button{
color: blue;
}
</style>
 <script>
      function showErrorMessage() {
         alert("Your cart is empty! Please add some food items to checkout");
      }
   </script>
</head>
<body>
<h2>Cart Details</h2>
<table>
<tr>
<th>Food Name</th>
<th>Food Description</th>
<th>Food Price</th>
<th>Food Quantity</th>
<th>Update Item</th>
<th>Delete Item</th>
</tr>
<c:forEach var="element" items="${sessionScope.cartlines}">
		<tr>
         <td>${element.fooditem.getFoodName()}</td>
         <td>${element.fooditem.getDescription()}</td>
         <td>${element.fooditem.getPrice()}</td>
         <form action="/customer/menu/update" method="post">
         <td><input type="number" name="quantity" value="${element.getQuantity()}"></td>
  		 <td>
        <button type="submit" name="itemAction" value="update">Update</button>
        <input type="hidden" name="id" value="${element.fooditem.getFoodId()}"/>         
         </form>
   		</td>
   		<td>
   <form action="/customer/menu/delete" method="post">
        <button type="submit" name="itemAction" value="delete">Delete</button>
        <input type="hidden" name="id" value="${element.fooditem.getFoodId()}"/> 
   </form>
   		</td>
        </tr>   
 </c:forEach> 
 </table>
 <br>
 <form action="/customer/menu" method="get">
        <button type="submit" name="itemAction" value="menu">Show Menu</button>
  </form>
  <br>
<c:choose>
    <c:when test="${sessionScope.cartlines.size()>0}">
		<form action="/customer/cart/checkout" method="get">
        <button type="submit" name="itemAction" value="checkout">Checkout</button>
  		</form>
    </c:when>
    <c:otherwise>
        <button type="button" name="itemAction" value="checkout" onClick="showErrorMessage()">Checkout</button>
    </c:otherwise>
</c:choose>
 <p>${itemAddedMessage}</p>
</body>
</html>