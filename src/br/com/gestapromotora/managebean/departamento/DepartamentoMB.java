package br.com.gestapromotora.managebean.departamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.DepartamentoFacade;
import br.com.gestapromotora.model.Departamento;

@Named
@ViewScoped
public class DepartamentoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Departamento> listaDepartamento;
	
	
	
	
	@PostConstruct
	public void init() {
		gerarListaDepartamento();
	}




	public synchronized List<Departamento> getListaDepartamento() {
		return listaDepartamento;
	}




	public synchronized void setListaDepartamento(List<Departamento> listaDepartamento) {
		this.listaDepartamento = listaDepartamento;
	}
	
	
	
	public void gerarListaDepartamento() {
		DepartamentoFacade departamentoFacade = new DepartamentoFacade();
		listaDepartamento = departamentoFacade.lista("Select d From Departamento d");
		if (listaDepartamento == null) {
			listaDepartamento = new ArrayList<Departamento>();
		}
	}
	
	
	public String novoDepartamento() {
		return "cadDepartamento";
	}
	
	
	public String editar(Departamento departamento) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("departamento", departamento);
		return "cadDepartamento";
	}
	
	
	public String acessarUsuarios(Departamento departamento) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("departamento", departamento);
		return "consUsuario";
	}
	
	
	
	
	

}
