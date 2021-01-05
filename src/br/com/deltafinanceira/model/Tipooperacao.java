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
@Table(name = "tipooperacao")
public class Tipooperacao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idtipooperacao")
	private Integer idtipooperacao;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "margem")
	private boolean margem;
	
	
	
	public Tipooperacao() {
		
	}



	public Integer getIdtipooperacao() {
		return idtipooperacao;
	}



	public void setIdtipooperacao(Integer idtipooperacao) {
		this.idtipooperacao = idtipooperacao;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public boolean isMargem() {
		return margem;
	}



	public void setMargem(boolean margem) {
		this.margem = margem;
	}
	
	
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idtipooperacao != null ? idtipooperacao.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Tipooperacao)) {
			return false;
		}
		Tipooperacao other = (Tipooperacao) object;
		if ((this.idtipooperacao == null && other.idtipooperacao != null)
				|| (this.idtipooperacao != null && !this.idtipooperacao.equals(other.idtipooperacao))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Tipooperacao[ idtipooperacao=" + idtipooperacao + " ]";
	}
	

}
