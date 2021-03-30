package br.com.deltafinanceira.managebean.lead;

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

import br.com.deltafinanceira.facade.LeadFacade;
import br.com.deltafinanceira.model.Lead;
import br.com.deltafinanceira.util.Formatacao;
import br.com.deltafinanceira.util.UsuarioLogadoMB;


@Named
@ViewScoped
public class LeadMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Lead> listaLead;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private int nNovos;
	private int nHoje;
	private int nAtrasados;
	private int nTodos;
	
	
	@PostConstruct
	public void init() {
		gerarListaLeadTudo();
	}


	public List<Lead> getListaLead() {
		return listaLead;
	}


	public void setListaLead(List<Lead> listaLead) {
		this.listaLead = listaLead;
	}
	
	
	
	
	
	public int getnNovos() {
		return nNovos;
	}


	public void setnNovos(int nNovos) {
		this.nNovos = nNovos;
	}


	public int getnHoje() {
		return nHoje;
	}


	public void setnHoje(int nHoje) {
		this.nHoje = nHoje;
	}


	public int getnAtrasados() {
		return nAtrasados;
	}


	public void setnAtrasados(int nAtrasados) {
		this.nAtrasados = nAtrasados;
	}


	public int getnTodos() {
		return nTodos;
	}


	public void setnTodos(int nTodos) {
		this.nTodos = nTodos;
	}


	public String novo() {
		return "cadLead";	
	}
	
	
	public void gerarListaLead(int situacao) {
		String datahoje = Formatacao.ConvercaoDataNfe(new Date());
		LeadFacade leadFacade = new LeadFacade();
		String sql = "Select l From Lead l Where l.cliente.usuario.idusuario=" 
				+ usuarioLogadoMB.getUsuario().getIdusuario();
		if (situacao == 1) {
			sql = sql + " and l.situacao=1";
		}else if(situacao == 2) {
			sql = sql + " and l.proximocontato='" + datahoje + "'";
		}else if(situacao == 3) {
			sql = sql + " and l.proximocontato<'" + datahoje + "'";
		}
		sql = sql + " ORDER BY l.proximocontato DESC";
		listaLead = leadFacade.lista(sql);
		if (listaLead == null) {
			listaLead = new ArrayList<Lead>();
		}
	}
	
	
	public void gerarListaLeadTudo() {
		String datahoje = Formatacao.ConvercaoDataNfe(new Date());
		Date dataHojeD = Formatacao.ConvercaoStringData(datahoje);
		LeadFacade leadFacade = new LeadFacade();
		String sql = "Select l From Lead l Where l.situacao<=6"
				+ " and l.cliente.usuario.idusuario=" 
				+ usuarioLogadoMB.getUsuario().getIdusuario();
		listaLead = leadFacade.lista(sql);
		if (listaLead == null) {
			listaLead = new ArrayList<Lead>();
		}
		nAtrasados = 0;
		nHoje = 0;
		nNovos = 0;
		nTodos = 0;
		for (int i = 0; i < listaLead.size(); i++) {
			if (listaLead.get(i).getSituacao() == 1) {
				nNovos = nNovos + 1;
			}else if(listaLead.get(i).getProximocontato() != null 
					&& listaLead.get(i).getProximocontato().equals(dataHojeD)) {
				nHoje = nHoje + 1;
			}else if(listaLead.get(i).getProximocontato() != null 
					&& listaLead.get(i).getProximocontato().before(dataHojeD)) {
				nAtrasados = nAtrasados + 1;
			}
			nTodos = nTodos + 1;
		}
	}
	
	
	public String historico(Lead lead) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("lead", lead);
		return "historicoLead";
	}
	 
	
	
	
	
	

}
