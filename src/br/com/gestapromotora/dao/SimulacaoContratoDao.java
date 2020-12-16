package br.com.gestapromotora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Simulacaocontrato;

public class SimulacaoContratoDao {

	public Simulacaocontrato salvar(Simulacaocontrato simulacaocontrato) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		simulacaocontrato = manager.merge(simulacaocontrato);
		tx.commit();
		manager.close();
		return simulacaocontrato;
	}

	public Simulacaocontrato consultar(int idsimulacao) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select n from Simulacaocontrato n where n.idsimulacao=" + idsimulacao);
		Simulacaocontrato simulacaocontrato = null;
		if (q.getResultList().size() > 0) {
			simulacaocontrato = (Simulacaocontrato) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return simulacaocontrato;
	}

	@SuppressWarnings("unchecked")
	public List<Simulacaocontrato> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Simulacaocontrato> lista = q.getResultList();
		manager.close();
		return lista;
	}
}
