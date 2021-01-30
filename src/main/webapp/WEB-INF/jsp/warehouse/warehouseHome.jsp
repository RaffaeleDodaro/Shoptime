<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Shoptime Warehouse</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="../../../css/warehouse_home.css">
	<script src="../../../js/warehouse.js"></script>
	<link rel="icon" type="image/png" href="/images/logo.png" />
</head>
<body>

<jsp:include page="navbarWarehouse.jsp" />

<h2 class="headline">-- Lista Ordini --</h2>
<div>
<table id="tab-ordini" class="table container table-striped table-bordered table-dark">

	<thead>
		<tr>
			<th>Codice</th>
			<th>Data</th>
			<th>Stato</th>
			<th></th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach var="ordine" items="${listaOrdini}">
			<tr class="table">
				<td class="white">${ordine.codice}</td>
				<td class="white">${ordine.data}</td>
				<td class="white">${ordine.stato}</td> 
				
				<td><input type="button" class="btn btn-info btn-sm" onclick="getOrdine(this)" id="${ordine.codice}" name="${ordine.codice}" value="Mostra Dettagli"></td>
			</tr>		
		</c:forEach>
	</tbody>
</table>
</div>
<c:if test="${success != null}">
			<div class="alert alert-success alert-dismissible fade show fixed-bottom" role="alert">
				 <strong>ATTENZIONE:</strong> ${success}
				 <button type="button" class="close" data-dismiss="alert" aria-label="Close">
				    <span aria-hidden="true">&times;</span>
		  		</button>
			</div>
</c:if>

<c:if test="${errore != null}">
			<div class="alert alert-danger alert-dismissible fade show fixed-bottom" role="alert">
				 <strong>ATTENZIONE:</strong> ${errore}
				 <button type="button" class="close" data-dismiss="alert" aria-label="Close">
				    <span aria-hidden="true">&times;</span>
		  		</button>
			</div>
</c:if>


</body>
</html>