package it.unical.mat.progetto.ingsweb.persistance.dao;

import java.util.List;

import it.unical.mat.progetto.ingsweb.model.Categoria;
import it.unical.mat.progetto.ingsweb.model.Prodotto;


public interface CategoriaDAO {
	
	public void save(Categoria categoria); // Create
	public Categoria findByPrimaryKey(String nome); // Retrieve
	public List<Categoria> findAll();
	public void update(Categoria categoria); //Update
	public void delete(Categoria categoria); //Delete
	public List<Prodotto> getProductFromCategories(String nome); 
}
