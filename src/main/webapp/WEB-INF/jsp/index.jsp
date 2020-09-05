<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.example.ReservationsApp.data.Specialist" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Specialists list</title>
    </head>
    <body>

        <%
            List<Specialist> list = (List<Specialist>) request.getAttribute("list");
            for (Specialist specialist : list) {
        %>
        <hr>
        <%=specialist.getId()%> <%=specialist.getName()%>
        <a href="/makeReservation?specialistId=<%=specialist.getId()%>">Make a reservation</a>
        <a href="/reservations?specialistId=<%=specialist.getId()%>">Current reservations</a>
        <%
            }
        %>

    </body>
</html>