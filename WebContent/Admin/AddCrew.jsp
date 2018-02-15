<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Crew</title>
<style type="text/css">
#adminMenu {
	margin: 0;
	position: absolute;
	top: 60%;
	left: 50%;
	margin-right: -50%;
	transform: translate(-50%, -50%);
	max-width: 60%;
	margin-top:25%;
	padding: 5%;
	border: 0px solid blue;
	background-color: #ffffff;
	opacity: 0.75;
}

#bttn {
	margin-left: 94%;
	border-radius: 0;
}
</style>
</head>
<body>
	<jsp:include page="../UI.jsp" />
	<div class="card border-primary mb-3 mx-auto "
		style="max-width: 18rem; margin-left: 30%; margin-top: 42px; border: 0; opacity: 0.85;">
		<div class="card-body text-primary" style="border: 0; text-align: center;">
			<h5  class="card-title" style="color: black;">Crew Registration</h5>
			<p class="card-text">Enter the details</p>
		</div>
	</div>
	<form action="AddCrew" method="post" class="form-control"
		id="adminMenu">
		<table>
			<tr>
				<td scope="row"><b>Enter Crew Name</b></td>
				<td><input style="border-color: darkgray;" type="text"
					name="crewname" required></td>
			</tr>
			<tr>
				<td scope="row"><b>Enter Designation</b></td>
				<td><select style="border-color: darkgray;" name="designation">
						<option value="Pilot">Pilot</option>
						<option value="Air">AirCrew</option>
						<option value="Ground">Ground</option>
				</select></td>
			</tr>

			<tr>
				<td scope="row"><b>Enter Contact</b></td>
				<td><input style="border-color: darkgray;" type="text"
					name="contact" required></td>
			</tr>
			<tr>
			<tr>
				<td scope="row"><b>Enter Password</b></td>
				<td><input style="border-color: darkgray;" type="password"
					name="password" required></td>
			</tr>
			<tr>
				<td scope="row"><b>Enter the Languages [separated by ,]</b></td>
				<td><input style="border-color: darkgray;" type="text"
					name="languages" required></td>
			</tr>
			<tr>
				<td></td>
				<td><button type="submit" name="Sumbit" value="Add the Crew"
						class="btn btn-info">Add Crew</button></td>
			</tr>
		</table>
	</form>

</body>
</html>