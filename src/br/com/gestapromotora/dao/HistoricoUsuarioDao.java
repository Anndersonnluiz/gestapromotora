package br.com.gestapromotora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Historicousuario;

public class HistoricoUsuarioDao {

	
	public Historicousuario salvar(Historicousuario historicousuario) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		historicousuario = manager.merge(historicousuario);
		tx.commit();
		manager.close();
		return historicousuario;
	}

	public Historicousuario consultar(int idhistoricousuario) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select h from Historicousuario h where h.idhistoricousuario=" + idhistoricousuario);
		Historicousuario historicousuario = null;
		if (q.getResultList().size() > 0) {
			historicousuario = (Historicousuario) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return historicousuario;
	}

	@SuppressWarnings("unchecked")
	public List<Historicousuario> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Historicousuario> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
