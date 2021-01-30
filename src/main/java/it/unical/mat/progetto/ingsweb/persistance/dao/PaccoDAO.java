package it.unical.mat.progetto.ingsweb.persistance.dao;

import java.util.List;

import it.unical.mat.progetto.ingsweb.model.Corriere;
import it.unical.mat.progetto.ingsweb.model.Log;
import it.unical.mat.progetto.ingsweb.model.Magazziniere;
import it.unical.mat.progetto.ingsweb.model.Ordine;
import it.unical.mat.progetto.ingsweb.model.Pacco;


public interface PaccoDAO {
	
	public void save(Pacco pacco, int codice_ordine, int id_magazziniere, int codice_indirizzo); // Create
	public Pacco findByPrimaryKey(int id); // Retrieve
	public Pacco findByOrder(Ordine ordine);
	public List<Pacco> findAll();
	public void update(Pacco pacco); //Update
	public void delete(Pacco pacco); //Delete

	public Corriere getCourierFromPackage(Pacco pacco);
	public Magazziniere getWarehouseWorkerFromPackage(Pacco pacco);
	public Ordine getOrderFromPackage(Pacco pacco);
	public List<Log> getLogsOfPackage(Pacco pacco);
	public void updateCourierOfPackage(Pacco pacco, int id_corriere);
	
}
