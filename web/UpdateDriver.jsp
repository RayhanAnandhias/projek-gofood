<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Driver</title>
</head>
<body>
	<form action="ActionController" method="post">
		<input type="hidden" name="action" value="View Driver" style="font-family: 'Comfortaa'"><input type="submit" value="back" style="font-family: 'Comfortaa'">
	</form>
	<div align="center">
		<BR><BR><h1 style="font-family: 'Comfortaa'">Update Driver</h1><BR><BR>
	</div>
	
	<form action="ActionController" method="post" style="font-family: 'Comfortaa'">
		<span style="margin-left:35em">Full Name </span> <span style="margin-left:5.3em"><input type="text" name="updatedfullname" value="${driver.getFull_name()}" style="font-family: 'Comfortaa'"></span> <BR><BR>
		<span style="margin-left:35em">Email </span> <span style="margin-left:7.6em"><input type="text" name="updatedemail" value="${driver.getEmail()}" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:35em">Telephone Number </span> <span style="margin-left:0.7em"><input type="text" name="updatedtelpnum" value="${driver.getTelp_no()}" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:35em">Motor </span><BR>
		<span style="margin-left:36em">Plat Number </span> <span style="margin-left:3em"><input type="text" name="updatedplatnum" value="${driver.getMotor().getNo_Plat()}" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:36em">Merk </span> <span style="margin-left:6.8em"><input type="text" name="updatedmerk" value="${driver.getMotor().getMerk()}" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:35em">Location </span><BR>
		<input type="hidden" name="locationkode" value="${driver.getLocation().getKode()}">
		<span style="margin-left:36em">Street </span> <span style="margin-left:6.2em"><input type="text" name="updatedstreet" value="${driver.getLocation().getStreet()}" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:36em">City </span> <span style="margin-left:7.3em"><input type="text" name="updatedcity" value="${driver.getLocation().getCity()}" style="font-family: 'Comfortaa'"></span><BR><BR>
		
		<span style="margin-left:60em">
			<input type="hidden" name="action" value="AfterUpdateDriver">
			<input type="submit" value="Update" style="font-family: 'Comfortaa'"/>
		</span>
	</form>
</body>
</html>