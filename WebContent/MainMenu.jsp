<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html language="en">
<head>
<style>
.card-body {width =100%;
	
}

#airportHeader {
	padding: .75rem 1.25rem;
	margin-bottom: 0;
	background-color: rgba(0, 0, 0, 0);
	border-bottom: 0;
}



#airportFooter {
	padding: .75rem 1.25rem;
	background-color: rgba(0, 0, 0, .0);
	border-top: 0;
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
#footer{
 position:fixed;
 bottom:0;
 left:45%;
 transform: translate(0, -50%);
 color: white;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Menu</title>
</head>
<body background="Airport.jpg">
	<jsp:include page="UI.jsp" />
	<div class=" text-center card" id="airportCard">
		<div class="card-header" id="airportHeader">
			<h1>AIRPORT SYSTEM</h1>
		</div>

		<div class="card-body ">
			<form action="MainMenu" method="get">
				<input type="submit" class="btn btn-outline-dark" name="user" value="Admin" /> <input
					type="submit" class="btn btn-outline-dark" name="user" value="Crew" /> <input type="submit"
					name="user" class="btn btn-outline-dark" value="Customer" />

			</form>
		</div>

	</div>
	
</body>

</html>