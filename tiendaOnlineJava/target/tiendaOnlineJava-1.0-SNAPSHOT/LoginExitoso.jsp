<%-- 
    Document   : LoginExitoso
    Created on : 10 ene 2024, 11:09:50
    Author     : dawmi
--%>

<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="mgb.model.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bienvenida</title>
</head>
<body>
    <% 
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
    %>
            <h2>Hola <%= user.getNombre() %></h2>
            <p>Bienvenido a nuestra página. ¡Gracias por iniciar sesión!</p>
    <%
        } else {
    %>
            <h2>Error de Acceso</h2>
            <p>No tienes permisos para ver esta página. Por favor, inicia sesión primero.</p>
    <%
        }
    %>
</body>
</html>
