package br.com.deltafinanceira.managebean.contrato;

import br.com.deltafinanceira.facade.BancoFacade;
import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.facade.PromotoraFacade;
import br.com.deltafinanceira.facade.SituacaoFacade;
import br.com.deltafinanceira.facade.TipoOperacaoFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Banco;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Promotora;
import br.com.deltafinanceira.model.Situacao;
import br.com.deltafinanceira.model.Tipooperacao;
import br.com.deltafinanceira.model.Usuario;
import br.com.deltafinanceira.util.Mensagem;
import br.com.deltafinanceira.util.UsuarioLogadoMB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class ContratoMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Inject
  private UsuarioLogadoMB usuarioLogadoMB;
  
  private List<Contrato> listaContrato;
  
  private List<Usuario> listaUsuario;
  
  private String nomeCliente;
  
  private String cpf;
  
  private Usuario usuario;
  
  private List<Tipooperacao> listaTipoOperacao;
  
  private Tipooperacao tipooiperacao;
  
  private List<Banco> listaBanco;
  
  private Banco banco;
  
  private List<Situacao> listaSituacao;
  
  private Situacao situacao;
  
  private boolean unicoUsuario;
  
  private List<Promotora> listaPromotora;
  
  private Promotora promotora;
  
  @PostConstruct
  public void init() {
    gerarListaUsuario();
    gerarListaTipoOperacao();
    gerarListaBanco();
    gerarListaSituacao();
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
  
  public Situacao getSituacao() {
    return this.situacao;
  }
  
  public void setSituacao(Situacao situacao) {
    this.situacao = situacao;
  }
  
  public boolean isUnicoUsuario() {
    return this.unicoUsuario;
  }
  
  public void setUnicoUsuario(boolean unicoUsuario) {
    this.unicoUsuario = unicoUsuario;
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
  
  public void gerarListaContrato() {
    ContratoFacade contratoFacade = new ContratoFacade();
    String sql = "Select c From Contrato c WHERE c.situacao.identificador<>6 ";
    if (!this.usuarioLogadoMB.getUsuario().isAcessogeral() && 
      !this.usuarioLogadoMB.getUsuario().getTipocolaborador().getDescricao().equalsIgnoreCase("Operacional"))
      sql = String.valueOf(sql) + " AND c.usuario.idusuario=" + this.usuarioLogadoMB.getUsuario().getIdusuario(); 
    sql = String.valueOf(sql) + " ORDER BY c.idcontrato DESC";
    this.listaContrato = contratoFacade.lista(sql);
    if (this.listaContrato == null)
      this.listaContrato = new ArrayList<>(); 
  }
  
  public String editar(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    session.setAttribute("orgaobanco", contrato.getOrgaoBanco());
    return "cadContrato";
  }
  
  public void bloquearEdicao(Contrato contrato) {
    if (contrato.isBloqueio()) {
      contrato.setBloqueio(false);
      contrato.setDescricaobloqueio("unlock");
      Mensagem.lancarMensagemInfo("Desbloqueio de contrato feito com sucesso", "");
    } else {
      contrato.setBloqueio(true);
      contrato.setDescricaobloqueio("lock");
      Mensagem.lancarMensagemInfo("Bloqueio de contrato feito com sucesso", "");
    } 
    ContratoFacade contratoFacade = new ContratoFacade();
    contrato = contratoFacade.salvar(contrato);
  }
  
  public void digitarEdicao(Contrato contrato) {
    if (contrato.isDigitado()) {
      contrato.setDigitado(false);
      contrato.setDescricaodigitado("file");
      Mensagem.lancarMensagemInfo("Desbloqueio de contrato desfeita com sucesso", "");
    } else {
      contrato.setDigitado(true);
      contrato.setDescricaodigitado("file-text");
      Mensagem.lancarMensagemInfo("Digitade contrato feito com sucesso", "");
    } 
    ContratoFacade contratoFacade = new ContratoFacade();
    contrato = contratoFacade.salvar(contrato);
  }
  
  public String malote(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    return "cadMaloteContrato";
  }
  
  public String historicopendencia(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    return "cadHistoricoPendencia";
  }
  
  public void entregueFisico(Contrato contrato) {
    if (contrato.isFisico()) {
      contrato.setFisico(false);
      contrato.setDescricaofisico("x-circle");
      Mensagem.lancarMensagemInfo("Contrato nentregue", "");
    } else {
      contrato.setFisico(true);
      contrato.setDescricaofisico("check");
      Mensagem.lancarMensagemInfo("Contrato Entregue", "");
    } 
    ContratoFacade contratoFacade = new ContratoFacade();
    contrato = contratoFacade.salvar(contrato);
  }
  
  public String trocatTitular(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    return "trocarTitular";
  }
  
  public String financeiro(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    return "consFinanceiro";
  }
  
  public void gerarListaUsuario() {
    UsuarioFacade usuarioFacade = new UsuarioFacade();
    String sql = "Select u From Usuario u WHERE u.ativo=true and u.treinamento=false";
    sql = String.valueOf(sql) + " and u.departamento.iddepartamento=7 order by u.nome";
    this.listaUsuario = usuarioFacade.listar(sql);
    if (this.listaUsuario == null)
      this.listaUsuario = new ArrayList<>(); 
  }
  
  public void pesquisar() {
    String sql = "Select c From Contrato c WHERE  c.cliente.nome like '%" + 
      this.nomeCliente + "%' and c.usuario.treinamento=false and c.cliente.cpf like '%" + this.cpf + "%'";
    if (this.usuario != null && this.usuario.getIdusuario() != null && 
      !this.usuarioLogadoMB.getUsuario().getTipocolaborador().getDescricao().equalsIgnoreCase("Operacional"))
      sql = String.valueOf(sql) + " and c.usuario.idusuario=" + this.usuario.getIdusuario(); 
    if (this.tipooiperacao != null && this.tipooiperacao.getIdtipooperacao() != null)
      sql = String.valueOf(sql) + " and c.tipooperacao.idtipooperacao=" + this.tipooiperacao.getIdtipooperacao(); 
    if (this.banco != null && this.banco.getIdbanco() != null)
      sql = String.valueOf(sql) + " and c.banco.idbanco=" + this.banco.getIdbanco(); 
    if (this.situacao != null && this.situacao.getIdsituacao() != null)
      sql = String.valueOf(sql) + " and c.situacao.idsituacao=" + this.situacao.getIdsituacao(); 
    if (this.promotora != null && this.promotora.getIdpromotora() != null)
      sql = String.valueOf(sql) + " and c.promotora.idpromotora=" + this.promotora.getIdpromotora(); 
    sql = String.valueOf(sql) + " ORDER BY c.idcontrato DESC";
    ContratoFacade contratoFacade = new ContratoFacade();
    this.listaContrato = contratoFacade.lista(sql);
    if (this.listaContrato == null)
      this.listaContrato = new ArrayList<>(); 
  }
  
  public void limpar() {
    gerarListaContrato();
    this.usuario = null;
    this.nomeCliente = "";
    this.cpf = "";
    this.tipooiperacao = null;
    this.banco = null;
    this.situacao = null;
    this.promotora = null;
  }
  
  public String anexarArquivo(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    session.setAttribute("voltarTela", "consContrato");
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
    this.listaBanco = bancoFacade.lista("Select b From Banco b Where b.visualizar=true ORDER BY b.nome");
    if (this.listaBanco == null)
      this.listaBanco = new ArrayList<>(); 
  }
  
  public String historicoContrato(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    return "linhaTempoContrato";
  }
  
  public String imprimirFicha(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    session.setAttribute("voltar", "consContrato");
    return "fichaContrato";
  }
  
  public void detalheSituacao(Contrato contrato) {
    Mensagem.lancarMensagemInfo("Situado Contrato:", contrato.getDetalhesituacao());
  }
  
  public String alterarSituacao(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    session.setAttribute("voltar", "consContrato");
    return "alterarSituacao";
  }
  
  public void gerarListaPromotora() {
    PromotoraFacade promotoraFacade = new PromotoraFacade();
    this.listaPromotora = promotoraFacade.lista("Select p From Promotora p");
    if (this.listaPromotora == null)
      this.listaPromotora = new ArrayList<>(); 
  }
}
