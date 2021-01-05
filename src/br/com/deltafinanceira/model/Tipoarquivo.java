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
@Table(name = "tipoarquivo")
public class Tipoarquivo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idtipoarquivo")
	private Integer idtipoarquivo;
	@Column(name = "descricao")
	private String descricao;
	
	
	
	public Tipoarquivo() {
	
	}



	public Integer getIdtipoarquivo() {
		return idtipoarquivo;
	}



	public void setIdtipoarquivo(Integer idtipoarquivo) {
		this.idtipoarquivo = idtipoarquivo;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idtipoarquivo != null ? idtipoarquivo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Tipoarquivo)) {
			return false;
		}
		Tipoarquivo other = (Tipoarquivo) object;
		if ((this.idtipoarquivo == null && other.idtipoarquivo != null)
				|| (this.idtipoarquivo != null && !this.idtipoarquivo.equals(other.idtipoarquivo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Tipoarquivo[ idtipoarquivo=" + idtipoarquivo + " ]";
	}

}
