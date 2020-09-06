<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.example.ReservationsApp.data.Reservations" %>
<%@page import="com.example.ReservationsApp.data.Specialist" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>Reservations</title>
    </head>
    <body>
        <%
            Reservations reservation = (Reservations) request.getAttribute("value");

            long different = (long) request.getAttribute("different");

            int inLine = (int) request.getAttribute("inLine");

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;

            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;

            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;

            long elapsedSeconds = different / secondsInMilli;
        %>

        <div class="col-md-10">
        <br>
        Below you can find the information for upcoming reservation. <b>Please save the reservation code in order to check your reservation.</b><br><br>
        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th>Reservation code</td>
                    <th>Specialist</td>
                    <th>Visit date</td>
                    <th>Time left until visit</td>
                    <th>Visits in line before you</th>
                    <th></th>
                </tr>
            <tbody>
                <tr>
                    <td><b><%=reservation.getReservationCode()%></b></td>
                    <td><%=reservation.getSpecialist().getName()%></td>
                    <td><%=reservation.getVisitDate()%></td>
                    <td><%=elapsedDays%> d, <%=elapsedHours%> h, <%=elapsedMinutes%> min</td>
                    <td><%=inLine%> visits</td>
                    <td><a href="/reservationCancel?reservationCode=<%=reservation.getReservationCode()%>">Cancel visit</a>
                </tr>
            </tbody>
        </table>


        <br><a href="/">Back</a>
        </div>
    </body>
</html>
