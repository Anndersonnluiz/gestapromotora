package br.com.gestapromotora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Promotora;

public class PromotoraDao {

	public Promotora salvar(Promotora promotora) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		promotora = manager.merge(promotora);
		tx.commit();
		manager.close();
		return promotora;
	}

	public Promotora consultar(int idpromotora) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select p from Promotora p where p.idpromotora=" + idpromotora);
		Promotora promotora = null;
		if (q.getResultList().size() > 0) {
			promotora = (Promotora) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return promotora;
	}

	@SuppressWarnings("unchecked")
	public List<Promotora> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Promotora> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
