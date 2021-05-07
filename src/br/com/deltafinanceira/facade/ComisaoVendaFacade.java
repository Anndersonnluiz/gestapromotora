package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.ComissaoVendaDao;
import br.com.deltafinanceira.model.Comissaovenda;


public class ComisaoVendaFacade {
	
	ComissaoVendaDao comissaoVendaDao;

	public Comissaovenda salvar(Comissaovenda coeficiente) {
		comissaoVendaDao = new ComissaoVendaDao();
		return comissaoVendaDao.salvar(coeficiente);
	}

	public Comissaovenda consultar(int idcoeficiente) {
		comissaoVendaDao = new ComissaoVendaDao();
		return comissaoVendaDao.consultar(idcoeficiente);
	}

	public List<Comissaovenda> lista(String sql) {
		comissaoVendaDao = new ComissaoVendaDao();
		return comissaoVendaDao.lista(sql);
	}
}
