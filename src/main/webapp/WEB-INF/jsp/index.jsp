<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="/js/productManagement.js"></script>
<jsp:include page="headMarketPlace.jsp" />
<link rel="stylesheet" href="/css/index.css" type="text/css" />
</head>
<body>

	<jsp:include page="navbarMarketPlace.jsp" />


	<div id="demoSlide" class="carousel slide containerSlide"
		data-ride="carousel">
		<ul class="carousel-indicators">
			<li data-target="#demoSlide" data-slide-to="0" class="active"></li>
			<li data-target="#demoSlide" data-slide-to="1"></li>
			<li data-target="#demoSlide" data-slide-to="2"></li>
		</ul>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<a href="javascript:requestProduct(87, ${false}, 8)"> <img
					src="https://gizchina.it/wp-content/uploads/2020/07/come-creare-watchface-per-Xiaomi-Mi-Band-5-2.jpg"
					width="100%" height="500" id="imgSlide1" alt="Mi band 5">
					<div class="carousel-caption">
						<h3>Xiaomi Mi Band 5</h3>
						<p>Xiaomi Mi Band 5 Smart Bracelet Activity Tracker e Fitness
							Tracker con Schermo AMOLED a Colori 1,1'', Tipo Magnetico
							Caricare, 50m Impermeabile Pedometro e Notifiche di Messaggistica</p>
					</div>
				</a>
			</div>
			<div class="carousel-item">
				<a href="javascript:requestProduct(80, ${false}, 8)"> <img
					src="https://images2.alphacoders.com/105/thumb-1920-1053430.jpg"
					alt="Logitech G502" width="100%" height="500" id="imgSlide2">
					<div class="carousel-caption">
						<h3>Logitech G502</h3>
						<p>11 Pulsanti Programmabili e Rotella di Scorrimento
							Ultraveloce Doppia Modalita': Il mouse da gioco cablato Logitech
							G ti offre un controllo completamente personalizzato delle tue
							impostazioni di gioco</p>
					</div>
				</a>
			</div>
			<div class="carousel-item">
				<a href="javascript:requestProduct(66, ${false}, 8)"> <img
					src="https://www.tomshw.it/images/images/2020/08/offerte-nespresso-109552.jpg"
					alt="Nespresso Inissia" width="100%" height="500" id="imgSlide3">
					<div class="carousel-caption">
						<h3>Nespresso Inissia</h3>
						<p>Nespresso Inissia XN1005 macchina per caffe' espresso, ruby
							red.La macchina per caffe' a capsule più compatta di Nespresso.</p>
					</div>
				</a>
			</div>
			<div class="carousel-item">
				<a href="javascript:requestProduct(66, ${false}, 8)"> <img
					src="https://s1.1zoom.me/b5050/541/362489-commander06_1920x1080.jpg"
					alt="Nespresso Inissia" width="100%" height="500" id="imgSlide4">
					<div class="carousel-caption">
						<h3>EVGA GeForce GTX TITAN X</h3>
						<p>Grafikkarten - GF GTX TITAN X - 12 GB GDDR5 - PCI Express 3.0 x16 DVI, HDMI, 3 x DisplayPort</p>
					</div>
				</a>
			</div>
		</div>
		<!-- <a class="carousel-control-prev" href="#demoSlide" data-slide="prev">
			<span class="carousel-control-prev-icon"></span>
		</a> <a class="carousel-control-next" href="#demoSlide" data-slide="next">
			<!-- <span class="carousel-control-next-icon"></span>
		</a> -->
	</div>







	<!--

	<c:forEach var="prodotto" items="${prodotti}">
		<div class="item product col-sm-4"
			id="${prodotto.codice}, ${prodotto.venditore.id}">
			<a
				href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})">
				<img src="${prodotto.urlImg}" class="img-square" width="120"
				height="223" />
				<h3 id="productName${prodotto.codice}, ${prodotto.venditore.id}">${prodotto.nome}</h3>
				<p>${prodotto.prezzo}&euro;</p>
				<p>
					<a href="javascript:requestSeller(${prodotto.venditore.id})">${prodotto.venditore.nome}<p></a>
			</a>
		</div>
	</c:forEach>
 



	<div class="containergrid container" id="griglia">
		<div class="row">
			<c:forEach var="prodotto" items="${prodottiInformatica}">
				<div class="col-md-4 col-sm-6">
					<div class="product-grid2">
						<div class="product-image2">
							<a
								href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})">
								<img class="pic-1" src="${prodotto.urlImg}"> <img
								class="pic-2" src="${prodotto.urlImg}">
							</a>
							<ul class="social">
								<li><a href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})" data-tip="Visualizza prodotto"><i
										class="fa fa-eye"></i></a></li>
								<!--  <li><a href="javascript:requestProduct(${prodotto.codice},${true}, ${prodotto.venditore.id})" data-tip="Aggiungi al carrello"><i
										class="fa fa-shopping-cart"></i></a></li>
							</ul>
						</div>
						<div class="product-content">
							<h3 class="title">
								<a href="javascript:requestProduct(${prodotto.codice},${true}, ${prodotto.venditore.id})">${prodotto.nome}</a>
							</h3>
							<span class="price">${prodotto.prezzo}&euro;</span>
							<p><a href="javascript:requestSeller(${prodotto.venditore.id})">${prodotto.venditore.nome}</p></a>
						</div>
					</div>
				</div>

			</c:forEach>
		</div>
	</div>
	<hr>
 -->













	<!--<c:forEach var="prodotto" items="${prodottiInformatica}">
		<div class="col-md-4 col-sm-6">
			<div class="product-grid2">
				<div class="product-image2">
					<a
						href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})">
						<img class="pic-1" src="${prodotto.urlImg}"> <img
						class="pic-2" src="${prodotto.urlImg}">
					</a>
					<ul class="social">
						<li><a
							href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})"
							data-tip="Visualizza prodotto"><i class="fa fa-eye"></i></a></li>
						  <li><a href="javascript:requestProduct(${prodotto.codice},${true}, ${prodotto.venditore.id})" data-tip="Aggiungi al carrello"><i
										class="fa fa-shopping-cart"></i></a></li>
							</ul>
						</div>
						<div class="product-content">
							<h3 class="title">
								<a href="javascript:requestProduct(${prodotto.codice},${true}, ${prodotto.venditore.id})">${prodotto.nome}</a>
							</h3>
							<span class="price">${prodotto.prezzo}&euro;</span>
							<p><a href="javascript:requestSeller(${prodotto.venditore.id})">${prodotto.venditore.nome}</p></a>
						</div>
					</div>
				</div>
	</c:forEach>-->




	<br>
	<div>
		<div class="row" id="rigaProdotti">
			<div class="col-md-12">
				<h2>
					Prodotti <b>Informatica</b>
				</h2>
				<div id="myCarousel" class="carousel slide" data-ride="carousel"
					data-interval="0">
					<!-- Carousel indicators -->
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
					</ol>
					<!-- Wrapper for carousel items -->
					<div class="carousel-inner">
						<div class="item carousel-item active">
							<div class="row">
								<c:forEach begin="0" end="5" step="1" var="prodotto"
									items="${prodottiInformatica}">
									<div class="col">
										<div class="thumb-wrapper">
											<div class="img-box">
												<a
													href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})"><img
													src="${prodotto.urlImg}" class="img-responsive img-fluid"
													alt=""></a>
											</div>
											<div class="thumb-content">
												<a
													href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})"><h4>${prodotto.nome}</h4></a>
												<p>
													Venditore: <a
														href="javascript:requestSeller(${prodotto.venditore.id})">${prodotto.venditore.nome}</a>
												<p>
												<p class="item-price">
													<span>${prodotto.prezzo}&euro;</span>
												</p>
												<!-- <div class="star-rating">
													<ul class="list-inline">
														<li class="list-inline-item"><i class="fa fa-star"></i></li>
														<li class="list-inline-item"><i class="fa fa-star"></i></li>
														<li class="list-inline-item"><i class="fa fa-star"></i></li>
														<li class="list-inline-item"><i class="fa fa-star"></i></li>
														<li class="list-inline-item"><i class="fa fa-star-o"></i></li>
													</ul>
													 
												</div>
												<a
													href="requestProduct(${prodotto.codice},${true}, ${prodotto.venditore.id})"
													class="btn btn-primary">Aggiungi al carrello</a>-->
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<!-- <a class="carousel-control left carousel-control-prev"
						href="#myCarousel" data-slide="prev"> <i
						class="fa fa-angle-left"></i>
					</a> <a class="carousel-control right carousel-control-next"
						href="#myCarousel" data-slide="next"> <i
						class="fa fa-angle-right"></i></a>-->
				</div>

			</div>
		</div>
	</div>

	<br>
	<div>
		<div class="row" id="rigaProdotti">
			<div class="col-md-12">
				<h2>
					Prodotti <b>Film</b>
				</h2>
				<div id="myCarousel" class="carousel slide" data-ride="carousel"
					data-interval="0">
					<!-- Carousel indicators -->
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
					</ol>
					<!-- Wrapper for carousel items -->
					<div class="carousel-inner">
						<div class="item carousel-item active">
							<div class="row">
								<c:forEach begin="0" end="5" step="1" var="prodotto"
									items="${prodottiFilm}">
									<div class="col">
										<div class="thumb-wrapper">
											<div class="img-box">
												<a
													href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})"><img
													src="${prodotto.urlImg}" class="img-responsive img-fluid"
													alt=""></a>
											</div>
											<div class="thumb-content">
												<a
													href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})"><h4>${prodotto.nome}</h4></a>
												<p>
													<a
														href="javascript:requestSeller(${prodotto.venditore.id})">Venditore:
														${prodotto.venditore.nome}
														<p>
													</a>
												<p class="item-price">
													<span>${prodotto.prezzo}&euro;</span>
												</p>
												<!-- <div class="star-rating">
													<ul class="list-inline">
														<li class="list-inline-item"><i class="fa fa-star"></i></li>
														<li class="list-inline-item"><i class="fa fa-star"></i></li>
														<li class="list-inline-item"><i class="fa fa-star"></i></li>
														<li class="list-inline-item"><i class="fa fa-star"></i></li>
														<li class="list-inline-item"><i class="fa fa-star-o"></i></li>
													</ul>
													 
												</div>
												<a
													href="requestProduct(${prodotto.codice},${true}, ${prodotto.venditore.id})"
													class="btn btn-primary" " class="btn btn-primary">Aggiungi
													al carrello</a>-->
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<!-- <a class="carousel-control left carousel-control-prev"
						href="#myCarousel" data-slide="prev"> <i
						class="fa fa-angle-left"></i>
					</a> <a class="carousel-control right carousel-control-next"
						href="#myCarousel" data-slide="next"> <i
						class="fa fa-angle-right"></i></a>-->
				</div>

			</div>
		</div>
	</div>

	<br>

	<div class="row" id="rigaProdotti">
		<div class="col-md-12">
			<h2>
				Prodotti <b>Videogiochi</b>
			</h2>
			<div id="myCarousel" class="carousel slide" data-ride="carousel"
				data-interval="0">
				<!-- Carousel indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>
				<!-- Wrapper for carousel items -->
				<div class="carousel-inner">
					<div class="item carousel-item active">
						<div class="row">
							<c:forEach begin="0" end="5" step="1" var="prodotto"
								items="${prodottiVideogiochi}">
								<div class="col">
									<div class="thumb-wrapper">
										<div class="img-box">
											<a
												href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})"><img
												src="${prodotto.urlImg}" class="img-responsive img-fluid"
												alt=""></a>
										</div>
										<div class="thumb-content">
											<a
												href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})"><h4>${prodotto.nome}</h4></a>
											<p>
												<a href="javascript:requestSeller(${prodotto.venditore.id})">Venditore:
													${prodotto.venditore.nome}</a>
											<p class="item-price">
												<span>${prodotto.prezzo}&euro;</span>
											</p>
											<!-- <div class="star-rating">
													<ul class="list-inline">
														<li class="list-inline-item"><i class="fa fa-star"></i></li>
														<li class="list-inline-item"><i class="fa fa-star"></i></li>
														<li class="list-inline-item"><i class="fa fa-star"></i></li>
														<li class="list-inline-item"><i class="fa fa-star"></i></li>
														<li class="list-inline-item"><i class="fa fa-star-o"></i></li>
													</ul>
													 
												</div>
												<a
													href="requestProduct(${prodotto.codice},${true}, ${prodotto.venditore.id})"
													class="btn btn-primary" class="btn btn-primary">Aggiungi
													al carrello</a>-->
										</div>
									</div>

								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<!-- <a class="carousel-control left carousel-control-prev"
						href="#myCarousel" data-slide="prev"> <i
						class="fa fa-angle-left"></i>
					</a> <a class="carousel-control right carousel-control-next"
						href="#myCarousel" data-slide="next"> <i
						class="fa fa-angle-right"></i></a>-->
			</div>

		</div>
	</div>



	<br>


	<div class="row" id="rigaProdotti">
		<div class="col-md-12">
			<h2>
				Prodotti <b>Cucina</b>
			</h2>
			<div id="myCarousel" class="carousel slide" data-ride="carousel"
				data-interval="0">
				<!-- Carousel indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>
				<!-- Wrapper for carousel items -->
				<div class="carousel-inner">
					<div class="item carousel-item active">
						<div class="row">
							<c:forEach begin="0" end="5" step="1" var="prodotto"
								items="${prodottiCucina}">
								<div class="col">
									<div class="thumb-wrapper">
										<div class="img-box">
											<a
												href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})"><img
												src="${prodotto.urlImg}" class="img-responsive img-fluid"
												alt=""></a>
										</div>
										<div class="thumb-content">
											<a
												href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})"><h4>${prodotto.nome}</h4></a>
											<p>
												<a href="javascript:requestSeller(${prodotto.venditore.id})">Venditore:
													${prodotto.venditore.nome}
													<p>
												</a>
											<p class="item-price">
												<span>${prodotto.prezzo}&euro;</span>
											</p>
											<!-- <div class="star-rating">
													<ul class="list-inline">
														<li class="list-inline-item"><i class="fa fa-star"></i></li>
														<li class="list-inline-item"><i class="fa fa-star"></i></li>
														<li class="list-inline-item"><i class="fa fa-star"></i></li>
														<li class="list-inline-item"><i class="fa fa-star"></i></li>
														<li class="list-inline-item"><i class="fa fa-star-o"></i></li>
													</ul>
													 
												</div>
												<a
													href="requestProduct(${prodotto.codice},${true}, ${prodotto.venditore.id})"
													class="btn btn-primary">Aggiungi al carrello</a>-->
										</div>
									</div>

								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<!-- <a class="carousel-control left carousel-control-prev"
						href="#myCarousel" data-slide="prev"> <i
						class="fa fa-angle-left"></i>
					</a> <a class="carousel-control right carousel-control-next"
						href="#myCarousel" data-slide="next"> <i
						class="fa fa-angle-right"></i></a>-->
			</div>

		</div>
	</div>












	<!-- 
								<div class="col-sm-3">
									<div class="thumb-wrapper">
										<div class="img-box">
											<img src="https://image.ibb.co/g0CAPp/ipad.jpg"
												class="img-responsive img-fluid" alt="">
										</div>
										<div class="thumb-content">
											<h4>Apple iPad</h4>
											<p class="item-price">
												<strike>$400.00</strike> <span>$369.00</span>
											</p>
											<div class="star-rating">
												<ul class="list-inline">
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star-o"></i></li>
												</ul>
											</div>
											<a href="#" class="btn btn-primary">Aggiungi al carrello</a>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="thumb-wrapper">
										<div class="img-box">
											<img src="https://image.ibb.co/g0CAPp/ipad.jpg"
												class="img-responsive img-fluid" alt="">
										</div>
										<div class="thumb-content">
											<h4>Sony Headphone</h4>
											<p class="item-price">
												<strike>$25.00</strike> <span>$23.99</span>
											</p>
											<div class="star-rating">
												<ul class="list-inline">
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star-o"></i></li>
												</ul>
											</div>
											<a href="#" class="btn btn-primary">Aggiungi al carrello</a>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="thumb-wrapper">
										<div class="img-box">
											<img src="https://image.ibb.co/g0CAPp/ipad.jpg"
												class="img-responsive img-fluid" alt="">
										</div>
										<div class="thumb-content">
											<h4>Macbook Air</h4>
											<p class="item-price">
												<strike>$899.00</strike> <span>$649.00</span>
											</p>
											<div class="star-rating">
												<ul class="list-inline">
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i
														class="fa fa-star-half-o"></i></li>
												</ul>
											</div>
											<a href="#" class="btn btn-primary">Aggiungi al carrello</a>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="thumb-wrapper">
										<div class="img-box">
											<img src="https://image.ibb.co/g0CAPp/ipad.jpg"
												class="img-responsive img-fluid" alt="">
										</div>
										<div class="thumb-content">
											<h4>Nikon DSLR</h4>
											<p class="item-price">
												<strike>$315.00</strike> <span>$250.00</span>
											</p>
											<div class="star-rating">
												<ul class="list-inline">
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star-o"></i></li>
													<li class="list-inline-item"><i class="fa fa-star-o"></i></li>
												</ul>
											</div>
											<a href="#" class="btn btn-primary">Aggiungi al carrello</a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="item carousel-item">
							<div class="row">
								<div class="col-sm-3">
									<div class="thumb-wrapper">
										<div class="img-box">
											<img src="https://image.ibb.co/g0CAPp/ipad.jpg"
												class="img-responsive img-fluid" alt="">
										</div>
										<div class="thumb-content">
											<h4>Sony Play Station</h4>
											<p class="item-price">
												<strike>$289.00</strike> <span>$269.00</span>
											</p>
											<div class="star-rating">
												<ul class="list-inline">
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star-o"></i></li>
												</ul>
											</div>
											<a href="#" class="btn btn-primary">Aggiungi al carrello</a>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="thumb-wrapper">
										<div class="img-box">
											<img src="https://image.ibb.co/g0CAPp/ipad.jpg"
												class="img-responsive img-fluid" alt="">
										</div>
										<div class="thumb-content">
											<h4>Macbook Pro</h4>
											<p class="item-price">
												<strike>$1099.00</strike> <span>$869.00</span>
											</p>
											<div class="star-rating">
												<ul class="list-inline">
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i
														class="fa fa-star-half-o"></i></li>
												</ul>
											</div>
											<a href="#" class="btn btn-primary">Aggiungi al carrello</a>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="thumb-wrapper">
										<div class="img-box">
											<img src="https://image.ibb.co/g0CAPp/ipad.jpg"
												class="img-responsive img-fluid" alt="">
										</div>
										<div class="thumb-content">
											<h4>Bose Speaker</h4>
											<p class="item-price">
												<strike>$109.00</strike> <span>$99.00</span>
											</p>
											<div class="star-rating">
												<ul class="list-inline">
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star-o"></i></li>
													<li class="list-inline-item"><i class="fa fa-star-o"></i></li>
												</ul>
											</div>
											<a href="#" class="btn btn-primary">Aggiungi al carrello</a>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="thumb-wrapper">
										<div class="img-box">
											<img src="https://image.ibb.co/g0CAPp/ipad.jpg"
												class="img-responsive img-fluid" alt="">
										</div>
										<div class="thumb-content">
											<h4>Samsung Galaxy S8</h4>
											<p class="item-price">
												<strike>$599.00</strike> <span>$569.00</span>
											</p>
											<div class="star-rating">
												<ul class="list-inline">
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star-o"></i></li>
												</ul>
											</div>
											<a href="#" class="btn btn-primary">Aggiungi al carrello</a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="item carousel-item">
							<div class="row">
								<div class="col-sm-3">
									<div class="thumb-wrapper">
										<div class="img-box">
											<img src="https://image.ibb.co/g0CAPp/ipad.jpg"
												class="img-responsive img-fluid" alt="">
										</div>
										<div class="thumb-content">
											<h4>Apple iPhone</h4>
											<p class="item-price">
												<strike>$369.00</strike> <span>$349.00</span>
											</p>
											<div class="star-rating">
												<ul class="list-inline">
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star-o"></i></li>
												</ul>
											</div>
											<a href="#" class="btn btn-primary">Aggiungi al carrello</a>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="thumb-wrapper">
										<div class="img-box">
											<img src="https://image.ibb.co/g0CAPp/ipad.jpg"
												class="img-responsive img-fluid" alt="">
										</div>
										<div class="thumb-content">
											<h4>Canon DSLR</h4>
											<p class="item-price">
												<strike>$315.00</strike> <span>$250.00</span>
											</p>
											<div class="star-rating">
												<ul class="list-inline">
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star-o"></i></li>
												</ul>
											</div>
											<a href="#" class="btn btn-primary">Aggiungi al carrello</a>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="thumb-wrapper">
										<div class="img-box">
											<img src="https://image.ibb.co/g0CAPp/ipad.jpg"
												class="img-responsive img-fluid" alt="">
										</div>
										<div class="thumb-content">
											<h4>Google Pixel</h4>
											<p class="item-price">
												<strike>$450.00</strike> <span>$418.00</span>
											</p>
											<div class="star-rating">
												<ul class="list-inline">
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star-o"></i></li>
												</ul>
											</div>
											<a href="#" class="btn btn-primary">Aggiungi al carrello</a>
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="thumb-wrapper">
										<div class="img-box">
											<img src="https://image.ibb.co/g0CAPp/ipad.jpg"
												class="img-responsive img-fluid" alt="">
										</div>
										<div class="thumb-content">
											<h4>Apple Watch</h4>
											<p class="item-price">
												<strike>$350.00</strike> <span>$330.00</span>
											</p>
											<div class="star-rating">
												<ul class="list-inline">
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star"></i></li>
													<li class="list-inline-item"><i class="fa fa-star-o"></i></li>
												</ul>
											</div>
											<a href="#" class="btn btn-primary">Aggiungi al carrello</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- Carousel controls -->























	<!-- 
		<c:forEach var="prodotto" items="${prodotti}">
				<div class="item product col-sm-4"
					id="${prodotto.codice}, ${prodotto.venditore.id}">
					<a
						href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})">
						<img src="${prodotto.urlImg}" class="img-square" width="120"
						height="223" />
						<h3 id="productName${prodotto.codice}, ${prodotto.venditore.id}">${prodotto.nome}</h3>
						<p>${prodotto.prezzo}&euro;</p>
						<p>
							<a href="javascript:requestSeller(${prodotto.venditore.id})">${prodotto.venditore.nome}<p></a>
					</a>
				</div>
			</c:forEach>
			


		<div class="carousel-item active">
			<img
				src="https://images-eu.ssl-images-amazon.com/images/I/810o-i5jpiL._AC_UL200_SR200,200_.jpg"
				alt="Los Angeles" width="1100" height="500">
			<div class="carousel-caption">
				<h3>MicroSD</h3>
				<p>SanDisk MicroSDXC UHS-I 128 GB - Scheda per Nintendo Switch,
					Official Nintendo Licensed Product</p>
			</div>
		</div>
		<div class="carousel-item">
			<img
				src="https://images-eu.ssl-images-amazon.com/images/I/81lwVXscuBL._AC_UL200_SR200,200_.jpg"
				alt="Chicago" width="1100" height="500">
			<div class="carousel-caption">
				<h3>FIFA 21</h3>
				<p>FIFA 21 PlayStation 4 [Edizione Italiana]</p>
			</div>
		</div>
		<div class="carousel-item">
			<img
				src="https://images-eu.ssl-images-amazon.com/images/I/41FBPQ%2Bmq0L._AC_UL200_SR200,200_.jpg"
				alt="New York" width="1100" height="500">
			<div class="carousel-caption">
				<h3>PS5</h3>
				<p>PlayStation Network PSN Card 10€ | Codice download per PSN -
					Account italiano</p>
			</div>
		</div>
	</div>
	<a class="carousel-control-prev" href="#demo" data-slide="prev"> <span
		class="carousel-control-prev-icon"></span>
	</a>
	<a class="carousel-control-next" href="#demo" data-slide="next"> <span
		class="carousel-control-next-icon"></span>
	</a>
	</div>











	<div class="container">
		<div class="row">
			<c:forEach var="prodotto" items="${prodotti}">
				<div class="item product col-xs-6 col-sm-4 col-md-2"
					id="${prodotto.codice}, ${prodotto.venditore.id}">
					<div class="thumbnail">
						<a
							href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})">
							<img src="${prodotto.urlImg}" class="img-square" width="120"
							height="223" />
						</a>
						<div class="text">
							<div class="thumbnail">
								<a id="product-title"
									href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})">
									<p id="productName${prodotto.codice}, ${prodotto.venditore.id}">${prodotto.nome}</p>
									<p>${prodotto.prezzo}&euro;</p>
								</a> <a href="javascript:requestSeller(${prodotto.venditore.id})">${prodotto.venditore.nome}<p></a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div> -->
	<!-- <div class="col-sm-4">
				<a href="https://www.google.it"><img
					src="/images/ps5-camera.png" class="img-fluid">
					<h3>PlayStation®5 - HD Camera</h3>
					<p>
					  10,75  &euro;
					</p>
					<button type="button" class="btn btn-outline-primary">Dettagli</button></a>

			</div>
			<div class="col-sm-4">
				<a href="https://www.google.it"><img
					src="/images/cyberpunk-2077.png" class="img-fluid">
					<h3>Cyberpunk 2077 D1 Edition - Day-One - Playstation 4</h3>
					<p>60 &euro;</p>
					<button type="button" class="btn btn-outline-primary">Dettagli</button></a>

			</div>
		</div>
		<div class="row">
			<div class="col-sm-4">
				<a href="https://www.google.it"><img src="/images/ps5.png"
					class="img-fluid">
					<h3>PS5</h3>
					<p>500 &euro;</p>
					<button type="button" class="btn btn-outline-primary"="www.google.it">Dettagli</button>
				</a>
			</div>
			<div class="col-sm-4">
				<a href="https://www.google.it"><img
					src="/images/ps5-camera.png" class="img-fluid">
					<h3>PlayStation 5 - HD Camera</h3>
					<p>100 &euro;</p>
					<button type="button" class="btn btn-outline-primary">Dettagli</button></a>

			</div>
			<div class="col-sm-4">
				<a href="Prova"><img src="/images/cyberpunk-2077.png"
					class="img-fluid">
					<h3>Cyberpunk 2077 D1 Edition - Day-One - Playstation 4</h3>
					<p>60 &euro;</p>
					<button type="button" class="btn btn-outline-primary">Dettagli</button></a>

			</div>
			 -->

	<jsp:include page="footerMarketPlace.jsp" />
</body>
</html>