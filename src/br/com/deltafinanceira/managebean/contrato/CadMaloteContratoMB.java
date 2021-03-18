package br.com.deltafinanceira.managebean.contrato;

import br.com.deltafinanceira.facade.MaloteContratoFacade;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Malotecontrato;
import br.com.deltafinanceira.util.UsuarioLogadoMB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class CadMaloteContratoMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Contrato contrato;
  
  private List<Malotecontrato> listaMaloteContrato;
  
  @Inject
  private UsuarioLogadoMB usuarioLogadoMB;
  
  private Malotecontrato malotecontrato;
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.contrato = (Contrato)session.getAttribute("contrato");
    session.removeAttribute("contrato");
    this.malotecontrato = new Malotecontrato();
    this.malotecontrato.setDataenvio(new Date());
    gerarListaMalotes();
  }
  
  public Contrato getContrato() {
    return this.contrato;
  }
  
  public void setContrato(Contrato contrato) {
    this.contrato = contrato;
  }
  
  public List<Malotecontrato> getListaMaloteContrato() {
    return this.listaMaloteContrato;
  }
  
  public void setListaMaloteContrato(List<Malotecontrato> listaMaloteContrato) {
    this.listaMaloteContrato = listaMaloteContrato;
  }
  
  public Malotecontrato getMalotecontrato() {
    return this.malotecontrato;
  }
  
  public void setMalotecontrato(Malotecontrato malotecontrato) {
    this.malotecontrato = malotecontrato;
  }
  
  public void gerarListaMalotes() {
    MaloteContratoFacade maloteContratoFacade = new MaloteContratoFacade();
    this.listaMaloteContrato = maloteContratoFacade.lista("Select m From Malotecontrato m WHERE m.usuario.idusuario=" + 
        this.usuarioLogadoMB.getUsuario().getIdusuario() + " and m.contrato.idcontrato=" + this.contrato.getIdcontrato());
    if (this.listaMaloteContrato == null)
      this.listaMaloteContrato = new ArrayList<>(); 
  }
  
  public void salvarMalote() {
    MaloteContratoFacade maloteContratoFacade = new MaloteContratoFacade();
    this.malotecontrato.setContrato(this.contrato);
    this.malotecontrato.setUsuario(this.usuarioLogadoMB.getUsuario());
    this.malotecontrato = maloteContratoFacade.salvar(this.malotecontrato);
    this.listaMaloteContrato.add(this.malotecontrato);
    this.malotecontrato = new Malotecontrato();
    this.malotecontrato.setDataenvio(new Date());
  }
  
  public String voltar() {
    return "consContrato";
  }
}
