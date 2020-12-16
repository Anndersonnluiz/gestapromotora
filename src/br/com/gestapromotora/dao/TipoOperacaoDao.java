package br.com.gestapromotora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Situacao;
import br.com.gestapromotora.model.Tipooperacao;

public class TipoOperacaoDao {

	public Tipooperacao salvar(Tipooperacao tipooperacao) {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		tipooperacao = manager.merge(tipooperacao);
		tx.commit();

		return tipooperacao;
	}

	@SuppressWarnings("unchecked")
	public List<Tipooperacao> listar(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		Query q = manager.createQuery(sql);
		List<Tipooperacao> lista = q.getResultList();

		return lista;
	}

	public void excluir(int idtipooperacao) {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("Select t from Tipooperacao t where t.idtipooperacao=" + idtipooperacao);
		if (q.getResultList().size() > 0) {
			Tipooperacao tipooperacao = (Tipooperacao) q.getResultList().get(0);
			manager.remove(tipooperacao);
		}
		tx.commit();

	}
	
	
	public Tipooperacao consultar(int idtipooperacao) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select c from Tipooperacao c where c.idtipooperacao=" + idtipooperacao);
		Tipooperacao tipooperacao = null;
		if (q.getResultList().size() > 0) {
			tipooperacao = (Tipooperacao) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return tipooperacao;
	}
}
