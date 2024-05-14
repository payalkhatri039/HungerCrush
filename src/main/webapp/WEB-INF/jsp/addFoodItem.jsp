<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Food Items</title>
</head>
<style type="text/css">
    label {
        display: inline-block;
        width: 200px;
        margin: 5px;
        text-align: left;
    }
    input[type=text], input[type=password], select {
        width: 200px;  
    }
    input[type=radio] {
        display: inline-block;
        margin-left: 45px;
    }
     
    button {
        padding: 10px;
        margin: 10px;
    }
    .error {
    color: blue;
}  
</style>
<body>
<h3>Enter details of food item you want to add to the Menu</h3>
<form:form action="/restaurant/addFoodItem.htm" method="post" modelAttribute="fooditem">
<div>
<form:label path="foodName">Enter Item Name:</form:label>
 <form:input path="foodName"/> <br/>
 <form:errors path="foodName" cssClass="error" /><br/>
</div>
 
 <div>
 <form:label path="description">Enter Item Description</form:label>
 <form:input path="description"/><br/>
 <form:errors path="description" cssClass="error" /><br/>
 </div>
 
 <div>            
 <form:label path="price">Enter Item Price</form:label>
 <form:input path="price"/><br/>  
  <form:errors path="price" cssClass="error" /><br/>
 </div>
 
 <br>
<button type="submit" name="Add Item"> Add Item</button>
</form:form>
<br/>
<h4>${addedMessage}</h4>
<a href="http://localhost:8080/HomePage.htm">Click here to go back to Home Menu</a>
<br>

</body>
</html>