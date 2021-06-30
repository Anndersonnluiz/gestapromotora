package br.com.deltafinanceira.managebean.contrato;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.model.Contrato;

@Named
@ViewScoped
public class RelatorioContratosMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<Contrato> listaContrato;

	private String corretor;

	private String periodo;

	private int nContratos;

	private float valorProducao;

	private float comissaoRecebida;

	private String voltarTela;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		this.listaContrato = (List<Contrato>) session.getAttribute("listaContrato");
		session.removeAttribute("listaContrato");
		this.voltarTela = (String) session.getAttribute("voltarTela");
		session.removeAttribute("voltarTela");
		this.corretor = (String) session.getAttribute("corretor");
		session.removeAttribute("corretor");
		this.nContratos = this.listaContrato.size();
	}


	public String getCorretor() {
		return this.corretor;
	}

	public void setCorretor(String corretor) {
		this.corretor = corretor;
	}

	public String getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public int getnContratos() {
		return this.nContratos;
	}

	public void setnContratos(int nContratos) {
		this.nContratos = nContratos;
	}

	public float getValorProducao() {
		return this.valorProducao;
	}

	public void setValorProducao(float valorProducao) {
		this.valorProducao = valorProducao;
	}

	public float getComissaoRecebida() {
		return this.comissaoRecebida;
	}

	public void setComissaoRecebida(float comissaoRecebida) {
		this.comissaoRecebida = comissaoRecebida;
	}

	/**
	 * @return the listaContrato
	 */
	public List<Contrato> getListaContrato() {
		return listaContrato;
	}


	/**
	 * @param listaContrato the listaContrato to set
	 */
	public void setListaContrato(List<Contrato> listaContrato) {
		this.listaContrato = listaContrato;
	}


	public void gerarCalculos() {
		this.nContratos = this.listaContrato.size();
	}

	public String voltar() {
		return voltarTela;
	}
}
