package br.com.gestapromotora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Historicocomissao;

public class HistoricoComissaoDao {

	
	public Historicocomissao salvar(Historicocomissao historicocomissao) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		historicocomissao = manager.merge(historicocomissao);
		tx.commit();
		manager.close();
		return historicocomissao;
	}

	public Historicocomissao consultar(int idhistoricocomissao) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select h from Historicocomissao h where h.idhistoricocomissao=" + idhistoricocomissao);
		Historicocomissao historicocomissao = null;
		if (q.getResultList().size() > 0) {
			historicocomissao = (Historicocomissao) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return historicocomissao;
	}

	@SuppressWarnings("unchecked")
	public List<Historicocomissao> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Historicocomissao> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
