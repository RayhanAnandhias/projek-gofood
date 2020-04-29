<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Food</title>
</head>
<body>
	<form action="ActionController" method="post">
		<input type="hidden" name="action" value="view food from update" style="font-family: 'Comfortaa'"><input type="submit" value="back" style="font-family: 'Comfortaa'">
	</form>
	<div align="center">
		<BR><BR><h1 style="font-family: 'Comfortaa'">Update Food</h1><BR><BR>
	</div>
	<form action="ActionController" method="post" style="font-family: 'Comfortaa'">
		<span style="margin-left:35em">Name </span> <span style="margin-left:5.3em"><input type="text" name="updatedname" value="${food.getName()}" style="font-family: 'Comfortaa'"></span> <BR><BR>
		<span style="margin-left:35em">Price </span> <span style="margin-left:6.2em"><input type="text" name="updatedprice" value="${food.getPrice()}" style="font-family: 'Comfortaa'"></span><BR><BR>
<!--		<span style="margin-left:35em">Quantity </span> <span style="margin-left:7.3em"><input type="text" name="updatedquantity" value="$food.getQuantity()}" style="font-family: 'Comfortaa'"></span><BR><BR>-->
		<span style="margin-left:35em">Detail </span> <span style="margin-left:3em"><input type="text" name="updateddetail" value="${food.getDetail()}" style="font-family: 'Comfortaa'"></span><BR><BR>
		
		<input type="hidden" name="name" value="${food.getName()}">
		<input type="hidden" name="kode" value="${food.getKode()}">
		<span style="margin-left:60em">
			<input type="hidden" name="action" value="AfterUpdateFood">
			<input type="submit" value="Update" style="font-family: 'Comfortaa'"/>
		</span>
	</form>
</body>
</html>