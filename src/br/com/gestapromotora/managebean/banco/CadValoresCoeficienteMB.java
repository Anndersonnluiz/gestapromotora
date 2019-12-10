package br.com.gestapromotora.managebean.banco;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.RegrasCoeficienteFacade;
import br.com.gestapromotora.facade.ValoresCoeficienteFacade;
import br.com.gestapromotora.model.Coeficiente;
import br.com.gestapromotora.model.Regrascoeficiente;
import br.com.gestapromotora.model.Valorescoeficiente;

@Named
@ViewScoped
public class CadValoresCoeficienteMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Coeficiente coeficiente;
	private Valorescoeficiente valorescoeficiente;
	private Regrascoeficiente regrascoeficiente;
	private List<Regrascoeficiente> listaRegrasCoeficiente;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		coeficiente = (Coeficiente) session.getAttribute("coeficiente");
		valorescoeficiente = (Valorescoeficiente) session.getAttribute("valorescoeficiente");
		session.removeAttribute("coeficiente");
		session.removeAttribute("valorescoeficiente");
		if (coeficiente == null) {
			coeficiente = valorescoeficiente.getCoeficiente();
		}
		if (valorescoeficiente == null) {
			valorescoeficiente = new Valorescoeficiente();
			valorescoeficiente.setCoeficiente(coeficiente);
		}else {
			buscarRegrasCoeficiente();
		}
		regrascoeficiente = new Regrascoeficiente();
	}


	public Coeficiente getCoeficiente() {
		return coeficiente;
	}


	public void setCoeficiente(Coeficiente coeficiente) {
		this.coeficiente = coeficiente;
	}


	public Valorescoeficiente getValorescoeficiente() {
		return valorescoeficiente;
	}


	public void setValorescoeficiente(Valorescoeficiente valorescoeficiente) {
		this.valorescoeficiente = valorescoeficiente;
	}


	public Regrascoeficiente getRegrascoeficiente() {
		return regrascoeficiente;
	}


	public void setRegrascoeficiente(Regrascoeficiente regrascoeficiente) {
		this.regrascoeficiente = regrascoeficiente;
	}


	public List<Regrascoeficiente> getListaRegrasCoeficiente() {
		return listaRegrasCoeficiente;
	}


	public void setListaRegrasCoeficiente(List<Regrascoeficiente> listaRegrasCoeficiente) {
		this.listaRegrasCoeficiente = listaRegrasCoeficiente;
	}
	
	
	public void buscarRegrasCoeficiente() {
		RegrasCoeficienteFacade regrasCoeficienteFacade = new RegrasCoeficienteFacade();
		listaRegrasCoeficiente = regrasCoeficienteFacade.lista("Select r From Regrascoeficiente r WHERE r.valorescoeficiente.idvalorescoeficiente=" 
				+ valorescoeficiente.getIdvalorescoeficiente());
		if (listaRegrasCoeficiente == null) {
			listaRegrasCoeficiente = new ArrayList<Regrascoeficiente>();
		}
	}
	
	
	public String salvar() {
		ValoresCoeficienteFacade valoresCoeficienteFacade = new ValoresCoeficienteFacade();
		valorescoeficiente = valoresCoeficienteFacade.salvar(valorescoeficiente);
		salvarRegras();
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("coeficiente", coeficiente);
		return "consValoresCoeficiente";
	}
	
	public String cancelar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("coeficiente", coeficiente);
		return "consValoresCoeficiente";
	}
	
	
	public void salvarRegras() {
		RegrasCoeficienteFacade regrasCoeficienteFacade = new RegrasCoeficienteFacade();
		for (int i = 0; i < listaRegrasCoeficiente.size(); i++) {
			listaRegrasCoeficiente.get(i).setValorescoeficiente(valorescoeficiente);
			regrasCoeficienteFacade.salvar(listaRegrasCoeficiente.get(i));
		}
	}
	
	
	public void adicionarRegra() {
		if (listaRegrasCoeficiente == null || listaRegrasCoeficiente.isEmpty()) {
			listaRegrasCoeficiente = new ArrayList<Regrascoeficiente>();
		}
		listaRegrasCoeficiente.add(regrascoeficiente);
		for (int i = 0; i < listaRegrasCoeficiente.size(); i++) {
			listaRegrasCoeficiente.get(i).setContador(i);
		}
		regrascoeficiente = new Regrascoeficiente();
	}
	
	
	public void excluirRegra(Regrascoeficiente regrascoeficiente) {
		for (int i = 0; i < listaRegrasCoeficiente.size(); i++) {
			if (regrascoeficiente.getContador()==listaRegrasCoeficiente.get(i).getContador()) {
				listaRegrasCoeficiente.remove(i);
			}
			i = listaRegrasCoeficiente.size();
		}
	}
	
	
	
	
	
	
	
	
	

}
