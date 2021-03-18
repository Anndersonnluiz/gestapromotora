package br.com.deltafinanceira.managebean.contrato;

import br.com.deltafinanceira.bean.AlteracaoBean;
import br.com.deltafinanceira.dao.NotificacaoDao;
import br.com.deltafinanceira.facade.BancoFacade;
import br.com.deltafinanceira.facade.CoeficienteFacade;
import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.facade.HistoricoComissaoFacade;
import br.com.deltafinanceira.facade.HistoricoUsuarioFacade;
import br.com.deltafinanceira.facade.OrgaoBancoFacade;
import br.com.deltafinanceira.facade.SituacaoFacade;
import br.com.deltafinanceira.model.Banco;
import br.com.deltafinanceira.model.Coeficiente;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Historicocomissao;
import br.com.deltafinanceira.model.Historicousuario;
import br.com.deltafinanceira.model.Notificacao;
import br.com.deltafinanceira.model.OrgaoBanco;
import br.com.deltafinanceira.model.Situacao;
import br.com.deltafinanceira.util.Formatacao;
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
public class AlterarSituacaoMB implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Inject
  private UsuarioLogadoMB usuarioLogadoMB;
  
  private Contrato contrato;
  
  private Situacao situacao;
  
  private List<Situacao> listaSituacao;
  
  private String voltar;
  
  private AlteracaoBean alteracaoBean;
  
  private boolean valorliberado;
  
  private List<Banco> listaBanco;
  
  private List<OrgaoBanco> listaOrgao;
  
  private Banco banco;
  
  private OrgaoBanco orgaoBanco;
  
  private List<Coeficiente> listaCoefiente;
  
  private Coeficiente coeficiente; 
  
  @PostConstruct
  public void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    this.contrato = (Contrato)session.getAttribute("contrato");
    session.removeAttribute("contrato");
    this.voltar = (String)session.getAttribute("voltar");
    session.removeAttribute("voltar");
    this.situacao = this.contrato.getSituacao();
    gerarListaSituacao();
    this.banco = this.contrato.getOrgaoBanco().getBanco();
    this.orgaoBanco = this.contrato.getOrgaoBanco();
    gerarListaBanco();
    gerarListaOrgao();
    buscarCoeficiente();
    this.alteracaoBean = new AlteracaoBean();
    this.alteracaoBean.setDescricao(this.contrato.getSituacao().getDescricao());
    if (this.usuarioLogadoMB.getUsuario().isAcessogeral() || 
      this.usuarioLogadoMB.getUsuario().getTipocolaborador().getAcessocolaborador().isAcessooperacional()) {
      this.valorliberado = false;
    } else {
      this.valorliberado = true;
    } 
    if (!contrato.isPossuioperador()) {
		contrato.setOperador(usuarioLogadoMB.getUsuario().getNome());
	}
    gerarListaValores();
  }
  
  public Contrato getContrato() {
    return this.contrato;
  }
  
  public void setContrato(Contrato contrato) {
    this.contrato = contrato;
  }
  
  public Situacao getSituacao() {
    return this.situacao;
  }
  
  public void setSituacao(Situacao situacao) {
    this.situacao = situacao;
  }
  
  public List<Situacao> getListaSituacao() {
    return this.listaSituacao;
  }
  
  public void setListaSituacao(List<Situacao> listaSituacao) {
    this.listaSituacao = listaSituacao;
  }
  
  public String getVoltar() {
    return this.voltar;
  }
  
  public void setVoltar(String voltar) {
    this.voltar = voltar;
  }
  
  public AlteracaoBean getAlteracaoBean() {
    return this.alteracaoBean;
  }
  
  public void setAlteracaoBean(AlteracaoBean alteracaoBean) {
    this.alteracaoBean = alteracaoBean;
  }
  
  public boolean isValorliberado() {
    return this.valorliberado;
  }
  
  public void setValorliberado(boolean valorliberado) {
    this.valorliberado = valorliberado;
  }
  
  public Banco getBanco() {
    return this.banco;
  }
  
  public void setBanco(Banco banco) {
    this.banco = banco;
  }
  
  public OrgaoBanco getOrgaoBanco() {
    return this.orgaoBanco;
  }
  
  public void setOrgaoBanco(OrgaoBanco orgaoBanco) {
    this.orgaoBanco = orgaoBanco;
  }
  
  public List<Coeficiente> getListaCoefiente() {
	return listaCoefiente;
}

public void setListaCoefiente(List<Coeficiente> listaCoefiente) {
	this.listaCoefiente = listaCoefiente;
}

public Coeficiente getCoeficiente() {
	return coeficiente;
}

public void setCoeficiente(Coeficiente coeficiente) {
	this.coeficiente = coeficiente;
}

public void gerarListaSituacao() {
    SituacaoFacade situacaoFacade = new SituacaoFacade();
    String sql = "Select s From Situacao s WHERE s.visualizar=true ";
    if (this.contrato.getTipooperacao().getIdtipooperacao().intValue() != 1)
      sql = String.valueOf(sql) + " AND s.portabilidade=false "; 
    sql = String.valueOf(sql) + " ORDER BY s.descricao";
    this.listaSituacao = situacaoFacade.lista(sql);
    if (this.listaSituacao == null)
      this.listaSituacao = new ArrayList<>(); 
  }
  
  public String cancelar() {
    return this.voltar;
  }
  
  public List<Banco> getListaBanco() {
    return this.listaBanco;
  }
  
  public void setListaBanco(List<Banco> listaBanco) {
    this.listaBanco = listaBanco;
  }
  
  public List<OrgaoBanco> getListaOrgao() {
    return this.listaOrgao;
  }
  
  public void setListaOrgao(List<OrgaoBanco> listaOrgao) {
    this.listaOrgao = listaOrgao;
  }
  
  public String salvar() {
    ContratoFacade contratoFacade = new ContratoFacade();
    this.contrato.setSituacao(this.situacao);
    this.contrato.setUltimamudancasituacao(new Date());
    this.contrato.setOrgaoBanco(this.orgaoBanco);
    if (this.situacao.getIdsituacao().intValue() == 16)
      this.contrato.setDatapagamento(new Date()); 
    gerarProducao();
    if (this.coeficiente != null && this.coeficiente.getIdcoeficiente() != null) {
      this.contrato.setIdregracoeficiente(this.coeficiente.getIdcoeficiente().intValue());
      gerarComissao();
    } 
    gerarNotificacao();
    if (contrato.getSituacao().getIdsituacao() != 35) {
		contrato.setPossuioperador(true);
	}
    this.contrato = contratoFacade.salvar(this.contrato);
    if (!this.alteracaoBean.getDescricao().equalsIgnoreCase(this.contrato.getSituacao().getDescricao())) {
      Historicousuario historicousuario = new Historicousuario();
      salvarHistorico(historicousuario);
    } 
    return this.voltar;
  }
  
  public void gerarNotificacao() {
    NotificacaoDao notificacaoDao = new NotificacaoDao();
    Notificacao notificacao = new Notificacao();
    notificacao.setDatalancamento(new Date());
    notificacao.setVisto(false);
    notificacao.setUsuario(this.contrato.getUsuario());
    notificacao.setIdcontrato(this.contrato.getIdcontrato().intValue());
    notificacao.setTitulo("Contrato: " + this.contrato.getCodigocontrato());
    notificacao.setDescricao("Seu contrato do(a) cliente: " + this.contrato.getCliente().getNome() + 
        " mudou seu status para: " + this.situacao.getDescricao());
    notificacaoDao.salvar(notificacao);
  }
  
  public void buscarCoeficiente() {
    if (this.contrato.getIdregracoeficiente() > 0) {
    	CoeficienteFacade coeficienteFacade = new CoeficienteFacade();
      this.coeficiente = coeficienteFacade.consultar(this.contrato.getIdregracoeficiente());
    } 
  }
  
  public void salvarHistorico(Historicousuario historicousuario) {
    HistoricoUsuarioFacade historicoUsuarioFacade = new HistoricoUsuarioFacade();
    historicousuario.setDatacadastro(new Date());
    historicousuario.setTitulo("Alteração");
    historicousuario.setIcone("mudanca.png");
    historicousuario.setIdcontrato(this.contrato.getIdcontrato().intValue());
    historicousuario.setHora(Formatacao.foramtarHoraString());
    historicousuario.setUsuario(this.usuarioLogadoMB.getUsuario());
    historicousuario.setDescricao("Situação alterada de " + this.alteracaoBean.getDescricao() + " para " + 
        this.contrato.getSituacao().getDescricao() + ", Tipo do contrato: " + 
        this.contrato.getTipooperacao().getDescricao() + ", Código contrato: " + this.contrato.getCodigocontrato());
    historicoUsuarioFacade.salvar(historicousuario);
  }
  
  public void gerarListaBanco() {
    BancoFacade bancoFacade = new BancoFacade();
    this.listaBanco = bancoFacade.lista("Select b From Banco b WHERE b.nome !='Nenhum' ORDER BY b.nome");
    if (this.listaBanco == null)
      this.listaBanco = new ArrayList<>(); 
  }
  
  public void gerarListaOrgao() {
    OrgaoBancoFacade orgaoBancoFacade = new OrgaoBancoFacade();
    if (this.banco != null && this.banco.getIdbanco() != null)
      this.listaOrgao = orgaoBancoFacade
        .lista("Select o From OrgaoBanco o WHERE o.banco.idbanco=" + this.banco.getIdbanco()); 
    if (this.listaOrgao == null)
      this.listaOrgao = new ArrayList<>(); 
  }
  
  public void gerarListaValores() {
    CoeficienteFacade coeficienteFacade = new CoeficienteFacade();
    this.listaCoefiente = coeficienteFacade.lista(
        "Select c From Coeficiente c WHERE c.orgaoBanco.idorgaobanco=" + 
        this.contrato.getOrgaoBanco().getIdorgaobanco() + 
        " AND c.tipooperacao.idtipooperacao=" + 
        this.contrato.getTipooperacao().getIdtipooperacao());
    if (this.listaCoefiente == null)
      this.listaCoefiente = new ArrayList<>(); 
  }
  
  public void gerarProducao() {
    HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
    Historicocomissao historicocomissao = historicoComissaoFacade.consultar(this.contrato.getIdcontrato().intValue());
    if (historicocomissao == null) {
      historicocomissao = new Historicocomissao();
      historicocomissao.setDatalancamento(new Date());
      historicocomissao.setContrato(this.contrato);
      historicocomissao.setUsuario(this.usuarioLogadoMB.getUsuario());
      historicocomissao.setTipo("PENDENTE");
      int mes = Formatacao.getMesData(new Date()) + 1;
      int ano = Formatacao.getAnoData(new Date());
      historicocomissao.setAno(ano);
      historicocomissao.setMes(mes);
    } 
    if (this.contrato.getParcelaspagas() > 12 && this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
      historicocomissao.setProdliq(this.contrato.getValorquitar());
    } else if (this.contrato.getTipooperacao().getIdtipooperacao().intValue() != 1) {
      historicocomissao.setProdliq(this.contrato.getValorcliente());
    } else {
      historicocomissao.setProdliq(0.0F);
    } 
    historicoComissaoFacade.salvar(historicocomissao);
  }
  
  public void gerarComissao() {
    HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
    Historicocomissao historicocomissao = historicoComissaoFacade.consultar(this.contrato.getIdcontrato().intValue());
    if (historicocomissao == null) {
      historicocomissao = new Historicocomissao();
      historicocomissao.setDatalancamento(new Date());
      historicocomissao.setContrato(this.contrato);
      historicocomissao.setUsuario(this.usuarioLogadoMB.getUsuario());
      historicocomissao.setTipo("PENDENTE");
      int mes = Formatacao.getMesData(new Date()) + 1;
      int ano = Formatacao.getAnoData(new Date());
      historicocomissao.setAno(ano);
      historicocomissao.setMes(mes);
    } 
    if (this.contrato.getParcelaspagas() > 12 && this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
      historicocomissao
        .setCmdbruta(this.contrato.getValorquitar() * this.coeficiente.getComissaoloja() / 100.0F);
      historicocomissao
        .setCmsliq(this.contrato.getValorquitar() * this.coeficiente.getComissaocorretor() / 100.0F);
    } else if (this.contrato.getTipooperacao().getIdtipooperacao().intValue() != 1) {
      historicocomissao
        .setCmdbruta(this.contrato.getValorcliente() * this.coeficiente.getComissaoloja() / 100.0F);
      historicocomissao
        .setCmsliq(this.contrato.getValorcliente() * this.coeficiente.getComissaocorretor() / 100.0F);
    } else {
      historicocomissao.setCmdbruta(0.0F);
      historicocomissao.setCmsliq(0.0F);
    } 
    historicoComissaoFacade.salvar(historicocomissao);
  }
}
