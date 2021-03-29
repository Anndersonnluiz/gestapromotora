package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.LeadDao;
import br.com.deltafinanceira.model.Lead;

public class LeadFacade {
	
	LeadDao leadDao;

	public Lead salvar(Lead lead) {
		leadDao = new LeadDao();
		return leadDao.salvar(lead);
	}

	public Lead consultar(int idlead) {
		leadDao = new LeadDao();
		return leadDao.consultar(idlead);
	}

	public List<Lead> lista(String sql) {
		leadDao = new LeadDao();
		return leadDao.lista(sql);
	}
}
