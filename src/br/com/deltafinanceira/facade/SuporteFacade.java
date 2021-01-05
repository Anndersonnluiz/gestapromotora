package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.SuporteDao;
import br.com.deltafinanceira.model.Suporte;

public class SuporteFacade {

	SuporteDao suporteDao;
	
	public Suporte salvar(Suporte suporte) {
		suporteDao = new SuporteDao();
		return suporteDao.salvar(suporte);
	}

	public Suporte consultar(int idsuporte) {
		suporteDao = new SuporteDao();
		return suporteDao.consultar(idsuporte);
	}

	public List<Suporte> lista(String sql) {
		suporteDao = new SuporteDao();
		return suporteDao.lista(sql);
	}
	
}
