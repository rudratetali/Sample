 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crew-Profile</title>
</head>
<body>
<h1>
<c:if test="${requestScope.crew  eq null }">
<c:out value="Error!!! Please try again Later " escapeXml="true"></c:out>
</c:if>
</h1>
<c:if test="${requestScope.crew  ne null }">
<h2>My Profile</h2>
<c:set var = "crew" scope = "session" value = "${requestScope.crew}"/>
Id: <c:out value="${crew.getCrewId() }" ></c:out><br>
Name: <c:out value="${crew.getName() }" ></c:out><br>
Designation: <c:out value="${crew.getDesignation() }" ></c:out><br>
Contact: <c:out value="${crew.getContact() }" ></c:out><br>
Languages: <c:out value="${crew.getLanguages() }" ></c:out><br>
LeaveCount: 
<c:choose>
         
         <c:when test = "${crew.getLeaveCount() >18 }">
             <c:out value="${crew.getLeaveCount()-18 }" ></c:out> (LOP) <br>
         </c:when>
         
                  
         <c:otherwise>
           <c:out value="${crew.getLeaveCount() }" ></c:out> (PTO) <br> 
         </c:otherwise>
</c:choose>
</c:if>
<input type="button" value="Go Back" onclick="window.location.href='CrewLoginMenu.jsp';"/>
</body>
</html>