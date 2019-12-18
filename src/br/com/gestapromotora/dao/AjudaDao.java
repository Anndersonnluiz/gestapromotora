package br.com.gestapromotora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Ajuda;

public class AjudaDao {

	public Ajuda salvar(Ajuda ajuda) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		ajuda = manager.merge(ajuda);
		tx.commit();
		manager.close();
		return ajuda;
	}

	public Ajuda consultar(int idajuda) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select a from Ajuda a where a.idajuda=" + idajuda);
		Ajuda ajuda = null;
		if (q.getResultList().size() > 0) {
			ajuda = (Ajuda) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return ajuda;
	}

	@SuppressWarnings("unchecked")
	public List<Ajuda> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Ajuda> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
