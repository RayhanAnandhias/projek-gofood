<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Driver</title>
</head>
<body>
	<form action="ActionController" method="post">
		<input type="submit" name="action" value="Back to Main Menu" style="font-family: 'Comfortaa'">
	</form>
	<div align="center">
		<BR><BR><h1 style="font-family: 'Comfortaa'">Register Driver</h1><BR><BR>
	</div>
	
	<form action="ActionController" method="post" style="font-family: 'Comfortaa'">
		<span style="margin-left:35em">Full Name </span> <span style="margin-left:5.3em"><input type="text" name="fullname" style="font-family: 'Comfortaa'"></span> <BR><BR>
		<span style="margin-left:35em">Email </span> <span style="margin-left:7.6em"><input type="text" name="email" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:35em">Telephone Number </span> <span style="margin-left:0.7em"><input type="text" name="telpnum" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:35em">Motor </span><BR>
		<span style="margin-left:36em">Plat Number </span> <span style="margin-left:3em"><input type="text" name="platnum" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:36em">Merk </span> <span style="margin-left:6.8em"><input type="text" name="merk" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:35em">Location </span><BR>
		<span style="margin-left:36em">Street </span> <span style="margin-left:6.2em"><input type="text" name="street" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:36em">City </span> <span style="margin-left:7.3em"><input type="text" name="city" style="font-family: 'Comfortaa'"></span><BR><BR>
		
		<span style="margin-left:60em">
			<input type="hidden" name="action" value="RegDriver">
			<input type="submit" style="font-family: 'Comfortaa'"/>
		</span>
	</form>
</body>
</html>