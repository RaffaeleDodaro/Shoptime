package it.unical.mat.progetto.ingsweb.persistance.dao;

import java.util.List;

import it.unical.mat.progetto.ingsweb.model.NotificheVenditore;
import it.unical.mat.progetto.ingsweb.model.Prodotto;
import it.unical.mat.progetto.ingsweb.model.Utente;
import it.unical.mat.progetto.ingsweb.model.Venditore;

public interface NotificheVenditoreDAO {
	public void save(NotificheVenditore nv, Venditore venditore); // Create
	public NotificheVenditore findByPrimaryKey(int id, Venditore venditore); // Retrieve
	public List<NotificheVenditore> findAll();
	public void update(Venditore venditore, int nv, boolean letto); //Update
	public void delete(Venditore venditore, int id); //Delete
	
	public int findLastId(Venditore venditore);
}
