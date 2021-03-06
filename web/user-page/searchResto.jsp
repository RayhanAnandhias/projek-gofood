<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Data Restaurant</title>
	</head>
	<body>
		<form action="ActionController" method="post">
			<input type="submit" name="action" value="Back to Main menu" style="font-family: 'Comfortaa'">
		</form>
		<div align="center">
			<table border="1" cellpadding="5" style="font-family: 'Comfortaa'">
	            <h1 style="font-family: 'Comfortaa'">Search Restaurant or Food</h1>
	            
	            <form action="ActionController" method="post">
	            	<label style="font-family: 'Comfortaa'">Search Box</label>
	            	
	            	<span style="margin-left:1em"></span>

					<c:set var = "boxvalue" value = "${sdbvalue}"/>
					<c:set var = "atrrestval" value = "${attributerest}"/>
	            	<input type="search" name="search restaurant box" style="font-family: 'Comfortaa'" value="${boxvalue}" onchange="setCheckedValueOfRadioButtonGroup('${atrrestval}')">
	            	<span style="margin-left:1em">
						<input type="submit" name="action" value="Search By" style="font-family: 'Comfortaa'" />
					</span>
					
					<script>
						function setCheckedValueOfRadioButtonGroup(vValue) {
							var radios = document.getElementsByName("attribute rest");
						    for (var j = 0; j < radios.length; j++) {
						        if (radios[j].value == vValue) {
						            radios[j].checked = true;
						            break;
						        }
						    }
						}
					</script>

					<span style="margin-left:3em">
						<input type="submit" name="action" value="Retrieve All Restaurant data" style="font-family: 'Comfortaa'"/>
					</span>

					<span style="margin-left:3em">
						<input type="submit" name="action" value="Recommended Nearest Restaurant" style="font-family: 'Comfortaa'"/>
					</span>
					
					<br><br>
					<label style="font-family: 'Comfortaa'">Search by :</label>
					<input type="radio" id="kode" name="attribute rest" value="kode">
						<label for="kode" style="font-family: 'Comfortaa'">restaurant id</label><span style="margin-left:1em"></span>
					<input type="radio" id="name" name="attribute rest" value="name">
						<label for="name" style="font-family: 'Comfortaa'">restaurant name</label><span style="margin-left:1em"></span>
					<input type="radio" id="street" name="attribute rest" value="street">
						<label for="street" style="font-family: 'Comfortaa'">restaurant location</label><span style="margin-left:1em"></span>
					<input type="radio" id="telp_no" name="attribute rest" value="telp_no">
						<label for="telp_no" style="font-family: 'Comfortaa'">restaurant telephone number</label><span style="margin-left:1em"></span>
					<input type="radio" id="detail" name="attribute rest" value="detail">
						<label for="detail" style="font-family: 'Comfortaa'">restaurant detail</label><span style="margin-left:1em"></span>
					<input type="radio" id="food_name" name="attribute rest" value="food_name">
						<label for="food_name" style="font-family: 'Comfortaa'">food name</label><span style="margin-left:1em"></span>
					<br><br>
					
				</form>
	            <tr>
	                <th>Name</th>
	                <th>Location</th>
					<th>Distance</th>
	                <th>Telephone Number</th>
	                <th>Detail</th>
	                <th>Food</th>
	            </tr>
	            <c:forEach items="${dataList}" var="dataItem">
	            	<form action="ActionController" method="post">
				        <tr>
				            <td>${dataItem.getName()}</td>
				            <td>${dataItem.getLocation().getStreet()}</td>
							<td>${util.computeDistance(dataItem.getLocation(), user.getLocation())} km</td>
				            <td>${dataItem.getTelp_no()}</td>
				            <td>${dataItem.getDetail()}</td>
				            <td><input type="submit" name="action" value="view Food" style="font-family: 'Comfortaa'"></td>
				        </tr>
				        <input type="hidden" name="kode" value="${dataItem.getKode()}">
				        <input type="hidden" name="name" value="${dataItem.getName()}">
				        <input type="hidden" name="locationkode" value="${dataItem.getLocation().getKode()}">
				        <input type="hidden" name="street" value="${dataItem.getLocation().getStreet()}">
				        <input type="hidden" name="telpnum" value="${dataItem.getTelp_no()}">
				        <input type="hidden" name="detail" value="${dataItem.getDetail()}">
				     </form>
			    </c:forEach>
	        </table>
		</div>
	</body>
</html>