<%@ page language="java" contentType="text/html; charset=iso-8859-15"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/navbar.css" type="text/css">
<script src="/js/productManagement.js"></script>
<script src="/js/notifyHandler.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- <link rel="stylesheet" href="/css/mp.css" type="text/css" /> -->
<script src="/js/loginRegister.js"></script>
</head>
<body>

	<header class="section-header">
		<section class="header-main">
			<div class="container-fluid">
				<div class="row align-items-center">
					<div class="col-lg-3 col-sm-12 col-md-4 col-5">
						<a href="/" class="brand-wrap" data-abc="true"> <img
							class="logo" id="imglogo" src="/images/logo.png" height="35">
							<span class="logo">ShopTime</span>
						</a>
					</div>
					<div class="col-lg-4 col-xl-5 col-sm-12 col-md-4 d-none d-md-block">
						<form action="#" class="search-wrap">
							<div class="input-group w-100">
								<input type="text" class="form-control search-form"
									style="width: 55%;" placeholder="Cerca" id="searchText">
								<div class="input-group-append">
									<button id="userLogo" class="btn btn-primary search-button"
										type="submit" onclick="searchProduct();">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
						</form>
					</div>
					<div class="col-lg-5 col-xl-4 col-sm-12 col-md-4 col-7">
						<div class="d-flex justify-content-end">

							<div class="dropdown btn-group">
								<!--<c:choose>
									<c:when test="${seller != null}">
										<a class="nav-link nav-icons" href="#" id="nav-link"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false" data-abc="true"><i
											class="fas fa fa-bell"></i></a>
									</c:when>
								</c:choose>-->



								<c:choose>
									<c:when test="${seller != null}">
										<div class="dropdown">
											<button class="dropbtn" id="notifiche">
												<i style="font-size: 130%" class="fas fa fa-bell"></i>
												<div class="dropdown-content">
													<c:forEach var="notifica" items="${notifiche}">
														<a class="nav-link tendina fa fa-bell" href=""
															onMouseOver="read(${notifica.codice});">${notifica.messaggio}</a>
													</c:forEach>
												</div>
											</button>
										</div>
									</c:when>
								</c:choose>




							</div>
							<button id="cart" onclick="showCart()">
								<i class="nav-link nav-user-img fa fa-shopping-cart"
									id="nav-link"></i>
							</button>
							<div class="dropdown btn-group">
								<button id="user-logo" class="dropbtn">
									<i id="nav-link" class="fa">&#xf007;</i>
									<div class="dropdown-content">
										<c:choose>
											<c:when test="${user == null}">
												<a class="nav-link tendina fa fa-user" href=""
													data-toggle="modal" data-target="#modalLoginForm">&nbsp;&nbsp;&nbsp;
													Accedi</a>
												<a class="nav-link tendina fa fa-user-plus" href=""
													data-toggle="modal" data-target="#modalRegisterForm">&nbsp;&nbsp;&nbsp;Registrati</a>
											</c:when>
											<c:otherwise>
												<a class="nav-link tendina" href="userPage">Il mio
													account</a>
												<c:choose>
													<c:when test="${seller != null}">
														<a class="nav-link tendina" href="sellProduct">Vuoi
															vendere un prodotto?</a>
													</c:when>
												</c:choose>
												<a class="nav-link tendina" href="javascript:logout()">Logout</a>
											</c:otherwise>
										</c:choose>
									</div>
								</button>
							</div>
						</div>
					</div>
				</div>
		</section>
		<nav class="navbar navbar-expand-md navbar-main border-bottom">
			<div class="container-fluid">
				<form class="d-md-none my-2">
					<div class="input-group w-100">
						<input type="text" class="form-control search-form"
							style="width: 55%;" placeholder="Cerca" id="searchText">
						<div class="input-group-append">
							<button id="userLogo" class="btn btn-primary search-button"
								type="submit" onclick="searchProduct();">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
				</form>
				<button class="navbar-toggler collapsed" type="button"
					data-toggle="collapse" data-target="#dropdown6"
					aria-expanded="false">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="navbar-collapse collapse col-sx-6" id="dropdown6"
					style="">
					<ul class="navbar-nav mr-auto">

						<!-- <li class="nav-item"><a class="nav-link"
							href="javascript:showProductsFromCategory('Telefonia')"
							data-abc="true">Telefonia</a></li> -->
						<li class="nav-item"><a class="nav-link"
							href="javascript:showProductsFromCategory('Videogiochi')"
							data-abc="true">Videogiochi</a></li>
						<li class="nav-item"><a class="nav-link"
							href="javascript:showProductsFromCategory('Informatica')"
							data-abc="true">Informatica</a></li>
						<li class="nav-item"><a class="nav-link"
							href="javascript:showProductsFromCategory('Film e TV')"
							data-abc="true">Film e TV</a></li>
						<!--<li class="nav-item"><a class="nav-link"
							href="javascript:showProductsFromCategory('CD e Vinili')"
							data-abc="true">CD e Vinili</a></li>-->
						<li class="nav-item"><a class="nav-link"
							href="javascript:showProductsFromCategory('Cucina')"
							data-abc="true">Cucina</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>









	<!-- Grey with black text
	<div class="container">
		<nav class="navbar navbar-expand-sm" id="topNav">
			<ul class="navbar-nav" id="demo">
				<li class="nav-item active"><a class="nav-link" href="/"> <span
						class="glyphicon glyphicon-home"></span>Home
				</a></li>
				<li class="nav-item active"><a class="nav-link"
					onclick="showCart()">Carrello ${cartSize}</a></li>
				<li>
					<div class="dropdown">
						<button class="dropbtn">
							<i style="font-size: 24px" class="fa">&#xf007;</i>
							<div class="dropdown-content">
								<c:choose>
									<c:when test="${user == null}">
										<li class="nav-item active"><a class="nav-link" href=""
											data-toggle="modal" data-target="#modalLoginForm">Accedi</a></li>
										<li class="nav-item active"><a class="nav-link"
											href="register">Registrati</a></li>
									</c:when>
									<c:otherwise>
										<li class="nav-item active"><a class="nav-link"
											href="userPage">Il mio account</a></li>

										<li class="nav-item active"><a class="nav-link"
											href="sellProduct">Vuoi vendere un prodotto?</a></li>

										<li class="nav-item active"><a class="nav-link"
											href="javascript:logout()">Logout</a></li>
									</c:otherwise>
								</c:choose>
							</div>
						</button>
					</div> <!--  		<c:if test="${name == null}">
					<li class="nav-item active"><a class="nav-link" href=""
						data-toggle="modal" data-target="#modalLoginForm">Accedi</a></li>
					<li class="nav-item active"><a class="nav-link" href="register">Registrati</a></li>
				</c:if>-->
	<!-- Dropdown  -->
	<!-- <div class="dropdown">
					<button class="dropbtn">Categorie
					<div class="dropdown-content">
							<li> <a href="#">Libri</a>
								<ul>
										<li><a href="javascript:showProductsFromCategory('Azione')">Azione</a> </li>
										<li><a href="javascript:showProductsFromCategory('Comici')">Comici</a> </li>
								</ul>
							</li>
						
							<li> <a href="#">Elettronica</a>
								<ul>
										<li><a href="javascript:showProductsFromCategory('Telefonia')">Telefonia</a> </li>
										<li><a href="javascript:showProductsFromCategory('Videogiochi')">Videogiochi</a> </li>
										<li><a href="javascript:showProductsFromCategory('Computer')">Computer</a> </li>
								</ul>
							</li>
							</button>
					</div>  <c:if test="${nome != null}">
						<div class="dropdown">
							<button class="dropbtn">
								Ciao: ${name}<i class="fa fa-caret-down"></i>
							</button>
							<div class="dropdown-content">
								<a href="#">vcsd</a> <a href="#">Casalvefasinghi</a> <a href="#">avfda</a>
								<a href="logout">logout</a>
							</div>
						</div>
					</c:if>
			</ul>
			<c:choose>
	           <c:when test="${seller != null}">
	           	<li class="nav-item active">
	               <div class="dropdown">
                  		<button class="dropbtn">
                  			<i  style="font-size: 24px" class="fa fa-bell"></i>
                  			<div class="dropdown-content">
                  				<c:forEach var="notifica" items="${notifiche}">
                 				 	<div class="toast-body">
    									${notifica.messaggio}
  									</div>
                  				</c:forEach> 

				  			</div> 				
                 		</button>
          			</div>
                 </li>
               </c:when>
			</c:choose>	
			<form class="form-inline my-2 my-lg-0 ml-auto" action="#">
				<input class="form-control mr-sm-2" type="text"
					placeholder="Cerca un prodotto" id="searchText">
				<button class="btn btn-danger" type="submit"
					onclick="searchProduct();" id="btnSubmit">Cerca</button>
			</form>
		</nav>
	</div>
 -->
	<div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header text-center">
					<h4 class="modal-title w-100 font-weight-bold" id="accessBtn">Accedi</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body mx-3">
					<div class="md-form mb-5">
						<!--<i class="fas fa-envelope prefix grey-text"></i> -->
						<input type="email" id="email" class="form-control validate"
							name="email"> <label data-error="wrong"
							data-success="right" for="defaultForm-email" id="insertEmail">Email</label>
					</div>

					<div class="md-form mb-4">
						<!--<i class="fas fa-lock prefix grey-text"></i>-->
						<input type="password" id="password" class="form-control validate"
							name="password"> <label data-error="wrong"
							data-success="right" for="defaultForm-pass" id="insertPassword">Password</label>
					</div>

				</div>
				<div class="modal-footer d-flex justify-content-center">
					<input type="submit" class="btn btn-dark" onclick="access()"
						value="Entra"></input>
				</div>
			</div>
		</div>
	</div>
	<!-- 
	<div class="container">
		<nav class="navbar navbar-expand-sm" id="navDown">
			<ul class="navbar-nav">
				<li><a href="#">Libri</a>
					<ul>
						<li><a href="javascript:showProductsFromCategory('Azione')">Azione</a>
						</li>
						<li><a href="javascript:showProductsFromCategory('Comici')">Comici</a>
						</li>
					</ul></li>

				<li><a href="#">Elettronica</a>
					<ul>
						<li><a
							href="javascript:showProductsFromCategory('Telefonia')">Telefonia</a>
						</li>
						<li><a
							href="javascript:showProductsFromCategory('Videogiochi')">Videogiochi</a>
						</li>
						<li><a href="javascript:showProductsFromCategory('Computer')">Computer</a>
						</li>
					</ul></li>
				<li><a href="javascript:showProductsFromCategory('Film e TV')">Film
						e TV</a></li>
				<li><a
					href="javascript:showProductsFromCategory('CD e Vinili')">CD e
						Vinili</a></li>
			</ul>
		</nav>
	</div>-->

	<div class="modal fade" id="modalRegisterForm" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-scrollable" role="document">
			<div class="modal-content">
				<div class="modal-header text-center">
					<h4 class="modal-title w-100 font-weight-bold" id="accessBtn">Registrati</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body mx-3">
					<div class="md-form mb-4">
						<label for="name" id="labelNome">Nome</label> <input type="text"
							class="form-control" id="nome">
					</div>

					<div class="md-form mb-4">
						<!--<i class="fas fa-lock prefix grey-text"></i>-->
						<label for="surname" id="labelCognome">Cognome</label> <input
							type="text" class="form-control" id="cognome">
					</div>

					<div class="md-form mb-4">
						<label for="numeroTelefono" id="labelNumeroTelefono">Telefono</label>
						<input type="number" class="form-control" id="numeroTelefono">
					</div>

					<div class="md-form mb-4">
						<label for="email" id="labelEmail">Email</label> <input
							type="email" class="form-control" id="emailRegister">
					</div>

					<div class="md-form mb-4">
						<label for="password" id="labelPassword">Password</label> <input
							type="password" class="form-control" id="passwordRegister">
					</div>
					<p>Inserisci i dati dell'indirizzo</p>
					<div class="md-form mb-4">
						<label for="nomeDestinatario" id="labelNomeDestinatario">Nome
							destinatario</label> <input type="text" class="form-control"
							id="nomeDestinatario">
					</div>

					<div class="md-form mb-4">
						<label for="numeroTelefonicoDestinatario"
							id="labelTelefonoDestinatario">Numero telefono
							destinatario</label> <input type="number" class="form-control"
							id="numeroTelefonicoDestinatario">
					</div>

					<div class="md-form mb-4">
						<label for="form-address" id="labelVia">Via</label> <input
							type="search" class="form-control" id="form-address"
							placeholder="Via*" />

					</div>

					<div class="md-form mb-4">
						<label for="form-address2" id="labelRegione">Regione</label> <input
							type="text" class="form-control" id="form-address2" disabled />
					</div>

					<div class="md-form mb-4">
						<label for="form-city" id="labelCitta">Citta'</label> <input
							type="text" class="form-control" id="form-city" disabled />
					</div>

					<div class="md-form mb-4">
						<label for="form-zip" id="labelCap">CAP</label> <input
							type="number" class="form-control" id="form-zip" disabled />
					</div>
					<p>Inserisci i dati della carta di credito</p>
					<div class="md-form mb-4">
						<label for="intestatario" id="labelIntestatario">Intestatario</label>
						<input type="text" class="form-control" id="intestatario">
					</div>

					<div class="md-form mb-4">
						<label for="numero" id="labelNumero">Numero</label> <input
							type="number" class="form-control" id="numero">
					</div>

					<div class="md-form mb-4">
						<label for="scadenza" id="labelScadenza">Scadenza:</label> <input
							type="month" id="scadenza" name="start" min="2021-01"
							value="2021-01">
					</div>

					<div class="md-form mb-4">
						<label for="cvv" id="labelCvv">CVV</label> <input type="number"
							class="form-control" id="cvv">
					</div>

					<input type="button" class="btn btn-dark btnRegistrati"
						onclick="registerUserJS()" value="Registrati"></input>


				</div>
			</div>
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



	<!-- Fine sezione accedi -->