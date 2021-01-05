package br.com.deltafinanceira.managebean.filial;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.FilialFacade;
import br.com.deltafinanceira.model.Filial;

@Named
@ViewScoped
public class CadFilialMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Filial filial;
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		filial = (Filial) session.getAttribute("filial");
		session.removeAttribute("filial");
		if (filial == null) {
			filial = new Filial();
			filial.setAtivo(true);
		}
	}



	public Filial getFilial() {
		return filial;
	}



	public void setFilial(Filial filial) {
		this.filial = filial;
	}
	
	
	
	public String cancelar() {
		return "consFilial";
	}
	
	
	public String salvar() {
		FilialFacade filialFacade = new FilialFacade();
		filial = filialFacade.salvar(filial);
		return "consFilial";
	}
	
	
	
	

}
