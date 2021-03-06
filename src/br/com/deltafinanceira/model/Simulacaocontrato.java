package br.com.deltafinanceira.model;

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
@Table(name = "simulacaocontrato")
public class Simulacaocontrato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idsimulacaocontrato")
	private Integer idsimulacaocontrato;
	@Column(name = "datacadastro")
	private String datacadastro;
	@Column(name = "tabela")
	private String tabela;
	@Column(name = "taxajuros")
	private float taxajuros;
	@Column(name = "amortizacao")
	private String amortizacao;
	@JoinColumn(name = "contrato_idcontrato", referencedColumnName = "idcontrato")
	@ManyToOne(optional = false)
	private Contrato contrato;
	@JoinColumn(name = "orgaobanco_idorgaobanco", referencedColumnName = "idorgaobanco")
	@ManyToOne(optional = false)
	private OrgaoBanco orgaoBanco;
	
	
	public Simulacaocontrato() {
	
	}


	public synchronized Integer getIdsimulacaocontrato() {
		return idsimulacaocontrato;
	}


	public synchronized void setIdsimulacaocontrato(Integer idsimulacaocontrato) {
		this.idsimulacaocontrato = idsimulacaocontrato;
	}


	public String getDatacadastro() {
		return datacadastro;
	}


	public void setDatacadastro(String datacadastro) {
		this.datacadastro = datacadastro;
	}


	public synchronized Contrato getContrato() {
		return contrato;
	}


	public synchronized void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
	
	

	public synchronized OrgaoBanco getOrgaoBanco() {
		return orgaoBanco;
	}


	public synchronized void setOrgaoBanco(OrgaoBanco orgaoBanco) {
		this.orgaoBanco = orgaoBanco;
	}


	public String getTabela() {
		return tabela;
	}


	public void setTabela(String tabela) {
		this.tabela = tabela;
	}


	public float getTaxajuros() {
		return taxajuros;
	}


	public void setTaxajuros(float taxajuros) {
		this.taxajuros = taxajuros;
	}


	public String getAmortizacao() {
		return amortizacao;
	}


	public void setAmortizacao(String amortizacao) {
		this.amortizacao = amortizacao;
	}


	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idsimulacaocontrato != null ? idsimulacaocontrato.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Simulacaocontrato)) {
			return false;
		}
		Simulacaocontrato other = (Simulacaocontrato) object;
		if ((this.idsimulacaocontrato == null && other.idsimulacaocontrato != null)
				|| (this.idsimulacaocontrato != null && !this.idsimulacaocontrato.equals(other.idsimulacaocontrato))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Simulacaocontrato[ idsimulacaocontrato=" + idsimulacaocontrato + " ]";
	}
	
	
	
	

}
