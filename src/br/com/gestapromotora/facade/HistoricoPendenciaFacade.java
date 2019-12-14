package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.HistoricoPendenciaDao;
import br.com.gestapromotora.model.Historicopendencia;


public class HistoricoPendenciaFacade {
	
	HistoricoPendenciaDao historicoPendenciaDao;

	public Historicopendencia salvar(Historicopendencia historicopendencia) {
		historicoPendenciaDao = new HistoricoPendenciaDao();
		return historicoPendenciaDao.salvar(historicopendencia);
	}

	public Historicopendencia consultar(int idhistoricopendencia) {
		historicoPendenciaDao = new HistoricoPendenciaDao();
		return historicoPendenciaDao.consultar(idhistoricopendencia);
	}

	public List<Historicopendencia> lista(String sql) {
		historicoPendenciaDao = new HistoricoPendenciaDao();
		return historicoPendenciaDao.lista(sql);
	}
}
