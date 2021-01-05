package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.NotificacaoDao;
import br.com.deltafinanceira.model.Notificacao;

public class NotificacaoFacade {
	
	NotificacaoDao notificacaoDao;
	
	
	public void salvar(Notificacao notificacao) {
		notificacaoDao = new NotificacaoDao();
		notificacaoDao.salvar(notificacao);
	}

	public Notificacao consultar(int idnotificacao) {
		notificacaoDao = new NotificacaoDao();
		return notificacaoDao.consultar(idnotificacao);
	}

	public List<Notificacao> lista(String sql) {
		notificacaoDao = new NotificacaoDao();
		return notificacaoDao.lista(sql);
	}

}
