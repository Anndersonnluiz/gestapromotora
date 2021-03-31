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
	private String status;
	private int nProx7;
	private int nStandBy;
	private int nContratos;
	
	
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getnProx7() {
		return nProx7;
	}


	public void setnProx7(int nProx7) {
		this.nProx7 = nProx7;
	}


	public int getnStandBy() {
		return nStandBy;
	}


	public void setnStandBy(int nStandBy) {
		this.nStandBy = nStandBy;
	}


	public int getnContratos() {
		return nContratos;
	}


	public void setnContratos(int nContratos) {
		this.nContratos = nContratos;
	}
 

	public String novo() {
		return "cadLead";	
	}
	
	
	public void gerarListaLead(int situacao) {
		String datahoje = Formatacao.ConvercaoDataNfe(new Date());
		Date dataHojeD = Formatacao.ConvercaoStringData(datahoje);
		Date data7D = Formatacao.SomarDiasData(dataHojeD, 7);
		String data7 = Formatacao.ConvercaoDataNfe(data7D);
		LeadFacade leadFacade = new LeadFacade();
		String sql = "Select l From Lead l Where l.cliente.usuario.idusuario=" 
				+ usuarioLogadoMB.getUsuario().getIdusuario();
		if (situacao == 1) {
			sql = sql + " and l.situacao=1";
			status = "Novos";
		}else if(situacao == 2) {
			sql = sql + " and (l.situacao<>1 and l.situacao<>7 and l.situacao<>8)"
					+ " and l.proximocontato='" + datahoje + "'";
			status = "Hoje";
		}else if(situacao == 3) {
			sql = sql + " and (l.situacao<>1 and l.situacao<>7 and l.situacao<>8)" 
					+ " and l.proximocontato<'" + datahoje + "'";
			status = "Atrasados";
		}else if(situacao == 5) {
			sql = sql + " and (l.situacao<>1 and l.situacao<>7 and l.situacao<>8)"
					+ " and l.proximocontato>'" + datahoje + "' and l.proximocontato<='"
					+ data7 + "'";
			status = "Próx. 7 Dias";
		}else if(situacao == 6) {
			sql = sql + " and l.situacao=7";
			status = "Stand By";
		}else if(situacao == 7) {
			sql = sql + " and (l.situacao=5 or l.situacao=6)";
			status = "Pós Venda";
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
		String sql = "Select l From Lead l Where l.situacao<8"
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
		nProx7 = 0;
		nStandBy = 0;
		nContratos = 0;
		String dProx = "";
		Date dataProximo = null;
		Date data7 = null;
		for (int i = 0; i < listaLead.size(); i++) {
			if (listaLead.get(i).getProximocontato() != null) {
				dProx = Formatacao.ConvercaoDataNfe(listaLead.get(i).getProximocontato());
				dataProximo = Formatacao.ConvercaoStringData(dProx);
				data7 = Formatacao.SomarDiasData(dataHojeD, 7);
			}
			if (listaLead.get(i).getSituacao() == 1) {
				nNovos = nNovos + 1;
				nTodos = nTodos + 1;
			}else if((listaLead.get(i).getSituacao() != 8 && listaLead.get(i).getSituacao() !=7) 
					&& (dataProximo != null 
					&& dataProximo.equals(dataHojeD))) {
				nHoje = nHoje + 1;
				nTodos = nTodos + 1;
			}else if((listaLead.get(i).getSituacao() != 8 && listaLead.get(i).getSituacao() !=7) 
					&& (dataProximo != null 
					&& dataProximo.before(dataHojeD))) {
				nAtrasados = nAtrasados + 1;
				nTodos = nTodos + 1;
			}else if((listaLead.get(i).getSituacao() != 8 && listaLead.get(i).getSituacao() !=7) 
					&& (dataProximo != null 
					&& (dataProximo.before(data7) || dataProximo.equals(data7)))) {
				nProx7 = nProx7 + 1;
				nTodos = nTodos + 1;
			}else if(listaLead.get(i).getSituacao() == 7) {
				nStandBy = nStandBy + 1;
				nTodos = nTodos + 1;
			}else if(listaLead.get(i).getSituacao() == 5 || listaLead.get(i).getSituacao() == 6) {
				nContratos = nContratos + 1;
			}
		}
		status = "Todos";
	}
	
	
	public String historico(Lead lead) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("lead", lead);
		return "historicoLead";
	}
	
	
	public String editar(Lead lead) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("lead", lead);
		return "cadLead";
	}
	
	
	
	
	

}
