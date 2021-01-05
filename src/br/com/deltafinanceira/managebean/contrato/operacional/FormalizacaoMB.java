package br.com.deltafinanceira.managebean.contrato.operacional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.ContratoFacade;
import br.com.deltafinanceira.model.Contrato;
import br.com.deltafinanceira.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class FormalizacaoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private List<Contrato> listaContrato;
	
	
	
	@PostConstruct
	public void init() {
		gerarListaInicial();
	}
	
	
	
	
	
	
	
	public List<Contrato> getListaContrato() {
		return listaContrato;
	}


	public void setListaContrato(List<Contrato> listaContrato) {
		this.listaContrato = listaContrato;
	}


	
	public String editar(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		session.setAttribute("orgaobanco", contrato.getOrgaoBanco());
		session.setAttribute("voltarTela", "consFormalizacao");
		return "cadContrato";
	}


	public void gerarListaInicial() {
		ContratoFacade contratoFacade = new ContratoFacade();
		String sql = "Select c From Contrato c WHERE c.situacao.idsituacao=37  and c.simulacao=false";
		if (!usuarioLogadoMB.getUsuario().isAcessogeral() 
				&& !usuarioLogadoMB.getUsuario().getTipocolaborador().getAcessocolaborador().isAcessooperacional()) {
			sql = sql + " and c.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario();
		}
		listaContrato = contratoFacade.lista(sql);
		if (listaContrato == null) {
			listaContrato = new ArrayList<Contrato>();
		}
	}
	
	
	public String historicoContrato(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		return "linhaTempoContrato";
	}
	
	
	public String alterarSituacao(Contrato contrato) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		session.setAttribute("voltarTela", "consFormalizacao");
		return "alterarSituacao";
	}

}
