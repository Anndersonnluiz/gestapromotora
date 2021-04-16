package br.com.deltafinanceira.managebean.contrato.simulacao;

import br.com.deltafinanceira.facade.BancoFacade;
import br.com.deltafinanceira.facade.SimulacaoContratoFacade;
import br.com.deltafinanceira.facade.TipoOperacaoFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Banco;
import br.com.deltafinanceira.model.Simulacaocontrato;
import br.com.deltafinanceira.model.Tipooperacao;
import br.com.deltafinanceira.model.Usuario;
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
public class SimulacaoMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Inject
  private UsuarioLogadoMB usuarioLogadoMB;
  
  private List<Simulacaocontrato> listaSimulacao;
  
  private boolean unicoUsuario;
  
  private Usuario usuario;
  
  private List<Usuario> listaUsuario;
  
  private String nomeCliente;
  
  private String cpf;
  
  private Tipooperacao tipooperacao;
  
  private List<Tipooperacao> listaTipoOperacao;
  
  private Banco banco;
  
  private List<Banco> listaBanco;
  
  @PostConstruct
  public void init() {
    gerarListaUsuario();
    gerarListaTipoOperacao();
    gerarListaBanco();
    gerarListaSimulacao();
    if (!this.usuarioLogadoMB.getUsuario().isAcessogeral() && 
      !this.usuarioLogadoMB.getUsuario().isSupervisao()) {
      this.unicoUsuario = true;
      this.usuario = this.usuarioLogadoMB.getUsuario();
    } 
  }
  
  public synchronized List<Simulacaocontrato> getListaSimulacao() {
    return this.listaSimulacao;
  }
  
  public synchronized void setListaSimulacao(List<Simulacaocontrato> listaSimulacao) {
    this.listaSimulacao = listaSimulacao;
  }
  
  public UsuarioLogadoMB getUsuarioLogadoMB() {
    return this.usuarioLogadoMB;
  }
  
  public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
    this.usuarioLogadoMB = usuarioLogadoMB;
  }
  
  public boolean isUnicoUsuario() {
    return this.unicoUsuario;
  }
  
  public void setUnicoUsuario(boolean unicoUsuario) {
    this.unicoUsuario = unicoUsuario;
  }
  
  public Usuario getUsuario() {
    return this.usuario;
  }
  
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
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
  
  public Tipooperacao getTipooperacao() {
    return this.tipooperacao;
  }
  
  public void setTipooperacao(Tipooperacao tipooperacao) {
    this.tipooperacao = tipooperacao;
  }
  
  public List<Tipooperacao> getListaTipoOperacao() {
    return this.listaTipoOperacao;
  }
  
  public void setListaTipoOperacao(List<Tipooperacao> listaTipoOperacao) {
    this.listaTipoOperacao = listaTipoOperacao;
  }
  
  public Banco getBanco() {
    return this.banco;
  }
  
  public void setBanco(Banco banco) {
    this.banco = banco;
  }
  
  public List<Banco> getListaBanco() {
    return this.listaBanco;
  }
  
  public void setListaBanco(List<Banco> listaBanco) {
    this.listaBanco = listaBanco;
  }
  
  public void gerarListaSimulacao() {
    SimulacaoContratoFacade simulacaoContratoFacade = new SimulacaoContratoFacade();
    String sql = "Select s From Simulacaocontrato s WHERE s.contrato.simulacao=true";
    if (!this.usuarioLogadoMB.getUsuario().isAcessogeral() || 
      !this.usuarioLogadoMB.getUsuario().isResponsaveldepartamento())
      sql = String.valueOf(sql) + " and s.contrato.usuario.idusuario=" + this.usuarioLogadoMB.getUsuario().getIdusuario(); 
    sql = sql + " ORDER BY s.idsimulacaocontrato DESC";
    this.listaSimulacao = simulacaoContratoFacade.lista(sql);
  }
  
  public String novo() {
    return "cadSimulacaoContrato";
  }
  
  public String editar(Simulacaocontrato simulacaocontrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("simulacaocontrato", simulacaocontrato);
    return "cadSimulacaoContrato";
  }
  
  public String imprimirFicha(Simulacaocontrato simulacaocontrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("simulacaocontrato", simulacaocontrato);
    return "fichaSimulacao";
  }
  
  public void gerarListaUsuario() {
    UsuarioFacade usuarioFacade = new UsuarioFacade();
    this.listaUsuario = usuarioFacade.listar("Select u From Usuario u Where u.treinamento=false order by u.nome");
    if (this.listaUsuario == null)
      this.listaUsuario = new ArrayList<>(); 
  }
  
  public void pesquisar() {
    String sql = "Select s From Simulacaocontrato s WHERE  s.contrato.cliente.nome like '%" + 
      this.nomeCliente + "%' and s.contrato.usuario.treinamento=false and s.contrato.cliente.cpf like '%" + this.cpf + "%'";
    if (this.usuario != null && this.usuario.getIdusuario() != null && 
      !this.usuarioLogadoMB.getUsuario().getTipocolaborador().getDescricao().equalsIgnoreCase("Operacional"))
      sql = String.valueOf(sql) + " and s.contrato.usuario.idusuario=" + this.usuario.getIdusuario(); 
    if (this.tipooperacao != null && this.tipooperacao.getIdtipooperacao() != null)
      sql = String.valueOf(sql) + " and s.contrato.tipooperacao.idtipooperacao=" + this.tipooperacao.getIdtipooperacao(); 
    if (this.banco != null && this.banco.getIdbanco() != null)
      sql = String.valueOf(sql) + " and s.contrato.banco.idbanco=" + this.banco.getIdbanco(); 
    sql = String.valueOf(sql) + " ORDER BY s.idsimulacaocontrato DESC";
    SimulacaoContratoFacade simulacaoContratoFacade = new SimulacaoContratoFacade();
    this.listaSimulacao = simulacaoContratoFacade.lista(sql);
    if (this.listaSimulacao == null)
      this.listaSimulacao = new ArrayList<>(); 
  }
  
  public void limpar() {
    gerarListaSimulacao();
    this.usuario = null;
    this.nomeCliente = "";
    this.cpf = "";
    this.tipooperacao = null;
    this.banco = null;
  }
  
  public void gerarListaTipoOperacao() {
    TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
    this.listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t Where t.ativo=true");
    if (this.listaTipoOperacao == null)
      this.listaTipoOperacao = new ArrayList<>(); 
  }
  
  public void gerarListaBanco() {
    BancoFacade bancoFacade = new BancoFacade();
    this.listaBanco = bancoFacade.lista("Select b From Banco b Where b.nome !='Nenhum' ORDER BY b.nome");
    if (this.listaBanco == null)
      this.listaBanco = new ArrayList<>(); 
  }
}
