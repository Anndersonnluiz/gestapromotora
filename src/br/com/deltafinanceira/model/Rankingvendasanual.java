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
@Table(name = "rankingvendasanual")
public class Rankingvendasanual implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idrankingvendasanual")
	private Integer idrankingvendasanual;
	@Column(name = "ano")
	private int ano;
	@Column(name = "valorvenda")
	private float valorvenda;
	@JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
	@ManyToOne(optional = false)
	private Usuario usuario;
	
	
	public Rankingvendasanual() {
	
	}


	public Integer getIdrankingvendasanual() {
		return idrankingvendasanual;
	}


	public void setIdrankingvendasanual(Integer idrankingvendasanual) {
		this.idrankingvendasanual = idrankingvendasanual;
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
	
	
	
	
	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Rankingvendasanual)) {
			return false;
		}
		Rankingvendasanual other = (Rankingvendasanual) object;
		if ((this.idrankingvendasanual == null && other.idrankingvendasanual != null)
				|| (this.idrankingvendasanual != null && !this.idrankingvendasanual.equals(other.idrankingvendasanual))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Rankingvendasanual[ idrankingvendasanual=" + idrankingvendasanual + " ]";
	}
	
	
	
	
	

}
