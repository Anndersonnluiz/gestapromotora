package br.com.gestapromotora.managebean.contrato.portabilidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;


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
	
	
	
	
	
	@PostConstruct
	public void init() {
		gerarListaUsuario();
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



	public void gerarListaPortabilidade(String situacao) {
		ContratoFacade contratoFacade = new ContratoFacade();
		listaContrato = contratoFacade.lista("Select c From Contrato c WHERE c.tipooperacao.descricao like "
				+ "'%Portabilidade%' and c.situacao like '"+ situacao +"'");
		if (listaContrato == null) {
			listaContrato = new ArrayList<Contrato>();
		}
		mudarsituacao = true;
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
		String sql = "Select c From Contrato c WHERE c.tipooperacao.descricao like '%Portabilidade%' and c.cliente.nome like '%"+ nomeCliente +"%' and c.cliente.cpf like '%"+ cpf +"%'";
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
		gerarListaPortabilidade("");
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
		}
	}
	
	
	
	

}
