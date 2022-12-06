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
<title>Netflix Developer Mode</title>
</head>
<body>
	<p align="right">Current Mode: Developer
</body>

<body>
	<p align="left">
		<img src="imageAssets/logo_2015.png" alt="Netflix Logo" width="200">
		<br><br>
		<b>Backend Viewport</b>
		
	<form
		<% String movieText = (String)request.getAttribute("movieText"); %>
		<% String selectionText = (String)request.getAttribute("dropDownOptions"); %>
		action=http://localhost:8082/netflix-web-gui-implementation/Servlet
		method="get">

		<textarea readonly style="width: 800px; height: 300px; "><%=movieText %></textarea><br><br>
		
		<fieldset style="width:200px">
		<legend>Set Movie Information:</legend>
		Week: <input type="text" value="" name="textBoxWeek"><br>
		Category: <input type="text" value="" name="textBoxCategory"><br>
		Weekly Rank: <input type="text" value="" name="textBoxWeeklyRank"><br>
		Show Titles: <input type="text" value="" name="textBoxShowTitles"><br>
		Season Titles: <input type="text" value="" name="textBoxSeasonTitles"><br>
		Hours Viewed: <input type="text" value="" name="textBoxHoursViewed"><br>
		Cumulative Weeks Top 10: <input type="text" value="" name="textBoxCumulative"><br>
		</fieldset>
		
		<input type="submit" value="Add Movie" name="addMovieButton"><br><br>
		
		<fieldset style="width:200px">
		<legend>Purge/Unpurge Movie:</legend>
		
		<br>Select Movie:
		<%=selectionText%><br>
		
		<input type="radio" value="purge" name="purgeUnpurgeButton" checked>Purge<br>
		<input type="radio" value="unpurge" name="purgeUnpurgeButton">Unpurge<br>
		
		<input type="submit" value="Edit" name="editButton">
		<br><br>
		</fieldset>
		<br>
		
		<input type="submit" value="Switch to User Mode" name="developerToUserModeButton">
		<input type="submit" value="Logout" name="developerToLogout">
	</form>
</body>
</html>