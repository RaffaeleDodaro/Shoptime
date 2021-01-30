package it.unical.mat.progetto.ingsweb.model;

public class Indirizzo {

	String via, regione, citta, destinatario, numTelefono,numeroTelefonicoDestinatario;
	public String getNumeroTelefonicoDestinatario() {
		return numeroTelefonicoDestinatario;
	}

	public void setNumeroTelefonicoDestinatario(String numeroTelefonicoDestinatario) {
		this.numeroTelefonicoDestinatario = numeroTelefonicoDestinatario;
	}

	int codice, cap;

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

}
