package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.SuporteDao;
import br.com.gestapromotora.model.Suporte;

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
