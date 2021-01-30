<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style> .bottom-line{border: cyan solid 3px; margin-top:3px}</style>
</head>
<body>
	<div class="jumbotron jumbotron-fluid">
	  <div class="container">
	    <h1 class="display-4 white" align="center">Shoptime Warehouse</h1>
	  </div>
	</div>

	<nav class="navbar sticky-top navbar-expand-sm bg-dark navbar-dark bottom-line">
		
		<ul class="navbar-nav mr-auto">
          	<li class="nav-item active">
            	<a class="nav-link navbar-brand" href="/home">Home</a>
          	</li>
          	<li class="nav-item active dropdown">
	          	<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" aria-haspopup="true" aria-expanded="false">
	          		Ordini
	          	</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="ordiniInElaborazione">In Elaborazione</a> 
					<a class="dropdown-item" href="ordiniElaborati">Elaborati</a>
					<a class="dropdown-item" href="ordiniSpediti">Spediti</a>
				</div>
			</li>
			<li class="nav-item active">
            	<a class="nav-link" href="aggiornaScorte">Aggiorna Scorte</a>
          	
        </ul>
        <nav class="navbar navbar-dark bg-dark  mx-sm-5" >
		  <form class="form-inline" method="POST" action="findOrder">
		    <input class="form-control mr-sm-2" type="search" name="codiceOrdine" placeholder="Inserisci codice ordine" aria-label="Search">
		    <button class="btn btn-success my-2 my-sm-0" type="submit">Cerca</button>
		  </form>
		</nav>
        <a class="navbar-brand mr-sm-2" href="#"><strong>Benvenuto ${warehouseWorkerLogged}!</strong></a>
        <a class="nav-link my-2 my-sm-0 white" href="doWarehouseLogout">Logout</a>
        
			

	</nav>
</body>
</html>