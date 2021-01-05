package br.com.deltafinanceira.managebean.banco;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.CoeficienteFacade;
import br.com.deltafinanceira.facade.TipoOperacaoFacade;
import br.com.deltafinanceira.model.Coeficiente;
import br.com.deltafinanceira.model.OrgaoBanco;
import br.com.deltafinanceira.model.Tipooperacao;


@Named
@ViewScoped
public class CadCoeficienteMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrgaoBanco orgaoBanco;
	private Coeficiente coeficiente;
	private List<Tipooperacao> listaTipoOperacao;
	private Tipooperacao tipooiperacao;
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		orgaoBanco = (OrgaoBanco) session.getAttribute("orgaobanco");
		coeficiente = (Coeficiente) session.getAttribute("coeficiente");
		session.removeAttribute("coeficiente");
		session.removeAttribute("orgaobanco");
		gerarListaTipoOperacao();
		if (coeficiente == null) {
			coeficiente = new Coeficiente();
		}else {
			tipooiperacao = coeficiente.getTipooperacao();
		}
	}



	public OrgaoBanco getOrgaoBanco() {
		return orgaoBanco;
	}



	public void setOrgaoBanco(OrgaoBanco orgaoBanco) {
		this.orgaoBanco = orgaoBanco;
	}



	public Coeficiente getCoeficiente() {
		return coeficiente;
	}



	public void setCoeficiente(Coeficiente coeficiente) {
		this.coeficiente = coeficiente;
	}
	
	
	public List<Tipooperacao> getListaTipoOperacao() {
		return listaTipoOperacao;
	}



	public void setListaTipoOperacao(List<Tipooperacao> listaTipoOperacao) {
		this.listaTipoOperacao = listaTipoOperacao;
	}



	public Tipooperacao getTipooiperacao() {
		return tipooiperacao;
	}



	public void setTipooiperacao(Tipooperacao tipooiperacao) {
		this.tipooiperacao = tipooiperacao;
	}



	public String cancelar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("orgaobanco", orgaoBanco);
		return "consCoeficiente";
	}

	
	public String salvar() {
		CoeficienteFacade coeficienteFacade = new CoeficienteFacade();
		coeficiente.setOrgaoBanco(orgaoBanco);
		coeficiente.setTipooperacao(tipooiperacao);
		coeficiente = coeficienteFacade.salvar(coeficiente);
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("orgaobanco", orgaoBanco);
		return "consCoeficiente";
	}
	
	
	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t");
		if (listaTipoOperacao == null) {
			listaTipoOperacao = new ArrayList<Tipooperacao>();
		}
	}
	
	
	
	
	
	

}
