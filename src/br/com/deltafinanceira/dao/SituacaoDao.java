package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Situacao;

public class SituacaoDao {

	public Situacao salvar(Situacao situacao) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		situacao = manager.merge(situacao);
		tx.commit();
		manager.close();
		return situacao;
	}

	public Situacao consultar(int idSituacao) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select c from Situacao c where c.idsituacao=" + idSituacao);
		Situacao situacao = null;
		if (q.getResultList().size() > 0) {
			situacao = (Situacao) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return situacao;
	}

	@SuppressWarnings("unchecked")
	public List<Situacao> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Situacao> lista = q.getResultList();
		manager.close();
		return lista;
	}
	
	
}
