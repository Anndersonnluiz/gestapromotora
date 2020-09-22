package br.com.gestapromotora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Metafaturamentomensal;

public class MetaFaturamentoMensalDao {

	public Metafaturamentomensal salvar(Metafaturamentomensal metafaturamentomensal) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		metafaturamentomensal = manager.merge(metafaturamentomensal);
		tx.commit();
		manager.close();
		return metafaturamentomensal;
	}

	public Metafaturamentomensal consultar(int idmetafaturamentomensal) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("Select m from Metafaturamentomensal m where m.idmetafaturamentomensal=" + idmetafaturamentomensal);
		Metafaturamentomensal metafaturamentomensal = null;
		if (q.getResultList().size() > 0) {
			metafaturamentomensal = (Metafaturamentomensal) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return metafaturamentomensal;
	}

	@SuppressWarnings("unchecked")
	public List<Metafaturamentomensal> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Metafaturamentomensal> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
