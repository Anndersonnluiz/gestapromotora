package br.com.deltafinanceira.managebean.banco;

import br.com.deltafinanceira.facade.OrgaoBancoFacade;
import br.com.deltafinanceira.model.Banco;
import br.com.deltafinanceira.model.OrgaoBanco;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class CadOrgaoBancoMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Banco banco;
  
  private OrgaoBanco orgaoBanco;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.banco = (Banco)session.getAttribute("banco");
    this.orgaoBanco = (OrgaoBanco)session.getAttribute("orgaobanco");
    session.removeAttribute("banco");
    session.removeAttribute("orgaobanco");
    if (this.orgaoBanco == null)
      this.orgaoBanco = new OrgaoBanco(); 
  }
  
  public Banco getBanco() {
    return this.banco;
  }
  
  public void setBanco(Banco banco) {
    this.banco = banco;
  }
  
  public OrgaoBanco getOrgaoBanco() {
    return this.orgaoBanco;
  }
  
  public void setOrgaoBanco(OrgaoBanco orgaoBanco) {
    this.orgaoBanco = orgaoBanco;
  }
  
  public String cancelar() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("banco", this.banco);
    return "consOrgaoBanco";
  }
  
  public String salvar() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("banco", this.banco);
    OrgaoBancoFacade orgaoBancoFacade = new OrgaoBancoFacade();
    this.orgaoBanco.setBanco(this.banco);
    this.orgaoBanco = orgaoBancoFacade.salvar(this.orgaoBanco);
    return "consOrgaoBanco";
  }
}
