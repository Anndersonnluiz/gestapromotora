package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Campanhasusuario;

public class CampanhasUsuarioDao {

	public void salvar(Campanhasusuario campanhas) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		campanhas = manager.merge(campanhas);
		tx.commit();
		manager.close();
	}

	public Campanhasusuario consultar(int idcampanhasusuario) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select c from Campanhasusuario c where c.idcampanhasusuario=" + idcampanhasusuario);
		Campanhasusuario campanhas = null;
		if (q.getResultList().size() > 0) {
			campanhas = (Campanhasusuario) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return campanhas;
	}

	@SuppressWarnings("unchecked")
	public List<Campanhasusuario> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Campanhasusuario> lista = q.getResultList();
		manager.close();
		return lista;
	}
	
	
	public void excluir(int idCampanhaUsuario) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("Select c from Campanhasusuario c where c.idcampanhasusuario=" + idCampanhaUsuario);
		if (q.getResultList().size() > 0) {
			Campanhasusuario campanhasusuario = (Campanhasusuario) q.getResultList().get(0);
			manager.remove(campanhasusuario);
		}
		tx.commit();

	}
}
