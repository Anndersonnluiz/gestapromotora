package br.com.gestapromotora.managebean.banco;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.ValoresCoeficienteFacade;
import br.com.gestapromotora.model.Coeficiente;
import br.com.gestapromotora.model.Valorescoeficiente;

@Named
@ViewScoped
public class ValoresCoeficienteMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Coeficiente coeficiente;
	private List<Valorescoeficiente> listaValoresCoeficiente;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		coeficiente = (Coeficiente) session.getAttribute("coeficiente");
		session.removeAttribute("coeficiente");
		gerarListaValores();
	}


	public Coeficiente getCoeficiente() {
		return coeficiente;
	}


	public void setCoeficiente(Coeficiente coeficiente) {
		this.coeficiente = coeficiente;
	}


	public List<Valorescoeficiente> getListaValoresCoeficiente() {
		return listaValoresCoeficiente;
	}


	public void setListaValoresCoeficiente(List<Valorescoeficiente> listaValoresCoeficiente) {
		this.listaValoresCoeficiente = listaValoresCoeficiente;
	}
	
	
	public void gerarListaValores() {
		ValoresCoeficienteFacade valoresCoeficienteFacade = new ValoresCoeficienteFacade();
		listaValoresCoeficiente = valoresCoeficienteFacade.lista("Select v From Valorescoeficiente v WHERE v.coeficiente.idcoeficiente=" + coeficiente.getIdcoeficiente());
		if (listaValoresCoeficiente == null) {
			listaValoresCoeficiente = new ArrayList<Valorescoeficiente>();
		}
	}
	
	
	

	public String voltar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("orgaobanco", coeficiente.getOrgaoBanco());
		return "consCoeficiente";
	}
	
	
	public String novosValores() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("coeficiente", coeficiente);
		return "cadValoresCoeficiente";
	}
	
	
	public String editar(Valorescoeficiente valorescoeficiente) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("valorescoeficiente", valorescoeficiente);
		session.setAttribute("coeficiente", coeficiente);
		return "cadValoresCoeficiente";
	}
	
	
	
	
	

}
