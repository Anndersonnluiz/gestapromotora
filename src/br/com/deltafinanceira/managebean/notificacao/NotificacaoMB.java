package br.com.deltafinanceira.managebean.notificacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.dao.NotificacaoDao;
import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.facade.NotificacaoFacade;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Notificacao;
import br.com.deltafinanceira.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class NotificacaoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private List<Notificacao> listaNotificacao;
	
	
	
	@PostConstruct
	public void init() {
		listarNotificacao();
	}
	
	
	
	
	
	
	
	public List<Notificacao> getListaNotificacao() {
		return listaNotificacao;
	}







	public void setListaNotificacao(List<Notificacao> listaNotificacao) {
		this.listaNotificacao = listaNotificacao;
	}







	public void listarNotificacao() {
		NotificacaoDao notificacaoDao = new NotificacaoDao();
		listaNotificacao = notificacaoDao.lista("Select n From Notificacao n WHERE n.visto=false AND n.usuario.idusuario=" + 
					 usuarioLogadoMB.getUsuario().getIdusuario() + " ORDER BY n.datalancamento DESC");
		if (listaNotificacao == null) {
			listaNotificacao = new ArrayList<Notificacao>();
		}
	}
	
	
	
	public void visto(Notificacao notificacao) {
		NotificacaoDao notificacaoDao = new NotificacaoDao();
		notificacao.setVisto(true);
		notificacaoDao.salvar(notificacao);
		listaNotificacao.remove(notificacao);
		usuarioLogadoMB.listarNotificacao();
	}
	
	
	public String visualizarContrato(Notificacao notificacao) {
		if (notificacao.getIdcontrato() > 0) {
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			ContratoFacade contratoFacade = new ContratoFacade();
			Contrato contrato = contratoFacade.consultar(notificacao.getIdcontrato());
			session.setAttribute("contrato", contrato);
			session.setAttribute("orgaobanco", contrato.getOrgaoBanco());
			return "visualizarContrato";
		}
		return "";
	}
	
	
	
	public void vistoTodos() {
		NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
		if (listaNotificacao == null) {
			listaNotificacao = new ArrayList<Notificacao>();
		}
		for (int i = 0; i < listaNotificacao.size(); i++) {
			listaNotificacao.get(i).setVisto(true);
			notificacaoFacade.salvar(listaNotificacao.get(i));
		}
		listaNotificacao = new ArrayList<Notificacao>();
		usuarioLogadoMB.listarNotificacao();
	}
	
	

}
