package br.com.deltafinanceira.managebean.banco;

import br.com.deltafinanceira.facade.OrgaoBancoFacade;
import br.com.deltafinanceira.model.Banco;
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
public class OrgaoBancoMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private List<OrgaoBanco> listaOrgao;
  
  private Banco banco;
  
  private OrgaoBanco orgaobanco;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.banco = (Banco)session.getAttribute("banco");
    this.orgaobanco = (OrgaoBanco)session.getAttribute("orgaobanco");
    session.removeAttribute("banco");
    session.removeAttribute("orgaobanco");
    if (this.banco == null)
      this.banco = this.orgaobanco.getBanco(); 
    gerarListaOrgao();
  }
  
  public List<OrgaoBanco> getListaOrgao() {
    return this.listaOrgao;
  }
  
  public void setListaOrgao(List<OrgaoBanco> listaOrgao) {
    this.listaOrgao = listaOrgao;
  }
  
  public Banco getBanco() {
    return this.banco;
  }
  
  public void setBanco(Banco banco) {
    this.banco = banco;
  }
  
  public void gerarListaOrgao() {
    OrgaoBancoFacade orgaoBancoFacade = new OrgaoBancoFacade();
    if (this.banco != null) {
      this.listaOrgao = orgaoBancoFacade.lista("Select o From OrgaoBanco o Where o.banco.idbanco=" + this.banco.getIdbanco() + 
          " ORDER BY o.nome");
      if (this.listaOrgao == null)
        this.listaOrgao = new ArrayList<>(); 
    } 
  }
  
  public String novoOrgao() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("banco", this.banco);
    return "cadOrgaoBanco";
  }
  
  public String editar(OrgaoBanco orgaoBanco) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("banco", this.banco);
    session.setAttribute("orgaobanco", orgaoBanco);
    return "cadOrgaoBanco";
  }
  
  public String voltar() {
    return "consBanco";
  }
  
  public String voltarRoteiro() {
    return "consBancoRoteiro";
  }
  
  public String consultaCoeficiente(OrgaoBanco orgaoBanco) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("orgaobanco", orgaoBanco);
    return "consCoeficiente";
  }
  
  public String inserirLink(OrgaoBanco orgaoBanco) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("orgaobanco", orgaoBanco);
    return "cadRoteiro";
  }
}
