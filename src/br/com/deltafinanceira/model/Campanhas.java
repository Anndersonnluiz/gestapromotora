package br.com.deltafinanceira.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "campanhas")
public class Campanhas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idcampanhas")
	private Integer idcampanhas;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "datainicial")
	@Temporal(TemporalType.DATE)
	private Date datainicial;
	
	@Column(name = "datafinal")
	@Temporal(TemporalType.DATE)
	private Date datafinal;
	
	
	public Campanhas() {
	
	}


	public Integer getIdcampanhas() {
		return idcampanhas;
	}


	public void setIdcampanhas(Integer idcampanhas) {
		this.idcampanhas = idcampanhas;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Date getDatainicial() {
		return datainicial;
	}


	public void setDatainicial(Date datainicial) {
		this.datainicial = datainicial;
	}


	public Date getDatafinal() {
		return datafinal;
	}


	public void setDatafinal(Date datafinal) {
		this.datafinal = datafinal;
	}
	
	
	
	public int hashCode() {
	    int hash = 0;
	    hash += (this.idcampanhas != null) ? this.idcampanhas.hashCode() : 0;
	    return hash;
	  }
	  
	  public boolean equals(Object object) {
	    if (!(object instanceof Campanhas))
	      return false; 
	    Campanhas other = (Campanhas)object;
	    if ((this.idcampanhas == null && other.idcampanhas != null) || (
	      this.idcampanhas != null && !this.idcampanhas.equals(other.idcampanhas)))
	      return false; 
	    return true;
	  }
	  
	  public String toString() {
	    return "br.com.deltafinanceira.model.Campanhas[ idcampanhas=" + this.idcampanhas + " ]";
	  }
			
	
	
	
	

}
