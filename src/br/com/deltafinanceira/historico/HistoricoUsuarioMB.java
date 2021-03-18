package br.com.deltafinanceira.historico;

import br.com.deltafinanceira.facade.HistoricoUsuarioFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Historicousuario;
import br.com.deltafinanceira.model.Usuario;
import br.com.deltafinanceira.util.Formatacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class HistoricoUsuarioMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Usuario usuario;
  
  private List<Usuario> listaUsuario;
  
  private Date dataini;
  
  private Date datafin;
  
  private List<Historicousuario> listaHistorico;
  
  private Contrato contrato;
  
  @PostConstruct
  public void init() {
    gerarListaUsuario();
    gerarHistorico();
  }
  
  public Usuario getUsuario() {
    return this.usuario;
  }
  
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  
  public List<Usuario> getListaUsuario() {
    return this.listaUsuario;
  }
  
  public void setListaUsuario(List<Usuario> listaUsuario) {
    this.listaUsuario = listaUsuario;
  }
  
  public Date getDataini() {
    return this.dataini;
  }
  
  public void setDataini(Date dataini) {
    this.dataini = dataini;
  }
  
  public Date getDatafin() {
    return this.datafin;
  }
  
  public void setDatafin(Date datafin) {
    this.datafin = datafin;
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
  
  public void gerarListaUsuario() {
    UsuarioFacade usuarioFacade = new UsuarioFacade();
    this.listaUsuario = usuarioFacade.listar("Select u From Usuario u order by u.nome");
    if (this.listaUsuario == null)
      this.listaUsuario = new ArrayList<>(); 
  }
  
  public void gerarHistorico() {
    HistoricoUsuarioFacade historicoUsuarioFacade = new HistoricoUsuarioFacade();
    this.listaHistorico = historicoUsuarioFacade.lista("Select h From Historicousuario h Where h.datacadastro='" + 
        Formatacao.ConvercaoDataSql(new Date()) + "'");
    if (this.listaHistorico == null)
      this.listaHistorico = new ArrayList<>(); 
  }
  
  public void pesquisar() {
    HistoricoUsuarioFacade historicoUsuarioFacade = new HistoricoUsuarioFacade();
    String sql = "Select h From Historicousuario h Where h.descricao like '%%'";
    if (this.usuario != null && this.usuario.getIdusuario() != null)
      sql = String.valueOf(sql) + " and h.usuario.idusuario=" + this.usuario.getIdusuario(); 
    if (this.dataini != null && this.datafin != null)
      sql = String.valueOf(sql) + " and h.datacadastro>='" + Formatacao.ConvercaoDataSql(this.dataini) + 
        "' and h.datacadastro<='" + Formatacao.ConvercaoDataSql(this.datafin) + "'"; 
    this.listaHistorico = historicoUsuarioFacade.lista(sql);
    if (this.listaHistorico == null)
      this.listaHistorico = new ArrayList<>(); 
  }
  
  public void limpar() {
    this.usuario = null;
    this.datafin = null;
    this.dataini = null;
    gerarHistorico();
  }
}
