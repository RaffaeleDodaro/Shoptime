package it.unical.mat.progetto.ingsweb.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.unical.mat.progetto.ingsweb.model.Corriere;
import it.unical.mat.progetto.ingsweb.model.Indirizzo;
import it.unical.mat.progetto.ingsweb.model.Log;
import it.unical.mat.progetto.ingsweb.model.Magazziniere;
import it.unical.mat.progetto.ingsweb.model.Ordine;
import it.unical.mat.progetto.ingsweb.model.Pacco;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;
import it.unical.mat.progetto.ingsweb.persistance.DBSource;
import it.unical.mat.progetto.ingsweb.persistance.dao.PaccoDAO;

public class PaccoDAOJDBC implements PaccoDAO {

	DBSource dbSource;

	public PaccoDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}

	@Override
	public void save(Pacco pacco, int codice_ordine, int id_magazziniere, int codice_indirizzo) {
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "INSERT INTO pacco values(DEFAULT,?,?,?,?,?,true)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, pacco.getTrackingCode());
			st.setInt(2, id_magazziniere);
			st.setInt(3, codice_ordine);
			st.setInt(4, codice_indirizzo);
			st.setNull(5, java.sql.Types.INTEGER);
		
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Pacco findByPrimaryKey(int codice) {
		Pacco pacco = null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from pacco where codice=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, codice);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				int searchCode = rs.getInt("codice");
				String trackingCode = rs.getString("trackingcode");
				Indirizzo indirizzo = DBManager.getIstance().indirizzoDAO().findByPrimaryKey(rs.getInt("indirizzo"));
				pacco = new Pacco();
				pacco.setCodice(searchCode);
				pacco.setIndirizzo(indirizzo);
				pacco.setTrackingCode(trackingCode);
				List<Log> logs = getLogsOfPackage(pacco);
				pacco.setLogs(logs);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pacco;
	}

	@Override
	public List<Pacco> findAll() {
		List<Pacco> pacchi = new ArrayList<Pacco>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from pacco";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				int searchCode = rs.getInt("codice");
				String trackingCode = rs.getString("trackingcode");
				Indirizzo indirizzo = DBManager.getIstance().indirizzoDAO().findByPrimaryKey(rs.getInt("indirizzo"));

				Pacco pacco = new Pacco();
				pacco.setCodice(searchCode);
				pacco.setIndirizzo(indirizzo);
				pacco.setTrackingCode(trackingCode);
				pacchi.add(pacco);

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pacchi;
	}

	@Override
	public void update(Pacco pacco) {}

	@Override
	public void delete(Pacco pacco) {}

	@Override
	public Corriere getCourierFromPackage(Pacco pacco) {
		Corriere corriere = null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from pacco where codice=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, pacco.getCodice());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				corriere = DBManager.getIstance().corriereDAO().findByPrimaryKey(rs.getInt("corriere"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return corriere;
	}

	@Override
	public Magazziniere getWarehouseWorkerFromPackage(Pacco pacco) {
		Magazziniere magazziniere = null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from pacco where codice=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, pacco.getCodice());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				magazziniere = DBManager.getIstance().magazziniereDAO().findByPrimaryKey(rs.getInt("magazziniere"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return magazziniere;
	}

	@Override
	public Ordine getOrderFromPackage(Pacco pacco) {
		Ordine ordine = null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from pacco where codice=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, pacco.getCodice());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				ordine = DBManager.getIstance().ordineDAO().findByPrimaryKey(rs.getInt("ordine"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordine;
	}

	@Override
	public void updateCourierOfPackage(Pacco pacco, int id_corriere) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "UPDATE pacco SET corriere = ? WHERE codice=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, id_corriere);
			st.setInt(2, pacco.getCodice());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	@Override
	public List<Log> getLogsOfPackage(Pacco pacco) {
		List<Log> logs = new ArrayList<Log>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select codice from log where pacco = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, pacco.getCodice());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Log log = DBManager.getIstance().logDAO().findByPrimaryKey(rs.getInt("codice"));
				logs.add(log);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return logs;
	}

	@Override
	public Pacco findByOrder(Ordine ordine) {
		Pacco pacco = null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from pacco where ordine=? and prodottishoptime=true";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, ordine.getCodice());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				int searchCode = rs.getInt("codice");
				String trackingCode = rs.getString("trackingcode");
				Indirizzo indirizzo = DBManager.getIstance().indirizzoDAO().findByPrimaryKey(rs.getInt("indirizzo"));

				pacco = new Pacco();
				pacco.setCodice(searchCode);
				pacco.setIndirizzo(indirizzo);
				pacco.setTrackingCode(trackingCode);

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pacco;
	}
	
	

}
