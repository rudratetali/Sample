<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style>
.adminBody {
	background-size: cover;
	background-repeat: no repeat;
}
#bttn {
	margin-left: 94%;
	border-radius: 0;
}
</style>
<jsp:include page="../UI.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Flights</title>
<script>
$(document).ready(function(){
	$('#nav-tab a').on('click', function (e) {
			 e.preventDefault()
		  $(this).tab('show')
		})
})


	
</script>
</head>
<body>

	
	<c:if test="${requestScope.noAssigned == 'true'}">
		<script>
			alert("No Flight is Assigned");
		</script>
		<c:set var="noAssigned" value="false" scope="request" />
	</c:if>
	<c:if test="${requestScope.flightAssigned == 'true'}">
		<script>
			alert(" Flight is Assigned sucessfully with the crew ");
		</script>

	</c:if>
	<c:if test="${requestScope.flightAssigned == 'false'}">
		<script>
			alert(" Error occurred!!! Try again Later");
		</script>

	</c:if>
	<c:if test="${requestScope.noScheduled == 'true'}">
		<script>
			alert("No Flight is Scheduled yet...");
		</script>
		<c:set var="noScheduled" value="false" scope="request" />
	</c:if>
	
		
		<div class="card border-primary mb-3  mx-auto" style="max-width: 18rem; margin-left: 40%; margin-top: 25px; border: 0; opacity: 0.85;">
		<div class="card-body text-primary" style= "text-align: center; border: 0;">
			<h5 class="card-title" style="color: black;">Assign Flights</h5>
			<p class="card-text">Click on the flight number to Assign Crew</p>
		</div>
	</div>
	<nav>
  <div class="nav nav-tabs" id="nav-tab" role="tablist">
    <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Assigned</a>
    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">Unassigned</a>
    
  </div>
</nav>
<div class="tab-content" id="nav-tabContent">
  <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
  <c:if test="${requestScope.listFlight eq null}">
  <div class="card border-primary mb-3  mx-auto " style="max-width: 18rem; margin-left: 40%; margin-top: 25px;">
		<div class="card-body text-primary" style= "text-align: center;">
			<h5 class="card-title">Assigned Flights</h5>
			<p class="card-text">Sorry .. Flights are not Assigned yet</p>
		</div>
	</div>
  </c:if>
  <c:if test="${requestScope.listFlight ne null}">
  <table class="table table-hover" style="border: 0; background-color: #ffffff;
	opacity: 0.75;">
		<thead class="thead-dark">
			<tr>
				<th scope="col">Flight No</th>
				<th scope="col">Date</th>
				<th scope="col">Pilot Count</th>
				<th scope="col">Air Count</th>
				<th scope="col">Ground Count</th>
			</tr>
		</thead>
		<c:forEach items="${requestScope.listFlight}" var="flight">
			<tr>
				<th scope="row"><a style="color: black;"
					href="http://localhost:8080/AirportManagementSystem/Admin/ScheduleFlight?SNo=<c:out value="${flight.getScheduleNo()}"/>"><c:out
							value="${flight.getFlightNo()}" /></a></th>
				<th><c:out value="${flight.getDate()}"></c:out></th>
				<th><c:out value="${flight.getPilotCount()}"></c:out></th>
				<th><c:out value="${flight.getAirCount()}"></c:out></th>
				<th><c:out value="${flight.getGroundCount()}"></c:out></th>
			</tr>
		</c:forEach>
	</table>
  </c:if>  
  </div>
  <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
  <c:if test="${requestScope.flightList eq null}">
  <div class="card border-primary mb-3  mx-auto" style="max-width: 18rem; margin-left: 40%; margin-top: 25px;">
		<div class="card-body text-primary  mx-auto" style= "text-align: center;">
			<h5 class="card-title">Assigned Flights</h5>
			<p class="card-text">Sorry .. Flights are not Assigned yet</p>
		</div>
	</div>
  </c:if>
  <c:if test="${requestScope.flightList ne null}">
  <table class="table table-hover" style="border: 0; background-color: #ffffff;
	opacity: 0.75;">
	<thead class="thead-dark">
		<tr>
			<th scope="col">Flight No</th>
			<th scope="col">Date</th>
		</tr>
		</thead>
		<c:forEach items="${requestScope.flightList}" var="flight">
			<tr>
				<th scope="row"><a style="color: black;"
					href="http://localhost:8080/AirportManagementSystem/Admin/ScheduleFlight?SNo=<c:out value="${flight.getScheduleNo()}"/>"><c:out
							value="${flight.getFlightNo()}" /></a></th>
				<th><c:out value="${flight.getDate()}"></c:out></th>
			</tr>
		</c:forEach>
	</table>
  </c:if>
  </div> 
</div>
</body>
</html>