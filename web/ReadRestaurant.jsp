<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Data Restaurant</title>
		<link rel = "icon" href="data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIj8+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiBoZWlnaHQ9IjUxMnB4IiB2aWV3Qm94PSIwIDAgNTggNTgiIHdpZHRoPSI1MTJweCI+PGcgaWQ9IlBhZ2UtMSIgZmlsbD0ibm9uZSIgZmlsbC1ydWxlPSJldmVub2RkIj48ZyBpZD0iMDQwLS0tU3RyZWV0LUJhciIgZmlsbC1ydWxlPSJub256ZXJvIj48cGF0aCBpZD0iU2hhcGUiIGQ9Im01NiAzNXYyMmMwIC41NTIyODQ3LS40NDc3MTUzIDEtMSAxaC01MmMtLjU1MjI4NDc1IDAtMS0uNDQ3NzE1My0xLTF2LTIyeiIgZmlsbD0iI2YyOWMxZiIvPjxwYXRoIGlkPSJTaGFwZSIgZD0ibTEzIDU2Yy0uNTUyMjg0NyAwLTEtLjQ0NzcxNTMtMS0xdi03YzAtLjU1MjI4NDcuNDQ3NzE1My0xIDEtMXMxIC40NDc3MTUzIDEgMXY3YzAgLjU1MjI4NDctLjQ0NzcxNTMgMS0xIDF6IiBmaWxsPSIjYmRjM2M3Ii8+PHBhdGggaWQ9IlNoYXBlIiBkPSJtMjkgNTZjLS41NTIyODQ3IDAtMS0uNDQ3NzE1My0xLTF2LTdjMC0uNTUyMjg0Ny40NDc3MTUzLTEgMS0xczEgLjQ0NzcxNTMgMSAxdjdjMCAuNTUyMjg0Ny0uNDQ3NzE1MyAxLTEgMXoiIGZpbGw9IiNiZGMzYzciLz48cGF0aCBpZD0iU2hhcGUiIGQ9Im00NSA1NmMtLjU1MjI4NDcgMC0xLS40NDc3MTUzLTEtMXYtN2MwLS41NTIyODQ3LjQ0NzcxNTMtMSAxLTFzMSAuNDQ3NzE1MyAxIDF2N2MwIC41NTIyODQ3LS40NDc3MTUzIDEtMSAxeiIgZmlsbD0iI2JkYzNjNyIvPjxwYXRoIGlkPSJTaGFwZSIgZD0ibTE3IDQ1YzAgMi4yMDkxMzktMS43OTA4NjEgNC00IDRzLTQtMS43OTA4NjEtNC00eiIgZmlsbD0iI2YwYzQxOSIvPjxwYXRoIGlkPSJTaGFwZSIgZD0ibTMzIDQ1YzAgMi4yMDkxMzktMS43OTA4NjEgNC00IDRzLTQtMS43OTA4NjEtNC00eiIgZmlsbD0iI2YwYzQxOSIvPjxwYXRoIGlkPSJTaGFwZSIgZD0ibTQ5IDQ1YzAgMi4yMDkxMzktMS43OTA4NjEgNC00IDRzLTQtMS43OTA4NjEtNC00eiIgZmlsbD0iI2YwYzQxOSIvPjxwYXRoIGlkPSJTaGFwZSIgZD0ibTkgNThjMC0yLjIwOTEzOSAxLjc5MDg2MS00IDQtNHM0IDEuNzkwODYxIDQgNHoiIGZpbGw9IiM3ZjhjOGQiLz48cGF0aCBpZD0iU2hhcGUiIGQ9Im0yNSA1OGMwLTIuMjA5MTM5IDEuNzkwODYxLTQgNC00czQgMS43OTA4NjEgNCA0eiIgZmlsbD0iIzdmOGM4ZCIvPjxwYXRoIGlkPSJTaGFwZSIgZD0ibTQxIDU4YzAtMi4yMDkxMzkgMS43OTA4NjEtNCA0LTRzNCAxLjc5MDg2MSA0IDR6IiBmaWxsPSIjN2Y4YzhkIi8+PGcgZmlsbD0iI2YyOWMxZiI+PHBhdGggaWQ9IlNoYXBlIiBkPSJtNDQgMjdjMS4xMDQ1Njk1IDAgMiAuODk1NDMwNSAyIDJ2NWgtNHYtNWMwLTEuMTA0NTY5NS44OTU0MzA1LTIgMi0yeiIvPjxwYXRoIGlkPSJTaGFwZSIgZD0ibTM0IDI3YzEuMTA0NTY5NSAwIDIgLjg5NTQzMDUgMiAydjVoLTR2LTVjMC0xLjEwNDU2OTUuODk1NDMwNS0yIDItMnoiLz48cGF0aCBpZD0iU2hhcGUiIGQ9Im0yNCAyN2MxLjEwNDU2OTUgMCAyIC44OTU0MzA1IDIgMnY1aC00di01YzAtMS4xMDQ1Njk1Ljg5NTQzMDUtMiAyLTJ6Ii8+PHBhdGggaWQ9IlNoYXBlIiBkPSJtMTQgMjdjMS4xMDQ1Njk1IDAgMiAuODk1NDMwNSAyIDJ2NWgtNHYtNWMwLTEuMTA0NTY5NS44OTU0MzA1LTIgMi0yeiIvPjwvZz48cGF0aCBpZD0iU2hhcGUiIGQ9Im03IDE3LjAzNnYxNy40aC00di0xNy40MzZjLjQ3MjY1MzAxLjM0OTUzODIgMS4wMjY5MTAxMi41NzIyNzM4IDEuNjEuNjQ3Ljg0NDUyMTg5LjA5NDYzMDIgMS42OTQ1MTE0NC0uMTIyNjY4NCAyLjM5LS42MTF6IiBmaWxsPSIjODA1MzMzIi8+PHBhdGggaWQ9IlNoYXBlIiBkPSJtNTUgMTd2MTcuNDRoLTR2LTE3LjRjLjY5NTQ4ODYuNDg4MzMxNiAxLjU0NTQ3ODEuNzA1NjMwMiAyLjM5LjYxMS41ODM0NjUxLS4wNzU5MDkgMS4xMzc3NTM1LS4zMDAwMzQzIDEuNjEtLjY1MXoiIGZpbGw9IiM4MDUzMzMiLz48cGF0aCBpZD0iU2hhcGUiIGQ9Im01OCAzNWMtLjAwODE2NzIgMS4xMDExNjg4LS44OTg4MzEyIDEuOTkxODMyOC0yIDJoLTU0Yy0xLjEwMzIwMTg3LS4wMDMyOTQ4LTEuOTk2NzA1Mi0uODk2Nzk4MS0yLTItLjAwMjgyMzA3LS41MzA1OTA1LjIxMDE0MTEtMS4wMzk1Mzg4LjU5LTEuNDEuMzcwNDYxMi0uMzc5ODU4OS44Nzk0MDk0Ny0uNTkyODIzMSAxLjQxLS41OWg1NGMxLjEwMzIwMTkuMDAzMjk0OCAxLjk5NjcwNTIuODk2Nzk4MSAyIDJ6IiBmaWxsPSIjYTU2YTQzIi8+PHBhdGggaWQ9IlNoYXBlIiBkPSJtNTAuNTkuOTNjLS4zNjY3MzQyLS41NzkxMzI5Ni0xLjAwNDUxNTUtLjkzMDEwMTQxLTEuNjktLjkzaC0zOS44Yy0uNjg1NDg0NDYtLjAwMDEwMTQxLTEuMzIzMjY1ODIuMzUwODY3MDQtMS42OS45M2wtNi40MSAxMC4wN3YzLjgzYy0uMDA0ODE1MjMgMS40Njg4Mjk4Ljc1MjE1NjU1IDIuODM1MTYzOSAyIDMuNjEuNDg5NzE0MzQuMjk3NzQxMSAxLjAzOTc5NTA2LjQ4MjI0MDIgMS42MS41NCAxLjEyNDA5NjQ1LjExNDIxMzQgMi4yNDM4NjY4LS4yNTM4ODM4IDMuMDgwOTQ3NzMtMS4wMTI3ODYzczEuMzEyODU1OTMtMS44MzczMzYyIDEuMzA5MDUyMjctMi45NjcyMTM3Yy4wMDAwMDAwNSAyLjIwOTEzOSAxLjc5MDg2MSAzLjk5OTk5OTkgNCAzLjk5OTk5OTlzNC0xLjc5MDg2MDkgNC0zLjk5OTk5OTljMCAyLjIwOTEzOSAxLjc5MDg2MSA0IDQgNHM0LTEuNzkwODYxIDQtNGMwIDIuMjA5MTM5IDEuNzkwODYxIDQgNCA0czQtMS43OTA4NjEgNC00YzAgMi4yMDkxMzkgMS43OTA4NjEgNCA0IDRzNC0xLjc5MDg2MSA0LTRjMCAyLjIwOTEzOSAxLjc5MDg2MSA0IDQgNHM0LTEuNzkwODYxIDQtNGMtLjAwMzgwMzcgMS4xMjk4Nzc1LjQ3MTk3MTMgMi4yMDgzMTEyIDEuMzA5MDUyMyAyLjk2NzIxMzcuODM3MDgwOS43NTg5MDI1IDEuOTU2ODUxMiAxLjEyNjk5OTcgMy4wODA5NDc3IDEuMDEyNzg2My41NzAyMDQ5LS4wNTc3NTk4IDEuMTIwMjg1Ny0uMjQyMjU4OSAxLjYxLS41NCAxLjI0Nzg0MzQtLjc3NDgzNjEgMi4wMDQ4MTUyLTIuMTQxMTcwMiAyLTMuNjF2LTMuODN6IiBmaWxsPSIjZjNkNTViIi8+PHBhdGggaWQ9IlNoYXBlIiBkPSJtMTQtLjAwMDAwMDAyLTUgMTEuMDAwMDAwMDJoLThsNi40MS0xMC4wN2MuMzY2NzM0MTgtLjU3OTEzMjk2IDEuMDA0NTE1NTQtLjkzMDEwMTQxIDEuNjktLjkzeiIgZmlsbD0iI2YwYzQxOSIvPjxwYXRoIGlkPSJTaGFwZSIgZD0ibTIwIDAtMyAxMWgtOGw1LTExeiIgZmlsbD0iI2RmNGQ2MCIvPjxwYXRoIGlkPSJTaGFwZSIgZD0ibTI3IDAtMiAxMWgtOGwzLTExeiIgZmlsbD0iI2YwYzQxOSIvPjxwYXRoIGlkPSJTaGFwZSIgZD0ibTMzIDExaC04bDItMTFoNHoiIGZpbGw9IiNkZjRkNjAiLz48cGF0aCBpZD0iU2hhcGUiIGQ9Im00MSAxMWgtOGwtMi0xMWg3eiIgZmlsbD0iI2YwYzQxOSIvPjxwYXRoIGlkPSJTaGFwZSIgZD0ibTQ5IDExaC04bC0zLTExaDZ6IiBmaWxsPSIjZGY0ZDYwIi8+PHBhdGggaWQ9IlNoYXBlIiBkPSJtNTcgMTFoLThsLTUtMTEuMDAwMDAwMDIgNC45LjAwMDAwMDAyYy42ODU0ODQ1LS4wMDAxMDE0MSAxLjMyMzI2NTguMzUwODY3MDQgMS42OS45M3oiIGZpbGw9IiNmMGM0MTkiLz48cGF0aCBpZD0iU2hhcGUiIGQ9Im00OSAxMXY0YzAgMi4yMDkxMzktMS43OTA4NjEgNC00IDRzLTQtMS43OTA4NjEtNC00di00eiIgZmlsbD0iI2ZmNTM2NCIvPjxwYXRoIGlkPSJTaGFwZSIgZD0ibTMzIDExdjRjMCAyLjIwOTEzOS0xLjc5MDg2MSA0LTQgNHMtNC0xLjc5MDg2MS00LTR2LTR6IiBmaWxsPSIjZmY1MzY0Ii8+PHBhdGggaWQ9IlNoYXBlIiBkPSJtMTcgMTF2NGMwIDIuMjA5MTM5LTEuNzkwODYxIDQtNCA0cy00LTEuNzkwODYxLTQtNHYtNHoiIGZpbGw9IiNmZjUzNjQiLz48cGF0aCBpZD0iU2hhcGUiIGQ9Im0xOSA0M3YxYzAgLjU1MjI4NDctLjQ0NzcxNTMgMS0xIDFoLTEwYy0uNTUyMjg0NzUgMC0xLS40NDc3MTUzLTEtMXYtMWMuMDAzMjk0OC0xLjEwMzIwMTkuODk2Nzk4MTMtMS45OTY3MDUyIDItMmg4YzEuMTAzMjAxOS4wMDMyOTQ4IDEuOTk2NzA1Mi44OTY3OTgxIDIgMnoiIGZpbGw9IiM0NjRmNWQiLz48cGF0aCBpZD0iU2hhcGUiIGQ9Im0zNSA0M3YxYzAgLjU1MjI4NDctLjQ0NzcxNTMgMS0xIDFoLTEwYy0uNTUyMjg0NyAwLTEtLjQ0NzcxNTMtMS0xdi0xYy4wMDMyOTQ4LTEuMTAzMjAxOS44OTY3OTgxLTEuOTk2NzA1MiAyLTJoOGMxLjEwMzIwMTkuMDAzMjk0OCAxLjk5NjcwNTIuODk2Nzk4MSAyIDJ6IiBmaWxsPSIjNDY0ZjVkIi8+PHBhdGggaWQ9IlNoYXBlIiBkPSJtNTEgNDN2MWMwIC41NTIyODQ3LS40NDc3MTUzIDEtMSAxaC0xMGMtLjU1MjI4NDcgMC0xLS40NDc3MTUzLTEtMXYtMWMuMDAzMjk0OC0xLjEwMzIwMTkuODk2Nzk4MS0xLjk5NjcwNTIgMi0yaDhjMS4xMDMyMDE5LjAwMzI5NDggMS45OTY3MDUyLjg5Njc5ODEgMiAyeiIgZmlsbD0iIzQ2NGY1ZCIvPjwvZz48L2c+PC9zdmc+Cg==" type="image/x-icon">
	</head>
	<body>
		<form action="ActionController" method="post">
			<input type="submit" name="action" value="Back to Main Menu" style="font-family: 'Comfortaa'">
		</form>
		<div align="center">
			<table border="1" cellpadding="5" style="font-family: 'Comfortaa'">
	            <h1 style="font-family: 'Comfortaa'">List of restaurant</h1>
	            
	            <form action="ActionController" method="post">
	            	<label style="font-family: 'Comfortaa'">Search restaurant</label>
	            	
	            	<span style="margin-left:1em"></span>
					
					<c:set var = "boxvalue" value = "${sdbvalue}"/>
					<c:set var = "atrrestval" value = "${attributerest}"/>
	            	<input type="search" name="search restaurant box" style="font-family: 'Comfortaa'" value="${boxvalue}" onchange="setCheckedValueOfRadioButtonGroup('${atrrestval}')">
	            	<span style="margin-left:1em">
						<input type="submit" name="action" value="Search Restaurant by" style="font-family: 'Comfortaa'" />
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
						<input type="submit" name="action" value="Retrieve All Restaurant Data" style="font-family: 'Comfortaa'"/>
					</span>
					
					<br><br>
					<label style="font-family: 'Comfortaa'">Search by :</label>
					<input type="radio" id="name" name="attribute rest" value="name">
						<label for="name" style="font-family: 'Comfortaa'">Name</label><span style="margin-left:1em"></span>
					<input type="radio" id="street" name="attribute rest" value="street">
						<label for="street" style="font-family: 'Comfortaa'">Street</label><span style="margin-left:1em"></span>
					<input type="radio" id="city" name="attribute rest" value="city">
						<label for="city" style="font-family: 'Comfortaa'">City</label><span style="margin-left:1em"></span>
					<input type="radio" id="telp_no" name="attribute rest" value="telp_no">
						<label for="telp_no" style="font-family: 'Comfortaa'">Telephone Number</label><span style="margin-left:1em"></span>
					<input type="radio" id="detail" name="attribute rest" value="detail">
						<label for="detail" style="font-family: 'Comfortaa'">Detail</label><span style="margin-left:1em"></span>
					
					<br><br>
					
				</form>
	            <tr>
	                <th>Name</th>
	                <th>Location</th>
	                <th>Telephone Number</th>
	                <th>Detail</th>
	                <th>Food</th>
	                <th>Update</th>
	                <th>Delete</th>
	            </tr>
	            <c:forEach items="${dataList}" var="dataItem">
	            	<form action="ActionController" method="post">
				        <tr>
				            <td>${dataItem.getName()}</td>
				            <td>${dataItem.getLocation().getStreet()} - ${dataItem.getLocation().getCity()}</td>
				            <td>${dataItem.getTelp_no()}</td>
				            <td>${dataItem.getDetail()}</td>
				            <td><input type="submit" name="action" value="view food" style="font-family: 'Comfortaa'"></td>
				            <td><input type="submit" name="action" value="update restaurant" style="font-family: 'Comfortaa'"></td>
				        	<td><input type="submit" name="action" value="delete restaurant" style="font-family: 'Comfortaa'"></td>
				        </tr>
				        <input type="hidden" name="kode" value="${dataItem.getKode()}">
				        <input type="hidden" name="name" value="${dataItem.getName()}">
				        <input type="hidden" name="locationkode" value="${dataItem.getLocation().getKode()}">
				        <input type="hidden" name="street" value="${dataItem.getLocation().getStreet()}">
				        <input type="hidden" name="city" value="${dataItem.getLocation().getCity()}">
				        <input type="hidden" name="telpnum" value="${dataItem.getTelp_no()}">
				        <input type="hidden" name="detail" value="${dataItem.getDetail()}">
				     </form>
			    </c:forEach>
	        </table>
		</div>
	</body>
</html>