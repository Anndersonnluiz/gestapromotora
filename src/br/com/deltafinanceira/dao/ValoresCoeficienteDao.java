package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Valorescoeficiente;

public class ValoresCoeficienteDao {

	
	public Valorescoeficiente salvar(Valorescoeficiente valorescoeficiente) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		valorescoeficiente = manager.merge(valorescoeficiente);
		tx.commit();
		manager.close();
		return valorescoeficiente;
	}

	public Valorescoeficiente consultar(int idvalorescoeficiente) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select v from Valorescoeficiente v where v.idvalorescoeficiente=" + idvalorescoeficiente);
		Valorescoeficiente valorescoeficiente = null;
		if (q.getResultList().size() > 0) {
			valorescoeficiente = (Valorescoeficiente) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return valorescoeficiente;
	}

	@SuppressWarnings("unchecked")
	public List<Valorescoeficiente> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Valorescoeficiente> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
