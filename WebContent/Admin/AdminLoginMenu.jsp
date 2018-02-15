<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../UI.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Login Menu</title>
<style>
#adminMenu {
	margin: 0;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-right: -50%;
	transform: translate(-50%, -50%);
	max-width: 50%;
	padding-left: 20%;
	border: 0;
}
/* .adminCard{ */

/* 	border: 0; */
/* 	background-color: #e42f0a00; */
/* } */
.adminBody {
	background-size: cover;
	background-repeat: no repeat;
}

#btt {
	border-radius: 0;
	color: white;
}

.wrapper {
	display: flex;
	align-items: stretch;
}

#sidebar {
	min-width: 200px;
	max-width: 200px;
	min-height: 100vh;
}

#sidebar.active {
	margin-left: -200px;
}

@media ( max-width : 768px) {
	#sidebar {
		margin-left: -250px;
	}
	#sidebar.active {
		margin-left: 0;
	}
}

p {
	font-family: 'Poppins', sans-serif;
	font-size: 1.1em;
	font-weight: 300;
	line-height: 1.7em;
	color: #999;
}

a, a:hover, a:focus {
	color: inherit;
	text-decoration: none;
	transition: all 0.3s;
}

#sidebar {
	/* don't forget to add all the previously mentioned styles here too */
	background: #17a2b8;
	color: #fff;
	transition: all 0.3s;
}

#sidebar .sidebar-header {
	padding: 20px;
	background: white;
}

#sidebar ul.components {
	padding: 20px 0;
	border-bottom: 1px solid #47748b;
}

#sidebar ul p {
	color: #fff;
	padding: 10px;
}

#sidebar ul li a {
	padding: 10px;
	font-size: 1.1em;
	display: block;
}

#sidebar ul li a:hover {
	color: #7386D5;
	background: #fff;
}

#sidebar ul li.active>a, a[aria-expanded="true"] {
	color: #fff;
	background: #6d7fcc;
}

ul ul a {
	font-size: 0.9em !important;
	padding-left: 30px !important;
	background: #6d7fcc;
}
</style>

<script>
	$(document).ready(function() {

		$('#sidebarCollapse').on('click', function() {
			$('#sidebar').toggleClass('active');
		});

	});

	$(document).ready(function() {

		$('#addFlight').on('click', function(e) {

			e.preventDefault()
			$('#content').empty();
			$('#content').load('DisplayFlight');
		})
	});
	$(document).ready(function() {
		$('#assignFlight').on('click', function(e) {

			e.preventDefault()
			$('#content').empty();
			$('#content').load('AssignFlights');
		})
	});
	$(document).ready(function() {
		$('#addCrew').on('click', function(e) {

			e.preventDefault()
			$('#content').empty();
			$('#content').load('AddCrew.jsp');
		})
	});
	$(document).ready(function() {
		$('#leave').on('click', function(e) {

			e.preventDefault()
			$('#content').empty();
			$('#content').load('LeaveRequest');
		})
	});
</script>
</head>
<body background="../maxresdefault.jpg" class="adminBody"
	style="overflow: hidden;">


	<c:if test="${requestScope.addedFlight == 'true'}">
		<script>
			alert("Flight added Sucessfully");
		</script>
	</c:if>
	<c:if test="${requestScope.noLeave == 'true'}">
		<script>
			alert("No Leave requests to assign");
		</script>
	</c:if>
	<c:if test="${requestScope.addedFlight == 'false'}">
		<script>
			alert("Error Occurred!!!! Try again later ");
		</script>
	</c:if>
	<c:if test="${requestScope.leaveGranted == 'true'}">
		<script>
			alert("Leave Granted Sucessfully");
		</script>

	</c:if>
	<c:if test="${requestScope.leaveGranted == 'false'}">
		<script>
			alert("Error Occurred!!!! Try again later");
		</script>

	</c:if>
	<c:if test="${requestScope.addedCrew == 'true'}">
		<script>
			alert("Crew added Sucessfully");
		</script>

	</c:if>
	<c:if test="${requestScope.addedCrew == 'false'}">
		<script>
			alert("Error Occurred!!!! Try again later");
		</script>
	</c:if>







	<nav class="navbar" style="background-color: #17a2b8;">
	<button type="button" id="sidebarCollapse"
		class="btn btn-info navbar-brand">
		<i class="glyphicon glyphicon-align-left"></i>	<img src="../icon24.png" style=" height:auto; width:auto;" /></button>
<!-- 		<a style="color: white;" class="navbar-brand">Airport Management System</a> -->
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











	<div class="wrapper">
		<div class="row">
			<div class="col-sm-3">

				<nav id="sidebar"> <!-- Sidebar Header -->
				<div class="sidebar-header">
					<h5 style="color: #17a2b8; text-align: center;">Admin Menu</h5>
				</div>
				<!-- Sidebar Links -->
				<ul class="nav flex-column" style="margin-top: 35%;">
					<li class="nav-item">
						<button type="submit" name="operation" class="btn btn-info"
							style="width: 100%;" value="Add Flight" id="addFlight">Add
							Flight</button>
					</li>
					<li class="nav-item">
						<td><button type="submit" name="operation"
								style="width: 100%;" class="btn btn-info" value="Add Crew"
								id="addCrew">Add Crew</button></td>
					</li>
					<li class="nav-item">
						<button type="submit" name="operation" class="btn btn-info"
							style="width: 100%;" value="Assign Flight" id="assignFlight">Assign
							Flight</button>
					</li>
					<li class="nav-item">
						<button type="submit" name="operation" class="btn btn-info"
							style="width: 100%;" value="Leave Request" id="leave">Leave
							Request</button>
					</li>
				</ul>
				</nav>
			</div>
		</div>
		<div class="col-sm-9" style="margin-left: 15%;">
			<div id="content" class="container" style="position: fixed;left: 15%;">
				<div class="card border-primary mb-3   " id="adminCard" 
					style="max-width: 18rem;left: 10%;; margin-left: 55%; margin-top: 22%; border: 0; opacity: 0.85;">
					<div class="card-header" style="border: 0;">
						<h3>Welcome Admin</h3>
					</div>
					<div class="card-body text-primary">
						<h5 class="card-title">
							<c:out value=" Hello ${sessionScope.adminName}" />
						</h5>
						<p class="card-text">Have a great day...</p>
					</div>
				</div>
			</div>
		</div>
	</div>






</body>
</html>