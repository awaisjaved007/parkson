<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Register User</title>
</head>
<body>
<form action="registerUser" method="post">
    <h2>User Registeration Form: </h2>
    <pre>
First Name: 	  <input type="text" name="firstName"/>
Last Name:  	  <input type="text" name="lastName"/>
Email:      	  <input type="text" name="username"/>
Password:   	  <input type="password" name="password"/>
<input type="submit" value="Register"/>
</pre>
</form>
</body>
</html>