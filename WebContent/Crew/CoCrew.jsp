<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Co-Crew Display</title>
<style>
#bttn {
	margin-left: 94%;
	border-radius: 0;
}
</style>
</head>
<body>

	<jsp:include page="../UI.jsp" />
	<c:if test="${requestScope.listCrew  eq null }">
		<script>
			alert("Error Occured Try again later...");
			window.location.replace("CrewLoginMenu.jsp");
		</script>
	</c:if>

	<c:if test="${requestScope.listCrew  ne null }">
		<Button type="button" class="btn btn-info" id="bttn"
			onclick="window.location.href='CrewLoginMenu.jsp';">Go Back</Button>
		<div class="card border-primary mb-3"
			style="max-width: 18rem; margin-left: 40%; margin-top: 25px;>
<!-- border:0;opacity:0.85;"-->
			<div class="card-body text-primary"
				style="text-align: center; color: black;">
				<h5 class="card-title" style="color: black;">Crew on board...</h5>
				<p class="card-text">Happy and Safe Journey</p>
			</div>
		</div>
		<table class="table table-hover">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Crew-Id</th>
					<th scope="col">Name</th>
					<th scope="col">Designation</th>
					<th scope="col">Contact</th>
				</tr>
			</thead>
			<c:forEach items="${requestScope.listCrew}" var="crew">
				<tr>

					<th scope="row"><c:out value="${crew.getCrewId()}"></c:out></th>
					<th><c:out value="${crew.getName()}"></c:out></th>
					<th><c:out value="${crew.getDesignation()}"></c:out></th>
					<th><c:out value="${crew.getContact()}"></c:out></th>

				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>