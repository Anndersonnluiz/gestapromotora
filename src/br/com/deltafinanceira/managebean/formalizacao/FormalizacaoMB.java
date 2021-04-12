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
import br.com.deltafinanceira.util.Mensagem;

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
		session.setAttribute("voltarTela", "consFormalizacao");
		return "cadFormalizacao";
	}

	public String ficha(Formalizacao formalizacao) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("formalizacao", formalizacao);
		session.setAttribute("voltarTela", "consFormalizacao");
		return "fichaFormalizacao";
	}

	public String cadContrato() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		SituacaoFacade situacaoFacade = new SituacaoFacade();
		Contrato contrato = new Contrato();
		contrato.setSituacao(situacaoFacade.consultar(1));
		if (condition) {
			
		}
		if (this.tipoOpcoes == 1) {
			this.contrato.setOperacaoinss(true);
			this.contrato.setCredpessoal(false);
			this.contrato.setNomeoperacao("INSS");
		} else if (this.tipoOpcoes == 2) {
			this.contrato.setOperacaoinss(false);
			this.contrato.setCredpessoal(false);
			this.contrato.setNomeoperacao("SIAPE");
		} else if (this.tipoOpcoes == 3) {
			this.contrato.setOperacaoinss(false);
			this.contrato.setCredpessoal(true);
			this.contrato.setNomeoperacao("CRED PESSOAL");
		}
		if (this.contrato.getTipooperacao().getIdtipooperacao().intValue() == 1) {
			this.contrato.setVoltarTela("consPortabilidade");
		} else if (this.contrato.isOperacaoinss()) {
			this.contrato.setVoltarTela("consDemaisOperacoesINSS");
		} else {
			this.contrato.setVoltarTela("consDemaisOperacoes");
		}
		session.setAttribute("contrato", contrato);
		return "cadContrato";
	}

}
