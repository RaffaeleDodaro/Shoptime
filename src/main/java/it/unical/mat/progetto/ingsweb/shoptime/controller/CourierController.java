package it.unical.mat.progetto.ingsweb.shoptime.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unical.mat.progetto.ingsweb.model.Corriere;
import it.unical.mat.progetto.ingsweb.model.Indirizzo;
import it.unical.mat.progetto.ingsweb.model.Log;
import it.unical.mat.progetto.ingsweb.model.Pacco;

import it.unical.mat.progetto.ingsweb.persistance.DBManager;

@Controller
public class CourierController {
	
	String subfolder = "/courier/";
	List<Pacco> pacchi;
	Pacco detailedPackage;
	String courierMail;

	@GetMapping("courier")
	public String courier() {
		return subfolder + "courierLogin";
	}
	

	@PostMapping("doCourierLogin")
	public String login(HttpSession session, @RequestParam String email, @RequestParam String password, Model model) {
		if(loginOk(email, password)) {
			session.setAttribute("courierLogged", DBManager.getIstance().corriereDAO().findByEmail(email).getNome());
			courierMail = email;
			pacchi = DBManager.getIstance().corriereDAO().getPackagesFromMail(email);
			model.addAttribute("listaPacchi", pacchi);
			return subfolder + "courierHome";
		}
		return subfolder + "courierLogin";
	}
	
	@GetMapping("goHome")
	public String courierHome(Model model){
		pacchi = DBManager.getIstance().corriereDAO().getPackagesFromMail(courierMail);
		model.addAttribute("listaPacchi", pacchi);
		return subfolder + "courierHome";
	}

	@GetMapping("ongoing")
	public String onGoing(Model model){
		pacchi = DBManager.getIstance().corriereDAO().getPackagesFromMail(courierMail);
		List<Pacco> tmp = new ArrayList();
		for (Pacco p: pacchi){
			String logStatus = p.getLogs().get(p.getLogs().size() -1).getStato().toLowerCase();
			if (!logStatus.equals("consegnato") && !logStatus.equals("in giacenza"))
				tmp.add(p);
		}
		model.addAttribute("listaPacchi", tmp);
		return subfolder + "courierHome";
	}

	@GetMapping("stocked")
	public String stocked(Model model){
		model.addAttribute("listaPacchi", controlPackage("in giacenza"));
		return subfolder + "courierHome";
	}

	@GetMapping("finished")
	public String finished(Model model){
		model.addAttribute("listaPacchi", controlPackage("consegnato"));
		return subfolder + "courierHome";
	}

	@PostMapping("addLog")
	public String addLog(@RequestParam String posizione, @RequestParam String stato, @RequestParam String descrizione, Model model){
		Log tmp = new Log();
		tmp.setStato(stato);
		tmp.setDescrizione(descrizione);
		tmp.setPosizione(posizione);
		tmp.setData(Date.valueOf(LocalDate.now()));
		DBManager.getIstance().logDAO().save(tmp, detailedPackage.getCodice());
		return status(model);
	}

	@GetMapping("status")
	public String status(Model model){
		List<Log> logs = DBManager.getIstance().paccoDAO().getLogsOfPackage(detailedPackage);
		model.addAttribute("logs", logs);
		LocalDate localDate = LocalDate.now();
		model.addAttribute("now", localDate.now());
		if (logs.get(logs.size() -1).getStato().equals("Consegnato"))
			model.addAttribute("isConsegnato", 1);
		else
			model.addAttribute("isConsegnato", 0);
		return subfolder + "logStatus";
	}

	@ResponseBody
	@PostMapping("information")
	public String packageInfo(@RequestParam int id) {
		detailedPackage = DBManager.getIstance().paccoDAO().findByPrimaryKey(Integer.valueOf(id));
		return "packageInfo";
	}

	@GetMapping("packageInfo")
	public String packageInfo(Model model){
		model.addAttribute("pacco", detailedPackage);
		return subfolder + "packageInfo";
	}

	private boolean loginOk(String email, String password) {
		Corriere corriere = DBManager.getIstance().corriereDAO().findByEmail(email);
		
		if(corriere!=null && email.equals(corriere.getEmail()) && password.equals(corriere.getPassword()))
			return true;
		return false;
	}
	
	public List<Pacco> controlPackage(String condition){
		pacchi = DBManager.getIstance().corriereDAO().getPackagesFromMail(courierMail);
		List<Pacco> tmp = new ArrayList();
		for (Pacco p: pacchi){
			String logStatus = p.getLogs().get(p.getLogs().size() -1).getStato().toLowerCase();
			if (logStatus.equals(condition))
				tmp.add(p);
		}
		return tmp;
	}
}
