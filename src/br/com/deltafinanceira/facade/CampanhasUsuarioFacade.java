package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.CampanhasUsuarioDao;
import br.com.deltafinanceira.model.Campanhasusuario;

public class CampanhasUsuarioFacade {

	CampanhasUsuarioDao campanhasUsuarioDao;
	
	
	public void salvar(Campanhasusuario campanhasusuario) {
		campanhasUsuarioDao = new CampanhasUsuarioDao();
		campanhasUsuarioDao.salvar(campanhasusuario);
	}

	public Campanhasusuario consultar(int idcampanhasusuario) {
		campanhasUsuarioDao = new CampanhasUsuarioDao();
		return campanhasUsuarioDao.consultar(idcampanhasusuario);
	}

	public List<Campanhasusuario> lista(String sql) {
		campanhasUsuarioDao = new CampanhasUsuarioDao();
		return campanhasUsuarioDao.lista(sql);
	}
	
	public void excluir(int idCampanhasUsuario) {
		campanhasUsuarioDao = new CampanhasUsuarioDao();
		campanhasUsuarioDao.excluir(idCampanhasUsuario);
	}
}
