package br.com.gestapromotora.facade;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.gestapromotora.dao.TipoColaboradorDao;
import br.com.gestapromotora.model.Tipocolaborador;

public class TipoColaboradorFacade {

	TipoColaboradorDao tipoColaboradorDao;
	
	public Tipocolaborador salvar(Tipocolaborador tipocolaborador) {
		tipoColaboradorDao = new TipoColaboradorDao();
		try {
			return tipoColaboradorDao.salvar(tipocolaborador);
		} catch (SQLException ex) {
			Logger.getLogger(TipoColaboradorFacade.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public List<Tipocolaborador> listar(String sql) {
		tipoColaboradorDao = new TipoColaboradorDao();
		try {
			return tipoColaboradorDao.listar(sql);
		} catch (SQLException ex) {
			Logger.getLogger(TipoColaboradorFacade.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
}
