package br.com.deltafinanceira.managebean.contrato;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.facade.HistoricoPendenciaFacade;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Historicopendencia;
import br.com.deltafinanceira.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class CadHistoricoPendenciaMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Historicopendencia historicopendencia;
	private Contrato contrato;
	private List<Historicopendencia> listaHistoricoPendencia;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		contrato = (Contrato) session.getAttribute("contrato");
		session.removeAttribute("contrato");
		gerarListaHistoricoPendencia();
		historicopendencia = new Historicopendencia();
	}



	public Historicopendencia getHistoricopendencia() {
		return historicopendencia;
	}



	public void setHistoricopendencia(Historicopendencia historicopendencia) {
		this.historicopendencia = historicopendencia;
	}



	public Contrato getContrato() {
		return contrato;
	}



	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}



	public List<Historicopendencia> getListaHistoricoPendencia() {
		return listaHistoricoPendencia;
	}



	public void setListaHistoricoPendencia(List<Historicopendencia> listaHistoricoPendencia) {
		this.listaHistoricoPendencia = listaHistoricoPendencia;
	}
	
	
	
	public void gerarListaHistoricoPendencia() {
		HistoricoPendenciaFacade historicoPendenciaFacade = new HistoricoPendenciaFacade();
		listaHistoricoPendencia = historicoPendenciaFacade.lista("Select h From Historicopendencia h WHERE h.contrato.idcontrato=" + contrato.getIdcontrato());
		if (listaHistoricoPendencia == null) {
			listaHistoricoPendencia = new ArrayList<Historicopendencia>();
		}
	}
	
	
	public void salvarPendencia() {
		HistoricoPendenciaFacade historicoPendenciaFacade = new HistoricoPendenciaFacade();
		historicopendencia.setContrato(contrato);
		historicopendencia.setUsuario(usuarioLogadoMB.getUsuario());
		historicopendencia = historicoPendenciaFacade.salvar(historicopendencia);
		if (!contrato.isPendente()) {
			ContratoFacade contratoFacade = new ContratoFacade();
			contrato.setPendente(true);
			contratoFacade.salvar(contrato);
		}
		listaHistoricoPendencia.add(historicopendencia);
		historicopendencia = new Historicopendencia();
	}
	
	
	public String voltar() {
		return "consContrato";
	}
	
	
	
	
	
	

}
