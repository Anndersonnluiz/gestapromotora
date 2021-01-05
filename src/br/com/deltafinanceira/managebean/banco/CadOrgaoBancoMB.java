package br.com.deltafinanceira.managebean.banco;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.OrgaoBancoFacade;
import br.com.deltafinanceira.model.Banco;
import br.com.deltafinanceira.model.OrgaoBanco;

@Named
@ViewScoped
public class CadOrgaoBancoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Banco banco;
	private OrgaoBanco orgaoBanco;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		banco = (Banco) session.getAttribute("banco");
		orgaoBanco = (OrgaoBanco) session.getAttribute("orgaobanco");
		session.removeAttribute("banco");
		session.removeAttribute("orgaobanco");
		if (orgaoBanco == null) {
			orgaoBanco = new OrgaoBanco();
		}
	}


	public Banco getBanco() {
		return banco;
	}


	public void setBanco(Banco banco) {
		this.banco = banco;
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
		session.setAttribute("banco", banco);
		return "consOrgaoBanco";
	}
	
	
	public String salvar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("banco", banco);
		OrgaoBancoFacade orgaoBancoFacade = new OrgaoBancoFacade();
		orgaoBanco.setBanco(banco);
		orgaoBanco = orgaoBancoFacade.salvar(orgaoBanco);
		return "consOrgaoBanco";
	}
	
	

}
