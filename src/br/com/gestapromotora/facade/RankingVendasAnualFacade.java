package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.RankingVendasAnualDao;
import br.com.gestapromotora.model.Rankingvendasanual;

public class RankingVendasAnualFacade {

	RankingVendasAnualDao rankingVendasDao;
	
	public Rankingvendasanual salvar(Rankingvendasanual rankingvendas) {
		rankingVendasDao = new RankingVendasAnualDao();
		return rankingVendasDao.salvar(rankingvendas);
	}

	public Rankingvendasanual consultar(int idrankingvendas) {
		rankingVendasDao = new RankingVendasAnualDao();
		return rankingVendasDao.consultar(idrankingvendas);
	}

	public List<Rankingvendasanual> lista(String sql) {
		rankingVendasDao = new RankingVendasAnualDao();
		return rankingVendasDao.lista(sql);
	}
}
