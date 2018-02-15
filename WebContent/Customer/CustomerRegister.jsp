<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Register</title>
</head>
<body>
<h1>Registration</h1>
<c:if test="${requestScope.error == 'true' }">
<h2>Error Occurred!! Try again </h2>
</c:if>
<form action="CustomerRegister" method="post">
  Name:<br>
  <input type="text" name="name" required><br>
  Gender<br>
  
  <input type="radio" name="gender" value="male" checked> Male<br>
  <input type="radio" name="gender" value="female"> Female<br>
  <input type="radio" name="gender" value="other"> Other<br>

  UserID:<br>
  <input type="number" name="username" required><br>
  Password:<br>
  <input type="password" name="password" required><br>
  contact:<br>
   <input type="text" name="contact" required><br><br>
   
   <input type="submit" name="Sumbit" value="sumbit"   />
</form>

<input type="button" value="Go Back" onclick="window.location.href='../MainMenu.jsp';"/>
  
  

</body>
</html>