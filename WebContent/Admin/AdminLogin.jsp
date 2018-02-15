<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AdminLogin</title>
<style type="text/css">
.card-body {
width =100%;
	
}
.adminBody {
	background-size: cover;
	background-repeat: no repeat;
}

#airportHeader {
	padding: .75rem 1.25rem;
	margin-bottom: 0;
	background-color: rgba(0, 0, 0, 0);
	border-bottom: 0;
}

#airportCard {
	margin: 0;
	position: absolute;
	top: 35%;
	left: 20%;
	margin-right: -50%;
	transform: translate(-50%, -50%);
	height: 20%;
	width: 50%;
	opacity: 0.75;
	border: 0;
	background-color: #e42f0a00;
}
#adminForm
{
margin-left:150px;
background-color:#fff0;
border:0;
}
#bttn
{
line-height: 1;
}
</style>
</head>
<body background="../Flight.png" class="adminBody">
	<jsp:include page="../UI.jsp" />
	<h3>
		<c:if test="${requestScope.adminValid ne null }">
			<script>alert("Invalied Data")</script>
		</c:if>
	</h3>
	<div class=" text-center card" id="airportCard">

		<div class="card-header" id="airportHeader">
			<h1>ADMIN LOGIN</h1>
		</div>
		<div class="card-body">
			<form action="AdminLogin" method="post" class="form-control" id="adminForm">
				<table border=0>
					<tr>
						<td>Username</td>
						<td><input type="number" name="name" required></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="password" required></td>
					</tr>
					<tr>
						<td>
							
						<td >
							<button type="submit" class="btn btn-outline-secondary" name="Submit" id="bttn">Login</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>