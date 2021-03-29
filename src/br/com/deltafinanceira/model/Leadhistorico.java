package br.com.deltafinanceira.model;

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
@Table(name = "leadhistorico")
public class Leadhistorico implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idleadhistorico")
	private Integer idleadhistorico;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "datalancamento")
	@Temporal(TemporalType.DATE)
	private Date datalancamento;
	@JoinColumn(name = "lead_idlead", referencedColumnName = "idlead")
	@ManyToOne(optional = false)
	private Lead lead;
	
	
	public Leadhistorico() {
	
	}


	public Integer getIdleadhistorico() {
		return idleadhistorico;
	}


	public void setIdleadhistorico(Integer idleadhistorico) {
		this.idleadhistorico = idleadhistorico;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Date getDatalancamento() {
		return datalancamento;
	}


	public void setDatalancamento(Date datalancamento) {
		this.datalancamento = datalancamento;
	}


	public Lead getLead() {
		return lead;
	}


	public void setLead(Lead lead) {
		this.lead = lead;
	}
	
	
	
	
	
	public int hashCode() {
		int hash = 0;
		hash += (this.idleadhistorico != null) ? this.idleadhistorico.hashCode() : 0;
		return hash;
	}

	public boolean equals(Object object) {
		if (!(object instanceof Lead))
			return false;
		Leadhistorico other = (Leadhistorico) object;
		if ((this.idleadhistorico == null && other.idleadhistorico != null)
				|| (this.idleadhistorico != null && !this.idleadhistorico.equals(other.idleadhistorico)))
			return false;
		return true;
	}

	public String toString() {
		return "br.com.deltafinanceira.model.Leadhistorico[ idleadhistorico=" + this.idleadhistorico + " ]";
	}
	
	
	
	

}
