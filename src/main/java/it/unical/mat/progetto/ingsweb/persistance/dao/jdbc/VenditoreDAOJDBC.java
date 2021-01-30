package it.unical.mat.progetto.ingsweb.persistance.dao.jdbc;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.unical.mat.progetto.ingsweb.model.NotificheVenditore;
import it.unical.mat.progetto.ingsweb.model.Prodotto;
import it.unical.mat.progetto.ingsweb.model.Utente;
import it.unical.mat.progetto.ingsweb.model.Venditore;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;
import it.unical.mat.progetto.ingsweb.persistance.DBSource;
import it.unical.mat.progetto.ingsweb.persistance.dao.VenditoreDAO;
 
public class VenditoreDAOJDBC implements VenditoreDAO {
 
	DBSource dbSource;
 
	public VenditoreDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}
 
	@Override
	public void save(Venditore venditore) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "INSERT INTO venditore values(?, ?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, venditore.getPartitaIva());
			st.setInt(2, venditore.getId());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	@Override
	public Venditore findByPrimaryKey(int id) {
		Venditore venditore = new Venditore();
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "select * from venditore where utente=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				int idUtente = rs.getInt("utente");
			    String partitaIva = rs.getString("partitaiva");
			    Utente utente = DBManager.getIstance().UtenteDAO().findByPrimaryKey(idUtente);
			    venditore.setId(idUtente);
			    venditore.setPartitaIva(partitaIva);
			    venditore.setDatiUtente(utente);
			}
			else {
				venditore = null;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return venditore;
	}
 
	@Override
	public List<Venditore> findAll() {
		List<Venditore> venditori = new ArrayList<Venditore>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from venditore";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				Venditore venditore = new Venditore();
				int idUtente = rs.getInt("utente");
			    String partitaIva = rs.getString("partitaiva");
			    Utente utente = DBManager.getIstance().UtenteDAO().findByPrimaryKey(idUtente);
			    venditore.setId(idUtente);
			    venditore.setPartitaIva(partitaIva);
			    venditore.setDatiUtente(utente);
			    venditori.add(venditore);
			    this.setCatalogoVenditore(venditore);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return venditori;
	}
 
	@Override
	public void update(Venditore venditore) {
 
	}
 
	@Override
	public void delete(Venditore venditore) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "delete from prodotto_venditore where venditore = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, venditore.getId());
			st.executeUpdate();
			
			query = "delete from venditore where utente = ?";
			st = conn.prepareStatement(query);
			st.setInt(1, venditore.getId());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
 
	@Override
	public void addProdottoVenditore(Prodotto prodotto, Venditore venditore, int quantita) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "insert into prodotto_venditore values(?, ?, ?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, prodotto.getCodice());
			st.setInt(2, venditore.getId());
			st.setInt(3, quantita);
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	@Override
	public void updateProdottoVenditore(Prodotto prodotto, Venditore venditore, int quantita) {
				
		//mi prendo la quantita
		Connection conn2;
		try {
			conn2 = dbSource.getConnection();
			String query2 = "select disponibilita_venditore from prodotto_venditore where venditore = ? and prodotto = ?";
			PreparedStatement st2 = conn2.prepareStatement(query2);
			st2.setInt(1, venditore.getId());
			st2.setInt(2, prodotto.getCodice());
			ResultSet rs2 = st2.executeQuery();
			int quantitaVecchia=0;
			if (rs2.next()) {
				quantitaVecchia = rs2.getInt("disponibilita_venditore");
				System.out.println(quantitaVecchia + " vecchia ");
			}
			quantita = quantitaVecchia-quantita;
			System.out.println("/ nuova: "+quantita);
			conn2.close();
						
			Connection conn = dbSource.getConnection();
			String query = "update prodotto_venditore set disponibilita_venditore = ? where prodotto = ? and venditore = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, quantita);
			st.setInt(2, prodotto.getCodice());
			st.setInt(3, venditore.getId());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setPriceProductFromSeller(Prodotto prodotto, Venditore venditore) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "select prezzo from prodotto_venditore where prodotto = ? and venditore = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1,prodotto.getCodice());
			st.setInt(2, venditore.getId());
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				prodotto.setPrezzo(tronca(rs.getDouble("prezzo")));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public double tronca(double prezzo)
	{
		prezzo=prezzo*100;
		prezzo=Math.floor(prezzo);
		prezzo= prezzo/100;
		return prezzo;
	}
	
	
	
	@Override
	public void setCatalogoVenditore(Venditore venditore) {
	    List<Prodotto> catalogo = new ArrayList<Prodotto>(); 
	    try {
	      Connection conn = dbSource.getConnection();
	      String query = "select * from prodotto_venditore where venditore = ?";
	      PreparedStatement st = conn.prepareStatement(query);
	      st.setInt(1, venditore.getId());
	      ResultSet rs = st.executeQuery();
	      while(rs.next()) {
	        Prodotto prodotto = DBManager.getIstance().prodottoDAO().findByPrimaryKey(rs.getInt("prodotto"));
	        prodotto.setPrezzo(tronca(rs.getDouble("prezzo")));
	        int quantita = rs.getInt("disponibilita_venditore");
	        if(quantita > 0) {
	          catalogo.add(prodotto);
	        }
	      }
	      venditore.setCatalogoProdotti(catalogo);
	      conn.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
 
	@Override
	public void setNotificheVenditore(Venditore venditore) {
		List<NotificheVenditore> nvs = new ArrayList<NotificheVenditore>(); 
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from notificheVenditore where venditore = ? and letto=?";
			PreparedStatement st = conn.prepareStatement(query);
			System.out.println("PARTITA IVA: " + venditore.getPartitaIva());
			st.setString(1, venditore.getPartitaIva());
			st.setBoolean(2, false);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				NotificheVenditore nv = DBManager.getIstance().notificheVenditoreDAO().findByPrimaryKey(rs.getInt("id"),venditore);
				nvs.add(nv);
			}
			venditore.setNotifiche(nvs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
