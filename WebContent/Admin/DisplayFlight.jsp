<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Flight</title>
<style type="text/css">
#bttn {
	margin-left: 94%;
	border-radius: 0;
}
</style>


</head>
<body>
	<jsp:include page="../UI.jsp" />
<script type="text/javascript">
$(document).ready(function(){
	$('[name="myBtn"]').click(function(){
		
		document.getElementById("FNo").value = $(this).val();
		   document.dispflight.submit();
	})
	});

</script>
	<c:if test="${requestScope.noAssigned == 'true' }">
		<script>
		
			alert("Error Occured Try again later...");
			window.location.replace("AdminLoginMenu.jsp");
		</script>
	</c:if>



	<c:if test="${requestScope.flightList ne null }">

		<div class="card border-primary mb-3  mx-auto"
			style="max-width: 18rem; margin-left: 40%; margin-top: 25px; border: 0; opacity: 0.85;">
			<div class="card-body text-primary"
				style="text-align: center; color: black;">
				<h5 class="card-title" style="color: black;">Flights Available
				</h5>
				<p class="card-text">Click on Flight-No to add the Flight</p>
			</div>
		</div>
		<table class="table table-hover"
			style="border: 0; background-color: #ffffff; opacity: 0.75;">

			<thead class="thead-dark">
				<tr>
					<th scope="col">Flight Number</th>
					<th scope="col">Flight Name</th>
					<th scope="col">Source</th>
					<th scope="col">Destination</th>
				</tr>
			</thead>
			<c:forEach items="${requestScope.flightList}" var="flight">
				<tr>
<!-- 					<th scope="row"><input type="button" -->
<%-- 						onclick='window.location.href = "AddFlight?FNo=<c:out value="${flight.getFlightId()}"/>" ' --%>
<!-- 						class="btn btn-light" -->
<%-- 						value=<c:out value="${flight.getFlightNo()}"></c:out> /></th> --%>


					<th scope="row"><button type="sumbit" onclick=" display()" id="myBtn" name="myBtn"
							value=<c:out value="${flight.getFlightId()}"/>>
							<c:out value="${flight.getFlightNo()}"></c:out>
						</button></th>
					<th><c:out value="${flight.getFlightName()}"></c:out></th>
					<th><c:out value="${flight.getSource()}"></c:out></th>
					<th><c:out value="${flight.getDestination()}"></c:out></th>
				</tr>
			</c:forEach>
		</table>
	</c:if>
<form name="dispflight" action="DisplayFlight" method="post">
  <input type="hidden" name="FNo" id="FNo" />
  <input style="display:none;" type="submit" name="rudra" value="sumbit" />
</form>
</body>
</html>