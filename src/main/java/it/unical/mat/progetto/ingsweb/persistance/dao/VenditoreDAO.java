package it.unical.mat.progetto.ingsweb.persistance.dao;
 
import java.util.List;
 
import it.unical.mat.progetto.ingsweb.model.Prodotto;
import it.unical.mat.progetto.ingsweb.model.Venditore;
 
public interface VenditoreDAO {
 
	public void save(Venditore venditore); // Create
	public Venditore findByPrimaryKey(int l); // Retrieve
	public List<Venditore> findAll();
	public void update(Venditore venditore); //Update
	public void delete(Venditore venditore); //Delete
 
	public void addProdottoVenditore(Prodotto prodotto, Venditore venditore, int quantita);
	public void updateProdottoVenditore(Prodotto prodotto, Venditore venditore, int quantita);
	public void setCatalogoVenditore(Venditore venditore);
	public void setNotificheVenditore(Venditore venditore);
	public void setPriceProductFromSeller(Prodotto prodotto, Venditore venditore);
}
