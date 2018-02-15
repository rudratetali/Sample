
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer View Menu</title>
</head>
<body>
<h1>Booked Flights</h1>

<c:if test="${requestScope.cancelled == 'true' }">
<c:out value="Invalied credentials " escapeXml="true"></c:out>
</c:if>
<c:if test="${requestScope.cancelled == 'false' }">
<c:out value="Invalied credentials " escapeXml="true"></c:out>
</c:if>
<c:if test="${requestScope.checkIn == 'true' }">
<c:out value="Invalied credentials " escapeXml="true"></c:out>
</c:if>
<c:if test="${requestScope.checkIn == 'false' }">
<c:out value="Invalied credentials " escapeXml="true"></c:out>
</c:if>
<c:if test="${requestScope.alreadyCheck == 'true' }">
<c:out value="Invalied credentials " escapeXml="true"></c:out>
</c:if>
<c:if test="${requestScope.alreadyCheck == 'false' }">
<c:out value="Invalied credentials " escapeXml="true"></c:out>
</c:if>
<!-- Change the code.. -->



<form action="CustomerViewMenu" method= get>
<h4>Search:</h4>

<input type="submit" name="search" value="PNR" required/>
<input type="submit" name="search" value= "Date" required/>
</form>
<br>
<br>
<br>

<table border=3>
<tr>
<th>PNR</th>
<th>Flight No</th>
<th>Flight Name</th>
<th>From</th>
<th>To</th>
<th>Date</th>
<th>Departure</th>
<th>Arrival</th>
<th>Count</th>
<th>Cancel</th>
<th>Check-in</th>
</tr>


<c:forEach items="${sessionScope.flightList}" var="flight">
<tr>
<td><c:out value="${flight.getPnrId()}"></c:out></td>
<td><c:out value="${flight.getFlightNo()}"></c:out></td>
<td><c:out value="${flight.getFlightName()}"></c:out></td>
<td><c:out value="${flight.getSource()}"></c:out></td>
<td><c:out value="${flight.getDestination()}"></c:out></td>
<td><c:out value="${flight.getDate()}"></c:out></td>
<td><c:out value="${flight.getDepartureTime()}"></c:out></td>
<td><c:out value="${flight.getArrivalTime()}"></c:out></td>
<td><c:out value="${flight.getCount()}"></c:out></td>
<td><a href="http://localhost:8080/AirportWeb/Cancel?PNRNo=<c:out value="${flight.getPnrId()}"/>">Cancel</a></td>
<td><a href="http://localhost:8080/AirportWeb/Check?PNRNo=<c:out value="${flight.getPnrId()}"/>">Check-in</a></td>
</tr>
 </c:forEach>
</table>
<input type="button" value="Go Back" onclick="window.location.href='CustomerLoginMenu.jsp';"/>
</body>
</html>