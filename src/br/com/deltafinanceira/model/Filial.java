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
@Table(name = "filial")
public class Filial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idfilial")
	private Integer idfilial;
	@Column(name = "razaosocial")
	private String razaosocial;
	@Column(name = "cpfcnpj")
	private String cpfcnpj;
	@Column(name = "ativo")
	private boolean ativo;
	
	
	public Filial() {
	
	}


	public Integer getIdfilial() {
		return idfilial;
	}


	public void setIdfilial(Integer idfilial) {
		this.idfilial = idfilial;
	}


	public String getRazaosocial() {
		return razaosocial;
	}


	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}
	
	
	
	
	public boolean isAtivo() {
		return ativo;
	}


	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}


	public String getCpfcnpj() {
		return cpfcnpj;
	}


	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}


	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idfilial != null ? idfilial.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Departamento)) {
			return false;
		}
		Filial other = (Filial) object;
		if ((this.idfilial == null && other.idfilial != null)
				|| (this.idfilial != null && !this.idfilial.equals(other.idfilial))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Filial[ idfilial=" + idfilial + " ]";
	}
	
	
	
	

}
