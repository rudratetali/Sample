<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crew Login Menu</title>
</head>
<body>
	<h1>

		<c:out value=" Hello ${sessionScope.crewName}" />

	</h1>
	<h2>Have a great day</h2>
	<h3>MENU:</h3>
	<c:if test="${requestScope.leaveRequest == 'true' }">
		<script>
		alert("Leave request is recorded Sucessfully");
		</script>
	</c:if>
	<c:if test="${requestScope.leaveRequest == 'false' }">
		<script>
		alert("Error Occured!!!  Try again later");
		</script>
	</c:if>
	<c:if test="${requestScope.compRequest == 'true'}">
		<script>
		alert("Compensate request is recorded Sucessfully");
		</script>
	</c:if>
	<c:if test="${requestScope.compRequest == 'false'}">
		<script>
		alert("Error Occured!!!  Try again later");
		</script>
	</c:if>
	<c:if test="${requestScope.slotRequest == 'true' }">
		<script>
		alert("Slot request is recorded Sucessfully");
		</script>
	</c:if>
	<c:if test="${requestScope.slotRequest == 'false' }">
		<script>
		alert("Error Occured!!!  Try again later");
		</script>
	</c:if>
	<form action="CrewLoginMenu" method="post">
		<input type="submit" name="operation" value="View Assigned" /><br>
		<input type="submit" name="operation" value="Leave Request" /><br>
		<input type="submit" name="operation" value="Slot Request" /><br>
		<input type="submit" name="operation" value="View Profile" /><br>
		<input type="submit" name="operation" value="Logout" /><br>
	</form>
</body>
</html>