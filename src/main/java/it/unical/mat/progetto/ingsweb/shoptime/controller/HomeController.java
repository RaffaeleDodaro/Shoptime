package it.unical.mat.progetto.ingsweb.shoptime.controller;

import java.sql.Date;
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
public class HomeController {

	@GetMapping("/")
	public String home(HttpSession session, Model model) {
		System.out.println("SONO QUI");
		List<Prodotto> cart = new ArrayList<Prodotto>();
		if (session.getAttribute("cart") == null) {
			double cartPrice = 0;
			session.setAttribute("cart", cart);
			session.setAttribute("cartSize", 0);
			session.setAttribute("cartPrice", tronca(cartPrice));
		}
		//if (session.getAttribute("prodotti") == null) {
		loadCatalog(session);
		//}
		return "index";
	}
	public double tronca(double prezzo)
	{
		prezzo=prezzo*100;
		prezzo=Math.floor(prezzo);
		prezzo= prezzo/100;
		return prezzo;
	}
	public void loadCatalog(HttpSession session) {

		if (session.getAttribute("prodottiInformatica") == null) {
			List<Prodotto> prodottiInformatica = findProductCategory("Informatica");
			session.setAttribute("prodottiInformatica", prodottiInformatica);
		}	
		if (session.getAttribute("prodottiVideogiochi") == null) {
			List<Prodotto> prodottiVideogiochi = findProductCategory("Videogiochi");
			session.setAttribute("prodottiVideogiochi", prodottiVideogiochi);
		}	
		if (session.getAttribute("prodottiFilm") == null) {
			List<Prodotto> prodottiFilm = findProductCategory("Film e TV");
			session.setAttribute("prodottiFilm", prodottiFilm);
		}	
		if (session.getAttribute("prodottiCucina") == null) { 
			List<Prodotto> prodottiCucina = findProductCategory("Cucina");
			session.setAttribute("prodottiCucina", prodottiCucina);
		}	
	}
	
	public List<Prodotto> findProductCategory(String categoria)
	{
		List<Prodotto> prodotti = DBManager.getIstance().categoriaDAO().getProductFromCategories(categoria);

		List<Prodotto> prodottiVenditore = new ArrayList<Prodotto>();

		for (Prodotto prodotto : prodotti) {
			List<Venditore> venditoriProdotto = DBManager.getIstance().prodottoDAO().getSellersFromProduct(prodotto);
			
			for (Venditore venditore : venditoriProdotto) {
				Prodotto p = new Prodotto();
				p.setCodice(prodotto.getCodice());
				p.setDescrizione(prodotto.getDescrizione());
				p.setNome(prodotto.getNome());
				p.setPrezzo(tronca(DBManager.getIstance().prodottoDAO().getPriceProductFromSeller(venditore, prodotto)));
				p.setQuantita(prodotto.getQuantita());
				p.setTotale(prodotto.getTotale());
				p.setUrlImg(prodotto.getUrlImg());
				p.setVenditore(venditore);
				System.out.println(p.getNome() + ": " + p.getVenditore().getNome());
				prodottiVenditore.add(p);
			}
		}
		return prodottiVenditore;
	}
	
	
	
	
	@GetMapping("read")
	public void read(HttpSession session, @RequestParam int idNotifica) {
		Venditore venditore = (Venditore) session.getAttribute("seller"); 
		DBManager.getIstance().notificheVenditoreDAO().update(venditore, idNotifica, true);
		System.out.println("HO LETTO LA NOTIIFCA" + idNotifica + " di " + venditore.getNome());
	}
	
	// carica prodotti amazon
	@GetMapping("loadAmazon")
	public String loadAmazon(String nome, float prezzo, String urlImg, String descrizione, int quantita,
			String categoria) {
		// System.out.println("loadAmazon");
		Prodotto prodotto = new Prodotto();
		prodotto.setDescrizione(descrizione);
		prodotto.setNome(nome);
		prodotto.setPrezzo(prezzo);
		prodotto.setUrlImg(urlImg);

		/*
		 * System.out.println(prodotto.getNome() + " " + prodotto.getDescrizione() + " "
		 * + prodotto.getPrezzo() + " " + prodotto.getUrlImg());
		 */
		DBManager.getIstance().prodottoDAO().save(prodotto, 1, quantita, categoria);

		return "/";
	}
	
}