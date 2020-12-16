package br.com.gestapromotora.managebean.contrato.simulacao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.model.Simulacaocontrato;

@Named
@ViewScoped
public class FichaSimulacaoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Simulacaocontrato simulacaocontrato;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		simulacaocontrato = (Simulacaocontrato) session.getAttribute("simulacaocontrato");
		session.removeAttribute("simulacaocontrato");
		if (simulacaocontrato == null) {
			simulacaocontrato = new Simulacaocontrato();
		}
	}


	


	public Simulacaocontrato getSimulacaocontrato() {
		return simulacaocontrato;
	}





	public void setSimulacaocontrato(Simulacaocontrato simulacaocontrato) {
		this.simulacaocontrato = simulacaocontrato;
	}
	
	
	public String voltar() {
		return "consSimulacaoContrato";
	}

}
