package it.unical.mat.progetto.ingsweb.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.unical.mat.progetto.ingsweb.model.CartaDiCredito;
import it.unical.mat.progetto.ingsweb.model.Utente;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;
import it.unical.mat.progetto.ingsweb.persistance.DBSource;
import it.unical.mat.progetto.ingsweb.persistance.dao.CartaDiCreditoDAO;

public class CartaDiCreditoDAOJDBC implements CartaDiCreditoDAO {

	DBSource dbSource;

	public CartaDiCreditoDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}

	@Override
	public void save(CartaDiCredito cartaDiCredito, Utente utente, String intestatario, boolean newCard) {
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query;
			PreparedStatement st;
			if (newCard) {
				query = "INSERT INTO cartadicredito values(?,?,?,?)";
				st = conn.prepareStatement(query);
				st.setString(1, cartaDiCredito.getNumero());
				st.setString(2, cartaDiCredito.getIntestatario());
				st.setDate(3, cartaDiCredito.getScadenza());
				st.setInt(4, cartaDiCredito.getCVV());
				st.executeUpdate();
			}

			query = "INSERT INTO utente_cartadicredito values(?,?)";
			st = conn.prepareStatement(query);
			st.setInt(1, DBManager.getIstance().UtenteDAO().checkEmail(utente.getEmail()).getId());
			st.setString(2, cartaDiCredito.getNumero());

			st.executeUpdate();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public CartaDiCredito findByPrimaryKey(String numeroCarta) {
		CartaDiCredito cdc = null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from cartadicredito where numero=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, numeroCarta);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String numero = rs.getString("numero");
				String intestatario = rs.getString("intestatario");
				Date scadenza = rs.getDate("scadenza");
				int cvv = rs.getInt("cvv");
				cdc = new CartaDiCredito();
				cdc.setNumero(numero);
				cdc.setIntestatario(intestatario);
				cdc.setScadenza(scadenza);
				cdc.setCVV(cvv);
				//cdc.setUtente(DBManager.getIstance().UtenteDAO().findByPrimaryKey(rs.getInt("utente")).getId());
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cdc;
	}

	@Override
	public List<CartaDiCredito> findAll() {
		List<CartaDiCredito> carte = new ArrayList<CartaDiCredito>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from cartadicredito";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String numero = rs.getString("numero");
				String intestatario = rs.getString("intestatario");
				Date scadenza = rs.getDate("scadenza");
				int cvv = rs.getInt("cvv");
				CartaDiCredito cdc = null;
				cdc = new CartaDiCredito();
				cdc.setNumero(numero);
				cdc.setIntestatario(intestatario);
				cdc.setScadenza(scadenza);
				cdc.setCVV(cvv);
				cdc.setUtente(DBManager.getIstance().UtenteDAO().findByPrimaryKey(rs.getInt("utente")).getId());
				carte.add(cdc);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carte;
	}

	@Override
	public void update(CartaDiCredito cartaDiCredito) {
	}

	@Override
	public void delete(CartaDiCredito cartaDiCredito, int id) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "delete from utente_cartadicredito where cartadicredito = ? and utente=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, cartaDiCredito.getNumero());
			st.setInt(2, id);
			st.executeUpdate();

			query = "delete from cartadicredito where numero = ? and utente =?";
			st = conn.prepareStatement(query);
			st.setString(1, cartaDiCredito.getNumero());
			st.setInt(2, id);
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}