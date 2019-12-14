package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.MaloteContratoDao;
import br.com.gestapromotora.model.Malotecontrato;


public class MaloteContratoFacade {

	MaloteContratoDao maloteContratoDao;
	
	public Malotecontrato salvar(Malotecontrato malotecontrato) {
		maloteContratoDao = new MaloteContratoDao();
		return maloteContratoDao.salvar(malotecontrato);
	}

	public Malotecontrato consultar(int idmalotecontrato) {
		maloteContratoDao = new MaloteContratoDao();
		return maloteContratoDao.consultar(idmalotecontrato);
	}

	public List<Malotecontrato> lista(String sql) {
		maloteContratoDao = new MaloteContratoDao();
		return maloteContratoDao.lista(sql);
	}
}
