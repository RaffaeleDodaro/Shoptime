/**
 * 
 */

function confirmOrder() {
	/*
	var quantitaProdotto;
	for(var i=0;i<10;i++)
		quantitaProdotto=$(".qty") qty${prodotto.quantita}*/
	$.ajax({
		url: "confirmPage",
		method: "GET",
		success: function(response) {
			//alert("qui");
			if (response == "confirmPage") {
				//alert("qui");
				location.href = "finalConfirmPage";
			}
			else {
				alert("non loggato");
			}
		},
		fail: function(jqXHR, textStatus) {
			alert("Request failed: " + textStatus);
		}
	});
}

function createOrder() {
	
	var addressId = $("#addressForm input[type='radio']:checked").attr('id');
	
	//if(document.getElementById(""))
	
	
	//alert(addressId);
	var cardNumber = $("#cardForm input[type='radio']:checked").attr('id');
	//alert(cardNumber);
	$.ajax({
		url: "createOrder",
		method: "GET",
		data: { 
			numeroCarta: cardNumber, 
			idIndirizzo: addressId 
			},
		success: function(response) {
			location.href = "userPage";
		},
		fail: function(jqXHR, textStatus) {
			alert("Request failed: " + textStatus);
		}
	});
}