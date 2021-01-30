package it.unical.mat.progetto.ingsweb.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import it.unical.mat.progetto.ingsweb.model.Categoria;
import it.unical.mat.progetto.ingsweb.model.Prodotto;
import it.unical.mat.progetto.ingsweb.persistance.DBManager;
import it.unical.mat.progetto.ingsweb.persistance.DBSource;
import it.unical.mat.progetto.ingsweb.persistance.dao.CategoriaDAO;
 
public class CategoriaDAOJDBC implements CategoriaDAO {
 
	DBSource dbSource;

	public CategoriaDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}
 
	@Override
	public void save(Categoria categoria) {
	}

	@Override
	public Categoria findByPrimaryKey(String nome) {
		Categoria categoria = new Categoria();
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "select * from categoria where nome=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, nome);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String nomeC = rs.getString("nome");
				categoria.setNome(nomeC);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categoria;
	}

	@Override
	public List<Categoria> findAll() {
		List<Categoria> categorie = new ArrayList<Categoria>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from categoria";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String nome = rs.getString("nome");
				Categoria cat = new Categoria();
				cat.setNome(nome);
				categorie.add(cat);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorie;
	}
 
	@Override
	public void update(Categoria categoria) {
	}

	@Override
	public void delete(Categoria categoria) {
	}

	@Override
	public List<Prodotto> getProductFromCategories(String nome) {
		List<Prodotto> prodotti = new ArrayList<Prodotto>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select prodotto from prodotto_categoria where categoria = ?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, nome);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Prodotto prodotto = DBManager.getIstance().prodottoDAO().findByPrimaryKey(rs.getInt("prodotto"));
				prodotti.add(prodotto);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return prodotti;

	}

}
