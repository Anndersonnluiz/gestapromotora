package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.ClienteDao;
import br.com.gestapromotora.model.Cliente;


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

	public List<Cliente> lista(String sql) {
		clienteDao = new ClienteDao();
		return clienteDao.lista(sql);
	}
}
