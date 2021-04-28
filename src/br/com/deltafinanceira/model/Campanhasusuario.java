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
@Table(name = "campanhasusuario")
public class Campanhasusuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idcampanhasusuario")
	private Integer idcampanhasusuario;
	
	@JoinColumn(name = "campanhas_idcampanhas", referencedColumnName = "idcampanhas")
	@ManyToOne(optional = false)
	private Campanhas campanhas;
	
	@JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
	@ManyToOne(optional = false)
	private Usuario usuario;
	
	
	public Campanhasusuario() {
	
	}


	public Integer getIdcampanhasusuario() {
		return idcampanhasusuario;
	}


	public void setIdcampanhasusuario(Integer idcampanhasusuario) {
		this.idcampanhasusuario = idcampanhasusuario;
	}


	public Campanhas getCampanhas() {
		return campanhas;
	}


	public void setCampanhas(Campanhas campanhas) {
		this.campanhas = campanhas;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public int hashCode() {
	    int hash = 0;
	    hash += (this.idcampanhasusuario != null) ? this.idcampanhasusuario.hashCode() : 0;
	    return hash;
	  }
	  
	  public boolean equals(Object object) {
	    if (!(object instanceof Campanhasusuario))
	      return false; 
	    Campanhasusuario other = (Campanhasusuario)object;
	    if ((this.idcampanhasusuario == null && other.idcampanhasusuario != null) || (
	      this.idcampanhasusuario != null && !this.idcampanhasusuario.equals(other.idcampanhasusuario)))
	      return false; 
	    return true;
	  }
	  
	  public String toString() {
	    return "br.com.deltafinanceira.model.Campanhasusuario[ idcampanhasusuario=" + this.idcampanhasusuario + " ]";
	  }

	
	
	
}
