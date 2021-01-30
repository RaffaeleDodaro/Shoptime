package it.unical.mat.progetto.ingsweb.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.unical.mat.progetto.ingsweb.model.Indirizzo;
import it.unical.mat.progetto.ingsweb.model.Utente;
import it.unical.mat.progetto.ingsweb.persistance.DBSource;
import it.unical.mat.progetto.ingsweb.persistance.dao.IndirizzoDAO;

public class IndirizzoDAOJDBC implements IndirizzoDAO {

	DBSource dbSource;

	public IndirizzoDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}

	@Override
	public void save(Indirizzo indirizzo) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "INSERT INTO indirizzo values(DEFAULT,?,?,?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, indirizzo.getCap());
			st.setString(2, indirizzo.getVia());
			st.setString(3, indirizzo.getRegione());
			st.setString(4, indirizzo.getCitta());
			st.setString(5,  indirizzo.getDestinatario());
            st.setString(6,  indirizzo.getNumTelefono());
            st.setString(7,  indirizzo.getNumeroTelefonicoDestinatario());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Indirizzo findByPrimaryKey(int id) {
		Indirizzo indirizzo = new Indirizzo();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from indirizzo where codice=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				int codice = rs.getInt("codice");
				int cap = rs.getInt("cap");
				String via = rs.getString("via");
				String regione = rs.getString("regione");
				String citta = rs.getString("citta");
				String destinatario = rs.getString("destinatario");
				String numTelefono = rs.getString("numtelefono");
				indirizzo.setCap(cap);
				indirizzo.setCitta(citta);
				indirizzo.setCodice(codice);
				indirizzo.setRegione(regione);
				indirizzo.setVia(via);
				indirizzo.setDestinatario(destinatario);
                indirizzo.setNumTelefono(numTelefono);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return indirizzo;
	}

	@Override
	public List<Indirizzo> findAll() {
		List<Indirizzo> indirizzi = new ArrayList<Indirizzo>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from indirizzo";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				Indirizzo indirizzo = new Indirizzo();
				indirizzo.setCap(rs.getInt("cap"));
				indirizzo.setVia(rs.getString("via"));
				indirizzo.setRegione(rs.getString("regione"));
				indirizzo.setCitta(rs.getString("citta"));
				indirizzo.setDestinatario(rs.getString("destinatario"));
				indirizzo.setNumTelefono(rs.getString("numTelefono"));
				indirizzo.setCodice(rs.getInt("codice"));
				indirizzi.add(indirizzo);				
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return indirizzi;
	}
	
	
	@Override
	public void update(Indirizzo indirizzo) {
	}

	@Override
	public void delete(Indirizzo indirizzo, int id) {
		
		try {
			Connection conn = dbSource.getConnection();
			String query = "delete from utente_indirizzo where utente = ? and indirizzo=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, id);
			st.setInt(2, indirizzo.getCodice());
			st.executeUpdate();

			/*query = "delete from cartadicredito where numero = ?";
			st = conn.prepareStatement(query);
			st.setString(1, cartaDiCredito.getNumero());
			st.executeUpdate();*/
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public Indirizzo findByValue(Indirizzo ind) {
		Indirizzo indirizzo=null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from indirizzo where cap=? and via=? and regione=? and citta =?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, ind.getCap());
			st.setString(2, ind.getVia());
			st.setString(3, ind.getRegione());
			st.setString(4, ind.getCitta());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				indirizzo= new Indirizzo();
				indirizzo.setDestinatario(rs.getString("destinatario"));
				indirizzo.setCap(rs.getInt("cap"));
				indirizzo.setCitta(rs.getString("citta"));
				indirizzo.setCodice(rs.getInt("codice"));
				indirizzo.setRegione(rs.getString("regione"));
				indirizzo.setVia(rs.getString("via"));
				indirizzo.setNumTelefono(rs.getString("numTelefono"));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return indirizzo;
	}

	
	
}