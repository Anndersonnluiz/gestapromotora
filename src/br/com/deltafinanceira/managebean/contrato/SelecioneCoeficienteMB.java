package br.com.deltafinanceira.managebean.contrato;

import br.com.deltafinanceira.dao.RegrasCoeficienteDao;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.OrgaoBanco;
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
public class SelecioneCoeficienteMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Contrato contrato;
  
  private List<Valorescoeficiente> listaValores;
  
  private List<Regrascoeficiente> listaRegrasValores;
  
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
  
  public List<Regrascoeficiente> getListaRegrasValores() {
    return this.listaRegrasValores;
  }
  
  public void setListaRegrasValores(List<Regrascoeficiente> listaRegrasValores) {
    this.listaRegrasValores = listaRegrasValores;
  }
  
  public void gerarListaValores() {
    RegrasCoeficienteDao regrasCoeficienteDao = new RegrasCoeficienteDao();
    this.listaRegrasValores = regrasCoeficienteDao
      .lista("Select v From Regrascoeficiente v WHERE v.valorescoeficiente.coeficiente.orgaoBanco.idorgaobanco=" + 
        this.orgaobanco.getIdorgaobanco() + " AND v.valorescoeficiente.coeficiente.tipooperacao.idtipooperacao=" + 
        this.contrato.getTipooperacao().getIdtipooperacao());
    if (this.listaRegrasValores == null)
      this.listaRegrasValores = new ArrayList<>(); 
  }
  
  public String selecionarCoeficiente(Regrascoeficiente regrascoeficiente) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.contrato.setValorescoeficiente(regrascoeficiente.getValorescoeficiente().getIdvalorescoeficiente().intValue());
    if (this.contrato.getTipooperacao() != null)
      if (this.contrato.getTipooperacao().isMargem()) {
        this.contrato.setValoroperacao(this.contrato.getMargemutilizada() / regrascoeficiente.getValorescoeficiente().getCoeficientevalor());
      } else {
        this.contrato.setValoroperacao(
            this.contrato.getParcela() / regrascoeficiente.getValorescoeficiente().getCoeficientevalor() - this.contrato.getValorquitar());
      }  
    this.contrato.setValorcliente(this.contrato.getValoroperacao());
    session.setAttribute("contrato", this.contrato);
    session.setAttribute("regrascoeficiente", regrascoeficiente);
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
