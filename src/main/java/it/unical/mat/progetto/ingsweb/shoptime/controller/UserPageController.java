package it.unical.mat.progetto.ingsweb.shoptime.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.unical.mat.progetto.ingsweb.model.CartaDiCredito;
import it.unical.mat.progetto.ingsweb.model.Indirizzo;
import it.unical.mat.progetto.ingsweb.model.NotificheVenditore;
import it.unical.mat.progetto.ingsweb.model.Ordine;
import it.unical.mat.progetto.ingsweb.model.Prodotto;
import it.unical.mat.progetto.ingsweb.model.Recensione;
import it.unical.mat.progetto.ingsweb.model.Utente;
import it.unical.mat.progetto.ingsweb.model.Venditore;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;

@Controller
public class UserPageController {

	@GetMapping("addReview")
	@ResponseBody
	public String addReview(HttpSession session, @RequestParam String testoRecensione, @RequestParam int valutazione) {
		Utente utente = (Utente) session.getAttribute("user");
		if(utente ==null)
			return "non loggato";
		
		Prodotto prodotto = (Prodotto) session.getAttribute("product");
		Recensione recensione = new Recensione();
		recensione.setProdotto(prodotto);
		recensione.setTestoRecensione(testoRecensione);
		recensione.setUtente(utente);
		recensione.setValutazione(valutazione);
		List<Recensione> recensioni = (List<Recensione>) session.getAttribute("reviews");
	    if(recensioni == null) {
	      recensioni = new ArrayList<Recensione>();
	    }
	    try {
	      DBManager.getIstance().recensioneDAO().save(recensione);
	    }catch(Exception e) {
	      return "errore";
	    }
	    recensioni.add(recensione);
	    session.setAttribute("reviews", recensioni);
	    return "ok";
	}
	
	@GetMapping("userPage")
	public String userPage(HttpSession session) {
		Utente utente = (Utente) session.getAttribute("user");
		DBManager.getIstance().UtenteDAO().setUserOrders(utente);
		for (Ordine o : utente.getOrdini()) {
			/*for (Prodotto p : o.getProdotti()) {
				System.out.println(p.getNome() + " " + p.getPrezzo());
			}*/
			String tracking = DBManager.getIstance().ordineDAO().gerOrderTracking(o);
			if(!tracking.equals("")) {
				o.setTracking(tracking);
			}
		}
		DBManager.getIstance().UtenteDAO().setUserCards(utente);
		DBManager.getIstance().UtenteDAO().setUserReviews(utente);
		DBManager.getIstance().UtenteDAO().setUserAddresses(utente);
		session.setAttribute("userReviews", utente.getRecensioni());

		Collections.reverse(utente.getOrdini());
		session.setAttribute("userOrders", utente.getOrdini());

		System.out.println(utente.getOrdini().size());

		session.setAttribute("userAddresses", utente.getIndirizzi());

		session.setAttribute("userCards", utente.getCarte());
		// boolean isSeller = false;
		Venditore venditore = DBManager.getIstance().venditoreDAO().findByPrimaryKey(utente.getId());
		if (venditore != null) {
			System.out.println("SONO IL VENDITORE  " + venditore.getNome());
			// isSeller = true;
			DBManager.getIstance().venditoreDAO().setCatalogoVenditore(venditore);
			session.setAttribute("sellerCatalog", venditore.getCatalogoProdotti());
		}
		session.setAttribute("seller", venditore);

		return "userPage";
	}

	// VENDITA PRODOTTO
	@GetMapping("sell")
	public String sell(HttpSession session, String nome, float prezzo, String urlImg, String descrizione, int quantita,
			String categoria) {
		// System.out.println("sell");
		Prodotto prodotto = new Prodotto();
		prodotto.setDescrizione(descrizione);
		prodotto.setNome(nome);
		prodotto.setPrezzo(prezzo);
		prodotto.setUrlImg(urlImg);
		Venditore venditore = (Venditore) session.getAttribute("seller");
		prodotto.setVenditore(venditore);
		//List<Prodotto> prodotti = (List<Prodotto>) session.getAttribute("prodotti");
		//prodotti.add(prodotto);
		prodotto.getNome();
		System.out.println(categoria);
		List<Prodotto> prodotti = (List<Prodotto>) session.getAttribute("prodotti"+categoria);
		System.out.println(prodotto.getNome());
		session.setAttribute("prodotti"+categoria, prodotti);
		Integer id = (Integer) session.getAttribute("id");
		System.out.println(id);
		//System.out.println(categoria + " categoria sell");
		
		if(!(categoria.equalsIgnoreCase("Film")))
			DBManager.getIstance().prodottoDAO().save(prodotto, id, quantita, categoria);
		else
		{
			System.out.println("qui");
			DBManager.getIstance().prodottoDAO().save(prodotto, id, quantita, "Film e TV");
		}
		
		prodotto = DBManager.getIstance().prodottoDAO().findByName(prodotto.getNome());
		prodotto.setVenditore(venditore);
		prodotto.setPrezzo(prezzo);
		prodotti.add(prodotto);
		System.out.println(prodotto.getNome());
		System.out.println(categoria);
		session.setAttribute("prodotti"+categoria, prodotti);
		return "userPage";
	}

	@GetMapping("sellProduct")
	public String sellProduct(HttpSession session) {
		return "sellProduct";
	}
	// FINE VENDITA DI PRODOTTO

	// INIZIO DETTAGLI ORDINE
	@PostMapping("seeDetailsOrder")
	public String detailsOrder(HttpSession session, @RequestParam int id) {
		Ordine o = DBManager.getIstance().ordineDAO().findByPrimaryKey(id);
		session.setAttribute("ordine", o);

		// Indirizzo indirizzo =
		// DBManager.getIstance().indirizzoDAO().findByPrimaryKey(o.getIndirizzo().getCodice());

		session.setAttribute("indirizzo", o.getIndirizzo());

		session.setAttribute("carta", o.getCartaDiCredito().getNumero());

		// session.setAttribute("carta", o.getCartaDiCredito());

		return "detailsOrder";
	}

	@GetMapping("detailsOrder")
	public String detailsOrder() {
		return "detailsOrder";
	}

	// fine DETTAGLI ORDINE

	// INIZIO AGGIUNGI CARTA
	@PostMapping("addCard")
	@ResponseBody
	public String addCard(HttpSession session, @RequestParam String intestatario, @RequestParam String numero,
			@RequestParam String scadenza, @RequestParam Integer cvv) {
		
		Utente utente= (Utente) session.getAttribute("user");
		

		System.out.println("qui");
		Date date = Date.valueOf(scadenza + "-01");
		CartaDiCredito cdc = DBManager.getIstance().cartaDiCreditoDAO().findByPrimaryKey(numero);

		if (cdc == null) {
			cdc = new CartaDiCredito();
			cdc.setCVV(cvv);
			cdc.setIntestatario(intestatario);
			cdc.setNumero(numero);
			cdc.setScadenza(date);
			int idUser = (Integer) session.getAttribute("id");
			cdc.setUtente(idUser);
			//utente = DBManager.getIstance().UtenteDAO().findByPrimaryKey((Integer) session.getAttribute("id"));
			utente = (Utente) session.getAttribute("user");
			DBManager.getIstance().cartaDiCreditoDAO().save(cdc,utente, intestatario, true);
			return "ok";
		}
		//utente = DBManager.getIstance().UtenteDAO().findByPrimaryKey((Integer) session.getAttribute("id"));
		DBManager.getIstance().cartaDiCreditoDAO().save(cdc, utente, intestatario, true);
		DBManager.getIstance().UtenteDAO().setUserCards(utente);
		session.setAttribute("userCards", utente.getCarte());
		return "ok";
	}
	
	@PostMapping("addIva")
	@ResponseBody
	public String addSeller(HttpSession session, @RequestParam String iva) {
		Utente utente = (Utente) session.getAttribute("user");
		System.out.println(utente.getId());
		Venditore venditore = new Venditore();
		venditore.setDatiUtente(utente);
		venditore.setPartitaIva(iva);
		DBManager.getIstance().venditoreDAO().save(venditore);
		session.setAttribute("seller", venditore);
		return "ok";
	}

	// Rimuvoi carta da profilo
	@GetMapping("removeCard")
	@ResponseBody
	public String removeCard(HttpSession session, @RequestParam String card) {
		int idUtente = (Integer) session.getAttribute("id");
		System.out.println(idUtente + " idUtente");
		CartaDiCredito cdc = DBManager.getIstance().cartaDiCreditoDAO().findByPrimaryKey(card);
		DBManager.getIstance().cartaDiCreditoDAO().delete(cdc, idUtente);

		return "ok";
	}
	// fine RIMUOVI AGGIUNGI CARTA

	// INIZIO AGGIUNGI INDIRIZZO
	@PostMapping("addAddress")
	@ResponseBody
	public String registerUser(HttpSession session, @RequestParam String nomeDestinatario,
			@RequestParam String telefonoDestinatario, @RequestParam String via, @RequestParam String regione,
			@RequestParam String citta, @RequestParam Integer cap) {
		Indirizzo indirizzo = new Indirizzo();
		indirizzo.setCap(cap);
		indirizzo.setCitta(citta);
		indirizzo.setRegione(regione);
		indirizzo.setVia(via);

		Utente utente= (Utente) session.getAttribute("user");		
		
		
		if (nomeDestinatario == null || nomeDestinatario == "")
			nomeDestinatario = utente.getNome() + " " + utente.getCognome();

		if (telefonoDestinatario == null || telefonoDestinatario == "")
			telefonoDestinatario = utente.getNumTelefonico();

		indirizzo.setDestinatario(nomeDestinatario);
		indirizzo.setNumTelefono(telefonoDestinatario);

		List<Indirizzo> indirizzi = DBManager.getIstance().indirizzoDAO().findAll();
		Indirizzo codeLastElement = indirizzi.get(indirizzi.size() - 1);
		indirizzo.setCodice(codeLastElement.getCodice() + 1);

		int idUser = (Integer) session.getAttribute("id");
		//utente = DBManager.getIstance().UtenteDAO().findByPrimaryKey(idUser);

		Indirizzo i = DBManager.getIstance().indirizzoDAO().findByValue(indirizzo);
		if (i == null) {
			System.out.println(indirizzo.getCodice());
			DBManager.getIstance().indirizzoDAO().save(indirizzo);
			DBManager.getIstance().UtenteDAO().addUserAddress(utente, indirizzo);
		} else {
			DBManager.getIstance().UtenteDAO().addUserAddress(utente, i);
		}
		DBManager.getIstance().UtenteDAO().setUserAddresses(utente);
		session.setAttribute("userAddresses", utente.getIndirizzi());
		return "ok";
	}

	// Rimuvoi indirizzo da profilo
	@GetMapping("removeAddress")
	@ResponseBody
	public String removeAddress(HttpSession session, @RequestParam Integer address) {
		int idUtente = (Integer) session.getAttribute("id");
		System.out.println(idUtente + "idUtente");
		Indirizzo indirizzo = DBManager.getIstance().indirizzoDAO().findByPrimaryKey(address);
		DBManager.getIstance().indirizzoDAO().delete(indirizzo, idUtente);

		return "ok";
	}
	// fine rimuovi carta*/
	// RIMUOVI AGGIUNGI INDIRIZZO

}
