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
@Table(name = "lead")
public class Lead implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idlead")
	private Integer idlead;
	@Column(name = "situacao")
	private int situacao;
	@Column(name = "ultimocontato")
	@Temporal(TemporalType.DATE)
	private Date ultimocontato;
	@Column(name = "proximocontato")
	@Temporal(TemporalType.DATE)
	private Date proximocontato;
	@Column(name = "tipooperacao")
	private String tipooperacao;
	@Column(name = "idoperacao")
	private int idoperacao;
	@Column(name = "corsituacao")
	private String corsituacao;
	@JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
	@ManyToOne(optional = false)
	private Cliente cliente;

	public Lead() {

	}

	public Integer getIdlead() {
		return idlead;
	}

	public void setIdlead(Integer idlead) {
		this.idlead = idlead;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	public Date getUltimocontato() {
		return ultimocontato;
	}

	public void setUltimocontato(Date ultimocontato) {
		this.ultimocontato = ultimocontato;
	}

	public Date getProximocontato() {
		return proximocontato;
	}

	public void setProximocontato(Date proximocontato) {
		this.proximocontato = proximocontato;
	}

	public String getTipooperacao() {
		return tipooperacao;
	}

	public void setTipooperacao(String tipooperacao) {
		this.tipooperacao = tipooperacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getIdoperacao() {
		return idoperacao;
	}

	public void setIdoperacao(int idoperacao) {
		this.idoperacao = idoperacao;
	}

	public String getCorsituacao() {
		return corsituacao;
	}

	public void setCorsituacao(String corsituacao) {
		this.corsituacao = corsituacao;
	}
	
	

	public int hashCode() {
		int hash = 0;
		hash += (this.idlead != null) ? this.idlead.hashCode() : 0;
		return hash;
	}

	public boolean equals(Object object) {
		if (!(object instanceof Lead))
			return false;
		Lead other = (Lead) object;
		if ((this.idlead == null && other.idlead != null)
				|| (this.idlead != null && !this.idlead.equals(other.idlead)))
			return false;
		return true;
	}

	public String toString() {
		return "br.com.deltafinanceira.model.Lead[ idlead=" + this.idlead + " ]";
	}

}
