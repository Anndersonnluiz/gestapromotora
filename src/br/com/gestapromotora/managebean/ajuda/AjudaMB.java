package br.com.gestapromotora.managebean.ajuda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.gestapromotora.facade.AjudaFacade;
import br.com.gestapromotora.model.Ajuda;

@Named
@ViewScoped
public class AjudaMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Ajuda> listaAjuda;
	
	
	@PostConstruct
	public void init() {
		gerarListaAjuda();
	}


	public List<Ajuda> getListaAjuda() {
		return listaAjuda;
	}


	public void setListaAjuda(List<Ajuda> listaAjuda) {
		this.listaAjuda = listaAjuda;
	}
	
	
	
	public void gerarListaAjuda() {
		AjudaFacade ajudaFacade = new AjudaFacade();
		listaAjuda = ajudaFacade.lista("Select a From Ajuda a");
		if (listaAjuda == null) {
			listaAjuda = new ArrayList<Ajuda>();
		}
	}

}
