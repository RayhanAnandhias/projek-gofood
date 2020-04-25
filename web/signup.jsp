<%-- 
    Document   : signup
    Created on : Apr 25, 2020, 11:39:26 AM
    Author     : rayhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
        <h1>Sign Up</h1>
        <form action="ActionController" method="post">
            <label for="name"> Enter Name :</label><br>
            <input type="text" id="name" name="name" placeholder="Name" required> <BR><br>
            
            <label for="no_telp"> Enter Telephone Number :</label><br>
            <input type="tel" id="no_telp" name="no_telp" placeholder="No Telp" pattern="^(^\+62|62|^08)(\d{3,4}-?){2}\d{3,4}$" required> <BR><br>
            
            <label for="email"> Enter E-mail :</label><br>
            <input type="email" id="email" name="email" placeholder="E-mail" required> <BR><br>
            
            <label for="pwd"> Enter Password :</label><br>
            <input type="password" id="pwd" name="pwd" placeholder="Password" required> <BR><br>
            
            <input type="hidden" name="saldo" value=0>
            <input type="hidden" name="action" value="insertUser">
            <input type="submit" />
        </form>
    </body>
</html>
