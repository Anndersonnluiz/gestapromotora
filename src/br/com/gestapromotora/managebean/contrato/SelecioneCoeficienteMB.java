package br.com.gestapromotora.managebean.contrato;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.ValoresCoeficienteFacade;
import br.com.gestapromotora.model.Contrato;
import br.com.gestapromotora.model.OrgaoBanco;
import br.com.gestapromotora.model.Valorescoeficiente;

@Named
@ViewScoped
public class SelecioneCoeficienteMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Contrato contrato;
	private List<Valorescoeficiente> listaValores;
	private OrgaoBanco orgaobanco;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		contrato = (Contrato) session.getAttribute("contrato");
		orgaobanco = (OrgaoBanco) session.getAttribute("orgaobanco");
		session.removeAttribute("contrato");
		session.removeAttribute("orgaobanco");
		gerarListaValores();
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public List<Valorescoeficiente> getListaValores() {
		return listaValores;
	}

	public void setListaValores(List<Valorescoeficiente> listaValores) {
		this.listaValores = listaValores;
	}

	public void gerarListaValores() {
		ValoresCoeficienteFacade valoresCoeficienteFacade = new ValoresCoeficienteFacade();
		listaValores = valoresCoeficienteFacade
				.lista("Select v From Valorescoeficiente v WHERE v.coeficiente.orgaoBanco.idorgaobanco="
						+ orgaobanco.getIdorgaobanco() + " AND v.coeficiente.tipooperacao.idtipooperacao="
						+ contrato.getTipooperacao().getIdtipooperacao());
		if (listaValores == null) {
			listaValores = new ArrayList<Valorescoeficiente>();
		}
	}

	public String selecionarCoeficiente(Valorescoeficiente valorescoeficiente) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		contrato.setValorescoeficiente(valorescoeficiente);
		if (contrato.getTipooperacao() != null) {
			if (contrato.getTipooperacao().isMargem()) {
				contrato.setValoroperacao(contrato.getMargemutilizada() / valorescoeficiente.getCoeficientevalor());
			} else {
				contrato.setValoroperacao(
						(contrato.getParcela() / valorescoeficiente.getCoeficientevalor()) - contrato.getValorquitar());
			}
		}
		contrato.setValorcliente(contrato.getValoroperacao());
		session.setAttribute("contrato", contrato);
		session.setAttribute("orgaobanco", orgaobanco);
		return "cadContrato";
	}

	public String voltar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("contrato", contrato);
		session.setAttribute("orgaobanco", orgaobanco);
		return "cadContrato";
	}

}
