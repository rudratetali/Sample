<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Unassigned FLights</title>
<style>
#bttn {
	margin-left: 94%;
	border-radius: 0;
}
</style>
</head>
<body>
	<jsp:include page="../UI.jsp" />
	<input type="button" value="Go Back"
		onclick="window.location.href='AssignFlights.jsp';" id="bttn" class="btn btn-info"  />
	
	<div class="card border-primary mb-3 "
		style="max-width: 18rem; margin-left: 40%; margin-top: 25px;">
		<div class="card-body text-primary" style="text-align: center;">
			<h5 class="card-title">UnAssigned Flights</h5>
			<p class="card-text">Click on the flight number to Assign Crew</p>
		</div>
	</div>
	<table class="table table-hover" style="border: 0;">
	<thead class="thead-dark">
		<tr>
			<th scope="col">Flight No</th>
			<th scope="col">Date</th>
		</tr>
		</thead>
		<c:forEach items="${requestScope.listFlight}" var="flight">
			<tr>
				<td scope="row"><a
					href="http://localhost:8080/AirportManagementSystem/Admin/ScheduleFlight?SNo=<c:out value="${flight.getScheduleNo()}"/>"><c:out
							value="${flight.getFlightNo()}" /></a></td>
				<td><c:out value="${flight.getDate()}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>