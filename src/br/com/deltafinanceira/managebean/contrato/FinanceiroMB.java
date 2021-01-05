package br.com.deltafinanceira.managebean.contrato;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.FinanceiroContratoFacade;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Financeirocontrato;
import br.com.deltafinanceira.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class FinanceiroMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private Contrato contrato;
	private Financeirocontrato financeirocontrato;
	private List<Financeirocontrato> listaFinanceiro;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		contrato = (Contrato) session.getAttribute("contrato");
		session.removeAttribute("contrato");
		financeirocontrato = new Financeirocontrato();
		gerarListaFinanceiro();
	}

	public UsuarioLogadoMB getUsuarioLogadoMB() {
		return usuarioLogadoMB;
	}

	public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
		this.usuarioLogadoMB = usuarioLogadoMB;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Financeirocontrato getFinanceirocontrato() {
		return financeirocontrato;
	}

	public void setFinanceirocontrato(Financeirocontrato financeirocontrato) {
		this.financeirocontrato = financeirocontrato;
	}

	public List<Financeirocontrato> getListaFinanceiro() {
		return listaFinanceiro;
	}

	public void setListaFinanceiro(List<Financeirocontrato> listaFinanceiro) {
		this.listaFinanceiro = listaFinanceiro;
	}
 
	public String voltar() {
		return "consContrato";
	}

	public void salvarFinanceiro() {
		FinanceiroContratoFacade financeiroContratoFacade = new FinanceiroContratoFacade();
		financeirocontrato.setContrato(contrato);
		financeirocontrato.setUsuario(usuarioLogadoMB.getUsuario());
		financeirocontrato = financeiroContratoFacade.salvar(financeirocontrato);
		listaFinanceiro.add(financeirocontrato);
		financeirocontrato = new Financeirocontrato();
	}

	public void gerarListaFinanceiro() {
		FinanceiroContratoFacade financeiroContratoFacade = new FinanceiroContratoFacade();
		listaFinanceiro = financeiroContratoFacade
				.lista("Select f From Financeirocontrato f WHERE f.contrato.idcontrato=" + contrato.getIdcontrato());
		if (listaFinanceiro == null) {
			listaFinanceiro = new ArrayList<Financeirocontrato>();
		}
	}

}
