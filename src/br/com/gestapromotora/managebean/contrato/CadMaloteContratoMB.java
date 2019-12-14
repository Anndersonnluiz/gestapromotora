package br.com.gestapromotora.managebean.contrato;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.MaloteContratoFacade;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.Malotecontrato;
import br.com.gestapromotora.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class CadMaloteContratoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Contrato contrato;
	private List<Malotecontrato> listaMaloteContrato;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private Malotecontrato malotecontrato;
	
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		contrato = (Contrato) session.getAttribute("contrato");
		session.removeAttribute("contrato");
		malotecontrato = new Malotecontrato();
		malotecontrato.setDataenvio(new Date());
		gerarListaMalotes();
	}



	public Contrato getContrato() {
		return contrato;
	}



	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
	
	
	public List<Malotecontrato> getListaMaloteContrato() {
		return listaMaloteContrato;
	}



	public void setListaMaloteContrato(List<Malotecontrato> listaMaloteContrato) {
		this.listaMaloteContrato = listaMaloteContrato;
	}



	public Malotecontrato getMalotecontrato() {
		return malotecontrato;
	}



	public void setMalotecontrato(Malotecontrato malotecontrato) {
		this.malotecontrato = malotecontrato;
	}



	public void gerarListaMalotes() {
		MaloteContratoFacade maloteContratoFacade = new MaloteContratoFacade();
		listaMaloteContrato = maloteContratoFacade.lista("Select m From Malotecontrato m WHERE m.usuario.idusuario=" 
		+ usuarioLogadoMB.getUsuario().getIdusuario() + " and m.contrato.idcontrato=" + contrato.getIdcontrato());
		if (listaMaloteContrato == null) {
			listaMaloteContrato = new ArrayList<Malotecontrato>();
		}
	}
	
	
	public void salvarMalote() {
		MaloteContratoFacade maloteContratoFacade = new MaloteContratoFacade();
		malotecontrato.setContrato(contrato);
		malotecontrato.setUsuario(usuarioLogadoMB.getUsuario());
		malotecontrato = maloteContratoFacade.salvar(malotecontrato);
		listaMaloteContrato.add(malotecontrato);
		malotecontrato = new Malotecontrato();
		malotecontrato.setDataenvio(new Date());
	}
	
	
	

	
	
	
}
