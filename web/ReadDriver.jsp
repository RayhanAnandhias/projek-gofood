<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Data Driver</title>
		<link rel = "icon" href="data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIj8+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiBoZWlnaHQ9IjUxMnB4IiB2aWV3Qm94PSIwIDAgNjQgNjQiIHdpZHRoPSI1MTJweCI+PGcgaWQ9IkRlbGl2ZXJ5Ij48cGF0aCBkPSJtOSAyMWgyOHYyNGgtMjh6IiBmaWxsPSIjYjM4YjYzIi8+PHBhdGggZD0ibTM1IDIxdjE0YTYgNiAwIDAgMSAtNiA2aC0yMHY0aDI4di0yNHoiIGZpbGw9IiNhOTdjNTAiLz48cGF0aCBkPSJtNTUgMjVhMy42MTggMy42MTggMCAwIDAgMi0zLjIzNnYtOC43NjRoLTE0djZhNiA2IDAgMCAwIDYgNnY0aDZ6IiBmaWxsPSIjZmZiY2FiIi8+PHBhdGggZD0ibTU1IDI5aC04YTYgNiAwIDAgMCAtNiA2djIyaDIwdi0yMmE2IDYgMCAwIDAgLTYtNnoiIGZpbGw9IiNmZjUwMjMiLz48cGF0aCBkPSJtNTUgNDN2LTZhNCA0IDAgMCAwIC04IDB2NGEyIDIgMCAwIDEgLTIgMmgtMTJ2OGgxNGE4IDggMCAwIDAgOC04eiIgZmlsbD0iI2ZmZGE0NCIvPjxwYXRoIGQ9Im0zMyA0MWgtNnY0IDQgNGg2di0yLTh6IiBmaWxsPSIjZGY0NjFmIi8+PHBhdGggZD0ibTU4IDEzdi0yYTggOCAwIDAgMCAtMTYgMHYyeiIgZmlsbD0iI2RmNDYxZiIvPjxwYXRoIGQ9Im0yMyAyOSAzIDJ2LTEwaC02djEweiIgZmlsbD0iI2ZmYTczMyIvPjxwYXRoIGQ9Im0xOSA0NWE0IDQgMCAwIDAgNCA0aDR2LTR6IiBmaWxsPSIjZmZiY2FiIi8+PHBhdGggZD0ibTQxIDU3aDIwdjRoLTIweiIgZmlsbD0iIzZkNmU3MSIvPjxwYXRoIGQ9Im01NiAyOC4wOHYtMi41MTFhNC41OSA0LjU5IDAgMCAwIDItMy44di03Ljc2OWExIDEgMCAwIDAgMS0xdi0yYTkgOSAwIDAgMCAtMTggMHYxaC01YTEgMSAwIDAgMCAwIDJoNnY1YTcuMDExIDcuMDExIDAgMCAwIDYgNi45Mjl2Mi4wNzFoLTFhNy4wMDggNy4wMDggMCAwIDAgLTcgN3Y3aC0ydi0yMWExIDEgMCAwIDAgLTEtMWgtMjhhMSAxIDAgMCAwIC0xIDF2MjRhMSAxIDAgMCAwIDEgMWg5LjFhNS4wMDkgNS4wMDkgMCAwIDAgNC45IDRoM3YzYTEgMSAwIDAgMCAxIDFoNmExIDEgMCAwIDAgMS0xdi0xaDZ2OWExIDEgMCAwIDAgMSAxaDIwYTEgMSAwIDAgMCAxLTF2LTI2YTcuMDA2IDcuMDA2IDAgMCAwIC02LTYuOTJ6bS0xMy0xNy4wOGE3IDcgMCAwIDEgMTQgMHYxaC0xNHptOCAxM2gtMmE1LjAwNiA1LjAwNiAwIDAgMSAtNS01di01aDEydjcuNzY0YTIuNiAyLjYgMCAwIDEgLTEuNDQ3IDIuMzQxIDEgMSAwIDAgMCAtLjU1My44OTV2M2gtNHYtMmgxYTEgMSAwIDAgMCAwLTJ6bS0yNi0ydjcuMTMxbC0xLjQ0NS0uOTYzYTEgMSAwIDAgMCAtMS4xMSAwbC0xLjQ0NS45NjN2LTcuMTMxem0tMTUgMGg5djlhMSAxIDAgMCAwIDEuNTU1LjgzMmwyLjQ0NS0xLjYzMiAyLjQ0NSAxLjYzYTEgMSAwIDAgMCAxLjU1NS0uODN2LTloOXYyMGgtMnYtMWExIDEgMCAwIDAgLTEtMWgtNmExIDEgMCAwIDAgLTEgMXYzaC0xNnptMTMgMjZhMy4wMDYgMy4wMDYgMCAwIDEgLTIuODI5LTJoNS44Mjl2MnptOSA0aC00di0xMGg0em0yLThoMTFhMyAzIDAgMCAwIDMtM3YtNGEzIDMgMCAwIDEgNiAwdjZhNy4wMDggNy4wMDggMCAwIDEgLTcgN2gtMTN6bTEzIDhhOS4wMSA5LjAxIDAgMCAwIDktOXYtNmE1IDUgMCAwIDAgLTEwIDB2NGExIDEgMCAwIDEgLTEgMWgtM3YtN2E1LjAwNiA1LjAwNiAwIDAgMSA1LTVoOGE1LjAwNiA1LjAwNiAwIDAgMSA1IDV2MjFoLTE4di00em0xMyA4aC0xOHYtMmgxOHoiIGZpbGw9IiMyMzFmMjAiLz48L2c+PC9zdmc+Cg==" type="image/x-icon">
	</head>
	<body>
		<form action="ActionController" method="post">
			<input type="submit" name="action" value="Back to Main Menu" style="font-family: 'Comfortaa'">
		</form>
		<div align="center">
			<table border="1" cellpadding="5" style="font-family: 'Comfortaa'">
	            <h1 style="font-family: 'Comfortaa'">List of drivers</h1>
	            
	            <form action="ActionController" method="post">
	            	<label style="font-family: 'Comfortaa'">Search Driver</label>
	            	
	            	<span style="margin-left:1em"></span>
					
					<c:set var = "boxvalue" value = "${sdbvalue}"/>
					<c:set var = "atrdrivval" value = "${attributedriver}"/>
	            	<input type="search" name="search driver box" style="font-family: 'Comfortaa'" value="${boxvalue}" onchange="setCheckedValueOfRadioButtonGroup('${atrdrivval}')">
	            	<span style="margin-left:1em">
						<input type="submit" name="action" value="Search Driver by" style="font-family: 'Comfortaa'" />
					</span>
					
					<script>
						function setCheckedValueOfRadioButtonGroup(vValue) {
							var radios = document.getElementsByName("attribute driver");
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
					<input type="radio" id="kode" name="attribute driver" value="kode">
						<label for="kode" style="font-family: 'Comfortaa'">Kode</label><span style="margin-left:1em"></span>
					<input type="radio" id="fullname" name="attribute driver" value="full_name">
						<label for="fullname" style="font-family: 'Comfortaa'">Full Name</label><span style="margin-left:1em"></span>
					<input type="radio" id="email" name="attribute driver" value="email">
						<label for="email" style="font-family: 'Comfortaa'">Email</label><span style="margin-left:1em"></span>
					<input type="radio" id="telpnum" name="attribute driver" value="telp_no">
						<label for="telpnum" style="font-family: 'Comfortaa'">Telephone Number</label><span style="margin-left:1em"></span>
					<input type="radio" id="numplat" name="attribute driver" value="no_plat">
						<label for="numplat" style="font-family: 'Comfortaa'">Plat Number</label><span style="margin-left:1em"></span>
					<input type="radio" id="merk" name="attribute driver" value="merk">
						<label for="merk" style="font-family: 'Comfortaa'">Merk</label><span style="margin-left:1em"></span>
					<input type="radio" id="street" name="attribute driver" value="street">
						<label for="street" style="font-family: 'Comfortaa'">Street</label><span style="margin-left:1em"></span>
					
					<br><br>
					
				</form>
	            <tr>
	                <th>Full Name</th>
	                <th>Email</th>
	                <th>Telephone Number</th>
	                <th>Motor</th>
	                <th>Location</th>
	                <th>Update</th>
	                <th>Delete</th>
	            </tr>
	            <c:forEach items="${dataList}" var="dataItem">
	            	<form action="ActionController" method="post">
				        <tr>
				            <td>${dataItem.getFull_name()}</td>
				            <td>${dataItem.getEmail()}</td>
				            <td>${dataItem.getTelp_no()}</td>
				            <td>${dataItem.getMotor().getNo_Plat()} - ${dataItem.getMotor().getMerk()}</td>
				            <td>${dataItem.getLocation().getStreet()}</td>
				            <td><input type="submit" name="action" value="update driver" style="font-family: 'Comfortaa'"></td>
				        	<td><input type="submit" name="action" value="delete driver" style="font-family: 'Comfortaa'"></td>
				        </tr>
				        <input type="hidden" name="kode" value="${dataItem.getKode()}">
				        <input type="hidden" name="fullname" value="${dataItem.getFull_name()}">
				        <input type="hidden" name="email" value="${dataItem.getEmail()}">
				        <input type="hidden" name="telpnum" value="${dataItem.getTelp_no()}">
				        <input type="hidden" name="numplat" value="${dataItem.getMotor().getNo_Plat()}">
				        <input type="hidden" name="merk" value="${dataItem.getMotor().getMerk()}">
				     </form>
			    </c:forEach>
	        </table>
		</div>
	</body>
</html>