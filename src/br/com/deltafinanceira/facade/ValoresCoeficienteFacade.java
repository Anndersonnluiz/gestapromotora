package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.ValoresCoeficienteDao;
import br.com.deltafinanceira.model.Valorescoeficiente;


public class ValoresCoeficienteFacade {
	
	ValoresCoeficienteDao valoresCoeficienteDao;

	
	public Valorescoeficiente salvar(Valorescoeficiente valorescoeficiente) {
		valoresCoeficienteDao = new ValoresCoeficienteDao();
		return valoresCoeficienteDao.salvar(valorescoeficiente);
	}

	public Valorescoeficiente consultar(int idvalorescoeficiente) {
		valoresCoeficienteDao = new ValoresCoeficienteDao();
		return valoresCoeficienteDao.consultar(idvalorescoeficiente);
	}

	public List<Valorescoeficiente> lista(String sql) {
		valoresCoeficienteDao = new ValoresCoeficienteDao();
		return valoresCoeficienteDao.lista(sql);
	}
}
