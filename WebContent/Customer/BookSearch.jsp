<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
</head>
<body>
<c:if test="${requestScope.search == 'false'}">
<script type="text/javascript">
alert("No Flights available for the Search Constraints")
</script>
</c:if>
<c:if test="${requestScope.search != 'true'}">
<h1>Search</h1>
<form action="BookSearch" method="post">
   From:
  <select name="from" >
  <c:forEach items="${requestScope.listFlight}" var="flight">
  <option   value=<c:out value="${flight.getSource()}"/>><c:out value="${flight.getSource()}"/></option>
  </c:forEach>
  </select> <br>
  <br>
   To:
  <select name="to" >
  <c:forEach items="${requestScope.listFlight}" var="flight">
  <option   value=<c:out value="${flight.getDestination()}"/>><c:out value="${flight.getDestination()}"/></option>
  </c:forEach>
 </select> <br>
 <br>
 Date:
  <input type="date" name="dateofJourney" > <br>
  <br>
  Seats: 
  <select name="seatno" >
  <option type="number" value=1 >1</option>
  <option type="number"  value=2 >2</option>
  <option type="number" value=3 >3</option>
  <option type="number" value=4  >4</option>
</select><br>
<br>

 Class: 
  <select name="class" required >
  <option type="text"  value="B">Business</option>
  <option type="text"  value="E">Economy</option>
   </select><br>
   <br>
  
  Modify Search: 
  <select name="modify">
  <option type="text" selected='selected'  value="EarlyDeparture" >Early Departure</option>
  <option type="text"  value="LateDeparture" >Late Departure</option>
  <option type="text"  value="LowPrice" >Low Price</option>
  <option type="text"  value="HighPrice"  >High Price</option>
</select><br>
  <br>
    <input type="submit" name="sumbit" value="Search"   />
</form>
</c:if>

<input type="button" value="Go Back" onclick="window.location.href='CustomerLoginMenu.jsp';"/>
</body>
</html>