package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.MetaFaturamentoAnualDao;
import br.com.deltafinanceira.model.Metafaturamentoanual;


public class MetaFaturamentoAnualFacade {

	MetaFaturamentoAnualDao metaFaturamentoAnualDao;
	
	public Metafaturamentoanual salvar(Metafaturamentoanual metafaturamentoanual) {
		metaFaturamentoAnualDao = new MetaFaturamentoAnualDao();
		return metaFaturamentoAnualDao.salvar(metafaturamentoanual);
	}

	public Metafaturamentoanual consultar(int idmetafaturamentoanual) {
		metaFaturamentoAnualDao = new MetaFaturamentoAnualDao();
		return metaFaturamentoAnualDao.consultar(idmetafaturamentoanual);
	}

	public List<Metafaturamentoanual> lista(String sql) {
		metaFaturamentoAnualDao = new MetaFaturamentoAnualDao();
		return metaFaturamentoAnualDao.lista(sql);
	}
}
