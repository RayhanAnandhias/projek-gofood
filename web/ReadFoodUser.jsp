<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Data Food</title>
	</head>
	<body>
		<form action="ActionController" method="post">
			<input type="hidden" name="action" value = "Retrieve All Restaurant Data">
			<input type="submit"  value="Back" style="font-family: 'Comfortaa'">
		</form>
		<div align="center">
			<table border="1" cellpadding="5" style="font-family: 'Comfortaa'">
				<c:set var = "restname" value = "${restnameparam}"/>
	            <h1 style="font-family: 'Comfortaa'">List of Food "${restname}"</h1>
	            
	            <form action="ActionController" method="post">
					<br><br>
					
				</form>
	            <tr>
	                <th>Name</th>
	                <th>Price</th>
	                <th>Quantity</th>
	                <th>Detail</th>
	            </tr>
	            <c:forEach items="${dataList}" var="dataItem">
	            	<form action="ActionController" method="post">
				        <tr>
				            <td>${dataItem.getName()}</td>
				            <td><fmt:setLocale value = "id_ID"/>
         						<fmt:formatNumber value = "${dataItem.getPrice()}" type = "currency"/>
         					</td>
				            <td>${dataItem.getQuantity()}</td>
				            <td>${dataItem.getDetail()}</td>
				        </tr>
				        <input type="hidden" name="kode" value="${dataItem.getKode()}">
				        <input type="hidden" name="name" value="${dataItem.getName()}">
				        <input type="hidden" name="price" value="${dataItem.getPrice()}">
				        <input type="hidden" name="quantity" value="${dataItem.getQuantity()}">
				        <input type="hidden" name="detail" value="${dataItem.getDetail()}">
				     </form>
			    </c:forEach>
	        </table>
		</div>
	</body>
</html>