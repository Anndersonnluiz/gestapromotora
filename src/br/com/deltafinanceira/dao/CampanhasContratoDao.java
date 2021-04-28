package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Campanhascontrato;

public class CampanhasContratoDao {

	public void salvar(Campanhascontrato campanhas) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		campanhas = manager.merge(campanhas);
		tx.commit();
		manager.close();
	}

	public Campanhascontrato consultar(int idcampanhascontrato) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select c from Campanhascontrato c where c.idcampanhascontrato=" + idcampanhascontrato);
		Campanhascontrato campanhas = null;
		if (q.getResultList().size() > 0) {
			campanhas = (Campanhascontrato) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return campanhas;
	}

	@SuppressWarnings("unchecked")
	public List<Campanhascontrato> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Campanhascontrato> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
