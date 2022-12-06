<!-- 
Cameron Holbrook
CSCI 3381
11/15/22
 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Netflix Home</title>
</head>
<body>
<form
		action=http://localhost:8082/netflix-web-gui-implementation/Servlet
		method="get">
		<br> <br> <br> <br>
		<p align="center">
		<b>Choose a Netflix mode:</b>
		<br>
		<p align="center">
			<input type="submit" value="User Mode" name="userModeButton">
			<input type="submit" value="Developer Mode" name="developerModeButton">
			<input type="submit" value="Logout" name="logoutButton">
		</p>
	</form>
</body>
</html>