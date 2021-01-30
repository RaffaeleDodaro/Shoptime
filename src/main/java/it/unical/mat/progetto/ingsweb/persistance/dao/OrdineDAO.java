package it.unical.mat.progetto.ingsweb.persistance.dao;

import java.util.List;

import it.unical.mat.progetto.ingsweb.model.CartaDiCredito;
import it.unical.mat.progetto.ingsweb.model.Indirizzo;
import it.unical.mat.progetto.ingsweb.model.Ordine;
import it.unical.mat.progetto.ingsweb.model.Prodotto;
import it.unical.mat.progetto.ingsweb.model.Utente;

public interface OrdineDAO {

	public void save(Ordine ordine, Utente utente, Indirizzo indirizzo, CartaDiCredito carta, boolean shoptime); // Create
	public Ordine findByPrimaryKey(int id); // Retrieve
	public List<Ordine> findAll();
	public void update(Ordine ordine); // Update
	public void delete(Ordine ordine); // Delete
	public Utente getUserFromOrder(Ordine ordine);
	public void setProductsFromOrder(Ordine ordine);
	public void addOrderProduct(Prodotto prodotto, Ordine ordine, boolean shoptime);
	public void removeOrderProduct(Prodotto prodotto, Ordine ordine);
	public void updateOrderProduct(Prodotto prodotto, Ordine ordine, int quantita);
	//List<Ordine> findByPrimaryKeyAll(int id);
	public List<Ordine> findAllOrdersShipped();
	public List<Ordine> findAllOrdersProcessed();
	public List<Ordine> findAllOrdersToBeProcessed();
	public List<Ordine> findAllShoptimeOrders();
	public int getMaxCode();
	public void updateOrderProductMessage(int codice, int idProdotto, String message);
	public void updateOrderState(Ordine ordine, String stato);
	public Ordine findByPrimaryKeyShoptime(int id);
	public void setProductsFromOrderShoptime(Ordine ordine);
	public String gerOrderTracking(Ordine ordine);
}