package it.unical.mat.progetto.ingsweb.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.unical.mat.progetto.ingsweb.model.Log;
import it.unical.mat.progetto.ingsweb.model.Pacco;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;
import it.unical.mat.progetto.ingsweb.persistance.DBSource;
import it.unical.mat.progetto.ingsweb.persistance.dao.LogDAO;

public class LogDAOJDBC implements LogDAO {

	DBSource dbSource;
	
	public LogDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}

	@Override
	public void save(Log log, int id_pacco) {
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "INSERT INTO log values(DEFAULT,?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setDate(1, log.getData());
			st.setString(2, log.getStato());
			st.setString(3, log.getPosizione());
			st.setInt(4, id_pacco);
			if(log.getDescrizione()==null)
				st.setNull(5, java.sql.Types.VARCHAR);
			else
				st.setString(5, log.getDescrizione());
		
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Log findByPrimaryKey(int codice) {
		Log log = null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from log where codice=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, codice);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				int searchCode = rs.getInt("codice");
				Date data = rs.getDate("datalog");
				String stato = rs.getString("stato");
				String posizione = rs.getString("posizione");
				int codicePacco = rs.getInt("pacco");
				String descrizione = rs.getString("descrizione");

				log = new Log();
				log.setCodice(searchCode);
				log.setData(data);
				log.setStato(stato);
				log.setPosizione(posizione);
				log.setCodicePacco(codicePacco);
				log.setDescrizione(descrizione);

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return log;
	}

	@Override
	public List<Log> findAll() {
		List<Log> logs = new ArrayList<Log>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from log";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				int searchCode = rs.getInt("codice");
				Date data = rs.getDate("datalog");
				String stato = rs.getString("stato");
				String posizione = rs.getString("posizione");
				int codicePacco = rs.getInt("pacco");
				String descrizione = rs.getString("descrizione");

				Log log = new Log();
				log.setCodice(searchCode);
				log.setData(data);
				log.setStato(stato);
				log.setPosizione(posizione);
				log.setCodicePacco(codicePacco);
				log.setDescrizione(descrizione);
				logs.add(log);

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return logs;
	}

	@Override
	public void update(Log log) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Log log) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Pacco getPackageFromLog(Log log) {
		Pacco pacco = null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from log where codice=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, log.getCodice());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				pacco = DBManager.getIstance().paccoDAO().findByPrimaryKey(rs.getInt("pacco"));

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pacco;
	}

	@Override
	public List<Log> findByTrackingCode(String trackingCode) {
		List<Log> logs = new ArrayList<Log>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from log where pacco = (select codice from pacco where trackingcode = ?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1,  trackingCode);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int searchCode = rs.getInt("codice");
				Date data = rs.getDate("datalog");
				String stato = rs.getString("stato");
				String posizione = rs.getString("posizione");
				int codicePacco = rs.getInt("pacco");
				String descrizione = rs.getString("descrizione");

				Log log = new Log();
				log.setCodice(searchCode);
				log.setData(data);
				log.setStato(stato);
				log.setPosizione(posizione);
				log.setCodicePacco(codicePacco);
				log.setDescrizione(descrizione);
				logs.add(log);

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return logs;
	}
	
	

}
