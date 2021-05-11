package br.com.deltafinanceira.managebean.relatorios;

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

import br.com.deltafinanceira.facade.ComisaoVendaFacade;
import br.com.deltafinanceira.facade.TipoOperacaoFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Comissaovenda;
import br.com.deltafinanceira.model.Historicocomissao;
import br.com.deltafinanceira.model.Tipooperacao;
import br.com.deltafinanceira.model.Usuario;
import br.com.deltafinanceira.util.Formatacao;
import br.com.deltafinanceira.util.Mensagem;
import br.com.deltafinanceira.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class ComissaoSupervisorMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private List<Comissaovenda> listaComissao;
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
	private float valorSupervisor;
	private float valorCorretor;
	private float valorProducao;
	private Date dataini;
	private Date datafin;
	private boolean selecionarTodos;
	private Date dataCadastroIni;
	private Date dataCadastroFinal;
	private float valortotal;

	@PostConstruct
	public void init() {
		gerarListaInicial();
		gerarListaUsuario();
		gerarListaTipoOperacao();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCdinterno() {
		return cdinterno;
	}

	public void setCdinterno(int cdinterno) {
		this.cdinterno = cdinterno;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(int dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	public List<Tipooperacao> getListaTipoOperacao() {
		return listaTipoOperacao;
	}

	public void setListaTipoOperacao(List<Tipooperacao> listaTipoOperacao) {
		this.listaTipoOperacao = listaTipoOperacao;
	}

	public Tipooperacao getTipooiperacao() {
		return tipooiperacao;
	}

	public void setTipooiperacao(Tipooperacao tipooiperacao) {
		this.tipooiperacao = tipooiperacao;
	}

	public boolean isUnicoUsuario() {
		return unicoUsuario;
	}

	public void setUnicoUsuario(boolean unicoUsuario) {
		this.unicoUsuario = unicoUsuario;
	}

	public UsuarioLogadoMB getUsuarioLogadoMB() {
		return usuarioLogadoMB;
	}

	public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
		this.usuarioLogadoMB = usuarioLogadoMB;
	}

	public int getnPortabilidade() {
		return nPortabilidade;
	}

	public void setnPortabilidade(int nPortabilidade) {
		this.nPortabilidade = nPortabilidade;
	}

	public int getnMargem() {
		return nMargem;
	}

	public void setnMargem(int nMargem) {
		this.nMargem = nMargem;
	}

	public int getnCartao() {
		return nCartao;
	}

	public void setnCartao(int nCartao) {
		this.nCartao = nCartao;
	}

	public int getnRefin() {
		return nRefin;
	}

	public void setnRefin(int nRefin) {
		this.nRefin = nRefin;
	}

	public float getValorSupervisor() {
		return valorSupervisor;
	}

	public void setValorSupervisor(float valorSupervisor) {
		this.valorSupervisor = valorSupervisor;
	}

	public float getValorCorretor() {
		return valorCorretor;
	}

	public void setValorCorretor(float valorCorretor) {
		this.valorCorretor = valorCorretor;
	}

	public float getValorProducao() {
		return valorProducao;
	}

	public void setValorProducao(float valorProducao) {
		this.valorProducao = valorProducao;
	}

	public Date getDataini() {
		return dataini;
	}

	public void setDataini(Date dataini) {
		this.dataini = dataini;
	}

	public Date getDatafin() {
		return datafin;
	}

	public void setDatafin(Date datafin) {
		this.datafin = datafin;
	}

	public boolean isSelecionarTodos() {
		return selecionarTodos;
	}

	public void setSelecionarTodos(boolean selecionarTodos) {
		this.selecionarTodos = selecionarTodos;
	}

	public Date getDataCadastroIni() {
		return dataCadastroIni;
	}

	public void setDataCadastroIni(Date dataCadastroIni) {
		this.dataCadastroIni = dataCadastroIni;
	}

	public Date getDataCadastroFinal() {
		return dataCadastroFinal;
	}

	public void setDataCadastroFinal(Date dataCadastroFinal) {
		this.dataCadastroFinal = dataCadastroFinal;
	}

	public float getValortotal() {
		return valortotal;
	}

	public void setValortotal(float valortotal) {
		this.valortotal = valortotal;
	}

	public void gerarListaInicial() {
		ComisaoVendaFacade comisaoVendaFacade = new ComisaoVendaFacade();
		String sql = "Select c From Comissaovenda c WHERE c.contrato.simulacao=false and c.baixa=false";
		sql = sql + " order by c.contrato.datapagamento";
		listaComissao = comisaoVendaFacade.lista(sql);
		if (listaComissao == null) {
			listaComissao = new ArrayList<Comissaovenda>();
		}
		valorCorretor = 0.0f;
		valorSupervisor = 0.0f;
		nCartao = 0;
		nMargem = 0;
		nPortabilidade = 0;
		nRefin = 0;
		valorProducao = 0.0f;
		valortotal = 0.0f;
		for (int i = 0; i < listaComissao.size(); i++) {
			if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 1) {
				nPortabilidade = nPortabilidade + 1;
			} else if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 2
					|| listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 12) {
				nMargem = nMargem + 1;
			} else if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 7
					|| listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 8
					|| listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 9) {
				nRefin = nRefin + 1;
			} else {
				nCartao = nCartao + 1;
			}
			valorCorretor = valorCorretor + listaComissao.get(i).getComissaocorretor();
			valorSupervisor = valorSupervisor + listaComissao.get(i).getComissaovenda();
			valorProducao = valorProducao + listaComissao.get(i).getProdliq();
		}
	}

	public void pesquisar() {
		String sql = "Select c From Comissaovenda c Where c.contrato.simulacao=false and c.baixa=false";
		if (tipooiperacao != null && tipooiperacao.getIdtipooperacao() != null) {
			sql = sql + " and c.contrato.tipooperacao.idtipooperacao=" + tipooiperacao.getIdtipooperacao();
		}

		if (dataCadastroIni != null && dataCadastroFinal != null) {
			sql = sql + " and c.datalancamento>='" + Formatacao.ConvercaoDataNfe(dataCadastroIni) + "'"
					+ " and c.datalancamento<='" + Formatacao.ConvercaoDataNfe(dataCadastroFinal) + "'";
		}
		if (usuario != null && usuario.getIdusuario() != null) {
			sql = sql + " and c.usuario.idusuario=" + usuario.getIdusuario();
		}
		sql = sql + " order by c.contrato.datapagamento";
		ComisaoVendaFacade comisaoVendaFacade = new ComisaoVendaFacade();
		listaComissao = comisaoVendaFacade.lista(sql);
		if (listaComissao == null) {
			listaComissao = new ArrayList<Comissaovenda>();
		}
		valorCorretor = 0.0f;
		valorSupervisor = 0.0f;
		nCartao = 0;
		nMargem = 0;
		nPortabilidade = 0;
		nRefin = 0;
		valorProducao = 0.0f;
		valortotal = 0.0f;
		for (int i = 0; i < listaComissao.size(); i++) {
			if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 1) {
				nPortabilidade = nPortabilidade + 1;
			} else if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 2
					|| listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 12) {
				nMargem = nMargem + 1;
			} else if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 7
					|| listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 8
					|| listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 9) {
				nRefin = nRefin + 1;
			} else {
				nCartao = nCartao + 1;
			}
			valorCorretor = valorCorretor + listaComissao.get(i).getComissaocorretor();
			valorSupervisor = valorSupervisor + listaComissao.get(i).getComissaovenda();
			valorProducao = valorProducao + listaComissao.get(i).getProdliq();
		}
	}

	public void limpar() {
		dataLancamento = 0;
		usuario = null;
		tipooiperacao = null;
		datafin = null;
		dataini = null;
		dataCadastroFinal = null;
		dataCadastroIni = null;
		gerarListaInicial();
	}

	public String editar(Historicocomissao historicocomissao) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("historicocomissao", historicocomissao);
		return "editarComissao";
	}

	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		String sql = "Select u From Usuario u WHERE u.ativo=true";
		sql = sql + " and u.comissaovenda=true order by u.nome";
		listaUsuario = usuarioFacade.listar(sql);
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}

	public String fichaComissoes() {
		boolean selecionado = false;
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		List<Comissaovenda> listaSelecionado = new ArrayList<Comissaovenda>();
		for (int i = 0; i < listaComissao.size(); i++) {
			if (listaComissao.get(i).isSelecionado()) {
				listaSelecionado.add(listaComissao.get(i));
				selecionado = true;
			}
		}
		if (!selecionado) {
			listaSelecionado = listaComissao;
		}
		session.setAttribute("listaComissao", listaSelecionado);
		String periodo = "";
		if (dataCadastroIni != null && dataCadastroFinal != null) {
			periodo = Formatacao.ConvercaoDataPadrao(dataCadastroIni) + " a " + Formatacao.ConvercaoDataPadrao(dataCadastroFinal);
		} else {
			periodo = "Sem Periodo";
		}
		session.setAttribute("periodo", periodo);
		String supervisor = "";
		if (usuario != null) {
			supervisor = usuario.getNome();
		} else {
			supervisor = "Todos";
		}
		session.setAttribute("supervisor", supervisor);
		return "fichaComissaoSupervisor";
	}

	public void selecionarTodosContratos() {
		boolean tipo = true;
		if (selecionarTodos) {
			tipo = false;
		} else {
			tipo = true;
		}
		for (int i = 0; i < listaComissao.size(); i++) {
			listaComissao.get(i).setSelecionado(tipo);
		}
	}

	public List<Comissaovenda> getListaComissao() {
		return listaComissao;
	}

	public void setListaComissao(List<Comissaovenda> listaComissao) {
		this.listaComissao = listaComissao;
	}
	
	
	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t Where t.ativo=true");
		if (listaTipoOperacao == null) {
			listaTipoOperacao = new ArrayList<Tipooperacao>();
		}
	}
	 
	
	public void baixarTodos() {
		boolean selecionado = false;
		List<Comissaovenda> listaSelecionado = new ArrayList<Comissaovenda>();
		for (int i = 0; i < listaComissao.size(); i++) {
			if (listaComissao.get(i).isSelecionado()) {
				listaSelecionado.add(listaComissao.get(i));
				selecionado = true;
			}
		}
		if (!selecionado) {
			listaSelecionado = listaComissao;
		}
		ComisaoVendaFacade comisaoVendaFacade = new ComisaoVendaFacade();
		for (int i = 0; i < listaSelecionado.size(); i++) {
			listaSelecionado.get(i).setBaixa(true);
			listaSelecionado.get(i).setDescricaobaixa("thumbs-up");
			listaSelecionado.get(i).setCorbaixa("green");
			comisaoVendaFacade.salvar(listaSelecionado.get(i));
			listaComissao.remove(listaSelecionado.get(i));
		}
		Mensagem.lancarMensagemInfo("Baixa Realizada", "");
	}

}
