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

import br.com.deltafinanceira.facade.LeadFacade;
import br.com.deltafinanceira.facade.LeadHistoricoFacade;
import br.com.deltafinanceira.model.Lead;
import br.com.deltafinanceira.model.Leadhistorico;

@Named
@ViewScoped
public class HistoricoLeadMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Lead lead;
	private List<Leadhistorico> listaHistorico;
	private boolean contratoemitido = false;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		lead = (Lead) session.getAttribute("lead");
		session.removeAttribute("lead");
		if (lead.getSituacao() == 5 || lead.getSituacao() == 6) {
			contratoemitido = true;
		}
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

	public boolean isContratoemitido() {
		return contratoemitido;
	}

	public void setContratoemitido(boolean contratoemitido) {
		this.contratoemitido = contratoemitido;
	}

	public void gerarListahistorico() {
		LeadHistoricoFacade leadHistoricoFacade = new LeadHistoricoFacade();
		listaHistorico = leadHistoricoFacade
				.lista("Select l From Leadhistorico l " + "Where l.lead.idlead=" + lead.getIdlead());
		if (listaHistorico == null) {
			listaHistorico = new ArrayList<Leadhistorico>();
		}
	}

	public void cadHistorico() {
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

	public String emissaoContrato() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("lead", lead);
		return "confDadosContrato";
	}

	public String simulacaoContrato() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("lead", lead);
		return "cadSimulacaoContrato";
	}

	public void alterarSituacao(int nSituacao) {
		LeadFacade leadFacade = new LeadFacade();
		lead.setSituacao(nSituacao);
		if (nSituacao == 1) {
			lead.setCorsituacao("#1E90FF");
		} else if (nSituacao == 2) {
			lead.setCorsituacao("#2E5495");
		} else if (nSituacao == 3) {
			lead.setCorsituacao("#FF8C00");
		} else if (nSituacao == 4) {
			lead.setCorsituacao("#B22222");
		} else if (nSituacao == 5) {
			lead.setCorsituacao("yellow");
		} else if (nSituacao == 6) {
			lead.setCorsituacao("green");
		} else if (nSituacao == 7) {
			lead.setCorsituacao("#9400D3");
		}else if (nSituacao == 8) {
			lead.setCorsituacao("gray");
		}
		lead = leadFacade.salvar(lead);
	}

	public String historicoContrato() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("lead", lead);
		return "historicoContrato";
	}

}
