<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Courier Home</title>
	<link rel="icon" type="images/png" href ="../../images/logo.png">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="../../js/courier.js"></script>
</head>
<body class="bg-dark">

<jsp:include page="courierNavbar.jsp" />

<table class="table table-dark table-hover table-striped table-bordered">
	<thead>
		<tr>
			<th>Codice</th>
			<th>Destinatario</th>
			<th>Indirizzo</th>
			<th>Stato</th>
			<th></th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach var="pacco" items="${listaPacchi}">
			<tr >
				
				<td>${pacco.trackingCode}</td>
				<td>${pacco.indirizzo.destinatario}</td>
				<td>${pacco.indirizzo.citta}, ${pacco.indirizzo.via}</td>
				<td><c:forEach items="${pacco.logs}" var="log" varStatus="loop">
						<c:if test="${loop.last}">
							${log.stato}
						</c:if>
					</c:forEach>
				</td>
				<td><input type="button" class ="btn btn-primary" onClick="getInfo(this)" id="${pacco.codice}" value="Apri"></td>
			</tr>			
		</c:forEach>
	</tbody>
</table>
</body>