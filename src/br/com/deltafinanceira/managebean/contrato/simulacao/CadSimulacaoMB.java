package br.com.deltafinanceira.managebean.contrato.simulacao;

import br.com.deltafinanceira.bean.ControladorCEPBean;
import br.com.deltafinanceira.bean.EnderecoBean;
import br.com.deltafinanceira.facade.BancoFacade;
import br.com.deltafinanceira.facade.ClienteFacade;
import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.facade.DadosBancarioFacade;
import br.com.deltafinanceira.facade.LeadFacade;
import br.com.deltafinanceira.facade.LeadHistoricoFacade;
import br.com.deltafinanceira.facade.OrgaoBancoFacade;
import br.com.deltafinanceira.facade.PromotoraFacade;
import br.com.deltafinanceira.facade.SimulacaoContratoFacade;
import br.com.deltafinanceira.facade.SituacaoFacade;
import br.com.deltafinanceira.facade.TipoOperacaoFacade;
import br.com.deltafinanceira.facade.ValoresCoeficienteFacade;
import br.com.deltafinanceira.model.Banco;
import br.com.deltafinanceira.model.Cliente;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Dadosbancario;
import br.com.deltafinanceira.model.Lead;
import br.com.deltafinanceira.model.Leadhistorico;
import br.com.deltafinanceira.model.OrgaoBanco;
import br.com.deltafinanceira.model.Promotora;
import br.com.deltafinanceira.model.Simulacaocontrato;
import br.com.deltafinanceira.model.Tipooperacao;
import br.com.deltafinanceira.model.Usuario;
import br.com.deltafinanceira.util.Formatacao;
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
public class CadSimulacaoMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;

	private Simulacaocontrato simulacaocontrato;

	private List<Banco> listaBanco;

	private List<Banco> listaBancoInicio;

	private Banco banco;

	private List<OrgaoBanco> listaOrgaoBanco;

	private OrgaoBanco orgaoBanco;

	private Dadosbancario dadosbancario;

	private List<Banco> listaBancoOperacao;

	private List<Tipooperacao> listaTipoOperacao;

	private Tipooperacao tipooiperacao;

	private Cliente cliente;

	private String cpf;

	private Banco bancoDadosBancario;

	private List<Usuario> listaUsuario;

	private Usuario usuario;

	private Promotora promotora;

	private List<Promotora> listaPromotora;

	private Lead lead;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		this.simulacaocontrato = (Simulacaocontrato) session.getAttribute("simulacaocontrato");
		session.removeAttribute("simulacaocontrato");
		this.lead = (Lead) session.getAttribute("lead");
		session.removeAttribute("lead");
		gerarListaOrgao();
		gerarListaTipoOperacao();
		gerarListaPromotora();
		gerarListaBanco();
		if (this.simulacaocontrato == null) {
			this.simulacaocontrato = new Simulacaocontrato();
			this.simulacaocontrato.setContrato(new Contrato());
			this.simulacaocontrato.getContrato().setDatacadastro(new Date());
			SituacaoFacade situacaoFacade = new SituacaoFacade();
			this.simulacaocontrato.getContrato().setSituacao(situacaoFacade.consultar(37));
			this.simulacaocontrato.getContrato().setOperacaoinss(false);
			this.usuario = this.usuarioLogadoMB.getUsuario();
			this.cliente = lead.getCliente();
			this.simulacaocontrato.setDatacadastro(Formatacao.ConvercaoDataPadrao(new Date()));
			this.dadosbancario = cliente.getDadosbancario();

		} else {
			this.cliente = this.simulacaocontrato.getContrato().getCliente();
			this.orgaoBanco = this.simulacaocontrato.getOrgaoBanco();
			this.banco = this.orgaoBanco.getBanco();
			gerarListaOrgao();
			this.promotora = this.simulacaocontrato.getContrato().getPromotora();
			this.dadosbancario = new Dadosbancario();
			this.dadosbancario = this.cliente.getDadosbancario();
			this.bancoDadosBancario = this.dadosbancario.getBanco();
		}
	}

	public synchronized Simulacaocontrato getSimulacaocontrato() {
		return this.simulacaocontrato;
	}

	public synchronized void setSimulacaocontrato(Simulacaocontrato simulacaocontrato) {
		this.simulacaocontrato = simulacaocontrato;
	}

	public synchronized List<Banco> getListaBanco() {
		return this.listaBanco;
	}

	public synchronized void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}

	public synchronized List<Banco> getListaBancoInicio() {
		return this.listaBancoInicio;
	}

	public synchronized void setListaBancoInicio(List<Banco> listaBancoInicio) {
		this.listaBancoInicio = listaBancoInicio;
	}

	public synchronized Banco getBanco() {
		return this.banco;
	}

	public synchronized void setBanco(Banco banco) {
		this.banco = banco;
	}

	public synchronized List<OrgaoBanco> getListaOrgaoBanco() {
		return this.listaOrgaoBanco;
	}

	public synchronized void setListaOrgaoBanco(List<OrgaoBanco> listaOrgaoBanco) {
		this.listaOrgaoBanco = listaOrgaoBanco;
	}

	public synchronized OrgaoBanco getOrgaoBanco() {
		return this.orgaoBanco;
	}

	public synchronized void setOrgaoBanco(OrgaoBanco orgaoBanco) {
		this.orgaoBanco = orgaoBanco;
	}

	public synchronized Dadosbancario getDadosbancario() {
		return this.dadosbancario;
	}

	public synchronized void setDadosbancario(Dadosbancario dadosbancario) {
		this.dadosbancario = dadosbancario;
	}

	public synchronized List<Banco> getListaBancoOperacao() {
		return this.listaBancoOperacao;
	}

	public synchronized void setListaBancoOperacao(List<Banco> listaBancoOperacao) {
		this.listaBancoOperacao = listaBancoOperacao;
	}

	public synchronized List<Tipooperacao> getListaTipoOperacao() {
		return this.listaTipoOperacao;
	}

	public synchronized void setListaTipoOperacao(List<Tipooperacao> listaTipoOperacao) {
		this.listaTipoOperacao = listaTipoOperacao;
	}

	public synchronized Tipooperacao getTipooiperacao() {
		return this.tipooiperacao;
	}

	public synchronized void setTipooiperacao(Tipooperacao tipooiperacao) {
		this.tipooiperacao = tipooiperacao;
	}

	public synchronized Cliente getCliente() {
		return this.cliente;
	}

	public synchronized void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public synchronized String getCpf() {
		return this.cpf;
	}

	public synchronized void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public synchronized Banco getBancoDadosBancario() {
		return this.bancoDadosBancario;
	}

	public synchronized void setBancoDadosBancario(Banco bancoDadosBancario) {
		this.bancoDadosBancario = bancoDadosBancario;
	}

	public synchronized List<Usuario> getListaUsuario() {
		return this.listaUsuario;
	}

	public synchronized void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public synchronized Usuario getUsuario() {
		return this.usuario;
	}

	public synchronized void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public synchronized Promotora getPromotora() {
		return this.promotora;
	}

	public synchronized void setPromotora(Promotora promotora) {
		this.promotora = promotora;
	}

	public synchronized List<Promotora> getListaPromotora() {
		return this.listaPromotora;
	}

	public synchronized void setListaPromotora(List<Promotora> listaPromotora) {
		this.listaPromotora = listaPromotora;
	}

	public void gerarListaOrgao() {
		OrgaoBancoFacade orgaoBancoFacade = new OrgaoBancoFacade();
		if (this.banco != null && this.banco.getIdbanco() != null)
			this.listaOrgaoBanco = orgaoBancoFacade
					.lista("Select o From OrgaoBanco o WHERE o.banco.idbanco=" + this.banco.getIdbanco());
		if (this.listaOrgaoBanco == null)
			this.listaOrgaoBanco = new ArrayList<>();
	}

	public void buscarCliente() {
		ClienteFacade clienteFacade = new ClienteFacade();
		this.cliente = clienteFacade.consultarCpf(this.cpf);
		this.simulacaocontrato.getContrato().setCliente(this.cliente);
		if (this.cliente == null) {
			this.cliente = new Cliente();
			this.dadosbancario = new Dadosbancario();
		}
	}

	public void buscarendereco() {
		ControladorCEPBean cep = new ControladorCEPBean();
		cep.setCep(this.cliente.getCep());
		EnderecoBean endereco = cep.carregarEndereco();
		this.cliente.setBairro(endereco.getBairro());
		this.cliente.setUfestado(endereco.getUf());
		this.cliente.setCidade(endereco.getLocalidade());
		this.cliente.setComplemento(endereco.getComplemento());
		if (endereco.getLogradouro() != null && endereco.getLogradouro().length() > 2) {
			String logradouro = endereco.getLogradouro().substring(endereco.getLogradouro().indexOf(" "),
					endereco.getLogradouro().length());
			int posicao = endereco.getLogradouro().length();
			for (int i = 0; i <= logradouro.length(); i++)
				posicao--;
			String tipo = endereco.getLogradouro().substring(0, posicao + 1);
			this.cliente.setLogradouro(logradouro);
			this.cliente.setTipologradouro(tipo);
		} else {
			Mensagem.lancarMensagemInfo("CEP incompleto", "preencha manualmente");
		}
	}

	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		this.listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t Where t.ativo=true");
		if (this.listaTipoOperacao == null)
			this.listaTipoOperacao = new ArrayList<>();
	}

	public void calculoParcelas() {
		if (this.simulacaocontrato.getContrato().getParcelaspagas() > 0.0D
				&& this.simulacaocontrato.getContrato().getTotalparcelas() > 0.0D) {
			this.simulacaocontrato.getContrato()
					.setPercentualpago((this.simulacaocontrato.getContrato().getParcelaspagas() * 100
							/ this.simulacaocontrato.getContrato().getTotalparcelas()));
			this.simulacaocontrato.getContrato()
					.setParcelasrestantes(this.simulacaocontrato.getContrato().getTotalparcelas()
							- this.simulacaocontrato.getContrato().getParcelaspagas());
		}
	}

	public void gerarListaPromotora() {
		PromotoraFacade promotoraFacade = new PromotoraFacade();
		this.listaPromotora = promotoraFacade.lista("SELECT p From Promotora p WHERE p.idpromotora>1");
		if (this.listaPromotora == null)
			this.listaPromotora = new ArrayList<>();
	}

	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		this.listaBancoOperacao = bancoFacade.lista("Select b From Banco b WHERE b.nome !='Nenhum' ORDER BY b.nome");
		if (this.listaBancoOperacao == null)
			this.listaBancoOperacao = new ArrayList<>();
		this.listaBanco = this.listaBancoOperacao;
		this.listaBancoInicio = this.listaBancoOperacao;
	}

	public String salvar() {
		this.simulacaocontrato.getContrato().setCliente(salvarCliente());
		ContratoFacade contratoFacade = new ContratoFacade();
		if (this.simulacaocontrato.getContrato() == null
				|| this.simulacaocontrato.getContrato().getIdcontrato() == null) {
			this.simulacaocontrato.getContrato().setUsuario(this.usuario);
			this.simulacaocontrato.getContrato().setSimulacao(true);
			if (this.simulacaocontrato.getContrato().getBanco() == null
					|| this.simulacaocontrato.getContrato().getBanco().getIdbanco() == null)
				this.simulacaocontrato.getContrato().setBanco(this.banco);

			if (lead != null && lead.getIdlead() != null) {
				LeadFacade leadFacade = new LeadFacade();
				lead.setUltimocontato(new Date());
				lead = leadFacade.salvar(lead);
				LeadHistoricoFacade leadHistoricoFacade = new LeadHistoricoFacade();
				Leadhistorico leadhistorico = new Leadhistorico();
				leadhistorico.setDatalancamento(new Date());
				leadhistorico.setDescricao("SIMULAÇÃO EMITIDA: " + this.simulacaocontrato.getContrato().getTipooperacao().getApelido() + " - "
						+ this.simulacaocontrato.getContrato().getNomeoperacao());
				leadhistorico.setLead(lead);
				leadHistoricoFacade.salvar(leadhistorico);
			}
		}
		if (this.promotora == null || this.promotora.getIdpromotora() == null) {
			PromotoraFacade promotoraFacade = new PromotoraFacade();
			this.promotora = promotoraFacade.consultar(1);
		}
		this.simulacaocontrato.getContrato().setPromotora(this.promotora);
		this.simulacaocontrato.setOrgaoBanco(this.orgaoBanco);
		this.simulacaocontrato.getContrato().setOrgaoBanco(orgaoBanco);
		ValoresCoeficienteFacade valoresCoeficienteFacade = new ValoresCoeficienteFacade();
		this.simulacaocontrato.getContrato()
				.setValorescoeficiente(valoresCoeficienteFacade.consultar(1).getIdvalorescoeficiente().intValue());
		Contrato contrato = this.simulacaocontrato.getContrato();
		contrato = contratoFacade.salvar(contrato);
		this.simulacaocontrato.setContrato(contrato);
		SimulacaoContratoFacade simulacaoContratoFacade = new SimulacaoContratoFacade();
		this.simulacaocontrato = simulacaoContratoFacade.salvar(this.simulacaocontrato);
		return "consSimulacaoContrato";
	}

	public Cliente salvarCliente() {
		ClienteFacade clienteFacade = new ClienteFacade();
		if (this.bancoDadosBancario == null || this.bancoDadosBancario.getIdbanco() == null) {
			BancoFacade bancoFacade = new BancoFacade();
			List<Banco> listaBanco = bancoFacade.lista("Select b From Banco b");
			if (listaBanco == null)
				listaBanco = new ArrayList<>();
			this.bancoDadosBancario = listaBanco.get(0);
		}
		if (this.cliente.getNascimento() != null) {
			String diames = "" + Formatacao.getDiaData(this.cliente.getNascimento())
					+ (Formatacao.getMesData(this.cliente.getNascimento()) + 1);
			this.cliente.setDiames(Integer.parseInt(diames));
		}
		salvarDadosBancarios();
		if (this.cpf != null && this.cpf.length() > 0)
			this.cliente.setCpf(this.cpf);
		this.cliente.setDadosbancario(this.dadosbancario);
		return clienteFacade.salvar(this.cliente);
	}

	public void salvarDadosBancarios() {
		DadosBancarioFacade dadosBancarioFacade = new DadosBancarioFacade();
		this.dadosbancario.setBanco(this.bancoDadosBancario);
		this.dadosbancario = dadosBancarioFacade.salvar(this.dadosbancario);
	}

	public String cancelar() {
		return "consSimulacaoContrato";
	}
}
