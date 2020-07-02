<%-- 
    Document   : createOrder
    Created on : Apr 28, 2020, 9:54:30 PM
    Author     : rayhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Order</title>
    </head>
    <body>
        <form action="ActionController" method="post">    
            <h1>Added Order On ${resto.getName()} Restaurant </h1><br>
            <h2>Data Restaurant</h2>


            <table border="1" cellpadding="5">
                <tr>
                    <th>Name</th>
                    <th>Location</th>
                    <th>Telephone Number</th>
                    <th>Detail</th>
                </tr>
                <tr>
                    <td>${resto.getName()}</td>
                    <td>${resto.getLocation().getStreet()}</td>
                    <td>${resto.getTelp_no()}</td>
                    <td>${resto.getDetail()}</td>
                </tr>
            </table><br>

            <h2> List Pesanan </h2>
            <table border="1" cellpadding="5">
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Detail</th>
                    <th>Quantity</th>
                    <th>Note</th>

                </tr>
               <c:forEach items="${dataList}" var="dataItem">
                        <tr>
                            <input type="hidden" name="kode" value="${dataItem.getKode()}">
                            <td><input type="text" name="name" value="${dataItem.getName()}" readonly></td>
                            <td><input type="text" name="price" value="${dataItem.getPrice()}" readonly></td>
                            <td><input type="text" name="detail" value="${dataItem.getDetail()}" readonly></td>
                            <td><input type="text" name="quantity"></td>
                            <td><input type="text" name="note"></td>
                        </tr>
                </c:forEach>
            </table><br>

            <p>Payment Method :</p>
            <input type="radio" id="gopay" name="method" value="gopay">
            <label for="gopay">gopay</label><br>
            <input type="radio" id="cash" name="method" value="cash">
            <label for="cash">cash</label><br><br>

                <input type="hidden" name="idUser" value="${user.getKode()}">
                <input type="hidden" name="kodeResto" value="${resto.getKode()}">
                <input type="hidden" name="action" value="orderFood">
                <input type="submit" value="Order Food"/>
        </form>
    </body>
</html>
