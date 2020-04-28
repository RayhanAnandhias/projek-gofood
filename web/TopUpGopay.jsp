<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Top Up Gopay</title>
</head>
<body>
	<form action="ActionController" method="post">
		<input type="submit" name="action" value="Retrieve All User Data" style="font-family: 'Comfortaa'">
	</form>
	<div align="center">
		<BR><BR><h1 style="font-family: 'Comfortaa'">Top Up Gopay</h1><BR><BR>
	</div>
	
	<form action="ActionController" method="post" style="font-family: 'Comfortaa'">
		<span style="margin-left:35em">Your Balance </span> 
		<span style="margin-left:5.3em"><fmt:setLocale value = "id_ID"/> <fmt:formatNumber value = "${user.getSaldo_gopay()}" type = "currency"/></span> <BR><BR>
		<span style="margin-left:35em">Minimum Top Up : <fmt:setLocale value = "id_ID"/> <fmt:formatNumber value = "10000" type = "currency"/></span> <BR><BR>
		<span style="margin-left:35em"><input type="text" name="topupval" style="font-family: 'Comfortaa'"></span><BR><BR>
		
		<span style="margin-left:60em">
			<input type="hidden" name="action" value="AfterTopUp">
			<input type="submit" value="Top Up Now" style="font-family: 'Comfortaa'" />
		</span>
	</form>
</body>
</html>