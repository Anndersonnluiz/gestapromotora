package br.com.gestapromotora.managebean.despesa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.dao.TipoDespesaDao;
import br.com.gestapromotora.model.Tipodespesa;

@Named
@ViewScoped
public class DespesaMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Tipodespesa> listaTipoDespesa;
	
	
	
	@PostConstruct
	public void init() {
		gerarListaDespesa();
	}



	public List<Tipodespesa> getListaTipoDespesa() {
		return listaTipoDespesa;
	}



	public void setListaTipoDespesa(List<Tipodespesa> listaTipoDespesa) {
		this.listaTipoDespesa = listaTipoDespesa;
	}
	
	
	
	
	public String novo() {
		return "cadDespesa";
	}
	
	
	public String editar(Tipodespesa tipodespesa) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("tipodespesa", tipodespesa);
		return "cadDespesa";
	}
	
	
	public void gerarListaDespesa() {
		TipoDespesaDao tipoDespesaDao = new TipoDespesaDao();
		listaTipoDespesa = tipoDespesaDao.lista("Select t From Tipodespesa t");
		if (listaTipoDespesa == null) {
			listaTipoDespesa = new ArrayList<Tipodespesa>();
		}
	}
	
	
	
	

}
