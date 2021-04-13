package br.com.deltafinanceira.managebean.formalizacao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.dao.NotificacaoDao;
import br.com.deltafinanceira.dao.UsuarioDao;
import br.com.deltafinanceira.facade.FormalizacaoFacade;
import br.com.deltafinanceira.facade.TipoOperacaoFacade;
import br.com.deltafinanceira.model.Formalizacao;
import br.com.deltafinanceira.model.Notificacao;
import br.com.deltafinanceira.model.Tipooperacao;
import br.com.deltafinanceira.model.Usuario;
import br.com.deltafinanceira.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class CadFormalizacaoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	
	private Formalizacao formalizacao;
	
	private boolean novo;

	private List<Tipooperacao> listaTipoOperacao;

	private Tipooperacao tipooiperacao;
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		formalizacao = (Formalizacao) session.getAttribute("formalizacao");
		session.removeAttribute("formalizacao");
		novo = false;
		if (formalizacao == null) {
			formalizacao = new Formalizacao();
			formalizacao.setAtivo(true);
			formalizacao.setEmitidocontrato(false);
			formalizacao.setUsuario(usuarioLogadoMB.getUsuario());
			novo = true;
		}
		gerarListaTipoOperacao();
	}



	public Formalizacao getFormalizacao() {
		return formalizacao;
	}



	public void setFormalizacao(Formalizacao formalizacao) {
		this.formalizacao = formalizacao;
	}
	
	
	
	
	public UsuarioLogadoMB getUsuarioLogadoMB() {
		return usuarioLogadoMB;
	}



	public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
		this.usuarioLogadoMB = usuarioLogadoMB;
	}



	public boolean isNovo() {
		return novo;
	}



	public void setNovo(boolean novo) {
		this.novo = novo;
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



	public String salvar() {
		FormalizacaoFacade formalizacaoFacade = new FormalizacaoFacade();
		formalizacao = formalizacaoFacade.salvar(formalizacao);
		if (novo) {
			gerarNotificacao();
		}
		return "consFormalizacao";
	}
	
	
	
	public String cancelar() {
		return "consFormalizacao";
	}
	
	
	public void gerarNotificacao() {
		UsuarioDao usuarioDao = new UsuarioDao();
		NotificacaoDao notificacaoDao = new NotificacaoDao();
		try {
			List<Usuario> listaUsuario = usuarioDao.listar(
					"Select u From Usuario u WHERE (u.formalizacao=true or u.acessogeral=true)");
			for (int i = 0; i < listaUsuario.size(); i++) {
				Notificacao notificacao = new Notificacao();
				notificacao.setDatalancamento(new Date());
				notificacao.setVisto(false);
				notificacao.setUsuario(listaUsuario.get(i));
				notificacao.setIdcontrato(0);
				notificacao.setTipooperacao(formalizacao.getTipooperacao().getDescricao());
				notificacao.setTitulo("Nova Formalização Emitida");
				notificacao.setDescricao("Formalização: " + formalizacao.getTipooperacao().getDescricao()
						+ " emitido pelo corretor(a) " + this.formalizacao.getUsuario().getNome());
				notificacaoDao.salvar(notificacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void gerarListaTipoOperacao() {
		TipoOperacaoFacade tipoOperacaoFacade = new TipoOperacaoFacade();
		this.listaTipoOperacao = tipoOperacaoFacade.lista("Select t From Tipooperacao t");
		if (this.listaTipoOperacao == null)
			this.listaTipoOperacao = new ArrayList<>();
	}
	
	
	
	
	

}
