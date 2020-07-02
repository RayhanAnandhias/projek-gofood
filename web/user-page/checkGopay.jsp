<%-- 
    Document   : checkGopay
    Created on : Apr 28, 2020, 11:27:35 AM
    Author     : rayhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cek Saldo Gopay</title>
    </head>
    <body>
        <h1>Cek Saldo Gopay</h1>
        <fieldset style="display: inline-block; width: 400px">
            <legend>Saldo Gopay</legend>
            <fmt:setLocale value="id_ID"/> <fmt:formatNumber value = "${user.getSaldo_gopay()}" type = "currency"/>
        </fieldset>
    </body>
</html>
