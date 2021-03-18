package br.com.deltafinanceira.managebean.cliente;

import br.com.deltafinanceira.facade.ClienteFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Cliente;
import br.com.deltafinanceira.model.Regrascoeficiente;
import br.com.deltafinanceira.model.Usuario;
import br.com.deltafinanceira.util.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class TrocaClienteMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private List<Usuario> listaUsuario;
  
  private Usuario usuario;
  
  private Cliente cliente;
  
  private String senha;
  
  private Regrascoeficiente regracoeficiente;
  
  private Usuario usuarioAtual;
  
  private boolean selecionarTodos;
  
  private List<Cliente> listaCliente;
  
  @PostConstruct
  public void init() {
    gerarListaUsuario();
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
  
  public Regrascoeficiente getRegracoeficiente() {
    return this.regracoeficiente;
  }
  
  public void setRegracoeficiente(Regrascoeficiente regracoeficiente) {
    this.regracoeficiente = regracoeficiente;
  }
  
  public Usuario getUsuarioAtual() {
    return this.usuarioAtual;
  }
  
  public void setUsuarioAtual(Usuario usuarioAtual) {
    this.usuarioAtual = usuarioAtual;
  }
  
  public boolean isSelecionarTodos() {
    return this.selecionarTodos;
  }
  
  public void setSelecionarTodos(boolean selecionarTodos) {
    this.selecionarTodos = selecionarTodos;
  }
  
  public Cliente getCliente() {
    return this.cliente;
  }
  
  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }
  
  public String getSenha() {
    return this.senha;
  }
  
  public void setSenha(String senha) {
    this.senha = senha;
  }
  
  public List<Cliente> getListaCliente() {
    return this.listaCliente;
  }
  
  public void setListaCliente(List<Cliente> listaCliente) {
    this.listaCliente = listaCliente;
  }
  
  public void gerarListaUsuario() {
    UsuarioFacade usuarioFacade = new UsuarioFacade();
    this.listaUsuario = usuarioFacade.listar("Select u From Usuario u order by u.nome");
    if (this.listaUsuario == null)
      this.listaUsuario = new ArrayList<>(); 
  }
  
  public String salvar() {
    ClienteFacade clienteFacade = new ClienteFacade();
    if (this.usuario != null) {
      for (int i = 0; i < this.listaCliente.size(); i++) {
        if (((Cliente)this.listaCliente.get(i)).isSelecionado()) {
          ((Cliente)this.listaCliente.get(i)).setUsuario(this.usuario);
          clienteFacade.salvar(this.listaCliente.get(i));
        } 
      } 
      buscarCliente();
    } else {
      Mensagem.lancarMensagemInfo("Informe o novo titular dos contratos", "");
      return "";
    } 
    return "";
  }
  
  public String cancelar() {
    return "dashboard";
  }
  
  public void buscarCliente() {
    if (this.usuarioAtual != null) {
      ClienteFacade clienteFacade = new ClienteFacade();
      this.listaCliente = clienteFacade.lista("SELECT c From Cliente c WHERE c.usuario.idusuario=" + 
          this.usuarioAtual.getIdusuario());
      if (this.listaCliente == null)
        this.listaCliente = new ArrayList<>(); 
    } 
  }
  
  public void selecionarTodosCliente() {
    if (this.selecionarTodos) {
      this.selecionarTodos = false;
    } else {
      this.selecionarTodos = true;
    } 
    for (int i = 0; i < this.listaCliente.size(); i++)
      ((Cliente)this.listaCliente.get(i)).setSelecionado(this.selecionarTodos); 
  }
}
