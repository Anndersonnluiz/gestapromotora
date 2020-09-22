package br.com.gestapromotora.managebean.financeiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.HistoricoComissaoFacade;
import br.com.gestapromotora.facade.UsuarioFacade;
import br.com.gestapromotora.model.Historicocomissao;
import br.com.gestapromotora.model.Usuario;
import br.com.gestapromotora.util.Formatacao;

@Named
@ViewScoped
public class HistoricoComissaoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Historicocomissao> listaComissao;
	private String nome;
	private int cdinterno;
	private String dataLancamento;
	private List<Usuario> listaUsuario;
	private Usuario usuario;
	
	
	@PostConstruct
	public void init() {
		gerarListaInicial();
		gerarListaUsuario();
	}


	public List<Historicocomissao> getListaComissao() {
		return listaComissao;
	}


	public void setListaComissao(List<Historicocomissao> listaComissao) {
		this.listaComissao = listaComissao;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getCdinterno() {
		return cdinterno;
	}


	public void setCdinterno(int cdinterno) {
		this.cdinterno = cdinterno;
	}
	
	
	
	public String getDataLancamento() {
		return dataLancamento;
	}


	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
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


	public void gerarListaInicial() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		listaComissao = historicoComissaoFacade.lista("Select h From Historicocomissao h");
		if (listaComissao == null) {
			listaComissao = new ArrayList<Historicocomissao>();
		}
	}
	
	
	public void pesquisar() {
		String sql = "Select h From Historicocomissao h Where h.usuario.nome like '%"+ nome +"%' ";
		if (cdinterno > 0) {
			sql = sql + " and h.usuario.cdinterno=" + cdinterno;
		}
		
		if (dataLancamento != null && dataLancamento.length() > 0 && !dataLancamento.equalsIgnoreCase("Selecione")) {
			sql = sql + " and h.datalancamento='" + Formatacao.getAnoData(new Date()) + "-"+ dataLancamento +"-01' ";
		}
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		listaComissao = historicoComissaoFacade.lista(sql);
		if (listaComissao == null) {
			listaComissao = new ArrayList<Historicocomissao>();
		}
	}
	
	public void limpar() {
		nome = "";
		cdinterno = 0;
		dataLancamento = null;
		gerarListaInicial();
	}
	
	public String editar(Historicocomissao historicocomissao) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("historicocomissao", historicocomissao);
		return "editarComissao";
	}
	
	
	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		listaUsuario = usuarioFacade.listar("Select u From Usuario u WHERE u.ativo=true");
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
	}

}
