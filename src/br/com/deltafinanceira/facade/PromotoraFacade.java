package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.PromotoraDao;
import br.com.deltafinanceira.model.Promotora;

public class PromotoraFacade {

	PromotoraDao promotoraDao;
	
	public Promotora salvar(Promotora promotora) {
		promotoraDao = new PromotoraDao();
		return promotoraDao.salvar(promotora);
	}

	public Promotora consultar(int idpromotora) {
		promotoraDao = new PromotoraDao();
		return promotoraDao.consultar(idpromotora);
	}

	public List<Promotora> lista(String sql) {
		promotoraDao = new PromotoraDao();
		return promotoraDao.lista(sql);
	}
}
