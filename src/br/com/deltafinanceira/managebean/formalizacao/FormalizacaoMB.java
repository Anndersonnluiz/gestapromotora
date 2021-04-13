package br.com.deltafinanceira.managebean.formalizacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.FormalizacaoFacade;
import br.com.deltafinanceira.facade.SituacaoFacade;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.model.Formalizacao;

@Named
@ViewScoped
public class FormalizacaoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Formalizacao> listaFormalizacao;

	@PostConstruct
	public void init() {
		gerarListaFormalizacao();
	}

	public List<Formalizacao> getListaFormalizacao() {
		return listaFormalizacao;
	}

	public void setListaFormalizacao(List<Formalizacao> listaFormalizacao) {
		this.listaFormalizacao = listaFormalizacao;
	}

	public void gerarListaFormalizacao() {
		FormalizacaoFacade formalizacaoFacade = new FormalizacaoFacade();
		listaFormalizacao = formalizacaoFacade
				.lista("Select f From Formalizacao f Where f.ativo=true and f.emitidocontrato=false");
		if (listaFormalizacao == null) {
			listaFormalizacao = new ArrayList<Formalizacao>();
		}
	}

	public String editar(Formalizacao formalizacao) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("formalizacao", formalizacao);
		return "cadFormalizacao";
	}

	public String ficha(Formalizacao formalizacao) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("formalizacao", formalizacao);
		return "fichaFormalizacao";
	}

	public String cadContrato(Formalizacao formalizacao) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		SituacaoFacade situacaoFacade = new SituacaoFacade();
		Contrato contrato = new Contrato();
		contrato.setSituacao(situacaoFacade.consultar(1));
		if (formalizacao.getUsuario().getTipovenda().equalsIgnoreCase("INSS")) {
			contrato.setOperacaoinss(true);
			contrato.setCredpessoal(false);
			contrato.setNomeoperacao("INSS");
		} else if (formalizacao.getUsuario().getTipovenda().equalsIgnoreCase("SIAPE")) {
			contrato.setOperacaoinss(false);
			contrato.setCredpessoal(false);
			contrato.setNomeoperacao("SIAPE");
		}
		contrato.setTipooperacao(formalizacao.getTipooperacao());
		contrato.setVoltarTela("consFormalizacao");
		session.setAttribute("contrato", contrato);
		session.setAttribute("formalizacao", formalizacao);
		return "cadContrato";
	}

	public String novo() {
		return "cadFormalizacao";
	}

}
