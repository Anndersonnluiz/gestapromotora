package br.com.deltafinanceira.managebean.banco;

import br.com.deltafinanceira.dao.RegrasCoeficienteDao;
import br.com.deltafinanceira.facade.RegrasCoeficienteFacade;
import br.com.deltafinanceira.facade.ValoresCoeficienteFacade;
import br.com.deltafinanceira.model.Coeficiente;
import br.com.deltafinanceira.model.Regrascoeficiente;
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
public class CadValoresCoeficienteMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Coeficiente coeficiente;
  
  private Valorescoeficiente valorescoeficiente;
  
  private Regrascoeficiente regrascoeficiente;
  
  private List<Regrascoeficiente> listaRegrasCoeficiente;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.coeficiente = (Coeficiente)session.getAttribute("coeficiente");
    this.valorescoeficiente = (Valorescoeficiente)session.getAttribute("valorescoeficiente");
    session.removeAttribute("coeficiente");
    session.removeAttribute("valorescoeficiente");
    if (this.coeficiente == null)
      this.coeficiente = this.valorescoeficiente.getCoeficiente(); 
    if (this.valorescoeficiente == null) {
      this.valorescoeficiente = new Valorescoeficiente();
      this.valorescoeficiente.setCoeficiente(this.coeficiente);
    } else {
      buscarRegrasCoeficiente();
    } 
    this.regrascoeficiente = new Regrascoeficiente();
  }
  
  public Coeficiente getCoeficiente() {
    return this.coeficiente;
  }
  
  public void setCoeficiente(Coeficiente coeficiente) {
    this.coeficiente = coeficiente;
  }
  
  public Valorescoeficiente getValorescoeficiente() {
    return this.valorescoeficiente;
  }
  
  public void setValorescoeficiente(Valorescoeficiente valorescoeficiente) {
    this.valorescoeficiente = valorescoeficiente;
  }
  
  public Regrascoeficiente getRegrascoeficiente() {
    return this.regrascoeficiente;
  }
  
  public void setRegrascoeficiente(Regrascoeficiente regrascoeficiente) {
    this.regrascoeficiente = regrascoeficiente;
  }
  
  public List<Regrascoeficiente> getListaRegrasCoeficiente() {
    return this.listaRegrasCoeficiente;
  }
  
  public void setListaRegrasCoeficiente(List<Regrascoeficiente> listaRegrasCoeficiente) {
    this.listaRegrasCoeficiente = listaRegrasCoeficiente;
  }
  
  public void buscarRegrasCoeficiente() {
    RegrasCoeficienteFacade regrasCoeficienteFacade = new RegrasCoeficienteFacade();
    this.listaRegrasCoeficiente = regrasCoeficienteFacade.lista("Select r From Regrascoeficiente r WHERE r.valorescoeficiente.idvalorescoeficiente=" + 
        this.valorescoeficiente.getIdvalorescoeficiente());
    if (this.listaRegrasCoeficiente == null)
      this.listaRegrasCoeficiente = new ArrayList<>(); 
  }
  
  public String salvar() {
    ValoresCoeficienteFacade valoresCoeficienteFacade = new ValoresCoeficienteFacade();
    this.valorescoeficiente = valoresCoeficienteFacade.salvar(this.valorescoeficiente);
    salvarRegras();
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("coeficiente", this.coeficiente);
    return "consValoresCoeficiente";
  }
  
  public String cancelar() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("coeficiente", this.coeficiente);
    return "consValoresCoeficiente";
  }
  
  public void salvarRegras() {
    RegrasCoeficienteFacade regrasCoeficienteFacade = new RegrasCoeficienteFacade();
    for (int i = 0; i < this.listaRegrasCoeficiente.size(); i++) {
      ((Regrascoeficiente)this.listaRegrasCoeficiente.get(i)).setValorescoeficiente(this.valorescoeficiente);
      regrasCoeficienteFacade.salvar(this.listaRegrasCoeficiente.get(i));
    } 
  }
  
  public void adicionarRegra() {
    if (this.listaRegrasCoeficiente == null || this.listaRegrasCoeficiente.isEmpty())
      this.listaRegrasCoeficiente = new ArrayList<>(); 
    this.listaRegrasCoeficiente.add(this.regrascoeficiente);
    for (int i = 0; i < this.listaRegrasCoeficiente.size(); i++)
      ((Regrascoeficiente)this.listaRegrasCoeficiente.get(i)).setContador(i); 
    this.regrascoeficiente = new Regrascoeficiente();
  }
  
  public void excluirRegra(String ilinha) {
    int linha = Integer.parseInt(ilinha);
    if (((Regrascoeficiente)this.listaRegrasCoeficiente.get(linha)).getIdregrascoeficiente() != null) {
      RegrasCoeficienteDao regrasCoeficienteDao = new RegrasCoeficienteDao();
      regrasCoeficienteDao.excluir(((Regrascoeficiente)this.listaRegrasCoeficiente.get(linha)).getIdregrascoeficiente().intValue());
    } 
    if (linha >= 0)
      this.listaRegrasCoeficiente.remove(linha); 
  }
}
