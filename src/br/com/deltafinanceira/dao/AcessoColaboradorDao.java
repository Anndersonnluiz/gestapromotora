package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Acessocolaborador;

public class AcessoColaboradorDao {

	public Acessocolaborador salvar(Acessocolaborador acessocolaborador) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		acessocolaborador = manager.merge(acessocolaborador);
		tx.commit();
		manager.close();
		return acessocolaborador;
	}

	public Acessocolaborador consultar(int idacesso) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select a from Acessocolaborador a where a.idacessocolaborador=" + idacesso);
		Acessocolaborador acessocolaborador = null;
		if (q.getResultList().size() > 0) {
			acessocolaborador = (Acessocolaborador) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return acessocolaborador;
	}

	@SuppressWarnings("unchecked")
	public List<Acessocolaborador> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Acessocolaborador> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
