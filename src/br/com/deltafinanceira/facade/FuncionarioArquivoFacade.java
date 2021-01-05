package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.FuncionarioArquivoDao;
import br.com.deltafinanceira.model.Funcionarioarquivo;


public class FuncionarioArquivoFacade {

	FuncionarioArquivoDao funcionarioArquivoDao;
	
	public Funcionarioarquivo salvar(Funcionarioarquivo FuncionarioArquivo) {
		funcionarioArquivoDao = new FuncionarioArquivoDao();
		return funcionarioArquivoDao.salvar(FuncionarioArquivo);
	}


	public List<Funcionarioarquivo> lista(String sql) {
		funcionarioArquivoDao = new FuncionarioArquivoDao();
		return funcionarioArquivoDao.listar(sql);
	}
	
	
	public void excluir(int idFuncionarioArquivo) {
		funcionarioArquivoDao = new FuncionarioArquivoDao();
		funcionarioArquivoDao.excluir(idFuncionarioArquivo);
	}
}
