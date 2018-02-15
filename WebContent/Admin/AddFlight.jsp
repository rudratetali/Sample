<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Flight</title>
<style type="text/css">
#bttn {
	margin-left: 94%;
	border-radius: 0;
}

#adminMenu {
	margin: 0;
	position: absolute;
	top: 55%;
	left: 50%;
	margin-right: -50%;
	transform: translate(-50%, -50%);
	max-width: 50%;
	padding-left: 10%;
	padding-right: 5%;
	padding-top: 5%;
	padding-bottom: 5%;
	border: 1px solid blue;
}

#datatd {
	padding-left: 22px;
}
</style>
</head>
<body>
	<jsp:include page="../UI.jsp" />
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
	<input type="button" value="Go Back" class="btn btn-info" id="bttn"
		onclick="window.location.href='AdminLoginMenu.jsp';" />
	<div class="card border-primary mb-3 mx-auto"
		style="max-width: 18rem; margin-left: 40%; margin-top: 25px;">
		<div class="card-body text-primary" style="text-align: center;">
			<h5 class="card-title">Flight Schedule</h5>
			<p class="card-text">Enter the details</p>
		</div>
	</div>
	<form action="AddFlight" method="post" class="form-control"
		id="adminMenu">
		<table>
			<tr>
				<td scope="row">Enter Date</td>
				<td id="datatd"><input style="border-color: darkgray;"
					type="date" name="date" required><br></td>
			</tr>
			<tr>
				<td scope="row">Enter Departure Time</td>
				<td id="datatd"><input type="time"
					style="border-color: darkgray;" name="depttime" required></td>
			</tr>
			<tr>
				<td scope="row">Enter Arrival Time</td>
				<td id="datatd"><input style="border-color: darkgray;"
					type="time" name="arrtime" required></td>
			</tr>
			<tr>
			<tr>
				<td scope="row">Enter Economy Fare</td>
				<td id="datatd"><input style="border-color: darkgray;" input
					type="number" name="Efare" required></td>
			</tr>
			<tr>
				<td scope="row">Enter Business Fare:</td>
				<td id="datatd"><input style="border-color: darkgray;"
					type="number" name="Bfare" required></td>
			</tr>
			<tr>
				<td></td>
				<td id="datatd"><button type="submit" name="Sumbit" value="Add the Flight"
						class="btn btn-info">Add Flight</button></td>
			</tr>
		</table>
	</form>
</body>
</html>