package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.CoeficienteDao;
import br.com.deltafinanceira.model.Coeficiente;

public class CoeficienteFacade {

	CoeficienteDao coeficienteDao;
	
	public Coeficiente salvar(Coeficiente coeficiente) {
		coeficienteDao = new CoeficienteDao();
		return coeficienteDao.salvar(coeficiente);
	}

	public Coeficiente consultar(int idcoeficiente) {
		coeficienteDao = new CoeficienteDao();
		return coeficienteDao.consultar(idcoeficiente);
	}

	public List<Coeficiente> lista(String sql) {
		coeficienteDao = new CoeficienteDao();
		return coeficienteDao.lista(sql);
	}
}
