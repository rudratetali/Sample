<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crew-Leave</title>
</head>
<body>
<c:if test="${requestScope.reVisit eq null }">
<h1> Enter the Leave Details </h1>
<form action="crewLeave" method="get">
No of Days:
<input type="number" name="leaveCount" required/></br>
Date:
<input type="date" name="dateofLeave" required/></br>
<input type="submit" name="operation" value="Compensation"/> 
<input type="submit" name="operation" value="Leave"/>
</form>

<input type="button" value="Go Back" onclick="window.location.href='CrewLoginMenu.jsp';"/>
</c:if>




</body>
</html>