package br.com.deltafinanceira.managebean.promotora;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.PromotoraFacade;
import br.com.deltafinanceira.model.Promotora;

@Named
@ViewScoped
public class PromotoraMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Promotora> listaPromotora;
	
	
	
	@PostConstruct
	public void init() {
		gerarListaPromotora();
	}



	public List<Promotora> getListaPromotora() {
		return listaPromotora;
	}



	public void setListaPromotora(List<Promotora> listaPromotora) {
		this.listaPromotora = listaPromotora;
	}
	
	
	
	
	
	public void gerarListaPromotora() {
		PromotoraFacade promotoraFacade = new PromotoraFacade();
		listaPromotora = promotoraFacade.lista("Select p From Promotora p");
		if (listaPromotora == null) {
			listaPromotora = new ArrayList<Promotora>();
		}
	}
	
	
	
	public String novo() {
		return "cadPromotora";
	}
	
	
	public String editar(Promotora promotora) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("promotora", promotora);
		return "cadPromotora";
	}
	
	
	

}
