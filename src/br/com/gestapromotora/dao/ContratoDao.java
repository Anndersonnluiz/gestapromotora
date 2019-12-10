package br.com.gestapromotora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Contrato;

public class ContratoDao {
	

	public Contrato salvar(Contrato contrato) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		contrato = manager.merge(contrato);
		tx.commit();
		manager.close();
		return contrato;
	}

	public Contrato consultar(int idcontrato) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select c from Contrato c where c.idcontrato=" + idcontrato);
		Contrato contrato = null;
		if (q.getResultList().size() > 0) {
			contrato = (Contrato) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return contrato;
	}

	@SuppressWarnings("unchecked")
	public List<Contrato> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Contrato> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
