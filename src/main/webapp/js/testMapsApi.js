<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="headMarketPlace.jsp" />
</head>
<body>

	<jsp:include page="navbarMarketPlace.jsp" />


	<form role="form" class="col-md-4">
		<div class="form-group">
			<label for="name" id="labelNome">Nome</label> <input type="name"
				class="form-control" id="name">
		</div>
		<div class="form-group">
			<label for="surname" id="labelCognome">Cognome</label> <input type="name"
				class="form-control" id="surname">
		</div>
		<div class="form-group">
			<label for="mobilenumber" id="labelNumeroTelefono">Telefono</label> <input type="number"
				class="form-control" id="mobilenumber">
		</div>
		<div class="form-group">
			<label for="email" id="labelEmail">Email</label> <input type="email"
				class="form-control" id="email">
		</div>
		<div class="form-group">
			<label for="password" id="labelPassword">Password</label> <input type="password"
				class="form-control" id="password">
		</div>

		<div class="form-group">
			<label for="form-address" id="labelVia">Via*</label> <input type="search"
				class="form-control" id="via" placeholder="Via*" />
		</div>
		<div class="form-group">
			<label for="form-address2" id="labelRegione">Regione</label> <input type="text"
				class="form-control" id="form-address2"
				placeholder="Street number and name" />
		</div>
		<div class="form-group">
			<label for="form-city" id="labelCitta">Citta'*</label> <input type="text"
				class="form-control" id="form-city" placeholder="Citta'*">
		</div>
		<div class="form-group">
			<label for="form-zip" id="labelCap">CAP*</label> <input type="text"
				class="form-control" id="form-zip" placeholder="CAP*">
		</div>
		<input type="button" class="btn" value="Registrati">
	</form>







	<!--

	<form action="/billing" class="form">
		<div class="form-group">
			<label for="form-address">Via*</label> <input type="search"
				class="form-control" id="form-address" placeholder="Via*" />
		</div>
		<div class="form-group">
			<label for="form-address2">Address 2</label> <input type="text"
				class="form-control" id="form-address2"
				placeholder="Street number and name" />
		</div>
		<div class="form-group">
			<label for="form-city">Citta'*</label> <input type="text"
				class="form-control" id="form-city" placeholder="Citta'*">
		</div>
		<div class="form-group">
			<label for="form-zip">CAP*</label> <input type="text"
				class="form-control" id="form-zip" placeholder="CAP*">
		</div>
	</form>
  -->
	<script src="https://cdn.jsdelivr.net/npm/places.js@1.19.0"></script>
	<script>
		(function() {
			var placesAutocomplete = places({
				appId : 'pl1OSEZA1OEA',
				apiKey : '1c6ad036f754cdfb6f749b781da7b714',
				container : document.querySelector('#form-address'),
				templates : {
					value : function(suggestion) {
						return suggestion.name;
					}
				}
			}).configure({
				type : 'address'
			});
			placesAutocomplete
					.on(
							'change',
							function resultSelected(e) {
								document.querySelector('#form-address2').value = e.suggestion.administrative
										|| '';
								document.querySelector('#form-city').value = e.suggestion.city
										|| '';
								document.querySelector('#form-zip').value = e.suggestion.postcode
										|| '';
							});
		})();
	</script>
	<jsp:include page="footerMarketPlace.jsp" />
</body>
</html>