package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Historicopendencia;

public class HistoricoPendenciaDao {

	
	public Historicopendencia salvar(Historicopendencia historicopendencia) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		historicopendencia = manager.merge(historicopendencia);
		tx.commit();
		manager.close();
		return historicopendencia;
	}

	public Historicopendencia consultar(int idhistoricopendencia) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select h from Historicopendencia h where h.idhistoricopendencia=" + idhistoricopendencia);
		Historicopendencia historicopendencia = null;
		if (q.getResultList().size() > 0) {
			historicopendencia = (Historicopendencia) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return historicopendencia;
	}

	@SuppressWarnings("unchecked")
	public List<Historicopendencia> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Historicopendencia> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
