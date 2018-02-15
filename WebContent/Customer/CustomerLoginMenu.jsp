<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Login Menu</title>
</head>
<body>

	<h3>
		<c:if test="${requestScope.transaction == 'true' }">
			<script>
				alert("Booking Successful");
			</script>
		</c:if>
		<c:if test="${requestScope.transaction == 'false' }">
			<script>
				alert("Booking Failed ... Try again Later...");
			</script>
		</c:if>
		<c:if test="${requestScope.error == 'true' }">
			<script>
				alert("Error Occured .. Try again !!!");
			</script>
		</c:if>
		<c:if test="${requestScope.noFlight == 'true' }">
			<script>
				alert("No Flights Booked");
			</script>
		</c:if>
	</h3>

	<h1>

		<c:out value=" Hello ${sessionScope.userName}" />

	</h1>
	<h2>Have a great day</h2>
	<h3>MENU:</h3>

	<form action="CustomerLoginMenu" method="get">
		<input type="submit" name="operation" value="Book" /> <input
			type="submit" name="operation" value="View" /> <input
			type="submit" name="operation" value="Logout" />
	</form>
</body>
</html>