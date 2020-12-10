package br.com.gestapromotora.managebean.contrato.operacional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
public class ProducaoMB implements Serializable{

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
	
	
	@PostConstruct
	public void init() {
		gerarListaInicial();
		gerarListaUsuario();
		gerarListaTipoOperacao();
		if (!usuarioLogadoMB.getUsuario().isAcessogeral() 
				|| !usuarioLogadoMB.getUsuario().isResponsaveldepartamento()) {
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


	
	
	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getStatusTipo() {
		return statusTipo;
	}


	public void setStatusTipo(String statusTipo) {
		this.statusTipo = statusTipo;
	}


	public boolean isSelecionarTodos() {
		return selecionarTodos;
	}


	public void setSelecionarTodos(boolean selecionarTodos) {
		this.selecionarTodos = selecionarTodos;
	}


	public int getnProducao() {
		return nProducao;
	}


	public void setnProducao(int nProducao) {
		this.nProducao = nProducao;
	}


	public float getValorPortabilidade() {
		return valorPortabilidade;
	}


	public void setValorPortabilidade(float valorPortabilidade) {
		this.valorPortabilidade = valorPortabilidade;
	}


	public float getValorMargem() {
		return valorMargem;
	}


	public void setValorMargem(float valorMargem) {
		this.valorMargem = valorMargem;
	}


	public float getValorCartao() {
		return valorCartao;
	}


	public void setValorCartao(float valorCartao) {
		this.valorCartao = valorCartao;
	}


	public float getValorRefin() {
		return valorRefin;
	}


	public void setValorRefin(float valorRefin) {
		this.valorRefin = valorRefin;
	}


	public float getValorProducao() {
		return valorProducao;
	}


	public void setValorProducao(float valorProducao) {
		this.valorProducao = valorProducao;
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


	public int getConvenio() {
		return convenio;
	}


	public void setConvenio(int convenio) {
		this.convenio = convenio;
	}


	public void gerarListaInicial() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		String sql = "Select h From Historicocomissao h WHERE h.tipo='PENDENTE' and h.contrato.situacao.idsituacao<>2"
				+ " and h.contrato.ultimamudancasituacao>='2020-11-01'";
		if (!usuarioLogadoMB.getUsuario().isAcessogeral() && usuarioLogadoMB.getUsuario().isResponsaveldepartamento()) {
			sql = sql + " and h.contrato.usuario.departamento.iddepartamento=" +
					usuarioLogadoMB.getUsuario().getDepartamento().getIddepartamento();
		}
		sql = sql + " order by h.contrato.ultimamudancasituacao";
		listaComissao = historicoComissaoFacade.lista(sql);
		if (listaComissao == null) {
			listaComissao = new ArrayList<Historicocomissao>();
		}
		nCartao = 0;
		nMargem = 0;
		nPortabilidade = 0;
		nRefin = 0;
		valorProducao = 0.0f;
		nProducao = 0;
		valorCartao = 0.00f;
		valorMargem = 0.00f;
		valorPortabilidade = 0.00f;
		valorRefin = 0.00f;
		for (int i = 0; i < listaComissao.size(); i++) {
			if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 1) {
				nPortabilidade = nPortabilidade + 1;
				valorPortabilidade = valorPortabilidade + listaComissao.get(i).getProdliq();
			}else if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 2) {
				nMargem = nMargem + 1;
				valorMargem = valorMargem + listaComissao.get(i).getProdliq();
			}else if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 7 ||
					listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 8 ||
					listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 9) {
				nRefin = nRefin + 1;
				valorRefin = valorRefin + listaComissao.get(i).getProdliq();
			}else if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 3 ||
					listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 4 ||
					listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 5){
				nCartao = nCartao + 1;
				valorCartao = valorCartao + listaComissao.get(i).getProdliq();
			}
			nProducao = nProducao + 1;
			valorProducao = valorProducao + listaComissao.get(i).getProdliq();
		}
	}
	
	
	public void pesquisar() {
		String sql = "Select h From Historicocomissao h Where h.tipo='PENDENTE' and h.contrato.situacao.idsituacao<>2";
		if (tipooiperacao != null && tipooiperacao.getIdtipooperacao() != null) {
			sql = sql + " and h.contrato.tipooperacao.idtipooperacao=" + tipooiperacao.getIdtipooperacao();
		}
		
		
		if (usuario != null && usuario.getIdusuario() != null) {
			sql = sql + " and h.usuario.idusuario=" + usuario.getIdusuario();
		}
		
		if (dataini != null && datafin != null) {
			sql = sql + " and h.contrato.ultimamudancasituacao>='" + Formatacao.ConvercaoDataNfe(dataini)
				+ "' and h.contrato.ultimamudancasituacao<='" + Formatacao.ConvercaoDataNfe(datafin)
				+ "'";
		}
		
		if (dataCadastroIni != null && dataCadastroFinal != null) {
			sql = sql + " and h.contrato.datacadastro>='" + Formatacao.ConvercaoDataNfe(dataCadastroIni) + "'"
					+ " and h.contrato.datacadastro<='" + Formatacao.ConvercaoDataNfe(dataCadastroFinal) + "'"; 
		}
		
		if (situacao > 0) {
			if (situacao == 1) {
				sql = sql + " and h.contrato.situacao.idsituacao=28";
			}else if(situacao == 2) {
				sql = sql + " and h.contrato.situacao.idsituacao=19";
			}else if(situacao == 3) {
				sql = sql + " and h.contrato.situacao.idsituacao=2";
			}else if(situacao == 4) {
				sql = sql + " and h.contrato.situacao.idsituacao=16 and h.tipo<>'Pago'";
			}else {
				sql = sql + " and h.contrato.situacao.idsituacao=36";
			}
		}
		if (statusTipo != null && statusTipo.length() > 0) {
			sql = sql + " and h.tipo='" + statusTipo + "'";
		}
		if (usuarioLogadoMB.getUsuario().isSupervisao()) {
			sql = sql + " and h.usuario.idusuario<>6";
		}
		if (convenio > 0) {
			if (convenio == 1) {
				sql = sql + " and h.contrato.operacaoinss=true";
			}else if(convenio == 2) {
				sql = sql + " and h.contrato.operacaoinss=false";
			}
		}
		sql = sql + " order by h.contrato.datapagamento";
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		listaComissao = historicoComissaoFacade.lista(sql);
		if (listaComissao == null) {
			listaComissao = new ArrayList<Historicocomissao>();
		} 
		nCartao = 0;
		nMargem = 0;
		nPortabilidade = 0;
		nRefin = 0;
		valorProducao = 0.0f;
		valorCartao = 0.00f;
		valorMargem = 0.00f;
		valorPortabilidade = 0.00f;
		valorRefin = 0.00f;
		nProducao= 0;
		for (int i = 0; i < listaComissao.size(); i++) {
			if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 1) {
				nPortabilidade = nPortabilidade + 1;
				valorPortabilidade = valorPortabilidade + listaComissao.get(i).getProdliq();
			}else if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 2) {
				nMargem = nMargem + 1;
				valorMargem = valorMargem + listaComissao.get(i).getProdliq();
			}else if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 7 ||
					listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 8 ||
					listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 9) {
				nRefin = nRefin + 1;
				valorRefin = valorRefin + listaComissao.get(i).getProdliq();
			}else if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 3 ||
					listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 4 ||
					listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 5){
				nCartao = nCartao + 1;
				valorCartao = valorCartao + listaComissao.get(i).getProdliq();
			}
			nProducao = nProducao + 1;
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
		cpf = "";
		dataCadastroFinal = null;
		dataCadastroIni = null;
		convenio = 0;
		gerarListaInicial();
	}
	
	
	
	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		String sql = "Select u From Usuario u WHERE u.ativo=true ";
		if (!usuarioLogadoMB.getUsuario().isAcessogeral() && 
				usuarioLogadoMB.getUsuario().isResponsaveldepartamento()) {
			sql = sql + " and u.departamento.iddepartamento=" 
					+ usuarioLogadoMB.getUsuario().getDepartamento().getIddepartamento();
		}
		listaUsuario = usuarioFacade.listar(sql);
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
