package br.com.gestapromotora.managebean.financeiro;

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

import br.com.gestapromotora.facade.HistoricoComissaoFacade;
import br.com.gestapromotora.facade.TipoOperacaoFacade;
import br.com.gestapromotora.facade.UsuarioFacade;
import br.com.gestapromotora.model.Historicocomissao;
import br.com.gestapromotora.model.Tipooperacao;
import br.com.gestapromotora.model.Usuario;
import br.com.gestapromotora.util.Formatacao;
import br.com.gestapromotora.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class HistoricoComissaoMB implements Serializable{

	/**
	 * 
	 */
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
	private float valorRepassada;
	private float valorRecebida;
	private float valorProducao;
	private Date dataini;
	private Date datafin;
	private String tipoFiltro;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		tipoFiltro = (String) session.getAttribute("tipoFiltro");
		session.removeAttribute("tipoFiltro");
		if (tipoFiltro == null || tipoFiltro.isEmpty()) {
			tipoFiltro = "Todos";
		}
		gerarListaInicial();
		gerarListaUsuario();
		gerarListaTipoOperacao();
		if (!usuarioLogadoMB.getUsuario().isAcessogeral()) {
			unicoUsuario = true;
			usuario = usuarioLogadoMB.getUsuario();
		}
	}


	public List<Historicocomissao> getListaComissao() {
		return listaComissao;
	}


	public void setListaComissao(List<Historicocomissao> listaComissao) {
		this.listaComissao = listaComissao;
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


	public float getValorRepassada() {
		return valorRepassada;
	}


	public void setValorRepassada(float valorRepassada) {
		this.valorRepassada = valorRepassada;
	}


	public float getValorRecebida() {
		return valorRecebida;
	}


	public void setValorRecebida(float valorRecebida) {
		this.valorRecebida = valorRecebida;
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


	
	
	public void gerarListaInicial() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		String sql = "Select h From Historicocomissao h WHERE h.tipo='PENDENTE' and h.contrato.situacao.idsituacao<>2";
		if (!usuarioLogadoMB.getUsuario().isAcessogeral()) {
			sql = sql + " and h.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario();
		}
		if (tipoFiltro.equalsIgnoreCase("NaoPago")) {
			sql = sql + " and h.contrato.situacao.idsituacao<>16";
		}else if (tipoFiltro.equalsIgnoreCase("Pago")) {
			sql = sql + " and h.contrato.situacao.idsituacao=16";
		}
		listaComissao = historicoComissaoFacade.lista(sql);
		if (listaComissao == null) {
			listaComissao = new ArrayList<Historicocomissao>();
		}
		valorRecebida = 0.0f;
		valorRepassada = 0.0f;
		nCartao = 0;
		nMargem = 0;
		nPortabilidade = 0;
		nRefin = 0;
		valorProducao = 0.0f;
		for (int i = 0; i < listaComissao.size(); i++) {
			if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 1) {
				nPortabilidade = nPortabilidade + 1;
			}else if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 2) {
				nMargem = nMargem + 1;
			}else if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 7 ||
					listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 8 ||
					listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 9) {
				nRefin = nRefin + 1;
			}else {
				nCartao = nCartao + 1;
			}
			valorRecebida = valorRecebida + listaComissao.get(i).getCmdbruta();
			valorRepassada = valorRepassada + listaComissao.get(i).getCmsliq();
			valorProducao = valorProducao + listaComissao.get(i).getProdliq();
		}
	}
	
	
	public void pesquisar() {
		String sql = "Select h From Historicocomissao h Where h.contrato.situacao.idsituacao<>2";
		if (tipooiperacao != null && tipooiperacao.getIdtipooperacao() != null) {
			sql = sql + " and h.contrato.tipooperacao.idtipooperacao=" + tipooiperacao.getIdtipooperacao();
		}
		
		if (dataLancamento > 0) {
			sql = sql + " and h.mes=" + dataLancamento;
		}
		
		if (usuario != null && usuario.getIdusuario() != null) {
			sql = sql + " and h.usuario.idusuario=" + usuario.getIdusuario();
		}
		
		if (dataini != null && datafin != null) {
			sql = sql + " and h.datalancamento>='" + Formatacao.ConvercaoDataNfe(dataini)
				+ "' and h.datalancamento<='" + Formatacao.ConvercaoDataNfe(datafin)
				+ "'";
		}
		
		if (situacao > 0) {
			if (situacao == 1) {
				sql = sql + " and h.contrato.situacao.idsituacao=28";
			}else if(situacao == 2) {
				sql = sql + " and h.contrato.situacao.idsituacao=19";
			}else if(situacao == 3) {
				sql = sql + " and h.contrato.situacao.idsituacao=2";
			}else {
				sql = sql + " and h.contrato.situacao.idsituacao=16";
			}
		}
		if (!usuarioLogadoMB.getUsuario().isAcessogeral()) {
			sql = sql + " and h.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario();
		}
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		listaComissao = historicoComissaoFacade.lista(sql);
		if (listaComissao == null) {
			listaComissao = new ArrayList<Historicocomissao>();
		}
		valorRecebida = 0.0f;
		valorRepassada = 0.0f;
		nCartao = 0;
		nMargem = 0;
		nPortabilidade = 0;
		nRefin = 0;
		valorProducao = 0.0f;
		for (int i = 0; i < listaComissao.size(); i++) {
			if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 1) {
				nPortabilidade = nPortabilidade + 1;
			}else if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 2) {
				nMargem = nMargem + 1;
			}else if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 7 ||
					listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 8 ||
					listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 9) {
				nRefin = nRefin + 1;
			}else {
				nCartao = nCartao + 1;
			}
			valorRecebida = valorRecebida + listaComissao.get(i).getCmdbruta();
			valorRepassada = valorRepassada + listaComissao.get(i).getCmsliq();
			valorProducao = valorProducao + listaComissao.get(i).getProdliq();
		}
	}
	
	public void limpar() {
		dataLancamento = 0;
		if (usuarioLogadoMB.getUsuario().isAcessogeral()) {
			usuario = null;
		}
		tipooiperacao = null;
		situacao =  0;
		datafin = null;
		dataini = null;
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
		listaUsuario = usuarioFacade.listar("Select u From Usuario u WHERE u.ativo=true");
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>(); 
		}
	}
	
	
	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t");
		if (listaTipoOperacao == null) {
			listaTipoOperacao = new ArrayList<Tipooperacao>();
		}
	}

}
