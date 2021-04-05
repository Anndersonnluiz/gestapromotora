package br.com.deltafinanceira.managebean.cliente;

import br.com.deltafinanceira.facade.ClienteFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Cliente;
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
public class ClienteMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private List<Cliente> listaCliente;
  
  private String nomeCliente;
  
  private String cpf;
  
  private Usuario usuario;
  
  private List<Usuario> listaUsuario;
  
  @Inject
  private UsuarioLogadoMB usuarioLogadoMB;
  
  private boolean verificarAcesso;
  
  @PostConstruct
  public void init() {
    if (!this.usuarioLogadoMB.getUsuario().isAcessogeral() && 
      !this.usuarioLogadoMB.getUsuario().isSupervisao()) {
      this.usuario = this.usuarioLogadoMB.getUsuario();
      this.verificarAcesso = false;
    } else {
      this.verificarAcesso = true;
    } 
    gerarListaCliente();
    gerarListaUsuario();
  }
  
  public List<Cliente> getListaCliente() {
    return this.listaCliente;
  }
  
  public void setListaCliente(List<Cliente> listaCliente) {
    this.listaCliente = listaCliente;
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
  
  public List<Usuario> getListaUsuario() {
    return this.listaUsuario;
  }
  
  public void setListaUsuario(List<Usuario> listaUsuario) {
    this.listaUsuario = listaUsuario;
  }
  
  public boolean isVerificarAcesso() {
    return this.verificarAcesso;
  }
  
  public void setVerificarAcesso(boolean verificarAcesso) {
    this.verificarAcesso = verificarAcesso;
  }
  
  public void gerarListaCliente() {
    ClienteFacade clienteFacade = new ClienteFacade();
    String sql = "Select c From Cliente c";
    if (this.usuario != null && this.usuario.getIdusuario() != null)
      sql = String.valueOf(sql) + " Where c.usuario.idusuario=" + this.usuario.getIdusuario(); 
    sql = String.valueOf(sql) + " ORDER BY c.nome, c.usuario.nome";
    this.listaCliente = clienteFacade.lista(sql);
    if (this.listaCliente == null)
      this.listaCliente = new ArrayList<>(); 
  }
  
  public String novoCliente() {
    return "cadCliente";
  }
  
  public String editar(Cliente cliente) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("cliente", cliente);
    return "cadCliente";
  }
  
  public void pesquisar() {
    String sql = "Select c From Cliente c WHERE c.nome like '%" + this.nomeCliente + "%' and c.cpf like '%" + this.cpf + "%'";
    if (this.usuario != null && this.usuario.getIdusuario() != null)
      sql = String.valueOf(sql) + " and c.usuario.idusuario=" + this.usuario.getIdusuario(); 
    sql = String.valueOf(sql) + " ORDER BY c.nome";
    ClienteFacade clienteFacade = new ClienteFacade();
    this.listaCliente = clienteFacade.lista(sql);
    if (this.listaCliente == null)
      this.listaCliente = new ArrayList<>(); 
  }
  
  public void limpar() {
    gerarListaCliente();
    this.nomeCliente = "";
    this.cpf = "";
    this.usuario = null;
  }
  
  public String historicoContrato(Cliente cliente) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("cliente", cliente);
    return "historicoContrato";
  }
  
  public void gerarListaUsuario() {
    UsuarioFacade usuarioFacade = new UsuarioFacade();
    String sql = "Select u From Usuario u WHERE u.ativo=true and u.treinamento=false";
    sql = String.valueOf(sql) + " and u.departamento.iddepartamento=7 order by u.nome";
    this.listaUsuario = usuarioFacade.listar(sql);
    if (this.listaUsuario == null)
      this.listaUsuario = new ArrayList<>(); 
  }
  
  public String relatorioCliente() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("listaCliente", this.listaCliente);
    String corretor = "";
    if (this.usuario != null && this.usuario.getIdusuario() != null) {
      corretor = this.usuario.getNome();
    } else {
      corretor = "Todos";
    } 
    session.setAttribute("corretor", corretor);
    return "relatorioCliente";
  }
  
  
  public String importar() {
	  return "importarCliente";
  }
}
