package br.com.gestapromotora.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Funcionarioarquivo;

public class FuncionarioArquivoDao {

	public Funcionarioarquivo salvar(Funcionarioarquivo Funcionarioarquivo) {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Funcionarioarquivo = manager.merge(Funcionarioarquivo);
		tx.commit();

		return Funcionarioarquivo;
	}

	@SuppressWarnings("unchecked")
	public List<Funcionarioarquivo> listar(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		Query q = manager.createQuery(sql);
		List<Funcionarioarquivo> lista = q.getResultList();

		return lista;
	}

	public void excluir(int idfuncionarioarquivo) {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("Select t from Funcionarioarquivo t where t.idfuncionarioarquivo=" + idfuncionarioarquivo);
		if (q.getResultList().size() > 0) {
			Funcionarioarquivo Funcionarioarquivo = (Funcionarioarquivo) q.getResultList().get(0);
			manager.remove(Funcionarioarquivo);
		}
		tx.commit();

	}
}
