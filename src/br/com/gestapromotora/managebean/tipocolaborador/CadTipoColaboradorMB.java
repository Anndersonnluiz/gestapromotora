package br.com.gestapromotora.managebean.tipocolaborador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.TipoColaboradorFacade;
import br.com.gestapromotora.model.Tipocolaborador;

@Named
@ViewScoped
public class CadTipoColaboradorMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tipocolaborador tipocolaborador;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		tipocolaborador = (Tipocolaborador) session.getAttribute("tipocolaborador");
		session.removeAttribute("tipocolaborador");
		if (tipocolaborador == null) {
			tipocolaborador = new Tipocolaborador();
		}
	}


	public Tipocolaborador getTipocolaborador() {
		return tipocolaborador;
	}


	public void setTipocolaborador(Tipocolaborador tipocolaborador) {
		this.tipocolaborador = tipocolaborador;
	}
	
	
	
	public String salvar() {
		TipoColaboradorFacade tipoColaboradorFacade = new TipoColaboradorFacade();
		tipocolaborador = tipoColaboradorFacade.salvar(tipocolaborador);
		return "consTipoColaborador";
	}
	
	
	public String cancelar() {
		return "consTipoColaborador";
	}
	
	
	
	

}
