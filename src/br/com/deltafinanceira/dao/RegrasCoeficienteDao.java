package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Regrascoeficiente;

public class RegrasCoeficienteDao {

	
	public Regrascoeficiente salvar(Regrascoeficiente regrascoeficiente) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		regrascoeficiente = manager.merge(regrascoeficiente);
		tx.commit();
		manager.close();
		return regrascoeficiente;
	}

	public Regrascoeficiente consultar(int idregrascoeficiente) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select r from Regrascoeficiente r where r.idregrascoeficiente=" + idregrascoeficiente);
		Regrascoeficiente regrascoeficiente = null;
		if (q.getResultList().size() > 0) {
			regrascoeficiente = (Regrascoeficiente) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return regrascoeficiente;
	}

	@SuppressWarnings("unchecked")
	public List<Regrascoeficiente> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Regrascoeficiente> lista = q.getResultList();
		manager.close();
		return lista;
	}
	
	
	public void excluir(int idregra) {
    	EntityManager manager;
    	manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
        Query q = manager.createQuery("select r from Regrascoeficiente r where r.idregrascoeficiente=" + idregra);
        if (q.getResultList().size()>0){
        	Regrascoeficiente regrascoeficiente = (Regrascoeficiente) q.getResultList().get(0);
            manager.remove(regrascoeficiente);
        }
        tx.commit();
        
    }
}
