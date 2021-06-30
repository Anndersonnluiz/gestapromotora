package br.com.deltafinanceira.managebean.contrato;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.deltafinanceira.bean.ControladorCEPBean;
import br.com.deltafinanceira.bean.EnderecoBean;
import br.com.deltafinanceira.dao.NotificacaoDao;
import br.com.deltafinanceira.dao.TipoArquivoDao;
import br.com.deltafinanceira.dao.UsuarioDao;
import br.com.deltafinanceira.facade.BancoFacade;
import br.com.deltafinanceira.facade.CampanhasContratoFacade;
import br.com.deltafinanceira.facade.CampanhasUsuarioFacade;
import br.com.deltafinanceira.facade.ClienteFacade;
import br.com.deltafinanceira.facade.CoeficienteFacade;
import br.com.deltafinanceira.facade.ContratoArquivoFacade;
import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.facade.ContratoUnificacaoFacade;
import br.com.deltafinanceira.facade.DadosBancarioFacade;
import br.com.deltafinanceira.facade.FormalizacaoFacade;
import br.com.deltafinanceira.facade.HistoricoComissaoFacade;
import br.com.deltafinanceira.facade.HistoricoUsuarioFacade;
import br.com.deltafinanceira.facade.LeadFacade;
import br.com.deltafinanceira.facade.LeadHistoricoFacade;
import br.com.deltafinanceira.facade.MetaFaturamentoMensalFacade;
import br.com.deltafinanceira.facade.OrgaoBancoFacade;
import br.com.deltafinanceira.facade.PromotoraFacade;
import br.com.deltafinanceira.facade.RankingVendasAnualFacade;
import br.com.deltafinanceira.facade.RankingVendasFacade;
import br.com.deltafinanceira.facade.TipoOperacaoFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Banco;
import br.com.deltafinanceira.model.Campanhascontrato;
import br.com.deltafinanceira.model.Campanhasusuario;
import br.com.deltafinanceira.model.Cliente;
import br.com.deltafinanceira.model.Coeficiente;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Contratoarquivo;
import br.com.deltafinanceira.model.Contratounificacao;
import br.com.deltafinanceira.model.Dadosbancario;
import br.com.deltafinanceira.model.Formalizacao;
import br.com.deltafinanceira.model.Historicocomissao;
import br.com.deltafinanceira.model.Historicousuario;
import br.com.deltafinanceira.model.Lead;
import br.com.deltafinanceira.model.Leadhistorico;
import br.com.deltafinanceira.model.Metafaturamentomensal;
import br.com.deltafinanceira.model.Notificacao;
import br.com.deltafinanceira.model.OrgaoBanco;
import br.com.deltafinanceira.model.Promotora;
import br.com.deltafinanceira.model.Rankingvendas;
import br.com.deltafinanceira.model.Rankingvendasanual;
import br.com.deltafinanceira.model.Tipoarquivo;
import br.com.deltafinanceira.model.Tipooperacao;
import br.com.deltafinanceira.model.Usuario;
import br.com.deltafinanceira.model.Valorescoeficiente;
import br.com.deltafinanceira.util.Formatacao;
import br.com.deltafinanceira.util.Ftp;
import br.com.deltafinanceira.util.Mensagem;
import br.com.deltafinanceira.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class CadFGTSMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Banco> listaBanco;

	private List<Banco> listaBancoInicio;

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

	private int mes;

	private int ano;

	private Coeficiente coeficiente;

	private boolean novo = true;

	private List<Contratoarquivo> listaContratoArquivo;

	private Tipoarquivo tipoarquivo;

	private StreamedContent fileDownload;

	private List<String> listaNomeArquivo;

	private UploadedFile file;

	private boolean arquivoEnviado;

	private String tipoDocumento;

	private List<Tipoarquivo> listaTipoArquivo;

	private Contratoarquivo contratoarquivo;

	private List<Usuario> listaUsuario;

	private Usuario usuario;

	private boolean habilitarUsuario = true;

	private Promotora promotora;

	private List<Promotora> listaPromotora;

	private String voltarTela;

	private Contratounificacao contratounificacao;

	private List<Contratounificacao> listaContratoUnificacao;

	private Lead lead;

	private Formalizacao formalizacao;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		this.contrato = (Contrato) session.getAttribute("contrato");
		this.orgaoBanco = (OrgaoBanco) session.getAttribute("orgaobanco");
		this.banco = (Banco) session.getAttribute("banco");
		this.coeficiente = (Coeficiente) session.getAttribute("coeficiente");
		this.voltarTela = (String) session.getAttribute("voltarTela");
		this.lead = (Lead) session.getAttribute("lead");
		this.formalizacao = (Formalizacao) session.getAttribute("formalizacao");
		session.removeAttribute("formalizacao");
		session.removeAttribute("lead");
		session.removeAttribute("orgaobanco");
		session.removeAttribute("contrato");
		session.removeAttribute("coeficiente");
		session.removeAttribute("voltarTela");
		this.cliente = this.contrato.getCliente();
		if (this.orgaoBanco != null)
			this.banco = this.orgaoBanco.getBanco();
		gerarListaBanco();
		gerarListaTipoArquivo();
		gerarListaOrgao();
		gerarListaUsuario();
		gerarListaPromotora();
		if (this.contrato == null) {
			this.contrato = new Contrato();
			this.mes = Formatacao.getMesData(new Date()) + 1;
			this.ano = Formatacao.getAnoData(new Date());
			this.usuario = this.usuarioLogadoMB.getUsuario();
			this.contratounificacao = new Contratounificacao();
		} else if (this.contrato.getIdcontrato() != null) {
			this.novo = false;
			gerarListaContratoAquivo();
			this.usuario = this.contrato.getUsuario();
			this.promotora = this.contrato.getPromotora();
			this.contratounificacao = new Contratounificacao();
			buscarUnificacoes();
		} else {
			if (formalizacao != null) {
				this.usuario = formalizacao.getUsuario();
				contrato.setSecretaria(formalizacao.getTipobeneficio());
				contrato.setMatricula("" + formalizacao.getNumerobeneficio());
				cpf = formalizacao.getCpf();
				buscarCliente();
				contrato.setObservacao(formalizacao.getObservacoes());
				cliente.setTelefonecelular(formalizacao.getTelefone1());
				cliente.setTelefonecomercial(formalizacao.getTelefone2());
				cliente.setTelefoneresidencial(formalizacao.getTelefone3());
			} else {
				this.usuario = this.usuarioLogadoMB.getUsuario();
			}
			this.promotora = this.contrato.getPromotora();
			this.contratounificacao = new Contratounificacao();
			BancoFacade bancoFacade = new BancoFacade();
			this.banco = bancoFacade.consultar(165);
			gerarListaOrgao();
		}
		if (this.usuarioLogadoMB.getUsuario().isAcessogeral())
			this.habilitarUsuario = false;
		if (this.cliente != null) {
			if (this.cliente.getDadosbancario() != null)
				this.dadosbancario = this.cliente.getDadosbancario();
			if (cliente.getCpf() != null && cliente.getCpf().length() > 0) {
				this.cpf = this.cliente.getCpf();
			}
		} else {
			this.cliente = new Cliente();
			this.dadosbancario = new Dadosbancario();
			this.cliente.setUsuario(this.usuario);
		}
		this.bancoDadosBancario = this.dadosbancario.getBanco();
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

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Valorescoeficiente getValorescoeficiente() {
		return this.valorescoeficiente;
	}

	public void setValorescoeficiente(Valorescoeficiente valorescoeficiente) {
		this.valorescoeficiente = valorescoeficiente;
	}

	public Dadosbancario getDadosbancario() {
		return this.dadosbancario;
	}

	public void setDadosbancario(Dadosbancario dadosbancario) {
		this.dadosbancario = dadosbancario;
	}

	public List<Banco> getListaBancoOperacao() {
		return this.listaBancoOperacao;
	}

	public void setListaBancoOperacao(List<Banco> listaBancoOperacao) {
		this.listaBancoOperacao = listaBancoOperacao;
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

	public UsuarioLogadoMB getUsuarioLogadoMB() {
		return this.usuarioLogadoMB;
	}

	public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
		this.usuarioLogadoMB = usuarioLogadoMB;
	}

	public Banco getBancoDadosBancario() {
		return this.bancoDadosBancario;
	}

	public void setBancoDadosBancario(Banco bancoDadosBancario) {
		this.bancoDadosBancario = bancoDadosBancario;
	}

	public int getMes() {
		return this.mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return this.ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Coeficiente getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(Coeficiente coeficiente) {
		this.coeficiente = coeficiente;
	}

	public boolean isNovo() {
		return this.novo;
	}

	public void setNovo(boolean novo) {
		this.novo = novo;
	}

	public List<Contratoarquivo> getListaContratoArquivo() {
		return this.listaContratoArquivo;
	}

	public void setListaContratoArquivo(List<Contratoarquivo> listaContratoArquivo) {
		this.listaContratoArquivo = listaContratoArquivo;
	}

	public Tipoarquivo getTipoarquivo() {
		return this.tipoarquivo;
	}

	public void setTipoarquivo(Tipoarquivo tipoarquivo) {
		this.tipoarquivo = tipoarquivo;
	}

	public StreamedContent getFileDownload() {
		return this.fileDownload;
	}

	public void setFileDownload(StreamedContent fileDownload) {
		this.fileDownload = fileDownload;
	}

	public List<String> getListaNomeArquivo() {
		return this.listaNomeArquivo;
	}

	public void setListaNomeArquivo(List<String> listaNomeArquivo) {
		this.listaNomeArquivo = listaNomeArquivo;
	}

	public UploadedFile getFile() {
		return this.file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public boolean isArquivoEnviado() {
		return this.arquivoEnviado;
	}

	public void setArquivoEnviado(boolean arquivoEnviado) {
		this.arquivoEnviado = arquivoEnviado;
	}

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public List<Tipoarquivo> getListaTipoArquivo() {
		return this.listaTipoArquivo;
	}

	public void setListaTipoArquivo(List<Tipoarquivo> listaTipoArquivo) {
		this.listaTipoArquivo = listaTipoArquivo;
	}

	public Contratoarquivo getContratoarquivo() {
		return this.contratoarquivo;
	}

	public void setContratoarquivo(Contratoarquivo contratoarquivo) {
		this.contratoarquivo = contratoarquivo;
	}

	public List<Banco> getListaBancoInicio() {
		return this.listaBancoInicio;
	}

	public void setListaBancoInicio(List<Banco> listaBancoInicio) {
		this.listaBancoInicio = listaBancoInicio;
	}

	public List<Usuario> getListaUsuario() {
		return this.listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isHabilitarUsuario() {
		return this.habilitarUsuario;
	}

	public void setHabilitarUsuario(boolean habilitarUsuario) {
		this.habilitarUsuario = habilitarUsuario;
	}

	public Promotora getPromotora() {
		return this.promotora;
	}

	public void setPromotora(Promotora promotora) {
		this.promotora = promotora;
	}

	public List<Promotora> getListaPromotora() {
		return this.listaPromotora;
	}

	public void setListaPromotora(List<Promotora> listaPromotora) {
		this.listaPromotora = listaPromotora;
	}

	public Contratounificacao getContratounificacao() {
		return this.contratounificacao;
	}

	public void setContratounificacao(Contratounificacao contratounificacao) {
		this.contratounificacao = contratounificacao;
	}

	public List<Contratounificacao> getListaContratoUnificacao() {
		return this.listaContratoUnificacao;
	}

	public void setListaContratoUnificacao(List<Contratounificacao> listaContratoUnificacao) {
		this.listaContratoUnificacao = listaContratoUnificacao;
	}

	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		this.listaBancoOperacao = bancoFacade.lista("Select b From Banco b WHERE b.visualizar=true ORDER BY b.codigo");
		if (this.listaBancoOperacao == null)
			this.listaBancoOperacao = new ArrayList<>();
		this.listaBanco = this.listaBancoOperacao;
		this.listaBancoInicio = this.listaBancoOperacao;
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
		this.contrato.setCliente(this.cliente);
		if (this.cliente == null) {
			this.cliente = new Cliente();
			this.dadosbancario = new Dadosbancario();
			this.cliente.setUsuario(this.usuario);
		} else if (this.cliente != null && this.cliente.getUsuario().getIdusuario() != usuario.getIdusuario()
				&& this.cliente.getUsuario().getIdusuario() != 13) {
			Mensagem.lancarMensagemWarn("Este cliente pertence ao corretor(a): ", this.cliente.getUsuario().getNome());
			this.cliente = new Cliente();
			this.dadosbancario = new Dadosbancario();
			this.cliente.setUsuario(this.usuario);
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

	public String selecionarCoeficiente() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		if (gerarListaValores()) {
			this.cliente.setCpf(this.cpf);
			if (this.bancoDadosBancario != null)
				this.dadosbancario.setBanco(this.bancoDadosBancario);
			this.cliente.setDadosbancario(this.dadosbancario);
			this.contrato.setCliente(this.cliente);
			session.setAttribute("contrato", this.contrato);
			session.setAttribute("orgaobanco", this.orgaoBanco);
			return "selecioneCoeficiente";
		}
		Mensagem.lancarMensagemWarn("Sem Coeficiente existente", "");
		return "";
	}

	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		this.listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t Where t.ativo=true");
		if (this.listaTipoOperacao == null)
			this.listaTipoOperacao = new ArrayList<>();
	}

	public void calculoParcelas() {
		if (this.contrato.getParcelaspagas() > 0.0D && this.contrato.getTotalparcelas() > 0.0D) {
			this.contrato
					.setPercentualpago((this.contrato.getParcelaspagas() * 100 / this.contrato.getTotalparcelas()));
			this.contrato.setParcelasrestantes(this.contrato.getTotalparcelas() - this.contrato.getParcelaspagas());
		}
	}

	public String salvar() {
		if (contrato.getMatricula() != null) {
			cliente.setMatricula(contrato.getMatricula());
		}
		this.contrato.setCliente(salvarCliente());
		this.contrato.setUsuario(this.usuario);
		ContratoFacade contratoFacade = new ContratoFacade();
		if (this.contrato == null || this.contrato.getIdcontrato() == null) {
			this.contrato.setIdregracoeficiente(0);
			if (this.contrato.getBanco() == null || this.contrato.getBanco().getIdbanco() == null) {
				this.contrato.setBanco(this.banco);
			}
			

		}
		if (orgaoBanco == null && listaOrgaoBanco != null) {
			orgaoBanco = listaOrgaoBanco.get(0);
		}
		if (this.promotora == null || this.promotora.getIdpromotora() == null) {
			PromotoraFacade promotoraFacade = new PromotoraFacade();
			this.promotora = promotoraFacade.consultar(1);
		}
		this.contrato.setOrgaoBanco(this.orgaoBanco);
		this.contrato.setPromotora(this.promotora);

		if (this.contrato == null || this.contrato.getIdcontrato() == null)
			this.contrato.setCodigocontrato(gerarCodigo(0));
		this.contrato = contratoFacade.salvar(this.contrato);
		if (this.novo) {
			if (usuarioLogadoMB.getUsuario().getIdusuario() != 1 && !usuarioLogadoMB.getUsuario().isTreinamento()) {
				gerarNotificacao(this.contrato);
			}
			gerarComissao(this.contrato);
			Historicousuario historicousuario = new Historicousuario();
			salvarHistorico(historicousuario, this.contrato);
			//gerarListaCampanhasCorretor();
		}
		verificarUpload(this.contrato);
		if (this.voltarTela != null && this.voltarTela.length() > 0)
			return this.voltarTela;
		return "consContrato";
	}

	public void verificarUpload(Contrato contrato) {
		if (this.listaContratoArquivo == null)
			this.listaContratoArquivo = new ArrayList<>();
		List<Contratoarquivo> listaArquivos = this.listaContratoArquivo;
		for (int i = 0; i < listaArquivos.size(); i++) {
			ContratoArquivoFacade contratoArquivoFacade = new ContratoArquivoFacade();
			if (listaArquivos.get(i).isNovoupload()) {
				((Contratoarquivo) listaArquivos.get(i)).setContrato(contrato);
				contratoArquivoFacade.salvar(listaArquivos.get(i));
			}
		}
	}

	public void gerarNotificacao(Contrato contrato) {
		UsuarioDao usuarioDao = new UsuarioDao();
		NotificacaoDao notificacaoDao = new NotificacaoDao();
		try {
			List<Usuario> listaUsuario = usuarioDao.listar(
					"Select u From Usuario u WHERE u.tipocolaborador.acessocolaborador.notificacaooperacional=true and u.departamento.filial.idfilial="
							+

							this.usuario.getDepartamento().getFilial().getIdfilial());
			for (int i = 0; i < listaUsuario.size(); i++) {
				Notificacao notificacao = new Notificacao();
				notificacao.setDatalancamento(new Date());
				notificacao.setVisto(false);
				notificacao.setUsuario(listaUsuario.get(i));
				notificacao.setIdcontrato(contrato.getIdcontrato().intValue());
				notificacao.setTipooperacao(contrato.getTipooperacao().getDescricao());
				notificacao.setTitulo("Novo Contrato: " + contrato.getCodigocontrato());
				notificacao.setDescricao(String.valueOf(contrato.getTipooperacao().getDescricao())
						+ " emitido pelo corretor(a) " + this.usuario.getNome());
				notificacaoDao.salvar(notificacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void gerarMetaFaturamento() {
		String tipo;
		Metafaturamentomensal metafaturamentomensal;
		MetaFaturamentoMensalFacade mensalFacade = new MetaFaturamentoMensalFacade();
		this.mes = Formatacao.getMesData(new Date()) + 1;
		this.ano = Formatacao.getAnoData(new Date());
		String sql = "Select m From Metafaturamentomensal m WHERE m.mes=" + this.mes + " AND m.ano=" + this.ano;

		tipo = "DEMAIS OPERAÇÃO";
		sql = sql + " and m.tipo='DEMAIS OPERAÇÃO'";
		List<Metafaturamentomensal> lista = mensalFacade.lista(sql);
		if (lista == null || lista.isEmpty()) {
			metafaturamentomensal = new Metafaturamentomensal();
			metafaturamentomensal.setAno(this.ano);
			metafaturamentomensal.setMes(this.mes);
			metafaturamentomensal.setPercentualmeta(0.0F);
			metafaturamentomensal.setCormeta("#f3291f");
			metafaturamentomensal.setTipo(tipo);
		} else {
			metafaturamentomensal = lista.get(0);
		}
		if (this.contrato.getParcelaspagas() > 12
				&& this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
			metafaturamentomensal.setValoratual(metafaturamentomensal.getValoratual() + this.contrato.getValorquitar());
		} else {
			metafaturamentomensal
					.setValoratual(metafaturamentomensal.getValoratual() + this.contrato.getValoroperacao());
		}
		mensalFacade.salvar(metafaturamentomensal);
	}

	public void gerarRankingMensal() {
		Rankingvendas rankingvendas;
		RankingVendasFacade rankingVendasFacade = new RankingVendasFacade();
		this.mes = Formatacao.getMesData(new Date()) + 1;
		this.ano = Formatacao.getAnoData(new Date());
		List<Rankingvendas> listaRanking = rankingVendasFacade
				.lista("Select r From Rankingvendas r WHERE r.mes=" + this.mes + " AND r.ano=" + this.ano
						+ " AND r.usuario.idusuario=" + this.usuarioLogadoMB.getUsuario().getIdusuario());
		if (listaRanking != null && listaRanking.size() > 0) {
			rankingvendas = listaRanking.get(0);
		} else {
			rankingvendas = new Rankingvendas();
			rankingvendas.setAno(this.ano);
			rankingvendas.setMes(this.mes);
			rankingvendas.setUsuario(this.usuarioLogadoMB.getUsuario());
		}
		if (this.contrato.getParcelaspagas() > 12
				&& this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
			rankingvendas.setComissaovenda(rankingvendas.getValorvenda()
					+ this.contrato.getValorquitar() * this.coeficiente.getComissaoloja() / 100.0F);
		} else {
			rankingvendas.setComissaovenda(rankingvendas.getValorvenda()
					+ this.contrato.getValoroperacao() * this.coeficiente.getComissaocorretor() / 100.0F);
		}
		rankingVendasFacade.salvar(rankingvendas);
	}

	public void gerarRankingAnual() {
		Rankingvendasanual rankingvendas;
		RankingVendasAnualFacade rankingVendasFacade = new RankingVendasAnualFacade();
		this.ano = Formatacao.getAnoData(new Date());
		List<Rankingvendasanual> listaRanking = rankingVendasFacade
				.lista("Select r From Rankingvendasanual r WHERE  r.ano=" + this.ano + " AND r.usuario.idusuario="
						+ this.usuarioLogadoMB.getUsuario().getIdusuario());
		if (listaRanking != null && listaRanking.size() > 0) {
			rankingvendas = listaRanking.get(0);
		} else {
			rankingvendas = new Rankingvendasanual();
			rankingvendas.setAno(this.ano);
			rankingvendas.setUsuario(this.usuarioLogadoMB.getUsuario());
		}
		if (this.contrato.getParcelaspagas() > 12
				&& this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
			rankingvendas.setValorvenda(rankingvendas.getValorvenda()
					+ this.contrato.getValorquitar() * this.coeficiente.getComissaoloja() / 100.0F);
		} else {
			rankingvendas.setValorvenda(rankingvendas.getValorvenda()
					+ this.contrato.getValoroperacao() * this.coeficiente.getComissaocorretor() / 100.0F);
		}
		rankingVendasFacade.salvar(rankingvendas);
	}

	public void gerarComissao(Contrato contrato) {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		Historicocomissao historicocomissao = new Historicocomissao();
		historicocomissao.setDatalancamento(new Date());
		historicocomissao.setContrato(contrato);
		historicocomissao.setUsuario(this.usuario);
		historicocomissao.setTipo("PENDENTE");
		int mes = Formatacao.getMesData(new Date()) + 1;
		int ano = Formatacao.getAnoData(new Date());
		historicocomissao.setAno(ano);
		historicocomissao.setMes(mes);
		if (contrato.getParcelaspagas() > 12 && contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
			historicocomissao.setCmdbruta(0.0F);
			historicocomissao.setCmsliq(0.0F);
			historicocomissao.setProdliq(contrato.getValorquitar());
			historicocomissao.setComissaototal(0.0F);
		} else if (contrato.getTipooperacao().getIdtipooperacao().intValue() != 1) {
			historicocomissao.setCmdbruta(0.0F);
			historicocomissao.setCmsliq(0.0F);
			historicocomissao.setProdliq(contrato.getValorcliente());
			historicocomissao.setComissaototal(0.0F);
		} else {
			historicocomissao.setCmdbruta(0.0F);
			historicocomissao.setCmsliq(0.0F);
			historicocomissao.setProdliq(0.0F);
			historicocomissao.setComissaototal(0.0F);
		}
		historicoComissaoFacade.salvar(historicocomissao);
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
		if (this.voltarTela != null && this.voltarTela.length() > 0)
			return this.voltarTela;
		return "consContrato";
	}

	public String gerarCodigo(int nContratos) {
		String codigo = "DF " + Formatacao.ConvercaoDataPadrao(new Date()) + " - ";
		ContratoFacade contratoFacade = new ContratoFacade();
		List<Contrato> lisContratos = contratoFacade.lista(
				"Select c From Contrato c Where c.datacadastro='" + Formatacao.ConvercaoDataSql(new Date()) + "'");
		if (lisContratos == null)
			lisContratos = new ArrayList<>();
		codigo = String.valueOf(codigo) + (lisContratos.size() + 1 + nContratos);
		return codigo;
	}

	public void fileUploadListener(FileUploadEvent e) {
		this.file = e.getFile();
		salvarArquivoFTP();
		if (this.arquivoEnviado) {
			String nome = e.getFile().getFileName();
			try {
				nome = new String(nome.getBytes(Charset.defaultCharset()), "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			if (this.listaNomeArquivo == null)
				this.listaNomeArquivo = new ArrayList<>();
			this.listaNomeArquivo.add(String.valueOf(this.cpf) + "_" + nome);
			salvarUpload();
		}
	}

	public boolean salvarArquivoFTP() {
		String msg = "";
		Ftp ftp = new Ftp("sistemasdelta.com.br", "deltafinanceiraftp@sistemadeltafinanceira.acessotemporario.net",
				"780920**Delta");
		try {
			if (!ftp.conectar()) {
				Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
				return false;
			}
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, (String) null, ex);
			Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
		}
		try {
			String nomeArquivoFTP = this.cpf;
			this.arquivoEnviado = ftp.enviarArquivoDOCS(this.file, nomeArquivoFTP, "");
			if (this.arquivoEnviado) {
				msg = "Arquivo: " + nomeArquivoFTP + " enviado com sucesso";
				salvarUpload();
			} else {
				msg = " Erro no nome do arquivo";
			}
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(msg, ""));
			ftp.desconectar();
			return true;
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, (String) null, ex);
			Mensagem.lancarMensagemInfo("Erro Salvar Arquivo", "" + ex);
			try {
				ftp.desconectar();
			} catch (IOException iOException) {
				Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, (String) null, iOException);
				Mensagem.lancarMensagemInfo("Erro", "desconectar FTP");
			}
			return false;
		}
	}

	public void gerarListaTipoArquivo() {
		TipoArquivoDao tipoArquivoDao = new TipoArquivoDao();
		this.listaTipoArquivo = tipoArquivoDao.listar("Select t From Tipoarquivo t order by t.descricao");
		if (this.listaTipoArquivo == null)
			this.listaTipoArquivo = new ArrayList<>();
	}

	public void salvarUpload() {
		if (this.arquivoEnviado)
			try {
				if (this.tipoarquivo != null && this.tipoarquivo.getIdtipoarquivo() != null) {
					this.contratoarquivo = new Contratoarquivo();
					this.contratoarquivo.setDataupload(new Date());
					this.contratoarquivo.setNovoupload(true);
					this.contratoarquivo.setNomearquivo(String.valueOf(this.cpf) + "_"
							+ new String(this.file.getFileName().trim().getBytes("ISO-8859-1"), "UTF-8"));
					this.contratoarquivo.setTipoarquivo(this.tipoarquivo);
					if (this.listaContratoArquivo == null)
						this.listaContratoArquivo = new ArrayList<>();
					this.listaContratoArquivo.add(this.contratoarquivo);
					this.contratoarquivo = new Contratoarquivo();
					this.listaNomeArquivo = new ArrayList<>();
					this.file = null;
					this.arquivoEnviado = false;
					Mensagem.lancarMensagemInfo("Salvo com sucesso", "");
				} else {
					Mensagem.lancarMensagemFatal("Erro", "Favor escolher o tipo de arquivo");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	}

	public void gerarListaContratoAquivo() {
		ContratoArquivoFacade contratoArquivoFacade = new ContratoArquivoFacade();
		if (this.contrato != null)
			this.listaContratoArquivo = contratoArquivoFacade.lista(
					"Select c From Contratoarquivo c WHERE c.contrato.idcontrato=" + this.contrato.getIdcontrato());
		if (this.listaContratoArquivo == null)
			this.listaContratoArquivo = new ArrayList<>();
	}

	public void excluirArquivo(String ilinha) {
		int linha = Integer.parseInt(ilinha);
		if (listaContratoArquivo.get(linha).getIdcontratoarquivo() != null) {
			ContratoArquivoFacade contratoArquivoFacade = new ContratoArquivoFacade();
			contratoArquivoFacade.excluir(
					((Contratoarquivo) this.listaContratoArquivo.get(linha)).getIdcontratoarquivo().intValue());
		}
		if (linha >= 0)
			this.listaContratoArquivo.remove(linha);
	}

	public void baixarArquivoFTP(Contratoarquivo contratoarquivo) {
		Ftp ftp = new Ftp("sistemasdelta.com.br", "deltafinanceiraftp@sistemadeltafinanceira.acessotemporario.net",
				"780920**Delta");
		try {
			if (!ftp.conectar())
				Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, (String) null, ex);
			Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
		}
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			InputStream is = ftp.receberArquivo(contratoarquivo.getNomearquivo(), contratoarquivo.getNomearquivo(), "");
			ExternalContext externalContext = context.getExternalContext();
			externalContext.responseReset();
			externalContext.setResponseHeader("Content-Disposition",
					"attachment;filename=" + contratoarquivo.getNomearquivo());
			OutputStream outputStream = externalContext.getResponseOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0)
				outputStream.write(buffer, 0, length);
			is.close();
			context.responseComplete();
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, (String) null, ex);
			Mensagem.lancarMensagemInfo("Erro Salvar Arquivo", "" + ex);
		}
		try {
			ftp.desconectar();
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, (String) null, ex);
			Mensagem.lancarMensagemInfo("Erro", "desconectar FTP");
		}
	}

	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		this.listaUsuario = usuarioFacade.listar("Select u From Usuario u Where u.treinamento=false order by u.nome");
		if (this.listaUsuario == null)
			this.listaUsuario = new ArrayList<>();
	}

	public void gerarListaPromotora() {
		PromotoraFacade promotoraFacade = new PromotoraFacade();
		this.listaPromotora = promotoraFacade.lista("SELECT p From Promotora p WHERE p.idpromotora>1");
		if (this.listaPromotora == null)
			this.listaPromotora = new ArrayList<>();
	}

	public boolean gerarListaValores() {
		CoeficienteFacade coeficienteFacade = new CoeficienteFacade();
		List<Coeficiente> listaRegrasValores = coeficienteFacade.lista(
				"Select v From Coeficiente v WHERE v.orgaoBanco.idorgaobanco=" + this.orgaoBanco.getIdorgaobanco()
						+ " AND v.tipooperacao.idtipooperacao=" + this.contrato.getTipooperacao().getIdtipooperacao());
		if (listaRegrasValores == null || listaRegrasValores.isEmpty())
			return false;
		return true;
	}

	public void salvarHistorico(Historicousuario historicousuario, Contrato contrato) {
		HistoricoUsuarioFacade historicoUsuarioFacade = new HistoricoUsuarioFacade();
		historicousuario.setDatacadastro(new Date());
		historicousuario.setTitulo("Novo");
		historicousuario.setIcone("novo.png");
		historicousuario.setHora(Formatacao.foramtarHoraString());
		historicousuario.setUsuario(this.usuario);
		String convenio = "";
		if (contrato.isOperacaoinss()) {
			convenio = "INSS";
		} else {
			convenio = "SIAPE";
		}
		historicousuario
				.setDescricao("Nova Emissão de contrato; Tipo do contrato: " + contrato.getTipooperacao().getDescricao()
						+ " - " + convenio + ", Cliente: " + contrato.getCliente().getNome());
		historicousuario.setIdcontrato(contrato.getIdcontrato().intValue());
		historicoUsuarioFacade.salvar(historicousuario);
	}

	public void adicionarOperacao() {
		if (this.listaContratoUnificacao == null)
			this.listaContratoUnificacao = new ArrayList<>();
		this.listaContratoUnificacao.add(this.contratounificacao);
		this.contratounificacao = new Contratounificacao();
	}

	public void excluirOperacao(String ilinha) {
		int linha = Integer.parseInt(ilinha);
		if (((Contratounificacao) this.listaContratoUnificacao.get(linha)).getIdcontratounificacao() != null) {
			ContratoUnificacaoFacade contratoUnificacaoFacade = new ContratoUnificacaoFacade();
			contratoUnificacaoFacade.excluir(((Contratounificacao) this.listaContratoUnificacao.get(linha))
					.getIdcontratounificacao().intValue());
		}
		this.listaContratoUnificacao.remove(linha);
	}

	public void buscarUnificacoes() {
		if (this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 1
				|| this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 7
				|| this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 8) {
			ContratoUnificacaoFacade contratoUnificacaoFacade = new ContratoUnificacaoFacade();
			this.listaContratoUnificacao = contratoUnificacaoFacade.lista(
					"Select c From Contratounificacao c Where c.contrato.idcontrato=" + this.contrato.getIdcontrato());
			if (this.listaContratoUnificacao == null)
				this.listaContratoUnificacao = new ArrayList<>();
		}
	}

	public void salvarUnificacao() {
		if (this.listaContratoUnificacao == null)
			this.listaContratoUnificacao = new ArrayList<>();
		ContratoUnificacaoFacade contratoUnificacaoFacade = new ContratoUnificacaoFacade();
		for (int i = 0; i < this.listaContratoUnificacao.size(); i++) {
			Contrato contratoMais = this.contrato;
			if (contratoMais.getIdcontrato() == null)
				contratoMais.setCodigocontrato(gerarCodigo(i));
			ContratoFacade contratoFacade = new ContratoFacade();
			contratoMais.setParcela(((Contratounificacao) this.listaContratoUnificacao.get(i)).getParcela());
			contratoMais.setMargemutilizada(listaContratoUnificacao.get(i).getParcela());
			contratoMais = contratoFacade.salvar(contratoMais);
			if (this.novo) {
				if (usuarioLogadoMB.getUsuario().getIdusuario() != 1 && !usuarioLogadoMB.getUsuario().isTreinamento()) {
					gerarNotificacao(contratoMais);
				}
				gerarComissao(contratoMais);
				Historicousuario historicousuario = new Historicousuario();
				salvarHistorico(historicousuario, contratoMais);
			}
			verificarUpload(contratoMais);
			((Contratounificacao) this.listaContratoUnificacao.get(i)).setContrato(contratoMais);
			contratoUnificacaoFacade.salvar(this.listaContratoUnificacao.get(i));
		}
	}

	public void gerarListaCampanhasCorretor() {
		CampanhasUsuarioFacade campanhasUsuarioFacade = new CampanhasUsuarioFacade();
		String dataHoje = Formatacao.ConvercaoDataNfe(new Date());
		String sql = "Select c From Campanhasusuario c Where c.campanhas.datainicial>='" + dataHoje
				+ "' and c.campanhas.datafinal<='" + dataHoje + "'";
		sql = sql + " and c.usuario.idusuario=" + contrato.getUsuario().getIdusuario();

		List<Campanhasusuario> listaCampanhasUsuario = campanhasUsuarioFacade.lista(sql);

		if (listaCampanhasUsuario == null) {
			listaCampanhasUsuario = new ArrayList<Campanhasusuario>();
		}
		CampanhasContratoFacade campanhasContratoFacade = new CampanhasContratoFacade();
		for (int i = 0; i < listaCampanhasUsuario.size(); i++) {
			Campanhascontrato campanhascontrato = new Campanhascontrato();
			campanhascontrato.setCampanhas(listaCampanhasUsuario.get(i).getCampanhas());
			campanhascontrato.setContrato(contrato);
			campanhasContratoFacade.salvar(campanhascontrato);
		}
	}
}
