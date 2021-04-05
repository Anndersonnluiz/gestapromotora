package br.com.deltafinanceira.managebean.contrato.faturamento;

import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Contrato;
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
public class FaturamentoMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Inject
  private UsuarioLogadoMB usuarioLogadoMB;
  
  private List<Contrato> listaContrato;
  
  private List<Usuario> listaUsuario;
  
  private String nomeCliente;
  
  private String cpf;
  
  private Usuario usuario;
  
  @PostConstruct
  public void init() {
    gerarListaContrato();
    gerarListaUsuario();
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
  
  public void gerarListaContrato() {
    ContratoFacade contratoFacade = new ContratoFacade();
    String sql = "Select c From Contrato c";
    if (!this.usuarioLogadoMB.getUsuario().isAcessogeral() && 
      !this.usuarioLogadoMB.getUsuario().getTipocolaborador().getDescricao().equalsIgnoreCase("Operacional"))
      sql = String.valueOf(sql) + " WHERE c.usuario.idusuario=" + this.usuarioLogadoMB.getUsuario().getIdusuario(); 
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
    this.listaUsuario = usuarioFacade.listar("Select u From Usuario u Where u.treinamento=false order by u.nome");
    if (this.listaUsuario == null)
      this.listaUsuario = new ArrayList<>(); 
  }
  
  public void pesquisar() {
    String sql = "Select c From Contrato c WHERE c.cliente.nome like '%" + this.nomeCliente + "%' and c.cliente.cpf like '%" + this.cpf + "%'";
    if (this.usuario != null && this.usuario.getIdusuario() != null && 
      !this.usuarioLogadoMB.getUsuario().getTipocolaborador().getDescricao().equalsIgnoreCase("Operacional"))
      sql = String.valueOf(sql) + " and c.usuario.idusuario=" + this.usuario.getIdusuario(); 
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
  }
  
  public String anexarArquivo(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    return "anexarArquivo";
  }
}
