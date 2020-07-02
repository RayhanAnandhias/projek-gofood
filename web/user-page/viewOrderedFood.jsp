<%--
  Created by IntelliJ IDEA.
  User: rayhan
  Date: 01/07/2020
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>View Ordered Food</title>
</head>
<body>
    <form action="ActionController" method="post">
        <input type="submit" name="action" value="Back to Main menu" style="font-family: 'Comfortaa'">
    </form>
    <div align="center">
        <table border="1" cellpadding="5" style="font-family: 'Comfortaa'">
            <tr>
                <th>Food Name</th>
                <th>Detail</th>
                <th>Quantity</th>
                <th>Notes</th>
                <th>sub total</th>
            </tr>
            <c:forEach items="${dataList}" var="dataItem">
                <form action="ActionController" method="post">
                    <tr>
                        <td>${util.getFoodByID(dataItem.getIdFood()).getName()}</td>
                        <td>${util.getFoodByID(dataItem.getIdFood()).getDetail()}</td>
                        <td>${dataItem.getQuantity()}</td>
                        <td>${dataItem.getNote()}</td>
                        <td><fmt:setLocale value = "id_ID"/>
                            <fmt:formatNumber value = "${dataItem.getQuantity()*util.getFoodByID(dataItem.getIdFood()).getPrice()}" type = "currency"/>
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </table>
    </div>
</body>
</html>
