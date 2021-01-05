package br.com.deltafinanceira.managebean.cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.ClienteFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Cliente;
import br.com.deltafinanceira.model.Usuario;
import br.com.deltafinanceira.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class ClienteMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Cliente> listaCliente;
	private String nomeCliente;
	private String cpf;
	private Usuario usuario;
	private List<Usuario> listaUsuario;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private boolean verificarAcesso;
	
	
	
	@PostConstruct
	public void init() {
		if (!usuarioLogadoMB.getUsuario().isAcessogeral() &&
				!usuarioLogadoMB.getUsuario().isSupervisao()) {
			usuario = usuarioLogadoMB.getUsuario();
			verificarAcesso = false;
		}else {
			verificarAcesso = true;
		}
		gerarListaCliente();
		gerarListaUsuario();
	}



	public List<Cliente> getListaCliente() {
		return listaCliente;
	}



	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
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



	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}



	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}



	public boolean isVerificarAcesso() {
		return verificarAcesso;
	}



	public void setVerificarAcesso(boolean verificarAcesso) {
		this.verificarAcesso = verificarAcesso;
	}



	public void gerarListaCliente() {
		ClienteFacade clienteFacade = new ClienteFacade();
		String sql = "Select c From Cliente c";
		if (usuario != null && usuario.getIdusuario() != null) {
			sql = sql + " Where c.usuario.idusuario=" + usuario.getIdusuario();
		}
		sql = sql + " ORDER BY c.nome, c.usuario.nome";
		listaCliente = clienteFacade.lista(sql);
		if (listaCliente == null) {
			listaCliente = new ArrayList<Cliente>();
		}
	}
	
	
	public String novoCliente() {
		return "cadCliente";
	}
	
	
	public String editar(Cliente cliente) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("cliente", cliente);
		return "cadCliente";
	}
	
	
	
	public void pesquisar() {
		String sql = "Select c From Cliente c WHERE c.nome like '%"+ nomeCliente +"%' and c.cpf like '%"+ cpf +"%'";
		if (usuario != null && usuario.getIdusuario() != null) {
			sql = sql + " and c.usuario.idusuario=" + usuario.getIdusuario();
		}
		sql = sql + " ORDER BY c.nome";
		ClienteFacade clienteFacade = new ClienteFacade();
		listaCliente = clienteFacade.lista(sql);
		if (listaCliente == null) {
			listaCliente = new ArrayList<Cliente>();
		}
	}
	
	public void limpar() {
		gerarListaCliente();
		nomeCliente = "";
		cpf = "";
		usuario = null;
	}
	
	
	public String historicoContrato(Cliente cliente) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("cliente", cliente);
		return "historicoContrato";
	}
	
	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		String sql = "Select u From Usuario u WHERE u.ativo=true";
		sql = sql + " and u.departamento.iddepartamento=7 order by u.nome";
		listaUsuario = usuarioFacade.listar(sql);
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}
	
	
	
 
}
