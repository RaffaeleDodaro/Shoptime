package it.unical.mat.progetto.ingsweb.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.unical.mat.progetto.ingsweb.model.Prodotto;
import it.unical.mat.progetto.ingsweb.model.Venditore;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;
import it.unical.mat.progetto.ingsweb.persistance.DBSource;
import it.unical.mat.progetto.ingsweb.persistance.dao.ProdottoDAO;

public class ProdottoDAOJDBC implements ProdottoDAO {

	DBSource dbSource;

	public ProdottoDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}

	@Override
	public void save(Prodotto prodotto, int venditore, int quantita, String categoria) {
		Connection conn;
		try {
			/*
			 * conn = dbSource.getConnection(); System.out.println(categoria +
			 * " categoria"); String query = "INSERT INTO prodotto values(DEFAULT,?, ?,?)";
			 * PreparedStatement st = conn.prepareStatement(query); st.setString(1,
			 * prodotto.getDescrizione()); // st.setDouble(2, prodotto.getPrezzo());
			 * st.setString(2, prodotto.getUrlImg()); st.setString(3, prodotto.getNome());
			 * st.executeUpdate();
			 * 
			 * Prodotto p = findByName(prodotto.getNome());
			 * 
			 * // INSERT IN PRODOTTO_VENDITORE query =
			 * "INSERT INTO prodotto_venditore values(?,?,?,?)"; st =
			 * conn.prepareStatement(query); st.setInt(1, p.getCodice()); st.setInt(2,
			 * venditore); st.setInt(3, quantita); st.setDouble(4, prodotto.getPrezzo());
			 * st.executeUpdate();
			 * 
			 * // INSERT IN PRODOTTO_CATEGORIA query =
			 * "INSERT INTO prodotto_categoria values(?,?)"; st =
			 * conn.prepareStatement(query); st.setInt(1, p.getCodice()); st.setString(2,
			 * categoria); st.executeUpdate();
			 * 
			 * conn.close();
			 */

			conn = dbSource.getConnection();
			Prodotto p = findByName(prodotto.getNome());

			if (p == null) {
				p = new Prodotto();
				String query = "INSERT INTO prodotto values(DEFAULT,?,?,?)";
				PreparedStatement st = conn.prepareStatement(query);
				st.setString(1, prodotto.getDescrizione()); //
				// st.setDouble(2, prodotto.getPrezzo());
				st.setString(2, prodotto.getUrlImg());
				st.setString(3, prodotto.getNome());
				st.executeUpdate();
				
				Prodotto p2 = findByName(prodotto.getNome());
				
				// INSERT IN PRODOTTO_VENDITORE
				System.out.println("quiiiiii");
				query = "INSERT INTO prodotto_venditore values(?,?,?,?)";
				st = conn.prepareStatement(query);
				System.out.println("codice " + p2.getCodice());
				st.setInt(1, p2.getCodice());
				st.setInt(2, venditore);
				st.setInt(3, quantita);
				st.setDouble(4, prodotto.getPrezzo());
				st.executeUpdate();

				// INSERT IN PRODOTTO_CATEGORIA
				query = "INSERT INTO prodotto_categoria values(?,?)";
				st = conn.prepareStatement(query);
				st.setInt(1, p2.getCodice());
				st.setString(2, categoria);
				st.executeUpdate();
			} else {

				String query = "UPDATE prodotto_venditore SET disponibilita_venditore = ? WHERE prodotto = ? and venditore =?;";
				PreparedStatement st = conn.prepareStatement(query);
				st.setInt(1, quantita);
				st.setInt(2, p.getCodice());
				st.setInt(3, venditore);
				st.executeUpdate();
			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Prodotto findByPrimaryKey(long id) {
		Prodotto prodotto = new Prodotto();
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "select * from prodotto where codice=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setLong(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				int searchCode = rs.getInt("codice");
				String nome = rs.getString("nome");
				String descrizione = rs.getString("descrizione");
				String urlImg = rs.getString("urlImg");
				//double prezzo = rs.getDouble("prezzo");
				prodotto.setCodice(searchCode);
				prodotto.setDescrizione(descrizione);
				prodotto.setNome(nome);
				// prodotto.setPrezzo(prezzo);
				prodotto.setUrlImg(urlImg);

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prodotto;
	}

	@Override
	public List<Prodotto> findAll() {
		List<Prodotto> prodotti = new ArrayList<Prodotto>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from prodotto";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				int searchCode = rs.getInt("codice");
				String nome = rs.getString("nome");
				String descrizione = rs.getString("descrizione");
				String urlImg = rs.getString("urlImg");
				// float prezzo = rs.getFloat("prezzo");
				Prodotto prodotto = new Prodotto();
				prodotto.setCodice(searchCode);
				prodotto.setDescrizione(descrizione);
				prodotto.setNome(nome);
				// prodotto.setPrezzo(prezzo);
				prodotto.setUrlImg(urlImg);
				prodotti.add(prodotto);

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prodotti;
	}

	@Override
	public void update(Prodotto prodotto) {
	}

	@Override
	public void delete(Prodotto prodotto) {
	}

	@Override
	public List<Venditore> getSellersFromProduct(Prodotto prodotto) {
		List<Venditore> venditori = new ArrayList<Venditore>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select venditore from prodotto_venditore where prodotto = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, prodotto.getCodice());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Venditore venditore = DBManager.getIstance().venditoreDAO().findByPrimaryKey(rs.getInt("venditore"));
				venditori.add(venditore);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return venditori;
	}

	@Override
	public Prodotto findByName(String name) {
		Prodotto prodotto = null;
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "select * from prodotto where nome=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, name);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				prodotto = new Prodotto();
				int searchCode = rs.getInt("codice");
				String nome = rs.getString("nome");
				String descrizione = rs.getString("descrizione");
				String urlImg = rs.getString("urlImg");
				//float prezzo = rs.getFloat("prezzo");
				prodotto.setCodice(searchCode);
				prodotto.setDescrizione(descrizione);
				prodotto.setNome(nome);
				//prodotto.setPrezzo(prezzo);
				prodotto.setUrlImg(urlImg);

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prodotto;
	}

	@Override
	public int getShoptimeProductQuantity(int codiceProdotto) {
		int quantity = -1;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from prodotto_venditore where prodotto = ? and venditore = (select id from utente where nome = ?) ";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, codiceProdotto);
			st.setString(2, "Shoptime");
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				quantity = rs.getInt("disponibilita_venditore");

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quantity;
	}

	@Override
	public void updateShoptimeProductQuantity(int codiceProdotto, int quantita) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "update prodotto_venditore set disponibilita_venditore = ? where prodotto = ? and venditore = (select id from utente where nome = ?) ";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, quantita);
			st.setInt(2, codiceProdotto);
			st.setString(3, "Shoptime");
			st.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Prodotto> getProductsFromSeller(int id) {
		List<Prodotto> prodotti = new ArrayList<Prodotto>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select prodotto from prodotto_venditore where venditore = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Prodotto prodotto = findByPrimaryKey(rs.getInt("prodotto"));
				prodotti.add(prodotto);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prodotti;
	}

	@Override
	public double getPriceProductFromSeller(Venditore venditore, Prodotto prodotto) {
		double prezzo = 0.0;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select prezzo from prodotto_venditore where venditore = ? and prodotto = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, venditore.getId());
			st.setInt(2, prodotto.getCodice());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				prezzo = rs.getDouble("prezzo");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prezzo;
	}

	
}
