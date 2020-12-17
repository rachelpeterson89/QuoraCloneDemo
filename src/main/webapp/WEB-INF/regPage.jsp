<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
   
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<div class="col-sm-6 mx-auto my-5">
	    <h1 class="text-center">Register</h1>
	    
	    <p class="text-danger"><form:errors path="user.*"/></p>
	    
	    <p class="text-danger">${email_error}</p>
	    <p class="text-danger">${password_error}</p>
	    
	    <form:form method="POST" action="/submitRegistration" modelAttribute="user">
	        <div class="form-group">
	            <form:label path="email">Email:</form:label>
	            <form:input class="form-control" type="email" path="email"/>
	        </div>
	        
	        <div class="form-group">
	            <form:label path="password">Password:</form:label>
	            <form:password class="form-control" path="password"/>
	        </div>
	        
	        <div class="form-group">
	            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
	            <form:password class="form-control" path="passwordConfirmation"/>
	        </div>
	        
	        <input type="submit" class="btn btn-outline-info" value="Register"/>
	    </form:form>
   </div> 
</body>
</html>