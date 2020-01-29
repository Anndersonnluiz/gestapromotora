package br.com.gestapromotora.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Coeficiente")
public class Coeficiente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idcoeficiente")
	private Integer idcoeficiente;
	@Column(name = "nometabela")
	private String nometabela;
	@Column(name = "nomeproduto")
	private String nomeproduto;
	@Column(name = "tipotabela")
	private String tipotabela;
	@Column(name = "codigotabela")
	private String codigotabela;
	@Column(name = "taxasjurosini")
	private Float taxasjurosini;
	@Column(name = "taxasjurosfin")
	private Float taxasjurosfin;
	@Column(name = "formdigstatustabela")
	private boolean formdigstatustabela;
	@JoinColumn(name = "orgaobanco_idorgaobanco", referencedColumnName = "idorgaobanco")
	@ManyToOne(optional = false)
	private OrgaoBanco orgaoBanco;
	@Column(name = "aberturaconta")
	private boolean aberturaconta;
	@Column(name = "compradividarefin")
	private boolean compradividarefin;
	@Column(name = "margemlivrerefin")
	private boolean margemlivrerefin;
	@Column(name = "portabilidademargemrefin")
	private boolean portabilidademargemrefin;
	@Column(name = "refinportabilidade")
	private boolean refinportabilidade;
	@Column(name = "compradivida")
	private boolean compradivida;
	@Column(name = "compradividarefinmargem")
	private boolean compradividarefinmargem;
	@Column(name = "compradividamargem")
	private boolean compradividamargem;
	@Column(name = "emprestimocomplementar")
	private boolean emprestimocomplementar;
	@Column(name = "margemlivre")
	private boolean margemlivre;
	@Column(name = "portabilidademargem")
	private boolean portabilidademargem;
	@Column(name = "refinanciamento")
	private boolean refinanciamento;
	@Column(name = "refinanciamentorec")
	private boolean refinanciamentorec;
	@Column(name = "portabilidade")
	private boolean portabilidade;
	@Column(name = "portabilidaderefin")
	private boolean portabilidaderefin;
	@Column(name = "refinportabilidademargem")
	private boolean refinportabilidademargem;
	@Column(name = "comercial")
	private boolean comercial;
	@Column(name = "coordenador")
	private boolean coordenador;
	@Column(name = "corretorexterno")
	private boolean corretorexterno;
	@Column(name = "corretorinterno")
	private boolean corretorinterno;
	@Column(name = "semgrupo")
	private boolean semgrupo;
	@Column(name = "comercialrestricao")
	private boolean comercialrestricao;
	@Column(name = "cobranca")
	private boolean cobranca;
	@Column(name = "retencao")
	private boolean retencao;
	@Column(name = "tipooperacoes")
	private boolean tipooperacoes;
	
	
	public Coeficiente() {
		aberturaconta = true;
		compradividarefin = true;
		margemlivrerefin = true;
		portabilidademargemrefin = true;
		refinportabilidade = true;
		compradivida = true;
		compradividarefinmargem = true;
		compradividamargem = true;
		emprestimocomplementar = true;
		margemlivre = true;
		portabilidademargem = true;
		refinanciamento = true;
		refinanciamentorec = true;
		portabilidade = true;
		portabilidaderefin = true;
		refinportabilidademargem = true;
		comercial = true;
		coordenador = true;
		corretorexterno = true;
		corretorinterno = true;
		semgrupo = true;
		comercialrestricao = true;
		cobranca = true;
		retencao = true;
		tipooperacoes = true;
	}


	public Integer getIdcoeficiente() {
		return idcoeficiente;
	}


	public void setIdcoeficiente(Integer idcoeficiente) {
		this.idcoeficiente = idcoeficiente;
	}


	public String getNometabela() {
		return nometabela;
	}


	public void setNometabela(String nometabela) {
		this.nometabela = nometabela;
	}


	public String getNomeproduto() {
		return nomeproduto;
	}


	public void setNomeproduto(String nomeproduto) {
		this.nomeproduto = nomeproduto;
	}


	public String getTipotabela() {
		return tipotabela;
	}


	public void setTipotabela(String tipotabela) {
		this.tipotabela = tipotabela;
	}


	public String getCodigotabela() {
		return codigotabela;
	}


	public void setCodigotabela(String codigotabela) {
		this.codigotabela = codigotabela;
	}


	public Float getTaxasjurosini() {
		return taxasjurosini;
	}


	public void setTaxasjurosini(Float taxasjurosini) {
		this.taxasjurosini = taxasjurosini;
	}


	public Float getTaxasjurosfin() {
		return taxasjurosfin;
	}


	public void setTaxasjurosfin(Float taxasjurosfin) {
		this.taxasjurosfin = taxasjurosfin;
	}


	public boolean isFormdigstatustabela() {
		return formdigstatustabela;
	}


	public void setFormdigstatustabela(boolean formdigstatustabela) {
		this.formdigstatustabela = formdigstatustabela;
	}


	public OrgaoBanco getOrgaoBanco() {
		return orgaoBanco;
	}


	public void setOrgaoBanco(OrgaoBanco orgaoBanco) {
		this.orgaoBanco = orgaoBanco;
	}
	
	
	
	public boolean isAberturaconta() {
		return aberturaconta;
	}


	public void setAberturaconta(boolean aberturaconta) {
		this.aberturaconta = aberturaconta;
	}


	public boolean isCompradividarefin() {
		return compradividarefin;
	}


	public void setCompradividarefin(boolean compradividarefin) {
		this.compradividarefin = compradividarefin;
	}


	public boolean isMargemlivrerefin() {
		return margemlivrerefin;
	}


	public void setMargemlivrerefin(boolean margemlivrerefin) {
		this.margemlivrerefin = margemlivrerefin;
	}


	public boolean isPortabilidademargemrefin() {
		return portabilidademargemrefin;
	}


	public void setPortabilidademargemrefin(boolean portabilidademargemrefin) {
		this.portabilidademargemrefin = portabilidademargemrefin;
	}


	public boolean isRefinportabilidade() {
		return refinportabilidade;
	}


	public void setRefinportabilidade(boolean refinportabilidade) {
		this.refinportabilidade = refinportabilidade;
	}


	public boolean isCompradivida() {
		return compradivida;
	}


	public void setCompradivida(boolean compradivida) {
		this.compradivida = compradivida;
	}


	public boolean isCompradividarefinmargem() {
		return compradividarefinmargem;
	}


	public void setCompradividarefinmargem(boolean compradividarefinmargem) {
		this.compradividarefinmargem = compradividarefinmargem;
	}


	public boolean isCompradividamargem() {
		return compradividamargem;
	}


	public void setCompradividamargem(boolean compradividamargem) {
		this.compradividamargem = compradividamargem;
	}


	public boolean isEmprestimocomplementar() {
		return emprestimocomplementar;
	}


	public void setEmprestimocomplementar(boolean emprestimocomplementar) {
		this.emprestimocomplementar = emprestimocomplementar;
	}


	public boolean isMargemlivre() {
		return margemlivre;
	}


	public void setMargemlivre(boolean margemlivre) {
		this.margemlivre = margemlivre;
	}


	public boolean isPortabilidademargem() {
		return portabilidademargem;
	}


	public void setPortabilidademargem(boolean portabilidademargem) {
		this.portabilidademargem = portabilidademargem;
	}


	public boolean isRefinanciamento() {
		return refinanciamento;
	}


	public void setRefinanciamento(boolean refinanciamento) {
		this.refinanciamento = refinanciamento;
	}


	public boolean isRefinanciamentorec() {
		return refinanciamentorec;
	}


	public void setRefinanciamentorec(boolean refinanciamentorec) {
		this.refinanciamentorec = refinanciamentorec;
	}


	public boolean isPortabilidade() {
		return portabilidade;
	}


	public void setPortabilidade(boolean portabilidade) {
		this.portabilidade = portabilidade;
	}


	public boolean isPortabilidaderefin() {
		return portabilidaderefin;
	}


	public void setPortabilidaderefin(boolean portabilidaderefin) {
		this.portabilidaderefin = portabilidaderefin;
	}


	public boolean isRefinportabilidademargem() {
		return refinportabilidademargem;
	}


	public void setRefinportabilidademargem(boolean refinportabilidademargem) {
		this.refinportabilidademargem = refinportabilidademargem;
	}


	public boolean isComercial() {
		return comercial;
	}


	public void setComercial(boolean comercial) {
		this.comercial = comercial;
	}


	public boolean isCoordenador() {
		return coordenador;
	}


	public void setCoordenador(boolean coordenador) {
		this.coordenador = coordenador;
	}


	public boolean isCorretorexterno() {
		return corretorexterno;
	}


	public void setCorretorexterno(boolean corretorexterno) {
		this.corretorexterno = corretorexterno;
	}


	public boolean isCorretorinterno() {
		return corretorinterno;
	}


	public void setCorretorinterno(boolean corretorinterno) {
		this.corretorinterno = corretorinterno;
	}


	public boolean isSemgrupo() {
		return semgrupo;
	}


	public void setSemgrupo(boolean semgrupo) {
		this.semgrupo = semgrupo;
	}


	public boolean isComercialrestricao() {
		return comercialrestricao;
	}


	public void setComercialrestricao(boolean comercialrestricao) {
		this.comercialrestricao = comercialrestricao;
	}


	public boolean isCobranca() {
		return cobranca;
	}


	public void setCobranca(boolean cobranca) {
		this.cobranca = cobranca;
	}


	public boolean isRetencao() {
		return retencao;
	}


	public void setRetencao(boolean retencao) {
		this.retencao = retencao;
	}


	public boolean isTipooperacoes() {
		return tipooperacoes;
	}


	public void setTipooperacoes(boolean tipooperacoes) {
		this.tipooperacoes = tipooperacoes;
	}


	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idcoeficiente != null ? idcoeficiente.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Banco)) {
			return false;
		}
		Coeficiente other = (Coeficiente) object;
		if ((this.idcoeficiente == null && other.idcoeficiente != null)
				|| (this.idcoeficiente != null && !this.idcoeficiente.equals(other.idcoeficiente))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.gestapromotora.model.Coeficiente[ idcoeficiente=" + idcoeficiente + " ]";
	}
	
	
	
	
	
	

}
