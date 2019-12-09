package br.com.gestapromotora.managebean.banco;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.OrgaoBancoFacade;
import br.com.gestapromotora.model.Banco;
import br.com.gestapromotora.model.OrgaoBanco;

@Named
@ViewScoped
public class OrgaoBancoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OrgaoBanco> listaOrgao;
	private Banco banco;
	private OrgaoBanco orgaobanco;
	
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		banco = (Banco) session.getAttribute("banco");
		orgaobanco = (OrgaoBanco) session.getAttribute("orgaobanco");
		session.removeAttribute("banco");
		session.removeAttribute("orgaobanco");
		if (banco == null) {
			banco = orgaobanco.getBanco();
		}
		gerarListaOrgao();
	}


	public List<OrgaoBanco> getListaOrgao() {
		return listaOrgao;
	}


	public void setListaOrgao(List<OrgaoBanco> listaOrgao) {
		this.listaOrgao = listaOrgao;
	}


	public Banco getBanco() {
		return banco;
	}


	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	
	
	public void gerarListaOrgao() {
		OrgaoBancoFacade orgaoBancoFacade = new OrgaoBancoFacade();
		if (banco != null) {
			listaOrgao = orgaoBancoFacade.lista("Select o From OrgaoBanco o Where o.banco.idbanco=" + banco.getIdbanco());
			if (listaOrgao == null) {
				listaOrgao = new ArrayList<OrgaoBanco>();
			}
		}
	}
	
	
	public String novoOrgao() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("banco", banco);
		return "cadOrgaoBanco";
	}
	
	
	public String editar(OrgaoBanco orgaoBanco) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("banco", banco);
		session.setAttribute("orgaobanco", orgaoBanco);
		return "cadOrgaoBanco";
	}
	
	
	public String voltar(){
		return "consBanco";
	}
	
	
	public String consultaCoeficiente(OrgaoBanco orgaoBanco) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("orgaobanco", orgaoBanco);
		return "consCoeficiente";
	}
	
	
	
	
	
	
	
	
	
	

}
