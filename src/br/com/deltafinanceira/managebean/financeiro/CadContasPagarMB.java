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

import br.com.deltafinanceira.dao.TipoDespesaDao;
import br.com.deltafinanceira.facade.ContasPagarFacade;
import br.com.deltafinanceira.model.Contaspagar;
import br.com.deltafinanceira.model.Tipodespesa;
import br.com.deltafinanceira.util.Formatacao;

@Named
@ViewScoped
public class CadContasPagarMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tipodespesa tipodespesa;
	private List<Tipodespesa> listaTipoDespesa;
	private Contaspagar contaspagar;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		contaspagar = (Contaspagar) session.getAttribute("contaspagar");
		session.removeAttribute("contaspagar");
		if (contaspagar == null) {
			contaspagar = new Contaspagar();
		}else {
			tipodespesa = contaspagar.getTipodespesa();
		}
		gerarListaDespesa();
	}


	public Tipodespesa getTipodespesa() {
		return tipodespesa;
	}


	public void setTipodespesa(Tipodespesa tipodespesa) {
		this.tipodespesa = tipodespesa;
	}


	public List<Tipodespesa> getListaTipoDespesa() {
		return listaTipoDespesa;
	}


	public void setListaTipoDespesa(List<Tipodespesa> listaTipoDespesa) {
		this.listaTipoDespesa = listaTipoDespesa;
	}


	public Contaspagar getContaspagar() {
		return contaspagar;
	}


	public void setContaspagar(Contaspagar contaspagar) {
		this.contaspagar = contaspagar;
	}
	
	
	public String cancelar() {
		return "consContasPagar"; 
	}
	
	
	public String salvar() {
		ContasPagarFacade contasPagarFacade = new ContasPagarFacade();
		if (contaspagar.getDatavencimento() == null) {
			contaspagar.setDatavencimento(new Date());
		}
		Date novadatavencimento = contaspagar.getDatavencimento();
		if (contaspagar.getTotalparcela() > 1 && contaspagar.getIdcontaspagar() == null) {
			contaspagar.setTipodespesa(tipodespesa);
			contaspagar.setNparcela(1);
			contaspagar.setMesreferente(Formatacao.getMesData(contaspagar.getDatavencimento()) + 1);
			contasPagarFacade.salvar(contaspagar);
			for (int i = 2; i <= contaspagar.getTotalparcela(); i++) {
				novadatavencimento = Formatacao.SomarDiasData(novadatavencimento, 30);
				Contaspagar gerandoParcelas = new Contaspagar();
				gerandoParcelas.setDatapagamento(contaspagar.getDatapagamento());
				gerandoParcelas.setDatavencimento(novadatavencimento);
				gerandoParcelas.setDescricao(contaspagar.getDescricao());
				gerandoParcelas.setNparcela(i);
				gerandoParcelas.setTipodespesa(tipodespesa);
				gerandoParcelas.setTotalparcela(contaspagar.getTotalparcela());
				gerandoParcelas.setValor(contaspagar.getValor());
				gerandoParcelas.setMesreferente(Formatacao.getMesData(novadatavencimento) + 1);
				contasPagarFacade.salvar(gerandoParcelas);
				
			}
		}else {
			contaspagar.setMesreferente(Formatacao.getMesData(contaspagar.getDatavencimento()) + 1);
			contaspagar.setTipodespesa(tipodespesa);
			contasPagarFacade.salvar(contaspagar);
		}
		return "consContasPagar";
	}
	
	
	public void gerarListaDespesa() {
		TipoDespesaDao tipoDespesaDao = new TipoDespesaDao();
		listaTipoDespesa = tipoDespesaDao.lista("Select t From Tipodespesa t");
		if (listaTipoDespesa == null) {
			listaTipoDespesa = new ArrayList<Tipodespesa>();
		}
	}
	
	
	
	

}
