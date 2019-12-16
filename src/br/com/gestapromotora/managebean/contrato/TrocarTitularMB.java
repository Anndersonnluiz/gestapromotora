package br.com.gestapromotora.managebean.contrato;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.ContratoFacade;
import br.com.gestapromotora.facade.UsuarioFacade;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.Usuario;

@Named
@ViewScoped
public class TrocarTitularMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Usuario> listaUsuario;
	private Usuario usuario;
	private Contrato contrato;
	private String senha;
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		contrato = (Contrato) session.getAttribute("contrato");
		session.removeAttribute("contrato");
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



	public Contrato getContrato() {
		return contrato;
	}



	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
	
	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}



	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		listaUsuario = usuarioFacade.listar("Select u From Usuario u");
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}
	
	
	public String salvar() {
		ContratoFacade contratoFacade = new ContratoFacade();
		if (usuario != null && senha.equalsIgnoreCase(contrato.getSenha())) {
			contrato.setUsuario(usuario);
			contrato = contratoFacade.salvar(contrato);
			return "consContrato";
		}
		return "";
	}
	
	
	public String cancelar() {
		return "consContrato";
	}
	
	
	
	
	
	
	

}
