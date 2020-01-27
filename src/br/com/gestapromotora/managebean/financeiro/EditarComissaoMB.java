package br.com.gestapromotora.managebean.financeiro;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.HistoricoComissaoFacade;
import br.com.gestapromotora.model.Historicocomissao;

@Named
@ViewScoped
public class EditarComissaoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Historicocomissao historicocomissao;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		historicocomissao = (Historicocomissao) session.getAttribute("historicocomissao");
		session.removeAttribute("historicocomissao");
	}


	public Historicocomissao getHistoricocomissao() {
		return historicocomissao;
	}


	public void setHistoricocomissao(Historicocomissao historicocomissao) {
		this.historicocomissao = historicocomissao;
	}
	
	
	public String voltar() {
		return "consPagamentoComissao";
	}
	
	public String salvar() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		historicoComissaoFacade.salvar(historicocomissao);
		return "consPagamentoComissao";
	}
	
	
	

}
