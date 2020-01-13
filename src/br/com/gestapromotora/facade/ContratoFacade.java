package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.bean.FiltrosBean;
import br.com.gestapromotora.dao.ContratoDao;
import br.com.gestapromotora.model.Contrato;


public class ContratoFacade {
	
	ContratoDao contratoDao;
	
	public Contrato salvar(Contrato contrato) {
		contratoDao = new ContratoDao();
		return contratoDao.salvar(contrato);
	}

	public Contrato consultar(int idcontrato) {
		contratoDao = new ContratoDao();
		return contratoDao.consultar(idcontrato);
	}

	public List<Contrato> lista(String sql) {
		contratoDao = new ContratoDao();
		return contratoDao.lista(sql);
	}
	
	public List<FiltrosBean> consultarNumSituacao() {
		contratoDao = new ContratoDao();
		return contratoDao.consultarNumSituacao();
	}
}
