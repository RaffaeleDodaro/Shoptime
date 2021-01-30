package it.unical.mat.progetto.ingsweb.persistance.dao.jdbc;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.unical.mat.progetto.ingsweb.model.Prodotto;
import it.unical.mat.progetto.ingsweb.model.Recensione;
import it.unical.mat.progetto.ingsweb.model.Utente;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;
import it.unical.mat.progetto.ingsweb.persistance.DBSource;
import it.unical.mat.progetto.ingsweb.persistance.dao.RecensioneDAO;
 
public class RecensioneDAOJDBC implements RecensioneDAO {
 
	DBSource dbSource;
 
	public RecensioneDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}
 
	@Override
	public void save(Recensione recensione) throws SQLException{
		Connection conn;
			conn = dbSource.getConnection();
			String query = "INSERT INTO recensione values(?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, recensione.getUtente().getId());
			st.setInt(2, recensione.getProdotto().getCodice());
			st.setInt(3, recensione.getValutazione());
			st.setString(4, recensione.getTestoRecensione());
			st.executeUpdate();
			conn.close();
	}
 
	@Override
	public Recensione findByPrimaryKey(int idUtente, int idProdotto) {
		Recensione recensione = null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from recensione where utente=? and prodotto=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, idUtente);
			st.setInt(2, idProdotto);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				// int utente = rs.getInt("utente");
				// int prodotto = rs.getInt("prodotto");
				byte valutazione = rs.getByte("valutazione");
				String testo = rs.getString("testo");
				recensione = new Recensione();
				recensione.setUtente(DBManager.getIstance().UtenteDAO().findByPrimaryKey(idUtente));
				recensione.setProdotto(DBManager.getIstance().prodottoDAO().findByPrimaryKey(idProdotto));
				recensione.setValutazione(valutazione);
				recensione.setTestoRecensione(testo);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recensione;
	}
 
	@Override
	public List<Recensione> findAll() {
		List<Recensione> recensioni = new ArrayList<Recensione>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from recensione";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				int utente = rs.getInt("utente");
				int prodotto = rs.getInt("prodotto");
				byte valutazione = rs.getByte("valutazione");
				String testo = rs.getString("testo");
				Recensione rec = new Recensione();
				rec.setUtente(DBManager.getIstance().UtenteDAO().findByPrimaryKey(utente));
				rec.setProdotto(DBManager.getIstance().prodottoDAO().findByPrimaryKey(prodotto));
				rec.setValutazione(valutazione);
				rec.setTestoRecensione(testo);
				recensioni.add(rec);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recensioni;
	}
 
	@Override
	public void updateTesto(Recensione recensione, String testo) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "UPDATE recensione set testo = ? where prodotto = ? and utente = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, testo);
			st.setInt(2, recensione.getProdotto().getCodice());
			st.setInt(3, recensione.getUtente().getId());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	
	@Override
	public void updateValutazione(Recensione recensione, byte valutazione) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "UPDATE recensione set valutazione = ? where prodotto = ? and utente = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setByte(1, valutazione);
			st.setInt(2, recensione.getProdotto().getCodice());
			st.setInt(3, recensione.getUtente().getId());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	@Override
	public void delete(Recensione recensione) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "delete from recensione where utente = ? and prodotto=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, recensione.getUtente().getId());
			st.setInt(2, recensione.getProdotto().getCodice());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Recensione> findByUser(Utente utente) {
		List<Recensione> recensioni = new ArrayList<Recensione>();
		try {
			Connection conn= dbSource.getConnection();
			String query = "select * from recensione where utente = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, utente.getId());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Recensione recensione = new Recensione();
				utente = DBManager.getIstance().UtenteDAO().findByPrimaryKey(utente.getId());
				Prodotto prodotto = DBManager.getIstance().prodottoDAO().findByPrimaryKey(rs.getInt("prodotto"));
				int valutazione = rs.getInt("valutazione");
				String testo = rs.getString("testo");
				recensione.setProdotto(prodotto);
				recensione.setTestoRecensione(testo);
				recensione.setUtente(utente);
				recensione.setProdotto(prodotto);
				recensioni.add(recensione);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recensioni;
	}

	@Override
	public List<Recensione> findByProduct(Prodotto prodotto) {
		List<Recensione> recensioni = new ArrayList<Recensione>();
		try {
			Connection conn= dbSource.getConnection();
			String query = "select * from recensione where prodotto = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, prodotto.getCodice());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Recensione recensione = new Recensione();
				prodotto = DBManager.getIstance().prodottoDAO().findByPrimaryKey(prodotto.getCodice());
				Utente utente = DBManager.getIstance().UtenteDAO().findByPrimaryKey(rs.getInt("utente"));
				int valutazione = rs.getInt("valutazione");
				String testo = rs.getString("testo");
				recensione.setProdotto(prodotto);
				recensione.setTestoRecensione(testo);
				recensione.setUtente(utente);
				recensione.setValutazione(valutazione);
				recensione.setProdotto(prodotto);
				recensioni.add(recensione);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recensioni;
	}
}
