package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.ClienteDao;
import br.com.deltafinanceira.model.Cliente;


public class ClienteFacade {
	 
	ClienteDao clienteDao;

	
	public Cliente salvar(Cliente banco) {
		clienteDao = new ClienteDao();
		return clienteDao.salvar(banco);
	}

	public Cliente consultar(int idusuario) {
		clienteDao = new ClienteDao();
		return clienteDao.consultar(idusuario);
	}
	
	public Cliente consultarCpf(String cpf) {
		clienteDao = new ClienteDao();
		return clienteDao.consultarCpf(cpf);
	}


	public List<Cliente> lista(String sql) {
		clienteDao = new ClienteDao();
		return clienteDao.lista(sql);
	}
}
