package br.com.deltafinanceira.managebean.filial;

import br.com.deltafinanceira.facade.DepartamentoFacade;
import br.com.deltafinanceira.facade.FilialFacade;
import br.com.deltafinanceira.model.Departamento;
import br.com.deltafinanceira.model.Filial;
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
public class FilialMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private List<Filial> listaFiliais;
  
  @PostConstruct
  public void init() {
    gerarListaFiliais();
  }
  
  public List<Filial> getListaFiliais() {
    return this.listaFiliais;
  }
  
  public void setListaFiliais(List<Filial> listaFiliais) {
    this.listaFiliais = listaFiliais;
  }
  
  public String novaFilial() {
    return "cadFilial";
  }
  
  public String editar(Filial filial) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("filial", filial);
    return "cadFilial";
  }
  
  public int gerarNumeroDepartamento(Filial filial) {
    DepartamentoFacade departamentoFacade = new DepartamentoFacade();
    List<Departamento> lisDepartamentos = departamentoFacade.lista("Select d From Departamento d Where d.filial.idfilial=" + 
        filial.getIdfilial());
    if (lisDepartamentos == null)
      lisDepartamentos = new ArrayList<>(); 
    return lisDepartamentos.size();
  }
  
  public String acessarDepartamento(Filial filial) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("filial", filial);
    return "consDepartamento";
  }
  
  public void gerarListaFiliais() {
    FilialFacade filialFacade = new FilialFacade();
    this.listaFiliais = filialFacade.lista("Select f From Filial f");
    if (this.listaFiliais == null)
      this.listaFiliais = new ArrayList<>(); 
  }
}
