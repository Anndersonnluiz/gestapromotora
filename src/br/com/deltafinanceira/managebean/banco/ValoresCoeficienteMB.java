package br.com.deltafinanceira.managebean.banco;

import br.com.deltafinanceira.facade.ValoresCoeficienteFacade;
import br.com.deltafinanceira.model.Coeficiente;
import br.com.deltafinanceira.model.Valorescoeficiente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class ValoresCoeficienteMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Coeficiente coeficiente;
  
  private List<Valorescoeficiente> listaValoresCoeficiente;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.coeficiente = (Coeficiente)session.getAttribute("coeficiente");
    session.removeAttribute("coeficiente");
    gerarListaValores();
  }
  
  public Coeficiente getCoeficiente() {
    return this.coeficiente;
  }
  
  public void setCoeficiente(Coeficiente coeficiente) {
    this.coeficiente = coeficiente;
  }
  
  public List<Valorescoeficiente> getListaValoresCoeficiente() {
    return this.listaValoresCoeficiente;
  }
  
  public void setListaValoresCoeficiente(List<Valorescoeficiente> listaValoresCoeficiente) {
    this.listaValoresCoeficiente = listaValoresCoeficiente;
  }
  
  public void gerarListaValores() {
    ValoresCoeficienteFacade valoresCoeficienteFacade = new ValoresCoeficienteFacade();
    this.listaValoresCoeficiente = valoresCoeficienteFacade.lista("Select v From Valorescoeficiente v WHERE v.coeficiente.idcoeficiente=" + this.coeficiente.getIdcoeficiente());
    if (this.listaValoresCoeficiente == null)
      this.listaValoresCoeficiente = new ArrayList<>(); 
  }
  
  public String voltar() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("orgaobanco", this.coeficiente.getOrgaoBanco());
    return "consCoeficiente";
  }
  
  public String novosValores() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("coeficiente", this.coeficiente);
    return "cadValoresCoeficiente";
  }
  
  public String editar(Valorescoeficiente valorescoeficiente) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("valorescoeficiente", valorescoeficiente);
    session.setAttribute("coeficiente", this.coeficiente);
    return "cadValoresCoeficiente";
  }
}
