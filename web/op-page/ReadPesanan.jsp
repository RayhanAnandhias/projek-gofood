<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Data Pesanan</title>
	</head>
	<body>
		<form action="ActionController" method="post">
			<input type="submit" name="action" value="Back to Main Menu" style="font-family: 'Comfortaa'">
		</form>
		<div align="center">
			<table border="1" cellpadding="5" style="font-family: 'Comfortaa'">
	            <h1 style="font-family: 'Comfortaa'">List of pesanan</h1>
	            
	            <form action="ActionController" method="post">
	            	<label style="font-family: 'Comfortaa'">Search Pesanan</label>
	            	
	            	<span style="margin-left:1em"></span>
					
					<c:set var = "boxvalue" value = "${sdbvalue}"/>
					<c:set var = "atrpsnval" value = "${attributepesanan}"/>
	            	<input type="search" name="search pesanan box" style="font-family: 'Comfortaa'" value="${boxvalue}" onchange="setCheckedValueOfRadioButtonGroup('${atrpsnval}')">
	            	<span style="margin-left:1em">
						<input type="submit" name="action" value="Search Pesanan by" style="font-family: 'Comfortaa'" />
					</span>
					
					<script>
						function setCheckedValueOfRadioButtonGroup(vValue) {
							var radios = document.getElementsByName("attribute pesanan");
						    for (var j = 0; j < radios.length; j++) {
						        if (radios[j].value == vValue) {
						            radios[j].checked = true;
						            break;
						        }
						    }
						}
					</script>

					<span style="margin-left:3em">
						<input type="submit" name="action" value="Retrieve All Pesanan Data" style="font-family: 'Comfortaa'"/>
					</span>
					
					<br><br>
					<label style="font-family: 'Comfortaa'">Search by :</label>
					<input type="radio" id="kode" name="attribute pesanan" value="kode">
						<label for="kode" style="font-family: 'Comfortaa'">Kode</label><span style="margin-left:1em"></span>
					<input type="radio" id="user_id" name="attribute pesanan" value="user_id">
						<label for="user_id" style="font-family: 'Comfortaa'">User Id</label><span style="margin-left:1em"></span>
					<input type="radio" id="restaurant_id" name="attribute pesanan" value="restaurant_id">
						<label for="restaurant_id" style="font-family: 'Comfortaa'">Restaurant Id</label><span style="margin-left:1em"></span>
					<input type="radio" id="driver_id" name="attribute pesanan" value="driver_id">
						<label for="driver_id" style="font-family: 'Comfortaa'">Driver Id</label><span style="margin-left:1em"></span>
					<input type="radio" id="pesanan" name="attribute driver" value="pesanan">
						<label for="pesanan" style="font-family: 'Comfortaa'">Food Id</label><span style="margin-left:1em"></span>
					<input type="radio" id="payment_method" name="attribute driver" value="payment_method">
						<label for="payment_method" style="font-family: 'Comfortaa'">Payment Method</label><span style="margin-left:1em"></span>
					
					<br><br>
					
				</form>
	            <tr>
	                <th>User Id</th>
	                <th>Restaurant Id</th>
	                <th>Driver Id</th>
	                <th>List Food Id</th>
	                <th>Payment Method</th>
	                <th>Ongkir</th>
	                <th>Total Cost</th>
	            </tr>
	            <c:forEach items="${dataList}" var="dataItem">
	            	<form action="ActionController" method="post">
				        <tr>
				            <td>${dataItem.getUserId()}</td>
				            <td>${dataItem.getRestaurantId()}</td>
				            <td>${dataItem.getDriverId()}</td>
				            <td>${dataItem.getPesanan()}</td>
				            <td>${dataItem.getPaymentMethod()}</td>
				            <td><fmt:setLocale value = "id_ID"/>
         						<fmt:formatNumber value = "${dataItem.getOngkir()}" type = "currency"/>
         					</td>
         					<td><fmt:setLocale value = "id_ID"/>
         						<fmt:formatNumber value = "${dataItem.getTotalCost()}" type = "currency"/>
         					</td>
				        </tr>
				     </form>
			    </c:forEach>
	        </table>
		</div>
	</body>
</html>