package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.DadosBancarioDao;
import br.com.deltafinanceira.model.Dadosbancario;

public class DadosBancarioFacade {

	DadosBancarioDao dadosBancarioDao;

	public Dadosbancario salvar(Dadosbancario dadosBancario) {
		dadosBancarioDao = new DadosBancarioDao();
		return dadosBancarioDao.salvar(dadosBancario);
	}

	public Dadosbancario consultar(int idusuario) {
		dadosBancarioDao = new DadosBancarioDao();
		return dadosBancarioDao.consultar(idusuario);
	}

	public List<Dadosbancario> lista(String sql) {
		dadosBancarioDao = new DadosBancarioDao();
		return dadosBancarioDao.lista(sql);
	}
}
