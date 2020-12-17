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
import br.com.gestapromotora.facade.SimulacaoContratoFacade;
import br.com.gestapromotora.facade.SituacaoFacade;
import br.com.gestapromotora.facade.TipoOperacaoFacade;
import br.com.gestapromotora.model.Banco;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.OrgaoBanco;
import br.com.gestapromotora.model.Simulacaocontrato;
import br.com.gestapromotora.model.Tipooperacao;
import br.com.gestapromotora.model.Valorescoeficiente;

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
	private String nomeCliente;
	private String cpf;
	private List<Simulacaocontrato> listaSimulacao;
	
	
	
	@PostConstruct
	public void init() {
		contrato = new Contrato();
		gerarListaBanco();
		gerarListaTipoOperacao();
		contrato.setDatacadastro(new Date());
		SituacaoFacade situacaoFacade = new SituacaoFacade();
		contrato.setSituacao(situacaoFacade.consultar(37));
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
	
	
	
	
	
	public String getNomeCliente() {
		return nomeCliente;
	}



	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public List<Simulacaocontrato> getListaSimulacao() {
		return listaSimulacao;
	}



	public void setListaSimulacao(List<Simulacaocontrato> listaSimulacao) {
		this.listaSimulacao = listaSimulacao;
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
		if (contrato.getTipooperacao().getIdtipooperacao() == 1) {
			contrato.setVoltarTela("consPortabilidade");
		}else {
			if (contrato.isOperacaoinss()) {
				contrato.setVoltarTela("consDemaisOperacoesINSS");
			}else {
				contrato.setVoltarTela("consDemaisOperacoes");
			}
		}
		return "cadContrato";
	}
	
	
	public String cadContratoImporte(Simulacaocontrato simulacaocontrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		popularContrato(simulacaocontrato);
		orgaoBanco = simulacaocontrato.getOrgaoBanco();
		contrato.setDatacadastro(new Date());
		SituacaoFacade situacaoFacade = new SituacaoFacade();
		contrato.setSituacao(situacaoFacade.consultar(37));
		session.setAttribute("banco", banco);
		session.setAttribute("contrato", contrato);
		session.setAttribute("orgaobanco", orgaoBanco);
		if (contrato.getTipooperacao().getIdtipooperacao() == 1) {
			contrato.setVoltarTela("consPortabilidade");
		}else {
			if (contrato.isOperacaoinss()) {
				contrato.setVoltarTela("consDemaisOperacoesINSS");
			}else {
				contrato.setVoltarTela("consDemaisOperacoes");
			}
		}
		return "cadContrato";
	}
	
	
	public void pesquisar() {
		String sql = "Select s From Simulacaocontrato s WHERE " 
				+ " s.contrato.cliente.nome like '%"+ nomeCliente +"%' and s.contrato.cliente.cpf like '%"+ cpf +"%'";
		sql = sql + " ORDER BY s.idsimulacaocontrato DESC";
		SimulacaoContratoFacade simulacaoContratoFacade = new SimulacaoContratoFacade();
		listaSimulacao = simulacaoContratoFacade.lista(sql);
		if (listaSimulacao == null) {
			listaSimulacao = new ArrayList<Simulacaocontrato>();
		}
	}
	
	public void limpar() {
		nomeCliente = "";
		cpf = "";
		banco = null;
	}
	
	
	public void popularContrato(Simulacaocontrato simulacaocontrato) {
		contrato.setAjustecomissaocheck(simulacaocontrato.getContrato().isAjustecomissaocheck());
		contrato.setAssinadobanco(simulacaocontrato.getContrato().isAssinadobanco());
		contrato.setBanco(simulacaocontrato.getContrato().getBanco());
		contrato.setBloqueio(simulacaocontrato.getContrato().isBloqueio());
		contrato.setCliente(simulacaocontrato.getContrato().getCliente());
		contrato.setCodigocontrato("");
		contrato.setContratoportado(simulacaocontrato.getContrato().getContratoportado());
		contrato.setDescricaobloqueio(simulacaocontrato.getContrato().getDescricaobloqueio());
		contrato.setDescricaodigitado(simulacaocontrato.getContrato().getDescricaodigitado());
		contrato.setDescricaofisico(simulacaocontrato.getContrato().getDescricaofisico());
		contrato.setDetalhesituacao(simulacaocontrato.getContrato().getDetalhesituacao());
		contrato.setDigitado(simulacaocontrato.getContrato().isDigitado());
		contrato.setFisico(simulacaocontrato.getContrato().isFisico());
		contrato.setMargemutilizada(simulacaocontrato.getContrato().getMargemutilizada());
		contrato.setMatricula(simulacaocontrato.getContrato().getMatricula());
		contrato.setObservacao(simulacaocontrato.getContrato().getObservacao());
		contrato.setParcela(simulacaocontrato.getContrato().getParcela());
		contrato.setParceladivergente(simulacaocontrato.getContrato().getParceladivergente());
		contrato.setParcelaspagas(simulacaocontrato.getContrato().getParcelaspagas());
		contrato.setParcelasrestantes(simulacaocontrato.getContrato().getParcelasrestantes());
		contrato.setPendente(simulacaocontrato.getContrato().isPendente());
		contrato.setPercentualpago(simulacaocontrato.getContrato().getPercentualpago());
		contrato.setPromotora(simulacaocontrato.getContrato().getPromotora());
		contrato.setReducaoparcela(simulacaocontrato.getContrato().isReducaoparcela());
		contrato.setSaldoinadimplencia(simulacaocontrato.getContrato().getSaldoinadimplencia());
		contrato.setSecretaria(simulacaocontrato.getContrato().getSecretaria());
		contrato.setSenha(simulacaocontrato.getContrato().getSenha());
		contrato.setSenhacontracheque(simulacaocontrato.getContrato().getSenhacontracheque());
		contrato.setSimulacao(false);
		contrato.setTarifa(simulacaocontrato.getContrato().getTarifa());
		contrato.setTipooperacao(simulacaocontrato.getContrato().getTipooperacao());
		contrato.setTotalparcelas(simulacaocontrato.getContrato().getTotalparcelas());
		contrato.setUltimamudancasituacao(simulacaocontrato.getContrato().getUltimamudancasituacao());
		contrato.setUsuario(simulacaocontrato.getContrato().getUsuario());
		contrato.setValorcliente(simulacaocontrato.getContrato().getValorcliente());
		contrato.setValorclientedivergente(simulacaocontrato.getContrato().getValoroperacaodivergente());
		contrato.setValorescoeficiente(new Valorescoeficiente());
		contrato.setValoroperacao(simulacaocontrato.getContrato().getValorcliente());
		contrato.setValoroperacaodivergente(simulacaocontrato.getContrato().getValoroperacaodivergente());
		contrato.setValorparcela(simulacaocontrato.getContrato().getValorparcela());
		contrato.setValorquitar(simulacaocontrato.getContrato().getValorquitar());
	}
	
	
	

}
