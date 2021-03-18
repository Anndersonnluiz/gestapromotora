package br.com.deltafinanceira.managebean.contrato;

import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.facade.HistoricoPendenciaFacade;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Historicopendencia;
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
public class CadHistoricoPendenciaMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Historicopendencia historicopendencia;
  
  private Contrato contrato;
  
  private List<Historicopendencia> listaHistoricoPendencia;
  
  @Inject
  private UsuarioLogadoMB usuarioLogadoMB;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.contrato = (Contrato)session.getAttribute("contrato");
    session.removeAttribute("contrato");
    gerarListaHistoricoPendencia();
    this.historicopendencia = new Historicopendencia();
  }
  
  public Historicopendencia getHistoricopendencia() {
    return this.historicopendencia;
  }
  
  public void setHistoricopendencia(Historicopendencia historicopendencia) {
    this.historicopendencia = historicopendencia;
  }
  
  public Contrato getContrato() {
    return this.contrato;
  }
  
  public void setContrato(Contrato contrato) {
    this.contrato = contrato;
  }
  
  public List<Historicopendencia> getListaHistoricoPendencia() {
    return this.listaHistoricoPendencia;
  }
  
  public void setListaHistoricoPendencia(List<Historicopendencia> listaHistoricoPendencia) {
    this.listaHistoricoPendencia = listaHistoricoPendencia;
  }
  
  public void gerarListaHistoricoPendencia() {
    HistoricoPendenciaFacade historicoPendenciaFacade = new HistoricoPendenciaFacade();
    this.listaHistoricoPendencia = historicoPendenciaFacade.lista("Select h From Historicopendencia h WHERE h.contrato.idcontrato=" + this.contrato.getIdcontrato());
    if (this.listaHistoricoPendencia == null)
      this.listaHistoricoPendencia = new ArrayList<>(); 
  }
  
  public void salvarPendencia() {
    HistoricoPendenciaFacade historicoPendenciaFacade = new HistoricoPendenciaFacade();
    this.historicopendencia.setContrato(this.contrato);
    this.historicopendencia.setUsuario(this.usuarioLogadoMB.getUsuario());
    this.historicopendencia = historicoPendenciaFacade.salvar(this.historicopendencia);
    if (!this.contrato.isPendente()) {
      ContratoFacade contratoFacade = new ContratoFacade();
      this.contrato.setPendente(true);
      contratoFacade.salvar(this.contrato);
    } 
    this.listaHistoricoPendencia.add(this.historicopendencia);
    this.historicopendencia = new Historicopendencia();
  }
  
  public String voltar() {
    return "consContrato";
  }
}
