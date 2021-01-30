package it.unical.mat.progetto.ingsweb.model;

import java.util.List;

public class Categoria {
	
	String nome;
	List<Prodotto> prodotti;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Prodotto> getProdotti() {
		return prodotti;
	}
	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

}
