package br.com.gestapromotora.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Tipocolaborador;

public class TipoColaboradorDao {

	public Tipocolaborador salvar(Tipocolaborador tipocolaborador) throws SQLException {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		tipocolaborador = manager.merge(tipocolaborador);
		tx.commit();

		return tipocolaborador;
	}

	@SuppressWarnings("unchecked")
	public List<Tipocolaborador> listar(String sql) throws SQLException {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		Query q = manager.createQuery(sql);
		List<Tipocolaborador> lista = q.getResultList();

		return lista;
	}

	public void excluir(int idtipocolaborador) throws SQLException {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("Select t from Tipocolaborador t where t.idtipocolaborador=" + idtipocolaborador);
		if (q.getResultList().size() > 0) {
			Tipocolaborador tipocolaborador = (Tipocolaborador) q.getResultList().get(0);
			manager.remove(tipocolaborador);
		}
		tx.commit();

	}
}
