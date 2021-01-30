package it.unical.mat.progetto.ingsweb.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.unical.mat.progetto.ingsweb.model.Corriere;
import it.unical.mat.progetto.ingsweb.model.NotificheVenditore;
import it.unical.mat.progetto.ingsweb.model.Prodotto;
import it.unical.mat.progetto.ingsweb.model.Utente;
import it.unical.mat.progetto.ingsweb.model.Venditore;
import it.unical.mat.progetto.ingsweb.persistance.dao.NotificheVenditoreDAO;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;
import it.unical.mat.progetto.ingsweb.persistance.DBSource;

public class NotificheVenditoreDAOJDBC implements NotificheVenditoreDAO {

	DBSource dbSource;
	
	public NotificheVenditoreDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}
	
	@Override
	public void save(NotificheVenditore nv, Venditore venditore) {
		Connection conn;
		try {
			conn = dbSource.getConnection();
			//ID VENDITORE PRODOTTO MESSAGGIO LETTO UTENTE
			String query = "INSERT INTO notifichevenditore values(?,?,?,?,?, ?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, nv.getCodice());
			st.setString(2, nv.getMessaggio());
			st.setBoolean(3, nv.isLetto());
			st.setString(4, venditore.getPartitaIva());
			st.setInt(5, nv.getUtente().getId());
			st.setInt(6, nv.getProdotto().getCodice());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public NotificheVenditore findByPrimaryKey(int id, Venditore venditore) {
		NotificheVenditore nv = new NotificheVenditore();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from notifichevenditore where id=? and venditore=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, id);
			st.setString(2, venditore.getPartitaIva());
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				int codice = rs.getInt("id");
				String messaggio = rs.getString("messaggio");
				boolean letto = rs.getBoolean("letto");
				nv.setCodice(codice);
				nv.setLetto(letto);
				nv.setMessaggio(messaggio);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}

	@Override
	public List<NotificheVenditore> findAll() {
		List<NotificheVenditore> nvs = new ArrayList<NotificheVenditore>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from notifichevenditore";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				int codice = rs.getInt("id");
				String messaggio = rs.getString("messaggio");
				boolean letto = rs.getBoolean("letto");
				NotificheVenditore nv = new NotificheVenditore();
				nv.setCodice(codice);
				nv.setLetto(letto);
				nv.setMessaggio(messaggio);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nvs;
	}

	@Override
	public void update(Venditore venditore, int nv, boolean letto) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "UPDATE notifichevenditore set letto = ? where venditore=? and id = ? ";
			PreparedStatement st = conn.prepareStatement(query);
			st.setBoolean(1,letto);
			st.setString(2, venditore.getPartitaIva());
			st.setInt(3, nv);
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Venditore venditore, int id) {}

	@Override
	public int findLastId(Venditore venditore) {
		int maxId = 1;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select max(id) as id from notifichevenditore where venditore = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, venditore.getPartitaIva());
			System.out.println(venditore.getPartitaIva());
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				maxId = rs.getInt("id");
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxId;
	}

}
