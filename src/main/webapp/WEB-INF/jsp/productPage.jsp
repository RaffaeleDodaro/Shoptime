<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="ISO-8859-1">
<jsp:include page="headMarketPlace.jsp" />
<link rel="stylesheet" href="/css/productPage.css" type="text/css" />
<script src="/js/productManagement.js"></script>
</head>
<body>
	<jsp:include page="navbarMarketPlace.jsp" />
	<!--  
	<img src="${product.urlImg}"></img>
	${product.nome}<br></br>
	${product.descrizione}<br></br>
	${product.prezzo}
	-->


	<div>
		<!-- BOOTSRAP TEMPLATE -->
		<div class="super_container">
			<header class="header" style="display: none;">
				<div class="header_main">
					<div class="container">
						<div class="row">
							<div
								class="col-lg-6 col-12 order-lg-2 order-3 text-lg-left text-right">
								<div class="header_search">
									<div class="header_search_content">
										<div class="header_search_form_container">
											<form action="#" class="header_search_form clearfix">
												<div class="custom_dropdown">
													<div class="custom_dropdown_list">
														<span class="custom_dropdown_placeholder clc">All
															Categories</span> <i class="fas fa-chevron-down"></i>
														<ul class="custom_list clc">
															<li><a class="clc" href="#">All Categories</a></li>
														</ul>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</header>
			<div class="single_product">
				<div class="container-fluid"
					style="background-color: #fff; padding: 11px;">
					<div class="row">
						<!--  <div class="col-lg-2 order-lg-1 order-2">
	                    <!-- <ul class="image_list">
	                        <li data-image="https://res.cloudinary.com/dxfq3iotg/image/upload/v1565713229/single_4.jpg"><img src="${product.urlImg}" alt=""></li>
	                        <!-- <li data-image="https://res.cloudinary.com/dxfq3iotg/image/upload/v1565713228/single_2.jpg"><img src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1565713228/single_2.jpg" alt=""></li>
	                        <!-- <li data-image="https://res.cloudinary.com/dxfq3iotg/image/upload/v1565713228/single_3.jpg"><img src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1565713228/single_3.jpg" alt=""></li>
	                    </ul>-->
						<div class="col-sm-4">
							<div class="image_selected">
								<img src="${product.urlImg}" alt="" height="50%" width="50%"
									class="img-responsive img-fluid">
							</div>
						</div>
						<div class="col-sm-6">
							<div class="product_description">
								<nav>
									<ol class="breadcrumb">
										<li class="breadcrumb-item"><a href="/">Home</a></li>
										<!--<li class="breadcrumb-item"><a href="#">Products</a></li>
	                                <li class="breadcrumb-item active">Accessories</li>-->
									</ol>
								</nav>
								<div class="product_name">${product.nome}</div>
								<!--<div class="product-rating"><span class="badge badge-success"><i class="fa fa-star"></i> 4.5 Star</span> <span class="rating-review">35 Ratings & 45 Reviews</span></div>-->
								<div class="product_price">${product.prezzo}&euro;</div>
								<!-- <div> <span class="product_saved">You Saved:</span> <span style='color:black'>₹ 2,000<span> </div>-->
								<!--<hr class="singleline">
	                        <div> <span class="product_info">EMI starts at ₹ 2,000. No Cost EMI Available<span><br> <span class="product_info">Warranty: 6 months warranty<span><br> <span class="product_info">7 Days easy return policy<span><br> <span class="product_info">7 Days easy return policy<span><br> <span class="product_info">In Stock: 25 units sold this week<span> </div>
	                        <div>
	                            <div class="row">
	                                <div class="col-md-5">
	                                    <div class="br-dashed">
	                                        <div class="row">
	                                            <div class="col-md-3 col-xs-3"> <img src="https://img.icons8.com/color/48/000000/price-tag.png"> </div>
	                                            <div class="col-md-9 col-xs-9">
	                                                <div class="pr-info"> <span class="break-all">Get 5% instant discount + 10X rewards @ RENTOPC</span> </div>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                                <div class="col-md-7"> </div>
	                            </div>
	                            <div class="row" style="margin-top: 15px;">
	                                <div class="col-xs-6" style="margin-left: 15px;"> <span class="product_options">RAM Options</span><br> <button class="btn btn-primary btn-sm">4 GB</button> <button class="btn btn-primary btn-sm">8 GB</button> <button class="btn btn-primary btn-sm">16 GB</button> </div>
	                                <div class="col-xs-6" style="margin-left: 55px;"> <span class="product_options">Storage Options</span><br> <button class="btn btn-primary btn-sm">500 GB</button> <button class="btn btn-primary btn-sm">1 TB</button> </div>
	                            </div>
	                        </div>
	                        <hr class="singleline"> -->
								<div class="order_info d-flex flex-row">
									<form action="#">
								</div>
								<div class="row">
									<div class="col-4 col-sm-4 col-md-4" style="margin-left: 17px;">
										<div class="quantity">
											<span>Quantit&agrave;: </span> <input type="number" step="1"
												max="99" min="1" value="1" title="Qty" id="qty" size="4">
										</div>
									</div>
									<div class="col-xs-6">



										<a href="#">
											<button type="button"
												onclick="requestProduct(${product.codice},${true}, ${product.venditore.id})"
												class="btn shop-button fa fa-cart-plus" id="addProductToCart">&nbsp;&nbsp;&nbsp;Aggiungi
												al carrello</button>
										</a>
										<button type="button" data-target="#modalReview" data-toggle="modal"
											class="btn shop-button fa fa-cart-plus" id="addReview">
												&nbsp;&nbsp;&nbsp;Recensisci
												Prodotto
										</button>
										</a>
										<!--<button type="button" class="btn btn-success shop-button">Buy Now</button>
	                                	 <div class="product_fav"><i class="fas fa-heart"></i></div>-->
										<div class="col-xs-6">
											<br>Venduto e spedito da <br> <a
												href="javascript:requestSeller(${product.venditore.id})"
												id="nomeVenditore"><strong>${product.venditore.nome}</strong></a>
										</div>




									</div>
									<br>

								</div>
							</div>
						</div>
					</div>

					<div class="modal fade" id="modalReview" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title">Recensisci articolo</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<textarea class="form-control col-xs-12" id="review-area"
										rows="7" cols="50"></textarea>
								</div>
								<div class="modal-footer">
									<form action="">
										<input class="star star-5" id="star-5" type="radio"
											name="star" /> <label class="star star-5" for="star-5">
										</label> <input class="star star-4" id="star-4" value="false"
											type="radio" name="star" /> <label class="star star-4"
											for="star-4"></label> <input class="star star-3" id="star-3"
											type="radio" value="false" name="star" /> <label
											class="star star-3" for="star-3"></label> <input
											class="star star-2" id="star-2" type="radio" value="false"
											name="star" /> <label class="star star-2" for="star-2"></label>
										<input class="star star-1" id="star-1" type="radio"
											value="false" name="star" /> <label class="star star-1"
											for="star-1"></label>
									</form>
									<button type="button" class="btn btn-secondary"
										onclick="addReview()" data-dismiss="modal">Aggiungi
										recensione</button>
								</div>
							</div>
						</div>
					</div>
					<!-- <div class="row row-underline">
	                <div class="col-md-6"> <span class=" deal-text">Combo Offers</span> </div>
	                <div class="col-md-6"> <a href="#" data-abc="true"> <span class="ml-auto view-all"></span> </a> </div>
	            </div> -->
					<!--  <div class="row">
	                <div class="col-md-5">
	                    <div class="row padding-2">
	                        <div class="col-md-5 padding-0">
	                            <div class="bbb_combo">
	                                <div class="bbb_combo_image"><img class="bbb_combo_image" src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1560924153/alcatel-smartphones-einsteiger-mittelklasse-neu-3m.jpg" alt=""></div>
	                                <div class="d-flex flex-row justify-content-start"> <strike style="color:red;"> <span class="fs-10" style="color:black;">₹ 32,000<span> </span></span></strike> <span class="ml-auto"><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i></span> </div>
	                                <div class="d-flex flex-row justify-content-start" style=" margin-bottom: 13px; "> <span style="margin-top: -4px;">₹30,000</span> <span class="ml-auto fs-10">23 Reviews</span> </div> <span>Acer laptop with 10GB RAM + 500 GB Hard Disk</span>
	                            </div>
	                        </div>
	                        <div class="col-md-2 text-center"> <span class="step">+</span> </div>
	                        <div class="col-md-5 padding-0">
	                            <div class="bbb_combo">
	                                <div class="bbb_combo_image"><img class="bbb_combo_image" src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1560924153/alcatel-smartphones-einsteiger-mittelklasse-neu-3m.jpg" alt=""></div>
	                                <div class="d-flex flex-row justify-content-start"> <strike style="color:red;"> <span class="fs-10" style="color:black;">₹ 32,000<span> </span></span></strike> <span class="ml-auto"><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i></span> </div>
	                                <div class="d-flex flex-row justify-content-start" style=" margin-bottom: 13px; "> <span style="margin-top: -4px;">₹30,000</span> <span class="ml-auto fs-10">23 Reviews</span> </div> <span>Acer laptop with 10GB RAM + 500 GB Hard Disk</span>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-xs-12" style="margin-left: 36px;">
	                            <div class="boxo-pricing-items">
	                                <div class="combo-pricing-item"> <span class="items_text">1 Item</span> <span class="combo_item_price">₹13,200</span> </div>
	                                <div class="combo-plus"> <span class="plus-sign">+</span> </div>
	                                <div class="combo-pricing-item"> <span class="items_text">1 Add-on</span> <span class="combo_item_price">₹500</span> </div>
	                                <div class="combo-plus"> <span class="plus-sign">=</span> </div>
	                                <div class="combo-pricing-item"> <span class="items_text">Total</span> <span class="combo_item_price">₹13,700</span> </div>
	                                <div class="add-both-cart-button"> <button type="button" class="btn btn-primary shop-button">Add to Cart</button> </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-md-2 text-center"> <span class="vertical-line"></span> </div>
	                <div class="col-md-5" style=" margin-left: -27px;">
	                    <div class="row padding-2">
	                        <div class="col-md-5 padding-0">
	                            <div class="bbb_combo">
	                                <div class="bbb_combo_image"><img class="bbb_combo_image" src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1560924153/alcatel-smartphones-einsteiger-mittelklasse-neu-3m.jpg" alt=""></div>
	                                <div class="d-flex flex-row justify-content-start"> <strike style="color:red;"> <span class="fs-10" style="color:black;">₹ 32,000<span> </span></span></strike> <span class="ml-auto"><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating p-rating"></i><i class="fa fa-star p-rating"></i></span> </div>
	                                <div class="d-flex flex-row justify-content-start" style=" margin-bottom: 13px; "> <span style="margin-top: -4px;">₹30,000</span> <span class="ml-auto fs-10">23 Reviews</span> </div> <span>Acer laptop with 10GB RAM + 500 GB Hard Disk</span>
	                            </div>
	                        </div>
	                        <div class="col-md-2 text-center"> <span class="step">+</span> </div>
	                        <div class="col-md-5 padding-0">
	                            <div class="bbb_combo">
	                                <div class="bbb_combo_image"><img class="bbb_combo_image" src="https://res.cloudinary.com/dxfq3iotg/image/upload/v1560924153/alcatel-smartphones-einsteiger-mittelklasse-neu-3m.jpg" alt=""></div>
	                                <div class="d-flex flex-row justify-content-start"> <strike style="color:red;"> <span class="fs-10" style="color:black;">₹ 32,000<span> </span></span></strike> <span class="ml-auto"><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i><i class="fa fa-star p-rating"></i></span> </div>
	                                <div class="d-flex flex-row justify-content-start" style=" margin-bottom: 13px; "> <span style="margin-top: -4px;">₹30,000</span> <span class="ml-auto fs-10">23 Reviews</span> </div> <span>Acer laptop with 10GB RAM + 500 GB Hard Disk</span>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="col-xs-12" style="margin-left: 36px;">
	                            <div class="boxo-pricing-items">
	                                <div class="combo-pricing-item"> <span class="items_text">1 Item</span> <span class="combo_item_price">₹13,200</span> </div>
	                                <div class="combo-plus"> <span class="plus-sign">+</span> </div>
	                                <div class="combo-pricing-item"> <span class="items_text">1 Add-on</span> <span class="combo_item_price">₹500</span> </div>
	                                <div class="combo-plus"> <span class="plus-sign">=</span> </div>
	                                <div class="combo-pricing-item"> <span class="items_text">Total</span> <span class="combo_item_price">₹13,700</span> </div>
	                                <div class="add-both-cart-button"> <button type="button" class="btn btn-primary shop-button">Add to Cart</button> </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>-->
					<div class="row row-underline">
						<div class="col-md-6">
							<span class=" deal-text">Descrizione</span>
							<div class="description-text">${product.descrizione}</div>
						</div>
						<div class="col-md-6">
							<a href="#" data-abc="true"> <span class="ml-auto view-all"></span>
							</a>
						</div>
					</div>
					<!--   <div class="row">
	                <div class="col-md-12">
	                    <table class="col-md-12">
	                        <tbody>
	                            <tr class="row mt-10">
	                                <td class="col-md-4"><span class="p_specification">Sales Package :</span> </td>
	                                <td class="col-md-8">
	                                    <ul>
	                                        <li>2 in 1 Laptop, Power Adaptor, Active Stylus Pen, User Guide, Warranty Documents</li>
	                                    </ul>
	                                </td>
	                            </tr>
	                            <tr class="row mt-10">
	                                <td class="col-md-4"><span class="p_specification">Model Number :</span> </td>
	                                <td class="col-md-8">
	                                    <ul>
	                                        <li> 14-dh0107TU </li>
	                                    </ul>
	                                </td>
	                            </tr>
	                            <tr class="row mt-10">
	                                <td class="col-md-4"><span class="p_specification">Part Number :</span> </td>
	                                <td class="col-md-8">
	                                    <ul>
	                                        <li>7AL87PA</li>
	                                    </ul>
	                                </td>
	                            </tr>
	                            <tr class="row mt-10">
	                                <td class="col-md-4"><span class="p_specification">Color :</span> </td>
	                                <td class="col-md-8">
	                                    <ul>
	                                        <li>Black</li>
	                                    </ul>
	                                </td>
	                            </tr>
	                            <tr class="row mt-10">
	                                <td class="col-md-4"><span class="p_specification">Suitable for :</span> </td>
	                                <td class="col-md-8">
	                                    <ul>
	                                        <li>Processing & Multitasking</li>
	                                    </ul>
	                                </td>
	                            </tr>
	                            <tr class="row mt-10">
	                                <td class="col-md-4"><span class="p_specification">Processor Brand :</span> </td>
	                                <td class="col-md-8">
	                                    <ul>
	                                        <li>Intel</li>
	                                    </ul>
	                                </td>
	                            </tr>
	                        </tbody>
	                    </table>
	                </div>
	            </div>-->
				</div>
			</div>
		</div>
		<div class="list-group">
			<c:forEach var="review" items="${reviews}">
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
						<small>${review.utente.nome}</small>
					</div>
					<p class="mb-1">${review.testoRecensione}</p>
				</a>
			</c:forEach>
		</div>
	</div>
	<jsp:include page="footerMarketPlace.jsp" />
</body>
</html>