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
<title>Netflix User Mode</title>
</head>
<body>
	<p align="right">Current Mode: User
</body>

<body>
	<p align="left">
		<img src="imageAssets/logo_2015.png" alt="Netflix Logo" width="200">
		<br>
	<form
		<% String recommendation = (String)request.getAttribute("recommendation"); %>
		<% String movieText = (String)request.getAttribute("movieText"); %>
		
		action=http://localhost:8082/netflix-web-gui-implementation/Servlet
		method="get">
		
		<!-- This is readonly because I don't want the user to edit the data; I want the developer to edit the data. -->
		<b>Netflix Viewport</b><br><br><textarea readonly style="width: 800px; height: 400px; "><%=movieText %></textarea><br><br>
		
		<fieldset style="width:200px">
		<legend>Choose a format:</legend>
		
		<input type="radio" value="TV" 				name="filmOrTV" checked>TV Series<br>
		<input type="radio" value="Films" 			name="filmOrTV">Film<br><br>
		
		</fieldset>		
		
		<fieldset style="width:200px">
		<legend>Choose a language:</legend>
		<input type="radio" value="(English)" 		name="language" checked>English<br>
		<input type="radio" value="(Non-English)" 	name="language">Non-English<br><br>
		</fieldset>
		<br>
		
		<!-- <input type="number" id="quantity" name="weeksTop10" min="1" max="10">Weeks Top 10<br><br> -->
		<input type="submit" value="Recommend" name="recommendButton"><br>
		
		<!-- Recommendation text goes here, after "Recommendation: " -->
		Recommendation: <%=recommendation %>
		
		<br><br>
		<input type="submit" value="Switch to Developer Mode"	name="userToDeveloperModeButton">
		<input type="submit" value="Logout" name="userToLogout"><br>
	</form>
</body>
</html>