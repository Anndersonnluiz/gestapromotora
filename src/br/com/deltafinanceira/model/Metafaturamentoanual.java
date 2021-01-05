package br.com.deltafinanceira.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "metafaturamentoanual")
public class Metafaturamentoanual implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idmetafaturamentoanual")
	private Integer idmetafaturamentoanual;
	@Column(name = "ano")
	private int ano;
	@Column(name = "valoratual")
	private float valoratual;
	@Column(name = "valortotal")
	private float valortotal;
	@Column(name = "percentualmeta")
	private float percentualmeta;
	@Column(name = "nvenda")
	private int nvenda;
	
	
	
	public Metafaturamentoanual() {
	
	}



	public Integer getIdmetafaturamentoanual() {
		return idmetafaturamentoanual;
	}



	public void setIdmetafaturamentoanual(Integer idmetafaturamentoanual) {
		this.idmetafaturamentoanual = idmetafaturamentoanual;
	}



	public int getAno() {
		return ano;
	}



	public void setAno(int ano) {
		this.ano = ano;
	}



	public float getValoratual() {
		return valoratual;
	}



	public void setValoratual(float valoratual) {
		this.valoratual = valoratual;
	}



	public float getValortotal() {
		return valortotal;
	}



	public void setValortotal(float valortotal) {
		this.valortotal = valortotal;
	}



	public float getPercentualmeta() {
		return percentualmeta;
	}



	public void setPercentualmeta(float percentualmeta) {
		this.percentualmeta = percentualmeta;
	}
	
	
	
	
	public int getNvenda() {
		return nvenda;
	}



	public void setNvenda(int nvenda) {
		this.nvenda = nvenda;
	}



	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idmetafaturamentoanual != null ? idmetafaturamentoanual.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Metafaturamentoanual)) {
			return false;
		}
		Metafaturamentoanual other = (Metafaturamentoanual) object;
		if ((this.idmetafaturamentoanual == null && other.idmetafaturamentoanual != null)
				|| (this.idmetafaturamentoanual != null && !this.idmetafaturamentoanual.equals(other.idmetafaturamentoanual))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Metafaturamentoanual[ idmetafaturamentoanual=" + idmetafaturamentoanual + " ]";
	}
	

}
