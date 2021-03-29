package br.com.deltafinanceira.managebean.financeiro;

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

import br.com.deltafinanceira.bean.AlteracaoBean;
import br.com.deltafinanceira.facade.CoeficienteFacade;
import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.facade.HistoricoComissaoFacade;
import br.com.deltafinanceira.facade.HistoricoUsuarioFacade;
import br.com.deltafinanceira.facade.PromotoraFacade;
import br.com.deltafinanceira.facade.SituacaoFacade;
import br.com.deltafinanceira.model.Coeficiente;
import br.com.deltafinanceira.model.Historicocomissao;
import br.com.deltafinanceira.model.Historicousuario;
import br.com.deltafinanceira.model.Promotora;
import br.com.deltafinanceira.model.Situacao;
import br.com.deltafinanceira.util.Formatacao;
import br.com.deltafinanceira.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class EditarComissaoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB; 
	private Historicocomissao historicocomissao;
	private List<Situacao> listaSituacao;
	private String tipoFiltro;
	private String tipoAntigo;
	private List<Promotora> listaPromotora;
	private Situacao situacao;
	private Promotora promotora;
	private AlteracaoBean alteracoesBean;
	private Integer convenio;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		historicocomissao = (Historicocomissao) session.getAttribute("historicocomissao");
		session.removeAttribute("historicocomissao");
		tipoFiltro = (String) session.getAttribute("tipoFiltro");
		session.removeAttribute("tipoFiltro");
		convenio = (Integer) session.getAttribute("convenio");
		session.removeAttribute("convenio");
		tipoAntigo = historicocomissao.getTipo();
		situacao = historicocomissao.getContrato().getSituacao();
		promotora = historicocomissao.getContrato().getPromotora();
		gerarListaSituacao();
		gerarListaPromotora();
		alteracoesBean = new AlteracaoBean();
		alteracoesBean.setDescricao(historicocomissao.getContrato().getSituacao().getDescricao());
	}


	public Historicocomissao getHistoricocomissao() {
		return historicocomissao;
	}


	public void setHistoricocomissao(Historicocomissao historicocomissao) {
		this.historicocomissao = historicocomissao;
	}
	
	
	public List<Situacao> getListaSituacao() {
		return listaSituacao;
	}


	public void setListaSituacao(List<Situacao> listaSituacao) {
		this.listaSituacao = listaSituacao;
	}


	public String getTipoFiltro() {
		return tipoFiltro;
	}


	public void setTipoFiltro(String tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}


	public String getTipoAntigo() {
		return tipoAntigo;
	}


	public void setTipoAntigo(String tipoAntigo) {
		this.tipoAntigo = tipoAntigo;
	}


	public List<Promotora> getListaPromotora() {
		return listaPromotora;
	}


	public void setListaPromotora(List<Promotora> listaPromotora) {
		this.listaPromotora = listaPromotora;
	}


	public Situacao getSituacao() {
		return situacao;
	}


	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}


	public Promotora getPromotora() {
		return promotora;
	}


	public void setPromotora(Promotora promotora) {
		this.promotora = promotora;
	}


	public String voltar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("tipoFiltro", tipoFiltro);
		session.setAttribute("convenio", convenio);
		return "consPagamentoComissao";
	}
	
	public String salvar() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		historicocomissao.getContrato().setSituacao(situacao);
		if (!historicocomissao.getTipo().equalsIgnoreCase(tipoAntigo)
				&& historicocomissao.getTipo().equalsIgnoreCase("Pago")) {
			historicocomissao.getContrato().setUltimamudancasituacao(new Date());
		}
		if (promotora == null || promotora.getIdpromotora() == null) {
			PromotoraFacade promotoraFacade = new PromotoraFacade();
			promotora = promotoraFacade.consultar(1);
		}
		historicocomissao.getContrato().setPromotora(promotora);
		ContratoFacade contratoFacade = new ContratoFacade();
		contratoFacade.salvar(historicocomissao.getContrato());
		historicoComissaoFacade.salvar(historicocomissao);
		if (!alteracoesBean.getDescricao().equalsIgnoreCase(historicocomissao.getContrato().getSituacao().getDescricao())) {
			Historicousuario historicousuario = new Historicousuario();
			salvarHistorico(historicousuario);
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("tipoFiltro", tipoFiltro);
		session.setAttribute("convenio", convenio);
		return "consPagamentoComissao";
	}
	
	
	public void gerarListaSituacao() {
		SituacaoFacade situacaoFacade = new SituacaoFacade();
		String sql = "Select s From Situacao s WHERE s.visualizar=true ";
		if (historicocomissao.getContrato().getTipooperacao().getIdtipooperacao() != 1) {
			sql = sql + " AND s.portabilidade=false ";
		}
		sql = sql + " ORDER BY s.descricao";
		listaSituacao = situacaoFacade.lista(sql);
		if (listaSituacao == null) {
			listaSituacao = new ArrayList<Situacao>();
		}
	}
	
	
	public void calcularValores() {
		CoeficienteFacade coeficienteFacade = new CoeficienteFacade();
		Coeficiente coeficiente = coeficienteFacade.consultar(historicocomissao.getContrato().getIdregracoeficiente());
		historicocomissao.setCmdbruta(historicocomissao.getProdliq() 
				* (coeficiente.getComissaoloja() / 100));
		historicocomissao.setCmsliq(historicocomissao.getProdliq() 
				* (coeficiente.getComissaocorretor() / 100));
		historicocomissao.setComissaototal(historicocomissao.getCmdbruta() + historicocomissao.getCmsliq());
	}
	
	
	public void gerarListaPromotora() {
		PromotoraFacade promotoraFacade = new PromotoraFacade();
		listaPromotora = promotoraFacade.lista("SELECT p From Promotora p WHERE p.idpromotora>1");
		if (listaPromotora == null) {
			listaPromotora = new ArrayList<Promotora>();
		}
	}
	
	
	public void salvarHistorico(Historicousuario historicousuario) {
		HistoricoUsuarioFacade historicoUsuarioFacade = new HistoricoUsuarioFacade();
		historicousuario.setDatacadastro(new Date());
		historicousuario.setHora(Formatacao.foramtarHoraString());
		historicousuario.setUsuario(usuarioLogadoMB.getUsuario());
		historicousuario.setDescricao("Situação alterada de " + alteracoesBean.getDescricao() +
				" para " + historicocomissao.getContrato().getSituacao().getDescricao());
		historicoUsuarioFacade.salvar(historicousuario);
	}
	
	
	
	
	

}
