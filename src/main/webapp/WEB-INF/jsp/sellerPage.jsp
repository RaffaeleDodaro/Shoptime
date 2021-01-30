<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="headMarketPlace.jsp" />
<script src="/js/productManagement.js"></script>
<link rel="stylesheet" href="/css/sellerPage.css" type="text/css" />
</head>
<body>
	<jsp:include page="navbarMarketPlace.jsp" />
	<div class="containergrid container" id="griglia">
		
	<h4>Catalogo di: ${nomeVenditore}</h4>
		<div class="row" id="riga">
			<c:forEach var="prodotto" items="${prodottiVenditore}">
				<div class="col-md-4 col-sm-6">
					<div class="product-grid2">
						<div class="product-image2">
							<a
								href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})">
								<img class="pic-1" src="${prodotto.urlImg}"> <img
								class="pic-2" src="${prodotto.urlImg}">
							</a>
							<ul class="social">
								<li><a href="javascript:requestProduct(${prodotto.codice}, ${false}, ${prodotto.venditore.id})" data-tip=""><i
										class=""></i></a></li>
							</ul>
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