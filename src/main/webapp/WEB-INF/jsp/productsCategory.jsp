<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="/js/productManagement.js"></script>
<jsp:include page="headMarketPlace.jsp" />
<link rel="stylesheet" href="/css/sellerPage.css" type="text/css" />
</head>
<body>
	<jsp:include page="navbarMarketPlace.jsp" />

	<!--<div class="container">
		<div class="row">
			<c:forEach var="prodotto" items="${prodottiCategoria}">
				<div class="product col-sm-4" id="${prodotto.codice}">
					<a href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})"> <img
						src="${prodotto.urlImg}" class="img-square" width="120"
						height="223" />
						<h3 id="productName${prodotto.codice}">${prodotto.nome}</h3>
						<p>${prodotto.prezzo}&euro;</p>
						<p><a href="javascript:requestSeller(${prodotto.venditore.id})">${prodotto.venditore.nome}<p></a>
						
					</a>
				</div>
			</c:forEach>
		</div>
	</div>
	-->
	
	
	<div class="containergrid container" id="griglia">
		<h4>Categoria: ${nomeCategoria}</h4>
		<div class="row">
			<c:forEach var="prodotto" items="${prodottiCategoria}">
				<div class="col-md-4 col-sm-6">
					<div class="product-grid2">
						<div class="product-image2">
							<a
								href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})">
								<img class="pic-1" src="${prodotto.urlImg}"> <img
								class="pic-2" src="${prodotto.urlImg}">
							</a>
							<ul class="social">
								<li><a href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})" data-tip="Quick View"><i
										class="fa fa-eye"></i></a></li>
								<!-- <li><a href="#" data-tip="Add to Cart"><i
										class="fa fa-shopping-cart"></i></a></li>
							 --></ul>
						</div>
						<div class="product-content">
							<h3 class="title">
								<a href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})">${prodotto.nome}</a>
							</h3>
							<span class="price">${prodotto.prezzo}&euro;</span>
						</div>
					</div>
				</div>

			</c:forEach>
		</div>
	</div>
	<hr>
	<jsp:include page="footerMarketPlace.jsp" />
</body>
</html>