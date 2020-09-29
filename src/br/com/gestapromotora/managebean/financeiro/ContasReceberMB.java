package br.com.gestapromotora.managebean.financeiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.dao.TipoDespesaDao;
import br.com.gestapromotora.facade.ContasReceberFacade;
import br.com.gestapromotora.model.Contasreceber;
import br.com.gestapromotora.model.Tipodespesa;
import br.com.gestapromotora.util.Formatacao;

@Named
@ViewScoped
public class ContasReceberMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Contasreceber> listaContasReceber;
	private Date dataini;
	private Date datafin;
	private List<Tipodespesa> listaTipoDespesa;
	private Tipodespesa tipodespesa;
	private float nvalorTotal;
	
	
	
	@PostConstruct
	public void init() {
		gerarListaInicial();
		gerarListaDespesa();
	}



	public List<Contasreceber> getListaContasReceber() {
		return listaContasReceber;
	}



	public void setListaContasReceber(List<Contasreceber> listaContasReceber) {
		this.listaContasReceber = listaContasReceber;
	}



	public Date getDataini() {
		return dataini;
	}



	public void setDataini(Date dataini) {
		this.dataini = dataini;
	}



	public Date getDatafin() {
		return datafin;
	}



	public void setDatafin(Date datafin) {
		this.datafin = datafin;
	}



	public List<Tipodespesa> getListaTipoDespesa() {
		return listaTipoDespesa;
	}



	public void setListaTipoDespesa(List<Tipodespesa> listaTipoDespesa) {
		this.listaTipoDespesa = listaTipoDespesa;
	}



	public Tipodespesa getTipodespesa() {
		return tipodespesa;
	}



	public void setTipodespesa(Tipodespesa tipodespesa) {
		this.tipodespesa = tipodespesa;
	}



	public float getNvalorTotal() {
		return nvalorTotal;
	}



	public void setNvalorTotal(float nvalorTotal) {
		this.nvalorTotal = nvalorTotal;
	}
	
	
	
	
	public String novo() {
		return "cadContasReceber";
	}
	
	
	public String editar(Contasreceber contasreceber) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contasreceber", contasreceber);
		return "cadContasReceber";
	}
	
	
	public void gerarListaInicial() {
		ContasReceberFacade contasReceberFacade = new ContasReceberFacade();
		listaContasReceber = contasReceberFacade.lista("Select c From Contasreceber c");
		if (listaContasReceber == null) {
			listaContasReceber = new ArrayList<Contasreceber>();
		}
		nvalorTotal = 0.0f;
		for (int i = 0; i < listaContasReceber.size(); i++) {
			nvalorTotal = nvalorTotal + listaContasReceber.get(i).getValor();
		}
	}
	
	
	
	public void pesquisar() {
		ContasReceberFacade contasReceberFacade = new ContasReceberFacade();
		String sql = "Select c From Contasreceber c WHERE c.descricao like '%%'";
		if (tipodespesa != null && tipodespesa.getIdtipodespesa() != null) {
			sql = sql + " AND c.tipodespesa.idtipodespesa=" + tipodespesa.getIdtipodespesa();
		}
		if (dataini != null && datafin != null) {
			sql = sql + " AND c.datavencimento>='" + Formatacao.ConvercaoDataNfe(dataini) + "' AND "
					+ "c.datavencimento<='" + Formatacao.ConvercaoDataNfe(datafin) + "'" ;
		}
		listaContasReceber = contasReceberFacade.lista(sql);
		if (listaContasReceber == null) {
			listaContasReceber = new ArrayList<Contasreceber>();
		}
		nvalorTotal = 0.0f;
		for (int i = 0; i < listaContasReceber.size(); i++) {
			nvalorTotal = nvalorTotal + listaContasReceber.get(i).getValor();
		}
	}
	
	
	public void limpar() {
		gerarListaInicial();
		tipodespesa = null;
		datafin = null;
		dataini = null;
	}
	
	
	public void gerarListaDespesa() {
		TipoDespesaDao tipoDespesaDao = new TipoDespesaDao();
		listaTipoDespesa = tipoDespesaDao.lista("Select t From Tipodespesa t");
		if (listaTipoDespesa == null) {
			listaTipoDespesa = new ArrayList<Tipodespesa>();
		}
	}
	
	
	
	public void excluirConta(String ilinha) {
		int linha = Integer.parseInt(ilinha);
		ContasReceberFacade contasReceberFacade = new ContasReceberFacade();
		contasReceberFacade.excluir(listaContasReceber.get(linha).getIdcontasreceber());
		if (linha >= 0) {
			listaContasReceber.remove(linha);
		}
	}
	
	
	

}
