package br.com.gestapromotora.managebean.contrato;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.BancoFacade;
import br.com.gestapromotora.facade.ContratoFacade;
import br.com.gestapromotora.facade.SituacaoFacade;
import br.com.gestapromotora.facade.TipoOperacaoFacade;
import br.com.gestapromotora.facade.UsuarioFacade;
import br.com.gestapromotora.model.Banco;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.Situacao;
import br.com.gestapromotora.model.Tipooperacao;
import br.com.gestapromotora.model.Usuario;
import br.com.gestapromotora.util.Mensagem;
import br.com.gestapromotora.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class ContratoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private List<Contrato> listaContrato;
	private List<Usuario> listaUsuario;
	private String nomeCliente;
	private String cpf;
	private Usuario usuario;
	private List<Tipooperacao> listaTipoOperacao;
	private Tipooperacao tipooiperacao;
	private List<Banco> listaBanco;
	private Banco banco;
	private List<Situacao> listaSituacao;
	private Situacao situacao;
	
	
	@PostConstruct
	public void init() {
		gerarListaContrato();
		gerarListaUsuario();
		gerarListaTipoOperacao();
		gerarListaBanco();
		gerarListaSituacao();
		
	}


	public List<Contrato> getListaContrato() {
		return listaContrato;
	}


	public void setListaContrato(List<Contrato> listaContrato) {
		this.listaContrato = listaContrato;
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


	public UsuarioLogadoMB getUsuarioLogadoMB() {
		return usuarioLogadoMB;
	}


	public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
		this.usuarioLogadoMB = usuarioLogadoMB;
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


	public List<Situacao> getListaSituacao() {
		return listaSituacao;
	}


	public void setListaSituacao(List<Situacao> listaSituacao) {
		this.listaSituacao = listaSituacao;
	}


	public Situacao getSituacao() {
		return situacao;
	}


	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}


	public void gerarListaContrato() {
		ContratoFacade contratoFacade = new ContratoFacade();
		String sql = "Select c From Contrato c WHERE c.situacao.identificador<>6 ";
		if (!usuarioLogadoMB.getUsuario().isAcessogeral() && !usuarioLogadoMB.getUsuario().getTipocolaborador().getDescricao()
				.equalsIgnoreCase("Operacional")) {
			sql = sql + " AND c.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario();
		}
		sql = sql + " ORDER BY c.idcontrato DESC";
		listaContrato = contratoFacade.lista(sql);
		if (listaContrato == null) {
			listaContrato = new ArrayList<Contrato>();
		}
	}
	
	
	public String editar(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		session.setAttribute("orgaobanco", contrato.getValorescoeficiente().getCoeficiente().getOrgaoBanco());
		return "cadContrato";
	}
	
	
	public void bloquearEdicao(Contrato contrato) {
		if (contrato.isBloqueio()) {
			contrato.setBloqueio(false);
			contrato.setDescricaobloqueio("unlock");
			Mensagem.lancarMensagemInfo("Desbloqueio de contrato feito com sucesso", "");
		}else {
			contrato.setBloqueio(true);
			contrato.setDescricaobloqueio("lock");
			Mensagem.lancarMensagemInfo("Bloqueio de contrato feito com sucesso", "");
		}
		ContratoFacade contratoFacade = new ContratoFacade();
		contrato = contratoFacade.salvar(contrato);
	}
	
	
	public void digitarEdicao(Contrato contrato) {
		if (contrato.isDigitado()) {
			contrato.setDigitado(false);
			contrato.setDescricaodigitado("file");
			Mensagem.lancarMensagemInfo("Desbloqueio de contrato desfeita com sucesso", "");
		}else {
			contrato.setDigitado(true);
			contrato.setDescricaodigitado("file-text");
			Mensagem.lancarMensagemInfo("Digitação de contrato feito com sucesso", "");
		}
		ContratoFacade contratoFacade = new ContratoFacade();
		contrato = contratoFacade.salvar(contrato);
	}
	
	
	public String malote(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		return "cadMaloteContrato";
	}
	
	
	public String historicopendencia(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		return "cadHistoricoPendencia";
	}
	
	
	public void entregueFisico(Contrato contrato) {
		if (contrato.isFisico()) {
			contrato.setFisico(false);
			contrato.setDescricaofisico("x-circle");
			Mensagem.lancarMensagemInfo("Contrato não entregue", "");
		}else {
			contrato.setFisico(true);
			contrato.setDescricaofisico("check");
			Mensagem.lancarMensagemInfo("Contrato Entregue", "");
		}
		ContratoFacade contratoFacade = new ContratoFacade();
		contrato = contratoFacade.salvar(contrato);
	}
	
	
	public String trocatTitular(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		return "trocarTitular";
	}
	
	
	public String financeiro(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		return "consFinanceiro";
	}
	
	
	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		listaUsuario = usuarioFacade.listar("Select u From Usuario u order by u.nome");
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}
	
	
	public void pesquisar() {
		String sql = "Select c From Contrato c WHERE " 
				+ " c.cliente.nome like '%"+ nomeCliente +"%' and c.cliente.cpf like '%"+ cpf +"%'";
		if (usuario != null && usuario.getIdusuario() != null  && !usuarioLogadoMB.getUsuario().getTipocolaborador().getDescricao()
				.equalsIgnoreCase("Operacional")) {
			sql = sql + " and c.usuario.idusuario=" + usuario.getIdusuario();
		}
		
		if (tipooiperacao != null && tipooiperacao.getIdtipooperacao() != null) {
			sql = sql + " and c.tipooperacao.idtipooperacao=" + tipooiperacao.getIdtipooperacao();
		}
		
		if (banco != null && banco.getIdbanco() != null) {
			sql = sql + " and c.banco.idbanco=" + banco.getIdbanco();
		}
		
		if (situacao != null && situacao.getIdsituacao() != null) {
			sql = sql + " and c.situacao.idsituacao=" + situacao.getIdsituacao();
		}

		sql = sql + " ORDER BY c.idcontrato DESC";
		ContratoFacade contratoFacade = new ContratoFacade();
		listaContrato = contratoFacade.lista(sql);
		if (listaContrato == null) {
			listaContrato = new ArrayList<Contrato>();
		}
	}
	
	public void limpar() {
		gerarListaContrato();
		usuario = null;
		nomeCliente = "";
		cpf = "";
		tipooiperacao = null;
		banco = null;
		situacao = null;
	}
	
	
	public String anexarArquivo(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		return "anexarArquivo";
	}
	
	

	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t");
		if (listaTipoOperacao == null) {
			listaTipoOperacao = new ArrayList<Tipooperacao>();
		}
	}
	
	
	public void gerarListaSituacao() {
		SituacaoFacade situacaoFacade = new SituacaoFacade();
		String sql = "Select s From Situacao s WHERE s.visualizar=true ";
		sql = sql + " ORDER BY s.descricao";
		listaSituacao = situacaoFacade.lista(sql);
		if (listaSituacao == null) {
			listaSituacao = new ArrayList<Situacao>();
		}
	}
	
	
	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		listaBanco = bancoFacade.lista("Select b From Banco b Where b.nome !='Nenhum' ORDER BY b.nome");
		if (listaBanco == null) {
			listaBanco = new ArrayList<Banco>();
		}
	}
	
	
	
	
	

}
