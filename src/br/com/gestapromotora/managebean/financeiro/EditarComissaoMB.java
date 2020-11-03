package br.com.gestapromotora.managebean.financeiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.HistoricoComissaoFacade;
import br.com.gestapromotora.facade.SituacaoFacade;
import br.com.gestapromotora.model.Historicocomissao;
import br.com.gestapromotora.model.Situacao;

@Named
@ViewScoped
public class EditarComissaoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Historicocomissao historicocomissao;
	private List<Situacao> listaSituacao;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		historicocomissao = (Historicocomissao) session.getAttribute("historicocomissao");
		session.removeAttribute("historicocomissao");
		gerarListaSituacao();
	}


	public Historicocomissao getHistoricocomissao() {
		return historicocomissao;
	}


	public void setHistoricocomissao(Historicocomissao historicocomissao) {
		this.historicocomissao = historicocomissao;
	}
	
	
	public List<Situacao> getListaSituacao() {
		return listaSituacao;
	}


	public void setListaSituacao(List<Situacao> listaSituacao) {
		this.listaSituacao = listaSituacao;
	}


	public String voltar() {
		return "consPagamentoComissao";
	}
	
	public String salvar() {
		HistoricoComissaoFacade historicoComissaoFacade = new HistoricoComissaoFacade();
		historicoComissaoFacade.salvar(historicocomissao);
		return "consPagamentoComissao";
	}
	
	
	public void gerarListaSituacao() {
		SituacaoFacade situacaoFacade = new SituacaoFacade();
		String sql = "Select s From Situacao s WHERE s.visualizar=true ";
		if (historicocomissao.getContrato().getTipooperacao().getIdtipooperacao() != 1) {
			sql = sql + " AND s.portabilidade=false ";
		}
		sql = sql + " ORDER BY s.descricao";
		listaSituacao = situacaoFacade.lista(sql);
		if (listaSituacao == null) {
			listaSituacao = new ArrayList<Situacao>();
		}
	}
	
	
	

}
