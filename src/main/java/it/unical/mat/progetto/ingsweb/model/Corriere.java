package it.unical.mat.progetto.ingsweb.model;

import java.util.List;

public class Corriere {
	
	int id;
	String email, password, cognome, nome;
	List<Pacco> pacchi;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Pacco> getPacchi() {
		return pacchi;
	}
	public void setPacchi(List<Pacco> pacchi) {
		this.pacchi = pacchi;
	}
	
	
	

}
