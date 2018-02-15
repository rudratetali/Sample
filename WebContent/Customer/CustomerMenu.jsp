<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MainMenu</title>
</head>
<body>
<h1>Sign-in / Sign-up</h1>
<form action="CustomerMenu" method="get">
	    <input type="submit" name="operation" value="Register"   />
	     <input type="submit" name="operation" value="Login"   />
	     
</form>
<input type="button" value="Go Back" onclick="window.location.href='../MainMenu.jsp';"/>
</body>
</html>