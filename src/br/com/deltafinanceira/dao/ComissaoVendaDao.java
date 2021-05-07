package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Comissaovenda;

public class ComissaoVendaDao {

	public Comissaovenda salvar(Comissaovenda comissaovenda) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		comissaovenda = manager.merge(comissaovenda);
		tx.commit();
		manager.close();
		return comissaovenda;
	}

	public Comissaovenda consultar(int idcomissaovenda) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select c from Comissaovenda c where c.idcomissaovenda=" + idcomissaovenda);
		Comissaovenda comissaovenda = null;
		if (q.getResultList().size() > 0) {
			comissaovenda = (Comissaovenda) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return comissaovenda;
	}

	@SuppressWarnings("unchecked")
	public List<Comissaovenda> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Comissaovenda> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
