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
@Table(name = "suporte")
public class Suporte implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idsuporte")
	private Integer idsuporte;
	@Column(name = "titulo")
	private String titulo;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "datacadastro")
	private String datacadastro;
	@Column(name = "hora")
	private String hora;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    
    
    public Suporte() {
	
	}


	public synchronized Integer getIdsuporte() {
		return idsuporte;
	}


	public synchronized void setIdsuporte(Integer idsuporte) {
		this.idsuporte = idsuporte;
	}


	public synchronized String getTitulo() {
		return titulo;
	}


	public synchronized void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public synchronized String getDescricao() {
		return descricao;
	}


	public synchronized void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public synchronized String getDatacadastro() {
		return datacadastro;
	}


	public synchronized void setDatacadastro(String datacadastro) {
		this.datacadastro = datacadastro;
	}


	public synchronized String getHora() {
		return hora;
	}


	public synchronized void setHora(String hora) {
		this.hora = hora;
	}


	public synchronized Usuario getUsuario() {
		return usuario;
	}


	public synchronized void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
    
	
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idsuporte != null ? idsuporte.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Suporte)) {
			return false;
		}
		Suporte other = (Suporte) object;
		if ((this.idsuporte == null && other.idsuporte != null)
				|| (this.idsuporte != null && !this.idsuporte.equals(other.idsuporte))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Suporte[ idsuporte=" + idsuporte + " ]";
	}
	
	
	
	
	

}
