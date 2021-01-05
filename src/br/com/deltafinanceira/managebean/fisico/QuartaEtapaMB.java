package br.com.deltafinanceira.managebean.fisico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.facade.SituacaoFacade;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Situacao;

@Named
@ViewScoped
public class QuartaEtapaMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Contrato> listaContrato;
	private Situacao situacao;
	
	
	
	@PostConstruct
	public void init() {
		gerarListaContrato();
		buscarSituacao();
	}



	public List<Contrato> getListaContrato() {
		return listaContrato;
	}



	public void setListaContrato(List<Contrato> listaContrato) {
		this.listaContrato = listaContrato;
	}
	
	
	
	public Situacao getSituacao() {
		return situacao;
	}



	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	
	

	public String editar(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		session.setAttribute("orgaobanco", contrato.getOrgaoBanco());
		return "cadContrato";
	}
	
	
	public void gerarListaContrato() {
		ContratoFacade contratoFacade = new ContratoFacade();
		listaContrato = contratoFacade.lista("Select c From Contrato c WHERE c.fisico=true and c.datapagamento is not null and c.situacao.idsituacao=33"
				 + " and c.pendente=false");
		if (listaContrato == null) {
			listaContrato = new ArrayList<Contrato>();
		}
	}
	
	
	public void buscarSituacao() {
		SituacaoFacade situacaoFacade = new SituacaoFacade();
		situacao = situacaoFacade.consultar(34);
	}
	
	public void proximaEtapa(Contrato contrato) {
		ContratoFacade contratoFacade = new ContratoFacade();
		contrato.setSituacao(situacao);
		contratoFacade.salvar(contrato);
		gerarListaContrato();
	}

}
