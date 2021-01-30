package it.unical.mat.progetto.ingsweb.model;

import java.util.List;

public class Prodotto {
	
	String nome;
	int codice;
	String descrizione;
	double prezzo;
	String urlImg;
	List<Recensione> recensioni;
	Venditore venditore;
	int quantita;
	double totale;
	int inOrdine;
	
	public int getInOrdine() {
		return inOrdine;
	}

	public void setInOrdine(int inOrdine) {
		this.inOrdine = inOrdine;
	}

	public Venditore getVenditore() {
		return venditore;
	}

	public void setVenditore(Venditore venditore) {
		this.venditore = venditore;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public String getUrlImg() {
		return urlImg;
	}
	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	

	

}