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

import br.com.gestapromotora.facade.BancoFacade;
import br.com.gestapromotora.facade.OrgaoBancoFacade;
import br.com.gestapromotora.facade.SituacaoFacade;
import br.com.gestapromotora.facade.TipoOperacaoFacade;
import br.com.gestapromotora.model.Banco;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.OrgaoBanco;
import br.com.gestapromotora.model.Tipooperacao;

@Named
@ViewScoped
public class ConfDadosContratoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Banco> listaBanco;
	private Banco banco;
	private List<OrgaoBanco> listaOrgaoBanco;
	private OrgaoBanco orgaoBanco;
	private Contrato contrato;
	private List<Tipooperacao> listaTipoOperacao;
	private Tipooperacao tipooiperacao;
	
	
	
	@PostConstruct
	public void init() {
		contrato = new Contrato();
		gerarListaBanco();
		gerarListaTipoOperacao();
		contrato.setDatacadastro(new Date());
		SituacaoFacade situacaoFacade = new SituacaoFacade();
		contrato.setSituacao(situacaoFacade.consultar(1));
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



	public List<OrgaoBanco> getListaOrgaoBanco() {
		return listaOrgaoBanco;
	}



	public void setListaOrgaoBanco(List<OrgaoBanco> listaOrgaoBanco) {
		this.listaOrgaoBanco = listaOrgaoBanco;
	}



	public OrgaoBanco getOrgaoBanco() {
		return orgaoBanco;
	}



	public void setOrgaoBanco(OrgaoBanco orgaoBanco) {
		this.orgaoBanco = orgaoBanco;
	}



	public Contrato getContrato() {
		return contrato;
	}



	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
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
	
	
	
	
	
	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		listaBanco = bancoFacade.lista("Select b From Banco b Where b.nome !='Nenhum' ORDER BY b.nome");
		if (listaBanco == null) {
			listaBanco = new ArrayList<Banco>();
		}
	}

	public void gerarListaOrgao() {
		OrgaoBancoFacade orgaoBancoFacade = new OrgaoBancoFacade();
		listaOrgaoBanco = orgaoBancoFacade
				.lista("Select o From OrgaoBanco o WHERE o.banco.idbanco=" + banco.getIdbanco());
		if (listaOrgaoBanco == null) {
			listaOrgaoBanco = new ArrayList<OrgaoBanco>();
		}
	}
	
	
	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t");
		if (listaTipoOperacao == null) {
			listaTipoOperacao = new ArrayList<Tipooperacao>();
		}
	}
	
	public String cancelar() {
		return "consContrato";
	}
	
	
	public String cadContrato() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("banco", banco);
		session.setAttribute("contrato", contrato);
		session.setAttribute("orgaobanco", orgaoBanco);
		return "cadContrato";
	}
	
	
	
	

}
