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
import javax.persistence.Transient;

@Entity
@Table(name = "comissaovenda")
public class Comissaovenda implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idcomissaovenda")
	private Integer idcomissaovenda;
	@Column(name = "comissaovenda")
	private float comissaovenda;
	@Column(name = "prodliq")
	private float prodliq;
	@Column(name = "comissaocorretor")
	private float comissaocorretor;
	@Column(name = "descricao")
	private String descricao;
	@Temporal(TemporalType.DATE)
	@Column(name = "datalancamento")
	private Date datalancamento;
	@JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
	@ManyToOne(optional = false)
	private Usuario usuario;
	@JoinColumn(name = "contrato_idcontrato", referencedColumnName = "idcontrato")
	@ManyToOne(optional = false)
	private Contrato contrato;
	@Transient
	private boolean selecionado;
	
	
	
	public Comissaovenda() {
	
	}



	public Integer getIdcomissaovenda() {
		return idcomissaovenda;
	}



	public void setIdcomissaovenda(Integer idcomissaovenda) {
		this.idcomissaovenda = idcomissaovenda;
	}



	public float getComissaovenda() {
		return comissaovenda;
	}



	public void setComissaovenda(float comissaovenda) {
		this.comissaovenda = comissaovenda;
	}



	public float getProdliq() {
		return prodliq;
	}



	public void setProdliq(float prodliq) {
		this.prodliq = prodliq;
	}



	public float getComissaocorretor() {
		return comissaocorretor;
	}



	public void setComissaocorretor(float comissaocorretor) {
		this.comissaocorretor = comissaocorretor;
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



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public Contrato getContrato() {
		return contrato;
	}



	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
	
	
	
	
	public boolean isSelecionado() {
		return selecionado;
	}



	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}



	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idcomissaovenda != null ? idcomissaovenda.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Comissaovenda)) {
			return false;
		}
		Comissaovenda other = (Comissaovenda) object;
		if ((this.idcomissaovenda == null && other.idcomissaovenda != null)
				|| (this.idcomissaovenda != null && !this.idcomissaovenda.equals(other.idcomissaovenda))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Comissaovenda[ idcomissaovenda=" + idcomissaovenda + " ]";
	}
	
	
	
	

}
