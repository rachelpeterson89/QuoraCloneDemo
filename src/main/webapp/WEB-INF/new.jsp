<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ask a Question</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>

<body>
	<div class="container col-6 mx-auto my-5">
		<h2 class="text-center">What is your question?</h2>
		<a href="/questions" class="btn btn-outline-dark">Dashboard</a>
		<a href="/logout" class="btn btn-outline-dark">Logout</a>
		
		<form action="/questions" method="post">
			<div class="form-group">
				<label for="question">Question:</label>
				<textarea name="question" id="question" class="form-control"></textarea>
			</div>
			
			<div class="form-group">
				<label for="tag">Tags:</label>
				<input type="text" name="tags" class="form-control" />
			</div>
			
			<input type="submit" value="Submit" class="btn btn-outline-dark" />
		</form>
	</div>
</body>
</html>