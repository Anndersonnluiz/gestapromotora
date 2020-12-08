package br.com.gestapromotora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Contratounificacao;

public class ContratoUnificacaoDao {

	public Contratounificacao salvar(Contratounificacao Contratounificacao) {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Contratounificacao = manager.merge(Contratounificacao);
		tx.commit();

		return Contratounificacao;
	}

	@SuppressWarnings("unchecked")
	public List<Contratounificacao> listar(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		Query q = manager.createQuery(sql);
		List<Contratounificacao> lista = q.getResultList();

		return lista;
	}

	public void excluir(int idContratounificacao) {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("Select t from Contratounificacao t where t.idcontratounificacao=" + idContratounificacao);
		if (q.getResultList().size() > 0) {
			Contratounificacao Contratounificacao = (Contratounificacao) q.getResultList().get(0);
			manager.remove(Contratounificacao);
		}
		tx.commit();

	}
}
