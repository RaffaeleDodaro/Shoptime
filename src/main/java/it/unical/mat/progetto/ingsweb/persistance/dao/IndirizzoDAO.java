package it.unical.mat.progetto.ingsweb.persistance.dao;

import java.util.List;

import it.unical.mat.progetto.ingsweb.model.Indirizzo;


public interface IndirizzoDAO {
	
	public void save(Indirizzo indirizzo); // Create
	public Indirizzo findByPrimaryKey(int id); // Retrieve
	public List<Indirizzo> findAll();
	public void update(Indirizzo indirizzo); // Update
	public void delete(Indirizzo indirizzo, int id); // Delete

	public Indirizzo findByValue(Indirizzo indirizzo);

}
