package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Campanhas;

public class CampanhasDao {

	
	public Campanhas salvar(Campanhas campanhas) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		campanhas = manager.merge(campanhas);
		tx.commit();
		manager.close();
		return campanhas;
	}

	public Campanhas consultar(int idcampanhas) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select c from Campanhas c where c.idcampanhas=" + idcampanhas);
		Campanhas campanhas = null;
		if (q.getResultList().size() > 0) {
			campanhas = (Campanhas) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return campanhas;
	}

	@SuppressWarnings("unchecked")
	public List<Campanhas> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Campanhas> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
