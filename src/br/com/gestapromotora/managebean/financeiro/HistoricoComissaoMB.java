package br.com.gestapromotora.managebean.financeiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.gestapromotora.facade.HistoricoComissaoFacade;
import br.com.gestapromotora.model.Historicocomissao;
import br.com.gestapromotora.util.Formatacao;

@Named
@ViewScoped
public class HistoricoComissaoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Historicocomissao> listaComissao;
	private String nome;
	private int cdinterno;
	private Date dataLancamento;
	
	
	@PostConstruct
	public void init() {
		gerarListaInicial();
	}


	public List<Historicocomissao> getListaComissao() {
		return listaComissao;
	}


	public void setListaComissao(List<Historicocomissao> listaComissao) {
		this.listaComissao = listaComissao;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getCdinterno() {
		return cdinterno;
	}


	public void setCdinterno(int cdinterno) {
		this.cdinterno = cdinterno;
	}


	public Date getDataLancamento() {
		return dataLancamento;
	}


	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	
	
	public void gerarListaInicial() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		listaComissao = historicoComissaoFacade.lista("Select h From Historicocomissao h");
		if (listaComissao == null) {
			listaComissao = new ArrayList<Historicocomissao>();
		}
	}
	
	
	public void pesquisar() {
		String sql = "Select h From Historicocomissao h Where h.usuario.nome like '%"+ nome +"%' ";
		if (cdinterno > 0) {
			sql = sql + " and h.usuario.cdinterno=" + cdinterno;
		}
		
		if (dataLancamento != null) {
			sql = sql + " and h.datalancamento='" + Formatacao.ConvercaoDataSql(dataLancamento) + "' ";
		}
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		listaComissao = historicoComissaoFacade.lista(sql);
		if (listaComissao == null) {
			listaComissao = new ArrayList<Historicocomissao>();
		}
	}
	
	public void limpar() {
		nome = "";
		cdinterno = 0;
		dataLancamento = null;
		gerarListaInicial();
	}

}
