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
@Table(name = "avisos")
public class Avisos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idavisos")
	private Integer idavisos;
	@Column(name = "titulo")
	private String titulo;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "nomeusuario")
	private String nomeusuario;
	@Column(name = "prioridade")
	private String prioridade;
	@Column(name = "corprioridade")
	private String corprioridade;
	@Temporal(TemporalType.DATE)
	@Column(name = "datainicio")
	private Date datainicio;
	@Temporal(TemporalType.DATE)
	@Column(name = "datafinal")
	private Date datafinal;
	
	
	public Avisos() {
	
	}


	public Integer getIdavisos() {
		return idavisos;
	}


	public void setIdavisos(Integer idavisos) {
		this.idavisos = idavisos;
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


	public String getNomeusuario() {
		return nomeusuario;
	}


	public void setNomeusuario(String nomeusuario) {
		this.nomeusuario = nomeusuario;
	}


	public String getPrioridade() {
		return prioridade;
	}


	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}


	public String getCorprioridade() {
		return corprioridade;
	}


	public void setCorprioridade(String corprioridade) {
		this.corprioridade = corprioridade;
	}


	public Date getDatainicio() {
		return datainicio;
	}


	public void setDatainicio(Date datainicio) {
		this.datainicio = datainicio;
	}


	public Date getDatafinal() {
		return datafinal;
	}


	public void setDatafinal(Date datafinal) {
		this.datafinal = datafinal;
	}
	
	
	
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idavisos != null ? idavisos.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Avisos)) {
			return false;
		}
		Avisos other = (Avisos) object;
		if ((this.idavisos == null && other.idavisos != null)
				|| (this.idavisos != null && !this.idavisos.equals(other.idavisos))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Avisos[ idavisos=" + idavisos + " ]";
	}
	
	
	

}
