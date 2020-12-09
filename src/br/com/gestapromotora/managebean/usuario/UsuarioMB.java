package br.com.gestapromotora.managebean.usuario;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.dao.UsuarioDao;
import br.com.gestapromotora.facade.ContratoFacade;
import br.com.gestapromotora.facade.HistoricoComissaoFacade;
import br.com.gestapromotora.facade.UsuarioFacade;
import br.com.gestapromotora.model.Departamento;
import br.com.gestapromotora.model.Historicocomissao;
import br.com.gestapromotora.model.Usuario;
import br.com.gestapromotora.util.Criptografia;
import br.com.gestapromotora.util.Mensagem;

@Named
@ViewScoped
public class UsuarioMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Usuario> listaUsuario;
	private Usuario usuario;
	private Departamento departamento;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		departamento = (Departamento) session.getAttribute("departamento");
		session.removeAttribute("departamento");
		gerarListaUsuario();
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		listaUsuario = usuarioFacade.listar("Select u From Usuario u Where u.departamento.iddepartamento="+ 
		 departamento.getIddepartamento() +" order by u.nome");
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}

	public String novoUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("departamento", departamento);
		return "cadUsuario";
	}

	public String editar(Usuario usuario) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("usuario", usuario);
		return "cadUsuario";
	}

	public void resetarSenhaUsuario(Usuario usuario) {
		String senhaResetada;
		// try {
		// senhaResetada = Criptografia.encript("senha");
		// if (usuario != null) {
		// usuario.setSenha("");
		// UsuarioFacade usuarioFacade = new UsuarioFacade();
		// usuarioFacade.salvar(usuario);
		// }
		// } catch (NoSuchAlgorithmException ex) {
		// Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
		// }
	}

	public void ativarDesativarUsuario(Usuario usuario) {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		if (usuario.isAtivo()) {
			usuario.setAtivo(false);
			usuario.setDescricaoativo("x-circle");
		} else {
			usuario.setAtivo(true);
			usuario.setDescricaoativo("check");
		}
		usuarioFacade.salvar(usuario);
	}

	public void resetSenha(Usuario usuario) {
		usuario.setSenha("t+lL5RPpboxFzSPRYideWhLr3pEApCXE683X+k3NiXw=");
		UsuarioDao usuarioDao = new UsuarioDao();
		try {
			usuarioDao.salvar(usuario);
			Mensagem.lancarMensagemInfo("Reset de Senha", "com sucesso!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluirUsuario(Usuario usuario) {
		if (usuario.getIdusuario() != 13) {
			UsuarioFacade usuarioFacade = new UsuarioFacade();
			Usuario usuarioDelta = usuarioFacade.consultar(13);
			alterarDados(usuario, usuarioDelta);
			usuarioFacade.excluir(usuario.getIdusuario());
			listaUsuario.remove(usuario);
			Mensagem.lancarMensagemInfo("Usuario excluido com sucesso", "");
		} else {
			Mensagem.lancarMensagemFatal("Atenção!!", "Este usuário não pode ser excluido");
		}
	}

	public void alterarDados(Usuario usuario, Usuario usuarioDelta) {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		ContratoFacade contratoFacade = new ContratoFacade();
		List<Historicocomissao> listaComissao = historicoComissaoFacade.lista(
				"SELECT h FROM Historicocomissao h WHERE h.contrato.usuario.idusuario=" + usuario.getIdusuario());
		if (listaComissao == null) {
			listaComissao = new ArrayList<Historicocomissao>();
		}
		for (int i = 0; i < listaComissao.size(); i++) {
			listaComissao.get(i).getContrato().setUsuario(usuarioDelta);
			contratoFacade.salvar(listaComissao.get(i).getContrato());
			listaComissao.get(i).setUsuario(usuarioDelta);
			historicoComissaoFacade.salvar(listaComissao.get(i));
		}
	}
	
	
	public String voltar() {
		return "consDepartamento";
	}
	
	
	

}
