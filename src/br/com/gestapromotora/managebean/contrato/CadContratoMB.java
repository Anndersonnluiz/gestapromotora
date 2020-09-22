package br.com.gestapromotora.managebean.contrato;

import java.io.Serializable;
import java.sql.SQLException;
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
import br.com.gestapromotora.dao.NotificacaoDao;
import br.com.gestapromotora.dao.UsuarioDao;
import br.com.gestapromotora.facade.BancoFacade;
import br.com.gestapromotora.facade.ClienteFacade;
import br.com.gestapromotora.facade.ContratoFacade;
import br.com.gestapromotora.facade.DadosBancarioFacade;
import br.com.gestapromotora.facade.OrgaoBancoFacade;
import br.com.gestapromotora.facade.TipoOperacaoFacade;
import br.com.gestapromotora.model.Banco;
import br.com.gestapromotora.model.Cliente;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.Dadosbancario;
import br.com.gestapromotora.model.Notificacao;
import br.com.gestapromotora.model.OrgaoBanco;
import br.com.gestapromotora.model.Tipooperacao;
import br.com.gestapromotora.model.Usuario;
import br.com.gestapromotora.model.Valorescoeficiente;
import br.com.gestapromotora.util.Formatacao;
import br.com.gestapromotora.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class CadContratoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Banco> listaBanco;
	private Banco banco;
	private List<OrgaoBanco> listaOrgaoBanco;
	private OrgaoBanco orgaoBanco;
	private Contrato contrato;
	private Cliente cliente;
	private String cpf;
	private Valorescoeficiente valorescoeficiente;
	private Dadosbancario dadosbancario;
	private List<Banco> listaBancoOperacao;
	private List<Tipooperacao> listaTipoOperacao;
	private Tipooperacao tipooiperacao;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private Banco bancoDadosBancario;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		contrato = (Contrato) session.getAttribute("contrato");
		orgaoBanco = (OrgaoBanco) session.getAttribute("orgaobanco");
		banco = (Banco) session.getAttribute("banco");
		session.removeAttribute("orgaobanco");
		session.removeAttribute("contrato");
		cliente = contrato.getCliente();
		banco = orgaoBanco.getBanco();
		gerarListaBanco();
		if (contrato == null) {
			contrato = new Contrato();
		}
		valorescoeficiente = contrato.getValorescoeficiente();
		if (cliente != null) {
			if (cliente.getDadosbancario() != null) {
				dadosbancario = cliente.getDadosbancario();
			}
			cpf = cliente.getCpf();
		} else {
			cliente = new Cliente();
			dadosbancario = new Dadosbancario();
		}
		bancoDadosBancario = dadosbancario.getBanco();
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Valorescoeficiente getValorescoeficiente() {
		return valorescoeficiente;
	}

	public void setValorescoeficiente(Valorescoeficiente valorescoeficiente) {
		this.valorescoeficiente = valorescoeficiente;
	}

	public Dadosbancario getDadosbancario() {
		return dadosbancario;
	}

	public void setDadosbancario(Dadosbancario dadosbancario) {
		this.dadosbancario = dadosbancario;
	}

	public List<Banco> getListaBancoOperacao() {
		return listaBancoOperacao;
	}

	public void setListaBancoOperacao(List<Banco> listaBancoOperacao) {
		this.listaBancoOperacao = listaBancoOperacao;
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

	public UsuarioLogadoMB getUsuarioLogadoMB() {
		return usuarioLogadoMB;
	}

	public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
		this.usuarioLogadoMB = usuarioLogadoMB;
	}

	public Banco getBancoDadosBancario() {
		return bancoDadosBancario;
	}

	public void setBancoDadosBancario(Banco bancoDadosBancario) {
		this.bancoDadosBancario = bancoDadosBancario;
	}

	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		listaBancoOperacao = bancoFacade.lista("Select b From Banco b WHERE b.nome !='Nenhum'");
		if (listaBancoOperacao == null) {
			listaBancoOperacao = new ArrayList<Banco>();
		}
		listaBanco = listaBancoOperacao;
	}

	public void gerarListaOrgao() {
		OrgaoBancoFacade orgaoBancoFacade = new OrgaoBancoFacade();
		listaOrgaoBanco = orgaoBancoFacade
				.lista("Select o From OrgaoBanco o WHERE o.banco.idbanco=" + banco.getIdbanco());
		if (listaOrgaoBanco == null) {
			listaOrgaoBanco = new ArrayList<OrgaoBanco>();
		}
	}

	public void buscarCliente() {
		ClienteFacade clienteFacade = new ClienteFacade();
		cliente = clienteFacade.consultarCpf(cpf);
		contrato.setCliente(cliente);
		if (cliente == null) {
			cliente = new Cliente();
			dadosbancario = new Dadosbancario();
		}
	}

	public void buscarendereco() {
		ControladorCEPBean cep = new ControladorCEPBean();
		cep.setCep(cliente.getCep());
		EnderecoBean endereco = cep.carregarEndereco();
		if (endereco.getLogradouro() != null) {
			cliente.setBairro(endereco.getBairro());
			cliente.setUfestado(endereco.getUf());
			cliente.setCidade(endereco.getLocalidade());
			cliente.setComplemento(endereco.getComplemento());
			String logradouro = endereco.getLogradouro().substring(endereco.getLogradouro().indexOf(" "),
					endereco.getLogradouro().length());
			int posicao = endereco.getLogradouro().length();
			for (int i = 0; i <= logradouro.length(); i++) {
				posicao = posicao - 1;
			}
			String tipo = endereco.getLogradouro().substring(0, posicao + 1);
			cliente.setLogradouro(logradouro);
			cliente.setTipologradouro(tipo);
		}
	}

	public String selecionarCoeficiente() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		cliente.setCpf(cpf);
		if (bancoDadosBancario != null) {
			dadosbancario.setBanco(bancoDadosBancario);
		}
		cliente.setDadosbancario(dadosbancario);
		contrato.setCliente(cliente);
		session.setAttribute("contrato", contrato);
		session.setAttribute("orgaobanco", orgaoBanco);
		return "selecioneCoeficiente";
	}

	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t");
		if (listaTipoOperacao == null) {
			listaTipoOperacao = new ArrayList<Tipooperacao>();
		}
	}

	public void calculoParcelas() {
		if (contrato.getParcelaspagas() > 0.0 && contrato.getTotalparcelas() > 0.0) {
			contrato.setPercentualpago((contrato.getParcelaspagas() * 100) / contrato.getTotalparcelas());
			contrato.setParcelasrestantes(contrato.getTotalparcelas() - contrato.getParcelaspagas());
		}
	}

	public String salvar() {
		contrato.setCliente(salvarCliente());
		contrato.setUsuario(usuarioLogadoMB.getUsuario());
		ContratoFacade contratoFacade = new ContratoFacade();
		contrato.setCodigocontrato(gerarCodigo());
		if (contrato.getIdcontrato() == null) {
			gerarNotificacao();
		}
		contrato = contratoFacade.salvar(contrato);
		return "consContrato";
	}
	
	
	public void gerarNotificacao() {
		UsuarioDao usuarioDao = new UsuarioDao();
		NotificacaoDao notificacaoDao = new NotificacaoDao();
		try {
			List<Usuario> listaUsuario = usuarioDao.listar("Select u From Usuario u WHERE "
					+ "u.tipocolaborador.acessocolaborador.notificacaooperacional=true");
			for (int i = 0; i < listaUsuario.size(); i++) {
				Notificacao notificacao = new Notificacao();
				notificacao.setDatalancamento(new Date());
				notificacao.setVisto(false);
				notificacao.setUsuario(listaUsuario.get(i));
				notificacao.setTitulo("Novo Contrato");
				notificacao.setDescricao("Novo contrato emitido pelo corretor(a) " + usuarioLogadoMB.getUsuario().getNome());
				notificacaoDao.salvar(notificacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Cliente salvarCliente() {
		ClienteFacade clienteFacade = new ClienteFacade();
		if (bancoDadosBancario == null || bancoDadosBancario.getIdbanco() == null) {
			BancoFacade bancoFacade = new BancoFacade();
			List<Banco> listaBanco = bancoFacade.lista("Select b From Banco b Where b.nome='Nenhum'");
			if (listaBanco == null) {
				listaBanco = new ArrayList<Banco>();
			}
			bancoDadosBancario = listaBanco.get(0);
		}
		salvarDadosBancarios();
		cliente.setDadosbancario(dadosbancario);
		return clienteFacade.salvar(cliente);
	}

	public void salvarDadosBancarios() {
		DadosBancarioFacade dadosBancarioFacade = new DadosBancarioFacade();
		dadosbancario.setBanco(bancoDadosBancario);
		dadosbancario = dadosBancarioFacade.salvar(dadosbancario);
	}

	public String cancelar() {
		return "consContrato";
	}

	public String gerarCodigo() {
		String codigo = "FF " + Formatacao.ConvercaoDataPadrao(new Date()) + " - ";
		ContratoFacade contratoFacade = new ContratoFacade();
		List<Contrato> lisContratos = contratoFacade.lista(
				"Select c From Contrato c Where c.datacadastro='" + Formatacao.ConvercaoDataSql(new Date()) + "'");
		if (lisContratos == null) {
			lisContratos = new ArrayList<Contrato>();
		}
		codigo = codigo + (lisContratos.size() + 1);
		return codigo;
	}

}
