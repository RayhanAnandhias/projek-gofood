<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Data User</title>
	</head>
	<body>
		<form action="ActionController" method="post">
			<input type="submit" name="action" value="Back to Main Menu" style="font-family: 'Comfortaa'">
		</form>
		<div align="center">
			<table border="1" cellpadding="5" style="font-family: 'Comfortaa'">
	            <h1 style="font-family: 'Comfortaa'">List of users</h1>
	            
	            <form action="ActionController" method="post">
	            	<label style="font-family: 'Comfortaa'">Search User</label>
	            	
	            	<span style="margin-left:1em"></span>
					
					<c:set var = "boxvalue" value = "${sdbvalue}"/>
					<c:set var = "atrusrval" value = "${attributeuser}"/>
	            	<input type="search" name="search driver box" style="font-family: 'Comfortaa'" value="${boxvalue}" onchange="setCheckedValueOfRadioButtonGroup('${atrusrval}')">
	            	<span style="margin-left:1em">
						<input type="submit" name="action" value="Search User by" style="font-family: 'Comfortaa'" />
					</span>
					
					<script>
						function setCheckedValueOfRadioButtonGroup(vValue) {
							var radios = document.getElementsByName("attribute user");
						    for (var j = 0; j < radios.length; j++) {
						        if (radios[j].value == vValue) {
						            radios[j].checked = true;
						            break;
						        }
						    }
						}
					</script>

					<span style="margin-left:3em">
						<input type="submit" name="action" value="Retrieve All User Data" style="font-family: 'Comfortaa'"/>
					</span>
					
					<br><br>
					<label style="font-family: 'Comfortaa'">Search by :</label>
					<input type="radio" id="kode" name="attribute user" value="kode">
						<label for="kode" style="font-family: 'Comfortaa'">Kode</label><span style="margin-left:1em"></span>
					<input type="radio" id="fullname" name="attribute user" value="full_name">
						<label for="fullname" style="font-family: 'Comfortaa'">Full Name</label><span style="margin-left:1em"></span>
					<input type="radio" id="email" name="attribute user" value="email">
						<label for="email" style="font-family: 'Comfortaa'">Email</label><span style="margin-left:1em"></span>
					<input type="radio" id="telpnum" name="attribute user" value="telp_no">
						<label for="telpnum" style="font-family: 'Comfortaa'">Telephone Number</label><span style="margin-left:1em"></span>
					<input type="radio" id="street" name="attribute user" value="street">
						<label for="street" style="font-family: 'Comfortaa'">Street</label><span style="margin-left:1em"></span>
					
					<br><br>
					
				</form>
	            <tr>
	                <th>Full Name</th>
	                <th>Email</th>
	                <th>Telephone Number</th>
	                <th>Location</th>
	                <th>GoPay Balance</th>
	                <th>Top Up</th>
	                <th>Delete</th>
	            </tr>
	            <c:forEach items="${dataList}" var="dataItem">
	            	<form action="ActionController" method="post">
				        <tr>
				            <td>${dataItem.getFull_name()}</td>
				            <td>${dataItem.getEmail()}</td>
				            <td>${dataItem.getTelp_no()}</td>
				            <td>${dataItem.getLocation().getStreet()}</td>
				            <td><fmt:setLocale value = "id_ID"/>
         						<fmt:formatNumber value = "${dataItem.getSaldo_gopay()}" type = "currency"/>
         					</td>
				            <td><input type="submit" name="action" value="top up gopay" style="font-family: 'Comfortaa'"></td>
				        	<td><input type="submit" name="action" value="delete user" style="font-family: 'Comfortaa'"></td>
				        </tr>
				        <input type="hidden" name="kode" value="${dataItem.getKode()}">
				        <input type="hidden" name="saldo_gopay" value="${dataItem.getSaldo_gopay()}">
				     </form>
			    </c:forEach>
	        </table>
		</div>
	</body>
</html>