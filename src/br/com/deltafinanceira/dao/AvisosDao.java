package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Avisos;

public class AvisosDao {

	
	public void salvar(Avisos avisos) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		avisos = manager.merge(avisos);
		tx.commit();
		manager.close();
	}

	public Avisos consultar(int idavisos) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("Select a from Avisos a where a.idavisos=" + idavisos);
		Avisos avisos = null;
		if (q.getResultList().size() > 0) {
			avisos = (Avisos) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return avisos;
	}

	@SuppressWarnings("unchecked")
	public List<Avisos> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Avisos> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
