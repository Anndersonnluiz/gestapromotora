package br.com.deltafinanceira.managebean.contrato;

import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.facade.HistoricoComissaoFacade;
import br.com.deltafinanceira.facade.RankingVendasAnualFacade;
import br.com.deltafinanceira.facade.RankingVendasFacade;
import br.com.deltafinanceira.facade.RegrasCoeficienteFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Historicocomissao;
import br.com.deltafinanceira.model.Rankingvendas;
import br.com.deltafinanceira.model.Rankingvendasanual;
import br.com.deltafinanceira.model.Regrascoeficiente;
import br.com.deltafinanceira.model.Usuario;
import br.com.deltafinanceira.util.Formatacao;
import br.com.deltafinanceira.util.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class TrocarTitularMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private List<Usuario> listaUsuario;
  
  private Usuario usuario;
  
  private Contrato contrato;
  
  private String senha;
  
  private Regrascoeficiente regracoeficiente;
  
  private Usuario usuarioAtual;
  
  private List<Historicocomissao> listaHistoricoComissao;
  
  private boolean selecionarTodos;
  
  @PostConstruct
  public void init() {
    gerarListaUsuario();
  }
  
  public List<Usuario> getListaUsuario() {
    return this.listaUsuario;
  }
  
  public void setListaUsuario(List<Usuario> listaUsuario) {
    this.listaUsuario = listaUsuario;
  }
  
  public Usuario getUsuario() {
    return this.usuario;
  }
  
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  
  public Contrato getContrato() {
    return this.contrato;
  }
  
  public void setContrato(Contrato contrato) {
    this.contrato = contrato;
  }
  
  public String getSenha() {
    return this.senha;
  }
  
  public void setSenha(String senha) {
    this.senha = senha;
  }
  
  public Regrascoeficiente getRegracoeficiente() {
    return this.regracoeficiente;
  }
  
  public void setRegracoeficiente(Regrascoeficiente regracoeficiente) {
    this.regracoeficiente = regracoeficiente;
  }
  
  public Usuario getUsuarioAtual() {
    return this.usuarioAtual;
  }
  
  public void setUsuarioAtual(Usuario usuarioAtual) {
    this.usuarioAtual = usuarioAtual;
  }
  
  public List<Historicocomissao> getListaHistoricoComissao() {
    return this.listaHistoricoComissao;
  }
  
  public void setListaHistoricoComissao(List<Historicocomissao> listaHistoricoComissao) {
    this.listaHistoricoComissao = listaHistoricoComissao;
  }
  
  public boolean isSelecionarTodos() {
    return this.selecionarTodos;
  }
  
  public void setSelecionarTodos(boolean selecionarTodos) {
    this.selecionarTodos = selecionarTodos;
  }
  
  public void gerarListaUsuario() {
    UsuarioFacade usuarioFacade = new UsuarioFacade();
    this.listaUsuario = usuarioFacade.listar("Select u From Usuario u order by u.nome");
    if (this.listaUsuario == null)
      this.listaUsuario = new ArrayList<>(); 
  }
  
  public String salvar() {
    HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
    ContratoFacade contratoFacade = new ContratoFacade();
    if (this.usuario != null) {
      for (int i = 0; i < this.listaHistoricoComissao.size(); i++) {
        if (((Historicocomissao)this.listaHistoricoComissao.get(i)).isSelecionado()) {
          ((Historicocomissao)this.listaHistoricoComissao.get(i)).setUsuario(this.usuario);
          ((Historicocomissao)this.listaHistoricoComissao.get(i)).getContrato().setUsuario(this.usuario);
          contratoFacade.salvar(((Historicocomissao)this.listaHistoricoComissao.get(i)).getContrato());
          historicoComissaoFacade.salvar(this.listaHistoricoComissao.get(i));
        } 
      } 
    } else {
      Mensagem.lancarMensagemInfo("Informe o novo titular dos contratos", "");
      return "";
    } 
    return "dashboard";
  }
  
  public String cancelar() {
    return "dashboard";
  }
  
  public void descontarRankingMensal() {
    RankingVendasFacade rankingVendasFacade = new RankingVendasFacade();
    int mes = Formatacao.getMesData(new Date()) + 1;
    int ano = Formatacao.getAnoData(new Date());
    List<Rankingvendas> listaRanking = rankingVendasFacade.lista("Select r From Rankingvendas r WHERE r.mes=" + mes + 
        " AND r.ano=" + ano + " AND r.usuario.idusuario=" + this.contrato.getUsuario().getIdusuario());
    if (listaRanking != null && listaRanking.size() > 0) {
      Rankingvendas rankingvendas = listaRanking.get(0);
      if (this.contrato.getParcelaspagas() > 12 && this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
        rankingvendas.setValorvenda(rankingvendas.getValorvenda() - 
            this.contrato.getValorquitar() * this.regracoeficiente.getFlatrecebidaregra() / 100.0F);
      } else {
        rankingvendas.setValorvenda(rankingvendas.getValorvenda() - 
            this.contrato.getValoroperacao() * this.regracoeficiente.getFlatrecebidaregra() / 100.0F);
      } 
      rankingVendasFacade.salvar(rankingvendas);
    } 
  }
  
  public void gerarRankingMensal() {
    Rankingvendas rankingvendas;
    RankingVendasFacade rankingVendasFacade = new RankingVendasFacade();
    int mes = Formatacao.getMesData(new Date()) + 1;
    int ano = Formatacao.getAnoData(new Date());
    List<Rankingvendas> listaRanking = rankingVendasFacade.lista("Select r From Rankingvendas r WHERE r.mes=" + mes + 
        " AND r.ano=" + ano + " AND r.usuario.idusuario=" + this.usuario.getIdusuario());
    if (listaRanking != null && listaRanking.size() > 0) {
      rankingvendas = listaRanking.get(0);
    } else {
      rankingvendas = new Rankingvendas();
      rankingvendas.setAno(ano);
      rankingvendas.setUsuario(this.usuario);
    } 
    if (this.contrato.getParcelaspagas() > 12 && this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
      rankingvendas.setValorvenda(rankingvendas.getValorvenda() + 
          this.contrato.getValorquitar() * this.regracoeficiente.getFlatrecebidaregra() / 100.0F);
    } else {
      rankingvendas.setValorvenda(rankingvendas.getValorvenda() + 
          this.contrato.getValoroperacao() * this.regracoeficiente.getFlatrecebidaregra() / 100.0F);
    } 
    rankingVendasFacade.salvar(rankingvendas);
  }
  
  public void descontarRankingAnual() {
    RankingVendasAnualFacade rankingVendasFacade = new RankingVendasAnualFacade();
    int ano = Formatacao.getAnoData(new Date());
    List<Rankingvendasanual> listaRanking = rankingVendasFacade.lista("Select r From Rankingvendasanual r WHERE  r.ano=" + 
        ano + " AND r.usuario.idusuario=" + this.contrato.getUsuario().getIdusuario());
    if (listaRanking != null && listaRanking.size() > 0) {
      Rankingvendasanual rankingvendas = listaRanking.get(0);
      if (this.contrato.getParcelaspagas() > 12 && this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
        rankingvendas.setValorvenda(rankingvendas.getValorvenda() - 
            this.contrato.getValorquitar() * this.regracoeficiente.getFlatrecebidaregra() / 100.0F);
      } else {
        rankingvendas.setValorvenda(rankingvendas.getValorvenda() - 
            this.contrato.getValoroperacao() * this.regracoeficiente.getFlatrecebidaregra() / 100.0F);
      } 
      rankingVendasFacade.salvar(rankingvendas);
    } 
  }
  
  public void gerarRankingAnual() {
    Rankingvendasanual rankingvendas;
    RankingVendasAnualFacade rankingVendasFacade = new RankingVendasAnualFacade();
    int ano = Formatacao.getAnoData(new Date());
    List<Rankingvendasanual> listaRanking = rankingVendasFacade.lista("Select r From Rankingvendasanual r WHERE  r.ano=" + 
        ano + " AND r.usuario.idusuario=" + this.usuario.getIdusuario());
    if (listaRanking != null && listaRanking.size() > 0) {
      rankingvendas = listaRanking.get(0);
    } else {
      rankingvendas = new Rankingvendasanual();
      rankingvendas.setAno(ano);
      rankingvendas.setUsuario(this.usuario);
    } 
    if (this.contrato.getParcelaspagas() > 12 && this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
      rankingvendas.setValorvenda(rankingvendas.getValorvenda() + 
          this.contrato.getValorquitar() * this.regracoeficiente.getFlatrecebidaregra() / 100.0F);
    } else {
      rankingvendas.setValorvenda(rankingvendas.getValorvenda() + 
          this.contrato.getValoroperacao() * this.regracoeficiente.getFlatrecebidaregra() / 100.0F);
    } 
    rankingVendasFacade.salvar(rankingvendas);
  }
  
  public void alterarComissao() {
    HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
    List<Historicocomissao> lista = historicoComissaoFacade.lista("Select h From Historicocomissao h WHERE h.contrato.idcontrato=" + 
        this.contrato.getIdcontrato());
    if (lista != null && lista.size() > 0) {
      Historicocomissao historicocomissao = lista.get(0);
      historicocomissao.setUsuario(this.usuario);
      historicoComissaoFacade.salvar(historicocomissao);
    } 
  }
  
  public void buscarRegraCoeficiente() {
    RegrasCoeficienteFacade regrasCoeficienteFacade = new RegrasCoeficienteFacade();
    this.regracoeficiente = regrasCoeficienteFacade.consultar(this.contrato.getIdregracoeficiente());
  }
  
  public void buscarContratos() {
    if (this.usuarioAtual != null) {
      HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
      this.listaHistoricoComissao = historicoComissaoFacade.lista("SELECT h From Historicocomissao h WHERE h.contrato.usuario.idusuario=" + 
          this.usuarioAtual.getIdusuario());
      if (this.listaHistoricoComissao == null)
        this.listaHistoricoComissao = new ArrayList<>(); 
    } 
  }
  
  public void selecionarTodosContratos() {
    if (this.selecionarTodos) {
      this.selecionarTodos = false;
    } else {
      this.selecionarTodos = true;
    } 
    for (int i = 0; i < this.listaHistoricoComissao.size(); i++)
      ((Historicocomissao)this.listaHistoricoComissao.get(i)).setSelecionado(this.selecionarTodos); 
  }
}
