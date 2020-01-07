package br.com.gestapromotora.managebean.banco;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.BancoFacade;
import br.com.gestapromotora.model.Banco;

@Named
@ViewScoped
public class BancoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Banco> listaBanco;
	private Banco banco;
	
	
	@PostConstruct
	public void init() {
		gerarListaBanco();
	}


	public List<Banco> getListaBanco() {
		return listaBanco;
	}


	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}


	public Banco getBanco() {
		return banco;
	}


	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	
	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		listaBanco = bancoFacade.lista("Select b From Banco b");
		if (listaBanco == null) {
			listaBanco = new ArrayList<Banco>();
		}
	}
	
	
	public String novoBanco() {
		return "cadBanco";
	}
	
	
	public String editar(Banco banco) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("banco", banco);
		return "cadBanco";
	}
	
	
	public String listaOrgaos(Banco banco) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("banco", banco);
		return "consOrgaoBanco";
	}
	
	
	
	
	
	

}