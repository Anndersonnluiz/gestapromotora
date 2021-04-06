package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.AvisosDao;
import br.com.deltafinanceira.model.Avisos;

public class AvisosFacade {

	
	private AvisosDao avisosDao;
	
	public void salvar(Avisos avisos) {
		avisosDao = new AvisosDao();
		avisosDao.salvar(avisos);
	}

	public Avisos consultar(int idavisos) {
		avisosDao = new AvisosDao();
		return avisosDao.consultar(idavisos);
	}

	public List<Avisos> lista(String sql) {
		avisosDao = new AvisosDao();
		return avisosDao.lista(sql);
	}
}
