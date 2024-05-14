<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ taglib uri="https://java.sun.com/jsp/jstl/sql" prefix="sql" %>
 --%>
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
<h2>Menu Details</h2>
<table >
<tr>
<th>Food Name</th>
<th>Food Description</th>
<th>Food Price</th>
<th>Delete Item</th>
<th>Update Item</th>
</tr>
<c:forEach var="element" items="${sessionScope.foodItemList}">
		<tr>
         <td>${element.getFoodName()}</td>
         <td>${element.getDescription()}</td>
         <td>${element.getPrice()}</td>
         <td>
 	<form action="/restaurant/menu/action" method="get">     
    <button type="submit" name="itemAction" value="delete">Delete</button>
    <input type="hidden" name="id" value="${element.getFoodId()}"/> 
   </form> 
   		</td>
  		 <td>
   <form action="/restaurant/menu/update" method="get">
        <button type="submit" name="itemAction" value="update">Update</button>
        <input type="hidden" name="id" value="${element.getFoodId()}"/> 
   </form>
   		</td>
        </tr>   
 </c:forEach>
 </table>
 <a href="http://localhost:8080/HomePage.htm">Click here to go back to Home Menu</a>
</body>
</html>