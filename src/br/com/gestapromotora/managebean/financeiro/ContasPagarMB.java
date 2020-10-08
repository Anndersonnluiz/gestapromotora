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

import br.com.gestapromotora.dao.ContasPagarDao;
import br.com.gestapromotora.dao.TipoDespesaDao;
import br.com.gestapromotora.facade.ContasPagarFacade;
import br.com.gestapromotora.model.Contaspagar;
import br.com.gestapromotora.model.Tipodespesa;
import br.com.gestapromotora.util.Formatacao;

@Named
@ViewScoped
public class ContasPagarMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Contaspagar> listaContasPagar;
	private Date dataini;
	private Date datafin;
	private List<Tipodespesa> listaTipoDespesa;
	private Tipodespesa tipodespesa;
	private float nvalorTotal;
	private String nomeMes;
	private int mesreferente;
	
	
	
	@PostConstruct
	public void init() {
		gerarListaInicial();
		gerarListaDespesa();
	}



	public List<Contaspagar> getListaContasPagar() {
		return listaContasPagar;
	}



	public void setListaContasPagar(List<Contaspagar> listaContasPagar) {
		this.listaContasPagar = listaContasPagar;
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



	public String getNomeMes() {
		return nomeMes;
	}



	public void setNomeMes(String nomeMes) {
		this.nomeMes = nomeMes;
	}



	public int getMesreferente() {
		return mesreferente;
	}



	public void setMesreferente(int mesreferente) {
		this.mesreferente = mesreferente;
	}



	public String novo() {
		return "cadContasPagar";
	}
	
	
	public String editar(Contaspagar contaspagar) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contaspagar", contaspagar);
		return "cadContasPagar";
	}
	
	
	public void gerarListaInicial() {
		ContasPagarDao contasPagarDao = new ContasPagarDao();
		mesreferente = Formatacao.getMesData(new Date()) + 1;
		nomeMes = Formatacao.nomeMes(mesreferente);
		listaContasPagar = contasPagarDao.lista("Select c From Contaspagar c Where c.mesreferente=" + mesreferente);
		if (listaContasPagar == null) {
			listaContasPagar = new ArrayList<Contaspagar>();
		}
		nvalorTotal = 0.0f;
		for (int i = 0; i < listaContasPagar.size(); i++) {
			nvalorTotal = nvalorTotal + listaContasPagar.get(i).getValor();
		}
	}
	
	
	
	public void pesquisar() {
		ContasPagarDao contasPagarDao = new ContasPagarDao();
		String sql = "Select c From Contaspagar c WHERE c.descricao like '%%'";
		if (tipodespesa != null && tipodespesa.getIdtipodespesa() != null) {
			sql = sql + " AND c.tipodespesa.idtipodespesa=" + tipodespesa.getIdtipodespesa();
		}
		if (dataini != null && datafin != null) {
			sql = sql + " AND c.datapagamento>='" + Formatacao.ConvercaoDataNfe(dataini) + "' AND "
					+ "c.datapagamento<='" + Formatacao.ConvercaoDataNfe(datafin) + "'" ;
		}
		if (mesreferente > 0) {
			sql = sql + " AND c.mesreferente=" + mesreferente;
			nomeMes = Formatacao.nomeMes(mesreferente);
		}else {
			nomeMes = "Todos";
		}
		listaContasPagar = contasPagarDao.lista(sql);
		if (listaContasPagar == null) {
			listaContasPagar = new ArrayList<Contaspagar>();
		}
		nvalorTotal = 0.0f;
		for (int i = 0; i < listaContasPagar.size(); i++) {
			nvalorTotal = nvalorTotal + listaContasPagar.get(i).getValor();
		}
	}
	
	
	public void limpar() {
		gerarListaInicial();
		tipodespesa = null;
		datafin = null;
		dataini = null;
		mesreferente = 0;
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
		ContasPagarFacade contasPagarFacade = new ContasPagarFacade();
		contasPagarFacade.excluir(listaContasPagar.get(linha).getIdcontaspagar());
		if (linha >= 0) {
			listaContasPagar.remove(linha);
		}
	}
	
	
	
	
	
	

}
