package br.com.gestapromotora.managebean.contrato.linhatempo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.HistoricoUsuarioFacade;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.Historicousuario;

@Named
@ViewScoped
public class LinhaTempoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Historicousuario> listaHistorico;
	private Contrato contrato;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		contrato = (Contrato) session.getAttribute("contrato");
		session.removeAttribute("contrsto");
		gerarHistorico();
	}


	public List<Historicousuario> getListaHistorico() {
		return listaHistorico;
	}


	public void setListaHistorico(List<Historicousuario> listaHistorico) {
		this.listaHistorico = listaHistorico;
	}
	
	
	
	public Contrato getContrato() {
		return contrato;
	}


	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}


	public void gerarHistorico() {
		HistoricoUsuarioFacade historicoUsuarioFacade = new HistoricoUsuarioFacade();
		listaHistorico = historicoUsuarioFacade.lista("Select h From Historicousuario h"
				 + " Where h.idcontrato=" + contrato.getIdcontrato());
		if (listaHistorico == null) {
			listaHistorico = new ArrayList<Historicousuario>();
		}
	}
	
	
	
	
	

}
