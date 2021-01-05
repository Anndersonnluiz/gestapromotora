package br.com.deltafinanceira.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.model.Cliente;

public class ClienteDao {

	
	public Cliente salvar(Cliente cliente) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		cliente = manager.merge(cliente);
		tx.commit();
		manager.close();
		return cliente;
	}

	public Cliente consultar(int idcliente) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select c from Cliente c where c.idcliente=" + idcliente);
		Cliente cliente = null;
		if (q.getResultList().size() > 0) {
			cliente = (Cliente) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return cliente;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> lista(String sql) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		Query q = manager.createQuery(sql);
		List<Cliente> lista = q.getResultList();
		manager.close();
		return lista;
	}
	
	
	public Cliente consultarCpf(String cpf) {
		EntityManager manager;
		manager = ConectionFactory.getConnection();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Query q = manager.createQuery("select c from Cliente c where c.cpf like '%" + cpf + "%'");
		Cliente cliente = null;
		if (q.getResultList().size() > 0) {
			cliente = (Cliente) q.getResultList().get(0);
		}
		tx.commit();
		manager.close();
		return cliente;
	}
}
