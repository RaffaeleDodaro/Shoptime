package it.unical.mat.progetto.ingsweb.model;

import java.sql.Date;

public class CartaDiCredito {
	
	String numero, intestatario;
	Date scadenza;
	int CVV,utente;
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getIntestatario() {
		return intestatario;
	}
	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}
	public Date getScadenza() {
		return scadenza;
	}
	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
	}
	public int getCVV() {
		return CVV;
	}
	public void setCVV(int cVV) {
		CVV = cVV;
	}
	public int getUtente() {
		return utente;
	}
	public void setUtente(int utente) {
		this.utente = utente;
	}
	

	
}
