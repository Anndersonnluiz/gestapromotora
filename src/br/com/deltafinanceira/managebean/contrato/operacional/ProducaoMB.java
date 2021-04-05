package br.com.deltafinanceira.managebean.contrato.operacional;

import br.com.deltafinanceira.facade.HistoricoComissaoFacade;
import br.com.deltafinanceira.facade.TipoOperacaoFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Historicocomissao;
import br.com.deltafinanceira.model.Tipooperacao;
import br.com.deltafinanceira.model.Usuario;
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
public class ProducaoMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;

	private List<Historicocomissao> listaComissao;

	private String nome;

	private int cdinterno;

	private int dataLancamento;

	private List<Usuario> listaUsuario;

	private Usuario usuario;

	private int situacao;

	private List<Tipooperacao> listaTipoOperacao;

	private Tipooperacao tipooiperacao;

	private boolean unicoUsuario = false;

	private int nPortabilidade;

	private int nMargem;

	private int nCartao;

	private int nRefin;

	private float valorPortabilidade;

	private float valorMargem;

	private float valorCartao;

	private Date dataini;

	private Date datafin;

	private String cpf;

	private String statusTipo;

	private boolean selecionarTodos;

	private int nProducao;

	private float valorRefin;

	private float valorProducao;

	private Date dataCadastroIni;

	private Date dataCadastroFinal;

	private int convenio;

	private String nomecliente;

	@PostConstruct
	public void init() {
		gerarListaInicial();
		gerarListaUsuario();
		gerarListaTipoOperacao();
		if (!this.usuarioLogadoMB.getUsuario().isAcessogeral()
				&& !this.usuarioLogadoMB.getUsuario().isResponsaveldepartamento()) {
			this.unicoUsuario = true;
			this.usuario = this.usuarioLogadoMB.getUsuario();
		}
	}

	public List<Historicocomissao> getListaComissao() {
		return this.listaComissao;
	}

	public void setListaComissao(List<Historicocomissao> listaComissao) {
		this.listaComissao = listaComissao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCdinterno() {
		return this.cdinterno;
	}

	public void setCdinterno(int cdinterno) {
		this.cdinterno = cdinterno;
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

	public int getDataLancamento() {
		return this.dataLancamento;
	}

	public void setDataLancamento(int dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public int getSituacao() {
		return this.situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	public List<Tipooperacao> getListaTipoOperacao() {
		return this.listaTipoOperacao;
	}

	public void setListaTipoOperacao(List<Tipooperacao> listaTipoOperacao) {
		this.listaTipoOperacao = listaTipoOperacao;
	}

	public Tipooperacao getTipooiperacao() {
		return this.tipooiperacao;
	}

	public void setTipooiperacao(Tipooperacao tipooiperacao) {
		this.tipooiperacao = tipooiperacao;
	}

	public boolean isUnicoUsuario() {
		return this.unicoUsuario;
	}

	public void setUnicoUsuario(boolean unicoUsuario) {
		this.unicoUsuario = unicoUsuario;
	}

	public UsuarioLogadoMB getUsuarioLogadoMB() {
		return this.usuarioLogadoMB;
	}

	public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
		this.usuarioLogadoMB = usuarioLogadoMB;
	}

	public int getnPortabilidade() {
		return this.nPortabilidade;
	}

	public void setnPortabilidade(int nPortabilidade) {
		this.nPortabilidade = nPortabilidade;
	}

	public int getnMargem() {
		return this.nMargem;
	}

	public void setnMargem(int nMargem) {
		this.nMargem = nMargem;
	}

	public int getnCartao() {
		return this.nCartao;
	}

	public void setnCartao(int nCartao) {
		this.nCartao = nCartao;
	}

	public int getnRefin() {
		return this.nRefin;
	}

	public void setnRefin(int nRefin) {
		this.nRefin = nRefin;
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

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getStatusTipo() {
		return this.statusTipo;
	}

	public void setStatusTipo(String statusTipo) {
		this.statusTipo = statusTipo;
	}

	public boolean isSelecionarTodos() {
		return this.selecionarTodos;
	}

	public void setSelecionarTodos(boolean selecionarTodos) {
		this.selecionarTodos = selecionarTodos;
	}

	public int getnProducao() {
		return this.nProducao;
	}

	public void setnProducao(int nProducao) {
		this.nProducao = nProducao;
	}

	public float getValorPortabilidade() {
		return this.valorPortabilidade;
	}

	public void setValorPortabilidade(float valorPortabilidade) {
		this.valorPortabilidade = valorPortabilidade;
	}

	public float getValorMargem() {
		return this.valorMargem;
	}

	public void setValorMargem(float valorMargem) {
		this.valorMargem = valorMargem;
	}

	public float getValorCartao() {
		return this.valorCartao;
	}

	public void setValorCartao(float valorCartao) {
		this.valorCartao = valorCartao;
	}

	public float getValorRefin() {
		return this.valorRefin;
	}

	public void setValorRefin(float valorRefin) {
		this.valorRefin = valorRefin;
	}

	public float getValorProducao() {
		return this.valorProducao;
	}

	public void setValorProducao(float valorProducao) {
		this.valorProducao = valorProducao;
	}

	public Date getDataCadastroIni() {
		return this.dataCadastroIni;
	}

	public void setDataCadastroIni(Date dataCadastroIni) {
		this.dataCadastroIni = dataCadastroIni;
	}

	public Date getDataCadastroFinal() {
		return this.dataCadastroFinal;
	}

	public void setDataCadastroFinal(Date dataCadastroFinal) {
		this.dataCadastroFinal = dataCadastroFinal;
	}

	public int getConvenio() {
		return this.convenio;
	}

	public void setConvenio(int convenio) {
		this.convenio = convenio;
	}

	public String getNomecliente() {
		return this.nomecliente;
	}

	public void setNomecliente(String nomecliente) {
		this.nomecliente = nomecliente;
	}

	public void gerarListaInicial() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		String sql = "Select h From Historicocomissao h WHERE h.tipo='PENDENTE' and h.contrato.situacao.idsituacao<>2 and h.contrato.ultimamudancasituacao>='2020-11-01' and h.baixa=false and h.contrato.simulacao=false";
		if (!this.usuarioLogadoMB.getUsuario().isAcessogeral()
				&& this.usuarioLogadoMB.getUsuario().isResponsaveldepartamento()) {
			sql = String.valueOf(sql) + " and h.contrato.usuario.departamento.iddepartamento=7";
		}else {
			sql = sql + " and h.contrato.usuario.treinamento=false";
		}
		sql = String.valueOf(sql) + " order by h.contrato.ultimamudancasituacao";
		this.listaComissao = historicoComissaoFacade.lista(sql);
		if (this.listaComissao == null)
			this.listaComissao = new ArrayList<>();
		this.nCartao = 0;
		this.nMargem = 0;
		this.nPortabilidade = 0;
		this.nRefin = 0;
		this.valorProducao = 0.0F;
		this.nProducao = 0;
		this.valorCartao = 0.0F;
		this.valorMargem = 0.0F;
		this.valorPortabilidade = 0.0F;
		this.valorRefin = 0.0F;
		for (int i = 0; i < this.listaComissao.size(); i++) {
			if (((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao().getIdtipooperacao()
					.intValue() == 1) {
				this.nPortabilidade++;
				this.valorPortabilidade += ((Historicocomissao) this.listaComissao.get(i)).getProdliq();
			} else if (((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
					.getIdtipooperacao().intValue() == 2) {
				this.nMargem++;
				this.valorMargem += ((Historicocomissao) this.listaComissao.get(i)).getProdliq();
			} else if (((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
					.getIdtipooperacao().intValue() == 7
					|| ((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
							.getIdtipooperacao().intValue() == 8
					|| ((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
							.getIdtipooperacao().intValue() == 9) {
				this.nRefin++;
				this.valorRefin += ((Historicocomissao) this.listaComissao.get(i)).getProdliq();
			} else if (((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
					.getIdtipooperacao().intValue() == 3
					|| ((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
							.getIdtipooperacao().intValue() == 4
					|| ((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
							.getIdtipooperacao().intValue() == 5) {
				this.nCartao++;
				this.valorCartao += ((Historicocomissao) this.listaComissao.get(i)).getProdliq();
			}
			this.nProducao++;
			this.valorProducao += ((Historicocomissao) this.listaComissao.get(i)).getProdliq();
		}
	}

	public void pesquisar() {
		String sql = "Select h From Historicocomissao h Where h.contrato.situacao.idsituacao<>2 and h.contrato.simulacao=false and h.contrato.cliente.nome like '%"
				+ this.nomecliente + "%' and h.contrato.usuario.treinamento=false and h.contrato.cliente.cpf like '%"
				+ this.cpf + "%'";
		if (this.tipooiperacao != null && this.tipooiperacao.getIdtipooperacao() != null)
			sql = String.valueOf(sql) + " and h.contrato.tipooperacao.idtipooperacao="
					+ this.tipooiperacao.getIdtipooperacao();
		if (this.usuario != null && this.usuario.getIdusuario() != null)
			sql = String.valueOf(sql) + " and h.usuario.idusuario=" + this.usuario.getIdusuario();
		if (this.dataini != null && this.datafin != null)
			sql = String.valueOf(sql) + " and h.contrato.ultimamudancasituacao>='"
					+ Formatacao.ConvercaoDataNfe(this.dataini) + "' and h.contrato.ultimamudancasituacao<='"
					+ Formatacao.ConvercaoDataNfe(this.datafin) + "'";
		if (this.dataCadastroIni != null && this.dataCadastroFinal != null)
			sql = String.valueOf(sql) + " and h.contrato.datacadastro>='"
					+ Formatacao.ConvercaoDataNfe(this.dataCadastroIni) + "'" + " and h.contrato.datacadastro<='"
					+ Formatacao.ConvercaoDataNfe(this.dataCadastroFinal) + "'";
		if (this.situacao > 0)
			if (this.situacao == 1) {
				sql = String.valueOf(sql) + " and h.contrato.situacao.idsituacao=28";
			} else if (this.situacao == 2) {
				sql = String.valueOf(sql) + " and h.contrato.situacao.idsituacao=19";
			} else if (this.situacao == 3) {
				sql = String.valueOf(sql) + " and h.contrato.situacao.idsituacao=2";
			} else if (this.situacao == 4) {
				sql = String.valueOf(sql) + " and h.contrato.situacao.idsituacao=16 and h.tipo<>'Pago'";
			} else {
				sql = String.valueOf(sql) + " and h.contrato.situacao.idsituacao=36";
			}
		if (this.statusTipo != null && this.statusTipo.length() > 0)
			sql = String.valueOf(sql) + " and h.tipo='" + this.statusTipo + "'";
		if (this.usuarioLogadoMB.getUsuario().isSupervisao())
			sql = String.valueOf(sql) + " and h.usuario.idusuario<>6";
		if (this.convenio > 0)
			if (this.convenio == 1) {
				sql = String.valueOf(sql) + " and h.contrato.operacaoinss=true";
			} else if (this.convenio == 2) {
				sql = String.valueOf(sql) + " and h.contrato.operacaoinss=false";
			}
		sql = String.valueOf(sql) + " order by h.contrato.datapagamento";
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		this.listaComissao = historicoComissaoFacade.lista(sql);
		if (this.listaComissao == null)
			this.listaComissao = new ArrayList<>();
		this.nCartao = 0;
		this.nMargem = 0;
		this.nPortabilidade = 0;
		this.nRefin = 0;
		this.valorProducao = 0.0F;
		this.valorCartao = 0.0F;
		this.valorMargem = 0.0F;
		this.valorPortabilidade = 0.0F;
		this.valorRefin = 0.0F;
		this.nProducao = 0;
		for (int i = 0; i < this.listaComissao.size(); i++) {
			if (((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao().getIdtipooperacao()
					.intValue() == 1) {
				this.nPortabilidade++;
				this.valorPortabilidade += ((Historicocomissao) this.listaComissao.get(i)).getProdliq();
			} else if (((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
					.getIdtipooperacao().intValue() == 2) {
				this.nMargem++;
				this.valorMargem += ((Historicocomissao) this.listaComissao.get(i)).getProdliq();
			} else if (((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
					.getIdtipooperacao().intValue() == 7
					|| ((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
							.getIdtipooperacao().intValue() == 8
					|| ((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
							.getIdtipooperacao().intValue() == 9) {
				this.nRefin++;
				this.valorRefin += ((Historicocomissao) this.listaComissao.get(i)).getProdliq();
			} else if (((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
					.getIdtipooperacao().intValue() == 3
					|| ((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
							.getIdtipooperacao().intValue() == 4
					|| ((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
							.getIdtipooperacao().intValue() == 5) {
				this.nCartao++;
				this.valorCartao += ((Historicocomissao) this.listaComissao.get(i)).getProdliq();
			}
			this.nProducao++;
			this.valorProducao += ((Historicocomissao) this.listaComissao.get(i)).getProdliq();
		}
	}

	public void limpar() {
		this.dataLancamento = 0;
		if (this.usuarioLogadoMB.getUsuario().isAcessogeral())
			this.usuario = null;
		this.tipooiperacao = null;
		this.situacao = 0;
		this.datafin = null;
		this.dataini = null;
		this.cpf = "";
		this.dataCadastroFinal = null;
		this.dataCadastroIni = null;
		this.convenio = 0;
		this.nomecliente = "";
		gerarListaInicial();
	}

	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		String sql = "Select u From Usuario u WHERE u.ativo=true and u.treinamento=false";
		sql = String.valueOf(sql) + " and u.departamento.iddepartamento=7";
		this.listaUsuario = usuarioFacade.listar(sql);
		if (this.listaUsuario == null)
			this.listaUsuario = new ArrayList<>();
	}

	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		this.listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t");
		if (this.listaTipoOperacao == null)
			this.listaTipoOperacao = new ArrayList<>();
	}

	public String relatorioGeral() {
		boolean selecionado = false;
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		List<Historicocomissao> listaSelecionado = new ArrayList<>();
		for (int i = 0; i < this.listaComissao.size(); i++) {
			if (((Historicocomissao) this.listaComissao.get(i)).isSelecionado()) {
				listaSelecionado.add(this.listaComissao.get(i));
				selecionado = true;

			}
		}
		if (!selecionado) {
			listaSelecionado = this.listaComissao;
		}
		session.setAttribute("listaComissao", listaSelecionado);
		String periodo = "";
		if (this.dataCadastroIni != null && this.dataCadastroFinal != null) {
			periodo = String.valueOf(Formatacao.ConvercaoDataPadrao(this.dataCadastroIni)) + " a "
					+ Formatacao.ConvercaoDataPadrao(this.dataCadastroFinal);
		} else {
			periodo = "Sem Periodo";
		}
		session.setAttribute("periodo", periodo);
		String corretor = "";
		if (this.usuario != null) {
			corretor = this.usuario.getNome();
		} else {
			corretor = "Todos";
		}
		session.setAttribute("corretor", corretor);
		return "relatorioProducao";
	}
}
