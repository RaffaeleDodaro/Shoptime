package it.unical.mat.progetto.ingsweb.model;
 
import java.util.List;
 
public class Venditore extends Utente {
 
	String partitaIva;
	List<Prodotto> catalogoProdotti;
	List<NotificheVenditore> notifiche;
 
 
	public List<NotificheVenditore> getNotifiche() {
		return notifiche;
	}
	public void setNotifiche(List<NotificheVenditore> notifiche) {
		this.notifiche = notifiche;
	}
	public String getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(String iva) {
		this.partitaIva = iva;
	}
	public List<Prodotto> getCatalogoProdotti() {
		return catalogoProdotti;
	}
	public void setCatalogoProdotti(List<Prodotto> catalogoProdotti) {
		this.catalogoProdotti = catalogoProdotti;
	}
	public void setDatiUtente(Utente utente) {
		setId(utente.getId());
		setNome(utente.getNome());
		setCognome(utente.getCognome());
		setEmail(utente.getEmail());
		setPassword(utente.getPassword());
	}
}