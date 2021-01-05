package br.com.deltafinanceira.managebean.financeiro;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.dao.MetaFaturamentoMensalDao;
import br.com.deltafinanceira.model.Metafaturamentomensal;

@Named
@ViewScoped
public class CadMetaMensalMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Metafaturamentomensal metafaturamentomensal;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		metafaturamentomensal = (Metafaturamentomensal) session.getAttribute("metafaturamentomensal");
		session.removeAttribute("metafaturamentomensal");
		if (metafaturamentomensal == null) {
			metafaturamentomensal = new Metafaturamentomensal();
		}
	}


	public Metafaturamentomensal getMetafaturamentomensal() {
		return metafaturamentomensal;
	}


	public void setMetafaturamentomensal(Metafaturamentomensal metafaturamentomensal) {
		this.metafaturamentomensal = metafaturamentomensal;
	}
	
	
	
	
	public String cancelar() {
		return "consMetaMensal";
	}
	
	
	
	public String salvar() {
		if (metafaturamentomensal.getIdmetafaturamentomensal() == null) {
			metafaturamentomensal.setPercentualmeta(0.0f);
			metafaturamentomensal.setCormeta("#f3291f");
			metafaturamentomensal.setNvenda(0);
		}
		MetaFaturamentoMensalDao metaFaturamentoMensalDao = new MetaFaturamentoMensalDao();
		metaFaturamentoMensalDao.salvar(metafaturamentomensal);
		return "consMetaMensal";
	}
	
	
	
	
	
	
	

}
