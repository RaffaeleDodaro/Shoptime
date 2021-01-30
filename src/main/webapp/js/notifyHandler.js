/**
 * 
 */

function read(idNotifica){
	//alert("ciao");
	$.ajax({
		url: "read",
		method: "GET",
		data: {idNotifica:idNotifica},
		success: function(response) {
		
		},
		function(jqXHR, textStatus) {
			alert("Request failed: " + textStatus);
		}		
	});
}