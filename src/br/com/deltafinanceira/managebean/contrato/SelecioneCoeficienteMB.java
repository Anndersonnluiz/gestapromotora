package br.com.deltafinanceira.managebean.contrato;

import br.com.deltafinanceira.facade.CoeficienteFacade;
import br.com.deltafinanceira.model.Coeficiente;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.OrgaoBanco;
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
public class SelecioneCoeficienteMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Contrato contrato;
  
  private List<Valorescoeficiente> listaValores;
  
  private List<Coeficiente> listaRegrasValores;
  
  private OrgaoBanco orgaobanco;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.contrato = (Contrato)session.getAttribute("contrato");
    this.orgaobanco = (OrgaoBanco)session.getAttribute("orgaobanco");
    session.removeAttribute("contrato");
    session.removeAttribute("orgaobanco");
    gerarListaValores();
  }
  
  public Contrato getContrato() {
    return this.contrato;
  }
  
  public void setContrato(Contrato contrato) {
    this.contrato = contrato;
  }
  
  public List<Valorescoeficiente> getListaValores() {
    return this.listaValores;
  }
  
  public void setListaValores(List<Valorescoeficiente> listaValores) {
    this.listaValores = listaValores;
  }
  
  
  
  public List<Coeficiente> getListaRegrasValores() {
	return listaRegrasValores;
}

public void setListaRegrasValores(List<Coeficiente> listaRegrasValores) {
	this.listaRegrasValores = listaRegrasValores;
}

public OrgaoBanco getOrgaobanco() {
	return orgaobanco;
}

public void setOrgaobanco(OrgaoBanco orgaobanco) {
	this.orgaobanco = orgaobanco;
}

public void gerarListaValores() {
    CoeficienteFacade coeficienteFacade = new CoeficienteFacade();
    this.listaRegrasValores = coeficienteFacade
      .lista("Select v From Coeficiente v WHERE v.orgaoBanco.idorgaobanco=" + 
        this.orgaobanco.getIdorgaobanco() + " AND v.tipooperacao.idtipooperacao=" + 
        this.contrato.getTipooperacao().getIdtipooperacao());
    if (this.listaRegrasValores == null)
      this.listaRegrasValores = new ArrayList<>(); 
  }
  
  public String selecionarCoeficiente(Coeficiente coeficiente) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.contrato.setValorescoeficiente(coeficiente.getIdcoeficiente().intValue());
    if (this.contrato.getTipooperacao() != null)
      if (this.contrato.getTipooperacao().isMargem()) {
        this.contrato.setValoroperacao(this.contrato.getMargemutilizada() / coeficiente.getCoeficientevalor());
      } else {
        this.contrato.setValoroperacao(
            this.contrato.getParcela() / coeficiente.getCoeficientevalor() - this.contrato.getValorquitar());
      }  
    this.contrato.setValorcliente(this.contrato.getValoroperacao());
    session.setAttribute("contrato", this.contrato);
    session.setAttribute("coeficiente", coeficiente);
    session.setAttribute("orgaobanco", this.orgaobanco);
    return "cadContrato";
  }
  
  public String voltar() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", this.contrato);
    session.setAttribute("orgaobanco", this.orgaobanco);
    return "cadContrato";
  }
}
