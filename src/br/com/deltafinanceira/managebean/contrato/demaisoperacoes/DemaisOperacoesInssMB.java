package br.com.deltafinanceira.managebean.contrato.demaisoperacoes;

import br.com.deltafinanceira.facade.BancoFacade;
import br.com.deltafinanceira.facade.CoeficienteFacade;
import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.facade.HistoricoComissaoFacade;
import br.com.deltafinanceira.facade.PromotoraFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Banco;
import br.com.deltafinanceira.model.Coeficiente;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Historicocomissao;
import br.com.deltafinanceira.model.Promotora;
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
public class DemaisOperacoesInssMB implements Serializable {
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
  
  private boolean digitadoAndamento = false;
  
  private boolean pagoClientePendencia = false;
  
  private boolean cancelados = false;
  
  private int nAguardandoAssinatura;
  
  private int nDigitados;
  
  private int nPagoCliente;
  
  private int nCancelados;
  
  private int nAguardandoSolicitacao;
  
  private int nAguardandoDigitacao;
  
  private int nPendenciaDocumentacao;
  
  private int nAguardandooperacional;
  
  private int nAguardandoPagamento;
  
  private int nInconsistenciaBanco;
  
  private int nInconsistenciaAguardando;
  
  private int nDigitadoPago;
  
  private int nLiberal;
  
  private int nMaloteNaoEnviado;
  
  private int nMaloteEnviado;
  
  private int nFormalizacaoDigital;
  
  private int nCanceladoBancoOperacional;
  
  private int nCanceladoCorretor;
  
  private int nCancelado;
  
  private List<Contrato> listaContratoPesquisa;
  
  private int nSituacao;
  
  private int nPendenciaAverbacao;
  
  private int nTodos;
  
  private List<Banco> listaBanco;
  
  private Banco banco;
  
  private boolean unicoUsuario;
  
  private int nFormalizacaoPendencia;
  
  private List<Promotora> listaPromotora;
  
  private Promotora promotora;
  
  @PostConstruct
  public void init() {
    gerarListaUsuario();
    gerarListaInicial();
    gerarListaBanco();
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
  
  public boolean isDigitadoAndamento() {
    return this.digitadoAndamento;
  }
  
  public void setDigitadoAndamento(boolean digitadoAndamento) {
    this.digitadoAndamento = digitadoAndamento;
  }
  
  public boolean isPagoClientePendencia() {
    return this.pagoClientePendencia;
  }
  
  public void setPagoClientePendencia(boolean pagoClientePendencia) {
    this.pagoClientePendencia = pagoClientePendencia;
  }
  
  public boolean isCancelados() {
    return this.cancelados;
  }
  
  public void setCancelados(boolean cancelados) {
    this.cancelados = cancelados;
  }
  
  public int getnAguardandoAssinatura() {
    return this.nAguardandoAssinatura;
  }
  
  public void setnAguardandoAssinatura(int nAguardandoAssinatura) {
    this.nAguardandoAssinatura = nAguardandoAssinatura;
  }
  
  public int getnDigitados() {
    return this.nDigitados;
  }
  
  public void setnDigitados(int nDigitados) {
    this.nDigitados = nDigitados;
  }
  
  public int getnPagoCliente() {
    return this.nPagoCliente;
  }
  
  public void setnPagoCliente(int nPagoCliente) {
    this.nPagoCliente = nPagoCliente;
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
  
  public int getnAguardandoPagamento() {
    return this.nAguardandoPagamento;
  }
  
  public void setnAguardandoPagamento(int nAguardandoPagamento) {
    this.nAguardandoPagamento = nAguardandoPagamento;
  }
  
  public int getnInconsistenciaBanco() {
    return this.nInconsistenciaBanco;
  }
  
  public void setnInconsistenciaBanco(int nInconsistenciaBanco) {
    this.nInconsistenciaBanco = nInconsistenciaBanco;
  }
  
  public int getnInconsistenciaAguardando() {
    return this.nInconsistenciaAguardando;
  }
  
  public void setnInconsistenciaAguardando(int nInconsistenciaAguardando) {
    this.nInconsistenciaAguardando = nInconsistenciaAguardando;
  }
  
  public int getnDigitadoPago() {
    return this.nDigitadoPago;
  }
  
  public void setnDigitadoPago(int nDigitadoPago) {
    this.nDigitadoPago = nDigitadoPago;
  }
  
  public int getnLiberal() {
    return this.nLiberal;
  }
  
  public void setnLiberal(int nLiberal) {
    this.nLiberal = nLiberal;
  }
  
  public int getnMaloteNaoEnviado() {
    return this.nMaloteNaoEnviado;
  }
  
  public void setnMaloteNaoEnviado(int nMaloteNaoEnviado) {
    this.nMaloteNaoEnviado = nMaloteNaoEnviado;
  }
  
  public int getnMaloteEnviado() {
    return this.nMaloteEnviado;
  }
  
  public void setnMaloteEnviado(int nMaloteEnviado) {
    this.nMaloteEnviado = nMaloteEnviado;
  }
  
  public int getnFormalizacaoDigital() {
    return this.nFormalizacaoDigital;
  }
  
  public void setnFormalizacaoDigital(int nFormalizacaoDigital) {
    this.nFormalizacaoDigital = nFormalizacaoDigital;
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
  
  public List<Contrato> getListaContratoPesquisa() {
    return this.listaContratoPesquisa;
  }
  
  public void setListaContratoPesquisa(List<Contrato> listaContratoPesquisa) {
    this.listaContratoPesquisa = listaContratoPesquisa;
  }
  
  public int getnSituacao() {
    return this.nSituacao;
  }
  
  public void setnSituacao(int nSituacao) {
    this.nSituacao = nSituacao;
  }
  
  public int getnPendenciaAverbacao() {
    return this.nPendenciaAverbacao;
  }
  
  public void setnPendenciaAverbacao(int nPendenciaAverbacao) {
    this.nPendenciaAverbacao = nPendenciaAverbacao;
  }
  
  public int getnTodos() {
    return this.nTodos;
  }
  
  public void setnTodos(int nTodos) {
    this.nTodos = nTodos;
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
  
  public boolean isUnicoUsuario() {
    return this.unicoUsuario;
  }
  
  public void setUnicoUsuario(boolean unicoUsuario) {
    this.unicoUsuario = unicoUsuario;
  }
  
  public int getnFormalizacaoPendencia() {
    return this.nFormalizacaoPendencia;
  }
  
  public void setnFormalizacaoPendencia(int nFormalizacaoPendencia) {
    this.nFormalizacaoPendencia = nFormalizacaoPendencia;
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
  
  public void gerarListaDemaisOperacoes(int situacao) {
    ContratoFacade contratoFacade = new ContratoFacade();
    String sql = "Select c From Contrato c WHERE c.tipooperacao.descricao not like '%Portabilidade%' and c.operacaoinss=true  and c.simulacao=false";
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
    session.setAttribute("voltar", "consDemaisOperacoesINSS");
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
    String sql = "Select c From Contrato c WHERE c.tipooperacao.descricao not like '%Portabilidade%' and c.cliente.nome like '%" + this.nomeCliente + 
      "%' and c.cliente.cpf like '%" + this.cpf + "%'" + 
      " and c.operacaoinss=true  and c.simulacao=false";
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
    gerarListaDemaisOperacoes(this.nSituacao);
    this.usuario = null;
    this.nomeCliente = "";
    this.cpf = "";
    this.banco = null;
    this.promotora = null;
  }
  
  public void gerarListaInicial() {
    ContratoFacade contratoFacade = new ContratoFacade();
    String sql = "Select c From Contrato c WHERE c.tipooperacao.descricao not like '%Portabilidade%' and c.operacaoinss=true  and c.simulacao=false";
    if (!this.usuarioLogadoMB.getUsuario().isAcessogeral() && 
      !this.usuarioLogadoMB.getUsuario().getTipocolaborador().getAcessocolaborador().isAcessooperacional())
      sql = String.valueOf(sql) + " and c.usuario.idusuario=" + this.usuarioLogadoMB.getUsuario().getIdusuario(); 
    this.listaContratoPesquisa = contratoFacade.lista(sql);
    if (this.listaContratoPesquisa == null)
      this.listaContratoPesquisa = new ArrayList<>(); 
    this.nDigitados = 0;
    this.nPendenciaDocumentacao = 0;
    this.nAguardandoAssinatura = 0;
    this.nAguardandoPagamento = 0;
    this.nPagoCliente = 0;
    this.nCancelado = 0;
    this.nPendenciaAverbacao = 0;
    this.nTodos = this.listaContratoPesquisa.size();
    for (int i = 0; i < this.listaContratoPesquisa.size(); i++) {
      if (((Contrato)this.listaContratoPesquisa.get(i)).getSituacao().getIdsituacao().intValue() == 1) {
        this.nDigitados++;
      } else if (((Contrato)this.listaContratoPesquisa.get(i)).getSituacao().getIdsituacao().intValue() == 5) {
        this.nPendenciaDocumentacao++;
      } else if (((Contrato)this.listaContratoPesquisa.get(i)).getSituacao().getIdsituacao().intValue() == 28) {
        this.nAguardandoAssinatura++;
      } else if (((Contrato)this.listaContratoPesquisa.get(i)).getSituacao().getIdsituacao().intValue() == 19) {
        this.nAguardandoPagamento++;
      } else if (((Contrato)this.listaContratoPesquisa.get(i)).getSituacao().getIdsituacao().intValue() == 16) {
        this.nPagoCliente++;
      } else if (((Contrato)this.listaContratoPesquisa.get(i)).getSituacao().getIdsituacao().intValue() == 2) {
        this.nCancelados++;
      } else if (((Contrato)this.listaContratoPesquisa.get(i)).getSituacao().getIdsituacao().intValue() == 36) {
        this.nPendenciaAverbacao++;
      } else if (((Contrato)this.listaContratoPesquisa.get(i)).getSituacao().getIdsituacao().intValue() == 37) {
        this.nFormalizacaoPendencia++;
      } 
    } 
  }
  
  public String trocatTitular(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    session.setAttribute("voltarTela", "consDemaisOperacoesInss");
    return "trocarTitular";
  }
  
  public String anexarArquivo(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    session.setAttribute("voltarTela", "consDemaisOperacoesINSS");
    return "anexarArquivo";
  }
  
  public void gerarListaBanco() {
    BancoFacade bancoFacade = new BancoFacade();
    this.listaBanco = bancoFacade.lista("Select b From Banco b Where b.visualizar=true ORDER BY b.nome");
    if (this.listaBanco == null)
      this.listaBanco = new ArrayList<>(); 
  }
  
  public String imprimirFicha(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    session.setAttribute("voltar", "consDemaisOperacoesINSS");
    return "fichaContrato";
  }
  
  public void detalheSituacao(Contrato contrato) {
    Mensagem.lancarMensagemInfo("Situado Contrato:", contrato.getDetalhesituacao());
  }
  
  public void gerarComissao(Contrato contrato) {
    CoeficienteFacade coeficienteFacade = new CoeficienteFacade();
    Coeficiente coeficiente = coeficienteFacade.consultar(contrato.getIdregracoeficiente());
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
      historicocomissao.setCmdbruta(contrato.getValorquitar() * coeficiente.getComissaoloja() / 100.0F);
      historicocomissao.setCmsliq(contrato.getValorquitar() * coeficiente.getComissaocorretor() / 100.0F);
      historicocomissao.setProdliq(contrato.getValorquitar());
    } else if (contrato.getTipooperacao().getIdtipooperacao().intValue() != 1) {
      historicocomissao
        .setCmdbruta(contrato.getValoroperacao() * coeficiente.getComissaoloja() / 100.0F);
      historicocomissao
        .setCmsliq(contrato.getValoroperacao() * coeficiente.getComissaocorretor() / 100.0F);
      historicocomissao.setProdliq(contrato.getValoroperacao());
    } else {
      historicocomissao.setCmdbruta(0.0F);
      historicocomissao.setCmsliq(0.0F);
      historicocomissao.setProdliq(0.0F);
    } 
    HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
    historicoComissaoFacade.salvar(historicocomissao);
    Mensagem.lancarMensagemInfo("Lanrealizado com sucesso", "");
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
