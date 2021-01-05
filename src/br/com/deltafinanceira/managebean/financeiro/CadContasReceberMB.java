package br.com.deltafinanceira.managebean.financeiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.dao.TipoDespesaDao;
import br.com.deltafinanceira.facade.ContasReceberFacade;
import br.com.deltafinanceira.model.Contasreceber;
import br.com.deltafinanceira.model.Tipodespesa;

@Named
@ViewScoped
public class CadContasReceberMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tipodespesa tipodespesa;
	private List<Tipodespesa> listaTipoDespesa;
	private Contasreceber contasreceber;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		contasreceber = (Contasreceber) session.getAttribute("contasreceber");
		session.removeAttribute("contasreceber");
		if (contasreceber == null) {
			contasreceber = new Contasreceber();
		}else {
			tipodespesa = contasreceber.getTipodespesa();
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
	
	
	public Contasreceber getContasreceber() {
		return contasreceber;
	}


	public void setContasreceber(Contasreceber contasreceber) {
		this.contasreceber = contasreceber;
	}


	public String cancelar() {
		return "consContasReceber"; 
	}
	
	
	public String salvar() {
		contasreceber.setTipodespesa(tipodespesa);
		ContasReceberFacade contasReceberFacade = new ContasReceberFacade();
		contasReceberFacade.salvar(contasreceber);
		return "consContasReceber";
	}
	
	
	public void gerarListaDespesa() {
		TipoDespesaDao tipoDespesaDao = new TipoDespesaDao();
		listaTipoDespesa = tipoDespesaDao.lista("Select t From Tipodespesa t");
		if (listaTipoDespesa == null) {
			listaTipoDespesa = new ArrayList<Tipodespesa>();
		}
	}

}
