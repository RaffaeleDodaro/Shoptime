<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
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
<h2 class="headline">-- Aggiorna Scorte del Magazzino --</h2>
<div class="container-fluid">
	<div class="content">
		<div class="row">
			<div class="col-sm-4 ">
	      				
	    	</div>
	    	<c:if test = "${stage == 0}">
		    	<div class="col-sm-4">
		     		<h3 align="center">Inserisci il codice del prodotto</h3><br>
		     		<form method="POST" action="getProduct">
				        <div class="input-group mb-3 ">
							<input type="text" class="form-control" placeholder="Codice prodotto"  name="codiceProdotto" aria-label="codiceProdotto" aria-describedby="basic-addon2">
							<div class="input-group-append">
								<button class="btn  btn-success" type="submit">Conferma</button>
							</div>
						</div>
					</form>
		   		</div>
		   	</c:if>
		   	<c:if test = "${stage == 1}">
		    	<div class="col-sm-4">
		     		<h5 id="dot" align="center">La disponibilità in magazzino del prodotto inserito è: ${quantita}</h5><br>
		     		<h5 align="center"> Inserire la nuova disponibilità in magazzino</h5><br>
		     		<form method="POST" action="updateStorage">
				        <div class="input-group mb-3 ">
							<input type="text" class="form-control" placeholder="Nuova Quantità"  name="nuovaQuantita" aria-label="nuovaQuantita" aria-describedby="basic-addon2">
							<div class="input-group-append">
								<button class="btn  btn-success" type="submit">Conferma</button>
							</div>
						</div>
					</form>
		   		</div>
		   	</c:if>
	    	<div class="col-sm-4 ">
	      				
	    	</div>
		</div>
	</div>
</div>

<c:if test="${errore != null}">
			<div class="alert alert-danger alert-dismissible fade show fixed-bottom" role="alert">
				 <strong>ATTENZIONE:</strong> ${errore}
				 <button type="button" class="close" data-dismiss="alert" aria-label="Close">
				    <span aria-hidden="true">&times;</span>
		  		</button>
			</div>
</c:if>

<c:if test="${success != null}">
			<div class="alert alert-success alert-dismissible fade show fixed-bottom" role="alert">
				 <strong>ATTENZIONE:</strong> ${success}
				 <button type="button" class="close" data-dismiss="alert" aria-label="Close">
				    <span aria-hidden="true">&times;</span>
		  		</button>
			</div>
</c:if>

</body>
</html>