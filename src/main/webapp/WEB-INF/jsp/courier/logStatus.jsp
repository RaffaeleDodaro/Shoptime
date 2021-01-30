<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Aggiorna stato</title>
	<link rel="icon" type="images/png" href ="../../images/logo.png">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="../../js/courier.js"></script>
</head>
<body class="bg-dark">

<jsp:include page="courierNavbar.jsp" />

<c:if test = "${isConsegnato == 0}">
	<form method="POST" action="addLog">
</c:if>
<table class="table table-dark table-hover table-striped table-bordered">
	<thead>
		<tr>
			<th>Codice</th>
			<th>Data</th>
			<th>Posizione</th>
			<th>Stato</th>
			<th>Descrizione</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="log" items="${logs}">
			<tr>
				<td>${log.codice}</td>
				<td>${log.data}</td>
				<td>${log.posizione}</td>
				<td>${log.stato}</td>
				<td>${log.descrizione}</td>
			</tr>
		</c:forEach>
		<c:if test = "${isConsegnato == 0}">
		<tr>

			<td></td>
			<td>${now}</td>
			<td><input type="text" id="form-control" class="form-control" name="posizione" placeholder="posizione" required></td>
			<td><input type="text" id="form-control" class="form-control" name="stato" placeholder="stato" required></td>
			<td><input type="text" id="form-control" class="form-control" name="descrizione" placeholder="descrizione"></td>
		</tr>
		</c:if>
	</tbody>
</table>
<c:if test = "${isConsegnato == 0}">
	<input type="submit" value="Aggiorna" class="btn btn-primary"> 
	</form>
</c:if>

</body>