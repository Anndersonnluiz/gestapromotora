package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.FinanceiroContratoDao;
import br.com.gestapromotora.model.Financeirocontrato;


public class FinanceiroContratoFacade {

	FinanceiroContratoDao financeiroContratoDao;
	
	public Financeirocontrato salvar(Financeirocontrato financeirocontrato) {
		financeiroContratoDao = new FinanceiroContratoDao();
		return financeiroContratoDao.salvar(financeirocontrato);
	}

	public Financeirocontrato consultar(int idfinanceirocontrato) {
		financeiroContratoDao = new FinanceiroContratoDao();
		return financeiroContratoDao.consultar(idfinanceirocontrato);
	}

	public List<Financeirocontrato> lista(String sql) {
		financeiroContratoDao = new FinanceiroContratoDao();
		return financeiroContratoDao.lista(sql);
	}
}
