package br.com.deltafinanceira.managebean.departamento;

import br.com.deltafinanceira.facade.DepartamentoFacade;
import br.com.deltafinanceira.model.Departamento;
import br.com.deltafinanceira.model.Filial;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class CadDepartamentoMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Departamento departamento;
  
  private Filial filial;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.departamento = (Departamento)session.getAttribute("departamento");
    this.filial = (Filial)session.getAttribute("filial");
    session.removeAttribute("filial");
    session.removeAttribute("departamento");
    if (this.departamento == null) {
      this.departamento = new Departamento();
      this.departamento.setNusuario(0);
      this.departamento.setFilial(this.filial);
    } 
  }
  
  public synchronized Departamento getDepartamento() {
    return this.departamento;
  }
  
  public synchronized void setDepartamento(Departamento departamento) {
    this.departamento = departamento;
  }
  
  public String cancelar() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("filial", this.filial);
    return "consDepartamento";
  }
  
  public String salvar() {
    DepartamentoFacade departamentoFacade = new DepartamentoFacade();
    this.departamento = departamentoFacade.salvar(this.departamento);
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("filial", this.filial);
    return "consDepartamento";
  }
}
