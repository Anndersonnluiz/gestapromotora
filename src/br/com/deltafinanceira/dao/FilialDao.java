package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Filial;

public class FilialDao {

	public Filial salvar(Filial filial) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		filial = manager.merge(filial);
		tx.commit();
		manager.close();
		return filial;
	}

	public Filial consultar(int idfilial) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select c from Filial c where c.idfilial=" + idfilial);
		Filial filial = null;
		if (q.getResultList().size() > 0) {
			filial = (Filial) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return filial;
	}

	@SuppressWarnings("unchecked")
	public List<Filial> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Filial> lista = q.getResultList();
		manager.close();
		return lista;
	}
	
	
	public void excluir(int idfilial) {
    	EntityManager manager;
    	manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
        Query q = manager.createQuery("select c from Filial c where c.idfilial=" + idfilial);
        if (q.getResultList().size()>0){
        	Filial filial = (Filial) q.getResultList().get(0);
            manager.remove(filial);
        }
        tx.commit();
        
    }
}
