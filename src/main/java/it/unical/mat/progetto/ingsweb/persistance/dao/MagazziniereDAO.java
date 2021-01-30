package it.unical.mat.progetto.ingsweb.persistance.dao;

import java.util.List;

import it.unical.mat.progetto.ingsweb.model.Magazziniere;
import it.unical.mat.progetto.ingsweb.model.Ordine;
import it.unical.mat.progetto.ingsweb.model.Pacco;



public interface MagazziniereDAO {
	
	public void save(Magazziniere magazziniere); // Create
	public Magazziniere findByPrimaryKey(int id); // Retrieve
	public List<Magazziniere> findAll();
	public void update(Magazziniere magazziniere); //Update
	public void delete(Magazziniere magazziniere); //Delete
	public List<Pacco> getElaboratedPackagesFromWarehouseWorker(Magazziniere magazziniere);
	public List<Ordine> getElaboratedOrdersFromWarehouseWorker(Magazziniere magazziniere);
	public Magazziniere findByEmail(String email);

	
}
