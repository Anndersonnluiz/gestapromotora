package br.com.gestapromotora.managebean.departamento;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.DepartamentoFacade;
import br.com.gestapromotora.model.Departamento;

@Named
@ViewScoped
public class CadDepartamentoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Departamento departamento;
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		departamento = (Departamento) session.getAttribute("departamento");
		session.removeAttribute("departamento");
		if (departamento == null) {
			departamento = new Departamento();
			departamento.setNusuario(0);
		}
	}



	public synchronized Departamento getDepartamento() {
		return departamento;
	}



	public synchronized void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	
	public String cancelar() {
		return "consDepartamento";
	}
	
	
	public String salvar() {
		DepartamentoFacade departamentoFacade = new DepartamentoFacade();
		departamento = departamentoFacade.salvar(departamento);
		return "consDepartamento";
	}
	
	
	
	
	
	

}
