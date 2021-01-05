package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.HistoricoPendenciaDao;
import br.com.deltafinanceira.model.Historicopendencia;


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
