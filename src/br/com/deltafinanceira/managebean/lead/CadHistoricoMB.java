package br.com.deltafinanceira.managebean.lead;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.deltafinanceira.facade.LeadFacade;
import br.com.deltafinanceira.facade.LeadHistoricoFacade;
import br.com.deltafinanceira.model.Lead;
import br.com.deltafinanceira.model.Leadhistorico;

@Named
@ViewScoped
public class CadHistoricoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Lead lead;
	private Leadhistorico leadhistorico;
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		lead = (Lead) session.getAttribute("lead");
		session.removeAttribute("lead");
		leadhistorico = new Leadhistorico();
	}



	public Lead getLead() {
		return lead;
	}



	public void setLead(Lead lead) {
		this.lead = lead;
	}



	public Leadhistorico getLeadhistorico() {
		return leadhistorico;
	}



	public void setLeadhistorico(Leadhistorico leadhistorico) {
		this.leadhistorico = leadhistorico;
	}
	
	
	
	public void salvar() {
		lead.setUltimocontato(leadhistorico.getDatalancamento());
		LeadFacade leadFacade = new LeadFacade();
		lead = leadFacade.salvar(lead);
		leadhistorico.setLead(lead);
		LeadHistoricoFacade leadHistoricoFacade = new LeadHistoricoFacade();
		leadHistoricoFacade.salvar(leadhistorico);
		RequestContext.getCurrentInstance().closeDialog(null);
	}
	
	
	
	

}
