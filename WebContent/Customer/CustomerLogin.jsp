<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CustomerLogin</title>
</head>
<body>
<h1>Login</h1>

<h3>
<c:if test="${requestScope.userValied ne null }">
<c:out value="Invalied credentials " escapeXml="true"></c:out>
</c:if>
</h3>




<form action="CustomerLogin" method="post">
   Name:<br>
  <input type="number" name="name" required><br>
  Password<br>
  <input type="password" name="password" required><br>
  
  <input type="submit" name="operation" value="Login"   /><br>
  </form>
  New User? Click to Register  <input type="button" value="Register" onclick="window.location.href='CustomerRegister.jsp';"/>
  <br>
  
  <input type="button" value="Go Back" onclick="window.location.href='../MainMenu.jsp';"/>
</body>
</html>