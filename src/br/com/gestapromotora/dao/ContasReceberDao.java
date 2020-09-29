package br.com.gestapromotora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Contasreceber;

public class ContasReceberDao {

	
	public Contasreceber salvar(Contasreceber Contasreceber) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Contasreceber = manager.merge(Contasreceber);
		tx.commit();
		manager.close();
		return Contasreceber;
	}

	public Contasreceber consultar(int idContasreceber) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select c from Contasreceber c where c.idcontasreceber=" + idContasreceber);
		Contasreceber Contasreceber = null;
		if (q.getResultList().size() > 0) {
			Contasreceber = (Contasreceber) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return Contasreceber;
	}

	@SuppressWarnings("unchecked")
	public List<Contasreceber> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Contasreceber> lista = q.getResultList();
		manager.close();
		return lista;
	}
	
	
	public void excluir(int idconta) {
    	EntityManager manager;
    	manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
        Query q = manager.createQuery("select c from Contasreceber c where c.idcontasreceber=" + idconta);
        if (q.getResultList().size()>0){
        	Contasreceber Contasreceber = (Contasreceber) q.getResultList().get(0);
            manager.remove(Contasreceber);
        }
        tx.commit();
        
    }
}
