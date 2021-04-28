package br.com.deltafinanceira.managebean.campanhas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.CampanhasFacade;
import br.com.deltafinanceira.facade.CampanhasUsuarioFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Campanhas;
import br.com.deltafinanceira.model.Campanhasusuario;
import br.com.deltafinanceira.model.Usuario;
import br.com.deltafinanceira.util.Mensagem;

@Named
@ViewScoped
public class CadCampanhasMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Campanhas campanhas;
	private Usuario usuario;
	private List<Usuario> listaUsuario;
	private List<Campanhasusuario> listaCampanhasUsuario;
	private Campanhasusuario campanhasusuario;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		campanhas = (Campanhas) session.getAttribute("campanhas");
		session.removeAttribute("campanhas");
		if (campanhas == null) {
			campanhas = new Campanhas();
			listaCampanhasUsuario = new ArrayList<Campanhasusuario>();
		}else {
			buscarCorretores();
		}
		campanhasusuario = new Campanhasusuario();
		gerarListaUsuario();
	}

	public Campanhas getCampanhas() {
		return campanhas;
	}

	public void setCampanhas(Campanhas campanhas) {
		this.campanhas = campanhas;
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

	public List<Campanhasusuario> getListaCampanhasUsuario() {
		return listaCampanhasUsuario;
	}

	public void setListaCampanhasUsuario(List<Campanhasusuario> listaCampanhasUsuario) {
		this.listaCampanhasUsuario = listaCampanhasUsuario;
	}

	public Campanhasusuario getCampanhasusuario() {
		return campanhasusuario;
	}

	public void setCampanhasusuario(Campanhasusuario campanhasusuario) {
		this.campanhasusuario = campanhasusuario;
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

	public void excluirUsuario(String slinha) {
		int linha = Integer.parseInt(slinha);
		Campanhasusuario campanhasusuario = listaCampanhasUsuario.get(linha);
		if (campanhasusuario.getIdcampanhasusuario() != null) {
			CampanhasUsuarioFacade campanhasUsuarioFacade = new CampanhasUsuarioFacade();
			campanhasUsuarioFacade.excluir(campanhasusuario.getIdcampanhasusuario());
		}
		listaCampanhasUsuario.remove(linha);
		Mensagem.lancarMensagemInfo("Excluido com sucesso!!", "");
	}

	public String salvar() {
		CampanhasFacade campanhasFacade = new CampanhasFacade();
		campanhas = campanhasFacade.salvar(campanhas);
		salvarCorretores();
		return "consCampanhas";
	}
	
	
	public String cancelar() {
		return "consCampanhas";
	}

	public void adicionarUsuario() {
		if (usuario != null && usuario.getIdusuario() != null) {
			campanhasusuario.setUsuario(usuario);
			listaCampanhasUsuario.add(campanhasusuario);
			campanhasusuario = new Campanhasusuario();
		} else {
			Mensagem.lancarMensagemInfo("Selecione um Corretor(a)", "");
		}
	}

	
	public void salvarCorretores() {
		CampanhasUsuarioFacade campanhasUsuarioFacade = new CampanhasUsuarioFacade();
		for (int i = 0; i < listaCampanhasUsuario.size(); i++) {
			listaCampanhasUsuario.get(i).setCampanhas(campanhas);
			campanhasUsuarioFacade.salvar(listaCampanhasUsuario.get(i));
		}
	}
	
	
	public void buscarCorretores() {
		CampanhasUsuarioFacade campanhasUsuarioFacade = new CampanhasUsuarioFacade();
		listaCampanhasUsuario = campanhasUsuarioFacade
				.lista("Select c From Campanhasusuario c Where c.campanhas.idcampanhas=" + campanhas.getIdcampanhas());
		if (listaCampanhasUsuario == null) {
			listaCampanhasUsuario = new ArrayList<Campanhasusuario>();
		}
	}
}
