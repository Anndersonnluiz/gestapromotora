package br.com.gestapromotora.managebean.financeiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.dao.TipoDespesaDao;
import br.com.gestapromotora.facade.ContasPagarFacade;
import br.com.gestapromotora.model.Contaspagar;
import br.com.gestapromotora.model.Tipodespesa;

@Named
@ViewScoped
public class CadContasPagarMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tipodespesa tipodespesa;
	private List<Tipodespesa> listaTipoDespesa;
	private Contaspagar contaspagar;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		contaspagar = (Contaspagar) session.getAttribute("contaspagar");
		session.removeAttribute("contaspagar");
		if (contaspagar == null) {
			contaspagar = new Contaspagar();
		}else {
			tipodespesa = contaspagar.getTipodespesa();
		}
		gerarListaDespesa();
	}


	public Tipodespesa getTipodespesa() {
		return tipodespesa;
	}


	public void setTipodespesa(Tipodespesa tipodespesa) {
		this.tipodespesa = tipodespesa;
	}


	public List<Tipodespesa> getListaTipoDespesa() {
		return listaTipoDespesa;
	}


	public void setListaTipoDespesa(List<Tipodespesa> listaTipoDespesa) {
		this.listaTipoDespesa = listaTipoDespesa;
	}


	public Contaspagar getContaspagar() {
		return contaspagar;
	}


	public void setContaspagar(Contaspagar contaspagar) {
		this.contaspagar = contaspagar;
	}
	
	
	public String cancelar() {
		return "consContasPagar"; 
	}
	
	
	public String salvar() {
		contaspagar.setTipodespesa(tipodespesa);
		ContasPagarFacade contasPagarFacade = new ContasPagarFacade();
		contasPagarFacade.salvar(contaspagar);
		return "consContasPagar";
	}
	
	
	public void gerarListaDespesa() {
		TipoDespesaDao tipoDespesaDao = new TipoDespesaDao();
		listaTipoDespesa = tipoDespesaDao.lista("Select t From Tipodespesa t");
		if (listaTipoDespesa == null) {
			listaTipoDespesa = new ArrayList<Tipodespesa>();
		}
	}
	
	
	
	

}
