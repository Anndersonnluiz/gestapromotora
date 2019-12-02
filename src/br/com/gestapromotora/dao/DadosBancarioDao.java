package br.com.gestapromotora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Dadosbancario;

public class DadosBancarioDao {

	
	  public Dadosbancario salvar(Dadosbancario dadosbancario){
	    	EntityManager manager;
	        manager = ConectionFactory.getConnection();
			EntityTransaction tx = manager.getTransaction();
			tx.begin();
			dadosbancario = manager.merge(dadosbancario);
	        tx.commit();
	        manager.close();
	        return dadosbancario;
	    }
	    
	    
	    
	    public Dadosbancario consultar(int idusuario) {
	    	EntityManager manager;
	        manager = ConectionFactory.getConnection();
			EntityTransaction tx = manager.getTransaction();
			tx.begin();
	         Query q = manager.createQuery("select d from Dadosbancario d where d.iddadosbancario=" + idusuario);
	         Dadosbancario dadosbancario = null;
	         if (q.getResultList().size() > 0) {
	        	 dadosbancario =  (Dadosbancario) q.getResultList().get(0);
	         }
	         tx.commit();
	         manager.close();
	         return dadosbancario;
	     }
	    
	    @SuppressWarnings("unchecked")
		public List<Dadosbancario> lista(String sql){
	    	EntityManager manager;
	        manager = ConectionFactory.getConnection();
	        Query q = manager.createQuery(sql);
	        List<Dadosbancario> lista = q.getResultList();
	        manager.close();
	        return lista;
	    }
}
