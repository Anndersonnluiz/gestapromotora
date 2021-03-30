package br.com.deltafinanceira.managebean.lead;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.deltafinanceira.facade.LeadHistoricoFacade;
import br.com.deltafinanceira.model.Lead;
import br.com.deltafinanceira.model.Leadhistorico;

@Named
@ViewScoped
public class HistoricoLeadMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Lead lead;
	private List<Leadhistorico> listaHistorico;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		lead = (Lead) session.getAttribute("lead");
		session.removeAttribute("lead");
		gerarListahistorico();
	}


	public Lead getLead() {
		return lead;
	}


	public void setLead(Lead lead) {
		this.lead = lead;
	}


	public List<Leadhistorico> getListaHistorico() {
		return listaHistorico;
	}


	public void setListaHistorico(List<Leadhistorico> listaHistorico) {
		this.listaHistorico = listaHistorico;
	}
	
	
	
	
	public void gerarListahistorico() {
		LeadHistoricoFacade leadHistoricoFacade = new LeadHistoricoFacade();
		listaHistorico = leadHistoricoFacade.lista("Select l From Leadhistorico l "
				+ "Where l.lead.idlead=" + lead.getIdlead());
		if (listaHistorico == null) {
			listaHistorico = new ArrayList<Leadhistorico>();
		}
	}
	
	
	public void cadHistorico(){
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("contentWidth", 350);
		options.put("modal", true);
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); 
		session.setAttribute("lead", lead);
		RequestContext.getCurrentInstance().openDialog("cadHistorico", options, null);
	}
	
	
	public String voltar() {
		return "consLead";
	}
	
	
	
	
	

}
