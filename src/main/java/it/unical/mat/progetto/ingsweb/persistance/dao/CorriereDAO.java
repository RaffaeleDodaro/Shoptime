package it.unical.mat.progetto.ingsweb.persistance.dao;

 

import java.util.List;

 

import it.unical.mat.progetto.ingsweb.model.Corriere;
import it.unical.mat.progetto.ingsweb.model.Pacco;

 

 

public interface CorriereDAO {
    
    public void save(Corriere corriere); // Create
    public Corriere findByPrimaryKey(int id); // Retrieve
    public List<Corriere> findAll();
    public void update(Corriere corriere); //Update
    public void delete(Corriere corriere); //Delete
    public List<Pacco> getPackagesFromCourier(Corriere corriere);
    public List<Pacco> getPackagesFromMail(String mail);
    public Corriere findByEmail(String email);
 

}