package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Lead;

public class LeadDao {

	
	public Lead salvar(Lead lead) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		lead = manager.merge(lead);
		tx.commit();
		manager.close();
		return lead;
	}

	public Lead consultar(int idlead) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select l from Lead l where l.idlead=" + idlead);
		Lead lead = null;
		if (q.getResultList().size() > 0) {
			lead = (Lead) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return lead;
	}

	@SuppressWarnings("unchecked")
	public List<Lead> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Lead> lista = q.getResultList();
		manager.close();
		return lista;
	}
	
}
