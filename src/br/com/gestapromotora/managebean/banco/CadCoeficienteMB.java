package br.com.gestapromotora.managebean.banco;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.CoeficienteFacade;
import br.com.gestapromotora.model.Coeficiente;
import br.com.gestapromotora.model.OrgaoBanco;


@Named
@ViewScoped
public class CadCoeficienteMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrgaoBanco orgaoBanco;
	private Coeficiente coeficiente;
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		orgaoBanco = (OrgaoBanco) session.getAttribute("orgaobanco");
		coeficiente = (Coeficiente) session.getAttribute("coeficiente");
		session.removeAttribute("coeficiente");
		session.removeAttribute("orgaobanco");
		if (coeficiente == null) {
			coeficiente = new Coeficiente();
		}
	}



	public OrgaoBanco getOrgaoBanco() {
		return orgaoBanco;
	}



	public void setOrgaoBanco(OrgaoBanco orgaoBanco) {
		this.orgaoBanco = orgaoBanco;
	}



	public Coeficiente getCoeficiente() {
		return coeficiente;
	}



	public void setCoeficiente(Coeficiente coeficiente) {
		this.coeficiente = coeficiente;
	}
	
	
	public String cancelar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("orgaobanco", orgaoBanco);
		return "consCoeficiente";
	}

	
	public String salvar() {
		CoeficienteFacade coeficienteFacade = new CoeficienteFacade();
		coeficiente.setOrgaoBanco(orgaoBanco);
		coeficiente = coeficienteFacade.salvar(coeficiente);
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("orgaobanco", orgaoBanco);
		return "consCoeficiente";
	}
	
	
	
	
	
	

}
