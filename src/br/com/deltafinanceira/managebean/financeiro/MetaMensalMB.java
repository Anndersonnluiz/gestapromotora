package br.com.deltafinanceira.managebean.financeiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.dao.MetaFaturamentoMensalDao;
import br.com.deltafinanceira.model.Metafaturamentomensal;
import br.com.deltafinanceira.util.Formatacao;

@Named
@ViewScoped
public class MetaMensalMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Metafaturamentomensal> listaMetaMensal;
	
	
	
	@PostConstruct
	public void init() {
		gerarListaMeta();
	}



	public List<Metafaturamentomensal> getListaMetaMensal() {
		return listaMetaMensal;
	}



	public void setListaMetaMensal(List<Metafaturamentomensal> listaMetaMensal) {
		this.listaMetaMensal = listaMetaMensal;
	}
	
	
	
	public void gerarListaMeta() {
		MetaFaturamentoMensalDao metaFaturamentoMensalDao = new MetaFaturamentoMensalDao();
		listaMetaMensal = metaFaturamentoMensalDao.lista("Select m From Metafaturamentomensal m WHERE m.ano=" 
				+ Formatacao.getAnoData(new Date()));
		if (listaMetaMensal == null) {
			listaMetaMensal = new ArrayList<Metafaturamentomensal>();
		}
	}
	
	
	public String novo() {
		return "cadMetaMensal";
	}
	
	
	public String editar(Metafaturamentomensal metafaturamentomensal) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("metafaturamentomensal", metafaturamentomensal);
		return "cadMetaMensal";
	}
	
	
	
	

}
