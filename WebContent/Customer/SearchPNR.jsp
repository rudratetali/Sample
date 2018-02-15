<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search By PNR</title>
</head>
<body>
	<form action="SearchPNR" method="post">
		Enter the PNR Number: <input type="number" name="pnrId" />
	</form>
	<c:if test="${requestScope.searchSuccess == 'false' }">
	<script>
	alert("Enter a valid PNR");
	</script>
	</c:if>
	<c:if test="${requestScope.searchSuccess == 'true' }">
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

			<c:forEach items="${requestScope.flightList}" var="flight">
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
					<td><a
						href="http://localhost:8080/AirportWeb/Cancel?PNRNo=<c:out value="${flight.getPnrId()}"/>">Cancel</a></td>
					<td><a
						href="http://localhost:8080/AirportWeb/Check?PNRNo=<c:out value="${flight.getPnrId()}"/>">Check-in</a></td>
				</tr>
			</c:forEach>
		</table>

	</c:if>
	<input type="button" value="Go Back" onclick="window.location.href='CustomerViewMenu.jsp';"/>
</body>
</html>