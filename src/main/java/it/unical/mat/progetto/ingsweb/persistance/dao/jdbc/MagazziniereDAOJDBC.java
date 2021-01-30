package it.unical.mat.progetto.ingsweb.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.unical.mat.progetto.ingsweb.model.Magazziniere;
import it.unical.mat.progetto.ingsweb.model.Ordine;
import it.unical.mat.progetto.ingsweb.model.Pacco;
import it.unical.mat.progetto.ingsweb.model.Utente;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;
import it.unical.mat.progetto.ingsweb.persistance.DBSource;
import it.unical.mat.progetto.ingsweb.persistance.dao.MagazziniereDAO;
import it.unical.mat.progetto.ingsweb.persistance.dao.OrdineDAO;

public class MagazziniereDAOJDBC implements MagazziniereDAO {

	DBSource dbSource;

	public MagazziniereDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}

	@Override
	public void save(Magazziniere magazziniere) {
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "INSERT INTO magazziniere values(DEFAULT,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, magazziniere.getEmail());
			st.setString(2, magazziniere.getPassword());
			st.setString(3, magazziniere.getCognome());
			st.setString(4, magazziniere.getNome());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Magazziniere findByPrimaryKey(int id) {
		Magazziniere magazziniere = null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from magazziniere where id=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				int searchId = rs.getInt("id");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
				String password = rs.getString("password");

				magazziniere = new Magazziniere();
				magazziniere.setId(searchId);
				magazziniere.setCognome(cognome);
				magazziniere.setEmail(email);
				magazziniere.setNome(nome);
				magazziniere.setPassword(password);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return magazziniere;
	}

	@Override
	public List<Magazziniere> findAll() {
		List<Magazziniere> magazzinieri = new ArrayList<Magazziniere>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from magazziniere";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				int searchId = rs.getInt("id");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
				String password = rs.getString("password");

				Magazziniere magazziniere = new Magazziniere();
				magazziniere.setId(searchId);
				magazziniere.setCognome(cognome);
				magazziniere.setEmail(email);
				magazziniere.setNome(nome);
				magazziniere.setPassword(password);
				magazzinieri.add(magazziniere);

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return magazzinieri;
	}

	@Override
	public void update(Magazziniere magazziniere) {}

	@Override
	public void delete(Magazziniere magazziniere) {}

	@Override
	public List<Pacco> getElaboratedPackagesFromWarehouseWorker(Magazziniere magazziniere) {
		List<Pacco> pacchi = new ArrayList<Pacco>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from pacco where magazziniere=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, magazziniere.getId());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Pacco pacco = DBManager.getIstance().paccoDAO().findByPrimaryKey(rs.getInt("codice"));
				pacchi.add(pacco);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pacchi;
	}

	@Override
	public List<Ordine> getElaboratedOrdersFromWarehouseWorker(Magazziniere magazziniere) {
		List<Ordine> ordini = new ArrayList<Ordine>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from pacco where magazziniere=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, magazziniere.getId());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Ordine ordine = DBManager.getIstance().ordineDAO().findByPrimaryKey(rs.getInt("codice"));
				ordini.add(ordine);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordini;
	}

	@Override
	public Magazziniere findByEmail(String email) {
		Magazziniere magazziniere = null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from magazziniere where email=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				int searchId = rs.getInt("id");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String mail = rs.getString("email");
				String password = rs.getString("password");

				magazziniere = new Magazziniere();
				magazziniere.setId(searchId);
				magazziniere.setCognome(cognome);
				magazziniere.setEmail(mail);
				magazziniere.setNome(nome);
				magazziniere.setPassword(password);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return magazziniere;
	}
}
