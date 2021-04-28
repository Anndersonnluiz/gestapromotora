package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.CampanhasDao;
import br.com.deltafinanceira.model.Campanhas;

public class CampanhasFacade {

	CampanhasDao campanhasDao;
	
	public Campanhas salvar(Campanhas campanhas) {
		campanhasDao = new CampanhasDao();
		return campanhasDao.salvar(campanhas);
	}

	public Campanhas consultar(int idcampanhas) {
		campanhasDao = new CampanhasDao();
		return campanhasDao.consultar(idcampanhas);
	}

	public List<Campanhas> lista(String sql) {
		campanhasDao = new CampanhasDao();
		return campanhasDao.lista(sql);
	}
}
