package br.com.gestapromotora.facade;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.gestapromotora.dao.FuncionarioDao;
import br.com.gestapromotora.model.Funcionario;

public class FuncionarioFacade {
	
	FuncionarioDao funcionarioDao;
	
	
	public Funcionario salvar(Funcionario funcionario) {
		funcionarioDao = new FuncionarioDao();
		try {
			return funcionarioDao.salvar(funcionario);
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public Funcionario consultar(int idFuncionario) {
		funcionarioDao = new FuncionarioDao();
		try {
			return funcionarioDao.consultar(idFuncionario);
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public List<Funcionario> listar(String sql) {
		funcionarioDao = new FuncionarioDao();
		try {
			return funcionarioDao.listar(sql);
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public void excluir(int idusuario) {
		funcionarioDao = new FuncionarioDao();
		funcionarioDao.excluir(idusuario);
	}
}
