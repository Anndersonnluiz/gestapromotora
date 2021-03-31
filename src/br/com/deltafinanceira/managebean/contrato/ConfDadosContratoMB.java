package br.com.deltafinanceira.managebean.contrato;

import br.com.deltafinanceira.facade.BancoFacade;
import br.com.deltafinanceira.facade.OrgaoBancoFacade;
import br.com.deltafinanceira.facade.SimulacaoContratoFacade;
import br.com.deltafinanceira.facade.SituacaoFacade;
import br.com.deltafinanceira.facade.TipoOperacaoFacade;
import br.com.deltafinanceira.model.Banco;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Lead;
import br.com.deltafinanceira.model.OrgaoBanco;
import br.com.deltafinanceira.model.Simulacaocontrato;
import br.com.deltafinanceira.model.Tipooperacao;
import br.com.deltafinanceira.util.Mensagem;
import br.com.deltafinanceira.util.UsuarioLogadoMB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class ConfDadosContratoMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;

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

	private boolean operacaoinss;

	private int tipoOpcoes;
	
	private Lead lead;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		lead = (Lead) session.getAttribute("lead");
		session.removeAttribute("lead");
		if (lead != null) {
			if (lead.getIdoperacao() >0) {
				TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
				tipooiperacao = tipoOperacaoFacade.consultar(lead.getIdoperacao());
			}
		}
		this.contrato = new Contrato();
		this.contrato.setCliente(lead.getCliente());
		gerarListaBanco();
		gerarListaTipoOperacao();
		this.contrato.setDatacadastro(new Date());
		SituacaoFacade situacaoFacade = new SituacaoFacade();
		this.contrato.setSituacao(situacaoFacade.consultar(37));
		this.tipoOpcoes = 1;
		this.contrato.setTipooperacao(tipooiperacao);
	}

	public List<Banco> getListaBanco() {
		return this.listaBanco;
	}

	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}

	public Banco getBanco() {
		return this.banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public List<OrgaoBanco> getListaOrgaoBanco() {
		return this.listaOrgaoBanco;
	}

	public void setListaOrgaoBanco(List<OrgaoBanco> listaOrgaoBanco) {
		this.listaOrgaoBanco = listaOrgaoBanco;
	}

	public OrgaoBanco getOrgaoBanco() {
		return this.orgaoBanco;
	}

	public void setOrgaoBanco(OrgaoBanco orgaoBanco) {
		this.orgaoBanco = orgaoBanco;
	}

	public Contrato getContrato() {
		return this.contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public List<Tipooperacao> getListaTipoOperacao() {
		return this.listaTipoOperacao;
	}

	public void setListaTipoOperacao(List<Tipooperacao> listaTipoOperacao) {
		this.listaTipoOperacao = listaTipoOperacao;
	}

	public Tipooperacao getTipooiperacao() {
		return this.tipooiperacao;
	}

	public void setTipooiperacao(Tipooperacao tipooiperacao) {
		this.tipooiperacao = tipooiperacao;
	}

	public String getNomeCliente() {
		return this.nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Simulacaocontrato> getListaSimulacao() {
		return this.listaSimulacao;
	}

	public void setListaSimulacao(List<Simulacaocontrato> listaSimulacao) {
		this.listaSimulacao = listaSimulacao;
	}

	public boolean isOperacaoinss() {
		return this.operacaoinss;
	}

	public int getTipoOpcoes() {
		return this.tipoOpcoes;
	}

	public void setTipoOpcoes(int tipoOpcoes) {
		this.tipoOpcoes = tipoOpcoes;
	}

	public void setOperacaoinss(boolean operacaoinss) {
		this.operacaoinss = operacaoinss;
	}

	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		this.listaBanco = bancoFacade.lista("Select b From Banco b Where b.visualizar=true ORDER BY b.nome");
		if (this.listaBanco == null)
			this.listaBanco = new ArrayList<>();
	}

	public void gerarListaOrgao() {
		OrgaoBancoFacade orgaoBancoFacade = new OrgaoBancoFacade();
		this.listaOrgaoBanco = orgaoBancoFacade
				.lista("Select o From OrgaoBanco o WHERE o.banco.idbanco=" + this.banco.getIdbanco());
		if (this.listaOrgaoBanco == null)
			this.listaOrgaoBanco = new ArrayList<>();
	}

	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		this.listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t Order By t.descricao");
		if (this.listaTipoOperacao == null)
			this.listaTipoOperacao = new ArrayList<>();
	}

	public String cancelar() {
		return "consContrato";
	}

	public String cadContrato() {
		if (this.contrato.getTipooperacao() != null && this.contrato.getTipooperacao().getIdtipooperacao() != null) {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			session.setAttribute("banco", this.banco);
			session.setAttribute("contrato", this.contrato);
			session.setAttribute("orgaobanco", this.orgaoBanco);
			session.setAttribute("lead", lead);
			if (this.tipoOpcoes == 1) {
				this.contrato.setOperacaoinss(true);
				this.contrato.setCredpessoal(false);
				this.contrato.setNomeoperacao("INSS");
			} else if (this.tipoOpcoes == 2) {
				this.contrato.setOperacaoinss(false);
				this.contrato.setCredpessoal(false);
				this.contrato.setNomeoperacao("SIAPE");
			} else if (this.tipoOpcoes == 3) {
				this.contrato.setOperacaoinss(false);
				this.contrato.setCredpessoal(true);
				this.contrato.setNomeoperacao("CRED PESSOAL");
			}
			if (this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
				this.contrato.setVoltarTela("consPortabilidade");
			} else if (this.contrato.isOperacaoinss()) {
				this.contrato.setVoltarTela("consDemaisOperacoesINSS");
			} else {
				this.contrato.setVoltarTela("consDemaisOperacoes");
			}
		} else {
			Mensagem.lancarMensagemInfo("Selecione o Tipo de Operação", "");
		}
		return "cadContrato";
	}

	public String cadContratoImporte(Simulacaocontrato simulacaocontrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		popularContrato(simulacaocontrato);
		this.orgaoBanco = simulacaocontrato.getOrgaoBanco();
		this.contrato.setDatacadastro(new Date());
		this.contrato.setOperacaoinss(this.operacaoinss);
		SituacaoFacade situacaoFacade = new SituacaoFacade();
		this.contrato.setSituacao(situacaoFacade.consultar(37));
		session.setAttribute("banco", this.banco);
		session.setAttribute("contrato", this.contrato);
		session.setAttribute("orgaobanco", this.orgaoBanco);
		if (this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
			this.contrato.setVoltarTela("consPortabilidade");
		} else if (this.contrato.isOperacaoinss()) {
			this.contrato.setVoltarTela("consDemaisOperacoesINSS");
		} else {
			this.contrato.setVoltarTela("consDemaisOperacoes");
		}
		return "cadContrato";
	}

	public void pesquisar() {
		String sql = "Select s From Simulacaocontrato s WHERE  s.contrato.cliente.nome like '%" + this.nomeCliente
				+ "%' and s.contrato.cliente.cpf like '%" + this.cpf + "%'";
		if (!this.usuarioLogadoMB.getUsuario().isAcessogeral() && !this.usuarioLogadoMB.getUsuario().isSupervisao()) {
			sql = sql + "s.contrato.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario();
		}
		sql = String.valueOf(sql) + " ORDER BY s.idsimulacaocontrato DESC";
		SimulacaoContratoFacade simulacaoContratoFacade = new SimulacaoContratoFacade();
		this.listaSimulacao = simulacaoContratoFacade.lista(sql);
		if (this.listaSimulacao == null)
			this.listaSimulacao = new ArrayList<>();
	}

	public void limpar() {
		this.nomeCliente = "";
		this.cpf = "";
		this.banco = null;
	}

	public void popularContrato(Simulacaocontrato simulacaocontrato) {
		this.contrato.setAjustecomissaocheck(simulacaocontrato.getContrato().isAjustecomissaocheck());
		this.contrato.setAssinadobanco(simulacaocontrato.getContrato().isAssinadobanco());
		this.contrato.setBanco(simulacaocontrato.getContrato().getBanco());
		this.contrato.setBloqueio(simulacaocontrato.getContrato().isBloqueio());
		this.contrato.setCliente(simulacaocontrato.getContrato().getCliente());
		this.contrato.setCodigocontrato("");
		this.contrato.setContratoportado(simulacaocontrato.getContrato().getContratoportado());
		this.contrato.setDescricaobloqueio(simulacaocontrato.getContrato().getDescricaobloqueio());
		this.contrato.setDescricaodigitado(simulacaocontrato.getContrato().getDescricaodigitado());
		this.contrato.setDescricaofisico(simulacaocontrato.getContrato().getDescricaofisico());
		this.contrato.setDetalhesituacao(simulacaocontrato.getContrato().getDetalhesituacao());
		this.contrato.setDigitado(simulacaocontrato.getContrato().isDigitado());
		this.contrato.setFisico(simulacaocontrato.getContrato().isFisico());
		this.contrato.setMargemutilizada(simulacaocontrato.getContrato().getMargemutilizada());
		this.contrato.setMatricula(simulacaocontrato.getContrato().getMatricula());
		this.contrato.setObservacao(simulacaocontrato.getContrato().getObservacao());
		this.contrato.setParcela(simulacaocontrato.getContrato().getParcela());
		this.contrato.setParceladivergente(simulacaocontrato.getContrato().getParceladivergente());
		this.contrato.setParcelaspagas(simulacaocontrato.getContrato().getParcelaspagas());
		this.contrato.setParcelasrestantes(simulacaocontrato.getContrato().getParcelasrestantes());
		this.contrato.setPendente(simulacaocontrato.getContrato().isPendente());
		this.contrato.setPercentualpago(simulacaocontrato.getContrato().getPercentualpago());
		this.contrato.setPromotora(simulacaocontrato.getContrato().getPromotora());
		this.contrato.setReducaoparcela(simulacaocontrato.getContrato().isReducaoparcela());
		this.contrato.setSaldoinadimplencia(simulacaocontrato.getContrato().getSaldoinadimplencia());
		this.contrato.setSecretaria(simulacaocontrato.getContrato().getSecretaria());
		this.contrato.setSenha(simulacaocontrato.getContrato().getSenha());
		this.contrato.setSenhacontracheque(simulacaocontrato.getContrato().getSenhacontracheque());
		this.contrato.setSimulacao(false);
		this.contrato.setTarifa(simulacaocontrato.getContrato().getTarifa());
		this.contrato.setTipooperacao(simulacaocontrato.getContrato().getTipooperacao());
		this.contrato.setTotalparcelas(simulacaocontrato.getContrato().getTotalparcelas());
		this.contrato.setUltimamudancasituacao(simulacaocontrato.getContrato().getUltimamudancasituacao());
		this.contrato.setUsuario(simulacaocontrato.getContrato().getUsuario());
		this.contrato.setValorcliente(simulacaocontrato.getContrato().getValorcliente());
		this.contrato.setValorclientedivergente(simulacaocontrato.getContrato().getValoroperacaodivergente());
		this.contrato.setValoroperacao(simulacaocontrato.getContrato().getValorcliente());
		this.contrato.setValoroperacaodivergente(simulacaocontrato.getContrato().getValoroperacaodivergente());
		this.contrato.setValorparcela(simulacaocontrato.getContrato().getValorparcela());
		this.contrato.setValorquitar(simulacaocontrato.getContrato().getValorquitar());
	}
}
