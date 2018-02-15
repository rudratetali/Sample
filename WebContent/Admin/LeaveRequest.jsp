<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LeaveRequests</title>
<style>
#bttn {
	margin-left: 94%;
	border-radius: 0;
}
</style>
</head>
<body>
	<jsp:include page="../UI.jsp" />
	
	<div class="card border-primary mb-3 mx-auto" style="max-width: 18rem; margin-left: 40%; margin-top: 25px; border: 0; opacity: 0.85;"">
		<div class="card-body text-primary" style= "text-align: center; border: 0;">
			<h5 style="color: black;" class="card-title">
			Leave Requests
			</h5>
			<p class="card-text">Click on Crew-Name to assign Leave</p>
		</div>
	</div>
	<c:if test="${requestScope.noLeave == 'true'}">
	<div class="card border-primary mb-3 mx-auto" style="max-width: 18rem; margin-left: 40%; margin-top: 25px; border: 0; opacity: 0.85;"">
		<div class="card-body text-primary" style= "text-align: center; border: 0;">
			<h5 style="color: black;" class="card-title">
			Sorry :(
			</h5>
			<p class="card-text">No Leave Requests to Assign</p>
		</div>
	</div>	
	</c:if>	
	
	<c:if test="${requestScope.noLeave eq null}">
	<table class="table table-hover" style="border: 0; background-color: #ffffff;
	opacity: 0.75;" >
	<thead class="thead-dark">
		<tr>
			<th scope="col">Crew Name</th>
			<th scope="col">Designation</th>
			<th scope="col">date</th>
			<th scope="col">DayCount</th>
		</tr>
		</thead>
		<c:forEach items="${requestScope.listCrew}" var="crew">
			<tr>
				<th scope="row"><a style="color: black;"
					href="http://localhost:8080/AirportManagementSystem/Admin/AssignLeave?LNo=<c:out value="${crew.getCrewId()}"/>"><c:out
							value="${crew.getName()}" /></a></th>
				<th><c:out value="${crew.getDesignation()}"></c:out></th>
				<th><c:out value="${crew.getDate()}"></c:out></th>
				<th><c:out value="${crew.getLeaveCount()}"></c:out></th>
			</tr>
		</c:forEach>
	</table>
	</c:if>
</body>
</html>