<%--
  Created by IntelliJ IDEA.
  User: rayhan
  Date: 25/05/2020
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>view order</title>
</head>
<body>
    <form action="ActionController" method="post">
        <input type="submit" name="action" value="Back to Main menu" style="font-family: 'Comfortaa'">
    </form>
    <div align="center">
        <table border="1" cellpadding="5" style="font-family: 'Comfortaa'">
            <tr>
                <th>ID Pesanan</th>
                <th>Date</th>
                <th>Restaurant</th>
                <th>Driver</th>
                <th>Rute Driver</th>
                <th>Payment Method</th>
                <th>Total Cost</th>
                <th>Item Pesanan</th>
            </tr>
            <c:forEach items="${dataList}" var="dataItem">
                <form action="ActionController" method="post">
                    <tr>
                        <td>${dataItem.getKode()}</td>
                        <td>${dataItem.getDate()}</td>
                        <td>${util.getRestaurantByID(dataItem.getRestaurantId()).getName()}</td>
                        <td>${util.getDriverByID(dataItem.getDriverId()).getFull_name()}</td>
                        <td>${util.printRoute(util.getRestaurantByID(dataItem.getRestaurantId()).getLocation(), user.getLocation())}</td>
                        <td>${util.translate_method(dataItem.getPaymentMethod())}</td>
                        <td><fmt:setLocale value = "id_ID"/>
                            <fmt:formatNumber value = "${dataItem.getTotalCost()}" type = "currency"/>
                        </td>
                        <td><input type="submit" name="action" value="view ordered food"></td>
                    </tr>
                        <input type="hidden" name="kodePesanan" value="${dataItem.getKode()}">
                </form>
            </c:forEach>
        </table>
    </div>
</body>
</html>
