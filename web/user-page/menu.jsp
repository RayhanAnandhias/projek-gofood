<%-- 
    Document   : menu
    Created on : Apr 27, 2020, 8:34:34 PM
    Author     : rayhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Menu</title>
    </head>
    <body>
        <div align="center">
            <h1>Menu</h1><br>
            <form action="ActionController" method="post">
            <input type="submit" name="action" value="Update Profile">
            <input type="submit" name="action" value="Check Gopay">
            <input type="submit" name="action" value="What do you want to eat?">
            <input type="submit" name="action" value="Create Order">
            <input type="submit" name="action" value="See Order">
            <input type="submit" name="action" value="Log Out">
        </form>
        </div> 
        
    </body>
</html>
