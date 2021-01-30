package it.unical.mat.progetto.ingsweb.persistance.dao;

import java.util.List;

import it.unical.mat.progetto.ingsweb.model.Corriere;
import it.unical.mat.progetto.ingsweb.model.Log;
import it.unical.mat.progetto.ingsweb.model.Pacco;

public interface LogDAO {

	public void save(Log log, int id_pacco); // Create
	public Log findByPrimaryKey(int id); // Retrieve
	public List<Log> findAll();
	public void update(Log log); //Update
	public void delete(Log log); //Delete

	public Pacco getPackageFromLog(Log log);
	public List<Log> findByTrackingCode(String trackingCode);
	
}