package br.com.deltafinanceira.managebean.relatorios;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.model.Comissaovenda;

@Named
@ViewScoped
public class FichaComissaoSupervisorMB  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Comissaovenda> listaComissao;
	private String supervisor;
	private String periodo;
	private int nContratos;
	private float valorProducao;
	private float comissaoRecebida;
	private int nPortabilidade;
	private int nMargem;
	private int nCartao;
	private boolean viewPortabilidade;
	private boolean viewMargem;
	private boolean viewCartao;
	private int nRefinanciamento;
	private boolean viewRefinanciamento;
	private String tipoFiltro;
	private String nomeSituacao;
	private Integer convenio;
	
	
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		listaComissao = (List<Comissaovenda>) session.getAttribute("listaComissao");
		session.removeAttribute("listaComissao");
		periodo = (String) session.getAttribute("periodo");
		session.removeAttribute("periodo");
		supervisor = (String) session.getAttribute("supervisor");
		session.removeAttribute("supervisor");
		gerarCalculos();
	}



	
	



	public List<Comissaovenda> getListaComissao() {
		return listaComissao;
	}








	public void setListaComissao(List<Comissaovenda> listaComissao) {
		this.listaComissao = listaComissao;
	}








	public String getSupervisor() {
		return supervisor;
	}








	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}








	public Integer getConvenio() {
		return convenio;
	}








	public void setConvenio(Integer convenio) {
		this.convenio = convenio;
	}



	public String getPeriodo() {
		return periodo;
	}



	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	
	
	public int getnContratos() {
		return nContratos;
	}



	public void setnContratos(int nContratos) {
		this.nContratos = nContratos;
	}



	public float getValorProducao() {
		return valorProducao;
	}



	public void setValorProducao(float valorProducao) {
		this.valorProducao = valorProducao;
	}



	public float getComissaoRecebida() {
		return comissaoRecebida;
	}



	public void setComissaoRecebida(float comissaoRecebida) {
		this.comissaoRecebida = comissaoRecebida;
	}



	public int getnPortabilidade() {
		return nPortabilidade;
	}



	public void setnPortabilidade(int nPortabilidade) {
		this.nPortabilidade = nPortabilidade;
	}



	public int getnMargem() {
		return nMargem;
	}



	public void setnMargem(int nMargem) {
		this.nMargem = nMargem;
	}



	public int getnCartao() {
		return nCartao;
	}



	public void setnCartao(int nCartao) {
		this.nCartao = nCartao;
	}



	public boolean isViewPortabilidade() {
		return viewPortabilidade;
	}



	public void setViewPortabilidade(boolean viewPortabilidade) {
		this.viewPortabilidade = viewPortabilidade;
	}



	public boolean isViewMargem() {
		return viewMargem;
	}



	public void setViewMargem(boolean viewMargem) {
		this.viewMargem = viewMargem;
	}



	public boolean isViewCartao() {
		return viewCartao;
	}



	public void setViewCartao(boolean viewCartao) {
		this.viewCartao = viewCartao;
	}



	public int getnRefinanciamento() {
		return nRefinanciamento;
	}



	public void setnRefinanciamento(int nRefinanciamento) {
		this.nRefinanciamento = nRefinanciamento;
	}



	public boolean isViewRefinanciamento() {
		return viewRefinanciamento;
	}



	public void setViewRefinanciamento(boolean viewRefinanciamento) {
		this.viewRefinanciamento = viewRefinanciamento;
	}



	public String getTipoFiltro() {
		return tipoFiltro;
	}



	public void setTipoFiltro(String tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}



	public String getNomeSituacao() {
		return nomeSituacao;
	}



	public void setNomeSituacao(String nomeSituacao) {
		this.nomeSituacao = nomeSituacao;
	}



	public void gerarCalculos() {
		nContratos = listaComissao.size();
		nCartao = 0;
		nMargem = 0;
		nPortabilidade = 0;
		viewCartao = false;
		viewMargem = false;
		viewPortabilidade = false;
		nRefinanciamento = 0;
		viewRefinanciamento= false;
		for (int i = 0; i < listaComissao.size(); i++) {
			valorProducao = valorProducao + listaComissao.get(i).getProdliq();
			comissaoRecebida = comissaoRecebida + listaComissao.get(i).getComissaovenda();
			if (listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 1) {
				nPortabilidade = nPortabilidade + 1;
				viewPortabilidade = true;
			}else if(listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 2) {
				nMargem = nMargem + 1;
				viewMargem = true;
			}else if(listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 3
					|| listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 4
					|| listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 5) {
				nCartao = nCartao + 1;
				viewCartao = true;
			}else if(listaComissao.get(i).getContrato().getTipooperacao().getIdtipooperacao() == 7) {
				nRefinanciamento = nRefinanciamento + 1;
				viewRefinanciamento = true;
			}
		}
	}
	
	
	public String voltar() {
		return "consComissaoSupervisor";
	}
	
}
