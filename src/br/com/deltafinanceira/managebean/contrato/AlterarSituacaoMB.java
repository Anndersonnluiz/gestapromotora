package br.com.deltafinanceira.managebean.contrato;

import br.com.deltafinanceira.bean.AlteracaoBean;
import br.com.deltafinanceira.dao.NotificacaoDao;
import br.com.deltafinanceira.facade.BancoFacade;
import br.com.deltafinanceira.facade.CoeficienteFacade;
import br.com.deltafinanceira.facade.ComisaoVendaFacade;
import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.facade.HistoricoComissaoFacade;
import br.com.deltafinanceira.facade.HistoricoUsuarioFacade;
import br.com.deltafinanceira.facade.OrgaoBancoFacade;
import br.com.deltafinanceira.facade.RankingVendasFacade;
import br.com.deltafinanceira.facade.SituacaoFacade;
import br.com.deltafinanceira.facade.UsuarioComissaoFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Banco;
import br.com.deltafinanceira.model.Coeficiente;
import br.com.deltafinanceira.model.Comissaovenda;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Historicocomissao;
import br.com.deltafinanceira.model.Historicousuario;
import br.com.deltafinanceira.model.Notificacao;
import br.com.deltafinanceira.model.OrgaoBanco;
import br.com.deltafinanceira.model.Rankingvendas;
import br.com.deltafinanceira.model.Situacao;
import br.com.deltafinanceira.model.Usuario;
import br.com.deltafinanceira.model.Usuariocomissao;
import br.com.deltafinanceira.util.Formatacao;
import br.com.deltafinanceira.util.Mensagem;
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

	private List<Usuario> listaUsuario;

	private Usuario operador;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		this.contrato = (Contrato) session.getAttribute("contrato");
		session.removeAttribute("contrato");
		this.voltar = (String) session.getAttribute("voltar");
		session.removeAttribute("voltar");
		this.situacao = this.contrato.getSituacao();
		gerarListaSituacao();
		this.banco = this.contrato.getOrgaoBanco().getBanco();
		this.orgaoBanco = this.contrato.getOrgaoBanco();
		gerarListaBanco();
		gerarListaOrgao();
		buscarCoeficiente();
		gerarListaUsuario();
		this.alteracaoBean = new AlteracaoBean();
		this.alteracaoBean.setDescricao(this.contrato.getSituacao().getDescricao());
		if (this.usuarioLogadoMB.getUsuario().isAcessogeral() || this.usuarioLogadoMB.getUsuario().getTipocolaborador()
				.getAcessocolaborador().isAcessooperacional()) {
			this.valorliberado = false;
		} else {
			this.valorliberado = true;
		}
		if (contrato.isPossuioperador()) {
			UsuarioFacade usuarioFacade = new UsuarioFacade();
			operador = usuarioFacade.consultar(contrato.getIdoperador());
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

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Usuario getOperador() {
		return operador;
	}

	public void setOperador(Usuario operador) {
		this.operador = operador;
	}

	public String salvar() {
		if (validarDados()) {
			ContratoFacade contratoFacade = new ContratoFacade();
			this.contrato.setSituacao(this.situacao);
			this.contrato.setUltimamudancasituacao(new Date());
			this.contrato.setOrgaoBanco(this.orgaoBanco);
			if (this.situacao.getIdsituacao().intValue() == 16) {
				if (contrato.getDatapagamento() == null) {
					this.contrato.setDatapagamento(new Date());
					if (contrato.getSituacao().getIdsituacao() == 2 || contrato.getSituacao().getIdsituacao() == 5
							|| contrato.getSituacao().getIdsituacao() == 12) {
						gerarRankingMensal();
					}
				}
			}
			gerarProducao();
			if (this.coeficiente != null && this.coeficiente.getIdcoeficiente() != null) {
				this.contrato.setIdregracoeficiente(this.coeficiente.getIdcoeficiente().intValue());
				gerarComissao();
			} else {
				this.contrato.setIdregracoeficiente(0);
			}
			gerarNotificacao();
			if (contrato.getSituacao().getIdsituacao() != 35) {
				contrato.setPossuioperador(true);
				if (operador != null && operador.getIdusuario() != null) {
					contrato.setIdoperador(operador.getIdusuario());
					contrato.setOperador(operador.getNome());
				}
			} else {
				contrato.setPossuioperador(false);
				contrato.setIdoperador(0);
			}
			this.contrato = contratoFacade.salvar(this.contrato);
			if (!this.alteracaoBean.getDescricao().equalsIgnoreCase(this.contrato.getSituacao().getDescricao())) {
				Historicousuario historicousuario = new Historicousuario();
				salvarHistorico(historicousuario);
			}
			return this.voltar;
		}
		return "";
	}

	public void gerarRankingMensal() {
		Rankingvendas rankingvendas;
		RankingVendasFacade rankingVendasFacade = new RankingVendasFacade();
		int mes = Formatacao.getMesData(new Date()) + 1;
		int ano = Formatacao.getAnoData(new Date());
		List<Rankingvendas> listaRanking = rankingVendasFacade.lista("Select r From Rankingvendas r WHERE r.mes=" + mes
				+ " AND r.ano=" + ano + " AND r.usuario.idusuario=" + this.contrato.getUsuario().getIdusuario());
		if (listaRanking != null && listaRanking.size() > 0) {
			rankingvendas = listaRanking.get(0);
		} else {
			rankingvendas = new Rankingvendas();
			rankingvendas.setAno(ano);
			rankingvendas.setMes(mes);
			rankingvendas.setUsuario(contrato.getUsuario());
		}

		rankingvendas.setComissaovenda(rankingvendas.getComissaovenda()
				+ this.contrato.getValorcliente() * this.coeficiente.getComissaoloja() / 100.0F);
		rankingvendas.setValorvenda(rankingvendas.getValorvenda() + this.contrato.getValorcliente());
		rankingVendasFacade.salvar(rankingvendas);
	}

	public boolean validarDados() {
		if (situacao == null || situacao.getIdsituacao() == null) {
			Mensagem.lancarMensagemInfo("Selecione a nova situação do contrato", "");
			return false;
		}
		if (orgaoBanco == null || orgaoBanco.getIdorgaobanco() == null) {
			Mensagem.lancarMensagemInfo("Selecione um novo Orgão", "");
			return false;
		}
		if (banco == null || banco.getIdbanco() == null) {
			Mensagem.lancarMensagemInfo("Selecione um novo Banco", "");
			return false;
		}
		return true;
	}

	public void gerarNotificacao() {
		NotificacaoDao notificacaoDao = new NotificacaoDao();
		Notificacao notificacao = new Notificacao();
		notificacao.setDatalancamento(new Date());
		notificacao.setVisto(false);
		notificacao.setUsuario(this.contrato.getUsuario());
		notificacao.setIdcontrato(this.contrato.getIdcontrato().intValue());
		notificacao.setTitulo("Contrato: " + this.contrato.getCodigocontrato());
		notificacao.setDescricao("Seu contrato do(a) cliente: " + this.contrato.getCliente().getNome()
				+ " mudou seu status para: " + this.situacao.getDescricao());
		notificacao.setTipooperacao(contrato.getTipooperacao().getDescricao());
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
		String convenio = "";
		if (contrato.isOperacaoinss()) {
			convenio = "INSS";
		} else {
			convenio = "SIAPE";
		}
		historicousuario.setDescricao("Situação alterada de " + this.alteracaoBean.getDescricao() + " para "
				+ this.contrato.getSituacao().getDescricao() + ", Tipo do contrato: "
				+ this.contrato.getTipooperacao().getDescricao() + " - " + convenio + ", Cliente: "
				+ contrato.getCliente().getNome());
		historicoUsuarioFacade.salvar(historicousuario);
	}

	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		this.listaBanco = bancoFacade.lista("Select b From Banco b ORDER BY b.nome");
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
		if (orgaoBanco != null && orgaoBanco.getIdorgaobanco() != null) {
			this.listaCoefiente = coeficienteFacade.lista("Select c From Coeficiente c WHERE c.orgaoBanco.idorgaobanco="
					+ this.orgaoBanco.getIdorgaobanco() + " AND c.ativo=true AND c.tipooperacao.idtipooperacao="
					+ this.contrato.getTipooperacao().getIdtipooperacao());
		}
		if (this.listaCoefiente == null)
			this.listaCoefiente = new ArrayList<>();
	}

	public void gerarProducao() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		Historicocomissao historicocomissao = historicoComissaoFacade
				.consultarPorContrato(this.contrato.getIdcontrato().intValue());
		if (historicocomissao == null) {
			historicocomissao = new Historicocomissao();
			historicocomissao.setDatalancamento(new Date());
			historicocomissao.setContrato(this.contrato);
			historicocomissao.setUsuario(this.contrato.getUsuario());
			historicocomissao.setTipo("PENDENTE");
			int mes = Formatacao.getMesData(new Date()) + 1;
			int ano = Formatacao.getAnoData(new Date());
			historicocomissao.setAno(ano);
			historicocomissao.setMes(mes);
		}
		if (this.contrato.getParcelaspagas() > 12
				&& this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
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
		Historicocomissao historicocomissao = historicoComissaoFacade
				.consultarPorContrato(this.contrato.getIdcontrato().intValue());
		if (historicocomissao == null) {
			historicocomissao = new Historicocomissao();
			historicocomissao.setDatalancamento(new Date());
			historicocomissao.setContrato(this.contrato);
			historicocomissao.setUsuario(this.contrato.getUsuario());
			historicocomissao.setTipo("PENDENTE");
			int mes = Formatacao.getMesData(new Date()) + 1;
			int ano = Formatacao.getAnoData(new Date());
			historicocomissao.setAno(ano);
			historicocomissao.setMes(mes);
		}
		Usuariocomissao usuariocomissao = buscarComissaoUsuario();
		if (this.contrato.getParcelaspagas() > 12
				&& this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
			historicocomissao
					.setCmsliq(this.contrato.getValorquitar() * usuariocomissao.getComissaocorretor() / 100.0F);
			historicocomissao.setCmdbruta(this.contrato.getValorquitar()
					* (this.coeficiente.getComissaototal() - usuariocomissao.getComissaocorretor()) / 100.0F);

			historicocomissao.setComissaototal(historicocomissao.getCmdbruta() + historicocomissao.getCmsliq());
		} else if (this.contrato.getTipooperacao().getIdtipooperacao().intValue() != 1) {
			historicocomissao
					.setCmsliq(this.contrato.getValorcliente() * usuariocomissao.getComissaocorretor() / 100.0F);
			historicocomissao.setCmdbruta(this.contrato.getValorcliente()
					* (this.coeficiente.getComissaototal() - usuariocomissao.getComissaocorretor()) / 100.0F);
			historicocomissao.setComissaototal(historicocomissao.getCmdbruta() + historicocomissao.getCmsliq());
		} else {
			historicocomissao.setCmdbruta(0.0F);
			historicocomissao.setCmsliq(0.0F);
			historicocomissao.setComissaototal(0.0F); 
		}
		if (contrato.getSituacao().getIdsituacao() == 16 && contrato.getUsuario().getIdusuario() != 13) {
			gerarComissaoSuperVisor(historicocomissao.getCmsliq());
		}
		historicoComissaoFacade.salvar(historicocomissao);
	}

	public void gerarComissaoSuperVisor(float comissaoCorretor) {
		List<Usuariocomissao> listaUsuariocomissao = buscarComissaoUsuarioSupervisor();
		for (int i = 0; i < listaUsuariocomissao.size(); i++) {
			if (contrato.isOperacaoinss()) {
				Comissaovenda comissaovenda = new Comissaovenda();
				comissaovenda.setComissaocorretor(comissaoCorretor);
				comissaovenda.setComissaovenda((contrato.getValorcliente() * listaUsuariocomissao.get(i).getComissaocorretor()) / 100.0F);
				comissaovenda.setContrato(contrato);
				comissaovenda.setDatalancamento(new Date());
				comissaovenda.setProdliq(contrato.getValorcliente());
				comissaovenda.setUsuario(listaUsuariocomissao.get(i).getUsuario());
				comissaovenda.setDescricao("Percentual da venda para o(a) Supervisor(a):" + listaUsuariocomissao.get(i).getUsuario().getNome());
				ComisaoVendaFacade comisaoVendaFacade = new ComisaoVendaFacade();
				comisaoVendaFacade.salvar(comissaovenda);
			}
		}

	}

	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		String sql = "Select u From Usuario u WHERE u.operador=true and u.treinamento=false order by u.nome";
		this.listaUsuario = usuarioFacade.listar(sql);
		if (this.listaUsuario == null)
			this.listaUsuario = new ArrayList<>();
	}

	public Usuariocomissao buscarComissaoUsuario() {
		UsuarioComissaoFacade usuarioComissaoFacade = new UsuarioComissaoFacade();
		List<Usuariocomissao> listaUsuarioComissao = usuarioComissaoFacade.listar(
				"Select u From Usuariocomissao u " + "Where u.usuario.idusuario=" + contrato.getUsuario().getIdusuario()
						+ " AND u.tipooperacao.idtipooperacao=" + contrato.getTipooperacao().getIdtipooperacao());
		if (listaUsuarioComissao == null) {
			listaUsuarioComissao = new ArrayList<Usuariocomissao>();
		}
		Usuariocomissao usuariocomissao;
		if (listaUsuarioComissao.size() > 0) {
			usuariocomissao = listaUsuarioComissao.get(0);
		} else {
			usuariocomissao = new Usuariocomissao();
			usuariocomissao.setComissaocorretor(0);
		}
		return usuariocomissao;
	}

	public List<Usuariocomissao> buscarComissaoUsuarioSupervisor() {
		UsuarioComissaoFacade usuarioComissaoFacade = new UsuarioComissaoFacade();
		List<Usuariocomissao> listaUsuarioComissao = usuarioComissaoFacade
				.listar("Select u From Usuariocomissao u " + "Where u.usuario.comissaovenda=true"
						+ " AND u.tipooperacao.idtipooperacao=" + contrato.getTipooperacao().getIdtipooperacao());
		if (listaUsuarioComissao == null) {
			listaUsuarioComissao = new ArrayList<Usuariocomissao>();
		}
		return listaUsuarioComissao;
	}

}
