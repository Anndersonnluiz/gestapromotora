package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.HistoricoComissaoDao;
import br.com.deltafinanceira.model.Historicocomissao;

public class HistoricoComissaoFacade {

	HistoricoComissaoDao historicoComissaoDao;
	
	public Historicocomissao salvar(Historicocomissao historicocomissao) {
		historicoComissaoDao = new HistoricoComissaoDao();
		return historicoComissaoDao.salvar(historicocomissao);
	}

	public Historicocomissao consultar(int idhistoricocomissao) {
		historicoComissaoDao = new HistoricoComissaoDao();
		return historicoComissaoDao.consultar(idhistoricocomissao);
	}
	
	public Historicocomissao consultarPorContrato(int idcontrato) {
		historicoComissaoDao = new HistoricoComissaoDao();
		return historicoComissaoDao.consultarPorContrato(idcontrato);
	}
	
	public List<Historicocomissao> lista(String sql) {
		historicoComissaoDao = new HistoricoComissaoDao();
		return historicoComissaoDao.lista(sql);
	}
}
