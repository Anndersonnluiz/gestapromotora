package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.HistoricoComissaoDao;
import br.com.gestapromotora.model.Historicocomissao;

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
	
	public List<Historicocomissao> lista(String sql) {
		historicoComissaoDao = new HistoricoComissaoDao();
		return historicoComissaoDao.lista(sql);
	}
}
