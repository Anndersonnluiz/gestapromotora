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
@Table(name = "rankingvendas")
public class Rankingvendas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idrankingvendas")
	private Integer idrankingvendas;
	@Column(name = "mes")
	private int mes;
	@Column(name = "ano")
	private int ano;
	@Column(name = "valorvenda")
	private float valorvenda;
	@Column(name = "comissaovenda")
	private float comissaovenda;
	@JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
	@ManyToOne(optional = false)
	private Usuario usuario;
	
	
	public Rankingvendas() {
	
	}


	public Integer getIdrankingvendas() {
		return idrankingvendas;
	}


	public void setIdrankingvendas(Integer idrankingvendas) {
		this.idrankingvendas = idrankingvendas;
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


	public float getValorvenda() {
		return valorvenda;
	}


	public void setValorvenda(float valorvenda) {
		this.valorvenda = valorvenda;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
	public float getComissaovenda() {
		return comissaovenda;
	}


	public void setComissaovenda(float comissaovenda) {
		this.comissaovenda = comissaovenda;
	}


	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idrankingvendas != null ? idrankingvendas.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Rankingvendas)) {
			return false;
		}
		Rankingvendas other = (Rankingvendas) object;
		if ((this.idrankingvendas == null && other.idrankingvendas != null)
				|| (this.idrankingvendas != null && !this.idrankingvendas.equals(other.idrankingvendas))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Rankingvendas[ idrankingvendas=" + idrankingvendas + " ]";
	}
	
	
	
	

}
