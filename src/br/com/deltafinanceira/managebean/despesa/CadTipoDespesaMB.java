package br.com.deltafinanceira.managebean.despesa;

import br.com.deltafinanceira.dao.TipoDespesaDao;
import br.com.deltafinanceira.model.Tipodespesa;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class CadTipoDespesaMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Tipodespesa tipodespesa;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.tipodespesa = (Tipodespesa)session.getAttribute("tipodespesa");
    session.removeAttribute("tipodespesa");
    if (this.tipodespesa == null)
      this.tipodespesa = new Tipodespesa(); 
  }
  
  public Tipodespesa getTipodespesa() {
    return this.tipodespesa;
  }
  
  public void setTipodespesa(Tipodespesa tipodespesa) {
    this.tipodespesa = tipodespesa;
  }
  
  public String cancelar() {
    return "consDespesa";
  }
  
  public String salvar() {
    TipoDespesaDao tipoDespesaDao = new TipoDespesaDao();
    tipoDespesaDao.salvar(this.tipodespesa);
    return "consDespesa";
  }
}
