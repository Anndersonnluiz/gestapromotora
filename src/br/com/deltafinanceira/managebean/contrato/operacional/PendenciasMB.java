package br.com.deltafinanceira.managebean.contrato.operacional;

import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.model.Contrato;
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
public class PendenciasMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Inject
  private UsuarioLogadoMB usuarioLogadoMB;
  
  private List<Contrato> listaContrato;
  
  @PostConstruct
  public void init() {
    gerarListaInicial();
  }
  
  public List<Contrato> getListaContrato() {
    return this.listaContrato;
  }
  
  public void setListaContrato(List<Contrato> listaContrato) {
    this.listaContrato = listaContrato;
  }
  
  public String editar(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    session.setAttribute("orgaobanco", contrato.getOrgaoBanco());
    session.setAttribute("voltarTela", "consPendencias");
    return "cadContrato";
  }
  
  public void gerarListaInicial() {
    ContratoFacade contratoFacade = new ContratoFacade();
    String sql = "Select c From Contrato c WHERE c.situacao.idsituacao=37  and c.simulacao=false";
    if (!this.usuarioLogadoMB.getUsuario().isAcessogeral() && 
      !this.usuarioLogadoMB.getUsuario().getTipocolaborador().getAcessocolaborador().isAcessooperacional()) {
      sql = String.valueOf(sql) + " and c.usuario.idusuario=" + this.usuarioLogadoMB.getUsuario().getIdusuario();
    }else {
    	sql = sql + " and c.usuario.treinamento=false";
    }
    this.listaContrato = contratoFacade.lista(sql);
    if (this.listaContrato == null)
      this.listaContrato = new ArrayList<>(); 
  }
  
  public String historicoContrato(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    return "linhaTempoContrato";
  }
  
  public String alterarSituacao(Contrato contrato) {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.setAttribute("contrato", contrato);
    session.setAttribute("voltarTela", "consPendencias");
    return "alterarSituacao";
  }
}
