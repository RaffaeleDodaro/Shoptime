package it.unical.mat.progetto.ingsweb.persistance.dao;

import java.util.List;

import it.unical.mat.progetto.ingsweb.model.CartaDiCredito;
import it.unical.mat.progetto.ingsweb.model.Indirizzo;
import it.unical.mat.progetto.ingsweb.model.Ordine;
import it.unical.mat.progetto.ingsweb.model.Recensione;
import it.unical.mat.progetto.ingsweb.model.Utente;



public interface UtenteDAO {
	
	public void save(Utente utente,Indirizzo indirizzo); // Create
	public Utente findByPrimaryKey(int id); // Retrieve
	public Utente findByEmail(String email, String password);
	public List<Utente> findAll();
	public void update(Utente utente); //Update
	public void delete(Utente utente); //Delete

	public void addUserCard(Utente utente, CartaDiCredito carta);
	public void addUserAddress(Utente utente, Indirizzo indirizzo);
	public void addUserReview(Utente utente, Recensione recensione);
	public void removeUserCard(Utente utente, CartaDiCredito carta);
	public void removeUserAddress(Utente utente, Indirizzo indirizzo);
	public void removeUserReview(Utente utente, Recensione recensione);
	public void setUserCards(Utente utente);
	public void setUserAddresses(Utente utente);
	public void setUserReviews(Utente utente);
	public void setUserOrders(Utente utente);
	public Utente checkEmail(String email);

}

