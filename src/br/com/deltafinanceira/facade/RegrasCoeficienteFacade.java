package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.RegrasCoeficienteDao;
import br.com.deltafinanceira.model.Regrascoeficiente;


public class RegrasCoeficienteFacade {
	
	RegrasCoeficienteDao regrasCoeficienteDao;
	
	public Regrascoeficiente salvar(Regrascoeficiente regrascoeficiente) {
		regrasCoeficienteDao = new RegrasCoeficienteDao();
		return regrasCoeficienteDao.salvar(regrascoeficiente);
	}

	public Regrascoeficiente consultar(int idvalorescoeficiente) {
		regrasCoeficienteDao = new RegrasCoeficienteDao();
		return regrasCoeficienteDao.consultar(idvalorescoeficiente);
	}

	public List<Regrascoeficiente> lista(String sql) {
		regrasCoeficienteDao = new RegrasCoeficienteDao();
		return regrasCoeficienteDao.lista(sql);
	}
	
	
	public void excluir(int idregra) {
		regrasCoeficienteDao = new RegrasCoeficienteDao();
		regrasCoeficienteDao.excluir(idregra);
	}

}
