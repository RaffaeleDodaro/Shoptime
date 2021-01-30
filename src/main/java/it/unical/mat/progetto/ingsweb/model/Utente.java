package it.unical.mat.progetto.ingsweb.model;

import java.util.List;

public class Utente {
	
	int id;
	String nome, cognome, email, password;
	String numTelefonico, numeroTelefonicoDestinatario;
	List<CartaDiCredito> carte;
	List<Indirizzo> indirizzi;
	Indirizzo predefinito;
	
	
	
	public String getNumeroTelefonicoDestinatario() {
		return numeroTelefonicoDestinatario;
	}
	public void setNumeroTelefonicoDestinatario(String numeroTelefonicoDestinatario) {
		this.numeroTelefonicoDestinatario = numeroTelefonicoDestinatario;
	}
	
	public Indirizzo getPredefinito() {
		return predefinito;
	}
	public void setPredefinito(Indirizzo predefinito) {
		this.predefinito = predefinito;
	}
	Indirizzo indirizzoPredefinito;
	List<Recensione> recensioni;
	List<Ordine> ordini;
	
	public Indirizzo getIndirizzoPredefinito() {
		return indirizzoPredefinito;
	}
	public void setIndirizzoPredefinito(Indirizzo indirizzoPredefinito) {
		this.indirizzoPredefinito = indirizzoPredefinito;
	}
	
	public List<Ordine> getOrdini() {
		return ordini;
	}
	
	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNumTelefonico() {
		return numTelefonico;
	}
	public void setNumTelefonico(String numTelefonico) {
		this.numTelefonico = numTelefonico;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<CartaDiCredito> getCarte() {
		return carte;
	}
	public void setCarte(List<CartaDiCredito> carte) {
		this.carte = carte;
	}

	public List<Indirizzo> getIndirizzi() {
		return indirizzi;
	}
	public void setIndirizzi(List<Indirizzo> indirizzi) {
		this.indirizzi = indirizzi;
	}
	public List<Recensione> getRecensioni() {
		return recensioni;
	}
	public void setRecensioni(List<Recensione> recensioni) {
		this.recensioni = recensioni;
	}	
}