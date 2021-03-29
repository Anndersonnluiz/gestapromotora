package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Leadhistorico;

public class LeadHistoricoDao {

	public Leadhistorico salvar(Leadhistorico lead) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		lead = manager.merge(lead);
		tx.commit();
		manager.close();
		return lead;
	}

	public Leadhistorico consultar(int idlead) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select l from Leadhistorico l where l.idleadhistorico=" + idlead);
		Leadhistorico lead = null;
		if (q.getResultList().size() > 0) {
			lead = (Leadhistorico) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return lead;
	}

	@SuppressWarnings("unchecked")
	public List<Leadhistorico> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Leadhistorico> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
