package br.com.deltafinanceira.managebean.avisos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.deltafinanceira.facade.AvisosUsuarioFacade;
import br.com.deltafinanceira.model.Avisosusuario;
import br.com.deltafinanceira.util.Formatacao;
import br.com.deltafinanceira.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class VisualizarAvisosMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private List<Avisosusuario> listaAvisos;
	
	
	
	@PostConstruct
	public void init() {
		listaAvisos();
	}
	
	
	
	
	
	
	public List<Avisosusuario> getListaAvisos() {
		return listaAvisos;
	}






	public void setListaAvisos(List<Avisosusuario> listaAvisos) {
		this.listaAvisos = listaAvisos;
	}






	public void listaAvisos() {
		String dataHoje = Formatacao.ConvercaoDataNfe(new Date());
		AvisosUsuarioFacade avisosUsuarioFacade = new AvisosUsuarioFacade();
		listaAvisos = avisosUsuarioFacade.lista("Select a From Avisosusuario a Where a.usuario.idusuario="
		+ usuarioLogadoMB.getUsuario().getIdusuario() + " and a.visto=false and a.avisos.datainicio<='" + 
				dataHoje + "' and a.avisos.datafinal>='" + dataHoje + "'");
		if (listaAvisos == null) {
			listaAvisos = new ArrayList<Avisosusuario>();
		}
	}
	
	public void visto(Avisosusuario avisosusuario) {
		AvisosUsuarioFacade avisosUsuarioFacade = new AvisosUsuarioFacade();
		avisosusuario.setVisto(true);
		avisosUsuarioFacade.salvar(avisosusuario);
		listaAvisos.remove(avisosusuario);
		usuarioLogadoMB.listarNotificacao();
	}
	
	public void vistoTodos() {
		AvisosUsuarioFacade avisosUsuarioFacade = new AvisosUsuarioFacade();
		if (listaAvisos == null) {
			listaAvisos = new ArrayList<Avisosusuario>();
		}
		for (int i = 0; i < listaAvisos.size(); i++) {
			listaAvisos.get(i).setVisto(true);
			avisosUsuarioFacade.salvar(listaAvisos.get(i));
		}
		listaAvisos = new ArrayList<Avisosusuario>();
		usuarioLogadoMB.listarNotificacao();
	}

}
