package br.com.deltafinanceira.managebean.banco;

import br.com.deltafinanceira.facade.CoeficienteFacade;
import br.com.deltafinanceira.facade.TipoOperacaoFacade;
import br.com.deltafinanceira.model.Coeficiente;
import br.com.deltafinanceira.model.OrgaoBanco;
import br.com.deltafinanceira.model.Tipooperacao;
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
public class CadCoeficienteMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private OrgaoBanco orgaoBanco;
  
  private Coeficiente coeficiente;
  
  private List<Tipooperacao> listaTipoOperacao;
  
  private Tipooperacao tipooiperacao;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.orgaoBanco = (OrgaoBanco)session.getAttribute("orgaobanco");
    this.coeficiente = (Coeficiente)session.getAttribute("coeficiente");
    session.removeAttribute("coeficiente");
    session.removeAttribute("orgaobanco");
    gerarListaTipoOperacao();
    if (this.coeficiente == null) {
      this.coeficiente = new Coeficiente();
    } else {
      this.tipooiperacao = this.coeficiente.getTipooperacao();
    } 
  }
  
  public OrgaoBanco getOrgaoBanco() {
    return this.orgaoBanco;
  }
  
  public void setOrgaoBanco(OrgaoBanco orgaoBanco) {
    this.orgaoBanco = orgaoBanco;
  }
  
  public Coeficiente getCoeficiente() {
    return this.coeficiente;
  }
  
  public void setCoeficiente(Coeficiente coeficiente) {
    this.coeficiente = coeficiente;
  }
  
  public List<Tipooperacao> getListaTipoOperacao() {
    return this.listaTipoOperacao;
  }
  
  public void setListaTipoOperacao(List<Tipooperacao> listaTipoOperacao) {
    this.listaTipoOperacao = listaTipoOperacao;
  }
  
  public Tipooperacao getTipooiperacao() {
    return this.tipooiperacao;
  }
  
  public void setTipooiperacao(Tipooperacao tipooiperacao) {
    this.tipooiperacao = tipooiperacao;
  }
  
  public String cancelar() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("orgaobanco", this.orgaoBanco);
    return "consCoeficiente";
  }
  
  public String salvar() {
    CoeficienteFacade coeficienteFacade = new CoeficienteFacade();
    this.coeficiente.setComissaototal(this.coeficiente.getComissaoloja() 
    		+ this.coeficiente.getComissaocorretor());
    this.coeficiente.setOrgaoBanco(this.orgaoBanco);
    this.coeficiente.setTipooperacao(this.tipooiperacao);
    this.coeficiente = coeficienteFacade.salvar(this.coeficiente);
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("orgaobanco", this.orgaoBanco);
    return "consCoeficiente";
  }
  
  public void gerarListaTipoOperacao() {
    TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
    this.listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t");
    if (this.listaTipoOperacao == null)
      this.listaTipoOperacao = new ArrayList<>(); 
  }
}
