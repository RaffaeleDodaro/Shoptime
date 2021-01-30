package it.unical.mat.progetto.ingsweb.persistance.dao;

import java.util.List;

import it.unical.mat.progetto.ingsweb.model.Prodotto;
import it.unical.mat.progetto.ingsweb.model.Venditore;

public interface ProdottoDAO {
	
	public void save(Prodotto prodotto, int venditore, int quantity, String categoria); // Create
	public Prodotto findByPrimaryKey(long id); // Retrieve
	public List<Prodotto> findAll();
	public void update(Prodotto prodotto); //Update
	public void delete(Prodotto prodotto); //Delete

	public List<Venditore> getSellersFromProduct(Prodotto prodotto);
	public Prodotto findByName(String name);
	void updateShoptimeProductQuantity(int codiceProdotto, int quantita);
	int getShoptimeProductQuantity(int codiceProdotto);
	public List<Prodotto> getProductsFromSeller(int id);
	double getPriceProductFromSeller(Venditore venditore, Prodotto prodotto);
	
}