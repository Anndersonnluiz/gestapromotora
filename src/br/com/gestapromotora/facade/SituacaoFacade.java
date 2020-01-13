package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.SituacaoDao;
import br.com.gestapromotora.model.Situacao;


public class SituacaoFacade {

	SituacaoDao situacaoDao;
	
	public Situacao salvar(Situacao situacao) {
		situacaoDao = new SituacaoDao();
		return situacaoDao.salvar(situacao);
	}

	public Situacao consultar(int idSituacao) {
		situacaoDao = new SituacaoDao();
		return situacaoDao.consultar(idSituacao);
	}

	public List<Situacao> lista(String sql) {
		situacaoDao = new SituacaoDao();
		return situacaoDao.lista(sql);
	}
}
