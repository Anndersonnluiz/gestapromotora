package br.com.gestapromotora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Departamento;

public class DepartamentoDao {

	
	public Departamento salvar(Departamento departamento) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		departamento = manager.merge(departamento);
		tx.commit();
		manager.close();
		return departamento;
	}

	public Departamento consultar(int iddepartamento) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select c from Departamento c where c.iddepartamento=" + iddepartamento);
		Departamento Departamento = null;
		if (q.getResultList().size() > 0) {
			Departamento = (Departamento) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return Departamento;
	}

	@SuppressWarnings("unchecked")
	public List<Departamento> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Departamento> lista = q.getResultList();
		manager.close();
		return lista;
	}
	
	
	public void excluir(int iddepartamento) {
    	EntityManager manager;
    	manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
        Query q = manager.createQuery("select c from Departamento c where c.iddepartamento=" + iddepartamento);
        if (q.getResultList().size()>0){
        	Departamento departamento = (Departamento) q.getResultList().get(0);
            manager.remove(departamento);
        }
        tx.commit();
        
    }
}
