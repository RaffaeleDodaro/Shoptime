package it.unical.mat.progetto.ingsweb.persistance;

import it.unical.mat.progetto.ingsweb.model.NotificheVenditore;
import it.unical.mat.progetto.ingsweb.persistance.dao.CartaDiCreditoDAO;
import it.unical.mat.progetto.ingsweb.persistance.dao.CategoriaDAO;
import it.unical.mat.progetto.ingsweb.persistance.dao.CorriereDAO;
import it.unical.mat.progetto.ingsweb.persistance.dao.IndirizzoDAO;
import it.unical.mat.progetto.ingsweb.persistance.dao.LogDAO;
import it.unical.mat.progetto.ingsweb.persistance.dao.MagazziniereDAO;
import it.unical.mat.progetto.ingsweb.persistance.dao.NotificheVenditoreDAO;
import it.unical.mat.progetto.ingsweb.persistance.dao.OrdineDAO;
import it.unical.mat.progetto.ingsweb.persistance.dao.PaccoDAO;
import it.unical.mat.progetto.ingsweb.persistance.dao.ProdottoDAO;
import it.unical.mat.progetto.ingsweb.persistance.dao.RecensioneDAO;
import it.unical.mat.progetto.ingsweb.persistance.dao.UtenteDAO;
import it.unical.mat.progetto.ingsweb.persistance.dao.VenditoreDAO;
import it.unical.mat.progetto.ingsweb.persistance.dao.jdbc.CartaDiCreditoDAOJDBC;
import it.unical.mat.progetto.ingsweb.persistance.dao.jdbc.CategoriaDAOJDBC;
import it.unical.mat.progetto.ingsweb.persistance.dao.jdbc.CorriereDAOJDBC;
import it.unical.mat.progetto.ingsweb.persistance.dao.jdbc.IndirizzoDAOJDBC;
import it.unical.mat.progetto.ingsweb.persistance.dao.jdbc.LogDAOJDBC;
import it.unical.mat.progetto.ingsweb.persistance.dao.jdbc.MagazziniereDAOJDBC;
import it.unical.mat.progetto.ingsweb.persistance.dao.jdbc.NotificheVenditoreDAOJDBC;
import it.unical.mat.progetto.ingsweb.persistance.dao.jdbc.OrdineDAOJDBC;
import it.unical.mat.progetto.ingsweb.persistance.dao.jdbc.PaccoDAOJDBC;
import it.unical.mat.progetto.ingsweb.persistance.dao.jdbc.ProdottoDAOJDBC;
import it.unical.mat.progetto.ingsweb.persistance.dao.jdbc.RecensioneDAOJDBC;
import it.unical.mat.progetto.ingsweb.persistance.dao.jdbc.UtenteDAOJDBC;
import it.unical.mat.progetto.ingsweb.persistance.dao.jdbc.VenditoreDAOJDBC;

public class DBManager {
	
	private static DBManager instance = null;
	static DBSource dataSource;
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
			//questi vanno messi in file di configurazione!!!
			dataSource=new DBSource("jdbc:postgresql://localhost:5430/shoptime","postgres","postgres");
			//System.out.println("FUNZIONA");
		}catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}
	
	public static DBManager getIstance() {
		if(instance == null)
			instance = new DBManager();
		return instance;
	}
	
	public static DBSource getDataSource() {
		return dataSource;
	}
	
	private DBManager() {
		
	}
	

	public CartaDiCreditoDAO cartaDiCreditoDAO() {
		return new CartaDiCreditoDAOJDBC(dataSource);
	}
	
	public CategoriaDAO categoriaDAO() {
		return new CategoriaDAOJDBC(dataSource);
	}
	
	public CorriereDAO corriereDAO() {
		return new CorriereDAOJDBC(dataSource);
	}
	
	public IndirizzoDAO indirizzoDAO() {
		return new IndirizzoDAOJDBC(dataSource);
	}
	
	public LogDAO logDAO() {
		return new LogDAOJDBC(dataSource);
	}
	
	public MagazziniereDAO magazziniereDAO() {
		return new MagazziniereDAOJDBC(dataSource);
	}
	
	public OrdineDAO ordineDAO() {
		return new OrdineDAOJDBC(dataSource);
	}
	
	public PaccoDAO paccoDAO() {
		return new PaccoDAOJDBC(dataSource);
	}
	
	
	public RecensioneDAO recensioneDAO() {
		return new RecensioneDAOJDBC(dataSource);
	}
	
	public UtenteDAO UtenteDAO() {
		return new UtenteDAOJDBC(dataSource);
	}
	
	public VenditoreDAO venditoreDAO() {
		return new VenditoreDAOJDBC(dataSource);
	}
	
	public ProdottoDAO prodottoDAO() {
		return new ProdottoDAOJDBC(dataSource);
	}
	
	public NotificheVenditoreDAO notificheVenditoreDAO() {
		return new NotificheVenditoreDAOJDBC(dataSource);
	}
}
