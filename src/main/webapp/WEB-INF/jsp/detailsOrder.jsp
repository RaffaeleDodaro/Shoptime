<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<link
		href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
		rel="stylesheet" id="bootstrap-css">
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<jsp:include page="headMarketPlace.jsp" />
	<script src="/js/userPage.js"></script>
	<link rel="stylesheet" href="/css/userPage.css" type="text/css" />
	<meta charset="ISO-8859-1">
</head>
<body>

	<jsp:include page="navbarMarketPlace.jsp" />
	${ordine.codice}<br></br>
	${ordine.data}<br></br>
	${ordine.stato}<br></br>
	${ordine.prezzo}<br></br>
	${indirizzo.cap}<br></br>
	${indirizzo.via}<br></br>
	${indirizzo.regione}<br></br>
	${indirizzo.citta}<br></br>
	${carta}<br></br>
	<jsp:include page="footerMarketPlace.jsp" />
</body>
</html>