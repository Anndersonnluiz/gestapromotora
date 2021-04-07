package br.com.deltafinanceira.managebean.avisos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.AvisosFacade;
import br.com.deltafinanceira.facade.AvisosUsuarioFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Avisos;
import br.com.deltafinanceira.model.Avisosusuario;
import br.com.deltafinanceira.model.Usuario;
import br.com.deltafinanceira.util.Mensagem;
import br.com.deltafinanceira.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class CadAvisosMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private Avisos avisos;
	private boolean novo;
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		avisos = (Avisos) session.getAttribute("avisos");
		session.removeAttribute("avisos");
		if (avisos == null) {
			avisos = new Avisos();
			avisos.setNomeusuario(usuarioLogadoMB.getUsuario().getNome());
			novo = true;
		}else {
			novo = false;
		}
	}
	

	public Avisos getAvisos() {
		return avisos;
	}



	public void setAvisos(Avisos avisos) {
		this.avisos = avisos;
	}
	
	
	
	public boolean isNovo() {
		return novo;
	}





	public void setNovo(boolean novo) {
		this.novo = novo;
	}




	
	
	
	public String salvar() {
		if (validarDados()) {
			if (avisos.getPrioridade().equalsIgnoreCase("Baixa")) {
				avisos.setCorprioridade("blue");
			}else if(avisos.getPrioridade().equalsIgnoreCase("Média")) {
				avisos.setCorprioridade("yellow");
			}else {
				avisos.setCorprioridade("red");
			}
			AvisosFacade avisosFacade = new AvisosFacade();
			avisos = avisosFacade.salvar(avisos);
			if (novo) {
				lancarAvisos();
			}
			
		}
		return "consAvisos";
	}


	public String cancelar() {
		return "consAvisos";
	}
	
	
	
	public boolean validarDados() {
		if (avisos.getPrioridade() == null) {
			Mensagem.lancarMensagemInfo("Selecione o tipo de prioridade", "");
			return false;
		}
		if (avisos.getDatainicio() == null) {
			Mensagem.lancarMensagemInfo("Informe a Data Inicial do aviso", "");
			return false;
		}
		if (avisos.getDatafinal() == null) {
			Mensagem.lancarMensagemInfo("Informe a Data Final do aviso", "");
			return false;
		}
		if (avisos.getTitulo() == null) {
			Mensagem.lancarMensagemInfo("Informe o Titulo do Aviso", "");
			return false;
		}
		return true;
	}
	
	
	
	public void lancarAvisos() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		List<Usuario> listaUsuario = usuarioFacade.listar("Select u From Usuario u Where u.ativo=true"
				+ " and u.treinamento=false");
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
		AvisosUsuarioFacade avisosUsuarioFacade = new AvisosUsuarioFacade();
		for (int i = 0; i < listaUsuario.size(); i++) {
			Avisosusuario avisosusuario = new Avisosusuario();
			avisosusuario.setAvisos(avisos);
			avisosusuario.setUsuario(listaUsuario.get(i));
			avisosusuario.setVisto(false);
			avisosUsuarioFacade.salvar(avisosusuario);
		}
	}
	
	
	

}
