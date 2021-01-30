package it.unical.mat.progetto.ingsweb.persistance.dao.jdbc;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import it.unical.mat.progetto.ingsweb.model.Corriere;
import it.unical.mat.progetto.ingsweb.model.Pacco;
import it.unical.mat.progetto.ingsweb.model.Utente;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;
import it.unical.mat.progetto.ingsweb.persistance.DBSource;
import it.unical.mat.progetto.ingsweb.persistance.dao.CorriereDAO;
 
public class CorriereDAOJDBC implements CorriereDAO {
 
	DBSource dbSource;
 
	public CorriereDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}
 
	@Override
	public void save(Corriere corriere) {
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "INSERT INTO corriere values(DEFAULT,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, corriere.getEmail());
			st.setString(2, corriere.getPassword());
			st.setString(3, corriere.getNome());
			st.setString(4, corriere.getCognome());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	@Override
	public Corriere findByPrimaryKey(int id) {
		Corriere corriere = null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from corriere where id=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				int searchId = rs.getInt("id");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
				String password = rs.getString("password");
				corriere = new Corriere();
				corriere.setId(searchId);
				corriere.setCognome(cognome);
				corriere.setEmail(email);
				corriere.setNome(nome);
				corriere.setPassword(password);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return corriere;
	}
 
	@Override
	public List<Corriere> findAll() {
		List<Corriere> corrieri = new ArrayList<Corriere>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from corriere";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				int searchId = rs.getInt("id");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
				String password = rs.getString("password");
				Corriere corriere = new Corriere();
				corriere.setId(searchId);
				corriere.setCognome(cognome);
				corriere.setEmail(email);
				corriere.setNome(nome);
				corriere.setPassword(password);
				corrieri.add(corriere); 
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return corrieri;
	}
 
	@Override
	public void update(Corriere corriere) {}
 
	@Override
	public void delete(Corriere corriere) {}
 
	@Override
	public List<Pacco> getPackagesFromCourier(Corriere corriere) {
		List<Pacco> pacchi = new ArrayList<Pacco>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from pacco where corriere=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, corriere.getId());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
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
	public List<Pacco> getPackagesFromMail(String mail) {
		List<Pacco> pacchi = new ArrayList<Pacco>();
		try{
			Connection conn = dbSource.getConnection();
			String query = "select * from pacco where corriere = (select id from corriere where email =? )";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, mail);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Pacco pacco = DBManager.getIstance().paccoDAO().findByPrimaryKey(rs.getInt("codice"));
				pacchi.add(pacco);
			}
			conn.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return pacchi;
	}

	@Override
	public Corriere findByEmail(String email) {
		Corriere corriere = null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from corriere where email=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				corriere = new Corriere();
				corriere.setId(rs.getInt("id"));
				corriere.setEmail(rs.getString("email"));
				corriere.setCognome(rs.getString("cognome"));
				corriere.setNome(rs.getString("nome"));
				corriere.setPassword(rs.getString("password"));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return corriere;
	}
}