package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.MetaFaturamentoMensalDao;
import br.com.deltafinanceira.model.Metafaturamentomensal;


public class MetaFaturamentoMensalFacade {
	
	MetaFaturamentoMensalDao metaFaturamentoMensalDao;

	public Metafaturamentomensal salvar(Metafaturamentomensal metafaturamentomensal) {
		metaFaturamentoMensalDao = new MetaFaturamentoMensalDao();
		return metaFaturamentoMensalDao.salvar(metafaturamentomensal);
	}

	public Metafaturamentomensal consultar(int idmalotecontrato) {
		metaFaturamentoMensalDao = new MetaFaturamentoMensalDao();
		return metaFaturamentoMensalDao.consultar(idmalotecontrato);
	}

	public List<Metafaturamentomensal> lista(String sql) {
		metaFaturamentoMensalDao = new MetaFaturamentoMensalDao();
		return metaFaturamentoMensalDao.lista(sql);
	}
}
