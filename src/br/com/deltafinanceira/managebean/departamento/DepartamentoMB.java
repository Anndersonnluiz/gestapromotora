package br.com.deltafinanceira.managebean.departamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.DepartamentoFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Departamento;
import br.com.deltafinanceira.model.Filial;
import br.com.deltafinanceira.model.Usuario;

@Named
@ViewScoped
public class DepartamentoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Departamento> listaDepartamento;
	private Filial filial;
	
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		filial = (Filial) session.getAttribute("filial");
		session.removeAttribute("filial");
		gerarListaDepartamento();
	}




	public synchronized List<Departamento> getListaDepartamento() {
		return listaDepartamento;
	}




	public synchronized void setListaDepartamento(List<Departamento> listaDepartamento) {
		this.listaDepartamento = listaDepartamento;
	}
	
	
	
	public Filial getFilial() {
		return filial;
	}




	public void setFilial(Filial filial) {
		this.filial = filial;
	}




	public void gerarListaDepartamento() {
		DepartamentoFacade departamentoFacade = new DepartamentoFacade();
		listaDepartamento = departamentoFacade.lista("Select d From Departamento d Where "
				+ "d.filial.idfilial=" + filial.getIdfilial());
		if (listaDepartamento == null) {
			listaDepartamento = new ArrayList<Departamento>();
		}
	}
	
	
	public String novoDepartamento() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("filial", filial);
		return "cadDepartamento";
	}
	
	
	public String editar(Departamento departamento) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("departamento", departamento);
		session.setAttribute("filial", filial);
		return "cadDepartamento";
	}
	
	
	public String acessarUsuarios(Departamento departamento) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("departamento", departamento);
		session.setAttribute("filial", filial);
		return "consUsuario";
	}
	
	
	public int gerarNumeroUsuarios(Departamento departamento) {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		List<Usuario> listaUsuario = usuarioFacade.listar("Select u From Usuario u Where "
				+ "u.departamento.iddepartamento="+ departamento.getIddepartamento());
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
		return listaUsuario.size();
	}
	
	
	public String voltar() {
		return "consFilial";
	}
	
	
	
	
	

}
