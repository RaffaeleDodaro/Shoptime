package it.unical.mat.progetto.ingsweb.persistance.dao;

import java.sql.SQLException;
import java.util.List;

import it.unical.mat.progetto.ingsweb.model.Prodotto;
import it.unical.mat.progetto.ingsweb.model.Recensione;
import it.unical.mat.progetto.ingsweb.model.Utente;

public interface RecensioneDAO {
	
	public void save(Recensione recensione) throws SQLException; // Create
	public Recensione findByPrimaryKey(int idUtente, int idProdotto); // Retrieve
	public List<Recensione> findAll();
	public void delete(Recensione recensione); //Delete
	void updateTesto(Recensione recensione, String testo);
	void updateValutazione(Recensione recensione, byte valutazione);
	public List<Recensione> findByUser(Utente utente);
	public List<Recensione> findByProduct(Prodotto prodotto);

}