package br.com.gestapromotora.managebean.banco;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class CoeficienteMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrgaoBanco orgaobanco;
	private List<Coeficiente> listaCoeficiente;
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		orgaobanco = (OrgaoBanco) session.getAttribute("orgaobanco");
		session.removeAttribute("orgaobanco");
		gerarListaCoeficiente();
	}



	public OrgaoBanco getOrgaobanco() {
		return orgaobanco;
	}



	public void setOrgaobanco(OrgaoBanco orgaobanco) {
		this.orgaobanco = orgaobanco;
	}



	public List<Coeficiente> getListaCoeficiente() {
		return listaCoeficiente;
	}



	public void setListaCoeficiente(List<Coeficiente> listaCoeficiente) {
		this.listaCoeficiente = listaCoeficiente;
	}

	
	public void gerarListaCoeficiente() {
		CoeficienteFacade coeficienteFacade = new CoeficienteFacade();
		listaCoeficiente = coeficienteFacade.lista("Select c From Coeficiente c WHERE c.orgaoBanco.idorgaobanco=" + orgaobanco.getIdorgaobanco());
		if (listaCoeficiente == null) {
			listaCoeficiente = new ArrayList<Coeficiente>();
		}
	}
	
	
	public String voltar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("orgaobanco", orgaobanco);
		return "consOrgaoBanco";
	}
	
	
	public String novoCoeficiente() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("orgaobanco", orgaobanco);
		return "cadCoeficiente";
	}
	
	
	public String editar(Coeficiente coeficiente) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("orgaobanco", orgaobanco);
		session.setAttribute("coeficiente", coeficiente);
		return "cadCoeficiente";
	}
	
	
	public String novosValores(Coeficiente coeficiente) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("coeficiente", coeficiente);
		return "consValoresCoeficiente";
	}
	
	
	
	
	
	
	
	
}


