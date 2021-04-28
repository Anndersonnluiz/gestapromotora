package br.com.deltafinanceira.managebean.campanhas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.CampanhasFacade;
import br.com.deltafinanceira.model.Campanhas;
import br.com.deltafinanceira.util.Formatacao;

@Named
@ViewScoped
public class CampanhasMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Campanhas> listaCampanhas;
	
	
	
	@PostConstruct
	public void init() {
		gerarListaCampanhas();
	}



	public List<Campanhas> getListaCampanhas() {
		return listaCampanhas;
	}



	public void setListaCampanhas(List<Campanhas> listaCampanhas) {
		this.listaCampanhas = listaCampanhas;
	}
	
	
	
	
	public void gerarListaCampanhas() {
		CampanhasFacade campanhasFacade = new CampanhasFacade();
		String dataHoje = Formatacao.ConvercaoDataNfe(new Date());
		listaCampanhas = campanhasFacade.lista("Select c From Campanhas c Where c.datafinal>='" + dataHoje
				+ "'");
		if (listaCampanhas == null) {
			listaCampanhas = new ArrayList<Campanhas>();
		}
	}
	
	
	public String novo() {
		return "cadCampanhas";
	}
	
	
	public String editar(Campanhas campanhas) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("campanhas", campanhas);
		return "cadCampanhas";
	}
	
	
	
	

}
