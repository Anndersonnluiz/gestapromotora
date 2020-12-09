package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.DepartamentoDao;
import br.com.gestapromotora.model.Departamento;


public class DepartamentoFacade {

	DepartamentoDao departamentoDao;
	
	public Departamento salvar(Departamento departamento) {
		departamentoDao = new DepartamentoDao();
		return departamentoDao.salvar(departamento);
	}

	public Departamento consultar(int idContasreceber) {
		departamentoDao = new DepartamentoDao();
		return departamentoDao.consultar(idContasreceber);
	}

	public List<Departamento> lista(String sql) {
		departamentoDao = new DepartamentoDao();
		return departamentoDao.lista(sql);
	}
	
	
	public void excluir(int idContasreceber) {
		departamentoDao = new DepartamentoDao();
		departamentoDao.excluir(idContasreceber);
	}
}
