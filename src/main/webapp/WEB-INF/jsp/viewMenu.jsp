<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ taglib uri="https://java.sun.com/jsp/jstl/sql" prefix="sql" %>
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Menu</title>
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
</tr>
<c:forEach var="element" items="${sessionScope.foodItemList}">
		<tr>
         <td>${element.getFoodName()}</td>
         <td>${element.getDescription()}</td>
         <td>${element.getPrice()}</td>
        </tr>   
 </c:forEach>
 </table>
 <a href="http://localhost:8080/HomePage.htm">Click here to go back to Home Menu</a>
</body>
</html>