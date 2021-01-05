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
@Table(name = "metafaturamentomensal")
public class Metafaturamentomensal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idmetafaturamentomensal")
	private Integer idmetafaturamentomensal;
	@Column(name = "mes")
	private int mes;
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
	@Column(name = "cormeta")
	private String cormeta;
	@Column(name = "tipo")
	private String tipo;
	
	
	
	public Metafaturamentomensal() {
	
	}



	public Integer getIdmetafaturamentomensal() {
		return idmetafaturamentomensal;
	}



	public void setIdmetafaturamentomensal(Integer idmetafaturamentomensal) {
		this.idmetafaturamentomensal = idmetafaturamentomensal;
	}



	public int getMes() {
		return mes;
	}



	public void setMes(int mes) {
		this.mes = mes;
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



	public String getCormeta() {
		return cormeta;
	}



	public void setCormeta(String cormeta) {
		this.cormeta = cormeta;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idmetafaturamentomensal != null ? idmetafaturamentomensal.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Metafaturamentomensal)) {
			return false;
		}
		Metafaturamentomensal other = (Metafaturamentomensal) object;
		if ((this.idmetafaturamentomensal == null && other.idmetafaturamentomensal != null)
				|| (this.idmetafaturamentomensal != null && !this.idmetafaturamentomensal.equals(other.idmetafaturamentomensal))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Metafaturamentomensal[ idmetafaturamentomensal=" + idmetafaturamentomensal + " ]";
	}
	
	
	
	
	

}
