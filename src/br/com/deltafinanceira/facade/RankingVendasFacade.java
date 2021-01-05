package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.RankingVendasDao;
import br.com.deltafinanceira.model.Rankingvendas;


public class RankingVendasFacade {

	RankingVendasDao rankingVendasDao;
	
	public Rankingvendas salvar(Rankingvendas rankingvendas) {
		rankingVendasDao = new RankingVendasDao();
		return rankingVendasDao.salvar(rankingvendas);
	}

	public Rankingvendas consultar(int idrankingvendas) {
		rankingVendasDao = new RankingVendasDao();
		return rankingVendasDao.consultar(idrankingvendas);
	}

	public List<Rankingvendas> lista(String sql) {
		rankingVendasDao = new RankingVendasDao();
		return rankingVendasDao.lista(sql);
	}
}
