package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.NotificacaoDao;
import br.com.gestapromotora.model.Notificacao;

public class NotificacaoFacade {
	
	NotificacaoDao notificacaoDao;
	
	
	public Notificacao salvar(Notificacao notificacao) {
		notificacaoDao = new NotificacaoDao();
		return notificacaoDao.salvar(notificacao);
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
