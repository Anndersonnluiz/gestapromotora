package br.com.deltafinanceira.managebean.formalizacao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.model.Formalizacao;

@Named
@ViewScoped
public class FichaFormalizacaoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Formalizacao formalizacao;
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		formalizacao = (Formalizacao) session.getAttribute("formalizacao");
		session.removeAttribute("formalizacao");
	}



	public Formalizacao getFormalizacao() {
		return formalizacao;
	}



	public void setFormalizacao(Formalizacao formalizacao) {
		this.formalizacao = formalizacao;
	}
	
	
	
	public String voltar() {
		return "consFormalizacao";
	}
	
	
	
	
	

}
