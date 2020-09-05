<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.example.ReservationsApp.data.Reservations" %>
<%@page import="com.example.ReservationsApp.data.Specialist" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservations</title>
    </head>
    <body>
        <%
            Specialist specialist = (Specialist) request.getAttribute("specialist");
        %>
        <hr>Upcoming reservations for the <%=specialist.getName()%> are:<br><hr>

        <%
            List<Reservations> list = (List<Reservations>) request.getAttribute("list");
            for (Reservations reservations : list) {
        %>
        <b><%=reservations.getVisitDate()%></b>

        <hr>
        <%
            }
        %>

        <a href="/">Back</a>
    </body>
</html>
