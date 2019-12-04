package br.com.gestapromotora.managebean.tipocolaborador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.TipoColaboradorFacade;
import br.com.gestapromotora.model.Tipocolaborador;

@Named
@ViewScoped
public class TipoColaboradorMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tipocolaborador tipocolaborador;
	private List<Tipocolaborador> listaTipoColaborador;
	
	
	
	@PostConstruct
	public void init() {
		gerarListaTipoColaborador();
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
		listaTipoColaborador = tipoColaboradorFacade.listar("Select t FROM Tipocolaborador t");
		if (listaTipoColaborador == null) {
			listaTipoColaborador = new ArrayList<Tipocolaborador>();
		}
	}
	
	
	public String novoTipoColaborador() {
		return "cadTipoColaborador";
	}
	
	
	public String editar(Tipocolaborador tipocolaborador) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("tipocolaborador", tipocolaborador);
		return "cadTipoColaborador";
	}
	
	
	
	

}
