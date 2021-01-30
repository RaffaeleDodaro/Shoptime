package it.unical.mat.progetto.ingsweb.shoptime;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unical.mat.progetto.ingsweb.model.CartaDiCredito;
import it.unical.mat.progetto.ingsweb.model.Corriere;
import it.unical.mat.progetto.ingsweb.model.Indirizzo;
import it.unical.mat.progetto.ingsweb.model.Magazziniere;
import it.unical.mat.progetto.ingsweb.model.Ordine;
import it.unical.mat.progetto.ingsweb.model.Pacco;
import it.unical.mat.progetto.ingsweb.model.Prodotto;
import it.unical.mat.progetto.ingsweb.model.Utente;
import it.unical.mat.progetto.ingsweb.model.Venditore;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;

public class TestDao {
	public static void main(String[] args) {
		DBManager db = DBManager.getIstance();
		try {
			db.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Utente utente = db.UtenteDAO().findByPrimaryKey(1);
		/*System.out.println("cognome: " + utente.getCognome() + " nome: " + utente.getNome());
		
		db.UtenteDAO().setUserReviews(utente);
		System.out.println("numero recensioni utente: " + utente.getRecensioni().size());
		
		db.UtenteDAO().setUserOrders(utente);
		System.out.println("numero ordini utente: " + utente.getOrdini().size());
		
		System.out.println(utente.getOrdini().get(0).getCodice() + " \n" + "testo recensione: " + utente.getRecensioni().get(0).getTestoRecensione());
		
		db.UtenteDAO().setUserCards(utente);
		for(int i=0;i<utente.getCarte().size();i++)
			System.out.println("numero carte: " + utente.getCarte().get(i).getNumero());
		*/
		//db.UtenteDAO().removeUserCard(utente, utente.getCarte().get(0));
		
		
		/*	COMMENTO PERCHE' SENNO' MI SI RIEMPE IL DB
		System.out.println("\n**********************************");
		System.out.println("*   Salvo una carta di credito     *");
		System.out.println("************************************\n");
		
		
		CartaDiCredito c=new CartaDiCredito();
		c.setNumero("8");
		c.setIntestario("ciccio pasticcio");
		c.setScadenza(Date.valueOf("2020-12-12"));
		c.setCVV(589);
		
		db.cartaDiCreditoDAO().save(c, utente);
		
		db.UtenteDAO().setUserCards(utente);
		
		System.out.println("Ho salvato una carta di credito");
		for(int i=0;i<utente.getCarte().size();i++)
			System.out.println("numero carte: " + utente.getCarte().get(i).getNumero());*/
		
		
		/*
		System.out.println("\n\n**********************************");
		System.out.println("*   Stampo tutte carte di credito    *");
		System.out.println("************************************\n");
		List<CartaDiCredito> carte = db.cartaDiCreditoDAO().findAll();
		for(int i=0;i<carte.size();i++)
			System.out.println("intestatario " + carte.get(i).getIntestario() + "       numero carta: " + carte.get(i).getNumero());
		
		*/
		
		/*COMMENTO PERCHE' SENNO' MI RIMUOVE TUTTE LE CARTE
		System.out.println("\n\n**********************************");
		System.out.println("*   Rimuovo una carta di credito     *");
		System.out.println("************************************\n");
		CartaDiCredito c = carte.get(0);
		db.cartaDiCreditoDAO().delete(c);
		carte = db.cartaDiCreditoDAO().findAll();
		for(int i=0;i<carte.size();i++)
			System.out.println("intestatario " + carte.get(i).getIntestario() + "       numero carta: " + carte.get(i).getNumero());
		System.out.println("Ho eliminato una carta di credito");
		
		
		System.out.println("\n\n**************************************");
		System.out.println("*   AGGIUNGO PRODOTTO			 *");
		System.out.println("**************************************\n");
		Prodotto p = new Prodotto();
		p.setDescrizione("Prodotto di prova");
		p.setPrezzo(120.23);
		p.setUrlImg("http://google.it");
		p.setNome("prova");
		p.setQuantita(100);
		db.prodottoDAO().save(p);
		*/
		/*
		
		System.out.println("\n\n**************************************");
		System.out.println("*  Stampo tutti i prodotti in vendita *");
		System.out.println("**************************************\n");
		List<Prodotto> prodotti = db.prodottoDAO().findAll();
		for(int i=0;i<prodotti.size();i++)
			System.out.println("descrizione " + prodotti.get(i).getDescrizione() + "       prezzo: " + prodotti.get(i).getPrezzo());
		
		
		
		
		System.out.println("\n\n**************************************");
		System.out.println("*  Cerco prodotto in vendita         *");
		System.out.println("**************************************\n");
		Prodotto prodotto=db.prodottoDAO().findByPrimaryKey(4);
		System.out.println("descrizione: " + prodotto.getDescrizione() + "        nome: "+prodotto.getNome());
		
		
		
		System.out.println("\n\n**************************************");
		System.out.println("*  Aggiungo quantita' prodotto in vendita*");
		System.out.println("**************************************\n");
		prodotto.setQuantita(prodotto.getQuantita()+1500);
		db.prodottoDAO().update(prodotto);
		System.out.println("descrizione: " + prodotto.getDescrizione() + "        nome: "+prodotto.getNome() + "        quantita: "+prodotto.getQuantita());
		
		
		
		
		
		System.out.println("\n\n**************************************");
		System.out.println("*  getSellersByProduct*");
		System.out.println("**************************************\n");
		System.out.println("venditore: " + db.prodottoDAO().getSellersFromProduct(prodotto).get(0).getNome());
		*/
		
		/*
		System.out.println("\n\n**************************************");
		System.out.println("*   AGGIUNGO ORDINE	 *");
		System.out.println("**************************************\n");
		Ordine ordine=new Ordine();
		ordine.setCodice(5);
		ordine.setData(Date.valueOf("2020-12-12"));
		ordine.setStato("Elaborazione");
		ordine.setPrezzo(546);

		Indirizzo indirizzo=new Indirizzo();
		indirizzo.setCAP(5155);
		indirizzo.setCodice(1);
		indirizzo.setVia("via fvwdsvwd");
		indirizzo.setRegione("calabria");
		indirizzo.setCitta("cosenza");
		ordine.setIndirizzo(indirizzo);
		db.ordineDAO().save(ordine, utente, indirizzo);
		Ordine o = db.ordineDAO().findByPrimaryKey(1);
		System.out.println(o.getIndirizzo().getRegione());
		*/
		
		/*
		System.out.println("\n\n**************************************");
		System.out.println("*  Stampo tutti gli ordini *");
		System.out.println("**************************************\n");
		List<Ordine> ordini = db.ordineDAO().findAll();
		for(int i=0;i<ordini.size();i++)
			System.out.println("codice " + ordini.get(i).getCodice() + "       prezzo: " + ordini.get(i).getPrezzo());
		
		
		
		
		
		System.out.println("\n\n**************************************");
		System.out.println("*  Cerco ordine         *");
		System.out.println("**************************************\n");
	    ordine=db.ordineDAO().findByPrimaryKey(5);
	 	System.out.println("codice " + ordine.getCodice() + "       prezzo: " + ordine.getPrezzo());
		
		
		
		
		System.out.println("\n\n**************************************");
		System.out.println("*  Cancello ordine         *");
		System.out.println("**************************************\n");
		db.ordineDAO().delete(ordini.get(0));
		
		
		System.out.println("\n\n**************************************");
		System.out.println("*  getUserFromOrder        *");
		System.out.println("**************************************\n");
		Utente u=db.ordineDAO().getUserFromOrder(ordine);
		System.out.println("nome " + u.getNome());
		
		
		
		
		
		
		System.out.println("\n\n**************************************");
		System.out.println("*  setProductsFromOrder        *");
		System.out.println("**************************************\n");
		db.ordineDAO().setProductsFromOrder(ordine);
		List<Prodotto> prodottoFromOrder = ordine.getProdotti();
		for(int i=0;i<prodottoFromOrder.size();i++)
			System.out.println("codice prodotto: " + prodottoFromOrder.get(i).getCodice() + "  dell'ordine: " + ordine.getCodice());
		
		
		
		
		
		//con db aperto su chrome
		System.out.println("\n\n**************************************");
		System.out.println("*  addOrderProduct       *");
		System.out.println("**************************************\n");
		db.ordineDAO().addOrderProduct(prodotto, ordine, 50);
		
		
		
		
		
		System.out.println("\n\n**************************************");
		System.out.println("*  removeOrderProduct       *");
		System.out.println("**************************************\n");
		db.ordineDAO().removeOrderProduct(prodotto, ordine);
		
		
		
		
		System.out.println("\n\n**************************************");
		System.out.println("*  updateOrderProduct       *");
		System.out.println("**************************************\n");
		db.ordineDAO().updateOrderProduct(prodotto, ordine, 15236);*/
		
		
		Venditore v=db.venditoreDAO().findByPrimaryKey(Integer.parseInt("1111111111"));
		//System.out.println(v.getCognome());
		
		/*System.out.println("\n\n**************************************");
		System.out.println("*  Stampo tutti i venditori ordini *");
		System.out.println("**************************************\n");
		List<Venditore> venditori = db.venditoreDAO().findAll();
		for(int i=0;i<venditori.size();i++)
			System.out.println(venditori.get(i).getCognome());*/
		
		db.venditoreDAO().delete(v);
		
		//Prodotto p= db.prodottoDAO().findByPrimaryKey(3);
		//db.venditoreDAO().addProdottoVenditore(p,v,10);
		//db.venditoreDAO().updateProdottoVenditore(p, v, 5);
		
		Pacco pacco=db.paccoDAO().findByPrimaryKey(2);
		/*System.out.println(pacco.getTrackingCode());
		pacco.setCodice(4);
		db.paccoDAO().save(pacco, 1, 1, 1);
		
		System.out.println("\n\n**************************************");
		System.out.println("*  Stampo tutti i pacchi  *");
		System.out.println("**************************************\n");
		List<Pacco> pacchi = db.paccoDAO().findAll();
		for(int i=0;i<pacchi.size();i++)
			System.out.println(pacchi.get(i).getIndirizzo().getCodice());*/
		
		
		
		
		
		//Corriere c= db.paccoDAO().getCourierFromPackage(pacco);
		//System.out.println(c.getCognome());
		
		
		
		//Magazziniere m=db.paccoDAO().getWarehouseWorkerFromPackage(pacco);
		//System.out.println(m.getId());
		
		
		//Ordine ordine=db.paccoDAO().getOrderFromPackage(pacco);
		//System.out.println(ordine.getCodice());
		
		
		
		//db.paccoDAO().updateCourierOfPackage(pacco, 1);
		
		
		
		Magazziniere magazziniere=db.magazziniereDAO().findByPrimaryKey(2);
		//System.out.println(magazziniere.getCognome());
		//magazziniere.setEmail("drago");
		//magazziniere.setId(4);
		//db.magazziniereDAO().save(magazziniere);
		
		
		
		//System.out.println("\n\n**************************************");
		//System.out.println("*  Stampo tutti i corrieri  *");
		//System.out.println("**************************************\n");
		//List<Magazziniere> magazzinieri = db.magazziniereDAO().findAll();
		//for(int i=0;i<magazzinieri.size();i++)
		//	System.out.println(magazzinieri.get(i).getNome());


		//List<Pacco> pacchiElaborati = db.magazziniereDAO().getElaboratedPackagesFromWarehouseWorker(magazziniere);
		//for(int i=0;i<pacchiElaborati.size();i++)
		//		System.out.println(pacchiElaborati.get(i).getCodice());
		
		
		
		

		/*List<Ordine> ordineElaborati = db.magazziniereDAO().getElaboratedOrdersFromWarehouseWorker(magazziniere);
		for(int i=0;i<ordineElaborati.size();i++)
				System.out.println(magazziniere.getId() + " "+"codice ordine: " +  ordineElaborati.get(i).getCodice()+ " prezzo totale: " + ordineElaborati.get(i).getPrezzo());*/
		
		
		
		
		
		Corriere corriere=db.corriereDAO().findByPrimaryKey(1);
		//System.out.println(corriere.getPassword());
		//corriere.setCognome("babidi");
		//corriere.setId(4);
		//db.corriereDAO().save(corriere);
		
		//List<Corriere> corrieri=db.corriereDAO().findAll();
		//for(int i=0;i<corrieri.size();i++)
		//	System.out.println(corrieri.get(i).getEmail());
		
		
		//db.paccoDAO().updateCourierOfPackage(pacco, 2);
	
		//int piva=11111111111;
		//long piva=11111111111;
//		List<Ordine> ordini = new ArrayList<Ordine>();
//		ordini = DBManager.getIstance().ordineDAO().findAllShoptimeOrders();
//		
//		DBManager.getIstance().ordineDAO().setProductsFromOrderShoptime(ordini.get(0));
//		
//		System.out.println(ordini.get(0).getProdotti().get(0).getNome());
//		System.out.println(DBManager.getIstance().prodottoDAO().getShoptimeProductQuantity(5));
//		System.out.println(DBManager.getIstance().logDAO().findByTrackingCode("d4g1u7F0Y5H0z8a0B8T8r7U8g0E0p0").getStato());
	}
}
