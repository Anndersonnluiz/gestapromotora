package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.FilialDao;
import br.com.deltafinanceira.model.Filial;

public class FilialFacade {

	FilialDao filialDao;
	
	
	public Filial salvar(Filial filial) {
		filialDao = new FilialDao();
		return filialDao.salvar(filial);
	}

	public Filial consultar(int idfilial) {
		filialDao = new FilialDao();
		return filialDao.consultar(idfilial);
	}

	public List<Filial> lista(String sql) {
		filialDao = new FilialDao();
		return filialDao.lista(sql);
	}
	
	
	public void excluir(int idfilial) {
		filialDao = new FilialDao();
		filialDao.excluir(idfilial);
	}
}
