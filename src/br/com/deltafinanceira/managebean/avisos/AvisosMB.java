package br.com.deltafinanceira.managebean.avisos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.AvisosFacade;
import br.com.deltafinanceira.model.Avisos;
import br.com.deltafinanceira.util.Formatacao;

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
		gerarListaAvisos();
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
	
	
	public void gerarListaAvisos() {
		AvisosFacade avisosFacade = new AvisosFacade();
		String dataHoje = Formatacao.ConvercaoDataNfe(new Date());
		listaAvisos = avisosFacade.lista("Select a From Avisos a Where a.datafinal>='" + dataHoje + "'");
		if (listaAvisos == null) {
			listaAvisos = new ArrayList<Avisos>();
		}
	}
	
	
	public String vistos(Avisos avisos) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("avisos", avisos);
		return "visualizarVistos";
	}
	
	
	
	

}
