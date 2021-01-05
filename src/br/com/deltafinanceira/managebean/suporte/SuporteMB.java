package br.com.deltafinanceira.managebean.suporte;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.deltafinanceira.facade.SuporteFacade;
import br.com.deltafinanceira.model.Suporte;

@Named
@ViewScoped
public class SuporteMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Suporte> listaSuporte;
	
	
	@PostConstruct
	public void init() {
		gerarListaSuporte();
	}


	
	
	
	public synchronized List<Suporte> getListaSuporte() {
		return listaSuporte;
	}





	public synchronized void setListaSuporte(List<Suporte> listaSuporte) {
		this.listaSuporte = listaSuporte;
	}





	public void gerarListaSuporte() {
		SuporteFacade suporteFacade = new SuporteFacade();
		listaSuporte = suporteFacade.lista("Select s From Suporte s");
		if (listaSuporte == null) {
			listaSuporte = new ArrayList<Suporte>();
		}
	}
	
	
	public String novo() {
		return "cadSuporte";
	}
	
	
	
	
	

}
