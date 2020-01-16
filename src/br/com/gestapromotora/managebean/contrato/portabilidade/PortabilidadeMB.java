package br.com.gestapromotora.managebean.contrato.portabilidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.bean.FiltrosBean;
import br.com.gestapromotora.facade.ContratoFacade;
import br.com.gestapromotora.facade.UsuarioFacade;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.Usuario;

@Named
@ViewScoped
public class PortabilidadeMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Contrato> listaContrato;
	private String situacao;
	private List<Usuario> listaUsuario;
	private String nomeCliente;
	private String cpf;
	private Usuario usuario;
	private boolean mudarsituacao = false;
	private boolean incompletos = false;
	private boolean digitadosPagDivida = false;
	private boolean dividaPaga = false;
	private boolean portabilidadeFin = false;
	private boolean refinanciamentoCriado = false;
	private boolean cancelados = false;
	private int nIncompletos;
	private int nDigitados;
	private int nDivida;
	private int nPortabilidade;
	private int nRefinanciamento;
	private int nCancelados;
	private int nAguardandoSolicitacao;
	private int nAguardandoDigitacao;
	private int nPendenciaDigitacao;
	private int nAguardandooperacional;
	private int nAguardandoCIP;
	private int nProblemasCIP;
	private int nProblemasCIPAguarOper;
	private int nSaldoAguarPagDivida;
	private int nSaldoAguarRespCorretor;
	private int nSaldoAguardandoOperacional;
	private int nIntencaoPortabilidade;
	private int nSemIntencao;
	private int nMargemLiberada;
	private int nProblemasAverbacao;
	private int nAverbacaoAguardandoOperacional;
	private int nPortabilidadeFinalizada;
	private int nMargemInsuficiente;
	private int nPortabilidadeAguardando;
	private int nAguardandoPagamento;
	private int nProblemasRefin;
	private int nProblemasRefinAguardandoOpe;
	private int nPagoCliente;
	private int nRefinaciamentoPortabilidade;
	private int nCanceladoBancoOperacional;
	private int nCanceladoCorretor;
	private int nCancelado;
	private List<FiltrosBean> listaFiltrosBean;
	private List<Contrato> listaContratoPesquisa;
	private int nSituacao;
	
	
	
	
	
	@PostConstruct
	public void init() {
		gerarListaUsuario();
		gerarListaInicial();
	}



	public List<Contrato> getListaContrato() {
		return listaContrato;
	}



	public void setListaContrato(List<Contrato> listaContrato) {
		this.listaContrato = listaContrato;
	}
	
	
	public String getSituacao() {
		return situacao;
	}



	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}



	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}



	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}



	public String getNomeCliente() {
		return nomeCliente;
	}



	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public boolean isMudarsituacao() {
		return mudarsituacao;
	}



	public void setMudarsituacao(boolean mudarsituacao) {
		this.mudarsituacao = mudarsituacao;
	}



	public boolean isIncompletos() {
		return incompletos;
	}



	public void setIncompletos(boolean incompletos) {
		this.incompletos = incompletos;
	}



	public boolean isDigitadosPagDivida() {
		return digitadosPagDivida;
	}



	public void setDigitadosPagDivida(boolean digitadosPagDivida) {
		this.digitadosPagDivida = digitadosPagDivida;
	}



	public boolean isDividaPaga() {
		return dividaPaga;
	}



	public void setDividaPaga(boolean dividaPaga) {
		this.dividaPaga = dividaPaga;
	}



	public boolean isPortabilidadeFin() {
		return portabilidadeFin;
	}



	public void setPortabilidadeFin(boolean portabilidadeFin) {
		this.portabilidadeFin = portabilidadeFin;
	}



	public boolean isRefinanciamentoCriado() {
		return refinanciamentoCriado;
	}



	public void setRefinanciamentoCriado(boolean refinanciamentoCriado) {
		this.refinanciamentoCriado = refinanciamentoCriado;
	}



	public boolean isCancelados() {
		return cancelados;
	}



	public void setCancelados(boolean cancelados) {
		this.cancelados = cancelados;
	}



	public int getnIncompletos() {
		return nIncompletos;
	}



	public void setnIncompletos(int nIncompletos) {
		this.nIncompletos = nIncompletos;
	}



	public int getnDigitados() {
		return nDigitados;
	}



	public void setnDigitados(int nDigitados) {
		this.nDigitados = nDigitados;
	}



	public int getnDivida() {
		return nDivida;
	}



	public void setnDivida(int nDivida) {
		this.nDivida = nDivida;
	}



	public int getnPortabilidade() {
		return nPortabilidade;
	}



	public void setnPortabilidade(int nPortabilidade) {
		this.nPortabilidade = nPortabilidade;
	}



	public int getnRefinanciamento() {
		return nRefinanciamento;
	}



	public void setnRefinanciamento(int nRefinanciamento) {
		this.nRefinanciamento = nRefinanciamento;
	}



	public int getnCancelados() {
		return nCancelados;
	}



	public void setnCancelados(int nCancelados) {
		this.nCancelados = nCancelados;
	}



	public int getnAguardandoSolicitacao() {
		return nAguardandoSolicitacao;
	}



	public void setnAguardandoSolicitacao(int nAguardandoSolicitacao) {
		this.nAguardandoSolicitacao = nAguardandoSolicitacao;
	}



	public int getnAguardandoDigitacao() {
		return nAguardandoDigitacao;
	}



	public void setnAguardandoDigitacao(int nAguardandoDigitacao) {
		this.nAguardandoDigitacao = nAguardandoDigitacao;
	}



	public int getnPendenciaDigitacao() {
		return nPendenciaDigitacao;
	}



	public void setnPendenciaDigitacao(int nPendenciaDigitacao) {
		this.nPendenciaDigitacao = nPendenciaDigitacao;
	}



	public int getnAguardandooperacional() {
		return nAguardandooperacional;
	}



	public void setnAguardandooperacional(int nAguardandooperacional) {
		this.nAguardandooperacional = nAguardandooperacional;
	}



	public int getnAguardandoCIP() {
		return nAguardandoCIP;
	}



	public void setnAguardandoCIP(int nAguardandoCIP) {
		this.nAguardandoCIP = nAguardandoCIP;
	}



	public int getnProblemasCIP() {
		return nProblemasCIP;
	}



	public void setnProblemasCIP(int nProblemasCIP) {
		this.nProblemasCIP = nProblemasCIP;
	}



	public int getnProblemasCIPAguarOper() {
		return nProblemasCIPAguarOper;
	}



	public void setnProblemasCIPAguarOper(int nProblemasCIPAguarOper) {
		this.nProblemasCIPAguarOper = nProblemasCIPAguarOper;
	}



	public int getnSaldoAguarPagDivida() {
		return nSaldoAguarPagDivida;
	}



	public void setnSaldoAguarPagDivida(int nSaldoAguarPagDivida) {
		this.nSaldoAguarPagDivida = nSaldoAguarPagDivida;
	}



	public List<FiltrosBean> getListaFiltrosBean() {
		return listaFiltrosBean;
	}



	public void setListaFiltrosBean(List<FiltrosBean> listaFiltrosBean) {
		this.listaFiltrosBean = listaFiltrosBean;
	}



	public List<Contrato> getListaContratoPesquisa() {
		return listaContratoPesquisa;
	}



	public void setListaContratoPesquisa(List<Contrato> listaContratoPesquisa) {
		this.listaContratoPesquisa = listaContratoPesquisa;
	}



	public int getnSaldoAguarRespCorretor() {
		return nSaldoAguarRespCorretor;
	}



	public void setnSaldoAguarRespCorretor(int nSaldoAguarRespCorretor) {
		this.nSaldoAguarRespCorretor = nSaldoAguarRespCorretor;
	}



	public int getnSaldoAguardandoOperacional() {
		return nSaldoAguardandoOperacional;
	}



	public void setnSaldoAguardandoOperacional(int nSaldoAguardandoOperacional) {
		this.nSaldoAguardandoOperacional = nSaldoAguardandoOperacional;
	}



	public int getnIntencaoPortabilidade() {
		return nIntencaoPortabilidade;
	}



	public void setnIntencaoPortabilidade(int nIntencaoPortabilidade) {
		this.nIntencaoPortabilidade = nIntencaoPortabilidade;
	}



	public int getnSemIntencao() {
		return nSemIntencao;
	}



	public void setnSemIntencao(int nSemIntencao) {
		this.nSemIntencao = nSemIntencao;
	}



	public int getnMargemLiberada() {
		return nMargemLiberada;
	}



	public void setnMargemLiberada(int nMargemLiberada) {
		this.nMargemLiberada = nMargemLiberada;
	}



	public int getnProblemasAverbacao() {
		return nProblemasAverbacao;
	}



	public void setnProblemasAverbacao(int nProblemasAverbacao) {
		this.nProblemasAverbacao = nProblemasAverbacao;
	}



	public int getnAverbacaoAguardandoOperacional() {
		return nAverbacaoAguardandoOperacional;
	}



	public void setnAverbacaoAguardandoOperacional(int nAverbacaoAguardandoOperacional) {
		this.nAverbacaoAguardandoOperacional = nAverbacaoAguardandoOperacional;
	}



	public int getnPortabilidadeFinalizada() {
		return nPortabilidadeFinalizada;
	}



	public void setnPortabilidadeFinalizada(int nPortabilidadeFinalizada) {
		this.nPortabilidadeFinalizada = nPortabilidadeFinalizada;
	}



	public int getnMargemInsuficiente() {
		return nMargemInsuficiente;
	}



	public void setnMargemInsuficiente(int nMargemInsuficiente) {
		this.nMargemInsuficiente = nMargemInsuficiente;
	}



	public int getnPortabilidadeAguardando() {
		return nPortabilidadeAguardando;
	}



	public void setnPortabilidadeAguardando(int nPortabilidadeAguardando) {
		this.nPortabilidadeAguardando = nPortabilidadeAguardando;
	}



	public int getnAguardandoPagamento() {
		return nAguardandoPagamento;
	}



	public void setnAguardandoPagamento(int nAguardandoPagamento) {
		this.nAguardandoPagamento = nAguardandoPagamento;
	}



	public int getnProblemasRefin() {
		return nProblemasRefin;
	}



	public void setnProblemasRefin(int nProblemasRefin) {
		this.nProblemasRefin = nProblemasRefin;
	}



	public int getnProblemasRefinAguardandoOpe() {
		return nProblemasRefinAguardandoOpe;
	}



	public void setnProblemasRefinAguardandoOpe(int nProblemasRefinAguardandoOpe) {
		this.nProblemasRefinAguardandoOpe = nProblemasRefinAguardandoOpe;
	}



	public int getnPagoCliente() {
		return nPagoCliente;
	}



	public void setnPagoCliente(int nPagoCliente) {
		this.nPagoCliente = nPagoCliente;
	}



	public int getnRefinaciamentoPortabilidade() {
		return nRefinaciamentoPortabilidade;
	}



	public void setnRefinaciamentoPortabilidade(int nRefinaciamentoPortabilidade) {
		this.nRefinaciamentoPortabilidade = nRefinaciamentoPortabilidade;
	}



	public int getnCanceladoBancoOperacional() {
		return nCanceladoBancoOperacional;
	}



	public void setnCanceladoBancoOperacional(int nCanceladoBancoOperacional) {
		this.nCanceladoBancoOperacional = nCanceladoBancoOperacional;
	}



	public int getnCanceladoCorretor() {
		return nCanceladoCorretor;
	}



	public void setnCanceladoCorretor(int nCanceladoCorretor) {
		this.nCanceladoCorretor = nCanceladoCorretor;
	}



	public int getnCancelado() {
		return nCancelado;
	}



	public void setnCancelado(int nCancelado) {
		this.nCancelado = nCancelado;
	}



	public int getnSituacao() {
		return nSituacao;
	}



	public void setnSituacao(int nSituacao) {
		this.nSituacao = nSituacao;
	}



	public void gerarListaPortabilidade(int situacao) {
		ContratoFacade contratoFacade = new ContratoFacade();
		listaContrato = contratoFacade.lista("Select c From Contrato c WHERE c.tipooperacao.descricao like "
				+ "'%Portabilidade%' and c.situacao.idsituacao ="+ situacao);
		if (listaContrato == null) {
			listaContrato = new ArrayList<Contrato>();
		}
		mudarsituacao = true;
		nSituacao = situacao;
	}
	
	
	public String editar(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		session.setAttribute("orgaobanco", contrato.getValorescoeficiente().getCoeficiente().getOrgaoBanco());
		return "cadContrato";
	}
	
	
	public String alterarSituacao(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		session.setAttribute("voltar", "consPortabilidade");
		return "alterarSituacao";
	}
	
	

	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		listaUsuario = usuarioFacade.listar("Select u From Usuario u");
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}
	
	
	public void pesquisar() {
		String sql = "Select c From Contrato c WHERE c.tipooperacao.descricao like '%Portabilidade%' and c.cliente.nome like '%"+ nomeCliente +
				"%' and c.cliente.cpf like '%"+ cpf +"%' and c.situacao.idsituacao=" + nSituacao;
		if (usuario != null && usuario.getIdusuario() != null) {
			sql = sql + " and c.usuario.idusuario=" + usuario.getIdusuario();
		}
		ContratoFacade contratoFacade = new ContratoFacade();
		listaContrato = contratoFacade.lista(sql);
		if (listaContrato == null) {
			listaContrato = new ArrayList<Contrato>();
		}
	}
	
	public void limpar() {
		gerarListaPortabilidade(nSituacao);
		usuario = null;
		nomeCliente = "";
		cpf = "";
	}
	
	
	public void botaoInmcompletos() {
		if (incompletos) {
			incompletos = false;
			mudarsituacao = false;
		}else {
			incompletos = true;
			cancelados = false;
			digitadosPagDivida = false;
			portabilidadeFin = false;
			refinanciamentoCriado = false;
			dividaPaga = false;
			mudarsituacao = false;
			nAguardandoDigitacao = 0;
			nAguardandooperacional = 0;
			nAguardandoSolicitacao = 0;
			nPendenciaDigitacao = 0;
			for (int i = 0; i < listaContratoPesquisa.size(); i++) {
				if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 4) {
					nAguardandoSolicitacao = nAguardandoSolicitacao + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 1) {
					nAguardandoDigitacao = nAguardandoDigitacao + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 5) {
					nPendenciaDigitacao = nPendenciaDigitacao + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 6) {
					nAguardandooperacional = nAguardandooperacional + 1;
				}
			}
		}
	}
	
	public void botaoDigitados() {
		if (digitadosPagDivida) {
			digitadosPagDivida = false;
			mudarsituacao = false;
		}else {
			incompletos = false;
			cancelados = false;
			digitadosPagDivida = true;
			portabilidadeFin = false;
			refinanciamentoCriado = false;
			dividaPaga = false;
			mudarsituacao = false;
			nAguardandoCIP = 0;
			nProblemasCIP = 0;
			nProblemasCIPAguarOper = 0;
			nSaldoAguarPagDivida = 0;
			nSaldoAguarRespCorretor = 0;
			nSaldoAguardandoOperacional = 0;
			for (int i = 0; i < listaContratoPesquisa.size(); i++) {
				if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 7) {
					nAguardandoCIP = nAguardandoCIP + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 8) {
					nProblemasCIP = nProblemasCIP + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 9) {
					nProblemasCIPAguarOper = nProblemasCIPAguarOper + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 10) {
					nSaldoAguarPagDivida = nSaldoAguarPagDivida + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 11) {
					nSaldoAguarRespCorretor = nSaldoAguarRespCorretor + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 12) {
					nSaldoAguardandoOperacional = nSaldoAguardandoOperacional + 1;
				}
			}
		}
	}
	
	public void botaoDivida() {
		if (dividaPaga) {
			dividaPaga = false;
			mudarsituacao = false;
		}else {
			incompletos = false;
			cancelados = false;
			digitadosPagDivida = false;
			portabilidadeFin = false;
			refinanciamentoCriado = false;
			dividaPaga = true;
			mudarsituacao = false;
			nIntencaoPortabilidade = 0;
			nSemIntencao = 0;
			nMargemLiberada = 0;
			nProblemasAverbacao = 0;
			nAverbacaoAguardandoOperacional = 0;
			for (int i = 0; i < listaContratoPesquisa.size(); i++) {
				if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 27) {
					nIntencaoPortabilidade = nIntencaoPortabilidade + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 26) {
					nSemIntencao = nSemIntencao + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 25) {
					nMargemLiberada = nMargemLiberada + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 24) {
					nProblemasAverbacao = nProblemasAverbacao + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 23) {
					nAverbacaoAguardandoOperacional = nAverbacaoAguardandoOperacional + 1;
				}
			}
		}
	}
	
	public void botaoPortabilidade() {
		if (portabilidadeFin) {
			portabilidadeFin = false;
			mudarsituacao = false;
		}else {
			incompletos = false;
			cancelados = false;
			digitadosPagDivida = false;
			portabilidadeFin = true;
			refinanciamentoCriado = false;
			dividaPaga = false;
			mudarsituacao = false;
			nPortabilidadeFinalizada = 0;
			nMargemInsuficiente = 0;
			nPortabilidadeAguardando = 0;
			for (int i = 0; i < listaContratoPesquisa.size(); i++) {
				if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 22) {
					nPortabilidadeFinalizada = nPortabilidadeFinalizada + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 21) {
					nMargemInsuficiente = nMargemInsuficiente + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 20) {
					nPortabilidadeAguardando = nPortabilidadeAguardando + 1;
				}
			}
		}
	}

	
	public void botaoRefinanciamento() {
		if (refinanciamentoCriado) {
			refinanciamentoCriado = false;
			mudarsituacao = false;
		}else {
			incompletos = false;
			cancelados = false;
			digitadosPagDivida = false;
			portabilidadeFin = false;
			refinanciamentoCriado = true;
			dividaPaga = false;
			mudarsituacao = false;
			nAguardandoPagamento = 0;
			nProblemasRefin = 0;
			nProblemasRefinAguardandoOpe = 0;
			nPagoCliente = 0;
			nRefinaciamentoPortabilidade = 0;
			for (int i = 0; i < listaContratoPesquisa.size(); i++) {
				if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 19) {
					nAguardandoPagamento = nAguardandoPagamento + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 18) {
					nProblemasRefin = nProblemasRefin + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 17) {
					nProblemasRefinAguardandoOpe = nProblemasRefinAguardandoOpe + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 16) {
					nPagoCliente = nPagoCliente + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 15) {
					nRefinaciamentoPortabilidade = nRefinaciamentoPortabilidade + 1;
				}
			}
		}
	}

	
	public void botaoCancelados() {
		if (cancelados) {
			cancelados = false;
			mudarsituacao = false;
		}else {
			incompletos = false;
			cancelados = true;
			digitadosPagDivida = false;
			portabilidadeFin = false;
			refinanciamentoCriado = false;
			dividaPaga = false;
			mudarsituacao = false;
			nCanceladoBancoOperacional = 0;
			nCanceladoCorretor = 0;
			nCancelado = 0;
			for (int i = 0; i < listaContratoPesquisa.size(); i++) {
				if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 14) {
					nCanceladoBancoOperacional = nCanceladoBancoOperacional + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 13) {
					nCanceladoCorretor = nCanceladoCorretor + 1;
				}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 2) {
					nCancelado = nCancelado + 1;
				}
			}
		}
	}
	
	
	public void gerarListaInicial() {
		ContratoFacade contratoFacade = new ContratoFacade();
		listaContratoPesquisa = contratoFacade.lista("Select c From Contrato c");
		if (listaContratoPesquisa == null) {
			listaContratoPesquisa = new ArrayList<Contrato>();
		}
		for (int i = 0; i < listaContratoPesquisa.size(); i++) {
			if (listaContratoPesquisa.get(i).getSituacao().getIdentificador() == 1) {
				nIncompletos = nIncompletos + 1;
			}else if (listaContratoPesquisa.get(i).getSituacao().getIdentificador() == 2) {
				nDigitados = nDigitados + 1;
			}else if (listaContratoPesquisa.get(i).getSituacao().getIdentificador() == 3) {
				nDivida = nDivida + 1;
			}else if (listaContratoPesquisa.get(i).getSituacao().getIdentificador() == 4) {
				nPortabilidade = nPortabilidade + 1;
			}else if (listaContratoPesquisa.get(i).getSituacao().getIdentificador() == 5) {
				nRefinanciamento = nRefinanciamento + 1;
			}else if (listaContratoPesquisa.get(i).getSituacao().getIdentificador() == 6){
				nCancelados = nCancelados + 1;
			}
		}
	}
	
	
	
	
	
	
	

}
