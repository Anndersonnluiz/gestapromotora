package br.com.gestapromotora.managebean.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;


import br.com.gestapromotora.facade.DadosBancarioFacade;
import br.com.gestapromotora.facade.TipoColaboradorFacade;
import br.com.gestapromotora.facade.UsuarioFacade;
import br.com.gestapromotora.model.Dadosbancario;
import br.com.gestapromotora.model.Tipocolaborador;
import br.com.gestapromotora.model.Usuario;

@Named
@ViewScoped
public class CadUsuarioMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Dadosbancario dadosbancario;
	private Tipocolaborador tipocolaborador;
	private List<Tipocolaborador> listaTipoColaborador;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		usuario = (Usuario) session.getAttribute("usuario");
		session.removeAttribute("usuario");
		if (usuario == null) {
			usuario = new Usuario();
			dadosbancario = new Dadosbancario();
		} else {
			buscarDadosBancarios(usuario);
			tipocolaborador = usuario.getTipocolaborador();
		}
		gerarListaTipoColaborador();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Dadosbancario getDadosbancario() {
		return dadosbancario;
	}

	public void setDadosbancario(Dadosbancario dadosbancario) {
		this.dadosbancario = dadosbancario;
	}

	public Tipocolaborador getTipocolaborador() {
		return tipocolaborador;
	}

	public void setTipocolaborador(Tipocolaborador tipocolaborador) {
		this.tipocolaborador = tipocolaborador;
	}

	public List<Tipocolaborador> getListaTipoColaborador() {
		return listaTipoColaborador;
	}

	public void setListaTipoColaborador(List<Tipocolaborador> listaTipoColaborador) {
		this.listaTipoColaborador = listaTipoColaborador;
	}

	public void gerarListaTipoColaborador() {
		TipoColaboradorFacade tipoColaboradorFacade = new TipoColaboradorFacade();
		listaTipoColaborador = tipoColaboradorFacade.listar("Select t From Tipocolaborador t");
		if (listaTipoColaborador == null) {
			listaTipoColaborador = new ArrayList<Tipocolaborador>();
		}
	}

	public void buscarDadosBancarios(Usuario usuario) {
		DadosBancarioFacade dadosBancarioFacade = new DadosBancarioFacade();
		dadosbancario = dadosBancarioFacade.consultar(usuario.getDadosbancario().getIddadosbancario());
		if (dadosbancario == null) {
			dadosbancario = new Dadosbancario();
		}
	}

	public String salvar() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		usuario.setTipocolaborador(tipocolaborador);
		salvarDadosBancarios();
		usuario.setDadosbancario(dadosbancario);
		if (usuario.getIdusuario() == null) {
			usuario.setSenha("t+lL5RPpboxFzSPRYideWhLr3pEApCXE683X+k3NiXw=");
			usuario = usuarioFacade.salvar(usuario);
		} else {
			usuario = usuarioFacade.salvar(usuario);
		}
		return "consUsuario";
	}
	
	
	public void salvarDadosBancarios() {
		DadosBancarioFacade dadosBancarioFacade = new DadosBancarioFacade();
		dadosbancario = dadosBancarioFacade.salvar(dadosbancario);
	}
	
	public String cancelar() {
		return "consUsuario";
	}
	
	

}
