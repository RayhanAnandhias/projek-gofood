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
                    <input type="submit" name="action" value="Back to Main menu" style="font-family: 'Comfortaa'">
		</form>
		<div align="center">
			<table border="1" cellpadding="5" style="font-family: 'Comfortaa'">
	            <h1 style="font-family: 'Comfortaa'">List of Food</h1>
	            
	            <form action="ActionController" method="post">
	            	<label style="font-family: 'Comfortaa'">Search food</label>
	            	
	            	<span style="margin-left:1em"></span>
					
					<c:set var = "boxvalue" value = "${sdbvalue}"/>
					<c:set var = "atrrestval" value = "${attributerest}"/>
	            	<input type="search" name="search food box" style="font-family: 'Comfortaa'" value="${boxvalue}" onchange="setCheckedValueOfRadioButtonGroup('${atrrestval}')">
	            	<span style="margin-left:1em">
						<input type="submit" name="action" value="Search food By" style="font-family: 'Comfortaa'" />
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
						<input type="submit" name="action" value="Retrieve All Food data" style="font-family: 'Comfortaa'"/>
					</span>
					
					<br><br>
					<label style="font-family: 'Comfortaa'">Search by :</label>
					<input type="radio" id="name" name="attribute rest" value="name">
						<label for="name" style="font-family: 'Comfortaa'">Name</label><span style="margin-left:1em"></span>
					<input type="radio" id="detail" name="attribute rest" value="detail">
						<label for="detail" style="font-family: 'Comfortaa'">Detail</label><span style="margin-left:1em"></span>
					<br><br>
					
				</form>
	            <tr>
	                <th>Name</th>
	                <th>Price</th>
<!--	                <th>Quantity</th>-->
	                <th>Detail</th>
                        <th>Available at Restaurant</th>
	            </tr>
	            <c:forEach items="${dataList}" var="dataItem">
	            	<form action="ActionController" method="post">
				        <tr>
				            <td>${dataItem.getName()}</td>
				            <td><fmt:setLocale value = "id_ID"/>
         						<fmt:formatNumber value = "${dataItem.getPrice()}" type = "currency"/>
         					</td>
<!--				            <td>$dataItem.getQuantity()}</td>-->
				            <td>${dataItem.getDetail()}</td>
				            <td><input type="submit" name="action" value="food available at" style="font-family: 'Comfortaa'"></td>
				        </tr>
				        <input type="hidden" name="kode" value="${dataItem.getKode()}">
				        <input type="hidden" name="name" value="${dataItem.getName()}">
				        <input type="hidden" name="price" value="${dataItem.getPrice()}">
<!--				        <input type="hidden" name="quantity" value="$dataItem.getQuantity()}">-->
				        <input type="hidden" name="detail" value="${dataItem.getDetail()}">
				     </form>
			    </c:forEach>
	        </table>
		</div>
	</body>
</html>