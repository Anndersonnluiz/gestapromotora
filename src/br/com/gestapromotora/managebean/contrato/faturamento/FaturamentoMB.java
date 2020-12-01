package br.com.gestapromotora.managebean.contrato.faturamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.ContratoFacade;
import br.com.gestapromotora.facade.UsuarioFacade;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.Usuario;
import br.com.gestapromotora.util.Mensagem;
import br.com.gestapromotora.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class FaturamentoMB implements Serializable{

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
	
	
	@PostConstruct
	public void init() {
		gerarListaContrato();
		gerarListaUsuario();
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


	public void gerarListaContrato() {
		ContratoFacade contratoFacade = new ContratoFacade();
		String sql = "Select c From Contrato c";
		if (!usuarioLogadoMB.getUsuario().isAcessogeral() && !usuarioLogadoMB.getUsuario().getTipocolaborador().getDescricao()
				.equalsIgnoreCase("Operacional")) {
			sql = sql + " WHERE c.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario();
		}
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
		String sql = "Select c From Contrato c WHERE c.cliente.nome like '%"+ nomeCliente +"%' and c.cliente.cpf like '%"+ cpf +"%'";
		if (usuario != null && usuario.getIdusuario() != null  && !usuarioLogadoMB.getUsuario().getTipocolaborador().getDescricao()
				.equalsIgnoreCase("Operacional")) {
			sql = sql + " and c.usuario.idusuario=" + usuario.getIdusuario();
		}
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
	}
	
	
	public String anexarArquivo(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		return "anexarArquivo";
	}

}
