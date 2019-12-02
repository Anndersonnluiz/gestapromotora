package br.com.gestapromotora.managebean.usuario;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.UsuarioFacade;
import br.com.gestapromotora.model.Usuario;
import br.com.gestapromotora.util.Criptografia;

@Named
@ViewScoped
public class UsuarioMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Usuario> listaUsuario;
	private Usuario usuario;
	
	
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


	public void gerarListaUsuario(){
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		listaUsuario = usuarioFacade.listar("Select u From Usuario u");
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}
	
	
	public String novoUsuario() {
		return "cadUsuario";
	}
	
	
	public String editar(Usuario usuario) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("usuario", usuario);
		return "cadUsuario";
	}
	
	
	public void resetarSenhaUsuario(Usuario usuario) {
        String senhaResetada;
        try {
            senhaResetada = Criptografia.encript("senha");
            if (usuario != null) {
                usuario.setSenha(senhaResetada);
                UsuarioFacade usuarioFacade = new UsuarioFacade();
                usuarioFacade.salvar(usuario);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	
	
	
}
