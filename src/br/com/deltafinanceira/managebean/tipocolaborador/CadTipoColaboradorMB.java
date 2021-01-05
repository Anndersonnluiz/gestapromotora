package br.com.deltafinanceira.managebean.tipocolaborador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.AcessoColaboradorFacade;
import br.com.deltafinanceira.facade.TipoColaboradorFacade;
import br.com.deltafinanceira.model.Acessocolaborador;
import br.com.deltafinanceira.model.Tipocolaborador;

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
			tipocolaborador.setAcessocolaborador(new Acessocolaborador());
		}
	}


	public Tipocolaborador getTipocolaborador() {
		return tipocolaborador;
	}


	public void setTipocolaborador(Tipocolaborador tipocolaborador) {
		this.tipocolaborador = tipocolaborador;
	}
	
	
	
	public String salvar() {
		AcessoColaboradorFacade acessoColaboradorFacade = new AcessoColaboradorFacade();
		tipocolaborador.setAcessocolaborador(acessoColaboradorFacade.salvar(tipocolaborador.getAcessocolaborador()));
		TipoColaboradorFacade tipoColaboradorFacade = new TipoColaboradorFacade();
		tipocolaborador = tipoColaboradorFacade.salvar(tipocolaborador);
		return "consTipoColaborador";
	}
	
	
	public String cancelar() {
		return "consTipoColaborador";
	}
	
	
	
	

}
