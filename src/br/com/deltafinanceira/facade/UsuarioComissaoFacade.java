package br.com.deltafinanceira.facade;

import java.util.List;

import br.com.deltafinanceira.dao.UsuarioComissaoDao;
import br.com.deltafinanceira.model.Usuariocomissao;

public class UsuarioComissaoFacade {

	UsuarioComissaoDao usuarioComissaoDao;
	
	
	public Usuariocomissao salvar(Usuariocomissao usuario) {
		usuarioComissaoDao = new UsuarioComissaoDao();
		return usuarioComissaoDao.salvar(usuario);
	}

	public Usuariocomissao consultar(int idusuariocomissao) {
		usuarioComissaoDao = new UsuarioComissaoDao();
		return usuarioComissaoDao.consultar(idusuariocomissao);
	}

	public List<Usuariocomissao> listar(String sql) {
		usuarioComissaoDao = new UsuarioComissaoDao();
		return usuarioComissaoDao.lista(sql);
	}


	public void excluir(int idusuariocomissao) {
		usuarioComissaoDao = new UsuarioComissaoDao();
		usuarioComissaoDao.excluir(idusuariocomissao);
	}
}
