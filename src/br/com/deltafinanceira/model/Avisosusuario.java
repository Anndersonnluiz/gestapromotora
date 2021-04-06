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
@Table(name = "avisosusuario")
public class Avisosusuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idavisosusuario")
	private Integer idavisosusuario;
	@Column(name = "visto")
	private boolean visto;
	@JoinColumn(name = "avisos_idavisos", referencedColumnName = "idavisos")
	@ManyToOne(optional = false)
	private Avisos avisos;
	@JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
	@ManyToOne(optional = false)
	private Usuario usuario;
	
	
	public Avisosusuario() {
	
	}


	public Integer getIdavisosusuario() {
		return idavisosusuario;
	}


	public void setIdavisosusuario(Integer idavisosusuario) {
		this.idavisosusuario = idavisosusuario;
	}


	public boolean isVisto() {
		return visto;
	}


	public void setVisto(boolean visto) {
		this.visto = visto;
	}


	public Avisos getAvisos() {
		return avisos;
	}


	public void setAvisos(Avisos avisos) {
		this.avisos = avisos;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idavisosusuario != null ? idavisosusuario.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Avisosusuario)) {
			return false;
		}
		Avisosusuario other = (Avisosusuario) object;
		if ((this.idavisosusuario == null && other.idavisosusuario != null)
				|| (this.idavisosusuario != null && !this.idavisosusuario.equals(other.idavisosusuario))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Avisosusuario[ idavisosusuario=" + idavisosusuario + " ]";
	}
	
	
	
	
	

}
