package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.CampanhasContratoDao;
import br.com.deltafinanceira.model.Campanhascontrato;


public class CampanhasContratoFacade {

	CampanhasContratoDao campanhasContratoDao;
	
	
	public void salvar(Campanhascontrato campanhascontrato) {
		campanhasContratoDao = new CampanhasContratoDao();
		campanhasContratoDao.salvar(campanhascontrato);
	}

	public Campanhascontrato consultar(int idcampanhascontrato) {
		campanhasContratoDao = new CampanhasContratoDao();
		return campanhasContratoDao.consultar(idcampanhascontrato);
	}

	public List<Campanhascontrato> lista(String sql) {
		campanhasContratoDao = new CampanhasContratoDao();
		return campanhasContratoDao.lista(sql);
	}
}
