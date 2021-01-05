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
@Table(name = "ajuda")
public class Ajuda implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idajuda")
	private Integer idajuda;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "link")
	private String link;
	
	
	public Ajuda() {
	
	}


	public Integer getIdajuda() {
		return idajuda;
	}


	public void setIdajuda(Integer idajuda) {
		this.idajuda = idajuda;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}
	
	
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idajuda != null ? idajuda.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Ajuda)) {
			return false;
		}
		Ajuda other = (Ajuda) object;
		if ((this.idajuda == null && other.idajuda != null)
				|| (this.idajuda != null && !this.idajuda.equals(other.idajuda))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Ajuda[ idajuda=" + idajuda + " ]";
	}

}
