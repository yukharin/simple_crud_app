<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Update</title>
</head>
<body>
	<h2>Please enter here new parameters</h2>
	<br>
	<form method="post" action="update" id="edit">
		<label>New name: <input type="text" name="name" /></label><br> 
		<input type="hidden" name="id" value="${sessionScope.id}" /> 
		<label>New age: <input type="text" name="age" /></label><br> 
		<input type="submit" value="Ok" name="Ok"><br>
	</form>
</body>
</html>