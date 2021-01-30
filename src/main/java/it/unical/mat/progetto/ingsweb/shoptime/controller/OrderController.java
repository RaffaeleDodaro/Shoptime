package it.unical.mat.progetto.ingsweb.shoptime.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unical.mat.progetto.ingsweb.model.CartaDiCredito;
import it.unical.mat.progetto.ingsweb.model.Indirizzo;
import it.unical.mat.progetto.ingsweb.model.NotificheVenditore;
import it.unical.mat.progetto.ingsweb.model.Ordine;
import it.unical.mat.progetto.ingsweb.model.Prodotto;
import it.unical.mat.progetto.ingsweb.model.Recensione;
import it.unical.mat.progetto.ingsweb.model.Utente;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;

@Controller
public class OrderController {

	@GetMapping("productPageReceivedRequestByAJAX")
	public String productPage(HttpSession session, @RequestParam Integer idProduct, @RequestParam boolean addCart,
			@RequestParam int quantita, @RequestParam Integer idVenditore) {
		System.out.println(idVenditore);
		Prodotto prodotto = DBManager.getIstance().prodottoDAO().findByPrimaryKey(idProduct);
		prodotto.setVenditore(DBManager.getIstance().venditoreDAO().findByPrimaryKey(idVenditore));
		DBManager.getIstance().venditoreDAO().setPriceProductFromSeller(prodotto, prodotto.getVenditore());
		
		
		
		System.out.println(prodotto.getVenditore().getNome());
		session.setAttribute("productName", prodotto.getNome());
		session.setAttribute("productDescription", prodotto.getDescrizione());
		session.setAttribute("productPrice", prodotto.getPrezzo());
		session.setAttribute("urlImg", prodotto.getUrlImg());
		session.setAttribute("product", prodotto);
		List<Recensione> recensioni = DBManager.getIstance().recensioneDAO().findByProduct(prodotto);
		session.setAttribute("reviews", recensioni);
		// putInCart(session, prodotto);
		if (addCart) {
			System.out.println("AGGIUNTO AL CARRELLO");
			putInCart(session, prodotto, quantita);
		}
		// System.out.println(session.getAttribute("productName") + " SONO IN
		// SHOWPRODUCTPAGE" );
		return "productPage";
	}
	
	@GetMapping("confirmPage")
	@ResponseBody
	public String confirmPage(HttpSession session) {
		// System.out.println("qui");
		// System.out.println((String)session.getAttribute("user"));
		if (session.getAttribute("user") == null) {
			return "non loggato";
		}

		Utente utente = (Utente) session.getAttribute("user");
		DBManager.getIstance().UtenteDAO().setUserAddresses(utente);
		DBManager.getIstance().UtenteDAO().setUserReviews(utente);
		DBManager.getIstance().UtenteDAO().setUserCards(utente);
		session.setAttribute("user", utente);
		session.setAttribute("userCards", utente.getCarte());
		return "confirmPage";
	}
	
	@GetMapping("finalConfirmPage")
	public String confirmPage() {
		return "confirmPage";
	}
	
	@GetMapping("productPage")
	public String showProduct(HttpSession session) {
		System.out.println("productPage");
		return "productPage";
	}
	
	public void putInCart(HttpSession session, Prodotto prodotto, int quantita) {
		if (session.getAttribute("cart") == null) {
			double cartPrice = 0;
			List<Prodotto> cart = new ArrayList<Prodotto>();
			session.setAttribute("cart", cart);
			session.setAttribute("cartSize", 0);
			session.setAttribute("cartPrice", cartPrice);
		}
		List<Prodotto> cart = (List<Prodotto>) session.getAttribute("cart");
		int cartSize = (int) session.getAttribute("cartSize");
		double cartPrice = (double) session.getAttribute("cartPrice");
		boolean inCart = false;
		for (Prodotto p : cart) {
			if (p.getCodice() == prodotto.getCodice() && p.getVenditore().getId() == prodotto.getVenditore().getId()) {
				inCart = true;
				p.setQuantita(p.getQuantita() + quantita);
				p.setTotale(p.getQuantita() * p.getPrezzo());
				cartPrice += quantita * p.getPrezzo();
			}
			if (inCart)
				break;
		}
		if (!inCart) {
			cart.add(prodotto);
			prodotto.setQuantita(quantita);
			prodotto.setTotale(prodotto.getPrezzo() * prodotto.getQuantita());
			cartPrice += prodotto.getTotale();
		}
		double price = 0;
		for (Prodotto p : cart) {
			price = p.getPrezzo();
			System.out.println(p.getNome());
		}
		cartSize += quantita;
		session.setAttribute("cart", cart);
		session.setAttribute("cartPrice", cartPrice);
		session.setAttribute("cartSize", cartSize);
	}
	
	@GetMapping("removeFromCart")
	@ResponseBody
	public double removeFromCart(HttpSession session, int codice, int idVenditore, int quantita) {
		System.out.println("qui");
		List<Prodotto> cart = (List<Prodotto>) session.getAttribute("cart");
		if (cart == null)
			return 0.0;

		double cartPrice = (double) session.getAttribute("cartPrice");
		System.out.println(cartPrice + "BEFORE");
		int cartSize = (int) session.getAttribute("cartSize");
		/*
		 * System.out.println("cartPrice: " + cartPrice); double totaleDaTogliere =
		 * (prodotto.getQuantita() - quantita)*prodotto.getPrezzo();
		 * System.out.println("tataleDaTogliere: " + totaleDaTogliere); cartPrice -=
		 * totaleDaTogliere; cartSize -= prodotto.getQuantita() - quantita;
		 * prodotto.setQuantita(quantita);
		 * prodotto.setTotale(prodotto.getQuantita()*prodotto.getPrezzo());
		 */
		for (Prodotto p : cart) {
			if (p.getCodice() == codice && p.getVenditore().getId() == idVenditore) {
				if (p.getQuantita() > quantita) {
					System.out.println("SOTTRAGGO DA CARRELLO");
					double totaleDaTogliere = (p.getQuantita() - quantita) * p.getPrezzo();
					cartPrice -= totaleDaTogliere;
					cartSize -= p.getQuantita() - quantita;
					p.setQuantita(quantita);
					p.setTotale(p.getQuantita() * p.getPrezzo());
				} else if (p.getQuantita() == quantita) {
					System.out.println("RIMUOVO DA CARRELLO");
					System.out.println("SONO QUI GUARDATEMI");
					cartPrice -= p.getTotale();
					cartSize -= p.getQuantita();
					p.setQuantita(0);
					p.setTotale(0);
					cart.remove(p);
				}
				break;
			}
		}
		System.out.println(cart.size());
		// System.out.println(product.getCodice()+product.getNome());
		// System.out.println(cart.get(0).getNome());
		System.out.println(cartPrice + " BEFORE");
		session.setAttribute("cart", cart);
		session.setAttribute("cartPrice", cartPrice);
		session.setAttribute("cartSize", cartSize);
		return cartPrice;
	}
	
	/*@GetMapping("createOrder")
	public String createOrder(HttpSession session, @RequestParam String numeroCarta, @RequestParam int idIndirizzo) {
		System.out.println("SONO QUI LOLOLOLLO");
		Utente utente = (Utente) session.getAttribute("user");
		List<Prodotto> cart = (List<Prodotto>) session.getAttribute("cart");
		Ordine ordine = new Ordine();
		Indirizzo indirizzo = DBManager.getIstance().indirizzoDAO().findByPrimaryKey(idIndirizzo);
		CartaDiCredito cdc = DBManager.getIstance().cartaDiCreditoDAO().findByPrimaryKey(numeroCarta);
		Date date = new Date(System.currentTimeMillis());
		ordine.setData(date);
		ordine.setStato("Elaborazione");
		double cartPrice = (double) session.getAttribute("cartPrice");
		ordine.setPrezzo(cartPrice);
		ordine.setProdotti(cart);

		List<Ordine> ordini = DBManager.getIstance().ordineDAO().findAll();
		Ordine codeLastElement = ordini.get(ordini.size() - 1);

		ordine.setCodice(codeLastElement.getCodice() + 1);

		ordine.setCartaDiCredito(DBManager.getIstance().cartaDiCreditoDAO().findByPrimaryKey(numeroCarta));

		DBManager.getIstance().ordineDAO().save(ordine, utente, indirizzo, cdc);

		for (Prodotto p : cart) {
			System.out.println("NEL CARRELLO " + p.getPrezzo());
			DBManager.getIstance().ordineDAO().addOrderProduct(p, ordine);

			DBManager.getIstance().venditoreDAO().updateProdottoVenditore(p, p.getVenditore(), p.getQuantita());

			NotificheVenditore nv = new NotificheVenditore();
			nv.setCodice(DBManager.getIstance().notificheVenditoreDAO().findLastId(p.getVenditore()) + 1);
			//System.out.println(DBManager.getIstance().notificheVenditoreDAO().findLastId(p.getVenditore()) + 1);
			nv.setLetto(false);
			nv.setProdotto(p);
			nv.setUtente(utente);
			nv.setMessaggio(utente.getNome() + " ha acquistato il tuo prodotto: " + p.getNome());
			DBManager.getIstance().notificheVenditoreDAO().save(nv, p.getVenditore());
		}
		session.setAttribute("lastOrder", ordine);
		session.setAttribute("cartSize", 0);
		session.setAttribute("cart", null);
		session.setAttribute("cartPrice", 0);
		return "userPage";
	}*/
	
	@GetMapping("createOrder")
	public String createOrder(HttpSession session, @RequestParam String numeroCarta, @RequestParam int idIndirizzo) {
		Utente utente = (Utente) session.getAttribute("user");
		List<Prodotto> cart = (List<Prodotto>) session.getAttribute("cart");
		Ordine ordineShoptime = new Ordine();
		Ordine ordineVenditore = new Ordine();
		Indirizzo indirizzo = DBManager.getIstance().indirizzoDAO().findByPrimaryKey(idIndirizzo);
		CartaDiCredito cdc = DBManager.getIstance().cartaDiCreditoDAO().findByPrimaryKey(numeroCarta);
		Date date = new Date(System.currentTimeMillis());
		List<Prodotto> prodottiShoptime = new ArrayList<Prodotto>();
		List<Prodotto> prodottiVenditore = new ArrayList<Prodotto>();
		int maxCode = DBManager.getIstance().ordineDAO().getMaxCode() + 1;
		
		for(Prodotto prodotto : cart) {
			if(prodotto.getVenditore().getNome().equals("Shoptime"))
				prodottiShoptime.add(prodotto);
			else
				prodottiVenditore.add(prodotto);
		}
		if(!prodottiShoptime.isEmpty())
			fillOrder(ordineShoptime, indirizzo, cdc, date, prodottiShoptime, true, utente, maxCode);
		if(!prodottiVenditore.isEmpty())
			fillOrder(ordineVenditore, indirizzo, cdc, date, prodottiVenditore, false, utente, maxCode);
		
		
		//session.setAttribute("lastOrder", ordine);
		session.setAttribute("cartSize", 0);
		session.setAttribute("cart", null);
		session.setAttribute("cartPrice", 0);
		return "userPage";
	}
	
	
	public void fillOrder(Ordine ordine, Indirizzo indirizzo, CartaDiCredito carta, Date data, List<Prodotto> prodotti, boolean shopTime, Utente utente, int code) {
		double totale = 0;
		for(Prodotto p : prodotti) {
			totale += p.getTotale();
		}
		ordine.setCodice(code);
		ordine.setData(data);
		ordine.setIndirizzo(indirizzo);
		ordine.setPrezzo(totale);
		ordine.setProdotti(prodotti);
		ordine.setCartaDiCredito(carta);
		DBManager.getIstance().ordineDAO().save(ordine, utente, indirizzo, carta, shopTime);
		
		for(Prodotto p : prodotti) {
			DBManager.getIstance().ordineDAO().addOrderProduct(p, ordine, shopTime);
			DBManager.getIstance().venditoreDAO().updateProdottoVenditore(p, p.getVenditore(), p.getQuantita());
			NotificheVenditore nv = new NotificheVenditore();
			nv.setCodice(DBManager.getIstance().notificheVenditoreDAO().findLastId(p.getVenditore()) + 1);
			//System.out.println(DBManager.getIstance().notificheVenditoreDAO().findLastId(p.getVenditore()) + 1);
			nv.setLetto(false);
			nv.setProdotto(p);
			nv.setUtente(utente);
			nv.setMessaggio(utente.getNome() + " ha acquistato il tuo prodotto: " + p.getNome());
			DBManager.getIstance().notificheVenditoreDAO().save(nv, p.getVenditore());
		}
		
	}

	@GetMapping("orderCompleted")
	public String orderCompleted() {
		return "orderCompleted";
	}
	

	@GetMapping("cart")
	public String cart() {
		return "cart";
	}
}
