<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shoptime Tracking</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="../../../css/tracking.css">
<link rel="icon" type="image/png" href="/images/logo.png" />
</head>
<body>
<div class="bg2">
	<c:if test="${logs!= null}">
		<h2 class="headline">Tracking del tuo pacco</h2>
	    <div>
	    	<div class="row">
		        <div class="col-sm-4 right-border">
		        </div>
		        <div class="col-sm-4 top-border">
				</div>
				<div class="col-sm-4 left-border">
		        </div>
	    	</div>
	        <c:forEach var="log" items="${logs}">
	        <div class="row">
		        <div class="col-sm-4 right-border">
		        </div>
		        <div class="col-sm-4">
		        	<div class="card">
						<p><strong> Data: </strong> ${log.data} </p>  
						<p><strong> Stato: </strong> ${log.stato} </p>     
						<p><strong>Posizione:</strong>	  ${log.posizione}  </p>
						<c:if test="${log.descrizione!=null}">
						<strong>Descrizione:</strong>  ${log.descrizione}
						</c:if>
					</div>
			      
				</div>
				<div class="col-sm-4 left-border">
		        </div>
			</div>	
			</c:forEach>
			<div class="row">
		        <div class="col-sm-4 right-border">
		        </div>
		        <div class="col-sm-4 bottom-border">
				</div>
				<div class="col-sm-4 left-border">
		        </div>
	    	</div>
		</div>
	</c:if>
	<c:if test="${logs == null }">
		<h3 align="center"> Il codice di tracking inserito non corrisponde a nessun pacco.</h3>
		<div class="text-center">
		<p> <form method="POST" action="getLogs">
                <input class="InputStyle" name="trackingCode" placeholder="Inserisci un'altro codice di tracking" style="font-family:Arial, FontAnsertwesome" type="text"><br><br>
                <input type="submit" class="InputStyle-button" style="font-family:Arial, FontAnsertwesome" value="Cerca">
            </form> 
        </p>
        <p> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br></p>
        </div>
	</c:if>
</div>
</body>
</html>