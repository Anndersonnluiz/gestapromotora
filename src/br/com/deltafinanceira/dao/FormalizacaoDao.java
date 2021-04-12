package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Formalizacao;

public class FormalizacaoDao {

	public Formalizacao salvar(Formalizacao formalizacao) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		formalizacao = manager.merge(formalizacao);
		tx.commit();
		manager.close();
		return formalizacao;
	}

	public Formalizacao consultar(int idformalizacao) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select f from Formalizacao f where f.idformalizacao=" + idformalizacao);
		Formalizacao financeirocontrato = null;
		if (q.getResultList().size() > 0) {
			financeirocontrato = (Formalizacao) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return financeirocontrato;
	}

	@SuppressWarnings("unchecked")
	public List<Formalizacao> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Formalizacao> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
