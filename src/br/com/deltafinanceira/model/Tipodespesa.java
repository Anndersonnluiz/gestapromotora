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
@Table(name = "tipodespesa")
public class Tipodespesa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idtipodespesa")
	private Integer idtipodespesa;
	@Column(name = "descricao")
	private String descricao;
	
	
	public Tipodespesa() {
	
	}


	public Integer getIdtipodespesa() {
		return idtipodespesa;
	}


	public void setIdtipodespesa(Integer idtipodespesa) {
		this.idtipodespesa = idtipodespesa;
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
		hash += (idtipodespesa != null ? idtipodespesa.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Tipodespesa)) {
			return false;
		}
		Tipodespesa other = (Tipodespesa) object;
		if ((this.idtipodespesa == null && other.idtipodespesa != null)
				|| (this.idtipodespesa != null && !this.idtipodespesa.equals(other.idtipodespesa))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Tipodespesa[ idtipodespesa=" + idtipodespesa + " ]";
	}
	
	
	
	

}
