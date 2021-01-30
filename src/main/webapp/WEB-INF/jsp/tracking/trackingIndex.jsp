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
<div class="bg text-center">

    <div class="centered">
        <p class="firstLine"> S &nbsp; H &nbsp; O &nbsp; P &nbsp; &nbsp; T &nbsp; I &nbsp; M &nbsp; E</p>
        <p class="secondLine">Servizio Tracking</p>
			
        <p> <form method="POST" action="getLogs">
                <input class="InputStyle" name="trackingCode" placeholder="Inserisci qui il tuo codice" style="font-family:Arial, FontAnsertwesome" type="text"><br><br>
                <input type="submit" class="InputStyle-button" style="font-family:Arial, FontAnsertwesome" value="Cerca">
            </form> 
        </p>
    </div>
</div>
</body>
</html>