package it.unical.mat.progetto.ingsweb.shoptime.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unical.mat.progetto.ingsweb.model.Prodotto;
import it.unical.mat.progetto.ingsweb.model.Venditore;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;

@Controller
public class SearchController {

	// MOSTRA PRODOTTI IN CATEGORIA
	@GetMapping("showProductsFromCategory")
	public String showProductsFromCategory(HttpSession session, String categoryName) {
		/*
		 * List<Prodotto> prodotti =
		 * DBManager.getIstance().categoriaDAO().getProductFromCategories(categoryName);
		 * 
		 * List<Prodotto> prodottiVenditore = new ArrayList<Prodotto>(); for (Prodotto
		 * prodotto : prodotti) { List<Venditore> venditoriProdotto =
		 * DBManager.getIstance().prodottoDAO().getSellersFromProduct(prodotto);
		 * 
		 * for (Venditore venditore : venditoriProdotto) { Prodotto p = new Prodotto();
		 * p.setCodice(prodotto.getCodice());
		 * p.setDescrizione(prodotto.getDescrizione()); p.setNome(prodotto.getNome());
		 * p.setPrezzo(DBManager.getIstance().prodottoDAO().getPriceProductFromSeller(
		 * venditore, prodotto));; p.setQuantita(prodotto.getQuantita());
		 * p.setTotale(prodotto.getTotale()); p.setUrlImg(prodotto.getUrlImg());
		 * p.setVenditore(venditore); System.out.println(p.getNome() + ": " +
		 * p.getVenditore().getNome()); prodottiVenditore.add(p); } }
		 * 
		 * session.setAttribute("prodottiCategoria", prodottiVenditore);
		 */
		List<Prodotto> prodotti = new ArrayList();
		if (categoryName.equals("prodottiTelefonia"))
			prodotti = (List<Prodotto>) session.getAttribute("prodottiTelefonia");
		if (categoryName.equals("prodottiTelefonia"))
			prodotti = (List<Prodotto>) session.getAttribute("prodottiTelefonia");
		switch (categoryName) {
		case "prodottiTelefonia":
			prodotti = (List<Prodotto>) session.getAttribute("prodottiTelefonia");
			break;
		case "Informatica":
			prodotti = (List<Prodotto>) session.getAttribute("prodottiInformatica");
			break;
		case "Videogiochi":
			prodotti = (List<Prodotto>) session.getAttribute("prodottiVideogiochi");
			break;
		case "Film e TV":
			prodotti = (List<Prodotto>) session.getAttribute("prodottiFilm");
			break;
		case "Cucina":
			prodotti = (List<Prodotto>) session.getAttribute("prodottiCucina");
			break;
		}

		session.setAttribute("prodottiCategoria", prodotti);
		session.setAttribute("nomeCategoria", categoryName);
		return "productsCategory";
	}

	@GetMapping("productsCategory")
	public String showProductsInCategory() {
		return "productsCategory";
	}
	// FINE MOSTRA PRODOTTI IN CATEGORIA

	@GetMapping("searchProductByName")
	@ResponseBody
	public String searchProductByName(HttpSession session, String productName) {
		session.setAttribute("productName", productName);

		List<Prodotto> listaProdottiTrovatiInformatica = searchProductInCategory(session, productName, "prodottiInformatica");
		List<Prodotto> listaProdottiTrovatiFilm = searchProductInCategory(session, productName, "prodottiFilm");
		List<Prodotto> listaProdottiTrovatiCucina = searchProductInCategory(session, productName, "prodottiCucina");
		List<Prodotto> listaProdottiTrovatiVideogiochi = searchProductInCategory(session, productName, "prodottiVideogiochi");

		
		List<Prodotto> listaGenerale = new ArrayList<Prodotto>();
		
		listaGenerale.addAll(listaProdottiTrovatiVideogiochi);
		listaGenerale.addAll(listaProdottiTrovatiInformatica);
		listaGenerale.addAll(listaProdottiTrovatiFilm);
		listaGenerale.addAll(listaProdottiTrovatiCucina);
		
		if (listaGenerale.size() != 0) {
			session.setAttribute("prodottiCercati", listaGenerale);
			return "searchedProducts";
		}
		return "not found";
	}
		

	public List<Prodotto> searchProductInCategory(HttpSession session, String nome, String categoria) {
		List<Prodotto> listaProdottiTrovati = new ArrayList<Prodotto>();
		List<Prodotto> listaProdotti = (List<Prodotto>) session.getAttribute(categoria);

		for (Prodotto p : listaProdotti)
			if (p.getNome().toLowerCase().contains(nome.toLowerCase()))
				listaProdottiTrovati.add(p);
		System.out.println(nome + " " + categoria);
		return listaProdottiTrovati;
	}

	@GetMapping("searchedProducts")
	public String searchedProduct() {
		return "searchedProducts";
	}
	// FINE RICERCA DI PRODOTTO

	@GetMapping("showProductsFromSeller")
	@ResponseBody
	public String showProductsFromSeller(HttpSession session, @RequestParam Integer id) {
		List<Prodotto> prodotti = DBManager.getIstance().prodottoDAO().getProductsFromSeller(id);

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

		session.setAttribute("prodottiVenditore", prodottiVenditore);
		session.setAttribute("nomeVenditore", DBManager.getIstance().venditoreDAO().findByPrimaryKey(id).getNome());

		return "ok";
	}
	public double tronca(double prezzo)
	{
		prezzo=prezzo*100;
		prezzo=Math.floor(prezzo);
		prezzo= prezzo/100;
		return prezzo;
	}
	@GetMapping("sellerPage")
	public String sellerPage() {
		return "sellerPage";
	}
}
