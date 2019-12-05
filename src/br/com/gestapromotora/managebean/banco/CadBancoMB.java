package br.com.gestapromotora.managebean.banco;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.BancoFacade;
import br.com.gestapromotora.model.Banco;

@Named
@ViewScoped
public class CadBancoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Banco banco;
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		banco = (Banco) session.getAttribute("banco");
		session.removeAttribute("banco");
		if (banco == null) {
			banco = new Banco();
		}
	}



	public Banco getBanco() {
		return banco;
	}



	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	
	public String salvar() {
		BancoFacade bancoFacade = new BancoFacade();
		banco = bancoFacade.salvar(banco);
		return "consBanco";
	}
	
	
	public String cancelar() {
		return "consBanco";
	}
	
	
	
	
	

}
