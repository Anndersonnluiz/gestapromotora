package br.com.gestapromotora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Contaspagar;
public class ContasPagarDao {

	public Contaspagar salvar(Contaspagar contaspagar) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		contaspagar = manager.merge(contaspagar);
		tx.commit();
		manager.close();
		return contaspagar;
	}

	public Contaspagar consultar(int idcontaspagar) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select c from Contaspagar c where c.idcontaspagar=" + idcontaspagar);
		Contaspagar contaspagar = null;
		if (q.getResultList().size() > 0) {
			contaspagar = (Contaspagar) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return contaspagar;
	}

	@SuppressWarnings("unchecked")
	public List<Contaspagar> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Contaspagar> lista = q.getResultList();
		manager.close();
		return lista;
	}
	
	
	public void excluir(int idconta) {
    	EntityManager manager;
    	manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
        Query q = manager.createQuery("select c from Contaspagar c where c.idcontaspagar=" + idconta);
        if (q.getResultList().size()>0){
        	Contaspagar contaspagar = (Contaspagar) q.getResultList().get(0);
            manager.remove(contaspagar);
        }
        tx.commit();
        
    }
}
