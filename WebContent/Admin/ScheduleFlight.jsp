<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Schedule Flights</title>
<style>
#bttn {
	margin-left: 94%;
	border-radius: 0;
}
</style>
</head>
<body>
	<jsp:include page="../UI.jsp" />
	<c:if test="${requestScope.nocrew == 'true' }">
		<script>
			alert("No crew are free to assign.. Please try again later")
			window.location.replace("AdminLoginMenu.jsp");
		</script>
	</c:if>
	<nav class="navbar" style="background-color: #17a2b8;">
	<button type="button" id="sidebarCollapse"
		class="btn btn-info navbar-brand">
		<i class="glyphicon glyphicon-align-left"></i>Airport Management
		System
	</button>
	<ul class="nav justify-content-end ">
		<li class="nav-item"><a style="color: white;"
			class="nav-link active" href="AdminLoginMenu.jsp">Home</a></li>
		<li class="nav-item"><a class="nav-link disabled" href="#"><img
				src="../mss-mssm-icon.png" style="height: 30px;" /></a></li>
		<li class="nav-item"><a style="color: white;"
			class="nav-link disabled" href="#"><c:out
					value="${sessionScope.adminName}" /></a></li>
		<li class="nav-item">
			<form action="AdminLoginMenu" method="get">
				<button type="submit" name="operation" class="btn btn-info" id="btt"
					value="Logout">Logout</button>
			</form>
		</li>

	</ul>

	</nav>
	<input type="button" value="Go Back"
		onclick="window.location.href='AdminLoginMenu.jsp';" id="bttn"
		class="btn btn-info" />
	<div class="card border-primary mb-3 mx-auto "
		style="max-width: 18rem; margin-left: 40%; margin-top: 25px;">
		<div class="card-body text-primary" style="text-align: center;">
			<h5 class="card-title">Crew Available</h5>
			<p class="card-text">Select the Crew to Assign</p>
		</div>
	</div>

	<c:if test="${requestScope.listcrew ne null }">

		<form action="ScheduleFlight" method="post">
			<table class="table table-hover" style="border: 0;">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Select</th>
						<th scope="col">Name</th>
						<th scope="col">Designation</th>
						<th scope="col">Status</th>
					</tr>
				</thead>
				<c:forEach items="${requestScope.listcrew}" var="crew">
					<tr>
						<td><input type="checkbox" name="crewId"
							value=<c:out value="${crew.getCrewId()}"/> /></td>
						<td><c:out value="${crew.getName()}"></c:out></td>
						<td><c:out value="${crew.getDesignation()}"></c:out></td>
						<td><c:out value="${crew.getStatus()}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
			<input type="submit" name="Assign" value="Assign" id="bttn"
				class="btn btn-info" />
		</form>
	</c:if>
</body>
</html>