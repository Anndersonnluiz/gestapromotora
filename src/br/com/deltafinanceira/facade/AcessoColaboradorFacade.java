package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.AcessoColaboradorDao;
import br.com.deltafinanceira.model.Acessocolaborador;

public class AcessoColaboradorFacade {

	AcessoColaboradorDao acessoColaboradorDao;

	public Acessocolaborador salvar(Acessocolaborador acessocolaborador) {
		acessoColaboradorDao = new AcessoColaboradorDao();
		return acessoColaboradorDao.salvar(acessocolaborador);
	}

	public Acessocolaborador consultar(int idacessocolaborador) {
		acessoColaboradorDao = new AcessoColaboradorDao();
		return acessoColaboradorDao.consultar(idacessocolaborador);
	}

	public List<Acessocolaborador> lista(String sql) {
		acessoColaboradorDao = new AcessoColaboradorDao();
		return acessoColaboradorDao.lista(sql);
	}
}
