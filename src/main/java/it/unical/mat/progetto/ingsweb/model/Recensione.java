package it.unical.mat.progetto.ingsweb.model;

public class Recensione {
	
	String testoRecensione;
	int valutazione;
	Utente utente;
	Prodotto prodotto;
	
	public String getTestoRecensione() {
		return testoRecensione;
	}
	public void setTestoRecensione(String testoRecensione) {
		this.testoRecensione = testoRecensione;
	}
	public int getValutazione() {
		return valutazione;
	}
	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public Prodotto getProdotto() {
		return prodotto;
	}
	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
	
	
}
