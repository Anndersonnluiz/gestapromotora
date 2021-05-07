package br.com.deltafinanceira.managebean.contrato.operacional;

import br.com.deltafinanceira.model.Historicocomissao;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class RelatorioProducaoMB implements Serializable {
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

	private Integer convenio;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		this.listaComissao = (List<Historicocomissao>) session.getAttribute("listaComissao");
		session.removeAttribute("listaComissao");
		this.periodo = (String) session.getAttribute("periodo");
		session.removeAttribute("periodo");
		this.corretor = (String) session.getAttribute("corretor");
		session.removeAttribute("corretor");
		convenio = (Integer) session.getAttribute("convenio");
		session.removeAttribute("convenio");
		gerarCalculos();
	}

	public List<Historicocomissao> getListaComissao() {
		return this.listaComissao;
	}

	public void setListaComissao(List<Historicocomissao> listaComissao) {
		this.listaComissao = listaComissao;
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

	public int getnPortabilidade() {
		return this.nPortabilidade;
	}

	public void setnPortabilidade(int nPortabilidade) {
		this.nPortabilidade = nPortabilidade;
	}

	public int getnMargem() {
		return this.nMargem;
	}

	public void setnMargem(int nMargem) {
		this.nMargem = nMargem;
	}

	public int getnCartao() {
		return this.nCartao;
	}

	public void setnCartao(int nCartao) {
		this.nCartao = nCartao;
	}

	public boolean isViewPortabilidade() {
		return this.viewPortabilidade;
	}

	public void setViewPortabilidade(boolean viewPortabilidade) {
		this.viewPortabilidade = viewPortabilidade;
	}

	public boolean isViewMargem() {
		return this.viewMargem;
	}

	public void setViewMargem(boolean viewMargem) {
		this.viewMargem = viewMargem;
	}

	public boolean isViewCartao() {
		return this.viewCartao;
	}

	public void setViewCartao(boolean viewCartao) {
		this.viewCartao = viewCartao;
	}

	public int getnRefinanciamento() {
		return this.nRefinanciamento;
	}

	public void setnRefinanciamento(int nRefinanciamento) {
		this.nRefinanciamento = nRefinanciamento;
	}

	public boolean isViewRefinanciamento() {
		return this.viewRefinanciamento;
	}

	public void setViewRefinanciamento(boolean viewRefinanciamento) {
		this.viewRefinanciamento = viewRefinanciamento;
	}

	public Integer getConvenio() {
		return convenio;
	}

	public void setConvenio(Integer convenio) {
		this.convenio = convenio;
	}

	public void gerarCalculos() {
		this.nContratos = this.listaComissao.size();
		this.nCartao = 0;
		this.nMargem = 0;
		this.nPortabilidade = 0;
		this.viewCartao = false;
		this.viewMargem = false;
		this.viewPortabilidade = false;
		this.nRefinanciamento = 0;
		this.viewRefinanciamento = false;
		for (int i = 0; i < this.listaComissao.size(); i++) {
			this.valorProducao += ((Historicocomissao) this.listaComissao.get(i)).getProdliq();
			if (((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao().getIdtipooperacao()
					.intValue() == 1) {
				this.nPortabilidade++;
				this.viewPortabilidade = true;
			} else if (((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
					.getIdtipooperacao().intValue() == 2) {
				this.nMargem++;
				this.viewMargem = true;
			} else if (((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
					.getIdtipooperacao().intValue() == 3
					|| ((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
							.getIdtipooperacao().intValue() == 4
					|| ((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
							.getIdtipooperacao().intValue() == 5) {
				this.nCartao++;
				this.viewCartao = true;
			} else if (((Historicocomissao) this.listaComissao.get(i)).getContrato().getTipooperacao()
					.getIdtipooperacao().intValue() == 7) {
				this.nRefinanciamento++;
				this.viewRefinanciamento = true;
			}
		}
	}

	public String voltar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("convenio", convenio);
		return "consProducao";
	}
}
