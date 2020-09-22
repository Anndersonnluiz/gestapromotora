package br.com.gestapromotora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Metafaturamentoanual;

public class MetaFaturamentoAnualDao {

	public Metafaturamentoanual salvar(Metafaturamentoanual metafaturamentoanual) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		metafaturamentoanual = manager.merge(metafaturamentoanual);
		tx.commit();
		manager.close();
		return metafaturamentoanual;
	}

	public Metafaturamentoanual consultar(int idmetafaturamentoanual) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("Select m from Metafaturamentomensal m where m.idmetafaturamentoanual=" + idmetafaturamentoanual);
		Metafaturamentoanual metafaturamentoanual = null;
		if (q.getResultList().size() > 0) {
			metafaturamentoanual = (Metafaturamentoanual) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return metafaturamentoanual;
	}

	@SuppressWarnings("unchecked")
	public List<Metafaturamentoanual> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Metafaturamentoanual> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
