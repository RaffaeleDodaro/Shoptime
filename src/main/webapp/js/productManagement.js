/**
 * 
 */
//var products = [];
/*
window.addEventListener("load", function() {
	var i = 1;
	do {
		var product = document.getElementById(i);
		if (product == null)
			break;
		products.push(product);
		i++;
	} while (product != null);
	addListener();
	//alert(products[1].textContent);
/*
function addListener(){
	for(var i = 0; i < products.length; i++){
		(function(i) {
			products[i].addEventListener("click", function(){
				var id = products[i].getAttribute("id");
				$.ajax({
					url: "productPage1",
					method: "GET",
					data: {idProduct: id},
					success: function(response) {
						alert("SUCCESSO");
					},
					fail: function(jqXHR, textStatus) {
						alert("Request failed: " + textStatus);
					}
				});
			});
		})(i);
	}
}*/

/*function Prodotto(codice, nome, descrizione, prezzo, urlImg) {
		this.codice = codice;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.urlImg = urlImg;
}*/

function requestProduct(id, addCart, idVenditore) {
	var quantita = 1;
	if (addCart) {
		quantita = $("#qty").val();
	}
	//alert(quantita);
	$.ajax({
		url: "productPageReceivedRequestByAJAX",
		method: "GET",
		data: {
			idProduct: id,
			addCart: addCart,
			quantita: quantita,
			idVenditore: idVenditore
		},
		success: function(response) {
			//alert("SUCCESSO");
			location.href = "productPage?id=" + id + "&idVenditore=" + idVenditore;
		},
		fail: function(jqXHR, textStatus) {
			alert("Request failed: " + textStatus);
		}
	});
}


function addReview() {
	var testoRecensione = $("#review-area").val();
	//alert(testoRecensione);
	var star1 = document.getElementById("star-1");
	var star2 = document.getElementById("star-2");
	var star3 = document.getElementById("star-3");
	var star4 = document.getElementById("star-4");
	var star5 = document.getElementById("star-5");
	var valutazione = 0;
	if (star1.checked) {
		valutazione = 1
		//alert("STAR 1");
	}
	if (star2.checked) {
		valutazione = 2;
		//alert("STAR 2");
	}
	if (star3.checked) {
		valutazione = 3;
		//alert("STAR 3");
	}
	if (star4.checked) {
		valutazione = 4;
//	alert("STAR 4");
	}
	if (star5.checked) {
		valutazione = 5;
	//	alert("STAR 5");
	}
	$.ajax({
		url: "addReview",
		method: "GET",
		data: { testoRecensione: testoRecensione, valutazione: valutazione },
		success: function(response) {
			if (response == "ok") {
				alert("Recensione aggiunta correttamente");
				location.href = "productPage";

			} else if (response == "errore") {
				alert("Hai già recensito questo prodotto");
			} else if (response == "non loggato") {
				alert("devi loggare per recensire un prodotto");
			}
		},
		fail: function(jqXHR, textStatus) {
			alert("Request failed: " + textStatus);
		}
	});
}

function showProductsFromCategory(categoryName) {
	$.ajax({
		url: "showProductsFromCategory",
		method: "GET",
		data: { categoryName: categoryName },
		success: function(response) {
			//alert("SUCCESSO");
			location.href = "productsCategory?categoria=" + categoryName;
		},
		fail: function(jqXHR, textStatus) {
			alert("Request failed: " + textStatus);
		}
	});
}
/*
$(document).ready(function() {
	$('#btnSubmit').click(function(e) {
		e.preventDefault(); 
		searchProduct();
	});
});*/


function searchProduct() {
	var name = $('#searchText').val();
	if (name == "") {
		alert("Inserisci un prodotto da cercare!");
	} else {
		$.ajax({
			url: "searchProductByName",
			method: "GET",
			data: { productName: name },
			success: function(response) {
				if (response == "searchedProducts") {
					location.href = "searchedProducts";
				}
				else {
					alert("prodotto non presente");
				}
			},
			error: function(jqXHR, textStatus) {
				alert("Request failed: " + textStatus);
			}
		});
	}
}


function sellProductFunction() {
	var nome = $('#textName').val();
	var prezzo = $('#textPrice').val();
	var urlImg = $('#textImage').val();
	var descrizione = $('#textDescription').val();
	var quantita = $('#textQuantity').val();
	var categoria = $('#category').val();

	if (nome != "" && prezzo != "" && urlImg != "" && descrizione != "" && quantita != "" && categoria != "") {

		$.ajax({
			url: "sell",
			method: "GET",
			data: {
				nome: nome,
				prezzo: prezzo,
				urlImg: urlImg,
				descrizione: descrizione,
				quantita: quantita,
				categoria: categoria
			},
			success: function(response) {
				location.href = "javascript:showCatalog()";
			},
			fail: function(jqXHR, textStatus) {
				alert("Request failed: " + textStatus);
			}
		});
	}


	if (nome == "") {
		document.getElementById("labelNome").style.color = "red";
		$('#labelNome').text("Inserisci il nome");
	}

	if (descrizione == "") {
		document.getElementById("labeldescription").style.color = "red";
		$('#labeldescription').text("Inserisci descrizione");
	}

	if (urlImg == "") {
		document.getElementById("labelimage").style.color = "red";
		$('#labelimage').text("Inserisci link all'immagine'");
	}

	if (prezzo == "") {
		document.getElementById("labelPrice").style.color = "red";
		$('#labelPrice').text("Inserisci il prezzo");
	}

	if (quantita == "") {
		document.getElementById("labelQuantity").style.color = "red";
		$('#labelQuantity').text("Inserisci la quantita");
	}
}

//DA NON DECOMMENTARE SENNO SI RIEMPIE IL DB SEMPRE CON GLI STESSI PRODOTTI
/*
$(document).ready(function() {
	alert("prova ajax");
	loadAllAmazon("https://www.amazon.it/gp/bestsellers/videogames/?ie=UTF8&ref_=sv_vg_10", "Videogiochi", "bestsellers");
	loadAllAmazon("https://www.amazon.it/gp/bestsellers/electronics/ref=zg_bs_electronics_home_all?pf_rd_p=2050f89c-0933-44b3-bba7-332b7d622560&pf_rd_s=center-2&pf_rd_t=2101&pf_rd_i=home&pf_rd_m=A11IL2PNWYJU7H&pf_rd_r=Q4QMC7BGQV9H6QBPZDMB&pf_rd_r=Q4QMC7BGQV9H6QBPZDMB&pf_rd_p=2050f89c-0933-44b3-bba7-332b7d622560","Informatica","bestsellers");
	loadAllAmazon("https://www.amazon.it/gp/bestsellers/kitchen/ref=zg_bs_kitchen_home_all?pf_rd_p=7a264333-0156-4895-9589-1c04cc8a9c64&pf_rd_s=center-1&pf_rd_t=2101&pf_rd_i=home&pf_rd_m=A11IL2PNWYJU7H&pf_rd_r=EWV48EQK1SQKV28JQYBY&pf_rd_r=EWV48EQK1SQKV28JQYBY&pf_rd_p=7a264333-0156-4895-9589-1c04cc8a9c64","Cucina","bestsellers");
	loadAllAmazon("https://www.amazon.it/gp/bestsellers/dvd/ref=zg_bs_dvd_home_all?pf_rd_p=d4b8a019-c427-468d-8b9b-ce6f4b5cb470&pf_rd_s=center-2&pf_rd_t=2101&pf_rd_i=home&pf_rd_m=A11IL2PNWYJU7H&pf_rd_r=6CEHD4AG3FTEJQKYKV3N&pf_rd_r=6CEHD4AG3FTEJQKYKV3N&pf_rd_p=d4b8a019-c427-468d-8b9b-ce6f4b5cb470","Film e TV","bestsellers");
	loadAllAmazon("https://www.amazon.it/gp/bestsellers/music/ref=zg_bs_music_home_all?pf_rd_p=b792cc80-8420-43b5-9e00-1869521862e3&pf_rd_s=center-3&pf_rd_t=2101&pf_rd_i=home&pf_rd_m=A11IL2PNWYJU7H&pf_rd_r=6CEHD4AG3FTEJQKYKV3N&pf_rd_r=6CEHD4AG3FTEJQKYKV3N&pf_rd_p=b792cc80-8420-43b5-9e00-1869521862e3","CD e Vinili","bestsellers");

});

*/


function loadAllAmazon(url, category, type) {
	var json;
	var json2;
	var obj;
	var obj2;
	//alert("qui");
	$.ajax({
		url: "https://api.rainforestapi.com/request?api_key=51A78C7970BF4F28BDDA2A49A1FF833A&type=" + type + "&url=" + url,
		method: "GET",
		success: function(response) {
			json = JSON.stringify(response);
			obj = JSON.parse(json);
			//console.log(json);
			for (var i = 0; i < 10; i++) {

				/*
								var link = obj.bestsellers[i].link;
								var descrizione;
								$.ajax({
									url: "https://api.rainforestapi.com/request?api_key=51A78C7970BF4F28BDDA2A49A1FF833A&type=product&url=" + link,
									method: "GET",
									success: function(response) {
										json2 = JSON.stringify(response);
										obj2 = JSON.parse(json2);
										console.log(json2);
										descrizione = obj2.product.feature_bullets;
									}
								});*/
				//alert(descrizione);
				$.ajax({
					url: "loadAmazon",
					method: "GET",
					data: {
						nome: obj.bestsellers[i].title,
						prezzo: obj.bestsellers[i].price.value,
						urlImg: obj.bestsellers[i].image,
						descrizione: obj.bestsellers[i].title,
						quantita: 5,
						categoria: category
					},
					success: function(response) {
						alert("upload done");
					},
					fail: function(jqXHR, textStatus) {
						alert("Request failed: " + textStatus);
					}
				});
			}
		},
		fail: function(jqXHR, textStatus) {
			alert("Request failed: " + textStatus);
		}
	});
}




function removeFromCart(codice, idVenditore, count) {
	//alert("ciao");
	//alert(idVenditore);
	//alert(count);
	var quantita = $("#" + count).val();
	//alert(quantita);
	//var product = new Prodotto(productCode, productName, productDesc, productPrice, productUrlImg);
	$.ajax({
		url: "removeFromCart",
		method: "GET",
		data: {
			codice: codice,
			idVenditore: idVenditore,
			quantita: quantita
		},
		success: function(response) {
			location.href = "cart";
			//alert("riga"+codice);
		},
		fail: function(jqXHR, textStatus) {
			alert("Request failed: " + textStatus);
		}
	});
}


function showCart() {
	location.href = "cart";
}


function read() {

}



function requestSeller(id) {
	$.ajax({
		url: "showProductsFromSeller",
		method: "GET",
		data: { id: id },
		success: function(response) {
			if (response == "ok")
				location.href = "sellerPage";
		},
		fail: function(jqXHR, textStatus) {
			alert("Request failed: " + textStatus);
		}
	});
}