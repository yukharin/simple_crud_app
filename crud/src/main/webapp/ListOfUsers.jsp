<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.yukharin.model.User"
	import="java.util.List" import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2 align="center">List Of Users</h2>

	<c:forEach var="user" items="${requestScope.users}">
		<ul>
			<li>Name: <c:out value="${user.name}" /></li>
			<li>Age: <c:out value="${user.age}" /></li>
			<li>Id:<c:out value="${user.id}"/><li>
			<form method="post" action="delete">
				<input type="hidden" value="${user.id}" name="id" />
				<input type="submit" name="delete" value="Delete" />
			</form>
			<form method="post" action="update">
				<input type="hidden" value="${user.id}" name="id" /> 
				<input type="submit" value="Edit" />
			</form>
		</ul>
		<hr/>
	</c:forEach>
	<h3>Add User</h3>
	<br>
	<form method="post" action="add">
	<label for="add_name">Name</label>
	<input type="text" name="name" id="add_name"/><br>
	<label for="add_age">Age</label>
	<input type="text" name="age" id="add_age"/><br>
	<input type="submit" value="Add User"><br> 
	</form>
</body>
</html>