<%-- 
    Document   : index
    Created on : Apr 24, 2020, 8:37:39 PM
    Author     : rayhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gofood App</title>
    </head>
    <body>
        <h1>Welcome To Gofood!</h1>
        <form action="ActionController" method="post">
            <input type="submit" name="action" value="Sign Up">
            <input type="submit" name="action" value="Sign In">
        </form>
    </body>
</html>
