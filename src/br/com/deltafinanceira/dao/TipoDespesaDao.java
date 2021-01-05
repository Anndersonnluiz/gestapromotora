package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Tipodespesa;

public class TipoDespesaDao {

	public Tipodespesa salvar(Tipodespesa tipodespesa) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		tipodespesa = manager.merge(tipodespesa);
		tx.commit();
		manager.close();
		return tipodespesa;
	}

	public Tipodespesa consultar(int idtipodespesa) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select t from Tipodespesa t where t.idtipodespesa=" + idtipodespesa);
		Tipodespesa tipodespesa = null;
		if (q.getResultList().size() > 0) {
			tipodespesa = (Tipodespesa) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return tipodespesa;
	}

	@SuppressWarnings("unchecked")
	public List<Tipodespesa> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Tipodespesa> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
