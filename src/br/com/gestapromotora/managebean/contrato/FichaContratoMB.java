package br.com.gestapromotora.managebean.contrato;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.model.Contrato;

@Named
@ViewScoped
public class FichaContratoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Contrato contrato;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		contrato = (Contrato) session.getAttribute("contrato");
		session.removeAttribute("contrato");
		if (contrato == null) {
			contrato = new Contrato();
		}
	}


	public Contrato getContrato() {
		return contrato;
	}


	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
	
	
	
	

}