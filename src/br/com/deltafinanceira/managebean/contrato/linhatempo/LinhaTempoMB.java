package br.com.deltafinanceira.managebean.contrato.linhatempo;

import br.com.deltafinanceira.facade.HistoricoUsuarioFacade;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Historicousuario;
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
public class LinhaTempoMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private List<Historicousuario> listaHistorico;
  
  private Contrato contrato;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.contrato = (Contrato)session.getAttribute("contrato");
    session.removeAttribute("contrsto");
    gerarHistorico();
  }
  
  public List<Historicousuario> getListaHistorico() {
    return this.listaHistorico;
  }
  
  public void setListaHistorico(List<Historicousuario> listaHistorico) {
    this.listaHistorico = listaHistorico;
  }
  
  public Contrato getContrato() {
    return this.contrato;
  }
  
  public void setContrato(Contrato contrato) {
    this.contrato = contrato;
  }
  
  public void gerarHistorico() {
    HistoricoUsuarioFacade historicoUsuarioFacade = new HistoricoUsuarioFacade();
    this.listaHistorico = historicoUsuarioFacade.lista("Select h From Historicousuario h Where h.idcontrato=" + 
        this.contrato.getIdcontrato());
    if (this.listaHistorico == null)
      this.listaHistorico = new ArrayList<>(); 
  }
}
