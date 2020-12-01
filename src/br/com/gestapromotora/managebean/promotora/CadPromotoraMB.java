package br.com.gestapromotora.managebean.promotora;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.PromotoraFacade;
import br.com.gestapromotora.model.Promotora;

@Named
@ViewScoped
public class CadPromotoraMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Promotora promotora;
	
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		promotora = (Promotora) session.getAttribute("promotora");
		session.removeAttribute("promotora");
		if (promotora == null) {
			promotora = new Promotora();
		}
	}




	public Promotora getPromotora() {
		return promotora;
	}




	public void setPromotora(Promotora promotora) {
		this.promotora = promotora;
	}
	
	
	
	public String cancelar() {
		return "consPromotora";
	}
	
	
	public String salvar() {
		PromotoraFacade promotoraFacade = new PromotoraFacade();
		promotoraFacade.salvar(promotora);
		return "consPromotora";
	}
	
	
	
	
	

}
