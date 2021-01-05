package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Rankingvendasanual;

public class RankingVendasAnualDao {

	public Rankingvendasanual salvar(Rankingvendasanual rankingvendas) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		rankingvendas = manager.merge(rankingvendas);
		tx.commit();
		manager.close();
		return rankingvendas;
	}

	public Rankingvendasanual consultar(int idrankingvendas) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select r from Rankingvendasanbual r where r.idrankingvendas=" + idrankingvendas);
		Rankingvendasanual rankingvendas = null;
		if (q.getResultList().size() > 0) {
			rankingvendas = (Rankingvendasanual) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return rankingvendas;
	}

	@SuppressWarnings("unchecked")
	public List<Rankingvendasanual> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Rankingvendasanual> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
