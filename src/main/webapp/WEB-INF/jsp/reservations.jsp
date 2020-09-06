<% response.addHeader("Refresh","30"); %>
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
        <div class="col-md-8">
        <%
            Specialist specialist = (Specialist) request.getAttribute("specialist");
            List<Reservations> list = (List<Reservations>) request.getAttribute("list");
        %>

        <h4>Specialist <%=specialist.getName()%> visits:</h4><br>
        <h6>Current visits:</h6>

        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th>Reservation date</th>
                <th>Reservation code</th>
                <th></th>
            </tr>
            <tbody>

        <%
            for (Reservations reservations : list) {
                if (reservations.getReservationStarted() == true) {
        %>
            <tr>
                <form method="POST" action="endVisit">
                <td><%=reservations.getVisitDate()%></td>
                <td><%=reservations.getReservationCode()%></td>
                <td><button type="submit" name="reservationCode" value="<%=reservations.getReservationCode()%>">End visit</button></td>
                </form>
                <hr>
            </tr>
        <%
                }
            }
        %>

            </tbody>
        </table>

        <hr><h6>Upcoming 5 visits:</h6><hr>

        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th>Reservation date</th>
                <th>Reservation code</th>
                <th></th>
                <th></th>
            </tr>
            <tbody>
        <%
            int count = 0;
            for (Reservations reservations : list) {
                if (reservations.getReservationEnded() == false && reservations.getReservationStarted() == false) {
        %>
            <tr>
                <form method="POST" action="startVisit">
                <td><%=reservations.getVisitDate()%></td>
                <td><%=reservations.getReservationCode()%></td>
                <td><button type="submit" name="reservationCode" value="<%=reservations.getReservationCode()%>">Start visit</button></td>
                <td><a href="/reservationCancel?reservationCode=<%=reservations.getReservationCode()%>">Cancel visit</a></td>
                </form>
            </tr>
        <%
                }
            count++;
            if (count == 5) break;
            }
        %>

            </tbody>
        </table>

        <a href="/">Back</a>
        </div>
    </body>
</html>
