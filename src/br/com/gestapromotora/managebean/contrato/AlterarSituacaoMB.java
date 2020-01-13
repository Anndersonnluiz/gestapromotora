package br.com.gestapromotora.managebean.contrato;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.ContratoFacade;
import br.com.gestapromotora.facade.SituacaoFacade;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.Situacao;

@Named
@ViewScoped
public class AlterarSituacaoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Contrato contrato;
	private Situacao situacao;
	private List<Situacao> listaSituacao;
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		contrato = (Contrato) session.getAttribute("contrato");
		session.removeAttribute("contrato");
		situacao = contrato.getSituacao();
		gerarListaSituacao();
	}



	public Contrato getContrato() {
		return contrato;
	}



	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
	public Situacao getSituacao() {
		return situacao;
	}



	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}



	public List<Situacao> getListaSituacao() {
		return listaSituacao;
	}



	public void setListaSituacao(List<Situacao> listaSituacao) {
		this.listaSituacao = listaSituacao;
	}



	public void gerarListaSituacao() {
		SituacaoFacade situacaoFacade = new SituacaoFacade();
		listaSituacao = situacaoFacade.lista("Select s From Situacao s");
		if (listaSituacao == null) {
			listaSituacao = new ArrayList<Situacao>();
		}
	}
	
	
	
	
	public String cancelar() {
		return "consPortabilidade";
	}
	
	
	public String salvar() {
		ContratoFacade contratoFacade = new ContratoFacade();
		contrato.setSituacao(situacao);
		contrato.setUltimamudancasituacao(new Date());
		contrato = contratoFacade.salvar(contrato);
		return "consPortabilidade";
	}
	
	  
	
	
	

}
