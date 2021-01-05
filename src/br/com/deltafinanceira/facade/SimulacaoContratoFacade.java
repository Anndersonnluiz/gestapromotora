package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.SimulacaoContratoDao;
import br.com.deltafinanceira.model.Simulacaocontrato;

public class SimulacaoContratoFacade {

	SimulacaoContratoDao simulacaoContratoDao;
	
	public Simulacaocontrato salvar(Simulacaocontrato simulacaocontrato) {
		simulacaoContratoDao = new SimulacaoContratoDao();
		return simulacaoContratoDao.salvar(simulacaocontrato);
	}

	public Simulacaocontrato consultar(int idsuporte) {
		simulacaoContratoDao = new SimulacaoContratoDao();
		return simulacaoContratoDao.consultar(idsuporte);
	}

	public List<Simulacaocontrato> lista(String sql) {
		simulacaoContratoDao = new SimulacaoContratoDao();
		return simulacaoContratoDao.lista(sql);
	}
}
