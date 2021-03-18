package br.com.deltafinanceira.managebean.promotora;

import br.com.deltafinanceira.facade.PromotoraFacade;
import br.com.deltafinanceira.model.Promotora;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class CadPromotoraMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Promotora promotora;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.promotora = (Promotora)session.getAttribute("promotora");
    session.removeAttribute("promotora");
    if (this.promotora == null)
      this.promotora = new Promotora(); 
  }
  
  public Promotora getPromotora() {
    return this.promotora;
  }
  
  public void setPromotora(Promotora promotora) {
    this.promotora = promotora;
  }
  
  public String cancelar() {
    return "consPromotora";
  }
  
  public String salvar() {
    PromotoraFacade promotoraFacade = new PromotoraFacade();
    promotoraFacade.salvar(this.promotora);
    return "consPromotora";
  }
}
