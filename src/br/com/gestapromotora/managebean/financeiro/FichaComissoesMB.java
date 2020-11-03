package br.com.gestapromotora.managebean.financeiro;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.model.Historicocomissao;

@Named
@ViewScoped
public class FichaComissoesMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Historicocomissao> listaComissao;
	private String corretor;
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
	
	
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		listaComissao = (List<Historicocomissao>) session.getAttribute("listaComissao");
		session.removeAttribute("listaComissao");
		tipoFiltro = (String) session.getAttribute("tipoFiltro");
		session.removeAttribute("tipoFiltro");
		periodo = (String) session.getAttribute("periodo");
		session.removeAttribute("periodo");
		corretor = (String) session.getAttribute("corretor");
		session.removeAttribute("corretor");
		gerarCalculos();
	}



	public List<Historicocomissao> getListaComissao() {
		return listaComissao;
	}



	public void setListaComissao(List<Historicocomissao> listaComissao) {
		this.listaComissao = listaComissao;
	}



	



	public String getCorretor() {
		return corretor;
	}



	public void setCorretor(String corretor) {
		this.corretor = corretor;
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
			comissaoRecebida = comissaoRecebida + listaComissao.get(i).getCmsliq();
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
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("tipoFiltro", tipoFiltro);
		return "consPagamentoComissao";
	}
	
	
	
	
	
	

}
