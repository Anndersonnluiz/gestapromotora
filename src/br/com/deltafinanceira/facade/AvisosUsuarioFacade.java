package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.AvisosUsuarioDao;
import br.com.deltafinanceira.model.Avisosusuario;

public class AvisosUsuarioFacade {

	private AvisosUsuarioDao avisosUsuarioDao;
	
	
	public void salvar(Avisosusuario avisos) {
		avisosUsuarioDao = new AvisosUsuarioDao();
		avisosUsuarioDao.salvar(avisos);
	}

	public Avisosusuario consultar(int idavisos) {
		avisosUsuarioDao = new AvisosUsuarioDao();
		return avisosUsuarioDao.consultar(idavisos);
	}

	public List<Avisosusuario> lista(String sql) {
		avisosUsuarioDao = new AvisosUsuarioDao();
		return avisosUsuarioDao.lista(sql);
	}
}
