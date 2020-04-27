<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Restaurant</title>
</head>
<body>
	<form action="ActionController" method="post">
		<input type="hidden" name="action" value="View Restaurant" style="font-family: 'Comfortaa'"><input type="submit" value="back" style="font-family: 'Comfortaa'">
	</form>
	<div align="center">
		<BR><BR><h1 style="font-family: 'Comfortaa'">Update Restaurant</h1><BR><BR>
	</div>
	
	<form action="ActionController" method="post" style="font-family: 'Comfortaa'">
		<span style="margin-left:35em">Name </span> <span style="margin-left:5.3em"><input type="text" name="updatedname" value="${restaurant.getName()}" style="font-family: 'Comfortaa'"></span> <BR><BR>
		<input type="hidden" name="locationkode" value="${restaurant.getLocation().getKode()}">
		<span style="margin-left:36em">Street </span> <span style="margin-left:6.2em"><input type="text" name="updatedstreet" value="${restaurant.getLocation().getStreet()}" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:36em">City </span> <span style="margin-left:7.3em"><input type="text" name="updatedcity" value="${restaurant.getLocation().getCity()}" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:35em">Telephone Number </span> <span style="margin-left:0.7em"><input type="text" name="updatedtelpnum" value="${restaurant.getTelp_no()}" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:36em">Detail </span> <span style="margin-left:3em"><input type="text" name="updateddetail" value="${restaurant.getDetail()}" style="font-family: 'Comfortaa'"></span><BR><BR>
		
		
		<span style="margin-left:60em">
			<input type="hidden" name="action" value="AfterUpdateRestaurant">
			<input type="submit" value="Update" style="font-family: 'Comfortaa'"/>
		</span>
	</form>
</body>
</html>