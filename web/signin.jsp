<%-- 
    Document   : signin
    Created on : Apr 25, 2020, 3:18:11 PM
    Author     : rayhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign in</title>
    </head>
    <body>
        <h1>Sign In</h1>
        <form action="ActionController" method="post">
            <label for="email"> Enter E-mail :</label><br>
            <input type="email" id="email" name="email" placeholder="E-mail" required> <BR><br>
            
            <label for="pwd"> Enter Password :</label><br>
            <input type="password" id="pwd" name="pwd" placeholder="Password" required> <BR><br>
            
            <input type="hidden" name="action" value="submitLogin">
            <input type="submit" />
        </form>
    </body>
</html>
