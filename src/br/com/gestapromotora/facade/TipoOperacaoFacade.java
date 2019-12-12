package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.TipoOperacaoDao;
import br.com.gestapromotora.model.Tipooperacao;


public class TipoOperacaoFacade {
	
	TipoOperacaoDao tipoOperacaoDao;

	public Tipooperacao salvar(Tipooperacao tipooperacao) {
		tipoOperacaoDao = new TipoOperacaoDao();
		return tipoOperacaoDao.salvar(tipooperacao);
	}

	public List<Tipooperacao> lista(String sql) {
		tipoOperacaoDao = new TipoOperacaoDao();
		return tipoOperacaoDao.listar(sql);
	}
}
