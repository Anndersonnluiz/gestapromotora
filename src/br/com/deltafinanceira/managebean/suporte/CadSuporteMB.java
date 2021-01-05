package br.com.deltafinanceira.managebean.suporte;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.deltafinanceira.facade.SuporteFacade;
import br.com.deltafinanceira.model.Suporte;
import br.com.deltafinanceira.util.Formatacao;
import br.com.deltafinanceira.util.UsuarioLogadoMB;

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
