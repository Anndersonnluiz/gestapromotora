package br.com.gestapromotora.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "notificacao")
public class Notificacao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnotificacao")
	private Integer idnotificacao;
	@Column(name = "titulo")
	private String titulo;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "visto")
	private boolean visto;
	@Temporal(TemporalType.DATE)
	@Column(name = "datalancamento")
	private Date datalancamento;
	@JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
	@ManyToOne(optional = false)
	private Usuario usuario;
	@Column(name = "idcontrato")
	private int idcontrato;
	
	
	public Notificacao() {
	
	}


	public Integer getIdnotificacao() {
		return idnotificacao;
	}


	public void setIdnotificacao(Integer idnotificacao) {
		this.idnotificacao = idnotificacao;
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


	public boolean isVisto() {
		return visto;
	}


	public void setVisto(boolean visto) {
		this.visto = visto;
	}


	public Date getDatalancamento() {
		return datalancamento;
	}


	public void setDatalancamento(Date datalancamento) {
		this.datalancamento = datalancamento;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
	public int getIdcontrato() {
		return idcontrato;
	}


	public void setIdcontrato(int idcontrato) {
		this.idcontrato = idcontrato;
	}


	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idnotificacao != null ? idnotificacao.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Notificacao)) {
			return false;
		}
		Notificacao other = (Notificacao) object;
		if ((this.idnotificacao == null && other.idnotificacao != null)
				|| (this.idnotificacao != null && !this.idnotificacao.equals(other.idnotificacao))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.gestapromotora.model.Notificacao[ idnotificacao=" + idnotificacao + " ]";
	}
	
	
	
	

}
