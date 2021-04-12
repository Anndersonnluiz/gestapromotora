package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.FormalizacaoDao;
import br.com.deltafinanceira.model.Formalizacao;

public class FormalizacaoFacade {

	FormalizacaoDao formalizacaoDao;
	
	
	public Formalizacao salvar(Formalizacao formalizacao) {
		formalizacaoDao = new FormalizacaoDao();
		return formalizacaoDao.salvar(formalizacao);
	}

	public Formalizacao consultar(int idfinanceirocontrato) {
		formalizacaoDao = new FormalizacaoDao();
		return formalizacaoDao.consultar(idfinanceirocontrato);
	}

	public List<Formalizacao> lista(String sql) {
		formalizacaoDao = new FormalizacaoDao();
		return formalizacaoDao.lista(sql);
	}
}
