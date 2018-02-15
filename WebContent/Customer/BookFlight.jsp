<%@page import="global.coda.ams.beans.FlightBook"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Flight</title>
</head>
<body>
<h1> Flight Details</h1>
<%
FlightBook flightObject= (FlightBook) request.getAttribute("flightObject");
if (flightObject.getTicketClass().equals("E")) {
	
	out.println("<html><p> No: " + flightObject.getFlightNo() + " &nbsp   Name: " + flightObject.getFlightName()+"<br>");
	out.println("<html> From: " + flightObject.getSource() + "&nbsp  To: " + flightObject.getDestination()+"<br>");
	out.println("<html> Date: " + flightObject.getDate() + " &nbsp time: " + flightObject.getDepartureTime()+"<br>");
	out.println("<html> Fare: " + flightObject.getEconomyFare() + "/-"+"<br></p>");
} else if (flightObject.getTicketClass().equals("B")) {
	
	out.println("<html><p> No: " + flightObject.getFlightNo() + "&nbsp  Name: " + flightObject.getFlightName()+"<br>");
	out.println(" From: " + flightObject.getSource() + " &nbsp To: " + flightObject.getDestination()+"<br>");
	out.println(" Date: " + flightObject.getDate() + " &nbsp Time: " + flightObject.getDepartureTime()+"<br>");
	out.println(" Fare: " + flightObject.getBusinessFare() + "/-"+"<br></p>");
}


out.println("<p> The Amount to be paid is: "+flightObject.getAmount()+"</p></html>");
out.println("<form action="+"\"transaction\""+"method="+"'get'"+">");
out.println("<input type="+"\"submit\""+" name="+"\"Book\""+" value="+"\"Book\""+"/>");
out.println("</form>");
%>

<input type="button" value="Go Back" onclick="window.location.href='BookSearch.jsp';"/>


</body>
</html>