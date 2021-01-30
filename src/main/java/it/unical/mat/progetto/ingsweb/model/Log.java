package it.unical.mat.progetto.ingsweb.model;

import java.sql.Date;

public class Log {
	
	int codice;
	Date data;
	String  stato, posizione, descrizione=null;
	int codicePacco;
	
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getPosizione() {
		return posizione;
	}
	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}
	public int getCodicePacco() {
		return codicePacco;
	}
	public void setCodicePacco(int codicePacco) {
		this.codicePacco = codicePacco;
	}
	
	
	

}
