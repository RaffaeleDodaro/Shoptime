<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="/js/loginRegister.js"></script>
<jsp:include page="headMarketPlace.jsp" />
</head>
<body>

	<jsp:include page="navbarMarketPlace.jsp" />

	<div class="container">
		<form role="form" class="col-md-4">
			<div class="form-group">
				<label for="name" id="labelNome">Nome</label> <input type="text"
					class="form-control" id="nome">
			</div>
			<div class="form-group">
				<label for="surname" id="labelCognome">Cognome</label> <input
					type="text" class="form-control" id="cognome">
			</div>
			<div class="form-group">
				<label for="numeroTelefono" id="labelNumeroTelefono">Telefono</label>
				<input type="number" class="form-control" id="numeroTelefono">
			</div>
			<div class="form-group">
				<label for="email" id="labelEmail">Email</label> <input type="email"
					class="form-control" id="emailRegister">
			</div>
			<div class="form-group">
				<label for="password" id="labelPassword">Password</label> <input
					type="password" class="form-control" id="passwordRegister">
			</div>

			<div class="form-group">
				<label for="form-address" id="labelVia">Via</label> <input
					type="search" class="form-control" id="form-address"
					placeholder="Via*" />
				<!-- NON DOBBIAMO TOCCARE L'ID CHE SENNO' NON FUNZIONA API -->
			</div>
			<div class="form-group">
				<label for="form-address2" id="labelRegione">Regione</label> <input
					type="text" class="form-control" id="form-address2" disabled />
			</div>
			<div class="form-group">
				<label for="form-city" id="labelCitta">Citta'</label> <input
					type="text" class="form-control" id="form-city" disabled />
			</div>
			<div class="form-group">
				<label for="form-zip" id="labelCap">CAP</label> <input type="number"
					class="form-control" id="form-zip" disabled />
			</div>

			<div class="form-group">
				<label for="intestatario" id="labelIntestatario">Intestatario</label>
				<input type="text" class="form-control" id="intestatario">
			</div>

			<div class="form-group">
				<label for="numero" id="labelNumero">Numero</label> <input
					type="number" class="form-control" id="numero">
			</div>


			<div class="form-group">
				<label for="scadenza" id="labelScadenza">Scadenza:</label> <input
					type="month" id="scadenza" name="start" min="2021-01"
					value="2021-01">
			</div>



			<div class="form-group">
				<label for="cvv" id="labelCvv">CVV</label> <input type="number"
					class="form-control" id="cvv">
			</div>


			<input type="button" class="btn btn-dark" onclick="registerUserJS()"
				value="Registrati"></input>
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
	</div>

</div>
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