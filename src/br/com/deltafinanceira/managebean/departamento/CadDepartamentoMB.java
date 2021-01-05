package br.com.deltafinanceira.managebean.departamento;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.DepartamentoFacade;
import br.com.deltafinanceira.model.Departamento;
import br.com.deltafinanceira.model.Filial;

@Named
@ViewScoped
public class CadDepartamentoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Departamento departamento;
	private Filial filial;
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		departamento = (Departamento) session.getAttribute("departamento");
		filial = (Filial) session.getAttribute("filial");
		session.removeAttribute("filial");
		session.removeAttribute("departamento");
		if (departamento == null) {
			departamento = new Departamento();
			departamento.setNusuario(0);
			departamento.setFilial(filial);
		}
	}



	public synchronized Departamento getDepartamento() {
		return departamento;
	}



	public synchronized void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	
	public String cancelar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("filial", filial);
		return "consDepartamento";
	}
	
	
	public String salvar() {
		DepartamentoFacade departamentoFacade = new DepartamentoFacade();
		departamento = departamentoFacade.salvar(departamento);
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("filial", filial);
		return "consDepartamento";
	}
	
	
	
	
	
	

}
