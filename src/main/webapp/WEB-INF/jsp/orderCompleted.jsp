<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="headMarketPlace.jsp" />
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="navbarMarketPlace.jsp" />
	<div>
		<div class="card shopping-cart">
			<div class="card-header bg-dark text-light">
				<!-- <i class="fa fa-shopping-cart" aria-hidden="true"></i> ORDINE <a
					href="/" class="btn btn-outline-info btn-sm pull-right">Continua
					a comprare!</a>-->
				<div class="clearfix"></div>
			</div>
			<div class="card-body">
				<!-- PRODUCT -->
				<c:forEach var="prodotto"  items="${cart}">
					<div class="row">
						<div class="col-12 col-sm-12 col-md-2 text-center">
							<img class="img-responsive" src="${prodotto.urlImg}"
								alt="prewiew" width="120" height="80">
						</div>
						<div class="col-12 text-sm-center col-sm-12 text-md-left col-md-6">
							<h4 class="product-name">
								<strong>${prodotto.nome}</strong>
							</h4>
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
										class="text-muted">x</span></strong>
								</h6>
							</div>
							<div class="col-4 col-sm-4 col-md-4">
								<div class="quantity">
									<!--<input type="button" value="+" class="plus"> -->
									<c:set var="var" value="n${prodotto.nome}" />
									<input type="number"
										step="1" max="${prodotto.quantita}" min="1" value="${prodotto.quantita}"
										title="Qty" class="qty" size="4">
									<!-- <input type="button" value="-"
										class="minus"> -->
								</div>
							</div>
						</div>
					</div>
					<br></br>
				</c:forEach>
				<hr>

				<!-- END PRODUCT -->

			</div>	
	</div>
	</div>
	<jsp:include page="footerMarketPlace.jsp" />
</body>
</html>