package it.unical.mat.progetto.ingsweb.shoptime.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unical.mat.progetto.ingsweb.model.CartaDiCredito;
import it.unical.mat.progetto.ingsweb.model.Indirizzo;
import it.unical.mat.progetto.ingsweb.model.Utente;
import it.unical.mat.progetto.ingsweb.model.Venditore;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;

@Controller
public class LoginRegisterController {

	@PostMapping("login")
	@ResponseBody
	public String login(HttpSession session, Model model, @RequestBody Utente u) {
		String email = u.getEmail();
		String password = u.getPassword();
		// System.out.println("email: " + email + " password: " + password);
		Utente utente = DBManager.getIstance().UtenteDAO().findByEmail(email, password);
		if (utente != null) {
			// System.out.println(utente.getId());
			// loadCatalog(session); ATTENZIONEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
			// model.addAttribute("user", utente);
			session.setAttribute("user", utente);
			session.setAttribute("id", utente.getId());
			Venditore venditore = DBManager.getIstance().venditoreDAO().findByPrimaryKey(utente.getId());
			if (venditore != null) {
				session.setAttribute("seller", venditore);
				DBManager.getIstance().venditoreDAO().setNotificheVenditore(venditore);
				// for (NotificheVenditore nv : venditore.getNotifiche()) {
				// System.out.println(nv.getMessaggio() + "sono la notifica");
				// }
				session.setAttribute("notifiche", venditore.getNotifiche());
			}
			return "login effettuato correttamente";
		}
		// System.out.println("Utente non esiste!");
		// model.addAttribute("userNotExists", "Utente non esiste");
		return "utente non esiste";
	}

	@GetMapping("register")
	public String register() {
		return "register";
	}

	@PostMapping("registerUser")
	@ResponseBody
	public String registerUser(@RequestParam String nome, @RequestParam String cognome, @RequestParam String password,
			@RequestParam String email, @RequestParam String numeroTelefono, @RequestParam String numeroTelefonicoDestinatario,
			@RequestParam String nomeDestinatario,
			@RequestParam String via,
			@RequestParam String regione, @RequestParam String citta, @RequestParam Integer cap,
			@RequestParam String intestatario, @RequestParam String numero, @RequestParam String scadenza,
			@RequestParam Integer cvv) {

		if (cognome == "") {
			List<Utente> utenti = DBManager.getIstance().UtenteDAO().findAll();
			for (Utente u : utenti)
				if (u.getNome().equalsIgnoreCase(nome))
					return "utente presente";
		}
		Date date = Date.valueOf(scadenza + "-01");

		Utente utente = DBManager.getIstance().UtenteDAO().checkEmail(email);

		if (utente == null) {
			System.out.println(password);
			Indirizzo indirizzo = new Indirizzo();
			indirizzo.setCap(cap);
			indirizzo.setCitta(citta);
			indirizzo.setRegione(regione);
			indirizzo.setVia(via);
			indirizzo.setDestinatario(nomeDestinatario);
			indirizzo.setNumTelefono(numeroTelefono);
			indirizzo.setNumeroTelefonicoDestinatario(numeroTelefonicoDestinatario);
			
			Indirizzo i2 = DBManager.getIstance().indirizzoDAO().findByValue(indirizzo); // se non c'e' gia' nel db =>
																							// lo salvo
			if (i2 == null)
				DBManager.getIstance().indirizzoDAO().save(indirizzo);

			utente = new Utente();
			utente.setNome(nome);
			utente.setCognome(cognome);
			utente.setPassword(password);
			utente.setEmail(email);
			utente.setNumTelefonico(numeroTelefono);
			utente.setIndirizzoPredefinito(DBManager.getIstance().indirizzoDAO().findByValue(indirizzo));

			CartaDiCredito cdc = new CartaDiCredito();
			cdc.setCVV(cvv);
			cdc.setNumero(numero);
			cdc.setIntestatario(intestatario);
			cdc.setScadenza(date);

			DBManager.getIstance().UtenteDAO().save(utente, indirizzo);

			CartaDiCredito c = DBManager.getIstance().cartaDiCreditoDAO().findByPrimaryKey(numero);
			if (c == null)
				DBManager.getIstance().cartaDiCreditoDAO().save(cdc, utente, intestatario,true);
			else
				DBManager.getIstance().cartaDiCreditoDAO().save(cdc, utente, intestatario,false);

			return "ok";
		}
		return "utente presente";
	}

	@GetMapping("logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		// showCatalog(session);
		return "index";
	}
}

