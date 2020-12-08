package br.com.gestapromotora.managebean.contrato.demaisoperacoes;

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

import br.com.gestapromotora.facade.BancoFacade;
import br.com.gestapromotora.facade.ContratoFacade;
import br.com.gestapromotora.facade.HistoricoComissaoFacade;
import br.com.gestapromotora.facade.RegrasCoeficienteFacade;
import br.com.gestapromotora.facade.UsuarioFacade;
import br.com.gestapromotora.model.Banco;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.Historicocomissao;
import br.com.gestapromotora.model.Regrascoeficiente;
import br.com.gestapromotora.model.Usuario;
import br.com.gestapromotora.util.Formatacao;
import br.com.gestapromotora.util.Mensagem;
import br.com.gestapromotora.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class DemaisOperacoesMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private List<Contrato> listaContrato;
	private String situacao;
	private List<Usuario> listaUsuario;
	private String nomeCliente;
	private String cpf;
	private Usuario usuario;
	private boolean mudarsituacao = false;
	private boolean incompletos = false;
	private boolean digitadoAndamento = false;
	private boolean pagoClientePendencia = false;
	private boolean cancelados = false;
	private int nIncompletos;
	private int nDigitados;
	private int nPagoCliente;
	private int nCancelados;
	private int nAguardandoSolicitacao;
	private int nAguardandoDigitacao;
	private int nPendenciaDocumentacao;
	private int nAguardandooperacional;
	private int nAguardandoPagamento;
	private int nInconsistenciaBanco;
	private int nInconsistenciaAguardando;
	private int nAguardandoAssinatura;
	private int nDigitadoPago;
	private int nLiberal;
	private int nMaloteNaoEnviado;
	private int nMaloteEnviado;
	private int nFormalizacaoDigital;
	private int nCanceladoBancoOperacional;
	private int nCanceladoCorretor;
	private int nCancelado;
	private List<Contrato> listaContratoPesquisa;
	private int nSituacao;
	private int nPendenciaAverbacao;
	private int nTodos;
	private List<Banco> listaBanco;
	private Banco banco;
	private boolean unicoUsuario;
	private int nFormalizacaoPendencia;
	
	
	
	@PostConstruct
	public void init() {
		gerarListaUsuario();
		gerarListaInicial();
		gerarListaBanco();
		if (!usuarioLogadoMB.getUsuario().isAcessogeral()
				&& !usuarioLogadoMB.getUsuario().isSupervisao()) {
			unicoUsuario = true;
			usuario = usuarioLogadoMB.getUsuario();
		}
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



	public boolean isDigitadoAndamento() {
		return digitadoAndamento;
	}



	public void setDigitadoAndamento(boolean digitadoAndamento) {
		this.digitadoAndamento = digitadoAndamento;
	}



	public boolean isPagoClientePendencia() {
		return pagoClientePendencia;
	}



	public void setPagoClientePendencia(boolean pagoClientePendencia) {
		this.pagoClientePendencia = pagoClientePendencia;
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



	public int getnPagoCliente() {
		return nPagoCliente;
	}



	public void setnPagoCliente(int nPagoCliente) {
		this.nPagoCliente = nPagoCliente;
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



	public int getnPendenciaDocumentacao() {
		return nPendenciaDocumentacao;
	}



	public void setnPendenciaDocumentacao(int nPendenciaDocumentacao) {
		this.nPendenciaDocumentacao = nPendenciaDocumentacao;
	}



	public int getnAguardandooperacional() {
		return nAguardandooperacional;
	}



	public void setnAguardandooperacional(int nAguardandooperacional) {
		this.nAguardandooperacional = nAguardandooperacional;
	}



	public int getnAguardandoPagamento() {
		return nAguardandoPagamento;
	}



	public void setnAguardandoPagamento(int nAguardandoPagamento) {
		this.nAguardandoPagamento = nAguardandoPagamento;
	}



	public int getnInconsistenciaBanco() {
		return nInconsistenciaBanco;
	}



	public void setnInconsistenciaBanco(int nInconsistenciaBanco) {
		this.nInconsistenciaBanco = nInconsistenciaBanco;
	}



	public int getnInconsistenciaAguardando() {
		return nInconsistenciaAguardando;
	}



	public void setnInconsistenciaAguardando(int nInconsistenciaAguardando) {
		this.nInconsistenciaAguardando = nInconsistenciaAguardando;
	}



	public int getnDigitadoPago() {
		return nDigitadoPago;
	}



	public void setnDigitadoPago(int nDigitadoPago) {
		this.nDigitadoPago = nDigitadoPago;
	}



	public int getnLiberal() {
		return nLiberal;
	}



	public void setnLiberal(int nLiberal) {
		this.nLiberal = nLiberal;
	}



	public int getnMaloteNaoEnviado() {
		return nMaloteNaoEnviado;
	}



	public void setnMaloteNaoEnviado(int nMaloteNaoEnviado) {
		this.nMaloteNaoEnviado = nMaloteNaoEnviado;
	}



	public int getnMaloteEnviado() {
		return nMaloteEnviado;
	}



	public void setnMaloteEnviado(int nMaloteEnviado) {
		this.nMaloteEnviado = nMaloteEnviado;
	}



	public int getnFormalizacaoDigital() {
		return nFormalizacaoDigital;
	}



	public void setnFormalizacaoDigital(int nFormalizacaoDigital) {
		this.nFormalizacaoDigital = nFormalizacaoDigital;
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



	public List<Contrato> getListaContratoPesquisa() {
		return listaContratoPesquisa;
	}



	public void setListaContratoPesquisa(List<Contrato> listaContratoPesquisa) {
		this.listaContratoPesquisa = listaContratoPesquisa;
	}



	public int getnSituacao() {
		return nSituacao;
	}



	public void setnSituacao(int nSituacao) {
		this.nSituacao = nSituacao;
	}
	
	
	public int getnPendenciaAverbacao() {
		return nPendenciaAverbacao;
	}



	public void setnPendenciaAverbacao(int nPendenciaAverbacao) {
		this.nPendenciaAverbacao = nPendenciaAverbacao;
	}



	public int getnTodos() {
		return nTodos;
	}



	public void setnTodos(int nTodos) {
		this.nTodos = nTodos;
	}



	public List<Banco> getListaBanco() {
		return listaBanco;
	}



	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}



	public Banco getBanco() {
		return banco;
	}



	public void setBanco(Banco banco) {
		this.banco = banco;
	}



	public boolean isUnicoUsuario() {
		return unicoUsuario;
	} 



	public void setUnicoUsuario(boolean unicoUsuario) {
		this.unicoUsuario = unicoUsuario;
	}



	public int getnAguardandoAssinatura() {
		return nAguardandoAssinatura;
	}



	public void setnAguardandoAssinatura(int nAguardandoAssinatura) {
		this.nAguardandoAssinatura = nAguardandoAssinatura;
	}



	public int getnFormalizacaoPendencia() {
		return nFormalizacaoPendencia;
	}



	public void setnFormalizacaoPendencia(int nFormalizacaoPendencia) {
		this.nFormalizacaoPendencia = nFormalizacaoPendencia;
	}



	public void gerarListaDemaisOperacoes(int situacao) {
		ContratoFacade contratoFacade = new ContratoFacade();
		String sql =  "Select c From Contrato c WHERE c.tipooperacao.descricao not like "
				+ "'%Portabilidade%' and c.operacaoinss=false";
		if (situacao > 0) {
			sql = sql + " and c.situacao.idsituacao ="+ situacao;
		}
		if (!usuarioLogadoMB.getUsuario().isAcessogeral()
				&& !usuarioLogadoMB.getUsuario().getTipocolaborador().getAcessocolaborador().isAcessooperacional()) {
			sql = sql + " and c.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario();
		}
		listaContrato = contratoFacade.lista(sql);
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
		session.setAttribute("voltar", "consDemaisOperacoes");
		return "alterarSituacao";
	}
	
	

	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		listaUsuario = usuarioFacade.listar("Select u From Usuario u order by u.nome");
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}
	
	
	public void pesquisar() {
		String sql = "Select c From Contrato c WHERE c.tipooperacao.descricao not like '%Portabilidade%' and c.cliente.nome like '%"+ nomeCliente +
				"%' and c.cliente.cpf like '%"+ cpf +"%'"
				+ " and c.operacaoinss=false ";
		if (nSituacao > 0) {
			sql = sql + " and c.situacao.idsituacao=" + nSituacao;
		}
		if (usuario != null && usuario.getIdusuario() != null  
				&& !usuarioLogadoMB.getUsuario().getTipocolaborador().getAcessocolaborador().isAcessooperacional()) {
			sql = sql + " and c.usuario.idusuario=" + usuario.getIdusuario();
		}
		if (banco != null && banco.getIdbanco() != null) {
			sql = sql + " and c.valorescoeficiente.coeficiente.orgaoBanco.banco.idbanco=" + banco.getIdbanco();
		}
		ContratoFacade contratoFacade = new ContratoFacade();
		listaContrato = contratoFacade.lista(sql);
		if (listaContrato == null) {
			listaContrato = new ArrayList<Contrato>();
		}
	}
	
	public void limpar() {
		gerarListaDemaisOperacoes(nSituacao);
		usuario = null;
		nomeCliente = "";
		cpf = "";
		banco = null;
	}
	
	

	
	
	
	
	public void gerarListaInicial() {
		ContratoFacade contratoFacade = new ContratoFacade();
		String sql = "Select c From Contrato c WHERE c.tipooperacao.descricao not like '%Portabilidade%'"
				+ " and c.operacaoinss=false ";
		if (!usuarioLogadoMB.getUsuario().isAcessogeral() 
				&& !usuarioLogadoMB.getUsuario().getTipocolaborador().getAcessocolaborador().isAcessooperacional()) {
			sql = sql + " and c.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario();
		}
		listaContratoPesquisa = contratoFacade.lista(sql);
		if (listaContratoPesquisa == null) {
			listaContratoPesquisa = new ArrayList<Contrato>();
		}
		nAguardandoAssinatura = 0;
		nDigitados = 0;
		nPendenciaDocumentacao = 0;
		nAguardandoPagamento = 0;
		nPagoCliente = 0;
		nCancelado = 0;
		nPendenciaAverbacao = 0;
		nTodos = listaContratoPesquisa.size();
		for (int i = 0; i < listaContratoPesquisa.size(); i++) {
			if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 1) {
				nDigitados = nDigitados + 1;
			}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 5) {
				nPendenciaDocumentacao = nPendenciaDocumentacao + 1;
			}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 28) {
				nAguardandoAssinatura = nAguardandoAssinatura + 1;
			}else if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 19) {
				nAguardandoPagamento = nAguardandoPagamento + 1;
			}else  if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 16){
				nPagoCliente = nPagoCliente + 1;
			}else  if (listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 2){
				nCancelados = nCancelados + 1;
			}else if(listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 36) {
				nPendenciaAverbacao = nPendenciaAverbacao + 1;
			}else if(listaContratoPesquisa.get(i).getSituacao().getIdsituacao() == 37) {
				nFormalizacaoPendencia = nFormalizacaoPendencia + 1;
			}
		}
	}
	
	
	public String trocatTitular(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		session.setAttribute("voltarTela", "consDemaisOperacoes");
		return "trocarTitular";
	}
	
	
	public String anexarArquivo(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		session.setAttribute("voltarTela", "consDemaisOperacoes");
		return "anexarArquivo";
	}
	
	
	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		listaBanco = bancoFacade.lista("Select b From Banco b Where b.nome !='Nenhum' ORDER BY b.nome");
		if (listaBanco == null) {
			listaBanco = new ArrayList<Banco>();
		}
	}
	
	public String imprimirFicha(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		session.setAttribute("voltar", "consDemaisOperacoes");
		return "fichaContrato";
	}
	
	public void detalheSituacao(Contrato contrato) {
		Mensagem.lancarMensagemInfo("Situação do Contrato:", contrato.getDetalhesituacao());
	}
	
	
	public void gerarComissao(Contrato contrato) {
		RegrasCoeficienteFacade regrasCoeficienteFacade = new RegrasCoeficienteFacade();
		Regrascoeficiente regrascoeficiente = regrasCoeficienteFacade.consultar(contrato.getIdregracoeficiente());
		Historicocomissao historicocomissao = new Historicocomissao();
		historicocomissao.setDatalancamento(new Date());
		historicocomissao.setContrato(contrato);
		historicocomissao.setUsuario(contrato.getUsuario());
		historicocomissao.setTipo("PENDENTE");
		int mes = Formatacao.getMesData(new Date()) + 1;
		int ano = Formatacao.getAnoData(new Date());
		historicocomissao.setAno(ano);
		historicocomissao.setMes(mes);
		if (contrato.getParcelaspagas() > 12 && contrato.getTipooperacao().getIdtipooperacao() == 1) {
			historicocomissao.setCmdbruta(contrato.getValorquitar() * (regrascoeficiente.getFlatrecebidaregra() / 100));
			historicocomissao.setCmsliq(contrato.getValorquitar() * (regrascoeficiente.getFlatrepassadavista() / 100));
			historicocomissao.setProdliq(contrato.getValorquitar());

		} else if (contrato.getTipooperacao().getIdtipooperacao() != 1) {
			historicocomissao
					.setCmdbruta(contrato.getValoroperacao() * (regrascoeficiente.getFlatrecebidaregra() / 100));
			historicocomissao
					.setCmsliq(contrato.getValoroperacao() * (regrascoeficiente.getFlatrepassadavista() / 100));
			historicocomissao.setProdliq(contrato.getValoroperacao());
		} else {
			historicocomissao.setCmdbruta(0.0f);
			historicocomissao.setCmsliq(0.0f);
			historicocomissao.setProdliq(0.0f);
		}
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		historicoComissaoFacade.salvar(historicocomissao);
		Mensagem.lancarMensagemInfo("Lançamento com sucesso", "");
	}
	
	
	public String historicoContrato(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		return "linhaTempoContrato";
	}

}
