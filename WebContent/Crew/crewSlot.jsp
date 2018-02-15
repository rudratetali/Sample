<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Slot Request</title>
</head>
<body>
<form action="CrewSlot" method="get">
Destination:
<input type="text" name="destination" value="<c:out value="${(requestScope.destination)}"></c:out>"  required /></br>
Date:
<input type="date" name="dateofSlot" value="<c:out value="${(requestScope.dateofSlot)}"></c:out>" required/></br>

<input type="submit" name="showslot" value="ShowAvailable"/> 
</form>

<input type="button" value="Go Back" onclick="window.location.href='CrewLoginMenu.jsp';"/>
<br>
<br>
 <c:if test="${requestScope.noFlight == 'true' }">
<h3>No Available Flights for this Schedule</h3>
</c:if> 
<c:if test="${requestScope.listFlight ne null }">
<form action="CrewSlot" method="post">
		Select the Flight:
		<select name="ScheduleNo" >
		<c:forEach items="${requestScope.listFlight}" var="flight">
		<option  value=<c:out value="${flight.getScheduleNo()}"></c:out> > From <c:out value="${flight.getSource()}"></c:out> at <c:out value="${flight.getDepartureTime()}"></c:out></option>
		</c:forEach>	
		</select>
  <input type="submit" name="slot" value="BookSlot"/>
  </form>
</c:if>


</body>
</html>