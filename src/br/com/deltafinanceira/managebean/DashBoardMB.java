package br.com.deltafinanceira.managebean;

import br.com.deltafinanceira.dao.MetaFaturamentoAnualDao;
import br.com.deltafinanceira.dao.MetaFaturamentoMensalDao;
import br.com.deltafinanceira.dao.NotificacaoDao;
import br.com.deltafinanceira.dao.RankingVendasAnualDao;
import br.com.deltafinanceira.dao.RankingVendasDao;
import br.com.deltafinanceira.facade.AvisosUsuarioFacade;
import br.com.deltafinanceira.facade.FormalizacaoFacade;
import br.com.deltafinanceira.facade.HistoricoComissaoFacade;
import br.com.deltafinanceira.facade.NotificacaoFacade;
import br.com.deltafinanceira.model.Avisosusuario;
import br.com.deltafinanceira.model.Formalizacao;
import br.com.deltafinanceira.model.Historicocomissao;
import br.com.deltafinanceira.model.Metafaturamentoanual;
import br.com.deltafinanceira.model.Metafaturamentomensal;
import br.com.deltafinanceira.model.Notificacao;
import br.com.deltafinanceira.model.Rankingvendas;
import br.com.deltafinanceira.model.Rankingvendasanual;
import br.com.deltafinanceira.util.Formatacao;
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
public class DashBoardMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;

	private Rankingvendas primeiroMes;

	private Rankingvendas segundoMes;

	private Rankingvendas terceiroMes;

	private Rankingvendasanual primeiroAno;

	private Rankingvendasanual segundoAno;

	private Rankingvendasanual terceiroAno;

	private List<Metafaturamentomensal> listaMetaMensal;

	private List<Metafaturamentoanual> listaMetaAnual;

	private String mesAtual;

	private Metafaturamentomensal janeiro;

	private Metafaturamentomensal fevereiro;

	private Metafaturamentomensal marco;

	private Metafaturamentomensal abril;

	private Metafaturamentomensal maio;

	private Metafaturamentomensal junho;

	private Metafaturamentomensal julho;

	private Metafaturamentomensal agosto;

	private Metafaturamentomensal setembro;

	private Metafaturamentomensal outubro;

	private Metafaturamentomensal novembro;

	private Metafaturamentomensal dezembro;

	private float valorPagar;

	private float valorReceber;

	private float fatutamento;

	private int mesatual;

	private int nNotificacao;

	private int nProducao;

	private int nAguardandoPagamento;

	private int nAguardandoAssinatura;

	private int nPendenciaAverbacao;

	private float valorAverbacao;

	private float valorComissaoRecebida;

	private int nComissaoRecebida;

	private boolean viewPagoCliente;

	private int nPendenciaDocumento;

	private float valorProducao;

	private int nTotalProducao;

	private int nFormalizacaoPendencia;

	private List<Notificacao> listaNotificacao;

	private int convenio = 0;

	private List<Avisosusuario> listaAvisos;

	private int nAvisos;

	private boolean verificarAvisos;

	private boolean verificarNotificacoes;

	private int nFormalizacao;

	@PostConstruct
	public void init() {
		faturamentoMensal();
		listarNotificacao();
		listaAvisos();
		gerarRankingMensal();
		gerarListaFormalizacao();
		int mes = Formatacao.getMesData(new Date()) + 1;
		this.mesAtual = Formatacao.nomeMes(mes);
		if (this.usuarioLogadoMB.getUsuario().isDiretoria() || this.usuarioLogadoMB.getUsuario().isSupervisao()) {
			this.viewPagoCliente = true;
		} else {
			this.viewPagoCliente = false;
		}
	}

	public Rankingvendas getPrimeiroMes() {
		return this.primeiroMes;
	}

	public void setPrimeiroMes(Rankingvendas primeiroMes) {
		this.primeiroMes = primeiroMes;
	}

	public Rankingvendas getSegundoMes() {
		return this.segundoMes;
	}

	public void setSegundoMes(Rankingvendas segundoMes) {
		this.segundoMes = segundoMes;
	}

	public Rankingvendas getTerceiroMes() {
		return this.terceiroMes;
	}

	public void setTerceiroMes(Rankingvendas terceiroMes) {
		this.terceiroMes = terceiroMes;
	}

	public Rankingvendasanual getPrimeiroAno() {
		return this.primeiroAno;
	}

	public void setPrimeiroAno(Rankingvendasanual primeiroAno) {
		this.primeiroAno = primeiroAno;
	}

	public Rankingvendasanual getSegundoAno() {
		return this.segundoAno;
	}

	public void setSegundoAno(Rankingvendasanual segundoAno) {
		this.segundoAno = segundoAno;
	}

	public Rankingvendasanual getTerceiroAno() {
		return this.terceiroAno;
	}

	public void setTerceiroAno(Rankingvendasanual terceiroAno) {
		this.terceiroAno = terceiroAno;
	}

	public List<Metafaturamentomensal> getListaMetaMensal() {
		return this.listaMetaMensal;
	}

	public void setListaMetaMensal(List<Metafaturamentomensal> listaMetaMensal) {
		this.listaMetaMensal = listaMetaMensal;
	}

	public List<Metafaturamentoanual> getListaMetaAnual() {
		return this.listaMetaAnual;
	}

	public void setListaMetaAnual(List<Metafaturamentoanual> listaMetaAnual) {
		this.listaMetaAnual = listaMetaAnual;
	}

	public String getMesAtual() {
		return this.mesAtual;
	}

	public void setMesAtual(String mesAtual) {
		this.mesAtual = mesAtual;
	}

	public Metafaturamentomensal getJaneiro() {
		return this.janeiro;
	}

	public void setJaneiro(Metafaturamentomensal janeiro) {
		this.janeiro = janeiro;
	}

	public Metafaturamentomensal getFevereiro() {
		return this.fevereiro;
	}

	public void setFevereiro(Metafaturamentomensal fevereiro) {
		this.fevereiro = fevereiro;
	}

	public Metafaturamentomensal getMarco() {
		return this.marco;
	}

	public void setMarco(Metafaturamentomensal marco) {
		this.marco = marco;
	}

	public Metafaturamentomensal getAbril() {
		return this.abril;
	}

	public void setAbril(Metafaturamentomensal abril) {
		this.abril = abril;
	}

	public Metafaturamentomensal getMaio() {
		return this.maio;
	}

	public void setMaio(Metafaturamentomensal maio) {
		this.maio = maio;
	}

	public Metafaturamentomensal getJunho() {
		return this.junho;
	}

	public void setJunho(Metafaturamentomensal junho) {
		this.junho = junho;
	}

	public Metafaturamentomensal getJulho() {
		return this.julho;
	}

	public void setJulho(Metafaturamentomensal julho) {
		this.julho = julho;
	}

	public Metafaturamentomensal getAgosto() {
		return this.agosto;
	}

	public void setAgosto(Metafaturamentomensal agosto) {
		this.agosto = agosto;
	}

	public Metafaturamentomensal getSetembro() {
		return this.setembro;
	}

	public void setSetembro(Metafaturamentomensal setembro) {
		this.setembro = setembro;
	}

	public Metafaturamentomensal getOutubro() {
		return this.outubro;
	}

	public void setOutubro(Metafaturamentomensal outubro) {
		this.outubro = outubro;
	}

	public Metafaturamentomensal getNovembro() {
		return this.novembro;
	}

	public void setNovembro(Metafaturamentomensal novembro) {
		this.novembro = novembro;
	}

	public Metafaturamentomensal getDezembro() {
		return this.dezembro;
	}

	public void setDezembro(Metafaturamentomensal dezembro) {
		this.dezembro = dezembro;
	}

	public float getFatutamento() {
		return this.fatutamento;
	}

	public void setFatutamento(float fatutamento) {
		this.fatutamento = fatutamento;
	}

	public int getMesatual() {
		return this.mesatual;
	}

	public void setMesatual(int mesatual) {
		this.mesatual = mesatual;
	}

	public float getValorPagar() {
		return this.valorPagar;
	}

	public void setValorPagar(float valorPagar) {
		this.valorPagar = valorPagar;
	}

	public float getValorReceber() {
		return this.valorReceber;
	}

	public void setValorReceber(float valorReceber) {
		this.valorReceber = valorReceber;
	}

	public int getnNotificacao() {
		return this.nNotificacao;
	}

	public void setnNotificacao(int nNotificacao) {
		this.nNotificacao = nNotificacao;
	}

	public int getnProducao() {
		return this.nProducao;
	}

	public void setnProducao(int nProducao) {
		this.nProducao = nProducao;
	}

	public int getnAguardandoPagamento() {
		return this.nAguardandoPagamento;
	}

	public void setnAguardandoPagamento(int nAguardandoPagamento) {
		this.nAguardandoPagamento = nAguardandoPagamento;
	}

	public int getnAguardandoAssinatura() {
		return this.nAguardandoAssinatura;
	}

	public void setnAguardandoAssinatura(int nAguardandoAssinatura) {
		this.nAguardandoAssinatura = nAguardandoAssinatura;
	}

	public UsuarioLogadoMB getUsuarioLogadoMB() {
		return this.usuarioLogadoMB;
	}

	public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
		this.usuarioLogadoMB = usuarioLogadoMB;
	}

	public int getnPendenciaAverbacao() {
		return this.nPendenciaAverbacao;
	}

	public void setnPendenciaAverbacao(int nPendenciaAverbacao) {
		this.nPendenciaAverbacao = nPendenciaAverbacao;
	}

	public float getValorAverbacao() {
		return this.valorAverbacao;
	}

	public void setValorAverbacao(float valorAverbacao) {
		this.valorAverbacao = valorAverbacao;
	}

	public float getValorComissaoRecebida() {
		return this.valorComissaoRecebida;
	}

	public void setValorComissaoRecebida(float valorComissaoRecebida) {
		this.valorComissaoRecebida = valorComissaoRecebida;
	}

	public int getnComissaoRecebida() {
		return this.nComissaoRecebida;
	}

	public void setnComissaoRecebida(int nComissaoRecebida) {
		this.nComissaoRecebida = nComissaoRecebida;
	}

	public boolean isViewPagoCliente() {
		return this.viewPagoCliente;
	}

	public void setViewPagoCliente(boolean viewPagoCliente) {
		this.viewPagoCliente = viewPagoCliente;
	}

	public int getnPendenciaDocumento() {
		return this.nPendenciaDocumento;
	}

	public void setnPendenciaDocumento(int nPendenciaDocumento) {
		this.nPendenciaDocumento = nPendenciaDocumento;
	}

	public float getValorProducao() {
		return this.valorProducao;
	}

	public void setValorProducao(float valorProducao) {
		this.valorProducao = valorProducao;
	}

	public int getnTotalProducao() {
		return this.nTotalProducao;
	}

	public void setnTotalProducao(int nTotalProducao) {
		this.nTotalProducao = nTotalProducao;
	}

	public int getnFormalizacaoPendencia() {
		return this.nFormalizacaoPendencia;
	}

	public void setnFormalizacaoPendencia(int nFormalizacaoPendencia) {
		this.nFormalizacaoPendencia = nFormalizacaoPendencia;
	}

	public List<Notificacao> getListaNotificacao() {
		return listaNotificacao;
	}

	public void setListaNotificacao(List<Notificacao> listaNotificacao) {
		this.listaNotificacao = listaNotificacao;
	}

	public int getConvenio() {
		return convenio;
	}

	public void setConvenio(int convenio) {
		this.convenio = convenio;
	}

	public List<Avisosusuario> getListaAvisos() {
		return listaAvisos;
	}

	public void setListaAvisos(List<Avisosusuario> listaAvisos) {
		this.listaAvisos = listaAvisos;
	}

	public int getnAvisos() {
		return nAvisos;
	}

	public void setnAvisos(int nAvisos) {
		this.nAvisos = nAvisos;
	}

	public boolean isVerificarAvisos() {
		return verificarAvisos;
	}

	public void setVerificarAvisos(boolean verificarAvisos) {
		this.verificarAvisos = verificarAvisos;
	}

	public boolean isVerificarNotificacoes() {
		return verificarNotificacoes;
	}

	public void setVerificarNotificacoes(boolean verificarNotificacoes) {
		this.verificarNotificacoes = verificarNotificacoes;
	}

	public int getnFormalizacao() {
		return nFormalizacao;
	}

	public void setnFormalizacao(int nFormalizacao) {
		this.nFormalizacao = nFormalizacao;
	}

	public void listarMetaMensal() {
		MetaFaturamentoMensalDao metaFaturamentoMensalDao = new MetaFaturamentoMensalDao();
		this.listaMetaMensal = metaFaturamentoMensalDao
				.lista("Select m From Metafaturamentomensal m WHERE  m.ano=" + Formatacao.getAnoData(new Date()));
		if (this.listaMetaMensal == null)
			this.listaMetaMensal = new ArrayList<>();
		for (int i = 0; i < this.listaMetaMensal.size(); i++) {
			if (((Metafaturamentomensal) this.listaMetaMensal.get(i)).getMes() == 1) {
				this.janeiro = this.listaMetaMensal.get(i);
			} else if (((Metafaturamentomensal) this.listaMetaMensal.get(i)).getMes() == 2) {
				this.fevereiro = this.listaMetaMensal.get(i);
			} else if (((Metafaturamentomensal) this.listaMetaMensal.get(i)).getMes() == 3) {
				this.marco = this.listaMetaMensal.get(i);
			} else if (((Metafaturamentomensal) this.listaMetaMensal.get(i)).getMes() == 4) {
				this.abril = this.listaMetaMensal.get(i);
			} else if (((Metafaturamentomensal) this.listaMetaMensal.get(i)).getMes() == 5) {
				this.maio = this.listaMetaMensal.get(i);
			} else if (((Metafaturamentomensal) this.listaMetaMensal.get(i)).getMes() == 6) {
				this.junho = this.listaMetaMensal.get(i);
			} else if (((Metafaturamentomensal) this.listaMetaMensal.get(i)).getMes() == 7) {
				this.julho = this.listaMetaMensal.get(i);
			} else if (((Metafaturamentomensal) this.listaMetaMensal.get(i)).getMes() == 8) {
				this.agosto = this.listaMetaMensal.get(i);
			} else if (((Metafaturamentomensal) this.listaMetaMensal.get(i)).getMes() == 9) {
				this.setembro = this.listaMetaMensal.get(i);
			} else if (((Metafaturamentomensal) this.listaMetaMensal.get(i)).getMes() == 10) {
				this.outubro = this.listaMetaMensal.get(i);
			} else if (((Metafaturamentomensal) this.listaMetaMensal.get(i)).getMes() == 11) {
				this.novembro = this.listaMetaMensal.get(i);
			} else {
				this.dezembro = this.listaMetaMensal.get(i);
			}
		}
		faturamentoMensal();
	}

	public void listarMetaAnual() {
		MetaFaturamentoAnualDao metaFaturamentoAnualDao = new MetaFaturamentoAnualDao();
		this.listaMetaAnual = metaFaturamentoAnualDao
				.lista("Select m From Metafaturamentoanual m WHERE m.ano=" + Formatacao.getAnoData(new Date()));
		if (this.listaMetaAnual == null)
			this.listaMetaAnual = new ArrayList<>();
	}

	public void gerarRankingMensal() {
		RankingVendasDao rankingVendasDao = new RankingVendasDao();
		List<Rankingvendas> listaRanking = rankingVendasDao
				.lista("Select r From Rankingvendas r WHERE r.mes=" + (Formatacao.getMesData(new Date()) + 1)
						+ " AND r.ano=" + Formatacao.getAnoData(new Date()) + " ORDER BY r.comissaovenda DESC");
		if (listaRanking == null)
			listaRanking = new ArrayList<>();
		primeiroMes = new Rankingvendas();
		segundoMes = new Rankingvendas();
		terceiroMes = new Rankingvendas();
		for (int i = 0; i < listaRanking.size(); i++) {
			if (i == 0) {
				this.primeiroMes = listaRanking.get(i);
				if (this.primeiroMes == null)
					this.primeiroMes = new Rankingvendas();
			} else if (i == 1) {
				this.segundoMes = listaRanking.get(i);
				if (this.segundoMes == null)
					this.segundoMes = new Rankingvendas();
			} else if (i == 2) {
				this.terceiroMes = listaRanking.get(i);
				if (this.terceiroMes == null)
					this.terceiroMes = new Rankingvendas();
			}
		}
	}

	public void gerarRankingAnual() {
		RankingVendasAnualDao rankingVendasDao = new RankingVendasAnualDao();
		List<Rankingvendasanual> listaRanking = rankingVendasDao
				.lista("Select r From Rankingvendasanual r WHERE  r.ano=" + Formatacao.getAnoData(new Date())
						+ " ORDER BY r.valorvenda DESC");
		if (listaRanking == null)
			listaRanking = new ArrayList<>();
		for (int i = 0; i < listaRanking.size(); i++) {
			if (i == 0) {
				this.primeiroAno = listaRanking.get(i);
				if (this.primeiroAno == null)
					this.primeiroAno = new Rankingvendasanual();
			} else if (i == 1) {
				this.segundoAno = listaRanking.get(i);
				if (this.segundoAno == null)
					this.segundoAno = new Rankingvendasanual();
			} else if (i == 2) {
				this.terceiroAno = listaRanking.get(i);
				if (this.terceiroAno == null)
					this.terceiroAno = new Rankingvendasanual();
			}
		}
	}

	public void faturamentoMensal() {
		Date dataInicio = Formatacao.ConvercaoStringData("2020-10-31");
		this.mesatual = Formatacao.getMesData(new Date()) + 1;
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		String sql = "Select h From Historicocomissao h Where h.contrato.situacao.idsituacao<>2 and h.baixa=false and h.contrato.simulacao=false"
				+ " and h.contrato.usuario.treinamento=false";
		if (!this.usuarioLogadoMB.getUsuario().isAcessogeral() && !this.usuarioLogadoMB.getUsuario().isSupervisao())
			sql = String.valueOf(sql) + " and h.usuario.idusuario=" + this.usuarioLogadoMB.getUsuario().getIdusuario();
		if (this.convenio > 0) {
			if (this.convenio == 1) {
				sql = String.valueOf(sql) + " and h.contrato.operacaoinss=true";
			} else if (this.convenio == 2) {
				sql = String.valueOf(sql) + " and h.contrato.operacaoinss=false";
			}
		}
		List<Historicocomissao> lista = historicoComissaoFacade.lista(sql);
		if (lista == null)
			lista = new ArrayList<>();
		this.nAguardandoAssinatura = 0;
		this.nAguardandoPagamento = 0;
		this.nProducao = 0;
		this.fatutamento = 0.0F;
		this.valorPagar = 0.0F;
		this.valorReceber = 0.0F;
		this.valorComissaoRecebida = 0.0F;
		this.valorAverbacao = 0.0F;
		this.valorProducao = 0.0F;
		this.nPendenciaDocumento = 0;
		this.valorProducao = 0.0F;
		this.nTotalProducao = 0;
		this.nFormalizacaoPendencia = 0;
		for (int i = 0; i < lista.size(); i++) {
			if (((Historicocomissao) lista.get(i)).getContrato().getSituacao().getIdsituacao().intValue() == 16
					&& ((Historicocomissao) lista.get(i)).getTipo().equalsIgnoreCase("PENDENTE")) {
				if (this.usuarioLogadoMB.getUsuario().isSupervisao()) {
					this.fatutamento += ((Historicocomissao) lista.get(i)).getProdliq();
				} else if (this.usuarioLogadoMB.getUsuario().isAcessogeral()) {
					this.fatutamento += ((Historicocomissao) lista.get(i)).getComissaototal();
				} else {
					this.fatutamento += ((Historicocomissao) lista.get(i)).getCmsliq();
				}
				this.nProducao++;
			} else if (((Historicocomissao) lista.get(i)).getContrato().getSituacao().getIdsituacao().intValue() == 19
					&& ((Historicocomissao) lista.get(i)).getTipo().equalsIgnoreCase("PENDENTE")) {
				if (this.usuarioLogadoMB.getUsuario().isAcessogeral()) {
					this.valorReceber += ((Historicocomissao) lista.get(i)).getComissaototal();
				} else {
					this.valorReceber += ((Historicocomissao) lista.get(i)).getCmsliq();
				}
				this.nAguardandoPagamento++;
			} else if (((Historicocomissao) lista.get(i)).getContrato().getSituacao().getIdsituacao().intValue() == 28
					&& ((Historicocomissao) lista.get(i)).getTipo().equalsIgnoreCase("PENDENTE")) {
				if (this.usuarioLogadoMB.getUsuario().isAcessogeral()) {
					this.valorPagar += ((Historicocomissao) lista.get(i)).getComissaototal();
				} else {
					this.valorPagar += ((Historicocomissao) lista.get(i)).getCmsliq();
				}
				this.nAguardandoAssinatura++;
			} else if (((Historicocomissao) lista.get(i)).getContrato().getSituacao().getIdsituacao().intValue() == 36
					&& ((Historicocomissao) lista.get(i)).getTipo().equalsIgnoreCase("PENDENTE")) {
				if (this.usuarioLogadoMB.getUsuario().isAcessogeral()) {
					this.valorAverbacao += ((Historicocomissao) lista.get(i)).getComissaototal();
				} else {
					this.valorAverbacao += ((Historicocomissao) lista.get(i)).getCmsliq();
				}
				this.nPendenciaAverbacao++;
			} else if (((Historicocomissao) lista.get(i)).getContrato().getSituacao().getIdsituacao().intValue() == 5
					&& ((Historicocomissao) lista.get(i)).getTipo().equalsIgnoreCase("PENDENTE")) {
				this.nPendenciaDocumento++;
			} else if (((Historicocomissao) lista.get(i)).getTipo().equalsIgnoreCase("Pago")
					&& ((Historicocomissao) lista.get(i)).getContrato().getUltimamudancasituacao().after(dataInicio)) {
				if (this.usuarioLogadoMB.getUsuario().isAcessogeral()) {
					this.valorComissaoRecebida += ((Historicocomissao) lista.get(i)).getComissaototal();
				} else {
					this.valorComissaoRecebida += ((Historicocomissao) lista.get(i)).getCmsliq();
				}
				this.nComissaoRecebida++;
			} else if (((Historicocomissao) lista.get(i)).getContrato().getSituacao().getIdsituacao()
					.intValue() == 37) {
				this.nFormalizacaoPendencia++;
			}
			if (((Historicocomissao) lista.get(i)).getTipo().equalsIgnoreCase("PENDENTE")
					&& ((Historicocomissao) lista.get(i)).getContrato().getUltimamudancasituacao().after(dataInicio)) {
				this.valorProducao += ((Historicocomissao) lista.get(i)).getProdliq();
				this.nTotalProducao++;
			}
		}
	}

	public String pagoCliente() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("tipoFiltro", "16");
		session.setAttribute("convenio", convenio);
		return "consPagamentoComissao";
	}

	public String receitaNaoPaga() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("tipoFiltro", "19");
		session.setAttribute("convenio", convenio);
		return "consPagamentoComissao";
	}

	public String receitaPaga() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("tipoFiltro", "28");
		session.setAttribute("convenio", convenio);
		return "consPagamentoComissao";
	}

	public String receitaPendencia() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("tipoFiltro", "36");
		session.setAttribute("convenio", convenio);
		return "consPagamentoComissao";
	}

	public String comissaoRecebida() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("tipoFiltro", "comissao");
		session.setAttribute("convenio", convenio);
		return "consPagamentoComissao";
	}

	public void listarNotificacao() {
		NotificacaoDao notificacaoDao = new NotificacaoDao();
		List<Notificacao> listaNotificacao = notificacaoDao
				.lista("Select n From Notificacao n WHERE n.visto=false AND n.usuario.idusuario="
						+ this.usuarioLogadoMB.getUsuario().getIdusuario() + " ORDER BY n.datalancamento DESC");
		if (listaNotificacao == null)
			listaNotificacao = new ArrayList<>();
		this.listaNotificacao = new ArrayList<Notificacao>();
		for (int i = 0; i < listaNotificacao.size(); i++) {
			if (this.listaNotificacao.size() < 3) {
				this.listaNotificacao.add(listaNotificacao.get(i));
			} else {
				i = listaNotificacao.size();
			}
		}
		this.nNotificacao = listaNotificacao.size();
		if (nNotificacao > 0) {
			verificarNotificacoes = true;
		} else {
			verificarNotificacoes = false;
		}
	}

	public String producaoGeral() {
		return "consProducao";
	}

	public String pendencias() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("voltar", "dashboard");
		return "consPendencias";
	}
	
	public String formalizacao() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("voltar", "dashboard");
		return "consFormalizacao";
	}

	public String receitaPendenciaDocs() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("tipoFiltro", "5");
		session.setAttribute("convenio", convenio);
		return "consPagamentoComissao";
	}

	public void vistoTodos() {
		NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
		if (listaNotificacao == null) {
			listaNotificacao = new ArrayList<Notificacao>();
		}
		for (int i = 0; i < listaNotificacao.size(); i++) {
			listaNotificacao.get(i).setVisto(true);
			notificacaoFacade.salvar(listaNotificacao.get(i));
		}
		listaNotificacao = new ArrayList<Notificacao>();
		listarNotificacao();
	}

	public void listaAvisos() {
		String dataHoje = Formatacao.ConvercaoDataNfe(new Date());
		AvisosUsuarioFacade avisosUsuarioFacade = new AvisosUsuarioFacade();
		listaAvisos = avisosUsuarioFacade.lista("Select a From Avisosusuario a Where a.usuario.idusuario="
				+ usuarioLogadoMB.getUsuario().getIdusuario() + " and a.visto=false and a.avisos.datainicio<='"
				+ dataHoje + "' and a.avisos.datafinal>='" + dataHoje + "'");
		if (listaAvisos == null) {
			listaAvisos = new ArrayList<Avisosusuario>();
		}
		nAvisos = listaAvisos.size();
		if (nAvisos > 0) {
			verificarAvisos = true;
		} else {
			verificarAvisos = false;
		}
	}
	
	
	public void gerarListaFormalizacao() {
		FormalizacaoFacade formalizacaoFacade = new FormalizacaoFacade();
		List<Formalizacao> listaFormalizacao = formalizacaoFacade.lista("Select f From Formalizacao f Where f.emitidocontrato=false");
		if (listaFormalizacao == null) {
			listaFormalizacao = new ArrayList<Formalizacao>();
		}
		nFormalizacao = 0;
		for (int i = 0; i < listaFormalizacao.size(); i++) {
			nFormalizacao = nFormalizacao + 1;
		}
	}
	
	
	
	
	

}
