package br.com.deltafinanceira.managebean.contrato;

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
import br.com.deltafinanceira.dao.NotificacaoDao;
import br.com.deltafinanceira.facade.BancoFacade;
import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.facade.HistoricoUsuarioFacade;
import br.com.deltafinanceira.facade.OrgaoBancoFacade;
import br.com.deltafinanceira.facade.RankingVendasAnualFacade;
import br.com.deltafinanceira.facade.RankingVendasFacade;
import br.com.deltafinanceira.facade.RegrasCoeficienteFacade;
import br.com.deltafinanceira.facade.SituacaoFacade;
import br.com.deltafinanceira.model.Banco;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Historicousuario;
import br.com.deltafinanceira.model.Notificacao;
import br.com.deltafinanceira.model.OrgaoBanco;
import br.com.deltafinanceira.model.Rankingvendas;
import br.com.deltafinanceira.model.Rankingvendasanual;
import br.com.deltafinanceira.model.Regrascoeficiente;
import br.com.deltafinanceira.model.Situacao;
import br.com.deltafinanceira.util.Formatacao;
import br.com.deltafinanceira.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class AlterarSituacaoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private Contrato contrato;
	private Situacao situacao;
	private List<Situacao> listaSituacao;
	private String voltar;
	private Regrascoeficiente regrascoeficiente;
	private AlteracaoBean alteracaoBean;
	private boolean valorliberado;
	private List<Banco> listaBanco;
	private List<OrgaoBanco> listaOrgao;
	private Banco banco;
	private OrgaoBanco orgaoBanco;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		contrato = (Contrato) session.getAttribute("contrato");
		session.removeAttribute("contrato");
		voltar = (String) session.getAttribute("voltar");
		session.removeAttribute("voltar");
		situacao = contrato.getSituacao();
		gerarListaSituacao();
		banco = contrato.getOrgaoBanco().getBanco();
		orgaoBanco = contrato.getOrgaoBanco();
		gerarListaBanco();
		gerarListaOrgao();
		//buscarRegraCoeficiente();
		alteracaoBean = new AlteracaoBean();
		alteracaoBean.setDescricao(contrato.getSituacao().getDescricao());
		if (usuarioLogadoMB.getUsuario().isAcessogeral() 
				|| usuarioLogadoMB.getUsuario().getTipocolaborador().getAcessocolaborador().isAcessooperacional()) {
			valorliberado = false;
		}else {
			valorliberado = true;
		}
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public List<Situacao> getListaSituacao() {
		return listaSituacao;
	}

	public void setListaSituacao(List<Situacao> listaSituacao) {
		this.listaSituacao = listaSituacao;
	}

	public Regrascoeficiente getRegrascoeficiente() {
		return regrascoeficiente;
	}

	public void setRegrascoeficiente(Regrascoeficiente regrascoeficiente) {
		this.regrascoeficiente = regrascoeficiente;
	}

	public String getVoltar() {
		return voltar;
	}

	public void setVoltar(String voltar) {
		this.voltar = voltar;
	}

	public AlteracaoBean getAlteracaoBean() {
		return alteracaoBean;
	}

	public void setAlteracaoBean(AlteracaoBean alteracaoBean) {
		this.alteracaoBean = alteracaoBean;
	}

	public boolean isValorliberado() {
		return valorliberado;
	}

	public void setValorliberado(boolean valorliberado) {
		this.valorliberado = valorliberado;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public OrgaoBanco getOrgaoBanco() {
		return orgaoBanco;
	}

	public void setOrgaoBanco(OrgaoBanco orgaoBanco) {
		this.orgaoBanco = orgaoBanco;
	}

	public void gerarListaSituacao() {
		SituacaoFacade situacaoFacade = new SituacaoFacade();
		String sql = "Select s From Situacao s WHERE s.visualizar=true ";
		if (contrato.getTipooperacao().getIdtipooperacao() != 1) {
			sql = sql + " AND s.portabilidade=false ";
		}
		sql = sql + " ORDER BY s.descricao";
		listaSituacao = situacaoFacade.lista(sql);
		if (listaSituacao == null) {
			listaSituacao = new ArrayList<Situacao>();
		}
	}

	public String cancelar() {
		return voltar;
	}

	public List<Banco> getListaBanco() {
		return listaBanco;
	}

	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}

	public List<OrgaoBanco> getListaOrgao() {
		return listaOrgao;
	}

	public void setListaOrgao(List<OrgaoBanco> listaOrgao) {
		this.listaOrgao = listaOrgao;
	}

	public String salvar() {
		ContratoFacade contratoFacade = new ContratoFacade();
		contrato.setSituacao(situacao);
		contrato.setUltimamudancasituacao(new Date());
		contrato.setOrgaoBanco(orgaoBanco);
		if (situacao.getIdsituacao() == 16) {
			contrato.setDatapagamento(new Date());
		}
		
//		else if (situacao.getIdsituacao() == 2) {
//			descontarRankingAnual();
//			descontarRankingMensal();
//		} 
		gerarNotificacao();
		contrato = contratoFacade.salvar(contrato);
		if (!alteracaoBean.getDescricao().equalsIgnoreCase(contrato.getSituacao().getDescricao())) {
			Historicousuario historicousuario = new Historicousuario();
			salvarHistorico(historicousuario);
		}
		return voltar;
	}

	public void gerarNotificacao() {
		NotificacaoDao notificacaoDao = new NotificacaoDao();
		Notificacao notificacao = new Notificacao();
		notificacao.setDatalancamento(new Date());
		notificacao.setVisto(false);
		notificacao.setUsuario(contrato.getUsuario());
		notificacao.setIdcontrato(contrato.getIdcontrato());
		notificacao.setTitulo("Contrato: " + contrato.getCodigocontrato());
		notificacao.setDescricao("Seu contrato do(a) cliente: " + contrato.getCliente().getNome()
				+ " mudou seu status para: " + situacao.getDescricao());
		notificacaoDao.salvar(notificacao);
	}

	public void descontarRankingMensal() {
		RankingVendasFacade rankingVendasFacade = new RankingVendasFacade();
		Rankingvendas rankingvendas;
		int mes = Formatacao.getMesData(new Date()) + 1;
		int ano = Formatacao.getAnoData(new Date());
		List<Rankingvendas> listaRanking = rankingVendasFacade.lista("Select r From Rankingvendas r WHERE r.mes=" + mes
				+ " AND r.ano=" + ano + " AND r.usuario.idusuario=" + contrato.getUsuario().getIdusuario());
		if (listaRanking != null && listaRanking.size() > 0) {
			rankingvendas = listaRanking.get(0);
			if (contrato.getParcelaspagas() > 12 && contrato.getTipooperacao().getIdtipooperacao() == 1) {
				rankingvendas.setValorvenda(rankingvendas.getValorvenda()
						- (contrato.getValorquitar() * (regrascoeficiente.getFlatrecebidaregra() / 100)));
			} else {
				rankingvendas.setValorvenda(rankingvendas.getValorvenda()
						- (contrato.getValoroperacao() * (regrascoeficiente.getFlatrecebidaregra() / 100)));
			}
			rankingVendasFacade.salvar(rankingvendas);
		}
	}

	public void descontarRankingAnual() {
		RankingVendasAnualFacade rankingVendasFacade = new RankingVendasAnualFacade();
		Rankingvendasanual rankingvendas;
		int ano = Formatacao.getAnoData(new Date());
		List<Rankingvendasanual> listaRanking = rankingVendasFacade.lista("Select r From Rankingvendasanual r WHERE "
				+ " r.ano=" + ano + " AND r.usuario.idusuario=" + contrato.getUsuario().getIdusuario());
		if (listaRanking != null && listaRanking.size() > 0) {
			rankingvendas = listaRanking.get(0);
			if (contrato.getParcelaspagas() > 12 && contrato.getTipooperacao().getIdtipooperacao() == 1) {
				rankingvendas.setValorvenda(rankingvendas.getValorvenda()
						- (contrato.getValorquitar() * (regrascoeficiente.getFlatrecebidaregra() / 100)));
			} else {
				rankingvendas.setValorvenda(rankingvendas.getValorvenda()
						- (contrato.getValoroperacao() * (regrascoeficiente.getFlatrecebidaregra() / 100)));
			}
			rankingVendasFacade.salvar(rankingvendas);
		}
	}

	public void buscarRegraCoeficiente() {
		RegrasCoeficienteFacade regrasCoeficienteFacade = new RegrasCoeficienteFacade();
		regrascoeficiente = regrasCoeficienteFacade.consultar(contrato.getIdregracoeficiente());
	}
	
	
	public void salvarHistorico(Historicousuario historicousuario) {
		HistoricoUsuarioFacade historicoUsuarioFacade = new HistoricoUsuarioFacade();
		historicousuario.setDatacadastro(new Date());
		historicousuario.setTitulo("Alteração");
		historicousuario.setIcone("mudanca.png");
		historicousuario.setIdcontrato(contrato.getIdcontrato());
		historicousuario.setHora(Formatacao.foramtarHoraString());
		historicousuario.setUsuario(usuarioLogadoMB.getUsuario());
		historicousuario.setDescricao("Situação alterada de " + alteracaoBean.getDescricao() +
				" para " + contrato.getSituacao().getDescricao() + ", Tipo do contrato: " + contrato.getTipooperacao().getDescricao()
				+ ", Código do contrato: " + contrato.getCodigocontrato());
		historicoUsuarioFacade.salvar(historicousuario);
	}
	
	
	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		listaBanco = bancoFacade.lista("Select b From Banco b WHERE b.nome !='Nenhum' ORDER BY b.nome");
		if (listaBanco == null) {
			listaBanco = new ArrayList<Banco>();
		}
	}

	public void gerarListaOrgao() {
		OrgaoBancoFacade orgaoBancoFacade = new OrgaoBancoFacade();
		if (banco != null && banco.getIdbanco() != null) {
			listaOrgao = orgaoBancoFacade
					.lista("Select o From OrgaoBanco o WHERE o.banco.idbanco=" 
							+ banco.getIdbanco());
		}
		if (listaOrgao == null) {
			listaOrgao = new ArrayList<OrgaoBanco>();
		}
	}

}
