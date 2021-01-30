/**
 * 


window.addEventListener("load", function() {
	registerEvents();
})

function registerEvents() {
	var accessBtn = document.getElementById("accessBtn");
	accessBtn.addEventListener("click", access);

	var registerBtn = document.getElementById("btnRegister");
	registerBtn.addEventListener("click", register);
}
 */
function Address(via, citta, regione, cap) {
	this.via = via;
	this.citta = citta;
	this.regione = regione;
	this.cap = cap;
}

function Account(email, password) {
	this.email = email;
	this.password = password;
}

function access() {
	//alert("sono dentro");
	var email = $('#email').val();
	var password = $('#password').val();

	if (email != "" && password != "") {
		var account = new Account(email, password);
		$.ajax({
			url: "login",
			method: "POST",
			data: JSON.stringify(account),
			contentType: "application/json",
			success: function(response) {
				if (response == "login effettuato correttamente") {
					//$('#accessBtn').text("loggato correttamente");
					location.reload();
				}
				else if (response == "utente non esiste") 
				{ 
					$('#accessBtn').text("Email e/o password sbagliata"); 
				}
			},
			fail: function(jqXHR, textStatus) {
				alert("Request failed: " + textStatus);
			}
		});
	}
	if (email == "") {
		document.getElementById("insertEmail").style.color = "red";
		$('#insertEmail').text("Inserisci email");
	}
	if (password == "") {
		document.getElementById("insertPassword").style.color = "red";
		$('#insertPassword').text("Inserisci password");
	}
}

function hasNumber(myString) {
	return /\d/.test(myString);
}

function registerUserJS() {
	//alert("ciao");
	var nome = $('#nome').val();
	var cognome = $('#cognome').val();
	var password = $('#passwordRegister').val();
	var email = $('#emailRegister').val();
	var numeroTelefono = $('#numeroTelefono').val();
	var numeroTelefonicoDestinatario= $('#numeroTelefonicoDestinatario').val();
	var nomeDestinatario=$('#nomeDestinatario').val();
	
	var via = $('#form-address').val();
	var regione = $('#form-address2').val();
	var citta = $('#form-city').val();
	var cap = $('#form-zip').val();
	//alert(numeroTelefonicoDestinatario);
	var intestatario = $('#intestatario').val();
	var numero = $('#numero').val();
	var scadenza = $('#scadenza').val();
	//alert(scadenza);
	var cvv = $('#cvv').val();

	//alert(password + " cifre carta: " + numero.length);

	if (nome != "" && cognome != "" && password != "" && email != "" && numeroTelefono != ""
		&& via != "" && intestatario != "" && numero != "" && cvv != "" && (!(hasNumber(nome))
			&& !(hasNumber(cognome)) && !(hasNumber(intestatario))) && (numero.length == 16 || numero.length == 30)
		&& cvv.length == 3 && numeroTelefonicoDestinatario.length <= 10) {

		//alert("non devo entrare qui");

		$.ajax({
			url: "registerUser",
			method: "POST",
			data: {
				nome: nome,
				cognome: cognome,
				password: password,
				email: email,
				numeroTelefono: numeroTelefono,
				numeroTelefonicoDestinatario: numeroTelefonicoDestinatario,
				nomeDestinatario:nomeDestinatario,
				via: via,
				regione: regione,
				citta: citta,
				cap: cap,
				intestatario: intestatario,
				numero: numero,
				scadenza: scadenza,
				cvv: cvv
			},
			dataType: "text",
			success: function(response) {
				if (response == "ok") {
					alert("Registrato correttamente");
					location.href = "/";
				}
				else if (response == "utente presente") {
					alert("Utente gia' registrato");
				}
			},
			error: function(jqXHR, textStatus) {
				alert("Request failed: " + textStatus);
			}
		});

	}
	if (nome == "") {
		document.getElementById("labelNome").style.color = "red";
		$('#labelNome').text("Inserisci il nome");
	}

	if (email == "") {
		document.getElementById("labelEmail").style.color = "red";
		$('#labelEmail').text("Inserisci email");
	}

	if (password == "") {
		document.getElementById("labelPassword").style.color = "red";
		$('#labelPassword').text("Inserisci password");
	}

	if (cognome == "") {
		document.getElementById("labelCognome").style.color = "red";
		$('#labelCognome').text("Inserisci il cognome");
	}

	if (numeroTelefono == "") {
		document.getElementById("labelNumeroTelefono").style.color = "red";
		$('#labelNumeroTelefono').text("Inserisci il numero di telefono");
	}

	if (numeroTelefonicoDestinatario == "") {
		document.getElementById("labelTelefonoDestinatariolabelTelefonoDestinatario").style.color = "red";
		$('#labelTelefonoDestinatario').text("Inserisci il numero di telefono del destinatario");
	}

	if (via == "") {
		document.getElementById("labelVia").style.color = "red";
		$('#labelVia').text("Inserisci la via");
	}

	if (intestatario == "") {
		document.getElementById("labelIntestatario").style.color = "red";
		$('#labelIntestatario').text("Inserisci il numero di telefono");
	}
	if (numero == "") {
		document.getElementById("labelNumero").style.color = "red";
		$('#labelNumero').text("Inserisci il numero di telefono");
	}

	if (scadenza == "") {
		document.getElementById("labelScadenza").style.color = "red";
		$('#labelScadenza').text("Inserisci il numero di telefono");
	}


	if (cvv == "") {
		document.getElementById("labelCvv").style.color = "red";
		$('#labelCvv').text("Inserisci il numero di telefono");
	}

	if (hasNumber(nome)) {
		document.getElementById("labelNome").style.color = "red";
		$('#labelNome').text("Il nome non deve contenere numeri");
	}

	if (hasNumber(cognome)) {
		document.getElementById("labelCognome").style.color = "red";
		$('#labelCognome').text("Il cognome non deve contenere numeri");
	}

	if (hasNumber(intestatario)) {
		document.getElementById("labelIntestatario").style.color = "red";
		$('#labelIntestatario').text("L'intestatario non deve contenere numeri");
	}

	if (numero.length != 16 && numero.length != 30) {
		alert("Il numero delle cifre della carta deve essere uguale a 16 o uguale a 30");
	}

	if (cvv.length != 3) {
		alert("Il numero delle cifre del CVV deve essere uguale a 3");
	}

	if (numeroTelefono.length > 10) {
		alert("Il numero delle cifre del numero di telefono deve essere meno di 10");
	}
}


function logout(){
	$.ajax({
		url:"logout",
		method:"GET",
		success:function(response){
			location.href="/";
		},
		fail: function(jqXHR, textStatus) {
			alert("Request failed: " + textStatus);
		}		
	});
}