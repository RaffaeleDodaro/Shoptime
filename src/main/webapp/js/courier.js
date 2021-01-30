function getInfo(btn){
	$.ajax({
		url: "information",
		method: "POST",
		data: {id: btn.id},
		success: function(response){
			window.open(response, "_self");
		},
		fail: function( jqXHR, textStatus){
			alert( "Request failed: " + textStatus);
		}
	});
}

function getPackage(){
	$.ajax({
		url: "status",
		method: "GET",
		success: function(response){
			window.open(response, "_self");
		},
		fail: function( jqXHR, textStatus){
			alert( "Request failed: " + textStatus);
		}
	});
}