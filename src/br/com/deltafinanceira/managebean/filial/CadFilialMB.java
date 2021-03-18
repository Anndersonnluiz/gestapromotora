package br.com.deltafinanceira.managebean.filial;

import br.com.deltafinanceira.facade.FilialFacade;
import br.com.deltafinanceira.model.Filial;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class CadFilialMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Filial filial;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.filial = (Filial)session.getAttribute("filial");
    session.removeAttribute("filial");
    if (this.filial == null) {
      this.filial = new Filial();
      this.filial.setAtivo(true);
    } 
  }
  
  public Filial getFilial() {
    return this.filial;
  }
  
  public void setFilial(Filial filial) {
    this.filial = filial;
  }
  
  public String cancelar() {
    return "consFilial";
  }
  
  public String salvar() {
    FilialFacade filialFacade = new FilialFacade();
    this.filial = filialFacade.salvar(this.filial);
    return "consFilial";
  }
}
