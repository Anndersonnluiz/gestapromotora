package br.com.gestapromotora.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.gestapromotora.connection.ConectionFactory;
import br.com.gestapromotora.model.Funcionario;

public class FuncionarioDao {

	public Funcionario salvar(Funcionario Funcionario) throws SQLException {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Funcionario = manager.merge(Funcionario);
		tx.commit();

		return Funcionario;
	}

	public Funcionario consultar(int idFuncionario) throws SQLException {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		Funcionario Funcionario = manager.find(Funcionario.class, idFuncionario);

		return Funcionario;
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> listar(String sql) throws SQLException {
		EntityManager manager;
		manager = ConectionFactory.getInstance();
		Query q = manager.createQuery(sql);
		List<Funcionario> lista = q.getResultList();

		return lista;
	}

	public void excluir(int idFuncionario) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("Select f from Funcionario f where f.idfuncionario=" + idFuncionario);
		if (q.getResultList().size() > 0) {
			Funcionario Funcionario = (Funcionario) q.getResultList().get(0);
			manager.remove(Funcionario);
		}
		tx.commit();

	}
}
