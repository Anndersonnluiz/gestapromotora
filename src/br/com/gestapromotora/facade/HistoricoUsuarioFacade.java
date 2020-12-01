package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.HistoricoUsuarioDao;
import br.com.gestapromotora.model.Historicousuario;

public class HistoricoUsuarioFacade {

	HistoricoUsuarioDao historicoUsuarioDao;
	
	
	public Historicousuario salvar(Historicousuario historicousuario) {
		historicoUsuarioDao = new HistoricoUsuarioDao();
		return historicoUsuarioDao.salvar(historicousuario);
	}

	public Historicousuario consultar(int idhistoricousuario) {
		historicoUsuarioDao = new HistoricoUsuarioDao();
		return historicoUsuarioDao.consultar(idhistoricousuario);
	}
	
	public List<Historicousuario> lista(String sql) {
		historicoUsuarioDao = new HistoricoUsuarioDao();
		return historicoUsuarioDao.lista(sql);
	}
}
