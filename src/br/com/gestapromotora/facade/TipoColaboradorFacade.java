package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.TipoColaboradorDao;
import br.com.gestapromotora.model.Tipocolaborador;

public class TipoColaboradorFacade {

	TipoColaboradorDao tipoColaboradorDao;
	
	public Tipocolaborador salvar(Tipocolaborador tipocolaborador) {
		tipoColaboradorDao = new TipoColaboradorDao();
			return tipoColaboradorDao.salvar(tipocolaborador);
	}

	public List<Tipocolaborador> listar(String sql) {
		tipoColaboradorDao = new TipoColaboradorDao();
			return tipoColaboradorDao.listar(sql);
	}
}
