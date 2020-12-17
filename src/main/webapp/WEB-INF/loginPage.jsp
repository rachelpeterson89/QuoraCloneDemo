<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<div class="col-sm-6 mx-auto my-5">
	    <h1 class="text-center">Login</h1>
	    <p class="text-danger"><c:out value="${error}" /></p>
	    <p class="text-danger">${login_error}</p>
	    <p class="text-danger">${success_error}</p>
	    
	    <form method="post" action="/submitLogin">
	        <div class="form-group">
	            <label for="email">Email</label>
	            <input type="text" class="form-control" id="email" name="email"/>
	        </div>
	        
	        <div class="form-group">
	            <label for="password">Password</label>
	            <input type="password" class="form-control" id="password" name="password"/>
	        </div>
	        <input type="submit" class="btn btn-outline-info" value="Login"/>
	    </form>   
    </div> 
</body>
</html>