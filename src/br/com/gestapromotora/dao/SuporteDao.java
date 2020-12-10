package br.com.gestapromotora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Suporte;

public class SuporteDao {

	public Suporte salvar(Suporte suporte) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		suporte = manager.merge(suporte);
		tx.commit();
		manager.close();
		return suporte;
	}

	public Suporte consultar(int idsuporte) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select n from Suporte n where n.idsuporte=" + idsuporte);
		Suporte suporte = null;
		if (q.getResultList().size() > 0) {
			suporte = (Suporte) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return suporte;
	}

	@SuppressWarnings("unchecked")
	public List<Suporte> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Suporte> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
