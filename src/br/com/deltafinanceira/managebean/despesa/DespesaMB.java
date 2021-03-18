package br.com.deltafinanceira.managebean.despesa;

import br.com.deltafinanceira.dao.TipoDespesaDao;
import br.com.deltafinanceira.model.Tipodespesa;
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
public class DespesaMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private List<Tipodespesa> listaTipoDespesa;
  
  @PostConstruct
  public void init() {
    gerarListaDespesa();
  }
  
  public List<Tipodespesa> getListaTipoDespesa() {
    return this.listaTipoDespesa;
  }
  
  public void setListaTipoDespesa(List<Tipodespesa> listaTipoDespesa) {
    this.listaTipoDespesa = listaTipoDespesa;
  }
  
  public String novo() {
    return "cadDespesa";
  }
  
  public String editar(Tipodespesa tipodespesa) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("tipodespesa", tipodespesa);
    return "cadDespesa";
  }
  
  public void gerarListaDespesa() {
    TipoDespesaDao tipoDespesaDao = new TipoDespesaDao();
    this.listaTipoDespesa = tipoDespesaDao.lista("Select t From Tipodespesa t");
    if (this.listaTipoDespesa == null)
      this.listaTipoDespesa = new ArrayList<>(); 
  }
}
