package br.com.deltafinanceira.managebean.banco;

import br.com.deltafinanceira.facade.CoeficienteFacade;
import br.com.deltafinanceira.model.Coeficiente;
import br.com.deltafinanceira.model.OrgaoBanco;
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
public class CoeficienteMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private OrgaoBanco orgaobanco;
  
  private List<Coeficiente> listaCoeficiente;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.orgaobanco = (OrgaoBanco)session.getAttribute("orgaobanco");
    session.removeAttribute("orgaobanco");
    gerarListaCoeficiente();
  }
  
  public OrgaoBanco getOrgaobanco() {
    return this.orgaobanco;
  }
  
  public void setOrgaobanco(OrgaoBanco orgaobanco) {
    this.orgaobanco = orgaobanco;
  }
  
  public List<Coeficiente> getListaCoeficiente() {
    return this.listaCoeficiente;
  }
  
  public void setListaCoeficiente(List<Coeficiente> listaCoeficiente) {
    this.listaCoeficiente = listaCoeficiente;
  }
  
  public void gerarListaCoeficiente() {
    CoeficienteFacade coeficienteFacade = new CoeficienteFacade();
    this.listaCoeficiente = coeficienteFacade.lista("Select c From Coeficiente c WHERE c.orgaoBanco.idorgaobanco=" + this.orgaobanco.getIdorgaobanco());
    if (this.listaCoeficiente == null)
      this.listaCoeficiente = new ArrayList<>(); 
  }
  
  public String voltar() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("orgaobanco", this.orgaobanco);
    return "consOrgaoBanco";
  }
  
  public String novoCoeficiente() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("orgaobanco", this.orgaobanco);
    return "cadCoeficiente";
  }
  
  public String editar(Coeficiente coeficiente) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("orgaobanco", this.orgaobanco);
    session.setAttribute("coeficiente", coeficiente);
    return "cadCoeficiente";
  }
  
  public String novosValores(Coeficiente coeficiente) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("coeficiente", coeficiente);
    return "consValoresCoeficiente";
  }
  
  public String novaImportacao() {
	    FacesContext fc = FacesContext.getCurrentInstance();
	    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
	    session.setAttribute("orgaobanco", this.orgaobanco);
	    return "importacaoCoeficiente";
	  }
}
