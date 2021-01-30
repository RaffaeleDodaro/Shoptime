package it.unical.mat.progetto.ingsweb.persistance.dao;

import java.util.List;

import it.unical.mat.progetto.ingsweb.model.CartaDiCredito;
import it.unical.mat.progetto.ingsweb.model.Utente;



public interface CartaDiCreditoDAO {
	
	public void save(CartaDiCredito cartaDiCredito, Utente utente, String intestatario, boolean newCard); // Create
	public CartaDiCredito findByPrimaryKey(String numeroCarta); // Retrieve
	public List<CartaDiCredito> findAll();
	public void update(CartaDiCredito cartaDiCredito); //Update
	public void delete(CartaDiCredito cartaDiCredito, int id); //Delete
}