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
		<link rel = "icon" href="data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIj8+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiBoZWlnaHQ9IjUxMnB4IiB2aWV3Qm94PSIwIDAgNjQgNjQiIHdpZHRoPSI1MTJweCI+PGcgaWQ9IkRlbGl2ZXJ5Ij48cGF0aCBkPSJtOSAyMWgyOHYyNGgtMjh6IiBmaWxsPSIjYjM4YjYzIi8+PHBhdGggZD0ibTM1IDIxdjE0YTYgNiAwIDAgMSAtNiA2aC0yMHY0aDI4di0yNHoiIGZpbGw9IiNhOTdjNTAiLz48cGF0aCBkPSJtNTUgMjVhMy42MTggMy42MTggMCAwIDAgMi0zLjIzNnYtOC43NjRoLTE0djZhNiA2IDAgMCAwIDYgNnY0aDZ6IiBmaWxsPSIjZmZiY2FiIi8+PHBhdGggZD0ibTU1IDI5aC04YTYgNiAwIDAgMCAtNiA2djIyaDIwdi0yMmE2IDYgMCAwIDAgLTYtNnoiIGZpbGw9IiNmZjUwMjMiLz48cGF0aCBkPSJtNTUgNDN2LTZhNCA0IDAgMCAwIC04IDB2NGEyIDIgMCAwIDEgLTIgMmgtMTJ2OGgxNGE4IDggMCAwIDAgOC04eiIgZmlsbD0iI2ZmZGE0NCIvPjxwYXRoIGQ9Im0zMyA0MWgtNnY0IDQgNGg2di0yLTh6IiBmaWxsPSIjZGY0NjFmIi8+PHBhdGggZD0ibTU4IDEzdi0yYTggOCAwIDAgMCAtMTYgMHYyeiIgZmlsbD0iI2RmNDYxZiIvPjxwYXRoIGQ9Im0yMyAyOSAzIDJ2LTEwaC02djEweiIgZmlsbD0iI2ZmYTczMyIvPjxwYXRoIGQ9Im0xOSA0NWE0IDQgMCAwIDAgNCA0aDR2LTR6IiBmaWxsPSIjZmZiY2FiIi8+PHBhdGggZD0ibTQxIDU3aDIwdjRoLTIweiIgZmlsbD0iIzZkNmU3MSIvPjxwYXRoIGQ9Im01NiAyOC4wOHYtMi41MTFhNC41OSA0LjU5IDAgMCAwIDItMy44di03Ljc2OWExIDEgMCAwIDAgMS0xdi0yYTkgOSAwIDAgMCAtMTggMHYxaC01YTEgMSAwIDAgMCAwIDJoNnY1YTcuMDExIDcuMDExIDAgMCAwIDYgNi45Mjl2Mi4wNzFoLTFhNy4wMDggNy4wMDggMCAwIDAgLTcgN3Y3aC0ydi0yMWExIDEgMCAwIDAgLTEtMWgtMjhhMSAxIDAgMCAwIC0xIDF2MjRhMSAxIDAgMCAwIDEgMWg5LjFhNS4wMDkgNS4wMDkgMCAwIDAgNC45IDRoM3YzYTEgMSAwIDAgMCAxIDFoNmExIDEgMCAwIDAgMS0xdi0xaDZ2OWExIDEgMCAwIDAgMSAxaDIwYTEgMSAwIDAgMCAxLTF2LTI2YTcuMDA2IDcuMDA2IDAgMCAwIC02LTYuOTJ6bS0xMy0xNy4wOGE3IDcgMCAwIDEgMTQgMHYxaC0xNHptOCAxM2gtMmE1LjAwNiA1LjAwNiAwIDAgMSAtNS01di01aDEydjcuNzY0YTIuNiAyLjYgMCAwIDEgLTEuNDQ3IDIuMzQxIDEgMSAwIDAgMCAtLjU1My44OTV2M2gtNHYtMmgxYTEgMSAwIDAgMCAwLTJ6bS0yNi0ydjcuMTMxbC0xLjQ0NS0uOTYzYTEgMSAwIDAgMCAtMS4xMSAwbC0xLjQ0NS45NjN2LTcuMTMxem0tMTUgMGg5djlhMSAxIDAgMCAwIDEuNTU1LjgzMmwyLjQ0NS0xLjYzMiAyLjQ0NSAxLjYzYTEgMSAwIDAgMCAxLjU1NS0uODN2LTloOXYyMGgtMnYtMWExIDEgMCAwIDAgLTEtMWgtNmExIDEgMCAwIDAgLTEgMXYzaC0xNnptMTMgMjZhMy4wMDYgMy4wMDYgMCAwIDEgLTIuODI5LTJoNS44Mjl2MnptOSA0aC00di0xMGg0em0yLThoMTFhMyAzIDAgMCAwIDMtM3YtNGEzIDMgMCAwIDEgNiAwdjZhNy4wMDggNy4wMDggMCAwIDEgLTcgN2gtMTN6bTEzIDhhOS4wMSA5LjAxIDAgMCAwIDktOXYtNmE1IDUgMCAwIDAgLTEwIDB2NGExIDEgMCAwIDEgLTEgMWgtM3YtN2E1LjAwNiA1LjAwNiAwIDAgMSA1LTVoOGE1LjAwNiA1LjAwNiAwIDAgMSA1IDV2MjFoLTE4di00em0xMyA4aC0xOHYtMmgxOHoiIGZpbGw9IiMyMzFmMjAiLz48L2c+PC9zdmc+Cg==" type="image/x-icon">
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
						<input type="submit" name="action" value="Retrieve All Driver Data" style="font-family: 'Comfortaa'"/>
					</span>
					
					<br><br>
					<label style="font-family: 'Comfortaa'">Search by :</label>
					<input type="radio" id="fullname" name="attribute user" value="full_name">
						<label for="fullname" style="font-family: 'Comfortaa'">Full Name</label><span style="margin-left:1em"></span>
					<input type="radio" id="email" name="attribute user" value="email">
						<label for="email" style="font-family: 'Comfortaa'">Email</label><span style="margin-left:1em"></span>
					<input type="radio" id="telpnum" name="attribute user" value="telp_no">
						<label for="telpnum" style="font-family: 'Comfortaa'">Telephone Number</label><span style="margin-left:1em"></span>
					<input type="radio" id="street" name="attribute user" value="street">
						<label for="street" style="font-family: 'Comfortaa'">Street</label><span style="margin-left:1em"></span>
					<input type="radio" id="city" name="attribute user" value="city">
						<label for="city" style="font-family: 'Comfortaa'">City</label><span style="margin-left:1em"></span>
					
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
				            <td>${dataItem.getLocation().getStreet()} - ${dataItem.getLocation().getCity()}</td>
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