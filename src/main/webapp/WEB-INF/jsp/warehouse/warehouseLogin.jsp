<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shoptime Warehouse Login</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="../../../css/warehouse_login.css">
	<link rel="icon" type="image/png" href="/images/logo.png" />
</head>
<body>


		
		<div class="d-flex justify-content-center h-100">
			<div class="card">
				<div class="card-header">
					<h2>Shoptime Warehouse</h2>
				</div>
				<div class="card-body">
					<form method="POST" action="doWarehouseLogin">
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input type="email" required="required" id="form-control" class="form-control" name="email" placeholder="user@example.it">
							
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input required="required" type="password" class="form-control" name="password" placeholder="password">
						</div>
						<div class="form-group">
							<input type="submit" value="Login" class="btn login_btn"> 
						</div>
					</form>
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
	
</body>
</html>