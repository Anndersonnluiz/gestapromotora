package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.MetaFaturamentoAnualDao;
import br.com.gestapromotora.model.Metafaturamentoanual;


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
