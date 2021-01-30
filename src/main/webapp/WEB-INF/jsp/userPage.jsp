<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/userPage.css" type="text/css" />
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="headMarketPlace.jsp" />
<script src="/js/userPage.js"></script>
<meta charset="ISO-8859-1">
</head>
<body onload="showOrders();">
	<jsp:include page="navbarMarketPlace.jsp" />
<body>
	<div>
		<div class="row">
			<div class="col-md-3 ">
				<div class="list-group ">
					<a href="javascript:showOrders()"
						class="list-group-item list-group-item-action">Ordini</a> <a
						href="javascript:showReviews()"
						class="list-group-item list-group-item-action">Recensioni</a> <a
						href="javascript:showAddresses()"
						class="list-group-item list-group-item-action">Indirizzi</a> <a
						href="javascript:showCards()"
						class="list-group-item list-group-item-action">Carte</a>
					<c:choose>
						<c:when test="${seller != null}">
							<a href="javascript:showCatalog()"
								class="list-group-item list-group-item-action">Catalogo</a>
						</c:when>
				
					<c:otherwise>
				              <a href="javascript:showAddIva()"
				                class="list-group-item list-group-item-action">Vendi</a>
				            </c:otherwise>
				
					</c:choose>
					<!--	<a href="#" class="list-group-item list-group-item-action">Post</a>
					<a href="#" class="list-group-item list-group-item-action">Category</a>
					<a href="#" class="list-group-item list-group-item-action">New</a>
					<a href="#" class="list-group-item list-group-item-action">Comments</a>
					<a href="#" class="list-group-item list-group-item-action">Appearance</a>
					<a href="#" class="list-group-item list-group-item-action">Reports</a>
					<a href="#" class="list-group-item list-group-item-action">Settings</a>-->


				</div>
			</div>
			<div class="col-md-9">
				<div class="card">
					<div class="card-body">
						<h4>${user.nome} ${user.cognome}</h4>
						<hr>
					</div>
				</div>


				<div class="row" id="orders">
					<c:forEach var="order" items="${userOrders}">

						<div class="col-md-12">

							<div class="card">
								<div class="card-header">
									<!--  <h4 class ="cart-title">
											Numero ordine: ${order.codice}
										</h4> -->
									<h4 class="card title">
										Numero ordine: ${order.codice} <br> Stato dell'ordine:
										${order.stato} <br> Tracking per prodotti shoptime:
										${order.tracking} <a href="tracking">PAGINA TRACKING</a>
									</h4>

								</div>

								<div class="card-body" id="${order.codice }">
									<c:forEach var="product" items="${order.prodotti}">
										<div class="row">
											<div class="col-md-3">
												<img src="${product.urlImg}"
													class="img-responsive img-fluid">
											</div>
											<div class="col-md-6">
												<p>${product.nome}</p>
												<br>
												<p>${product.venditore.id}</p>
											</div>
											<div class="col-md-3">
												<p>
													QUANTITA: ${product.inOrdine}: <br>
												<p>PREZZO: ${product.prezzo}&euro;
												<p>
											</div>
										</div>
									</c:forEach>
									<br> <br>
									<div class="row">
										<div class="col-md-6">
											<h4>INDIRIZZO</h4>
											<c:set var="address" value="${order.indirizzo}">
											</c:set>
											<a href="#"
												class="list-group-item list-group-item-action flex-column align-items-start">
												<div class="d-flex w-100 justify-content-between">
													<address>
														<strong>Nome destinatario:
															${address.destinatario}<br>Numero di telefono:
															${address.numTelefono}<br>Via: ${address.via}
														</strong><br> Citta':${address.citta}, Regione:
														${address.regione} <br> CAP: ${address.cap}<br>
													</address>
												</div>

											</a>
										</div>
										<div class="col-md-6">
											<h4>CARTA DI CREDITO</h4>
											<c:set var="card" value="${order.cartaDiCredito}">
											</c:set>
											<a href="#"
												class="list-group-item list-group-item-action flex-column align-items-start">
												<div class="d-flex w-100 justify-content-between">
													<div>
														<strong>Numero: ${card.numero}</strong><br>
														Intestatario: ${card.intestatario}, Data scadenza:
														${card.scadenza} <br> CVV: ${card.CVV}<br>
													</div>
												</div>
											</a>
										</div>
									</div>
								</div>
								<div class="card-footer">
									<h4>TOTALE: ${order.prezzo}&euro;</h4>
								</div>
							</div>
						</div>
						<br>
						<br>
					</c:forEach>
				</div>

				<div class="row" id="reviews">
					<c:forEach var="review" items="${userReviews}">
						<div class="col-md-12">
							<a href="#"
								class="list-group-item list-group-item-action flex-column align-items-start">
								<div class="d-flex w-100 justify-content-between">
									<h5>
										Valutazione:
										<c:forEach begin="1" end="${review.valutazione}">
											<span class="fa fa-star checked"></span>
										</c:forEach>
										<c:forEach begin="1" end="${5-review.valutazione}">
											<span class="fa fa-star unchecked"></span>
										</c:forEach>
									</h5>
									<small>${review.prodotto.nome}</small>
								</div>
								<p class="mb-1">${review.testoRecensione}</p>
							</a>
						</div>
					</c:forEach>
				</div>



				<div class="row" id="addresses">
					<c:forEach var="address" items="${userAddresses}">
						<div class="col-md-12">
							<a href="#"
								class="list-group-item list-group-item-action flex-column align-items-start">
								<div class="d-flex w-100 justify-content-between">
									<address>
										<strong>Nome destinatario: ${address.destinatario}<br>Numero
											di telefono: ${address.numTelefono}<br>Via:
											${address.via}
										</strong><br> Citta':${address.citta}, Regione: ${address.regione}
										<br> CAP: ${address.cap}<br>
									</address>
								</div>
								<div class="col-2 col-sm-2 col-md-2 text-right">
									<button class="btn btn-outline-danger btn-xs" type="button"
										onclick="removeAddress(${address.codice})">
										<i class="fa fa-trash" aria-hidden="true"></i>
									</button>
								</div>
							</a>
						</div>
					</c:forEach>
						<input type="submit" class="btn btn-dark"
							value="Aggiungi indirizzo" data-toggle="modal"
							data-target="#modalAddAddress"></input>
				</div>


				<div class="row" id="cards">
					<c:forEach var="card" items="${userCards}">
						<div class="col-md-12">
							<a href="#"
								class="list-group-item list-group-item-action flex-column align-items-start">
								<div class="d-flex w-100 justify-content-between">
									<div>
										<strong>Numero: ${card.numero}</strong><br> Intestatario:
										${card.intestatario}, Data scadenza: ${card.scadenza} <br>
										CVV: ${card.CVV}<br>
									</div>

								</div>
								<div class="col-2 col-sm-2 col-md-2 text-right">
									<button class="btn btn-outline-danger btn-xs" type="button"
										onclick="removeCard(${card.numero})">
										<i class="fa fa-trash" aria-hidden="true"></i>
									</button>
								</div>
							</a>
						</div>
					</c:forEach>
						<input type="submit" class="btn btn-dark" value="Aggiungi carta"
							data-toggle="modal" data-target="#modalAddCard"></input>
				</div>

				<div class="row" id="sellerCatalog">
					<c:forEach var="prodotto" items="${sellerCatalog}">
						<div class="col-md-3" id="${prodotto.codice}">
							<a href="javascript:requestProduct(${prodotto.codice})"> <img
								src="${prodotto.urlImg}" class="img-square" width="120"
								height="223" />
								<h3 id="productName${prodotto.codice}">${prodotto.nome}</h3>
								<p>${prodotto.prezzo}&euro;</p>
							</a>
						</div>
					</c:forEach>
				</div>

				 <div class="row" id="addIva">
					<div class="col-md-12">
						<input id="ivaField" name="PARTITA IVA"
							class="form-control input-md" required="" type="text">
						<div class="col-md-12">
							&nbsp; <input type="submit" class="btn btn-dark"
								onclick="addProductIva()" value="Aggiungi IVA" placeholder="Aggiungi IVA"></input>
						</div>
					</div>
			</div>
		</div>
	</div>






	<div class="modal fade" id="modalAddAddress" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header text-center">
					<h4 class="modal-title w-100 font-weight-bold" id="accessBtn">Aggiungi
						un nuovo indirizzo</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body mx-3">
					<div class="form-group">
						<label for="nomeDestinatario" id="labelNomeDestinatario">Nome
							destinatario</label> <input type="text" class="form-control"
							id="nomeDestinatario7"
							placeholder="">
					</div>

					<div class="form-group">
						<label for="telefonoDestinatario" id="labelNumeroDestinatario">Numero
							telefono destinatario</label> <input type="number" class="form-control"
							id="telefonoDestinatario8"
							placeholder="">
					</div>
					<div class="form-group">
						<label for="form-address3" id="labelVia">Via</label> <input
							type="search" class="form-control" id="form-address3"
							placeholder="Via*" />
						<!-- NON DOBBIAMO TOCCARE L'ID CHE SENNO' NON FUNZIONA API -->
					</div>
					<div class="form-group">
						<label for="form-address4" id="labelRegione">Regione</label> <input
							type="text" class="form-control" id="form-address4" disabled />
					</div>
					<div class="form-group">
						<label for="form-city5" id="labelCitta">Citta'</label> <input
							type="text" class="form-control" id="form-city5" disabled />
					</div>
					<div class="form-group">
						<label for="form-zip6" id="labelCap">CAP</label> <input
							type="number" class="form-control" id="form-zip6" disabled />
					</div>

				</div>
				<div class="modal-footer d-flex justify-content-center">
					<input type="submit" class="btn btn-dark" onclick="addAddress()"
						value="Aggiungi indirizzo"></input>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="modalAddCard" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header text-center">
					<h4 class="modal-title w-100 font-weight-bold" id="accessBtn">Aggiungi
						una nuova carta</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<div class="form-group">
					<label for="intestatario" id="labelIntestatario">Intestatario</label>
					<input type="text" class="form-control" id="intestatario2"
						placeholder="">
				</div>

				<div class="form-group">
					<label for="numero" id="labelNumero">Numero</label> <input
						type="number" class="form-control" id="numero2">
				</div>

				<div class="form-group">
					<label for="scadenza" id="labelScadenza">Scadenza</label> <input
						type="month" id="scadenza2" name="start" min="2021-01"
						value="2021-01">
				</div>

				<div class="form-group">
					<label for="cvv" id="labelCvv">CVV</label> <input type="number"
						class="form-control" id="cvv2">
				</div>


				<div class="modal-footer d-flex justify-content-center">
					<input type="submit" class="btn btn-dark" onclick="addCard()"
						value="Aggiungi carta"></input>
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
				container : document.querySelector('#form-address3'),
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
								document.querySelector('#form-address4').value = e.suggestion.administrative
										|| '';
								document.querySelector('#form-city5').value = e.suggestion.city
										|| '';
								document.querySelector('#form-zip6').value = e.suggestion.postcode
										|| '';
							});
		})();
	</script>


	<jsp:include page="footerMarketPlace.jsp" />

</body>
</html>