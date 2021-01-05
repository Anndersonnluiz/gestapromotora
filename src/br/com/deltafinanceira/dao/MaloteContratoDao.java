package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Malotecontrato;

public class MaloteContratoDao {

	
	public Malotecontrato salvar(Malotecontrato malotecontrato) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		malotecontrato = manager.merge(malotecontrato);
		tx.commit();
		manager.close();
		return malotecontrato;
	}

	public Malotecontrato consultar(int idmalotecontrato) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("Select m from Malotecontrato m where m.idmalotecontrato=" + idmalotecontrato);
		Malotecontrato malotecontrato = null;
		if (q.getResultList().size() > 0) {
			malotecontrato = (Malotecontrato) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return malotecontrato;
	}

	@SuppressWarnings("unchecked")
	public List<Malotecontrato> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Malotecontrato> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
