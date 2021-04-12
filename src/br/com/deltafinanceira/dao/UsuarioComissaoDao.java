package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Usuariocomissao;

public class UsuarioComissaoDao {

	public Usuariocomissao salvar(Usuariocomissao usuariocomissao) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		usuariocomissao = manager.merge(usuariocomissao);
		tx.commit();
		manager.close();
		return usuariocomissao;
	}

	public Usuariocomissao consultar(int idusuariocomissao) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select u from Usuariocomissao u where u.idusuariocomissao=" + idusuariocomissao);
		Usuariocomissao usuariocomissao = null;
		if (q.getResultList().size() > 0) {
			usuariocomissao = (Usuariocomissao) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return usuariocomissao;
	}

	@SuppressWarnings("unchecked")
	public List<Usuariocomissao> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Usuariocomissao> lista = q.getResultList();
		manager.close();
		return lista;
	}
	
	
	public void excluir(int idusuariocomissao) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("Select u from Usuariocomissao u where u.idusuariocomissao=" + idusuariocomissao);
		if (q.getResultList().size() > 0) {
			Usuariocomissao usuario = (Usuariocomissao) q.getResultList().get(0);
			manager.remove(usuario);
		}
		tx.commit();

	}
	
	
	
	
	
}
