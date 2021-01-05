package br.com.deltafinanceira.managebean.cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.deltafinanceira.facade.ClienteFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Cliente;
import br.com.deltafinanceira.model.Regrascoeficiente;
import br.com.deltafinanceira.model.Usuario;
import br.com.deltafinanceira.util.Mensagem;

@Named
@ViewScoped
public class TrocaClienteMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Usuario> listaUsuario;
	private Usuario usuario;
	private Cliente cliente;
	private String senha;
	private Regrascoeficiente regracoeficiente;
	private Usuario usuarioAtual;
	private boolean selecionarTodos;
	private List<Cliente> listaCliente;

	@PostConstruct
	public void init() {
		gerarListaUsuario();
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

	public Regrascoeficiente getRegracoeficiente() {
		return regracoeficiente;
	}

	public void setRegracoeficiente(Regrascoeficiente regracoeficiente) {
		this.regracoeficiente = regracoeficiente;
	}

	public Usuario getUsuarioAtual() {
		return usuarioAtual;
	}

	public void setUsuarioAtual(Usuario usuarioAtual) {
		this.usuarioAtual = usuarioAtual;
	}

	public boolean isSelecionarTodos() {
		return selecionarTodos;
	}

	public void setSelecionarTodos(boolean selecionarTodos) {
		this.selecionarTodos = selecionarTodos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		listaUsuario = usuarioFacade.listar("Select u From Usuario u order by u.nome");
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}

	public String salvar() {
		ClienteFacade clienteFacade = new ClienteFacade();
		if (usuario != null) {
			for (int i = 0; i < listaCliente.size(); i++) {
				if (listaCliente.get(i).isSelecionado()) {
					listaCliente.get(i).setUsuario(usuario);
					clienteFacade.salvar(listaCliente.get(i));
				}
			}
			buscarCliente();
		} else {
			Mensagem.lancarMensagemInfo("Informe o novo titular dos contratos", "");
			return "";
		}
		return "";
	}

	public String cancelar() {
		return "dashboard";
	}

	
	
	public void buscarCliente() {
		if (usuarioAtual != null) {
			ClienteFacade clienteFacade = new ClienteFacade();
			listaCliente = clienteFacade.lista("SELECT c From Cliente c WHERE"
					+ " c.usuario.idusuario=" + usuarioAtual.getIdusuario());
			if (listaCliente == null) {
				listaCliente = new ArrayList<Cliente>();
			}
		}
	}

	public void selecionarTodosCliente() {
		if (selecionarTodos) {
			selecionarTodos = false;
		} else {
			selecionarTodos = true;
		}
		for (int i = 0; i < listaCliente.size(); i++) {
			listaCliente.get(i).setSelecionado(selecionarTodos);
		}
	}

}
