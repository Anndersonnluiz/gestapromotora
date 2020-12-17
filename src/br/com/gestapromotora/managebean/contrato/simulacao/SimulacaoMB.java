package br.com.gestapromotora.managebean.contrato.simulacao;

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
import br.com.gestapromotora.facade.SimulacaoContratoFacade;
import br.com.gestapromotora.facade.TipoOperacaoFacade;
import br.com.gestapromotora.facade.UsuarioFacade;
import br.com.gestapromotora.model.Banco;
import br.com.gestapromotora.model.Simulacaocontrato;
import br.com.gestapromotora.model.Tipooperacao;
import br.com.gestapromotora.model.Usuario;
import br.com.gestapromotora.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class SimulacaoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private List<Simulacaocontrato> listaSimulacao;
	private boolean unicoUsuario;
	private Usuario usuario;
	private List<Usuario> listaUsuario;
	private String nomeCliente;
	private String cpf;
	private Tipooperacao tipooperacao;
	private List<Tipooperacao> listaTipoOperacao;
	private Banco banco;
	private List<Banco> listaBanco;
	
	
	
	@PostConstruct
	public void init() {
		gerarListaUsuario();
		gerarListaTipoOperacao();
		gerarListaBanco();
		gerarListaSimulacao();
		if (!usuarioLogadoMB.getUsuario().isAcessogeral()
				&& !usuarioLogadoMB.getUsuario().isSupervisao()) {
			unicoUsuario = true;
			usuario = usuarioLogadoMB.getUsuario();
		}
	}



	public synchronized List<Simulacaocontrato> getListaSimulacao() {
		return listaSimulacao;
	}



	public synchronized void setListaSimulacao(List<Simulacaocontrato> listaSimulacao) {
		this.listaSimulacao = listaSimulacao;
	}
	
	
	
	
	public UsuarioLogadoMB getUsuarioLogadoMB() {
		return usuarioLogadoMB;
	}



	public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
		this.usuarioLogadoMB = usuarioLogadoMB;
	}



	public boolean isUnicoUsuario() {
		return unicoUsuario;
	}



	public void setUnicoUsuario(boolean unicoUsuario) {
		this.unicoUsuario = unicoUsuario;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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



	public Tipooperacao getTipooperacao() {
		return tipooperacao;
	}



	public void setTipooperacao(Tipooperacao tipooperacao) {
		this.tipooperacao = tipooperacao;
	}



	public List<Tipooperacao> getListaTipoOperacao() {
		return listaTipoOperacao;
	}



	public void setListaTipoOperacao(List<Tipooperacao> listaTipoOperacao) {
		this.listaTipoOperacao = listaTipoOperacao;
	}



	public Banco getBanco() {
		return banco;
	}



	public void setBanco(Banco banco) {
		this.banco = banco;
	}



	public List<Banco> getListaBanco() {
		return listaBanco;
	}



	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}



	public void gerarListaSimulacao() {
		SimulacaoContratoFacade simulacaoContratoFacade = new SimulacaoContratoFacade();
		String sql = "Select s From Simulacaocontrato s WHERE s.contrato.simulacao=true";
		if (!usuarioLogadoMB.getUsuario().isAcessogeral() 
				|| !usuarioLogadoMB.getUsuario().isResponsaveldepartamento()) {
			sql = sql + " and s.contrato.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario();
		}
		listaSimulacao = simulacaoContratoFacade.lista(sql);
	}
	
	
	
	public String novo() {
		return "cadSimulacaoContrato";
	}
	
	
	public String editar(Simulacaocontrato simulacaocontrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("simulacaocontrato", simulacaocontrato);
		return "cadSimulacaoContrato";
	}
	
	
	public String imprimirFicha(Simulacaocontrato simulacaocontrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("simulacaocontrato", simulacaocontrato);
		return "fichaSimulacao";
	}
	
	
	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		listaUsuario = usuarioFacade.listar("Select u From Usuario u order by u.nome");
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}
	
	
	public void pesquisar() {
		String sql = "Select s From Simulacaocontrato s WHERE " 
				+ " s.contrato.cliente.nome like '%"+ nomeCliente +"%' and s.contrato.cliente.cpf like '%"+ cpf +"%'";
		if (usuario != null && usuario.getIdusuario() != null  && !usuarioLogadoMB.getUsuario().getTipocolaborador().getDescricao()
				.equalsIgnoreCase("Operacional")) {
			sql = sql + " and s.contrato.usuario.idusuario=" + usuario.getIdusuario();
		}
		
		if (tipooperacao != null && tipooperacao.getIdtipooperacao() != null) {
			sql = sql + " and s.contrato.tipooperacao.idtipooperacao=" + tipooperacao.getIdtipooperacao();
		}
		
		if (banco != null && banco.getIdbanco() != null) {
			sql = sql + " and s.contrato.banco.idbanco=" + banco.getIdbanco();
		}

		sql = sql + " ORDER BY s.idsimulacaocontrato DESC";
		SimulacaoContratoFacade simulacaoContratoFacade = new SimulacaoContratoFacade();
		listaSimulacao = simulacaoContratoFacade.lista(sql);
		if (listaSimulacao == null) {
			listaSimulacao = new ArrayList<Simulacaocontrato>();
		}
	}
	
	public void limpar() {
		gerarListaSimulacao();
		usuario = null;
		nomeCliente = "";
		cpf = "";
		tipooperacao = null;
		banco = null;
	}
	
	
	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t");
		if (listaTipoOperacao == null) {
			listaTipoOperacao = new ArrayList<Tipooperacao>();
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
