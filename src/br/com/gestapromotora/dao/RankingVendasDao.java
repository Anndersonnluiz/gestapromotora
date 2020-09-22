package br.com.gestapromotora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Rankingvendas;

public class RankingVendasDao {

	
	public Rankingvendas salvar(Rankingvendas rankingvendas) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		rankingvendas = manager.merge(rankingvendas);
		tx.commit();
		manager.close();
		return rankingvendas;
	}

	public Rankingvendas consultar(int idrankingvendas) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select r from Rankingvendas r where r.idrankingvendas=" + idrankingvendas);
		Rankingvendas rankingvendas = null;
		if (q.getResultList().size() > 0) {
			rankingvendas = (Rankingvendas) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return rankingvendas;
	}

	@SuppressWarnings("unchecked")
	public List<Rankingvendas> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Rankingvendas> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
