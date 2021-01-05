package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Financeirocontrato;

public class FinanceiroContratoDao {

	public Financeirocontrato salvar(Financeirocontrato financeirocontrato) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		financeirocontrato = manager.merge(financeirocontrato);
		tx.commit();
		manager.close();
		return financeirocontrato;
	}

	public Financeirocontrato consultar(int idfinanceirocontrato) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select f from Financeirocontrato f where f.idfinanceirocontrato=" + idfinanceirocontrato);
		Financeirocontrato financeirocontrato = null;
		if (q.getResultList().size() > 0) {
			financeirocontrato = (Financeirocontrato) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return financeirocontrato;
	}

	@SuppressWarnings("unchecked")
	public List<Financeirocontrato> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Financeirocontrato> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
