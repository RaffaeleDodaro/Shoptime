<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../../css/courierNavbar.css">
</head>
<body>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <a class="navbar-brand" href="#">Benvenuto ${courierLogged} !</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
      <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="/goHome">Home</a>
        </li>  
        <li class="nav-item active dropdown"><a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Pacchi</a>
            <div class="dropdown-menu bg-dark">
                <a class="dropdown-item" href="ongoing">Da consegnare</a> 
                <a class="dropdown-item" href="stocked">In giacenza</a>
                <a class="dropdown-item" href="finished">Consegnati</a>
            </div>
      </ul>
    </div>  
  </nav>
</body>
</html>