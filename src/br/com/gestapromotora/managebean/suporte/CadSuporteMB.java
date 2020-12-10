package br.com.gestapromotora.managebean.suporte;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


import br.com.gestapromotora.facade.SuporteFacade;
import br.com.gestapromotora.model.Suporte;
import br.com.gestapromotora.util.Formatacao;
import br.com.gestapromotora.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class CadSuporteMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private Suporte suporte;
	
	
	
	@PostConstruct
	public void init() {
		suporte = new Suporte();
		suporte.setDatacadastro(Formatacao.ConvercaoDataPadrao(new Date()));
		suporte.setHora(Formatacao.foramtarHoraString());
		suporte.setUsuario(usuarioLogadoMB.getUsuario());
	}



	public synchronized Suporte getSuporte() {
		return suporte;
	}



	public synchronized void setSuporte(Suporte suporte) {
		this.suporte = suporte;
	}
	
	
	public String cancelar() {
		return "consSuporte";
	}
	
	
	public String salvar() {
		SuporteFacade suporteFacade = new SuporteFacade();
		suporte = suporteFacade.salvar(suporte);
		return "consSuporte";
	}
	
	

}
