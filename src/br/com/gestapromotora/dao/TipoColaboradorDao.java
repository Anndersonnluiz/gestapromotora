package br.com.gestapromotora.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Tipocolaborador;

public class TipoColaboradorDao {

	
	 public Tipocolaborador salvar(Tipocolaborador tipocolaborador) throws SQLException{
	    	EntityManager manager;
	        manager = ConectionFactory.getInstance();
			EntityTransaction tx = manager.getTransaction();
			tx.begin();
			tipocolaborador = manager.merge(tipocolaborador);
	        tx.commit();
	        
	        return tipocolaborador;
	    }
	    
	     @SuppressWarnings("unchecked")
		public List<Tipocolaborador> listar(String sql) throws SQLException{
	    	EntityManager manager;
	        manager = ConectionFactory.getInstance();
	        Query q = manager.createQuery(sql);
	        List<Tipocolaborador> lista = q.getResultList();
	        
	        return lista;
	    }
}
