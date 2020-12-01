package br.com.gestapromotora.managebean.contrato.portabilidade;

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

import br.com.gestapromotora.bean.FiltrosBean;
import br.com.gestapromotora.facade.BancoFacade;
import br.com.gestapromotora.facade.ContratoFacade;
import br.com.gestapromotora.facade.HistoricoComissaoFacade;
import br.com.gestapromotora.facade.RegrasCoeficienteFacade;
import br.com.gestapromotora.facade.SituacaoFacade;
import br.com.gestapromotora.facade.TipoOperacaoFacade;
import br.com.gestapromotora.facade.UsuarioFacade;
import br.com.gestapromotora.model.Banco;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.Historicocomissao;
import br.com.gestapromotora.model.Regrascoeficiente;
import br.com.gestapromotora.model.Situacao;
import br.com.gestapromotora.model.Tipooperacao;
import br.com.gestapromotora.model.Usuario;
import br.com.gestapromotora.util.Formatacao;
import br.com.gestapromotora.util.Mensagem;
import br.com.gestapromotora.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class PortabilidadeMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private List<Contrato> listaContrato;
	private String situacao;
	private List<Usuario> listaUsuario;
	private String nomeCliente;
	private String cpf;
	private Usuario usuario;
	private boolean mudarsituacao = false;
	private boolean incompletos = false;
	private boolean digitadosPagDivida = false;
	private boolean dividaPaga = false;
	private boolean portabilidadeFin = false;
	private boolean refinanciamentoCriado = false;
	private boolean cancelados = false;
	private int nIncompletos;
	private int nDigitados;
	private int nDivida;
	private int nPortabilidade;
	private int nRefinanciamento;
	private int nCancelados;
	private int nAguardandoSolicitacao;
	private int nAguardandoDigitacao;
	private int nPendenciaDocumentacao;
	private int nAguardandooperacional;
	private int nAguardandoCIP;
	private int nProblemasCIP;
	private int nProblemasCIPAguarOper;
	private int nSaldoAguarPagDivida;
	private int nSaldoAguarRespCorretor;
	private int nSaldoAguardandoOperacional;
	private int nIntencaoPortabilidade;
	private int nSemIntencao;
	private int nMargemLiberada;
	private int nProblemasAverbacao;
	private int nAverbacaoAguardandoOperacional;
	private int nPortabilidadeFinalizada;
	private int nMargemInsuficiente;
	private int nPortabilidadeAguardando;
	private int nAguardandoPagamento;
	private int nProblemasRefin;
	private int nProblemasRefinAguardandoOpe;
	private int nPagoCliente;
	private int nRefinaciamentoPortabilidade;
	private int nCanceladoBancoOperacional;
	private int nCanceladoCorretor;
	private int nCancelado;
	private List<FiltrosBean> listaFiltrosBean;
	private List<Contrato> listaContratoPesquisa;
	private int nSituacao;
	private int nTodos;
	private List<Tipooperacao> listaTipoOperacao;
	private Tipooperacao tipooiperacao;
	private List<Banco> listaBanco;
	private Banco banco;
	private List<Situacao> listaSituacao;
	private boolean unicoUsuario;
	
	
	
	
	
	@PostConstruct
	public void init() {
		gerarListaUsuario();
		gerarListaInicial();
		gerarListaBanco();
		if (!usuarioLogadoMB.getUsuario().isAcessogeral()
				&& !usuarioLogadoMB.getUsuario().isSupervisao()) {
			unicoUsuario = true;
			usuario = usuarioLogadoMB.getUsuario();
		}
	}



	public List<Contrato> getListaContrato() {
		return listaContrato;
	}



	public void setListaContrato(List<Contrato> listaContrato) {
		this.listaContrato = listaContrato;
	}
	
	
	public String getSituacao() {
		return situacao;
	}



	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}



	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}



	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
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



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public boolean isMudarsituacao() {
		return mudarsituacao;
	}



	public void setMudarsituacao(boolean mudarsituacao) {
		this.mudarsituacao = mudarsituacao;
	}



	public boolean isIncompletos() {
		return incompletos;
	}



	public void setIncompletos(boolean incompletos) {
		this.incompletos = incompletos;
	}



	public boolean isDigitadosPagDivida() {
		return digitadosPagDivida;
	}



	public void setDigitadosPagDivida(boolean digitadosPagDivida) {
		this.digitadosPagDivida = digitadosPagDivida;
	}



	public boolean isDividaPaga() {
		return dividaPaga;
	}



	public void setDividaPaga(boolean dividaPaga) {
		this.dividaPaga = dividaPaga;
	}



	public boolean isPortabilidadeFin() {
		return portabilidadeFin;
	}



	public void setPortabilidadeFin(boolean portabilidadeFin) {
		this.portabilidadeFin = portabilidadeFin;
	}



	public boolean isRefinanciamentoCriado() {
		return refinanciamentoCriado;
	}



	public void setRefinanciamentoCriado(boolean refinanciamentoCriado) {
		this.refinanciamentoCriado = refinanciamentoCriado;
	}



	public boolean isCancelados() {
		return cancelados;
	}



	public void setCancelados(boolean cancelados) {
		this.cancelados = cancelados;
	}



	public int getnIncompletos() {
		return nIncompletos;
	}



	public void setnIncompletos(int nIncompletos) {
		this.nIncompletos = nIncompletos;
	}



	public int getnDigitados() {
		return nDigitados;
	}



	public void setnDigitados(int nDigitados) {
		this.nDigitados = nDigitados;
	}



	public int getnDivida() {
		return nDivida;
	}



	public void setnDivida(int nDivida) {
		this.nDivida = nDivida;
	}



	public int getnPortabilidade() {
		return nPortabilidade;
	}



	public void setnPortabilidade(int nPortabilidade) {
		this.nPortabilidade = nPortabilidade;
	}



	public int getnRefinanciamento() {
		return nRefinanciamento;
	}



	public void setnRefinanciamento(int nRefinanciamento) {
		this.nRefinanciamento = nRefinanciamento;
	}



	public int getnCancelados() {
		return nCancelados;
	}



	public void setnCancelados(int nCancelados) {
		this.nCancelados = nCancelados;
	}



	public int getnAguardandoSolicitacao() {
		return nAguardandoSolicitacao;
	}



	public void setnAguardandoSolicitacao(int nAguardandoSolicitacao) {
		this.nAguardandoSolicitacao = nAguardandoSolicitacao;
	}



	public int getnAguardandoDigitacao() {
		return nAguardandoDigitacao;
	}



	public void setnAguardandoDigitacao(int nAguardandoDigitacao) {
		this.nAguardandoDigitacao = nAguardandoDigitacao;
	}



	public int getnPendenciaDocumentacao() {
		return nPendenciaDocumentacao;
	}



	public void setnPendenciaDocumentacao(int nPendenciaDocumentacao) {
		this.nPendenciaDocumentacao = nPendenciaDocumentacao;
	}



	public int getnAguardandooperacional() {
		return nAguardandooperacional;
	}



	public void setnAguardandooperacional(int nAguardandooperacional) {
		this.nAguardandooperacional = nAguardandooperacional;
	}



	public int getnAguardandoCIP() {
		return nAguardandoCIP;
	}



	public void setnAguardandoCIP(int nAguardandoCIP) {
		this.nAguardandoCIP = nAguardandoCIP;
	}



	public int getnProblemasCIP() {
		return nProblemasCIP;
	}



	public void setnProblemasCIP(int nProblemasCIP) {
		this.nProblemasCIP = nProblemasCIP;
	}



	public int getnProblemasCIPAguarOper() {
		return nProblemasCIPAguarOper;
	}



	public void setnProblemasCIPAguarOper(int nProblemasCIPAguarOper) {
		this.nProblemasCIPAguarOper = nProblemasCIPAguarOper;
	}



	public int getnSaldoAguarPagDivida() {
		return nSaldoAguarPagDivida;
	}



	public void setnSaldoAguarPagDivida(int nSaldoAguarPagDivida) {
		this.nSaldoAguarPagDivida = nSaldoAguarPagDivida;
	}



	public List<FiltrosBean> getListaFiltrosBean() {
		return listaFiltrosBean;
	}



	public void setListaFiltrosBean(List<FiltrosBean> listaFiltrosBean) {
		this.listaFiltrosBean = listaFiltrosBean;
	}



	public List<Contrato> getListaContratoPesquisa() {
		return listaContratoPesquisa;
	}



	public void setListaContratoPesquisa(List<Contrato> listaContratoPesquisa) {
		this.listaContratoPesquisa = listaContratoPesquisa;
	}



	public int getnSaldoAguarRespCorretor() {
		return nSaldoAguarRespCorretor;
	}



	public void setnSaldoAguarRespCorretor(int nSaldoAguarRespCorretor) {
		this.nSaldoAguarRespCorretor = nSaldoAguarRespCorretor;
	}



	public int getnSaldoAguardandoOperacional() {
		return nSaldoAguardandoOperacional;
	}



	public void setnSaldoAguardandoOperacional(int nSaldoAguardandoOperacional) {
		this.nSaldoAguardandoOperacional = nSaldoAguardandoOperacional;
	}



	public int getnIntencaoPortabilidade() {
		return nIntencaoPortabilidade;
	}



	public void setnIntencaoPortabilidade(int nIntencaoPortabilidade) {
		this.nIntencaoPortabilidade = nIntencaoPortabilidade;
	}



	public int getnSemIntencao() {
		return nSemIntencao;
	}



	public void setnSemIntencao(int nSemIntencao) {
		this.nSemIntencao = nSemIntencao;
	}



	public int getnMargemLiberada() {
		return nMargemLiberada;
	}



	public void setnMargemLiberada(int nMargemLiberada) {
		this.nMargemLiberada = nMargemLiberada;
	}



	public int getnProblemasAverbacao() {
		return nProblemasAverbacao;
	}



	public void setnProblemasAverbacao(int nProblemasAverbacao) {
		this.nProblemasAverbacao = nProblemasAverbacao;
	}



	public int getnAverbacaoAguardandoOperacional() {
		return nAverbacaoAguardandoOperacional;
	}



	public void setnAverbacaoAguardandoOperacional(int nAverbacaoAguardandoOperacional) {
		this.nAverbacaoAguardandoOperacional = nAverbacaoAguardandoOperacional;
	}



	public int getnPortabilidadeFinalizada() {
		return nPortabilidadeFinalizada;
	}



	public void setnPortabilidadeFinalizada(int nPortabilidadeFinalizada) {
		this.nPortabilidadeFinalizada = nPortabilidadeFinalizada;
	}



	public int getnMargemInsuficiente() {
		return nMargemInsuficiente;
	}



	public void setnMargemInsuficiente(int nMargemInsuficiente) {
		this.nMargemInsuficiente = nMargemInsuficiente;
	}



	public int getnPortabilidadeAguardando() {
		return nPortabilidadeAguardando;
	}



	public void setnPortabilidadeAguardando(int nPortabilidadeAguardando) {
		this.nPortabilidadeAguardando = nPortabilidadeAguardando;
	}



	public int getnAguardandoPagamento() {
		return nAguardandoPagamento;
	}



	public void setnAguardandoPagamento(int nAguardandoPagamento) {
		this.nAguardandoPagamento = nAguardandoPagamento;
	}



	public int getnProblemasRefin() {
		return nProblemasRefin;
	}



	public void setnProblemasRefin(int nProblemasRefin) {
		this.nProblemasRefin = nProblemasRefin;
	}



	public int getnProblemasRefinAguardandoOpe() {
		return nProblemasRefinAguardandoOpe;
	}



	public void setnProblemasRefinAguardandoOpe(int nProblemasRefinAguardandoOpe) {
		this.nProblemasRefinAguardandoOpe = nProblemasRefinAguardandoOpe;
	}



	public int getnPagoCliente() {
		return nPagoCliente;
	}



	public void setnPagoCliente(int nPagoCliente) {
		this.nPagoCliente = nPagoCliente;
	}



	public int getnRefinaciamentoPortabilidade() {
		return nRefinaciamentoPortabilidade;
	}



	public void setnRefinaciamentoPortabilidade(int nRefinaciamentoPortabilidade) {
		this.nRefinaciamentoPortabilidade = nRefinaciamentoPortabilidade;
	}



	public int getnCanceladoBancoOperacional() {
		return nCanceladoBancoOperacional;
	}



	public void setnCanceladoBancoOperacional(int nCanceladoBancoOperacional) {
		this.nCanceladoBancoOperacional = nCanceladoBancoOperacional;
	}



	public int getnCanceladoCorretor() {
		return nCanceladoCorretor;
	}



	public void setnCanceladoCorretor(int nCanceladoCorretor) {
		this.nCanceladoCorretor = nCanceladoCorretor;
	}



	public int getnCancelado() {
		return nCancelado;
	}



	public void setnCancelado(int nCancelado) {
		this.nCancelado = nCancelado;
	}



	public int getnSituacao() {
		return nSituacao;
	}



	public void setnSituacao(int nSituacao) {
		this.nSituacao = nSituacao;
	}



	public int getnTodos() {
		return nTodos;
	}



	public void setnTodos(int nTodos) {
		this.nTodos = nTodos;
	}



	public UsuarioLogadoMB getUsuarioLogadoMB() {
		return usuarioLogadoMB;
	}



	public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
		this.usuarioLogadoMB = usuarioLogadoMB;
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



	public List<Situacao> getListaSituacao() {
		return listaSituacao;
	}



	public void setListaSituacao(List<Situacao> listaSituacao) {
		this.listaSituacao = listaSituacao;
	}



	public boolean isUnicoUsuario() {
		return unicoUsuario;
	}



	public void setUnicoUsuario(boolean unicoUsuario) {
		this.unicoUsuario = unicoUsuario;
	}



	public void gerarListaPortabilidade(int situacao) {
		ContratoFacade contratoFacade = new ContratoFacade();
		String sql = "Select c From Contrato c WHERE c.tipooperacao.descricao like "
				+ "'%Portabilidade%'";
		if (situacao > 0) {
			sql = sql + " and c.situacao.idsituacao ="+ situacao;
		}
		if (!usuarioLogadoMB.getUsuario().isAcessogeral() 
				&& !usuarioLogadoMB.getUsuario().getTipocolaborador().getAcessocolaborador().isAcessooperacional()) {
			sql = sql + " and c.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario();
		}
		listaContrato = contratoFacade.lista(sql);
		if (listaContrato == null) {
			listaContrato = new ArrayList<Contrato>();
		}
		mudarsituacao = true;
		nSituacao = situacao;
	}
	
	
	public String editar(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		session.setAttribute("orgaobanco", contrato.getValorescoeficiente().getCoeficiente().getOrgaoBanco());
		session.setAttribute("voltarTela", "consPortabilidade");
		return "cadContrato";
	}
	
	
	public String alterarSituacao(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		session.setAttribute("voltar", "consPortabilidade");
		return "alterarSituacao";
	}
	
	

	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		listaUsuario = usuarioFacade.listar("Select u From Usuario u order by u.nome");
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}
	
	
	public void pesquisar() {
		String sql = "Select c From Contrato c WHERE c.tipooperacao.descricao like '%Portabilidade%' and c.cliente.nome like '%"+ nomeCliente +
				"%' and c.cliente.cpf like '%"+ cpf +"%'";
		if (nSituacao > 0) {
			sql = sql + " and c.situacao.idsituacao=" + nSituacao;
		}
		if (usuario != null && usuario.getIdusuario() != null  
				&& !usuarioLogadoMB.getUsuario().getTipocolaborador().getAcessocolaborador().isAcessooperacional()) {
			sql = sql + " and c.usuario.idusuario=" + usuario.getIdusuario();
		}
		if (banco != null && banco.getIdbanco() != null) {
			sql = sql + " and c.valorescoeficiente.coeficiente.orgaoBanco.banco.idbanco=" + banco.getIdbanco();
		}
		ContratoFacade contratoFacade = new ContratoFacade();
		listaContrato = contratoFacade.lista(sql);
		if (listaContrato == null) {
			listaContrato = new ArrayList<Contrato>();
		}
	}
	
	public void limpar() {
		gerarListaPortabilidade(nSituacao);
		usuario = null;
		nomeCliente = "";
		cpf = "";
		tipooiperacao = null;
		banco = null;
	}
	
	
	
	public void gerarListaInicial() {
		ContratoFacade contratoFacade = new ContratoFacade();
		String sql = "Select c From Contrato c WHERE c.tipooperacao.descricao like '%Portabilidade%'";
		if (!usuarioLogadoMB.getUsuario().isAcessogeral() 
				&& !usuarioLogadoMB.getUsuario().getTipocolaborador().getAcessocolaborador().isAcessooperacional()) {
			sql = sql + " and c.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario();
		}
		listaContratoPesquisa = contratoFacade.lista(sql);
		if (listaContratoPesquisa == null) {
			listaContratoPesquisa = new ArrayList<Contrato>();
		}
		nTodos = listaContratoPesquisa.size();
		for (int i = 0; i < listaContratoPesquisa.size(); i++) {
			if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 1) {
				nDigitados = nDigitados + 1;
			}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 2) {
				nCancelados = nCancelados + 1;
			}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 5) {
				nPendenciaDocumentacao = nPendenciaDocumentacao + 1;
			}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 7) {
				nAguardandoCIP = nAguardandoCIP + 1;
			}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 15) {
				nRefinanciamento = nRefinanciamento + 1;
			}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 16){
				nPagoCliente = nPagoCliente + 1;
			}
		}
	}
	
	
	public String trocatTitular(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("voltarTela", "consPortabilidade");
		return "trocarTitular";
	}
	
	
	public String anexarArquivo(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		session.setAttribute("voltarTela", "consPortabilidade");
		return "anexarArquivo";
	}
	
	
	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t");
		if (listaTipoOperacao == null) {
			listaTipoOperacao = new ArrayList<Tipooperacao>();
		}
	}
	
	
	public void gerarListaSituacao() {
		SituacaoFacade situacaoFacade = new SituacaoFacade();
		String sql = "Select s From Situacao s WHERE s.visualizar=true ";
		sql = sql + " ORDER BY s.descricao";
		listaSituacao = situacaoFacade.lista(sql);
		if (listaSituacao == null) {
			listaSituacao = new ArrayList<Situacao>();
		}
	}
	
	
	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		listaBanco = bancoFacade.lista("Select b From Banco b Where b.nome !='Nenhum' ORDER BY b.nome");
		if (listaBanco == null) {
			listaBanco = new ArrayList<Banco>();
		}
	}
	
	
	
	public String imprimirFicha(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		session.setAttribute("voltar", "consPortabilidade");
		return "fichaContrato";
	}
	
	
	public void detalheSituacao(Contrato contrato) {
		Mensagem.lancarMensagemInfo("Situação do Contrato:", contrato.getDetalhesituacao());
	}
	
	
	public void gerarComissao(Contrato contrato) {
		RegrasCoeficienteFacade regrasCoeficienteFacade = new RegrasCoeficienteFacade();
		Regrascoeficiente regrascoeficiente = regrasCoeficienteFacade.consultar(contrato.getIdregracoeficiente());
		Historicocomissao historicocomissao = new Historicocomissao();
		historicocomissao.setDatalancamento(new Date());
		historicocomissao.setContrato(contrato);
		historicocomissao.setUsuario(contrato.getUsuario());
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
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		historicoComissaoFacade.salvar(historicocomissao);
	}
	
	
	
	

}
