package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Avisosusuario;

public class AvisosUsuarioDao {

	public void salvar(Avisosusuario avisos) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		avisos = manager.merge(avisos);
		tx.commit();
		manager.close();
	}

	public Avisosusuario consultar(int idavisosusuario) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("Select a from Avisosusuario a where a.idavisosusuario=" + idavisosusuario);
		Avisosusuario avisos = null;
		if (q.getResultList().size() > 0) {
			avisos = (Avisosusuario) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return avisos;
	}

	@SuppressWarnings("unchecked")
	public List<Avisosusuario> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Avisosusuario> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
