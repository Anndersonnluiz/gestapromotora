package br.com.gestapromotora.facade;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.gestapromotora.dao.UsuarioDao;
import br.com.gestapromotora.model.Usuario;

public class UsuarioFacade {

	UsuarioDao usuarioDao;

	public Usuario salvar(Usuario usuario) {
		usuarioDao = new UsuarioDao();
		try {
			return usuarioDao.salvar(usuario);
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public Usuario consultar(int idUsuario) {
		usuarioDao = new UsuarioDao();
		try {
			return usuarioDao.consultar(idUsuario);
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public Usuario consultar(String login, String senha) {
		usuarioDao = new UsuarioDao();
		try {
			return usuarioDao.consultar(login, senha);
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public List<Usuario> listaUsuario() throws SQLException {
		usuarioDao = new UsuarioDao();
		return usuarioDao.listaUsuario();
	}

	public List<Usuario> listar(String sql) {
		usuarioDao = new UsuarioDao();
		try {
			return usuarioDao.listar(sql);
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public List<Usuario> listaUsuario(String nome) {
		usuarioDao = new UsuarioDao();
		try {
			return usuarioDao.listaUsuario(nome);
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public List<Usuario> consultar(String sql) {
		usuarioDao = new UsuarioDao();
		try {
			return usuarioDao.consultar(sql);
		} catch (SQLException ex) {
			Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
}
