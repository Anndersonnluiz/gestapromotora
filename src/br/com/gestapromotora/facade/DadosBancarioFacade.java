package br.com.gestapromotora.facade;

import java.util.List;

import br.com.gestapromotora.dao.DadosBancarioDao;
import br.com.gestapromotora.model.Dadosbancario;

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
