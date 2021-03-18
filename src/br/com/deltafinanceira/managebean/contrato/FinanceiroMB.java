package br.com.deltafinanceira.managebean.contrato;

import br.com.deltafinanceira.facade.FinanceiroContratoFacade;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Financeirocontrato;
import br.com.deltafinanceira.util.UsuarioLogadoMB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class FinanceiroMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Inject
  private UsuarioLogadoMB usuarioLogadoMB;
  
  private Contrato contrato;
  
  private Financeirocontrato financeirocontrato;
  
  private List<Financeirocontrato> listaFinanceiro;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.contrato = (Contrato)session.getAttribute("contrato");
    session.removeAttribute("contrato");
    this.financeirocontrato = new Financeirocontrato();
    gerarListaFinanceiro();
  }
  
  public UsuarioLogadoMB getUsuarioLogadoMB() {
    return this.usuarioLogadoMB;
  }
  
  public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
    this.usuarioLogadoMB = usuarioLogadoMB;
  }
  
  public Contrato getContrato() {
    return this.contrato;
  }
  
  public void setContrato(Contrato contrato) {
    this.contrato = contrato;
  }
  
  public Financeirocontrato getFinanceirocontrato() {
    return this.financeirocontrato;
  }
  
  public void setFinanceirocontrato(Financeirocontrato financeirocontrato) {
    this.financeirocontrato = financeirocontrato;
  }
  
  public List<Financeirocontrato> getListaFinanceiro() {
    return this.listaFinanceiro;
  }
  
  public void setListaFinanceiro(List<Financeirocontrato> listaFinanceiro) {
    this.listaFinanceiro = listaFinanceiro;
  }
  
  public String voltar() {
    return "consContrato";
  }
  
  public void salvarFinanceiro() {
    FinanceiroContratoFacade financeiroContratoFacade = new FinanceiroContratoFacade();
    this.financeirocontrato.setContrato(this.contrato);
    this.financeirocontrato.setUsuario(this.usuarioLogadoMB.getUsuario());
    this.financeirocontrato = financeiroContratoFacade.salvar(this.financeirocontrato);
    this.listaFinanceiro.add(this.financeirocontrato);
    this.financeirocontrato = new Financeirocontrato();
  }
  
  public void gerarListaFinanceiro() {
    FinanceiroContratoFacade financeiroContratoFacade = new FinanceiroContratoFacade();
    this.listaFinanceiro = financeiroContratoFacade
      .lista("Select f From Financeirocontrato f WHERE f.contrato.idcontrato=" + this.contrato.getIdcontrato());
    if (this.listaFinanceiro == null)
      this.listaFinanceiro = new ArrayList<>(); 
  }
}
