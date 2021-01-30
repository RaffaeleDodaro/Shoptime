package it.unical.mat.progetto.ingsweb.persistance.dao.jdbc;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import it.unical.mat.progetto.ingsweb.model.CartaDiCredito;
import it.unical.mat.progetto.ingsweb.model.Indirizzo;
import it.unical.mat.progetto.ingsweb.model.Ordine;
import it.unical.mat.progetto.ingsweb.model.Prodotto;
import it.unical.mat.progetto.ingsweb.model.Recensione;
import it.unical.mat.progetto.ingsweb.model.Utente;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;
import it.unical.mat.progetto.ingsweb.persistance.DBSource;
import it.unical.mat.progetto.ingsweb.persistance.dao.OrdineDAO;
import it.unical.mat.progetto.ingsweb.persistance.dao.UtenteDAO;
 
public class UtenteDAOJDBC implements UtenteDAO {
 
	DBSource dbSource;
 
	public UtenteDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}
 
	@Override
	public void save(Utente utente,Indirizzo indirizzo) {
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "INSERT INTO utente values(DEFAULT,?,?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, utente.getNome());
			st.setString(2, utente.getCognome());
			st.setString(3, utente.getNumTelefonico());
			st.setString(4, utente.getEmail());
			st.setInt(5, utente.getIndirizzoPredefinito().getCodice());
			st.setString(6, utente.getPassword());
			st.executeUpdate();
			
			
			/*query = "INSERT INTO utente_cartadicredito values(?,?)";
			st = conn.prepareStatement(query);
			st.setInt(1, findByEmail(utente.getEmail(), utente.getPassword()).getId());
			st.setString(2, cdc.getNumero());
			st.executeUpdate();*/
			
			
			
			query = "INSERT INTO utente_indirizzo values(?,?)";
			st = conn.prepareStatement(query);
			st.setInt(1, findByEmail(utente.getEmail(), utente.getPassword()).getId());
			st.setInt(2, DBManager.getIstance().indirizzoDAO().findByValue(indirizzo).getCodice());
			st.executeUpdate();
			
			
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	@Override
	public Utente findByPrimaryKey(int id) {
		Utente utente = null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from utente where id=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				int searchId = rs.getInt("id");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String numTel = rs.getString("numtelefonico");
				utente = new Utente();
				utente.setId(searchId);
				utente.setCognome(cognome);
				utente.setEmail(email);
				utente.setNome(nome);
				utente.setNumTelefonico(numTel);
				utente.setPassword(password);
				//INSERIRE INDIRIZZO PREDEFINITO
				//utente.setIndirizzoPredefinito(indirizzo);
				
				}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utente;
	}
 
	@Override
	public List<Utente> findAll() {
		List<Utente> utenti = new ArrayList<Utente>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from utente";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				int searchId = rs.getInt("id");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String numTel = rs.getString("numtelefonico");
				Utente utente = null;
				utente = new Utente();
				utente.setId(searchId);
				utente.setCognome(cognome);
				utente.setEmail(email);
				utente.setNome(nome);
				utente.setNumTelefonico(numTel);
				utente.setPassword(password);
				utenti.add(utente);
				
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utenti;
	}
 
	@Override
	public void update(Utente utente) {}
 
	@Override
	public void delete(Utente utente) {}
 
	@Override
	public void setUserCards(Utente utente) {
		List<CartaDiCredito> carte = new ArrayList<CartaDiCredito>();
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "select * from utente_cartadicredito where utente = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, utente.getId());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String numeroCarta = rs.getString("cartadicredito");
				CartaDiCredito carta = DBManager.getIstance().cartaDiCreditoDAO().findByPrimaryKey(numeroCarta);
				carte.add(carta);
				
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		utente.setCarte(carte);	
	}
 
	@Override
	public void setUserAddresses(Utente utente) {
		List<Indirizzo> indirizzi = new ArrayList<Indirizzo>();
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "select * from utente_indirizzo where utente = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, utente.getId());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("indirizzo");
				Indirizzo indirizzo = DBManager.getIstance().indirizzoDAO().findByPrimaryKey(id);
				indirizzi.add(indirizzo);
				
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		utente.setIndirizzi(indirizzi);	
	}
 
	@Override
	public void addUserCard(Utente utente, CartaDiCredito carta) {
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "INSERT INTO utente_cartadicredito values(?,?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, utente.getId());
			st.setString(2, carta.getNumero());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	@Override
	public void addUserAddress(Utente utente, Indirizzo indirizzo) {
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "INSERT INTO utente_indirizzo values(?,?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, utente.getId());
			st.setInt(2, indirizzo.getCodice());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	@Override
	public void addUserReview(Utente utente, Recensione recensione) {
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "INSERT INTO recensione values(?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, utente.getId());
			st.setInt(2, recensione.getProdotto().getCodice());
			st.setInt(3, recensione.getValutazione());
			st.setString(4, recensione.getTestoRecensione());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	@Override
	public void setUserReviews(Utente utente) {
		List<Recensione> recensioni = new ArrayList<Recensione>();
		Connection conn;
		try {
			conn = dbSource.getConnection();
			//MODIFICARE CON DATABASE PRONTO
			String query = "select * from recensione where utente = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, utente.getId());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Recensione recensione = new Recensione();
				recensione.setTestoRecensione(rs.getString("testo"));
				recensione.setValutazione(rs.getByte("valutazione"));
				Prodotto prodotto = DBManager.getIstance().prodottoDAO().findByPrimaryKey(rs.getInt("prodotto"));
				recensione.setProdotto(prodotto);
				recensione.setUtente(utente);
				recensioni.add(recensione);
 
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		utente.setRecensioni(recensioni);	
	}
 
	@Override
	public void setUserOrders(Utente utente) {
		List<Ordine> ordini = new ArrayList<Ordine>();
		List<Integer> codes = new ArrayList<Integer>();
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "select * from ordine where utente = ?";
			PreparedStatement st = conn.prepareStatement(query);
			System.out.println(utente.getId());
			st.setInt(1, utente.getId());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				if(!codes.contains(rs.getInt("codice"))) {
					Ordine ordine = new Ordine();
					ordine.setCodice(rs.getInt("codice"));
					codes.add(rs.getInt("codice"));
					ordine.setStato(rs.getString("stato"));
					ordine.setPrezzo(rs.getFloat("totale"));
					ordine.setCartaDiCredito(DBManager.getIstance().cartaDiCreditoDAO().findByPrimaryKey(rs.getString("cartadicredito")));
					ordine.setIndirizzo(DBManager.getIstance().indirizzoDAO().findByPrimaryKey(rs.getInt("indirizzo")));
					ordine.setData(rs.getDate("dataordine"));
					OrdineDAO ordineDao = new OrdineDAOJDBC(dbSource);
					ordineDao.setProductsFromOrder(ordine);
					ordini.add(ordine);
					codes.add(rs.getInt("codice"));
				}
				
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		utente.setOrdini(ordini);
	}
 
	@Override
	public void removeUserCard(Utente utente, CartaDiCredito carta) {
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "DELETE FROM utente_cartadicredito where utente = ? and cartadicredito = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, utente.getId());
			st.setString(2, carta.getNumero());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	@Override
	public void removeUserAddress(Utente utente, Indirizzo indirizzo) {
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "DELETE FROM utente_indirizzo where utente = ? and indirizzo = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, utente.getId());
			st.setInt(2, indirizzo.getCodice());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	@Override
	public void removeUserReview(Utente utente, Recensione recensione) {
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "DELETE FROM utente_indirizzo where utente = ? and prodotto = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, utente.getId());
			st.setInt(2, recensione.getProdotto().getCodice());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
 
	}

	@Override
	public Utente findByEmail(String email, String password) {
		Utente utente = null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from utente where email=? and password= ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, email);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				int searchId = rs.getInt("id");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String emailU = rs.getString("email");
				String passwordU = rs.getString("password");
				String numTel = rs.getString("numtelefonico");
				utente = new Utente();
				utente.setId(searchId);
				utente.setCognome(cognome);
				utente.setEmail(emailU);
				utente.setNome(nome);
				utente.setNumTelefonico(numTel);
				utente.setPassword(passwordU);
				//INSERIRE INDIRIZZO PREDEFINITO
				//utente.setIndirizzoPredefinito(indirizzo);
				
				}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utente;
	}
	
	
	
	@Override
	public Utente checkEmail(String email) {
		Utente utente = null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from utente where email=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				int searchId = rs.getInt("id");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String emailU = rs.getString("email");
				String passwordU = rs.getString("password");
				String numTel = rs.getString("numtelefonico");
				utente = new Utente();
				utente.setId(searchId);
				utente.setCognome(cognome);
				utente.setEmail(emailU);
				utente.setNome(nome);
				utente.setNumTelefonico(numTel);
				utente.setPassword(passwordU);				
				}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utente;
	}
}