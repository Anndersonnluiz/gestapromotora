package br.com.gestapromotora.managebean.contrato.simulacao;

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

import br.com.gestapromotora.bean.ControladorCEPBean;
import br.com.gestapromotora.bean.EnderecoBean;
import br.com.gestapromotora.facade.BancoFacade;
import br.com.gestapromotora.facade.ClienteFacade;
import br.com.gestapromotora.facade.ContratoFacade;
import br.com.gestapromotora.facade.DadosBancarioFacade;
import br.com.gestapromotora.facade.OrgaoBancoFacade;
import br.com.gestapromotora.facade.PromotoraFacade;
import br.com.gestapromotora.facade.SimulacaoContratoFacade;
import br.com.gestapromotora.facade.SituacaoFacade;
import br.com.gestapromotora.facade.TipoOperacaoFacade;
import br.com.gestapromotora.facade.ValoresCoeficienteFacade;
import br.com.gestapromotora.model.Banco;
import br.com.gestapromotora.model.Cliente;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.Dadosbancario;
import br.com.gestapromotora.model.OrgaoBanco;
import br.com.gestapromotora.model.Promotora;
import br.com.gestapromotora.model.Simulacaocontrato;
import br.com.gestapromotora.model.Tipooperacao;
import br.com.gestapromotora.model.Usuario;
import br.com.gestapromotora.util.Formatacao;
import br.com.gestapromotora.util.Mensagem;
import br.com.gestapromotora.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class CadSimulacaoMB implements Serializable{

	/**
	 * 
	 */
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
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		simulacaocontrato = (Simulacaocontrato) session.getAttribute("simulacaocontrato");
		session.removeAttribute("simulacaocontrato");
		gerarListaOrgao();
		gerarListaTipoOperacao();
		gerarListaPromotora();
		gerarListaBanco();
		if (simulacaocontrato == null) {
			simulacaocontrato = new Simulacaocontrato();
			simulacaocontrato.setContrato(new Contrato());
			simulacaocontrato.getContrato().setDatacadastro(new Date());
			SituacaoFacade situacaoFacade = new SituacaoFacade();
			simulacaocontrato.getContrato().setSituacao(situacaoFacade.consultar(37));
			simulacaocontrato.getContrato().setOperacaoinss(false);
			usuario = usuarioLogadoMB.getUsuario();
			cliente = new Cliente();
			simulacaocontrato.setDatacadastro(Formatacao.ConvercaoDataPadrao(new Date()));
			dadosbancario = new Dadosbancario();
		}else {
			cliente = simulacaocontrato.getContrato().getCliente();
			orgaoBanco = simulacaocontrato.getOrgaoBanco();
			banco = orgaoBanco.getBanco();
			gerarListaOrgao();
			promotora = simulacaocontrato.getContrato().getPromotora();
		}
	}



	public synchronized Simulacaocontrato getSimulacaocontrato() {
		return simulacaocontrato;
	}



	public synchronized void setSimulacaocontrato(Simulacaocontrato simulacaocontrato) {
		this.simulacaocontrato = simulacaocontrato;
	}



	public synchronized List<Banco> getListaBanco() {
		return listaBanco;
	}



	public synchronized void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}



	public synchronized List<Banco> getListaBancoInicio() {
		return listaBancoInicio;
	}



	public synchronized void setListaBancoInicio(List<Banco> listaBancoInicio) {
		this.listaBancoInicio = listaBancoInicio;
	}



	public synchronized Banco getBanco() {
		return banco;
	}



	public synchronized void setBanco(Banco banco) {
		this.banco = banco;
	}



	public synchronized List<OrgaoBanco> getListaOrgaoBanco() {
		return listaOrgaoBanco;
	}



	public synchronized void setListaOrgaoBanco(List<OrgaoBanco> listaOrgaoBanco) {
		this.listaOrgaoBanco = listaOrgaoBanco;
	}



	public synchronized OrgaoBanco getOrgaoBanco() {
		return orgaoBanco;
	}



	public synchronized void setOrgaoBanco(OrgaoBanco orgaoBanco) {
		this.orgaoBanco = orgaoBanco;
	}



	public synchronized Dadosbancario getDadosbancario() {
		return dadosbancario;
	}



	public synchronized void setDadosbancario(Dadosbancario dadosbancario) {
		this.dadosbancario = dadosbancario;
	}



	public synchronized List<Banco> getListaBancoOperacao() {
		return listaBancoOperacao;
	}



	public synchronized void setListaBancoOperacao(List<Banco> listaBancoOperacao) {
		this.listaBancoOperacao = listaBancoOperacao;
	}



	public synchronized List<Tipooperacao> getListaTipoOperacao() {
		return listaTipoOperacao;
	}



	public synchronized void setListaTipoOperacao(List<Tipooperacao> listaTipoOperacao) {
		this.listaTipoOperacao = listaTipoOperacao;
	}



	public synchronized Tipooperacao getTipooiperacao() {
		return tipooiperacao;
	}



	public synchronized void setTipooiperacao(Tipooperacao tipooiperacao) {
		this.tipooiperacao = tipooiperacao;
	}



	public synchronized Cliente getCliente() {
		return cliente;
	}



	public synchronized void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public synchronized String getCpf() {
		return cpf;
	}



	public synchronized void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public synchronized Banco getBancoDadosBancario() {
		return bancoDadosBancario;
	}



	public synchronized void setBancoDadosBancario(Banco bancoDadosBancario) {
		this.bancoDadosBancario = bancoDadosBancario;
	}



	public synchronized List<Usuario> getListaUsuario() {
		return listaUsuario;
	}



	public synchronized void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}



	public synchronized Usuario getUsuario() {
		return usuario;
	}



	public synchronized void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	public synchronized Promotora getPromotora() {
		return promotora;
	}



	public synchronized void setPromotora(Promotora promotora) {
		this.promotora = promotora;
	}



	public synchronized List<Promotora> getListaPromotora() {
		return listaPromotora;
	}



	public synchronized void setListaPromotora(List<Promotora> listaPromotora) {
		this.listaPromotora = listaPromotora;
	}



	public void gerarListaOrgao() {
		OrgaoBancoFacade orgaoBancoFacade = new OrgaoBancoFacade();
		if (banco != null && banco.getIdbanco() != null) {
			listaOrgaoBanco = orgaoBancoFacade
					.lista("Select o From OrgaoBanco o WHERE o.banco.idbanco=" + banco.getIdbanco());
		}
		if (listaOrgaoBanco == null) {
			listaOrgaoBanco = new ArrayList<OrgaoBanco>();
		}
	}

	public void buscarCliente() {
		ClienteFacade clienteFacade = new ClienteFacade();
		cliente = clienteFacade.consultarCpf(cpf);
		simulacaocontrato.getContrato().setCliente(cliente);
		if (cliente == null) {
			cliente = new Cliente();
			dadosbancario = new Dadosbancario();
		}
	}

	public void buscarendereco() {
		ControladorCEPBean cep = new ControladorCEPBean();
		cep.setCep(cliente.getCep());
		EnderecoBean endereco = cep.carregarEndereco();
		cliente.setBairro(endereco.getBairro());
		cliente.setUfestado(endereco.getUf());
		cliente.setCidade(endereco.getLocalidade());
		cliente.setComplemento(endereco.getComplemento());
		if (endereco.getLogradouro() != null && endereco.getLogradouro().length() > 2) {
			String logradouro = endereco.getLogradouro().substring(endereco.getLogradouro().indexOf(" "),
					endereco.getLogradouro().length());
			int posicao = endereco.getLogradouro().length();
			for (int i = 0; i <= logradouro.length(); i++) {
				posicao = posicao - 1;
			}
			String tipo = endereco.getLogradouro().substring(0, posicao + 1);
			cliente.setLogradouro(logradouro);
			cliente.setTipologradouro(tipo);
		}else {
			Mensagem.lancarMensagemInfo("CEP incompleto", "preencha manualmente");
		}
	}
	
	
	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t");
		if (listaTipoOperacao == null) {
			listaTipoOperacao = new ArrayList<Tipooperacao>();
		}
	}
	
	
	public void calculoParcelas() {
		if (simulacaocontrato.getContrato().getParcelaspagas() > 0.0 && simulacaocontrato.getContrato().getTotalparcelas() > 0.0) {
			simulacaocontrato.getContrato().setPercentualpago((simulacaocontrato.getContrato().getParcelaspagas() * 100) / simulacaocontrato.getContrato().getTotalparcelas());
			simulacaocontrato.getContrato().setParcelasrestantes(simulacaocontrato.getContrato().getTotalparcelas() - simulacaocontrato.getContrato().getParcelaspagas());
		}
	}
	
	
	public void gerarListaPromotora() {
		PromotoraFacade promotoraFacade = new PromotoraFacade();
		listaPromotora = promotoraFacade.lista("SELECT p From Promotora p WHERE p.idpromotora>1");
		if (listaPromotora == null) {
			listaPromotora = new ArrayList<Promotora>();
		}
	}
	
	
	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		listaBancoOperacao = bancoFacade.lista("Select b From Banco b WHERE b.nome !='Nenhum' ORDER BY b.nome");
		if (listaBancoOperacao == null) {
			listaBancoOperacao = new ArrayList<Banco>();
		}
		listaBanco = listaBancoOperacao;
		listaBancoInicio = listaBancoOperacao;
	}
	
	
	
	public String salvar() {
		simulacaocontrato.getContrato().setCliente(salvarCliente());
		simulacaocontrato.getContrato().setUsuario(usuario);
		ContratoFacade contratoFacade = new ContratoFacade();
		if (simulacaocontrato.getContrato() == null || simulacaocontrato.getContrato().getIdcontrato() == null) {
			simulacaocontrato.getContrato().setSimulacao(true);
			if (simulacaocontrato.getContrato().getBanco() == null || simulacaocontrato.getContrato().getBanco().getIdbanco() == null) {
				simulacaocontrato.getContrato().setBanco(banco);
			}
		}
		if (promotora == null || promotora.getIdpromotora() == null) {
			PromotoraFacade promotoraFacade = new PromotoraFacade();
			promotora = promotoraFacade.consultar(1);
		}
		simulacaocontrato.getContrato().setPromotora(promotora);
		simulacaocontrato.setOrgaoBanco(orgaoBanco);
		ValoresCoeficienteFacade valoresCoeficienteFacade = new ValoresCoeficienteFacade();
		simulacaocontrato.getContrato().setValorescoeficiente(valoresCoeficienteFacade.consultar(1));
		Contrato contrato = simulacaocontrato.getContrato();
		contrato = contratoFacade.salvar(contrato);
		simulacaocontrato.setContrato(contrato);
		SimulacaoContratoFacade simulacaoContratoFacade = new SimulacaoContratoFacade();
		simulacaocontrato = simulacaoContratoFacade.salvar(simulacaocontrato);
		return "consSimulacaoContrato";
	}
	
	
	public Cliente salvarCliente() {
		ClienteFacade clienteFacade = new ClienteFacade();
		if (bancoDadosBancario == null || bancoDadosBancario.getIdbanco() == null) {
			BancoFacade bancoFacade = new BancoFacade();
			List<Banco> listaBanco = bancoFacade.lista("Select b From Banco b");
			if (listaBanco == null) {
				listaBanco = new ArrayList<Banco>();
			}
			bancoDadosBancario = listaBanco.get(0);
		}
		if (cliente.getNascimento() != null) {
			String diames = "" + Formatacao.getDiaData(cliente.getNascimento())
					+ (Formatacao.getMesData(cliente.getNascimento()) + 1);
			cliente.setDiames(Integer.parseInt(diames));
		}
		salvarDadosBancarios();
		if (cpf != null && cpf.length() > 0) {
			cliente.setCpf(cpf);
		}
		cliente.setDadosbancario(dadosbancario);
		return clienteFacade.salvar(cliente);
	}

	public void salvarDadosBancarios() {
		DadosBancarioFacade dadosBancarioFacade = new DadosBancarioFacade();
		dadosbancario.setBanco(bancoDadosBancario);
		dadosbancario = dadosBancarioFacade.salvar(dadosbancario);
	}
	
	
	public String cancelar() {
		return "consSimulacaoContrato";
	}
	
	
	
	
	
	
	

}
