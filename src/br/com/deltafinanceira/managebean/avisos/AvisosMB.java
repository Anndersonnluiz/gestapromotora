package br.com.deltafinanceira.managebean.avisos;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.model.Avisos;

@Named
@ViewScoped
public class AvisosMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Avisos> listaAvisos;
	
	
	
	@PostConstruct
	public void init() {
		
	}



	public List<Avisos> getListaAvisos() {
		return listaAvisos;
	}



	public void setListaAvisos(List<Avisos> listaAvisos) {
		this.listaAvisos = listaAvisos;
	}
	
	
	
	
	public String novo() {
		return "cadAvisos";
	}
	
	
	public String editar(Avisos avisos) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("avisos", avisos);
		return "cadAvisos";
	}
	
	

}
