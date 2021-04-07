package br.com.deltafinanceira.managebean.avisos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.AvisosUsuarioFacade;
import br.com.deltafinanceira.model.Avisos;
import br.com.deltafinanceira.model.Avisosusuario;

@Named
@ViewScoped
public class VisualizarVistosMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Avisos avisos;
	private List<Avisosusuario> listaVistos;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		avisos = (Avisos) session.getAttribute("avisos");
		session.removeAttribute("avisos");
		gerarListaVistos();
	}


	public Avisos getAvisos() {
		return avisos;
	}


	public void setAvisos(Avisos avisos) {
		this.avisos = avisos;
	}
	
	
	public List<Avisosusuario> getListaVistos() {
		return listaVistos;
	}


	public void setListaVistos(List<Avisosusuario> listaVistos) {
		this.listaVistos = listaVistos;
	}

	
	
	

	public void gerarListaVistos() {
		AvisosUsuarioFacade avisosUsuarioFacade = new AvisosUsuarioFacade();
		listaVistos = avisosUsuarioFacade.lista("Select a From Avisosusuario a Where a.visto=true "
				+ "and a.avisos.idavisos=" + avisos.getIdavisos());
		if (listaVistos == null) {
			listaVistos = new ArrayList<Avisosusuario>();
		}
	}
	
	
	public String voltar() {
		return "consAvisos";
	}
	
	
	
	
	
	

}
