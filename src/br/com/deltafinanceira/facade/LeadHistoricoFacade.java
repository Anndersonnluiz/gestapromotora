package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.LeadHistoricoDao;
import br.com.deltafinanceira.model.Leadhistorico;


public class LeadHistoricoFacade {

	LeadHistoricoDao leadHistoricoDao;
	
	public Leadhistorico salvar(Leadhistorico leadhistorico) {
		leadHistoricoDao = new LeadHistoricoDao();
		return leadHistoricoDao.salvar(leadhistorico);
	}

	public Leadhistorico consultar(int idlead) {
		leadHistoricoDao = new LeadHistoricoDao();
		return leadHistoricoDao.consultar(idlead);
	}

	public List<Leadhistorico> lista(String sql) {
		leadHistoricoDao = new LeadHistoricoDao();
		return leadHistoricoDao.lista(sql);
	}
}
