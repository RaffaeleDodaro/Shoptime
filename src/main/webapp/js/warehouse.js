$(document).ready(function(){
  $('#modal-avviso').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget)
  var idProdotto = button.data("prodotto")
  var nomeProdotto = button.data("nomeprodotto")
  var modal = $(this)
  modal.find('.modal-title').text("Messaggio per il prodotto:\n" + nomeProdotto)
  modal.find('.modal-body input').val(idProdotto)
})
  
})

function getOrdine(btn){
	$.ajax({
		url: "orderDetails",
		method: "POST",
		data: {codiceOrdine : btn.id},
		success: function(response){
			window.open(response, "_self");
		},
		fail: function( jqXHR, textStatus){
			alert( "Request failed: " + textStatus);
		}
	});
}

function confirmOrder(btn){
	$.ajax({
		url: "confirmOrder",
		method: "POST",
		data: {codiceOrdine : btn.id},
		success: function(response){
			window.open(response, "_self");
		},
		fail: function( jqXHR, textStatus){
			alert( "Request failed: " + textStatus);
		}
	});
}