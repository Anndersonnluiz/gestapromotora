package br.com.gestapromotora.managebean.cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.BancoFacade;
import br.com.gestapromotora.facade.HistoricoComissaoFacade;
import br.com.gestapromotora.facade.SituacaoFacade;
import br.com.gestapromotora.facade.TipoOperacaoFacade;
import br.com.gestapromotora.facade.UsuarioFacade;
import br.com.gestapromotora.model.Banco;
import br.com.gestapromotora.model.Cliente;
import br.com.gestapromotora.model.Historicocomissao;
import br.com.gestapromotora.model.Situacao;
import br.com.gestapromotora.model.Tipooperacao;
import br.com.gestapromotora.model.Usuario;

@Named
@ViewScoped
public class HistoricoContratoMB implements Serializable{

	/**
	 * 
	 */
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
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		cliente = (Cliente) session.getAttribute("cliente");
		session.removeAttribute("cliente");
		gerarListaContrato();
		gerarListaUsuario();
		gerarListaSituacao();
		gerarListaTipoOperacao();
		gerarListaBanco();
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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




	public List<Historicocomissao> getListaContrato() {
		return listaContrato;
	}


	public void setListaContrato(List<Historicocomissao> listaContrato) {
		this.listaContrato = listaContrato;
	}


	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}


	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	
	
	
	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public void gerarListaContrato() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		String sql = "Select c From Historicocomissao c WHERE c.contrato.cliente.idcliente=" + cliente.getIdcliente();
		sql = sql + " ORDER BY c.contrato.idcontrato DESC";
		listaContrato = historicoComissaoFacade.lista(sql);
		if (listaContrato == null) {
			listaContrato = new ArrayList<Historicocomissao>();
		}
	}
	
	
	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		listaUsuario = usuarioFacade.listar("Select u From Usuario u order by u.nome");
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}
	
	
	
	public void pesquisar() {
		String sql = "Select c From Historicocomissao c WHERE " 
				+ " c.contrato.cliente.idcliente=" + cliente.getIdcliente();
		
		
		if (tipooiperacao != null && tipooiperacao.getIdtipooperacao() != null) {
			sql = sql + " and c.contrato.tipooperacao.idtipooperacao=" + tipooiperacao.getIdtipooperacao();
		}
		
		if (banco != null && banco.getIdbanco() != null) {
			sql = sql + " and c.contrato.banco.idbanco=" + banco.getIdbanco();
		}
		
		if (situacao != null && situacao.getIdsituacao() != null) {
			sql = sql + " and c.contrato.situacao.idsituacao=" + situacao.getIdsituacao();
		}
		
		if (usuario != null && usuario.getIdusuario() != null) {
			sql = sql + " and c.contrato.usuario.idusuario=" + usuario.getIdusuario();
		}

		sql = sql + " ORDER BY c.idcontrato DESC";
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		listaContrato = historicoComissaoFacade.lista(sql);
		if (listaContrato == null) {
			listaContrato = new ArrayList<Historicocomissao>();
		}
	}
	
	public void limpar() {
		gerarListaContrato();
		usuario = null;
		tipooiperacao = null;
		banco = null;
		situacao = null;
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
	
	
	public String voltar() {
		return "consCliente";
	}
	
	
	

}
