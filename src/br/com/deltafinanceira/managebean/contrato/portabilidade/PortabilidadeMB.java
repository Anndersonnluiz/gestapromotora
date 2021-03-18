package br.com.deltafinanceira.managebean.contrato.portabilidade;

import br.com.deltafinanceira.bean.FiltrosBean;
import br.com.deltafinanceira.facade.BancoFacade;
import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.facade.HistoricoComissaoFacade;
import br.com.deltafinanceira.facade.PromotoraFacade;
import br.com.deltafinanceira.facade.RegrasCoeficienteFacade;
import br.com.deltafinanceira.facade.SituacaoFacade;
import br.com.deltafinanceira.facade.TipoOperacaoFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Banco;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Historicocomissao;
import br.com.deltafinanceira.model.Promotora;
import br.com.deltafinanceira.model.Regrascoeficiente;
import br.com.deltafinanceira.model.Situacao;
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
public class PortabilidadeMB implements Serializable {
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
  
  private int nAguardandoAssinatura;
  
  private int nFormalizacaoPendencia;
  
  private int nAguardandoFormalizacaoCIP;
  
  private List<Promotora> listaPromotora;
  
  private Promotora promotora;
  
  @PostConstruct
  public void init() {
    gerarListaUsuario();
    gerarListaInicial();
    gerarListaBanco();
    gerarListaPromotora();
    if (!this.usuarioLogadoMB.getUsuario().isAcessogeral() && 
      !this.usuarioLogadoMB.getUsuario().isSupervisao()) {
      this.unicoUsuario = true;
      this.usuario = this.usuarioLogadoMB.getUsuario();
    } 
  }
  
  public List<Contrato> getListaContrato() {
    return this.listaContrato;
  }
  
  public void setListaContrato(List<Contrato> listaContrato) {
    this.listaContrato = listaContrato;
  }
  
  public String getSituacao() {
    return this.situacao;
  }
  
  public void setSituacao(String situacao) {
    this.situacao = situacao;
  }
  
  public List<Usuario> getListaUsuario() {
    return this.listaUsuario;
  }
  
  public void setListaUsuario(List<Usuario> listaUsuario) {
    this.listaUsuario = listaUsuario;
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
  
  public Usuario getUsuario() {
    return this.usuario;
  }
  
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  
  public boolean isMudarsituacao() {
    return this.mudarsituacao;
  }
  
  public void setMudarsituacao(boolean mudarsituacao) {
    this.mudarsituacao = mudarsituacao;
  }
  
  public boolean isIncompletos() {
    return this.incompletos;
  }
  
  public void setIncompletos(boolean incompletos) {
    this.incompletos = incompletos;
  }
  
  public boolean isDigitadosPagDivida() {
    return this.digitadosPagDivida;
  }
  
  public void setDigitadosPagDivida(boolean digitadosPagDivida) {
    this.digitadosPagDivida = digitadosPagDivida;
  }
  
  public boolean isDividaPaga() {
    return this.dividaPaga;
  }
  
  public void setDividaPaga(boolean dividaPaga) {
    this.dividaPaga = dividaPaga;
  }
  
  public boolean isPortabilidadeFin() {
    return this.portabilidadeFin;
  }
  
  public void setPortabilidadeFin(boolean portabilidadeFin) {
    this.portabilidadeFin = portabilidadeFin;
  }
  
  public boolean isRefinanciamentoCriado() {
    return this.refinanciamentoCriado;
  }
  
  public void setRefinanciamentoCriado(boolean refinanciamentoCriado) {
    this.refinanciamentoCriado = refinanciamentoCriado;
  }
  
  public boolean isCancelados() {
    return this.cancelados;
  }
  
  public void setCancelados(boolean cancelados) {
    this.cancelados = cancelados;
  }
  
  public int getnIncompletos() {
    return this.nIncompletos;
  }
  
  public void setnIncompletos(int nIncompletos) {
    this.nIncompletos = nIncompletos;
  }
  
  public int getnDigitados() {
    return this.nDigitados;
  }
  
  public void setnDigitados(int nDigitados) {
    this.nDigitados = nDigitados;
  }
  
  public int getnDivida() {
    return this.nDivida;
  }
  
  public void setnDivida(int nDivida) {
    this.nDivida = nDivida;
  }
  
  public int getnPortabilidade() {
    return this.nPortabilidade;
  }
  
  public void setnPortabilidade(int nPortabilidade) {
    this.nPortabilidade = nPortabilidade;
  }
  
  public int getnRefinanciamento() {
    return this.nRefinanciamento;
  }
  
  public void setnRefinanciamento(int nRefinanciamento) {
    this.nRefinanciamento = nRefinanciamento;
  }
  
  public int getnCancelados() {
    return this.nCancelados;
  }
  
  public void setnCancelados(int nCancelados) {
    this.nCancelados = nCancelados;
  }
  
  public int getnAguardandoSolicitacao() {
    return this.nAguardandoSolicitacao;
  }
  
  public void setnAguardandoSolicitacao(int nAguardandoSolicitacao) {
    this.nAguardandoSolicitacao = nAguardandoSolicitacao;
  }
  
  public int getnAguardandoDigitacao() {
    return this.nAguardandoDigitacao;
  }
  
  public void setnAguardandoDigitacao(int nAguardandoDigitacao) {
    this.nAguardandoDigitacao = nAguardandoDigitacao;
  }
  
  public int getnPendenciaDocumentacao() {
    return this.nPendenciaDocumentacao;
  }
  
  public void setnPendenciaDocumentacao(int nPendenciaDocumentacao) {
    this.nPendenciaDocumentacao = nPendenciaDocumentacao;
  }
  
  public int getnAguardandooperacional() {
    return this.nAguardandooperacional;
  }
  
  public void setnAguardandooperacional(int nAguardandooperacional) {
    this.nAguardandooperacional = nAguardandooperacional;
  }
  
  public int getnAguardandoCIP() {
    return this.nAguardandoCIP;
  }
  
  public void setnAguardandoCIP(int nAguardandoCIP) {
    this.nAguardandoCIP = nAguardandoCIP;
  }
  
  public int getnProblemasCIP() {
    return this.nProblemasCIP;
  }
  
  public void setnProblemasCIP(int nProblemasCIP) {
    this.nProblemasCIP = nProblemasCIP;
  }
  
  public int getnProblemasCIPAguarOper() {
    return this.nProblemasCIPAguarOper;
  }
  
  public void setnProblemasCIPAguarOper(int nProblemasCIPAguarOper) {
    this.nProblemasCIPAguarOper = nProblemasCIPAguarOper;
  }
  
  public int getnSaldoAguarPagDivida() {
    return this.nSaldoAguarPagDivida;
  }
  
  public void setnSaldoAguarPagDivida(int nSaldoAguarPagDivida) {
    this.nSaldoAguarPagDivida = nSaldoAguarPagDivida;
  }
  
  public List<FiltrosBean> getListaFiltrosBean() {
    return this.listaFiltrosBean;
  }
  
  public void setListaFiltrosBean(List<FiltrosBean> listaFiltrosBean) {
    this.listaFiltrosBean = listaFiltrosBean;
  }
  
  public List<Contrato> getListaContratoPesquisa() {
    return this.listaContratoPesquisa;
  }
  
  public void setListaContratoPesquisa(List<Contrato> listaContratoPesquisa) {
    this.listaContratoPesquisa = listaContratoPesquisa;
  }
  
  public int getnSaldoAguarRespCorretor() {
    return this.nSaldoAguarRespCorretor;
  }
  
  public void setnSaldoAguarRespCorretor(int nSaldoAguarRespCorretor) {
    this.nSaldoAguarRespCorretor = nSaldoAguarRespCorretor;
  }
  
  public int getnSaldoAguardandoOperacional() {
    return this.nSaldoAguardandoOperacional;
  }
  
  public void setnSaldoAguardandoOperacional(int nSaldoAguardandoOperacional) {
    this.nSaldoAguardandoOperacional = nSaldoAguardandoOperacional;
  }
  
  public int getnIntencaoPortabilidade() {
    return this.nIntencaoPortabilidade;
  }
  
  public void setnIntencaoPortabilidade(int nIntencaoPortabilidade) {
    this.nIntencaoPortabilidade = nIntencaoPortabilidade;
  }
  
  public int getnSemIntencao() {
    return this.nSemIntencao;
  }
  
  public void setnSemIntencao(int nSemIntencao) {
    this.nSemIntencao = nSemIntencao;
  }
  
  public int getnMargemLiberada() {
    return this.nMargemLiberada;
  }
  
  public void setnMargemLiberada(int nMargemLiberada) {
    this.nMargemLiberada = nMargemLiberada;
  }
  
  public int getnProblemasAverbacao() {
    return this.nProblemasAverbacao;
  }
  
  public void setnProblemasAverbacao(int nProblemasAverbacao) {
    this.nProblemasAverbacao = nProblemasAverbacao;
  }
  
  public int getnAverbacaoAguardandoOperacional() {
    return this.nAverbacaoAguardandoOperacional;
  }
  
  public void setnAverbacaoAguardandoOperacional(int nAverbacaoAguardandoOperacional) {
    this.nAverbacaoAguardandoOperacional = nAverbacaoAguardandoOperacional;
  }
  
  public int getnPortabilidadeFinalizada() {
    return this.nPortabilidadeFinalizada;
  }
  
  public void setnPortabilidadeFinalizada(int nPortabilidadeFinalizada) {
    this.nPortabilidadeFinalizada = nPortabilidadeFinalizada;
  }
  
  public int getnMargemInsuficiente() {
    return this.nMargemInsuficiente;
  }
  
  public void setnMargemInsuficiente(int nMargemInsuficiente) {
    this.nMargemInsuficiente = nMargemInsuficiente;
  }
  
  public int getnPortabilidadeAguardando() {
    return this.nPortabilidadeAguardando;
  }
  
  public void setnPortabilidadeAguardando(int nPortabilidadeAguardando) {
    this.nPortabilidadeAguardando = nPortabilidadeAguardando;
  }
  
  public int getnAguardandoPagamento() {
    return this.nAguardandoPagamento;
  }
  
  public void setnAguardandoPagamento(int nAguardandoPagamento) {
    this.nAguardandoPagamento = nAguardandoPagamento;
  }
  
  public int getnProblemasRefin() {
    return this.nProblemasRefin;
  }
  
  public void setnProblemasRefin(int nProblemasRefin) {
    this.nProblemasRefin = nProblemasRefin;
  }
  
  public int getnProblemasRefinAguardandoOpe() {
    return this.nProblemasRefinAguardandoOpe;
  }
  
  public void setnProblemasRefinAguardandoOpe(int nProblemasRefinAguardandoOpe) {
    this.nProblemasRefinAguardandoOpe = nProblemasRefinAguardandoOpe;
  }
  
  public int getnPagoCliente() {
    return this.nPagoCliente;
  }
  
  public void setnPagoCliente(int nPagoCliente) {
    this.nPagoCliente = nPagoCliente;
  }
  
  public int getnRefinaciamentoPortabilidade() {
    return this.nRefinaciamentoPortabilidade;
  }
  
  public void setnRefinaciamentoPortabilidade(int nRefinaciamentoPortabilidade) {
    this.nRefinaciamentoPortabilidade = nRefinaciamentoPortabilidade;
  }
  
  public int getnCanceladoBancoOperacional() {
    return this.nCanceladoBancoOperacional;
  }
  
  public void setnCanceladoBancoOperacional(int nCanceladoBancoOperacional) {
    this.nCanceladoBancoOperacional = nCanceladoBancoOperacional;
  }
  
  public int getnCanceladoCorretor() {
    return this.nCanceladoCorretor;
  }
  
  public void setnCanceladoCorretor(int nCanceladoCorretor) {
    this.nCanceladoCorretor = nCanceladoCorretor;
  }
  
  public int getnCancelado() {
    return this.nCancelado;
  }
  
  public void setnCancelado(int nCancelado) {
    this.nCancelado = nCancelado;
  }
  
  public int getnSituacao() {
    return this.nSituacao;
  }
  
  public void setnSituacao(int nSituacao) {
    this.nSituacao = nSituacao;
  }
  
  public int getnTodos() {
    return this.nTodos;
  }
  
  public void setnTodos(int nTodos) {
    this.nTodos = nTodos;
  }
  
  public UsuarioLogadoMB getUsuarioLogadoMB() {
    return this.usuarioLogadoMB;
  }
  
  public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
    this.usuarioLogadoMB = usuarioLogadoMB;
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
  
  public List<Situacao> getListaSituacao() {
    return this.listaSituacao;
  }
  
  public void setListaSituacao(List<Situacao> listaSituacao) {
    this.listaSituacao = listaSituacao;
  }
  
  public boolean isUnicoUsuario() {
    return this.unicoUsuario;
  }
  
  public void setUnicoUsuario(boolean unicoUsuario) {
    this.unicoUsuario = unicoUsuario;
  }
  
  public int getnAguardandoAssinatura() {
    return this.nAguardandoAssinatura;
  }
  
  public void setnAguardandoAssinatura(int nAguardandoAssinatura) {
    this.nAguardandoAssinatura = nAguardandoAssinatura;
  }
  
  public int getnFormalizacaoPendencia() {
    return this.nFormalizacaoPendencia;
  }
  
  public void setnFormalizacaoPendencia(int nFormalizacaoPendencia) {
    this.nFormalizacaoPendencia = nFormalizacaoPendencia;
  }
  
  public int getnAguardandoFormalizacaoCIP() {
    return this.nAguardandoFormalizacaoCIP;
  }
  
  public void setnAguardandoFormalizacaoCIP(int nAguardandoFormalizacaoCIP) {
    this.nAguardandoFormalizacaoCIP = nAguardandoFormalizacaoCIP;
  }
  
  public List<Promotora> getListaPromotora() {
    return this.listaPromotora;
  }
  
  public void setListaPromotora(List<Promotora> listaPromotora) {
    this.listaPromotora = listaPromotora;
  }
  
  public Promotora getPromotora() {
    return this.promotora;
  }
  
  public void setPromotora(Promotora promotora) {
    this.promotora = promotora;
  }
  
  public void gerarListaPortabilidade(int situacao) {
    ContratoFacade contratoFacade = new ContratoFacade();
    String sql = "Select c From Contrato c WHERE c.tipooperacao.descricao like '%Portabilidade%' and c.simulacao=false";
    if (situacao > 0)
      sql = String.valueOf(sql) + " and c.situacao.idsituacao =" + situacao; 
    if (!this.usuarioLogadoMB.getUsuario().isAcessogeral() && 
      !this.usuarioLogadoMB.getUsuario().getTipocolaborador().getAcessocolaborador().isAcessooperacional())
      sql = String.valueOf(sql) + " and c.usuario.idusuario=" + this.usuarioLogadoMB.getUsuario().getIdusuario(); 
    this.listaContrato = contratoFacade.lista(sql);
    if (this.listaContrato == null)
      this.listaContrato = new ArrayList<>(); 
    this.mudarsituacao = true;
    this.nSituacao = situacao;
  }
  
  public String editar(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    session.setAttribute("orgaobanco", contrato.getOrgaoBanco());
    return "cadContrato";
  }
  
  public String alterarSituacao(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    session.setAttribute("voltar", "consPortabilidade");
    return "alterarSituacao";
  }
  
  public void gerarListaUsuario() {
    UsuarioFacade usuarioFacade = new UsuarioFacade();
    String sql = "Select u From Usuario u WHERE u.ativo=true";
    sql = String.valueOf(sql) + " and u.departamento.iddepartamento=7 order by u.nome";
    this.listaUsuario = usuarioFacade.listar(sql);
    if (this.listaUsuario == null)
      this.listaUsuario = new ArrayList<>(); 
  }
  
  public void pesquisar() {
    String sql = "Select c From Contrato c WHERE c.tipooperacao.descricao like '%Portabilidade%' and c.cliente.nome like '%" + this.nomeCliente + 
      "%' and c.cliente.cpf like '%" + this.cpf + "%' and c.simulacao=false";
    if (this.nSituacao > 0)
      sql = String.valueOf(sql) + " and c.situacao.idsituacao=" + this.nSituacao; 
    if (this.usuario != null && this.usuario.getIdusuario() != null)
      sql = String.valueOf(sql) + " and c.usuario.idusuario=" + this.usuario.getIdusuario(); 
    if (this.banco != null && this.banco.getIdbanco() != null)
      sql = String.valueOf(sql) + " and c.valorescoeficiente.coeficiente.orgaoBanco.banco.idbanco=" + this.banco.getIdbanco(); 
    if (this.promotora != null && this.promotora.getIdpromotora() != null)
      sql = String.valueOf(sql) + " and c.promotora.idpromotora=" + this.promotora.getIdpromotora(); 
    ContratoFacade contratoFacade = new ContratoFacade();
    this.listaContrato = contratoFacade.lista(sql);
    if (this.listaContrato == null)
      this.listaContrato = new ArrayList<>(); 
  }
  
  public void limpar() {
    gerarListaPortabilidade(this.nSituacao);
    this.usuario = null;
    this.nomeCliente = "";
    this.cpf = "";
    this.tipooiperacao = null;
    this.banco = null;
    this.promotora = null;
  }
  
  public void gerarListaInicial() {
    ContratoFacade contratoFacade = new ContratoFacade();
    String sql = "Select c From Contrato c WHERE c.tipooperacao.descricao like '%Portabilidade%' and c.simulacao=false";
    if (!this.usuarioLogadoMB.getUsuario().isAcessogeral() && 
      !this.usuarioLogadoMB.getUsuario().getTipocolaborador().getAcessocolaborador().isAcessooperacional())
      sql = String.valueOf(sql) + " and c.usuario.idusuario=" + this.usuarioLogadoMB.getUsuario().getIdusuario(); 
    this.listaContratoPesquisa = contratoFacade.lista(sql);
    if (this.listaContratoPesquisa == null)
      this.listaContratoPesquisa = new ArrayList<>(); 
    this.nTodos = this.listaContratoPesquisa.size();
    for (int i = 0; i < this.listaContratoPesquisa.size(); i++) {
      if (((Contrato)this.listaContratoPesquisa.get(i)).getSituacao().getIdsituacao().intValue() == 1) {
        this.nDigitados++;
      } else if (((Contrato)this.listaContratoPesquisa.get(i)).getSituacao().getIdsituacao().intValue() == 2) {
        this.nCancelados++;
      } else if (((Contrato)this.listaContratoPesquisa.get(i)).getSituacao().getIdsituacao().intValue() == 5) {
        this.nPendenciaDocumentacao++;
      } else if (((Contrato)this.listaContratoPesquisa.get(i)).getSituacao().getIdsituacao().intValue() == 7) {
        this.nAguardandoCIP++;
      } else if (((Contrato)this.listaContratoPesquisa.get(i)).getSituacao().getIdsituacao().intValue() == 15) {
        this.nRefinanciamento++;
      } else if (((Contrato)this.listaContratoPesquisa.get(i)).getSituacao().getIdsituacao().intValue() == 16) {
        this.nPagoCliente++;
      } else if (((Contrato)this.listaContratoPesquisa.get(i)).getSituacao().getIdsituacao().intValue() == 28) {
        this.nAguardandoAssinatura++;
      } else if (((Contrato)this.listaContratoPesquisa.get(i)).getSituacao().getIdsituacao().intValue() == 37) {
        this.nFormalizacaoPendencia++;
      } else if (((Contrato)this.listaContratoPesquisa.get(i)).getSituacao().getIdsituacao().intValue() == 38) {
        this.nAguardandoFormalizacaoCIP++;
      } 
    } 
  }
  
  public String trocatTitular(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("voltarTela", "consPortabilidade");
    return "trocarTitular";
  }
  
  public String anexarArquivo(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    session.setAttribute("voltarTela", "consPortabilidade");
    return "anexarArquivo";
  }
  
  public void gerarListaTipoOperacao() {
    TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
    this.listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t");
    if (this.listaTipoOperacao == null)
      this.listaTipoOperacao = new ArrayList<>(); 
  }
  
  public void gerarListaSituacao() {
    SituacaoFacade situacaoFacade = new SituacaoFacade();
    String sql = "Select s From Situacao s WHERE s.visualizar=true ";
    sql = String.valueOf(sql) + " ORDER BY s.descricao";
    this.listaSituacao = situacaoFacade.lista(sql);
    if (this.listaSituacao == null)
      this.listaSituacao = new ArrayList<>(); 
  }
  
  public void gerarListaBanco() {
    BancoFacade bancoFacade = new BancoFacade();
    this.listaBanco = bancoFacade.lista("Select b From Banco b Where b.nome !='Nenhum' ORDER BY b.nome");
    if (this.listaBanco == null)
      this.listaBanco = new ArrayList<>(); 
  }
  
  public String imprimirFicha(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    session.setAttribute("voltar", "consPortabilidade");
    return "fichaContrato";
  }
  
  public void detalheSituacao(Contrato contrato) {
    Mensagem.lancarMensagemInfo("Situado Contrato:", contrato.getDetalhesituacao());
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
    if (contrato.getParcelaspagas() > 12 && contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
      historicocomissao.setCmdbruta(contrato.getValorquitar() * regrascoeficiente.getFlatrecebidaregra() / 100.0F);
      historicocomissao.setCmsliq(contrato.getValorquitar() * regrascoeficiente.getFlatrepassadavista() / 100.0F);
      historicocomissao.setProdliq(contrato.getValorquitar());
    } else if (contrato.getTipooperacao().getIdtipooperacao().intValue() != 1) {
      historicocomissao
        .setCmdbruta(contrato.getValoroperacao() * regrascoeficiente.getFlatrecebidaregra() / 100.0F);
      historicocomissao
        .setCmsliq(contrato.getValoroperacao() * regrascoeficiente.getFlatrepassadavista() / 100.0F);
      historicocomissao.setProdliq(contrato.getValoroperacao());
    } else {
      historicocomissao.setCmdbruta(0.0F);
      historicocomissao.setCmsliq(0.0F);
      historicocomissao.setProdliq(0.0F);
    } 
    HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
    historicoComissaoFacade.salvar(historicocomissao);
    Mensagem.lancarMensagemInfo("Lancom sucesso", "");
  }
  
  public String historicoContrato(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    return "linhaTempoContrato";
  }
  
  public void gerarListaPromotora() {
    PromotoraFacade promotoraFacade = new PromotoraFacade();
    this.listaPromotora = promotoraFacade.lista("Select p From Promotora p");
    if (this.listaPromotora == null)
      this.listaPromotora = new ArrayList<>(); 
  }
}
