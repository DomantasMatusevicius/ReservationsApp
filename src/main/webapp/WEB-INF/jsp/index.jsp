<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.example.ReservationsApp.data.Reservations" %>
<%@page import="com.example.ReservationsApp.data.Specialist" %>
<%@page import="java.util.Calendar" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Specialists list</title>
    </head>
    <body>
    <h2>Customers department</h2>
    <h4>Make a reservation:</h4>

        <%
            List<Specialist> list = (List<Specialist>) request.getAttribute("list");
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(Calendar.getInstance().getTime());
        %>

        <form method="POST" action="saveReservation">
        <table>
          <thead>
            <tr>
                <th><label for="chooseSpecialist">Choose specialist:</label></th>
                <th>Choose a date:</th>
                <th></th>
            </tr>
          </thead>
          <tbody>
            <tr>
            <td>
          <select name="specialistId" id="chooseSpecialist">

          <%
                for (Specialist specialist : list) {
          %>
                <option name="specialistId" value="<%=specialist.getId()%>"><%=specialist.getName()%></option>
          <%
                }
          %>

            </td>
            <td><input name="visit_date" type="datetime-local" value="<%=(timeStamp!=null)?timeStamp:""%>" min="<%=(timeStamp!=null)?timeStamp:""%>"></td>
            <td><input type="submit" value="save"></td>
          </tr>
          </tbody>
          </table>
          </select>
        </form>
        <br><br>

        <h4>Check your reservation:</h4>
        <form method="POST" action="checkReservation">
        Enter your reservation code:
        <input name="genCode" value="">
        <input type="submit" value="check">
        </form>

        <br><br><hr>

        <h2>Specialists department</h2>
        <h4>Check your visits:</h4>
        <form method="POST" action="checkVisits">
        <label for="specialist">You are:</label>
        <select name="specialistId" id="specialist">

        <%
            for (Specialist specialist : list) {
        %>

                <option name="specialistId" value="<%=specialist.getId()%>"><%=specialist.getName()%></option>

        <%
            }
        %>
        <br><br>
        <input type="submit" value="check visits">
        </form>
    </body>
</html>