<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="/js/productManagement.js"></script>
<script src="/js/confirmOrder.js"></script>
<jsp:include page="headMarketPlace.jsp" />

<link rel="stylesheet" href="/css/cart.css" type="text/css" />


<script src="https://use.fontawesome.com/c560c025cf.js"></script>
</head>
<body>

	<jsp:include page="navbarMarketPlace.jsp" />

	<div>
		<div class="card shopping-cart">
			<div class="card-header bg-dark text-light">
				<i class="fa fa-shopping-cart" aria-hidden="true"></i> Carrello <a
					href="/" class="btn btn-outline-info btn-sm pull-right">Continua
					a comprare!</a>
				<div class="clearfix"></div>
			</div>
			<div class="card-body">
				<!-- PRODUCT -->
				<c:set var="count" value="0" />
				<c:forEach var="prodotto" items="${cart}">
					<div class="row" id="riga${prodotto.codice}">
						<div class="col-12 col-sm-12 col-md-2 text-center">
							<img class="img-responsive" src="${prodotto.urlImg}"
								alt="prewiew" width="120" height="200">
						</div>
						<div class="col-12 text-sm-center col-sm-12 text-md-left col-md-6">
							<h4 class="product-name">
								<a
									href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})"><strong>${prodotto.nome}</strong></a>
							</h4>
							<div class="col-xs-6">
								<br>Venduto e spedito da <br> <a
									href="javascript:requestSeller(${product.venditore.id})"
									id="nomeVenditore"><strong>${product.venditore.nome}</strong></a>
							</div>
							<!-- <h4>
							<small>Product description</small>
						</h4> -->
						</div>
						<div
							class="col-12 col-sm-12 text-sm-center col-md-4 text-md-right row">
							<div class="col-3 col-sm-3 col-md-6 text-md-right"
								style="padding-top: 5px">
								<h6>
									<strong>${prodotto.prezzo}&euro;<span
										class="text-muted">&nbsp; &nbsp; x</span></strong>
								</h6>
							</div>
							<div class="col-4 col-sm-4 col-md-4">
								<div class="quantity">
									<!--<input type="button" value="+" class="plus"> -->
									<c:set var="var" value="n${prodotto.nome}" />
									<input type="number" step="1" max="${prodotto.quantita}"
										min="1" value="${prodotto.quantita}" title="Qty" class="qty"
										id="${count}" size="4">
									<!-- <input type="button" value="-"
										class="minus"> -->
								</div>
							</div>
							<div class="col-2 col-sm-2 col-md-2 text-right">
								<button class="btn btn-outline-danger btn-xs" type="button"
									onclick="removeFromCart(${prodotto.codice}, ${prodotto.venditore.id}, ${count})">
									<i class="fa fa-trash" aria-hidden="true"></i>
								</button>
							</div>
						</div>
					</div>
					<br></br>
					<c:set var="count" value="${count + 1}" />
				</c:forEach>
				<hr>

				<!-- END PRODUCT -->

			</div>
			<div class="card-footer">

				<div class="pull-right" style="margin: 10px">

					<c:choose>
						<c:when test="${user!=null}">
							<a href="javascript:confirmOrder()"
								class="btn btn-success pull-right fab fa-amazon-pay" id="procediacquisto">&nbsp;&nbsp;&nbsp;Procedi con l'acquisto</a>
						</c:when>
						<c:otherwise>
							<a class="btn btn-success pull-right fa fa-shopping-basket" id="procediacquisto" data-toggle="modal"
								data-target="#modalLoginForm">&nbsp;&nbsp;&nbsp;Procedi con l'acquisto</a>

						</c:otherwise>
					</c:choose>
					<div class="pull-right"  style="margin: 5px">
						Prezzo totale <b id="prezzototale">${cartPrice}&euro;</b>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 
	<div class="container">
		<div class="row">
			<c:forEach var="prodotto" items="${cart}">
				<div class="item product col-sm-4" id="${prodotto.codice}">
					<a href="javascript:requestProduct(${prodotto.codice}, ${false})">
						<img src="${prodotto.urlImg}" class="img-square" width="120"
						height="223" />
						<h3 id="productName${prodotto.codice}">${prodotto.nome}</h3>
						<p>${prodotto.prezzo}&euro;</p>

					</a>
				</div>
			</c:forEach>
		</div>
	</div>
	</div> -->




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
						value="Entra"></input> <br>
					<p>
						Oppure <a href="register">Registrati</a>!
					</p>
				</div>


			</div>
		</div>
	</div>



	<jsp:include page="footerMarketPlace.jsp" />
</body>
</html>