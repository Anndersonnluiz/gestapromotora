package br.com.deltafinanceira.managebean.cliente;

import br.com.deltafinanceira.model.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class RelatorioClienteMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private String corretor;
  
  private List<Cliente> listaCliente;
  
  @SuppressWarnings("unchecked")
@PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.listaCliente = (List<Cliente>)session.getAttribute("listaCliente");
    this.corretor = (String)session.getAttribute("corretor");
    session.removeAttribute("corretor");
    session.removeAttribute("listaCliente");
  }
  
  public String getCorretor() {
    return this.corretor;
  }
  
  public void setCorretor(String corretor) {
    this.corretor = corretor;
  }
  
  public List<Cliente> getListaCliente() {
    return this.listaCliente;
  }
  
  public void setListaCliente(List<Cliente> listaCliente) {
    this.listaCliente = listaCliente;
  }
  
  public String voltar() {
    return "consCliente";
  }
}
