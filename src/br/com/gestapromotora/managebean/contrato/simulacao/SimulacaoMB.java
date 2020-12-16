package br.com.gestapromotora.managebean.contrato.simulacao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.SimulacaoContratoFacade;
import br.com.gestapromotora.model.Simulacaocontrato;
import br.com.gestapromotora.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class SimulacaoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private List<Simulacaocontrato> listaSimulacao;
	
	
	
	@PostConstruct
	public void init() {
		gerarListaSimulacao();
	}



	public synchronized List<Simulacaocontrato> getListaSimulacao() {
		return listaSimulacao;
	}



	public synchronized void setListaSimulacao(List<Simulacaocontrato> listaSimulacao) {
		this.listaSimulacao = listaSimulacao;
	}
	
	
	
	
	public void gerarListaSimulacao() {
		SimulacaoContratoFacade simulacaoContratoFacade = new SimulacaoContratoFacade();
		String sql = "Select s From Simulacaocontrato s WHERE s.contrato.simulacao=true";
		if (!usuarioLogadoMB.getUsuario().isAcessogeral() 
				|| !usuarioLogadoMB.getUsuario().isResponsaveldepartamento()) {
			sql = sql + " and s.contrato.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario();
		}
		listaSimulacao = simulacaoContratoFacade.lista(sql);
	}
	
	
	
	public String novo() {
		return "cadSimulacaoContrato";
	}
	
	
	public String editar(Simulacaocontrato simulacaocontrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("simulacaocontrato", simulacaocontrato);
		return "cadSimulacaoContrato";
	}
	
	
	public String imprimirFicha(Simulacaocontrato simulacaocontrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("simulacaocontrato", simulacaocontrato);
		return "fichaSimulacao";
	}
	
	
	
	
	

}
