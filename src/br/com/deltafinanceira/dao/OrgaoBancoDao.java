package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.OrgaoBanco;

public class OrgaoBancoDao {

	
	public OrgaoBanco salvar(OrgaoBanco orgaoBanco) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		orgaoBanco = manager.merge(orgaoBanco);
		tx.commit();
		manager.close();
		return orgaoBanco;
	}

	public OrgaoBanco consultar(int idbanco) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select o from OrgaoBanco o where o.idorgaobanco=" + idbanco);
		OrgaoBanco banco = null;
		if (q.getResultList().size() > 0) {
			banco = (OrgaoBanco) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return banco;
	}

	@SuppressWarnings("unchecked")
	public List<OrgaoBanco> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<OrgaoBanco> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
