package br.com.gestapromotora.managebean.contrato;

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

import br.com.gestapromotora.bean.ControladorCEPBean;
import br.com.gestapromotora.bean.EnderecoBean;
import br.com.gestapromotora.dao.NotificacaoDao;
import br.com.gestapromotora.dao.TipoArquivoDao;
import br.com.gestapromotora.dao.UsuarioDao;
import br.com.gestapromotora.facade.BancoFacade;
import br.com.gestapromotora.facade.ClienteFacade;
import br.com.gestapromotora.facade.ContratoArquivoFacade;
import br.com.gestapromotora.facade.ContratoFacade;
import br.com.gestapromotora.facade.DadosBancarioFacade;
import br.com.gestapromotora.facade.HistoricoComissaoFacade;
import br.com.gestapromotora.facade.MetaFaturamentoMensalFacade;
import br.com.gestapromotora.facade.OrgaoBancoFacade;
import br.com.gestapromotora.facade.PromotoraFacade;
import br.com.gestapromotora.facade.RankingVendasAnualFacade;
import br.com.gestapromotora.facade.RankingVendasFacade;
import br.com.gestapromotora.facade.TipoOperacaoFacade;
import br.com.gestapromotora.facade.UsuarioFacade;
import br.com.gestapromotora.model.Banco;
import br.com.gestapromotora.model.Cliente;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.Contratoarquivo;
import br.com.gestapromotora.model.Dadosbancario;
import br.com.gestapromotora.model.Historicocomissao;
import br.com.gestapromotora.model.Metafaturamentomensal;
import br.com.gestapromotora.model.Notificacao;
import br.com.gestapromotora.model.OrgaoBanco;
import br.com.gestapromotora.model.Promotora;
import br.com.gestapromotora.model.Rankingvendas;
import br.com.gestapromotora.model.Rankingvendasanual;
import br.com.gestapromotora.model.Regrascoeficiente;
import br.com.gestapromotora.model.Tipoarquivo;
import br.com.gestapromotora.model.Tipooperacao;
import br.com.gestapromotora.model.Usuario;
import br.com.gestapromotora.model.Valorescoeficiente;
import br.com.gestapromotora.util.Formatacao;
import br.com.gestapromotora.util.Ftp;
import br.com.gestapromotora.util.Mensagem;
import br.com.gestapromotora.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class CadContratoMB implements Serializable {

	/**
	 * 
	 */
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
	private Regrascoeficiente regrascoeficiente;
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

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		contrato = (Contrato) session.getAttribute("contrato");
		orgaoBanco = (OrgaoBanco) session.getAttribute("orgaobanco");
		banco = (Banco) session.getAttribute("banco");
		regrascoeficiente = (Regrascoeficiente) session.getAttribute("regrascoeficiente");
		session.removeAttribute("orgaobanco");
		session.removeAttribute("contrato");
		session.removeAttribute("regrascoeficiente");
		cliente = contrato.getCliente();
		if (orgaoBanco != null) {
			banco = orgaoBanco.getBanco();
		}
		gerarListaBanco();
		gerarListaTipoArquivo();
		gerarListaOrgao();
		gerarListaUsuario();
		gerarListaPromotora();
		if (contrato == null) {
			contrato = new Contrato();
			mes = Formatacao.getMesData(new Date()) + 1;
			ano = Formatacao.getAnoData(new Date());
			usuario = usuarioLogadoMB.getUsuario();
		} else if (contrato.getIdcontrato() != null) {
			novo = false;
			gerarListaContratoAquivo();
			usuario = contrato.getUsuario();
			promotora = contrato.getPromotora();
		}else {
			usuario = usuarioLogadoMB.getUsuario();
		}
		if (usuarioLogadoMB.getUsuario().isAcessogeral()) {
			habilitarUsuario = false;
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

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Regrascoeficiente getRegrascoeficiente() {
		return regrascoeficiente;
	}

	public void setRegrascoeficiente(Regrascoeficiente regrascoeficiente) {
		this.regrascoeficiente = regrascoeficiente;
	}

	public boolean isNovo() {
		return novo;
	}

	public void setNovo(boolean novo) {
		this.novo = novo;
	}

	public List<Contratoarquivo> getListaContratoArquivo() {
		return listaContratoArquivo;
	}

	public void setListaContratoArquivo(List<Contratoarquivo> listaContratoArquivo) {
		this.listaContratoArquivo = listaContratoArquivo;
	}

	public Tipoarquivo getTipoarquivo() {
		return tipoarquivo;
	}

	public void setTipoarquivo(Tipoarquivo tipoarquivo) {
		this.tipoarquivo = tipoarquivo;
	}

	public StreamedContent getFileDownload() {
		return fileDownload;
	}

	public void setFileDownload(StreamedContent fileDownload) {
		this.fileDownload = fileDownload;
	}

	public List<String> getListaNomeArquivo() {
		return listaNomeArquivo;
	}

	public void setListaNomeArquivo(List<String> listaNomeArquivo) {
		this.listaNomeArquivo = listaNomeArquivo;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public boolean isArquivoEnviado() {
		return arquivoEnviado;
	}

	public void setArquivoEnviado(boolean arquivoEnviado) {
		this.arquivoEnviado = arquivoEnviado;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public List<Tipoarquivo> getListaTipoArquivo() {
		return listaTipoArquivo;
	}

	public void setListaTipoArquivo(List<Tipoarquivo> listaTipoArquivo) {
		this.listaTipoArquivo = listaTipoArquivo;
	}

	public Contratoarquivo getContratoarquivo() {
		return contratoarquivo;
	}

	public void setContratoarquivo(Contratoarquivo contratoarquivo) {
		this.contratoarquivo = contratoarquivo;
	}

	public List<Banco> getListaBancoInicio() {
		return listaBancoInicio;
	}

	public void setListaBancoInicio(List<Banco> listaBancoInicio) {
		this.listaBancoInicio = listaBancoInicio;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isHabilitarUsuario() {
		return habilitarUsuario;
	}

	public void setHabilitarUsuario(boolean habilitarUsuario) {
		this.habilitarUsuario = habilitarUsuario;
	}

	public Promotora getPromotora() {
		return promotora;
	}

	public void setPromotora(Promotora promotora) {
		this.promotora = promotora;
	}

	public List<Promotora> getListaPromotora() {
		return listaPromotora;
	}

	public void setListaPromotora(List<Promotora> listaPromotora) {
		this.listaPromotora = listaPromotora;
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
		contrato.setUsuario(usuario);
		ContratoFacade contratoFacade = new ContratoFacade();
		if (contrato == null || contrato.getIdcontrato() == null) {
			contrato.setCodigocontrato(gerarCodigo());
			gerarMetaFaturamento();
			contrato.setIdregracoeficiente(regrascoeficiente.getIdregrascoeficiente());
		}
		if (promotora == null || promotora.getIdpromotora() == null) {
			PromotoraFacade promotoraFacade = new PromotoraFacade();
			promotora = promotoraFacade.consultar(1);
		}
		contrato.setPromotora(promotora);
		contrato = contratoFacade.salvar(contrato);
		verificarUpload();
		if (novo) {
			gerarNotificacao();
			gerarComissao();
			gerarRankingMensal();
			gerarRankingAnual();
		}
		return "consContrato";
	}
	
	
	public void verificarUpload() {
		if (listaContratoArquivo == null) {
			listaContratoArquivo = new ArrayList<Contratoarquivo>();
		}
		for (int i = 0; i < listaContratoArquivo.size(); i++) {
			ContratoArquivoFacade contratoArquivoFacade = new ContratoArquivoFacade();
			listaContratoArquivo.get(i).setContrato(contrato);
			contratoArquivoFacade.salvar(listaContratoArquivo.get(i));
		}
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
				notificacao.setIdcontrato(contrato.getIdcontrato());
				notificacao.setTitulo("Novo Contrato: " + contrato.getCodigocontrato());
				notificacao.setDescricao(
						contrato.getTipooperacao().getDescricao() + " emitido pelo corretor(a) " + usuarioLogadoMB.getUsuario().getNome());
				notificacaoDao.salvar(notificacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void gerarMetaFaturamento() {
		MetaFaturamentoMensalFacade mensalFacade = new MetaFaturamentoMensalFacade();
		mes = Formatacao.getMesData(new Date()) + 1;
		ano = Formatacao.getAnoData(new Date());
		String tipo;
		String sql = "Select m From Metafaturamentomensal m WHERE m.mes=" + mes + " AND m.ano=" + ano;
		if (contrato.getTipooperacao().getDescricao().equalsIgnoreCase("PORTABILIDADE")) {
			tipo = "PORTABILIDADE";
			sql = sql + " and m.tipo='PORTABILIDADE'";
		} else {
			tipo = "DEMAIS OPERAÇÕES";
			sql = sql + " and m.tipo='DEMAIS OPERAÇÕES'";
		}
		List<Metafaturamentomensal> lista = mensalFacade.lista(sql);
		Metafaturamentomensal metafaturamentomensal;
		if (lista == null || lista.isEmpty()) {
			metafaturamentomensal = new Metafaturamentomensal();
			metafaturamentomensal.setAno(ano);
			metafaturamentomensal.setMes(mes);
			metafaturamentomensal.setPercentualmeta(0.0f);
			metafaturamentomensal.setCormeta("#f3291f");
			metafaturamentomensal.setTipo(tipo);
		} else {
			metafaturamentomensal = lista.get(0);
		}
		if (contrato.getParcelaspagas() > 12 && contrato.getTipooperacao().getIdtipooperacao() == 1) {
			metafaturamentomensal.setValoratual(metafaturamentomensal.getValoratual()
					+ contrato.getValorquitar());
		} else {
			metafaturamentomensal.setValoratual(metafaturamentomensal.getValoratual()
					+ contrato.getValoroperacao());
		}
		mensalFacade.salvar(metafaturamentomensal);
	}

	public void gerarRankingMensal() {
		RankingVendasFacade rankingVendasFacade = new RankingVendasFacade();
		Rankingvendas rankingvendas;
		mes = Formatacao.getMesData(new Date()) + 1;
		ano = Formatacao.getAnoData(new Date());
		List<Rankingvendas> listaRanking = rankingVendasFacade.lista("Select r From Rankingvendas r WHERE r.mes=" + mes
				+ " AND r.ano=" + ano + " AND r.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario());
		if (listaRanking != null && listaRanking.size() > 0) {
			rankingvendas = listaRanking.get(0);
		} else {
			rankingvendas = new Rankingvendas();
			rankingvendas.setAno(ano);
			rankingvendas.setMes(mes);
			rankingvendas.setUsuario(usuarioLogadoMB.getUsuario());
		}
		if (contrato.getParcelaspagas() > 12 && contrato.getTipooperacao().getIdtipooperacao() == 1) {
			rankingvendas.setValorvenda(rankingvendas.getValorvenda()
					+ (contrato.getValorquitar() * (regrascoeficiente.getFlatrecebidaregra() / 100)));
		} else {
			rankingvendas.setValorvenda(rankingvendas.getValorvenda()
					+ (contrato.getValoroperacao() * (regrascoeficiente.getFlatrecebidaregra() / 100)));
		}
		rankingVendasFacade.salvar(rankingvendas);
	}
	
	
	public void gerarRankingAnual() {
		RankingVendasAnualFacade rankingVendasFacade = new RankingVendasAnualFacade();
		Rankingvendasanual rankingvendas;
		ano = Formatacao.getAnoData(new Date());
		List<Rankingvendasanual> listaRanking = rankingVendasFacade.lista("Select r From Rankingvendasanual r WHERE "
				+ " r.ano=" + ano + " AND r.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario());
		if (listaRanking != null && listaRanking.size() > 0) {
			rankingvendas = listaRanking.get(0);
		} else {
			rankingvendas = new Rankingvendasanual();
			rankingvendas.setAno(ano);
			rankingvendas.setUsuario(usuarioLogadoMB.getUsuario());
		}
		if (contrato.getParcelaspagas() > 12 && contrato.getTipooperacao().getIdtipooperacao() == 1) {
			rankingvendas.setValorvenda(rankingvendas.getValorvenda()
					+ (contrato.getValorquitar() * (regrascoeficiente.getFlatrecebidaregra() / 100)));
		} else {
			rankingvendas.setValorvenda(rankingvendas.getValorvenda()
					+ (contrato.getValoroperacao() * (regrascoeficiente.getFlatrecebidaregra() / 100)));
		}
		rankingVendasFacade.salvar(rankingvendas);
	}

	public void gerarComissao() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		Historicocomissao historicocomissao = new Historicocomissao();
		historicocomissao.setDatalancamento(new Date());
		historicocomissao.setContrato(contrato);
		historicocomissao.setUsuario(usuarioLogadoMB.getUsuario());
		historicocomissao.setTipo("PENDENTE");
		int mes = Formatacao.getMesData(new Date()) + 1;
		int ano = Formatacao.getAnoData(new Date());
		historicocomissao.setAno(ano);
		historicocomissao.setMes(mes);
		if (contrato.getParcelaspagas() > 12 && contrato.getTipooperacao().getIdtipooperacao() == 1) {
			historicocomissao.setCmdbruta(contrato.getValorquitar() * (regrascoeficiente.getFlatrecebidaregra() / 100));
			historicocomissao.setCmsliq(contrato.getValorquitar() * (regrascoeficiente.getFlatrepassadavista() / 100));
			historicocomissao.setProdliq(contrato.getValorquitar());

		} else if (contrato.getTipooperacao().getIdtipooperacao() != 1) {
			historicocomissao
					.setCmdbruta(contrato.getValoroperacao() * (regrascoeficiente.getFlatrecebidaregra() / 100));
			historicocomissao
					.setCmsliq(contrato.getValoroperacao() * (regrascoeficiente.getFlatrepassadavista() / 100));
			historicocomissao.setProdliq(contrato.getValoroperacao());
		} else {
			historicocomissao.setCmdbruta(0.0f);
			historicocomissao.setCmsliq(0.0f);
			historicocomissao.setProdliq(0.0f);
		}
		historicoComissaoFacade.salvar(historicocomissao);
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
			String diames = "" + Formatacao.getDiaData(cliente.getNascimento()) + (Formatacao.getMesData(cliente.getNascimento()) + 1);
			cliente.setDiames(Integer.parseInt(diames));
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
	
	
	public void fileUploadListener(FileUploadEvent e) {
		this.file = e.getFile();
		salvarArquivoFTP();
		if (arquivoEnviado) {
			String nome = e.getFile().getFileName();
			try {
				nome = new String(nome.getBytes(Charset.defaultCharset()), "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			if (listaNomeArquivo == null) {
				listaNomeArquivo = new ArrayList<String>();
			}
			listaNomeArquivo.add(nome);
			salvarUpload();
		}
	}
	public boolean salvarArquivoFTP() {
		String msg = "";
		Ftp ftp = new Ftp("sistemadeltafinanceira.acessotemporario.net", 
				"deltafinanceiraftp@sistemadeltafinanceira.acessotemporario.net", "780920**Delta");
		try {
			if (!ftp.conectar()) {
				Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
				return false;
			}
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, null, ex);
			Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
		}    
		try {
			String nomeArquivoFTP = "" +  contrato.getIdcontrato();
			arquivoEnviado = ftp.enviarArquivoDOCS(file, nomeArquivoFTP, "");
			if (arquivoEnviado) {
				msg = "Arquivo: " + nomeArquivoFTP + " enviado com sucesso";
				salvarUpload();
			}else{
				msg = " Erro no nome do arquivo";
			}
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(msg, ""));
			ftp.desconectar();
			return true;
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, null, ex);
			Mensagem.lancarMensagemInfo("Erro Salvar Arquivo", "" + ex);
		}
		try {
			ftp.desconectar();
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, null, ex);
			Mensagem.lancarMensagemInfo("Erro", "desconectar FTP");
		}
		return false;
	}
	
	
	
	public void gerarListaTipoArquivo() {
		TipoArquivoDao tipoArquivoDao = new TipoArquivoDao();
		listaTipoArquivo = tipoArquivoDao.listar("Select t From Tipoarquivo t");
		if (listaTipoArquivo == null) {
			listaTipoArquivo = new ArrayList<Tipoarquivo>();
		}
	}
	
	
	
	public void salvarUpload() {
		if (arquivoEnviado) {
			try {
				if (tipoarquivo != null && tipoarquivo.getIdtipoarquivo() != null) {
					contratoarquivo = new Contratoarquivo();
					contratoarquivo.setDataupload(new Date());
					contratoarquivo.setNomearquivo(
							 new String(file.getFileName().trim().getBytes("ISO-8859-1"), "UTF-8"));
					contratoarquivo.setTipoarquivo(tipoarquivo);
					if (listaContratoArquivo == null) {
						listaContratoArquivo = new ArrayList<Contratoarquivo>();
					}
					listaContratoArquivo.add(contratoarquivo);
					contratoarquivo = new Contratoarquivo();
					listaNomeArquivo = new ArrayList<String>();
					file = null;
					arquivoEnviado = false;
					Mensagem.lancarMensagemInfo("Salvo com sucesso", "");
				}else {
					Mensagem.lancarMensagemFatal("Erro", "Favor escolher o tipo de arquivo");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void gerarListaContratoAquivo() {
		ContratoArquivoFacade contratoArquivoFacade = new ContratoArquivoFacade();
		if (contrato != null) {
			listaContratoArquivo = contratoArquivoFacade.lista("Select c From Contratoarquivo c WHERE c.contrato.idcontrato=" 
					+ contrato.getIdcontrato());
		}
		if (listaContratoArquivo == null) {
			listaContratoArquivo = new ArrayList<Contratoarquivo>();
		}
	}
	
	
	public void excluirArquivo(String ilinha) {
		int linha = Integer.parseInt(ilinha);
		ContratoArquivoFacade contratoArquivoFacade = new ContratoArquivoFacade();
		contratoArquivoFacade.excluir(listaContratoArquivo.get(linha).getIdcontratoarquivo());
		if (linha >= 0) {
			listaContratoArquivo.remove(linha);
		}
	}
	
	
	
	public void baixarArquivoFTP(Contratoarquivo contratoarquivo) {
 
		Ftp ftp = new Ftp("sistemadeltafinanceira.acessotemporario.net", 
				"deltafinanceiraftp@sistemadeltafinanceira.acessotemporario.net", "780920**Delta");
		try {
			if (!ftp.conectar()) {
				Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
			}
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, null, ex);
			Mensagem.lancarMensagemInfo("Erro", "conectar FTP");
		}    
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			InputStream is = ftp.receberArquivo(contratoarquivo.getNomearquivo(), contratoarquivo.getNomearquivo(), "");
            ExternalContext externalContext = context.getExternalContext();

            externalContext.responseReset();
            externalContext.setResponseHeader("Content-Disposition", "attachment;filename=" + contratoarquivo.getNomearquivo());
			 OutputStream outputStream = externalContext.getResponseOutputStream();

	            byte[] buffer = new byte[1024];
	            int length;
	            while ((length = is.read(buffer)) > 0) {
	                outputStream.write(buffer, 0, length);
	            }

	            is.close();
	            context.responseComplete();
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, null, ex);
			Mensagem.lancarMensagemInfo("Erro Salvar Arquivo", "" + ex);
		}
		try {
			ftp.desconectar();
		} catch (IOException ex) {
			Logger.getLogger(AnexarArquivoMB.class.getName()).log(Level.SEVERE, null, ex);
			Mensagem.lancarMensagemInfo("Erro", "desconectar FTP");
		}
	}
	
	
	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		listaUsuario = usuarioFacade.listar("Select u From Usuario u order by u.nome");
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}
	
	
	public void gerarListaPromotora() {
		PromotoraFacade promotoraFacade = new PromotoraFacade();
		listaPromotora = promotoraFacade.lista("SELECT p From Promotora p WHERE p.idpromotora>1");
		if (listaPromotora == null) {
			listaPromotora = new ArrayList<Promotora>();
		}
	}
	
	

}
