package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.ContratoUnificacaoDao;
import br.com.deltafinanceira.model.Contratounificacao;

public class ContratoUnificacaoFacade {

	ContratoUnificacaoDao contratoUnificacaoDao;
	
	public Contratounificacao salvar(Contratounificacao contratounificacao) {
		contratoUnificacaoDao = new ContratoUnificacaoDao();
		return contratoUnificacaoDao.salvar(contratounificacao);
	}


	public List<Contratounificacao> lista(String sql) {
		contratoUnificacaoDao = new ContratoUnificacaoDao();
		return contratoUnificacaoDao.listar(sql);
	}
	
	
	public void excluir(int idcontratounificacao) {
		contratoUnificacaoDao = new ContratoUnificacaoDao();
		contratoUnificacaoDao.excluir(idcontratounificacao);
	}
}
