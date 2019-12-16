package br.com.gestapromotora.managebean.contrato;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.ContratoFacade;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.util.Mensagem;

@Named
@ViewScoped
public class ContratoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Contrato> listaContrato;
	
	
	@PostConstruct
	public void init() {
		gerarListaContrato();
	}


	public List<Contrato> getListaContrato() {
		return listaContrato;
	}


	public void setListaContrato(List<Contrato> listaContrato) {
		this.listaContrato = listaContrato;
	}


	public void gerarListaContrato() {
		ContratoFacade contratoFacade = new ContratoFacade();
		listaContrato = contratoFacade.lista("Select c From Contrato c");
		if (listaContrato == null) {
			listaContrato = new ArrayList<Contrato>();
		}
	}
	
	
	public String editar(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		session.setAttribute("orgaobanco", contrato.getValorescoeficiente().getCoeficiente().getOrgaoBanco());
		return "cadContrato";
	}
	
	
	public void bloquearEdicao(Contrato contrato) {
		if (contrato.isBloqueio()) {
			contrato.setBloqueio(false);
			contrato.setDescricaobloqueio("unlock");
			Mensagem.lancarMensagemInfo("Desbloqueio de contrato feito com sucesso", "");
		}else {
			contrato.setBloqueio(true);
			contrato.setDescricaobloqueio("lock");
			Mensagem.lancarMensagemInfo("Bloqueio de contrato feito com sucesso", "");
		}
		ContratoFacade contratoFacade = new ContratoFacade();
		contrato = contratoFacade.salvar(contrato);
	}
	
	
	public void digitarEdicao(Contrato contrato) {
		if (contrato.isDigitado()) {
			contrato.setDigitado(false);
			contrato.setDescricaodigitado("file");
			Mensagem.lancarMensagemInfo("Desbloqueio de contrato desfeita com sucesso", "");
		}else {
			contrato.setDigitado(true);
			contrato.setDescricaodigitado("file-text");
			Mensagem.lancarMensagemInfo("Digitação de contrato feito com sucesso", "");
		}
		ContratoFacade contratoFacade = new ContratoFacade();
		contrato = contratoFacade.salvar(contrato);
	}
	
	
	public String malote(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		return "cadMaloteContrato";
	}
	
	
	public String historicopendencia(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		return "cadHistoricoPendencia";
	}
	
	
	public void entregueFisico(Contrato contrato) {
		if (contrato.isFisico()) {
			contrato.setFisico(false);
			contrato.setDescricaofisico("x-circle");
			Mensagem.lancarMensagemInfo("Contrato não entregue", "");
		}else {
			contrato.setFisico(true);
			contrato.setDescricaofisico("check");
			Mensagem.lancarMensagemInfo("Contrato Entregue", "");
		}
		ContratoFacade contratoFacade = new ContratoFacade();
		contrato = contratoFacade.salvar(contrato);
	}
	
	
	public String trocatTitular(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		return "trocarTitular";
	}
	
	
	public String financeiro(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		return "consFinanceiro";
	}
	
	
	

}
