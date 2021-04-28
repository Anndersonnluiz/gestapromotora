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
@Table(name = "campanhascontrato")
public class Campanhascontrato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idcampanhascontrato")
	private Integer idcampanhascontrato;
	
	
	@JoinColumn(name = "campanhas_idcampanhas", referencedColumnName = "idcampanhas")
	@ManyToOne(optional = false)
	private Campanhas campanhas;
	
	
	@JoinColumn(name = "contrato_idcontrato", referencedColumnName = "idcontrato")
	@ManyToOne(optional = false)
	private Contrato contrato;
	
	
	public Campanhascontrato() {
	
	}


	public Integer getIdcampanhascontrato() {
		return idcampanhascontrato;
	}


	public void setIdcampanhascontrato(Integer idcampanhascontrato) {
		this.idcampanhascontrato = idcampanhascontrato;
	}


	public Campanhas getCampanhas() {
		return campanhas;
	}


	public void setCampanhas(Campanhas campanhas) {
		this.campanhas = campanhas;
	}


	public Contrato getContrato() {
		return contrato;
	}


	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
	
	public int hashCode() {
	    int hash = 0;
	    hash += (this.idcampanhascontrato != null) ? this.idcampanhascontrato.hashCode() : 0;
	    return hash;
	  }
	  
	  public boolean equals(Object object) {
	    if (!(object instanceof Campanhascontrato))
	      return false; 
	    Campanhascontrato other = (Campanhascontrato)object;
	    if ((this.idcampanhascontrato == null && other.idcampanhascontrato != null) || (
	      this.idcampanhascontrato != null && !this.idcampanhascontrato.equals(other.idcampanhascontrato)))
	      return false; 
	    return true;
	  }
	  
	  public String toString() {
	    return "br.com.deltafinanceira.model.Campanhascontrato[ idcampanhascontrato=" + this.idcampanhascontrato + " ]";
	  }

}
