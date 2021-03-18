package br.com.deltafinanceira.managebean.banco;

import br.com.deltafinanceira.facade.BancoFacade;
import br.com.deltafinanceira.model.Banco;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class CadBancoMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Banco banco;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.banco = (Banco)session.getAttribute("banco");
    session.removeAttribute("banco");
    if (this.banco == null)
      this.banco = new Banco(); 
  }
  
  public Banco getBanco() {
    return this.banco;
  }
  
  public void setBanco(Banco banco) {
    this.banco = banco;
  }
  
  public String salvar() {
    BancoFacade bancoFacade = new BancoFacade();
    this.banco = bancoFacade.salvar(this.banco);
    return "consBanco";
  }
  
  public String cancelar() {
    return "consBanco";
  }
}
