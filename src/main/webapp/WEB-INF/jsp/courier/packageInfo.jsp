<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Informazioni pacco</title>
	<link rel="icon" type="images/png" href ="../../images/logo.png">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="../../js/courier.js"></script>
	<style>
		.top-buffer {
			margin-top: 40px;
			}
		h4 {
			font-weight:normal;
		}
	</style>
</head>
<body class="bg-dark">

<jsp:include page="courierNavbar.jsp" />

<div class="container">
	<div class="row top-buffer">
		<div class="col-6 col-sm text-white">
		<h4>
			<strong>Destinatario:</strong> ${pacco.indirizzo.destinatario}<br>
			<strong>telefonico:</strong> ${pacco.indirizzo.numTelefono}<br>
			<strong>CAP:</strong> ${pacco.indirizzo.cap}<br>
			<strong>Indirizzo:</strong> ${pacco.indirizzo.regione}, ${pacco.indirizzo.citta}<br>
			&nbsp; &nbsp; ${pacco.indirizzo.via}
		</h4>
		</div>
		<div class="col-6 col-sm">
			<iframe
				width="600"
				height="450"
				frameborder="0" style="border:0"
				src="https://www.google.com/maps/embed/v1/place?key=AIzaSyDpi9LWAiIERXWpDWfBsD7apOmttdMjSjg&q=${pacco.indirizzo.via}+${pacco.indirizzo.citta}" allowfullscreen>
			</iframe>
		</div>
	</div>
	<div class="row col-12 col-sm">
		<form method="GET" action="status"><input type="submit" class ="btn btn-primary" value="Aggiorna stato"></form>
	</div>
</div>
</body>