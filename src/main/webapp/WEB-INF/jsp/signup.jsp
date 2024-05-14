<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Signup Page</title>
<script>
var myClock = document.getElementById('restaurantAbout');
myClock.style.display = 'none';

function show()
{
document.getElementById("restaurantAbout").style.display='block';
}
function hide()
{
document.getElementById("restaurantAbout").style.display='none';
}
</script>
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
</head>
<body>
<h2>Signup here!</h2>
 <form:form action="signup.htm" method="post" modelAttribute="user">
<div>
<form:label path="fname">Enter First Name:</form:label>
 <form:input path="fname"/> <br/>
 <form:errors path="fname" cssClass="error" /><br/>
</div>
 
 <div>
 <form:label path="lname">Enter Last Name:</form:label>
 <form:input path="lname"/><br/>
 <form:errors path="lname" cssClass="error" /><br/>
 </div>
 
 <div>            
 <form:label path="email">Enter E-mail:</form:label>
 <form:input path="email"/><br/>  
  <form:errors path="email" cssClass="error" /><br/>
 </div>
  
 <div>
 <form:label path="address">Enter Address:</form:label>
 <form:input path="address"/><br/>  
 <form:errors path="address" cssClass="error" /><br/>
 </div>
 
 <div>
 <form:label path="contactNo">Enter Contact Number:</form:label>
 <form:input path="contactNo"/><br/>       
 <form:errors path="contactNo" cssClass="error" /><br/>
 </div>
  
 <div>
 <form:label path="password">Password:</form:label>
 <form:password path="password"/><br/>
 <form:errors path="password" cssClass="error" /><br/>
 </div>
  
 <div>
 <form:label path="role">Role:</form:label>
 <form:radiobutton path="role" value="customer" onclick="hide();"/>Customer
 <form:radiobutton path="role" value="restaurant" onclick="show();"/>Restaurant<br/>
  <form:errors path="role" cssClass="error" /><br/>
 </div>
  
<div>
<div class="restaurantAbout" id="restaurantAbout" style="display:none">
 <form:label path="about">Enter About:</form:label>
 <form:input path="about"/><br/>  
</div>
</div> 
 <button>Sign Up</button>
<br>
</form:form>
<h4>${userRegisteredMessage}</h4>
<a href="http://localhost:8080/home">Click here to go back to Home</a>
</body>
</html>