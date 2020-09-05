<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.example.ReservationsApp.data.Reservations" %>
<%@page import="com.example.ReservationsApp.data.Specialist" %>
<%@page import="java.util.Calendar" %>
<%@page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Make a reservation</title>
    </head>
    <body>

        <%
            Specialist value = (Specialist) request.getAttribute("value");
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(Calendar.getInstance().getTime());
        %>

        <form method="POST" action="saveReservation">
             <input name="visit_date" type="datetime-local" value="<%=(timeStamp!=null)?timeStamp:""%>">
             <input type="hidden" name="specialistId" value="<%=value.getId()%>">
             <input type="submit" value="save">
        </form>
        <hr>
        <a href="/">Back</a>

    </body>
</html>