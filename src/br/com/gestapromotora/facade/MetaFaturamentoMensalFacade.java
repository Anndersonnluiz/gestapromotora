package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.MetaFaturamentoMensalDao;
import br.com.gestapromotora.model.Metafaturamentomensal;


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
