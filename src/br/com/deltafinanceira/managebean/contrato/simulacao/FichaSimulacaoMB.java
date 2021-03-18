package br.com.deltafinanceira.managebean.contrato.simulacao;

import br.com.deltafinanceira.model.Simulacaocontrato;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class FichaSimulacaoMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Simulacaocontrato simulacaocontrato;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.simulacaocontrato = (Simulacaocontrato)session.getAttribute("simulacaocontrato");
    session.removeAttribute("simulacaocontrato");
    if (this.simulacaocontrato == null)
      this.simulacaocontrato = new Simulacaocontrato(); 
  }
  
  public Simulacaocontrato getSimulacaocontrato() {
    return this.simulacaocontrato;
  }
  
  public void setSimulacaocontrato(Simulacaocontrato simulacaocontrato) {
    this.simulacaocontrato = simulacaocontrato;
  }
  
  public String voltar() {
    return "consSimulacaoContrato";
  }
}
