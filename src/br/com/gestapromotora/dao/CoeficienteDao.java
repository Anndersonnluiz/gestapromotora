package br.com.gestapromotora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Coeficiente;

public class CoeficienteDao {

	
	public Coeficiente salvar(Coeficiente coeficiente) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		coeficiente = manager.merge(coeficiente);
		tx.commit();
		manager.close();
		return coeficiente;
	}

	public Coeficiente consultar(int idcoeficiente) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select c from Coeficiente c where c.idcoeficiente=" + idcoeficiente);
		Coeficiente coeficiente = null;
		if (q.getResultList().size() > 0) {
			coeficiente = (Coeficiente) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return coeficiente;
	}

	@SuppressWarnings("unchecked")
	public List<Coeficiente> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Coeficiente> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
