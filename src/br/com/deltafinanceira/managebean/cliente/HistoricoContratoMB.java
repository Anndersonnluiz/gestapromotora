package br.com.deltafinanceira.managebean.cliente;

import br.com.deltafinanceira.facade.BancoFacade;
import br.com.deltafinanceira.facade.HistoricoComissaoFacade;
import br.com.deltafinanceira.facade.SituacaoFacade;
import br.com.deltafinanceira.facade.TipoOperacaoFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Banco;
import br.com.deltafinanceira.model.Cliente;
import br.com.deltafinanceira.model.Historicocomissao;
import br.com.deltafinanceira.model.Lead;
import br.com.deltafinanceira.model.Situacao;
import br.com.deltafinanceira.model.Tipooperacao;
import br.com.deltafinanceira.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class HistoricoContratoMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	private List<Tipooperacao> listaTipoOperacao;

	private Tipooperacao tipooiperacao;

	private List<Banco> listaBanco;

	private Banco banco;

	private List<Situacao> listaSituacao;

	private Situacao situacao;

	private List<Historicocomissao> listaContrato;

	private List<Usuario> listaUsuario;

	private Cliente cliente;

	private Lead lead;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		this.cliente = (Cliente) session.getAttribute("cliente");
		this.lead = (Lead) session.getAttribute("lead");
		session.removeAttribute("lead");
		session.removeAttribute("cliente");
		if (lead != null && cliente == null) {
			cliente = lead.getCliente();
		}
		gerarListaContrato();
		gerarListaUsuario();
		gerarListaSituacao();
		gerarListaTipoOperacao();
		gerarListaBanco();
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public List<Banco> getListaBanco() {
		return this.listaBanco;
	}

	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}

	public Banco getBanco() {
		return this.banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public List<Situacao> getListaSituacao() {
		return this.listaSituacao;
	}

	public void setListaSituacao(List<Situacao> listaSituacao) {
		this.listaSituacao = listaSituacao;
	}

	public Situacao getSituacao() {
		return this.situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public List<Historicocomissao> getListaContrato() {
		return this.listaContrato;
	}

	public void setListaContrato(List<Historicocomissao> listaContrato) {
		this.listaContrato = listaContrato;
	}

	public List<Usuario> getListaUsuario() {
		return this.listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Lead getLead() {
		return lead;
	}

	public void setLead(Lead lead) {
		this.lead = lead;
	}

	public void gerarListaContrato() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		String sql = "Select c From Historicocomissao c WHERE c.contrato.cliente.idcliente="
				+ this.cliente.getIdcliente();
		sql = String.valueOf(sql) + " ORDER BY c.contrato.idcontrato DESC";
		this.listaContrato = historicoComissaoFacade.lista(sql);
		if (this.listaContrato == null)
			this.listaContrato = new ArrayList<>();
	}

	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		this.listaUsuario = usuarioFacade.listar("Select u From Usuario u order by u.nome");
		if (this.listaUsuario == null)
			this.listaUsuario = new ArrayList<>();
	}

	public void pesquisar() {
		String sql = "Select c From Historicocomissao c WHERE  c.contrato.cliente.idcliente="
				+ this.cliente.getIdcliente();
		if (this.tipooiperacao != null && this.tipooiperacao.getIdtipooperacao() != null)
			sql = String.valueOf(sql) + " and c.contrato.tipooperacao.idtipooperacao="
					+ this.tipooiperacao.getIdtipooperacao();
		if (this.banco != null && this.banco.getIdbanco() != null)
			sql = String.valueOf(sql) + " and c.contrato.banco.idbanco=" + this.banco.getIdbanco();
		if (this.situacao != null && this.situacao.getIdsituacao() != null)
			sql = String.valueOf(sql) + " and c.contrato.situacao.idsituacao=" + this.situacao.getIdsituacao();
		if (this.usuario != null && this.usuario.getIdusuario() != null)
			sql = String.valueOf(sql) + " and c.contrato.usuario.idusuario=" + this.usuario.getIdusuario();
		sql = String.valueOf(sql) + " ORDER BY c.idcontrato DESC";
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		this.listaContrato = historicoComissaoFacade.lista(sql);
		if (this.listaContrato == null)
			this.listaContrato = new ArrayList<>();
	}

	public void limpar() {
		gerarListaContrato();
		this.usuario = null;
		this.tipooiperacao = null;
		this.banco = null;
		this.situacao = null;
	}

	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		this.listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t");
		if (this.listaTipoOperacao == null)
			this.listaTipoOperacao = new ArrayList<>();
	}

	public void gerarListaSituacao() {
		SituacaoFacade situacaoFacade = new SituacaoFacade();
		String sql = "Select s From Situacao s WHERE s.visualizar=true ";
		sql = String.valueOf(sql) + " ORDER BY s.descricao";
		this.listaSituacao = situacaoFacade.lista(sql);
		if (this.listaSituacao == null)
			this.listaSituacao = new ArrayList<>();
	}

	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		this.listaBanco = bancoFacade.lista("Select b From Banco b Where b.visualizar=true ORDER BY b.nome");
		if (this.listaBanco == null)
			this.listaBanco = new ArrayList<>();
	}

	public String voltar() {
		if (lead != null) {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			session.setAttribute("lead", lead);
			return "historicoLead";
		}
		return "consCliente";
	}
}
