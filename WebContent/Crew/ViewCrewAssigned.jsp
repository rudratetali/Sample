<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assigned Flights</title>
<style>
#bttn {
	margin-left: 94%;
	border-radius: 0;
}
</style>
</head>
<body>
	<jsp:include page="../UI.jsp" />
	<c:if test="${requestScope.noAssigned == 'true' }">
		<script>
			alert("Error Occured Try again later...");
			window.location.replace("CrewLoginMenu.jsp");
		</script>
	</c:if>

	<c:if test="${requestScope.listFlight ne null }">
		<Button type="button" class="btn btn-info" id="bttn"
			onclick="window.location.href='CrewLoginMenu.jsp';">Go Back</Button>
		<div class="card border-primary mb-3"
			style="max-width: 18rem; margin-left: 40%; margin-top: 25px;>
<!-- border:0;opacity:0.85;"-->
			<div class="card-body text-primary"
				style="text-align: center; color: black;">
				<h5 class="card-title" style="color: black;">Flights on
					board...</h5>
				<p class="card-text">Click on Flight-No to view the Co-crew</p>
			</div>
		</div>
		<table class="table table-hover">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Flight No</th>
					<th scope="col">Flight Name</th>
					<th scope="col">Source</th>
					<th scope="col">Destination</th>
					<th scope="col">Date</th>
					<th scope="col">Time of depart</th>
					<th scope="col">Time of arrival</th>
				</tr>
			</thead>
			<c:forEach items="${requestScope.listFlight}" var="flight">
				<tr>
					<td scope="row"><a
						href="http://localhost:8080/AirportManagementSystem/Crew/CoCrew?SNo=<c:out value="${flight.getScheduleNo()}"/>"><c:out
								value="${flight.getFlightNo()}"></c:out></a></td>
					<td><c:out value="${flight.getFlightName()}"></c:out></td>
					<td><c:out value="${flight.getSource()}"></c:out></td>
					<td><c:out value="${flight.getDestination()}"></c:out></td>
					<td><c:out value="${flight.getDate()}"></c:out></td>
					<td><c:out value="${flight.getDepartureTime()}"></c:out></td>
					<td><c:out value="${flight.getArrivalTime()}"></c:out></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>