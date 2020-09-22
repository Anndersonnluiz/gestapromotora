package br.com.gestapromotora.managebean.despesa;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.dao.TipoDespesaDao;
import br.com.gestapromotora.model.Tipodespesa;

@Named
@ViewScoped
public class CadTipoDespesaMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tipodespesa tipodespesa;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		tipodespesa = (Tipodespesa) session.getAttribute("tipodespesa");
		session.removeAttribute("tipodespesa");
		if (tipodespesa == null) {
			tipodespesa = new Tipodespesa();
		}
	}


	public Tipodespesa getTipodespesa() {
		return tipodespesa;
	}


	public void setTipodespesa(Tipodespesa tipodespesa) {
		this.tipodespesa = tipodespesa;
	}
	
	
	
	public String cancelar() {
		return "consDespesa";
	}
	
	
	public String salvar() {
		TipoDespesaDao tipoDespesaDao = new TipoDespesaDao();
		tipoDespesaDao.salvar(tipodespesa);
		return "consDespesa";
	}
	
	
	
	
	

}
