package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Contratoarquivo;

public class ContratoArquivoDao {

	public Contratoarquivo salvar(Contratoarquivo Contratoarquivo) {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Contratoarquivo = manager.merge(Contratoarquivo);
		tx.commit();

		return Contratoarquivo;
	}

	@SuppressWarnings("unchecked")
	public List<Contratoarquivo> listar(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		Query q = manager.createQuery(sql);
		List<Contratoarquivo> lista = q.getResultList();

		return lista;
	}

	public void excluir(int idContratoarquivo) {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("Select t from Contratoarquivo t where t.idcontratoarquivo=" + idContratoarquivo);
		if (q.getResultList().size() > 0) {
			Contratoarquivo Contratoarquivo = (Contratoarquivo) q.getResultList().get(0);
			manager.remove(Contratoarquivo);
		}
		tx.commit();

	}
}
