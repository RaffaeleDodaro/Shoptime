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
import it.unical.mat.progetto.ingsweb.model.Indirizzo;
import it.unical.mat.progetto.ingsweb.model.Ordine;
import it.unical.mat.progetto.ingsweb.model.Prodotto;
import it.unical.mat.progetto.ingsweb.model.Utente;
import it.unical.mat.progetto.ingsweb.model.Venditore;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;
import it.unical.mat.progetto.ingsweb.persistance.DBSource;
import it.unical.mat.progetto.ingsweb.persistance.dao.OrdineDAO;

public class OrdineDAOJDBC implements OrdineDAO {

	DBSource dbSource;

	public OrdineDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}

	@Override
	public void save(Ordine ordine, Utente utente, Indirizzo indirizzo, CartaDiCredito carta, boolean shoptime) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "INSERT INTO ordine values(?,?,?,?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, ordine.getCodice());
			st.setDate(2, ordine.getData());
			st.setString(3, "In Elaborazione");
			st.setDouble(4, ordine.getPrezzo());
			st.setInt(5, utente.getId());
			st.setInt(6, indirizzo.getCodice());
			st.setBoolean(7, shoptime);
			st.setString(8, carta.getNumero());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getMaxCode() {
		int maxCode = 0;
		try {
			Connection connection = dbSource.getConnection();
			String query = "select max(codice) as max from ordine";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				maxCode = rs.getInt("max");
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxCode;
	}

	/*@Override
	public Ordine findByPrimaryKey(int id) {
		Ordine ordine = new Ordine();
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "select * from ordine where codice=? and prodottishoptime = ? ";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, id);
			st.setBoolean(2, true);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				int codice = rs.getInt("codice");
				Date data = rs.getDate("dataordine");
				String stato = rs.getString("stato");
				float prezzo = rs.getFloat("totale");
				boolean bool = rs.getBoolean("prodottishoptime");
				Indirizzo indirizzo = DBManager.getIstance().indirizzoDAO().findByPrimaryKey(rs.getInt("indirizzo"));
				ordine.setCodice(codice);
				ordine.setData(data);
				ordine.setPrezzo(prezzo);
				ordine.setStato(stato);
				ordine.setIndirizzo(indirizzo);
				this.setProductsFromOrder(ordine);
				CartaDiCredito cdc = DBManager.getIstance().cartaDiCreditoDAO().findByPrimaryKey(rs.getString("cartadicredito"));
				ordine.setCartaDiCredito(cdc);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordine;
	}*/

	@Override
	public Ordine findByPrimaryKey(int id) {
		List<Ordine> ordini = new ArrayList<Ordine>();
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "select * from ordine where codice = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int codice = rs.getInt("codice");
				Date data = rs.getDate("dataordine");
				String stato = rs.getString("stato");
				float prezzo = rs.getFloat("totale");
				boolean bool = rs.getBoolean("prodottishoptime");
				Indirizzo indirizzo = DBManager.getIstance().indirizzoDAO().findByPrimaryKey(rs.getInt("indirizzo"));
				CartaDiCredito carta = DBManager.getIstance().cartaDiCreditoDAO().findByPrimaryKey(rs.getString("cartadicredito"));
				Ordine ordine = new Ordine();
				ordine.setCodice(codice);
				ordine.setData(data);
				ordine.setPrezzo(prezzo);
				ordine.setCartaDiCredito(carta);
				ordine.setStato(stato);
				ordine.setIndirizzo(indirizzo);
				this.setProductsFromOrder(ordine);
				ordini.add(ordine);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Ordine temp = ordini.get(0);
		Ordine ordine = new Ordine();
		float totale = 0;
		for(Ordine o : ordini) {
			totale += o.getPrezzo();
		}
		ordine.setCodice(id);
		ordine.setData(temp.getData());
		ordine.setIndirizzo(temp.getIndirizzo());
		ordine.setCartaDiCredito(temp.getCartaDiCredito());
		ordine.setStato(this.findByPrimaryKey(id).getStato());
		ordine.setPrezzo(totale);
		this.setProductsFromOrder(ordine);
		return ordine;
	}

	@Override
	public List<Ordine> findAll() {
		List<Ordine> ordini = new ArrayList<Ordine>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from ordine";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				int codice = rs.getInt("codice");
				Date data = rs.getDate("dataordine");
				String stato = rs.getString("stato");
				float prezzo = rs.getFloat("totale");
				boolean bool = rs.getBoolean("prodottishoptime");
				Indirizzo indirizzo = DBManager.getIstance().indirizzoDAO().findByPrimaryKey(rs.getInt("indirizzo"));
				Ordine ordine = new Ordine();
				ordine.setCodice(codice);
				ordine.setData(data);
				ordine.setPrezzo(prezzo);
				ordine.setStato(stato);
				ordine.setIndirizzo(indirizzo);
				
				CartaDiCredito cdc=DBManager.getIstance().cartaDiCreditoDAO().findByPrimaryKey(rs.getString("cartadicredito"));
				ordine.setCartaDiCredito(cdc);
				this.setProductsFromOrder(ordine);
				ordini.add(ordine);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("ordine size: "+ordini.size());
		return ordini;
	}

	@Override
	public void update(Ordine ordine) {
	}

	@Override
	public void delete(Ordine ordine) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "delete from ordine where codice = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, ordine.getCodice());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Utente getUserFromOrder(Ordine ordine) {
		Utente utente = null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select utente from ordine where codice = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, ordine.getCodice());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				utente = DBManager.getIstance().UtenteDAO().findByPrimaryKey(rs.getInt("utente"));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utente;
	}

	@Override
	public void setProductsFromOrder(Ordine ordine) {
		List<Prodotto> prodotti = new ArrayList<Prodotto>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from prodotto_ordine where ordine = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, ordine.getCodice());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				/*for (int i = 0; i < rs.getInt("quantita"); i++) {
					Prodotto prodotto = DBManager.getIstance().prodottoDAO().findByPrimaryKey(rs.getInt("prodotto"));
					//System.out.println("PREZZOOOOOO:  " + rs.getDouble("prezzo"));
					prodotto.setPrezzo(rs.getDouble("prezzo"));
					prodotti.add(prodotto);
				}*/
				Prodotto prodotto = DBManager.getIstance().prodottoDAO().findByPrimaryKey(rs.getInt("prodotto"));
				prodotto.setPrezzo(rs.getDouble("prezzo") *rs.getInt("quantita"));
				prodotto.setInOrdine(rs.getInt("quantita"));
				prodotti.add(prodotto);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ordine.setProdotti(prodotti);
	}

	@Override
	public void addOrderProduct(Prodotto prodotto, Ordine ordine, boolean shoptime) {
		//System.out.println("addOrderProduct "+ prodotto.getCodice() + quantita);
		try {
			Connection conn = dbSource.getConnection();
			String query = "INSERT INTO prodotto_ordine values(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, prodotto.getCodice());
			st.setInt(2, ordine.getCodice());
			st.setInt(3, prodotto.getQuantita());
			st.setBoolean(4, shoptime);
			st.setString(5,"");
			st.setInt(6, prodotto.getVenditore().getId());
			st.setDouble(7, prodotto.getPrezzo());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeOrderProduct(Prodotto prodotto, Ordine ordine) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "DELETE FROM prodotto_ordine where prodotto = ? and ordine = ? and prodottoshoptime = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, prodotto.getCodice());
			st.setInt(2, ordine.getCodice());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateOrderProduct(Prodotto prodotto, Ordine ordine, int quantita) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "UPDATE prodotto_ordine set quantita = ? where prodotto = ? and ordine = ? and prodottoshoptime = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, quantita);
			st.setInt(2, prodotto.getCodice());
			st.setInt(3, ordine.getCodice());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Ordine> findAllOrdersToBeProcessed() {
		List<Ordine> ordini = new ArrayList<Ordine>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from ordine where prodottishoptime = true and stato = 'In Elaborazione'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				int codice = rs.getInt("codice");
				Date data = rs.getDate("dataordine");
				String stato = rs.getString("stato");
				float prezzo = rs.getFloat("totale");
				boolean bool = rs.getBoolean("prodottishoptime");
				Indirizzo indirizzo = DBManager.getIstance().indirizzoDAO().findByPrimaryKey(rs.getInt("indirizzo"));
				Ordine ordine = new Ordine();
				CartaDiCredito carta = DBManager.getIstance().cartaDiCreditoDAO().findByPrimaryKey(rs.getString("cartadicredito"));
				ordine.setCartaDiCredito(carta);
				ordine.setCodice(codice);
				ordine.setData(data);
				ordine.setPrezzo(prezzo);
				ordine.setStato(stato);
				ordine.setIndirizzo(indirizzo);
				this.setProductsFromOrderShoptime(ordine);
				ordini.add(ordine);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordini;
	}
	
	
	@Override
	public List<Ordine> findAllOrdersProcessed() {
		List<Ordine> ordini = new ArrayList<Ordine>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from ordine where prodottishoptime = true and stato = 'Elaborato'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				int codice = rs.getInt("codice");
				Date data = rs.getDate("dataordine");
				String stato = rs.getString("stato");
				float prezzo = rs.getFloat("totale");
				boolean bool = rs.getBoolean("prodottishoptime");
				Indirizzo indirizzo = DBManager.getIstance().indirizzoDAO().findByPrimaryKey(rs.getInt("indirizzo"));
				Ordine ordine = new Ordine();
				CartaDiCredito carta = DBManager.getIstance().cartaDiCreditoDAO().findByPrimaryKey(rs.getString("cartadicredito"));
				ordine.setCartaDiCredito(carta);
				ordine.setCodice(codice);
				ordine.setData(data);
				ordine.setPrezzo(prezzo);
				ordine.setStato(stato);
				ordine.setIndirizzo(indirizzo);
				this.setProductsFromOrderShoptime(ordine);
				ordini.add(ordine);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordini;
	}
	


	@Override
	public void updateOrderState(Ordine ordine, String stato) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "update ordine set stato = ? where codice = ? and prodottishoptime = true";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, stato);
			st.setInt(2, ordine.getCodice());
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateOrderProductMessage(int codiceOrdine, int codiceProdotto, String messaggio) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "update prodotto_ordine set messaggio = ? where ordine = ? and prodotto = ? and prodottoshoptime = true ";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, messaggio);
			st.setInt(2,codiceOrdine);
			st.setInt(3, codiceProdotto);
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	


	@Override
	public Ordine findByPrimaryKeyShoptime(int id) {
		Ordine ordine = null;
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "select * from ordine where codice=? and prodottishoptime = true ";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				int codice = rs.getInt("codice");
				Date data = rs.getDate("dataordine");
				String stato = rs.getString("stato");
				float prezzo = rs.getFloat("totale");
				boolean bool = rs.getBoolean("prodottishoptime");
				Indirizzo indirizzo = DBManager.getIstance().indirizzoDAO().findByPrimaryKey(rs.getInt("indirizzo"));
				ordine = new Ordine();
				ordine.setCodice(codice);
				ordine.setData(data);
				ordine.setPrezzo(prezzo);
				ordine.setStato(stato);
				ordine.setIndirizzo(indirizzo);
				this.setProductsFromOrderShoptime(ordine);
				CartaDiCredito cdc = DBManager.getIstance().cartaDiCreditoDAO().findByPrimaryKey(rs.getString("cartadicredito"));
				ordine.setCartaDiCredito(cdc);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordine;
	}
	
	@Override
	public void setProductsFromOrderShoptime(Ordine ordine) {
		List<Prodotto> prodotti = new ArrayList<Prodotto>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from prodotto_ordine where ordine = ? and prodottoshoptime = true";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, ordine.getCodice());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				for (int i = 0; i < rs.getInt("quantita"); i++) {
					Prodotto prodotto = DBManager.getIstance().prodottoDAO().findByPrimaryKey(rs.getInt("prodotto"));
					prodotto.setPrezzo(rs.getDouble("prezzo"));
					prodotti.add(prodotto);
				}
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ordine.setProdotti(prodotti);
	}

	@Override
	public List<Ordine> findAllOrdersShipped() {
		List<Ordine> ordini = new ArrayList<Ordine>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from ordine where prodottishoptime = true and stato = 'Spedito'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				int codice = rs.getInt("codice");
				Date data = rs.getDate("dataordine");
				String stato = rs.getString("stato");
				float prezzo = rs.getFloat("totale");
				boolean bool = rs.getBoolean("prodottishoptime");
				Indirizzo indirizzo = DBManager.getIstance().indirizzoDAO().findByPrimaryKey(rs.getInt("indirizzo"));
				Ordine ordine = new Ordine();
				ordine.setCodice(codice);
				ordine.setData(data);
				ordine.setPrezzo(prezzo);
				ordine.setStato(stato);
				ordine.setIndirizzo(indirizzo);
				this.setProductsFromOrderShoptime(ordine);
				ordini.add(ordine);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordini;
	}

	@Override
	public List<Ordine> findAllShoptimeOrders() {
		List<Ordine> ordini = new ArrayList<Ordine>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from ordine where prodottishoptime = true";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				int codice = rs.getInt("codice");
				Date data = rs.getDate("dataordine");
				String stato = rs.getString("stato");
				float prezzo = rs.getFloat("totale");
				boolean bool = rs.getBoolean("prodottishoptime");
				Indirizzo indirizzo = DBManager.getIstance().indirizzoDAO().findByPrimaryKey(rs.getInt("indirizzo"));
				Ordine ordine = new Ordine();
				ordine.setCodice(codice);
				ordine.setData(data);
				ordine.setPrezzo(prezzo);
				ordine.setStato(stato);
				ordine.setIndirizzo(indirizzo);
				this.setProductsFromOrderShoptime(ordine);
				ordini.add(ordine);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordini;
	}
	
	@Override
	public String gerOrderTracking(Ordine ordine) {
		String tracking = "";
		try {
			Connection conn = dbSource.getConnection();
			String query= "select trackingcode from pacco where ordine=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, ordine.getCodice());
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				tracking = rs.getString("trackingcode");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return tracking;
	}
	
}
