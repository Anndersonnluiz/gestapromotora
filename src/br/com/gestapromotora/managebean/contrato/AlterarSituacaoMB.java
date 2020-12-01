package br.com.gestapromotora.managebean.contrato;

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

import br.com.gestapromotora.bean.AlteracaoBean;
import br.com.gestapromotora.dao.NotificacaoDao;
import br.com.gestapromotora.facade.ContratoFacade;
import br.com.gestapromotora.facade.HistoricoUsuarioFacade;
import br.com.gestapromotora.facade.RankingVendasAnualFacade;
import br.com.gestapromotora.facade.RankingVendasFacade;
import br.com.gestapromotora.facade.RegrasCoeficienteFacade;
import br.com.gestapromotora.facade.SituacaoFacade;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.Historicousuario;
import br.com.gestapromotora.model.Notificacao;
import br.com.gestapromotora.model.Rankingvendas;
import br.com.gestapromotora.model.Rankingvendasanual;
import br.com.gestapromotora.model.Regrascoeficiente;
import br.com.gestapromotora.model.Situacao;
import br.com.gestapromotora.util.Formatacao;
import br.com.gestapromotora.util.UsuarioLogadoMB;

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
		buscarRegraCoeficiente();
		alteracaoBean = new AlteracaoBean();
		alteracaoBean.setDescricao(contrato.getSituacao().getDescricao());
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
		return "consPortabilidade";
	}

	public String salvar() {
		ContratoFacade contratoFacade = new ContratoFacade();
		contrato.setSituacao(situacao);
		contrato.setUltimamudancasituacao(new Date());
		if (situacao.getIdsituacao() == 16) {
			contrato.setDatapagamento(new Date());
		} else if (situacao.getIdsituacao() == 2) {
			descontarRankingAnual();
			descontarRankingMensal();
		} 
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
		historicousuario.setHora(Formatacao.foramtarHoraString());
		historicousuario.setUsuario(usuarioLogadoMB.getUsuario());
		historicousuario.setDescricao("Situação alterada de " + alteracaoBean.getDescricao() +
				" para " + contrato.getSituacao().getDescricao() + ", Tipo do contrato: " + contrato.getTipooperacao().getDescricao()
				+ ", Código do contrato: " + contrato.getCodigocontrato());
		historicoUsuarioFacade.salvar(historicousuario);
	}

}
