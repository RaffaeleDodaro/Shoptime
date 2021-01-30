package it.unical.mat.progetto.ingsweb.shoptime.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.unical.mat.progetto.ingsweb.model.Log;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;


@Controller
public class TrackingController {
	final String subfolder = "/tracking/";
	
	List<Log> logs = null;
	
	@GetMapping("tracking")
	public String tracking(){
		return subfolder + "trackingIndex";
		
	}
	
	@PostMapping("getLogs")
	public String getLogs(@RequestParam String trackingCode, Model model){
		logs = DBManager.getIstance().logDAO().findByTrackingCode(trackingCode);
		if(!logs.isEmpty()) {
			model.addAttribute("logs", logs);
		}
		return subfolder + "trackingResult";
	}
}