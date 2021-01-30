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
<h2 class="headline">-- Dettagli Ordine --</h2>
<div class="container-fluid">
	<br>

	<div class="content">
	  <details>
		<summary>Info Ordine</summary>
		<p>
  			<div class="row">
    			<div class="col-sm-2 info">
     				<strong>Codice Ordine:</strong><br>
     				<strong>Data Ordine:</strong><br>
     				<strong>Stato Ordine:</strong><br>
     				<strong>Totale Ordine:</strong><br>
   				</div>
    			<div class="col-sm-2 info">
      				${ordine.codice}<br>
		     		${ordine.data}<br>
		     		${ordine.stato}<br>
		     		${ordine.prezzo} &euro;<br>
    			</div>
    		</div>
		</p>
	  </details>
	  
	  <details>
	    <summary>Info Destinatario</summary>
	 
	    	<div class="row">
    			<div class="col-sm-4 info">
			    	<address>
					    <strong>Destinatario:</strong><br/>
					    ${ordine.indirizzo.destinatario}
				  	</address>
				 	<address>
				 		<strong>Indirizzo:</strong><br/>
				    	${ordine.indirizzo.via}<br />
				    	${ordine.indirizzo.citta}<br />
				   		${ordine.indirizzo.regione}, ${ordine.indirizzo.getCap()}  <br/>
				    	<abbr title="Telefono">Tel:</abbr>  ${ordine.indirizzo.numTelefono}
				  	</address>
				  </div>
				  <div class="row">
    				<div class="col-sm-2 info">
    					<iframe
						  width="500"
						  height="350"
						  frameborder="0" style="border:0"
						  src="https://www.google.com/maps/embed/v1/place?key=AIzaSyDAv501Brr2eERJCy9-CH0DbZ6AmjJdP5s
						    &q=${ordine.indirizzo.via},${ordine.indirizzo.citta}" allowfullscreen>
						</iframe>
    				
    				</div>
    			</div>
			</div>
	  </details>
	
	  <details open="">
	    <summary>Lista Prodotti</summary>
	    <br>
	    <table id="tab-ordini" class="table container table-striped table-bordered table-dark">

			<thead>
				<tr>
					<th>Codice</th>
					<th>Nome</th>
					<th>Prezzo</th>
					<th>Notifica un Problema</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="prodotto" items="${ordine.prodotti}">
					<tr class="table white">
						<td>${prodotto.codice}</td>
						<td>${prodotto.nome}</td>
						<td>${prodotto.prezzo} &euro;</td>
						<td><input type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#modal-avviso" data-prodotto="${prodotto.codice}" data-nomeprodotto="${prodotto.nome}" id="${prodotto.codice}" name="${prodotto.codice}" value="Aggiungi Avviso"></td>
					</tr>		
				</c:forEach>
			</tbody>
		</table>
	  </details>
	</div>	
	<br>
	
	<c:if test = "${ordine.stato == 'In Elaborazione'}">
	
	    	<div class="row">
    			<div class="col-sm-4 "></div>
    			<div class="col-sm-4 ">
		        	<input type="button" onclick="confirmOrder(this)" class="btn btn-confirm btn-success btn-md confirm-btn" id="${ordine.codice}" name="${ordine.codice}" value="Conferma Ordine">
   				</div>
    			<div class="col-sm-4 "></div>
    	</div>
    	<br>
    	
    </c:if>
      
    <c:if test = "${ordine.stato == 'Elaborato'}">
    	<div class="row">
    			<div class="col-sm-4 "></div>
    			<div class="col-sm-4 ">
    			<form method="POST" action="assignCourier">
			        <div class="input-group mb-3 ">
						<input type="text" width="20%" class="form-control" placeholder="Inserisci l'ID Corriere" id="${ordine.codice}" name="idCorriere" aria-label="IdCorriere" aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn  btn-success" type="submit" id="${ordine.codice}" name="${ordine.codice}">Conferma</button>
						</div>
					</div>
				</form>
   				</div>
    			<div class="col-sm-4 "></div>
    	</div>
  
    </c:if>
      
    <c:if test = "${ordine.stato == 'Spedito'}">
         
    </c:if>
</div>
<c:if test="${errore != null }">
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

<div class="modal fade" id="modal-avviso" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Invia Messaggio</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form method="POST" action="sendMessage">
          <div class="form-group">
            <input type="hidden" class="form-control" name="idProdotto">
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">Scrivi Messaggio:</label>
            <textarea class="form-control" name="message" id="message-text"></textarea>
          </div>
          <button type="submit" class="btn btn-success">Invia messaggio</button>
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </form>
      </div>
      <div class="modal-footer">
        
       
      </div>
    </div>
  </div>
</div>

</body>
</html>