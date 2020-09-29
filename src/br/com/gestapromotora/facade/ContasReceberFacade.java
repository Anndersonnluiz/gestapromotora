package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.ContasReceberDao;
import br.com.gestapromotora.model.Contasreceber;


public class ContasReceberFacade {

	ContasReceberDao contasReceberDao;
	
	public Contasreceber salvar(Contasreceber Contasreceber) {
		contasReceberDao = new ContasReceberDao();
		return contasReceberDao.salvar(Contasreceber);
	}

	public Contasreceber consultar(int idContasreceber) {
		contasReceberDao = new ContasReceberDao();
		return contasReceberDao.consultar(idContasreceber);
	}

	public List<Contasreceber> lista(String sql) {
		contasReceberDao = new ContasReceberDao();
		return contasReceberDao.lista(sql);
	}
	
	
	public void excluir(int idContasreceber) {
		contasReceberDao = new ContasReceberDao();
		contasReceberDao.excluir(idContasreceber);
	}
	
}
