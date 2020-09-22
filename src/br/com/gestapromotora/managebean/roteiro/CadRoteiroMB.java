package br.com.gestapromotora.managebean.roteiro;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.OrgaoBancoFacade;
import br.com.gestapromotora.model.OrgaoBanco;

@Named
@ViewScoped
public class CadRoteiroMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrgaoBanco orgaoBanco;
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		orgaoBanco = (OrgaoBanco) session.getAttribute("orgaobanco");
		session.removeAttribute("orgaobanco");
	}



	public OrgaoBanco getOrgaoBanco() {
		return orgaoBanco;
	}



	public void setOrgaoBanco(OrgaoBanco orgaoBanco) {
		this.orgaoBanco = orgaoBanco;
	}
	
	
	public String cancelar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("orgaobanco", orgaoBanco);
		return "consOrgaoBancoRoteiro";
	}
	
	
	public String salvar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("orgaobanco", orgaoBanco);
		OrgaoBancoFacade orgaoBancoFacade = new OrgaoBancoFacade();
		orgaoBanco = orgaoBancoFacade.salvar(orgaoBanco);
		return "consOrgaoBancoRoteiro";
	}
	
	
	

}
