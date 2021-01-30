package it.unical.mat.progetto.ingsweb.shoptime.controller;


import java.sql.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unical.mat.progetto.ingsweb.model.Corriere;
import it.unical.mat.progetto.ingsweb.model.Log;
import it.unical.mat.progetto.ingsweb.model.Magazziniere;
import it.unical.mat.progetto.ingsweb.model.Ordine;
import it.unical.mat.progetto.ingsweb.model.Pacco;
import it.unical.mat.progetto.ingsweb.model.Prodotto;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;


@Controller
public class WarehouseController {
	final String subfolder = "/warehouse/";
	
	boolean success=false;
	List<Ordine> ordini;
	Ordine ordine;
	Magazziniere magazziniere = null;
	Prodotto prodotto;
	
	@GetMapping("/warehouse")
	public String warehouse() {
		return subfolder + "warehouseLogin";
	}
	
	@GetMapping("/home")
	public String home(Model model) {
		if (magazziniere != null) {
			getAllOrders(model);
			return subfolder + "warehouseHome";
		}
		else return subfolder + "warehouseLogin";
	}
	
	@PostMapping("findOrder")
	public String findOrder(@RequestParam int codiceOrdine, Model model) {
		Ordine tmp = null;
		tmp = DBManager.getIstance().ordineDAO().findByPrimaryKeyShoptime(codiceOrdine);
		
		if(tmp != null) {
			ordini.clear();
			ordini.add(tmp);
			model.addAttribute("listaOrdini", ordini);
		}
		else
			model.addAttribute("errore", "Nessun ordine corrisponde al codice inserito.");

		return  subfolder + "warehouseHome";
	}

	@GetMapping("aggiornaScorte")
	public String aggiornaScorte(Model model) {
		if (magazziniere != null) {
			model.addAttribute("stage", 0);
			return subfolder + "updateWarehouseStorage";
		}
		else return subfolder + "warehouseLogin";
	}
	
	@PostMapping("getProduct")
	public String getProduct(@RequestParam int codiceProdotto, Model model) {
		prodotto = DBManager.getIstance().prodottoDAO().findByPrimaryKey(codiceProdotto);
		int quantita = DBManager.getIstance().prodottoDAO().getShoptimeProductQuantity(prodotto.getCodice());
		if(quantita > -1) {
			model.addAttribute("stage", 1);
			model.addAttribute("quantita", quantita);
		}
		else {
			model.addAttribute("stage", 0);
			model.addAttribute("errore", "Il codice inserito non corrisponde a nessun prodotto.");
		}

		return subfolder + "updateWarehouseStorage";
	}
	
	@PostMapping("updateStorage")
	public String updateStorage(@RequestParam int nuovaQuantita, Model model) {
		
		if (nuovaQuantita >-1) {
			model.addAttribute("stage", 0);
			model.addAttribute("success", "Scorte del prodotto aggiornate!");
			DBManager.getIstance().prodottoDAO().updateShoptimeProductQuantity(prodotto.getCodice(), nuovaQuantita);
		}
		else {
			model.addAttribute("stage", 0);
			model.addAttribute("errore", "La nuova quantità non può essere un valore negativo.");
		}
		
		return subfolder + "updateWarehouseStorage";
	}
	
	@GetMapping("ordiniInElaborazione")
	public String ordiniInElaborazione(Model model) {
		if (magazziniere!=null) {
			ordini = DBManager.getIstance().ordineDAO().findAllOrdersToBeProcessed();
			model.addAttribute("listaOrdini", ordini);
			return subfolder + "warehouseHome";
		}
		else return subfolder + "warehouseLogin";
	}
	
	@GetMapping("ordiniElaborati")
	public String ordiniElaborati(Model model) {
		if (magazziniere!=null) {
			ordini = DBManager.getIstance().ordineDAO().findAllOrdersProcessed();
			model.addAttribute("listaOrdini", ordini);
			if (success) {
				model.addAttribute("success", "Operazione eseguita con successo!");
				success = false;
			}
			return subfolder + "warehouseHome";
		}
		else return subfolder + "warehouseLogin";
	}
	
	@GetMapping("ordiniSpediti")
	public String ordiniSpediti(Model model) {
		if (magazziniere!=null) {
			ordini = DBManager.getIstance().ordineDAO().findAllOrdersShipped();
			model.addAttribute("listaOrdini", ordini);
			return subfolder + "warehouseHome";
		}
		else return subfolder + "warehouseLogin";
	}
	
	@ResponseBody
	@PostMapping("orderDetails")
	public String orderDetails(@RequestParam String codiceOrdine) {
		ordine = DBManager.getIstance().ordineDAO().findByPrimaryKeyShoptime(Integer.valueOf(codiceOrdine));
		DBManager.getIstance().ordineDAO().setProductsFromOrderShoptime(ordine);
		return "warehouseOrderDetails";
	}
	
	@GetMapping("warehouseOrderDetails")
	public String warehouseOrderDetails(Model model) {
		if (magazziniere!=null) {
			model.addAttribute("ordine", ordine);
			return subfolder + "warehouseOrderDetails";
		}
		else return subfolder + "warehouseLogin";
	}
	
	@ResponseBody
	@PostMapping("confirmOrder")
	public String confirmOrder(@RequestParam String codiceOrdine, Model model) {
		Pacco pacco = new Pacco();
		pacco.setTrackingCode(new GeneraCodiceRandom().generaCodice(30, ""));
		DBManager.getIstance().paccoDAO().save(pacco, ordine.getCodice(), magazziniere.getId(), ordine.getIndirizzo().getCodice());
		DBManager.getIstance().ordineDAO().updateOrderState(ordine, "Elaborato");
		success = true;
		return "ordiniElaborati";
	}
	
	@PostMapping("assignCourier")
	public String assignCourier(@RequestParam int idCorriere, Model model) {
		Corriere corriere = DBManager.getIstance().corriereDAO().findByPrimaryKey(idCorriere);
		if(corriere != null) {
			Pacco pacco = DBManager.getIstance().paccoDAO().findByOrder(ordine);
			DBManager.getIstance().paccoDAO().updateCourierOfPackage(pacco, idCorriere);
			Log log = new Log();
			log.setData(Date.valueOf(java.time.LocalDateTime.now().toLocalDate()));
			log.setStato("Spedito");
			log.setPosizione("Magazzino Shoptime - Cosenza");
			log.setDescrizione("Pacco spedito all'indirizzo: "+ordine.getIndirizzo().getVia()
					+", "+ordine.getIndirizzo().getCitta()+", "+ordine.getIndirizzo().getRegione()+", "+ordine.getIndirizzo().getCap()+".\n" );
			
			DBManager.getIstance().logDAO().save(log, pacco.getCodice());
			DBManager.getIstance().ordineDAO().updateOrderState(ordine, "Spedito");
			
			ordini = DBManager.getIstance().ordineDAO().findAllOrdersShipped();
			model.addAttribute("listaOrdini", ordini);
			model.addAttribute("success", "Operazione eseguita con successo!");
			
			return subfolder + "warehouseHome";
		}
		else {
			model.addAttribute("errore", "Nessun corriere corrisponde al codice inserito.");
			model.addAttribute("ordine", ordine);
			return subfolder + "warehouseOrderDetails";
		}
	}
	
	
	@PostMapping("sendMessage")
	public String sendMessage(@RequestParam int idProdotto, @RequestParam String message, Model model) {
		DBManager.getIstance().ordineDAO().updateOrderProductMessage(ordine.getCodice(), idProdotto, message);
		model.addAttribute("ordine", ordine);
		model.addAttribute("success", "Avviso sul prodotto aggiornato correttamente.");
		return subfolder + "warehouseOrderDetails";
	}
	
	
	@PostMapping("doWarehouseLogin")
	public String login(HttpSession session, @RequestParam String email, @RequestParam String password, Model model) {
		if(loginOk(email, password)) {
			session.setAttribute("warehouseWorkerLogged", magazziniere.getNome());
			getAllOrders(model);
			return subfolder + "warehouseHome";
		}
		else {
			model.addAttribute("errore", "Email e/o password errati.");
			return subfolder + "warehouseLogin";
		}
	}
	
	@GetMapping("doWarehouseLogout")
	public String logout(HttpSession session) {
		
		if (magazziniere != null) {
			session.invalidate();
			magazziniere = null;
			
		}
		return subfolder + "warehouseLogin";
		
	}
	
	
	private boolean loginOk(String email, String password) {
		 magazziniere = DBManager.getIstance().magazziniereDAO().findByEmail(email);
		
		if(magazziniere!=null && email.equals(magazziniere.getEmail()) && password.equals(magazziniere.getPassword()))
			return true;
		return false;
	}
	
	private void getAllOrders(Model model) {
		ordini = DBManager.getIstance().ordineDAO().findAllShoptimeOrders();
		model.addAttribute("listaOrdini", ordini);
	}
	
	
	private class GeneraCodiceRandom {

		// array delle lettere
		private String[] caratteri = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
				"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "z", "y",
				"j", "k", "x", "w", "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "Z", "Y",
				"J", "K", "X", "W" };
		
		private String[] numeri = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
		private Random rand = new Random();
		public String generaCodice(int numeroCaratteriRandom, 
				 String separatore) {

			
			int lunghezzaCaratteri = caratteri.length;
			int lunghezzaNumeri = numeri.length;
		

			
			String stringaRandom = "";

			while (stringaRandom.length() < numeroCaratteriRandom) {

				
				int c = rand.nextInt(lunghezzaCaratteri);
				int n = rand.nextInt(lunghezzaNumeri);
						
				stringaRandom += caratteri[c];
				
				stringaRandom += numeri[n];
				
				
			}

			
			if (stringaRandom.length() > numeroCaratteriRandom) {
				stringaRandom = stringaRandom.substring(0, numeroCaratteriRandom);
			}

			
			return stringaRandom;
		}

	}
	
}