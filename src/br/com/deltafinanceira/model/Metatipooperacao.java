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
@Table(name = "metatipooperacao")
public class Metatipooperacao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idmetatipooperacao")
	private Integer idmetatipooperacao;
	@Column(name = "valormeta")
	private float valormeta;
	@Column(name = "valoralcancado")
	private float valoralcancado;
	@JoinColumn(name = "tipooperacao_idtipooperacao", referencedColumnName = "idtipooperacao")
	@ManyToOne(optional = false)
	private Tipooperacao tipooperacao;
	@JoinColumn(name = "metafaturamentomensal_idmetafaturamentomensal", referencedColumnName = "idmetafaturamentomensal")
	@ManyToOne(optional = false)
	private Metafaturamentomensal metafaturamentomensal;
	@JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
	@ManyToOne(optional = false)
	private Usuario usuario;
	
	
	public Metatipooperacao() {
	
	}


	public Integer getIdmetatipooperacao() {
		return idmetatipooperacao;
	}


	public void setIdmetatipooperacao(Integer idmetatipooperacao) {
		this.idmetatipooperacao = idmetatipooperacao;
	}


	public float getValormeta() {
		return valormeta;
	}


	public void setValormeta(float valormeta) {
		this.valormeta = valormeta;
	}


	public float getValoralcancado() {
		return valoralcancado;
	}


	public void setValoralcancado(float valoralcancado) {
		this.valoralcancado = valoralcancado;
	}


	public Tipooperacao getTipooperacao() {
		return tipooperacao;
	}


	public void setTipooperacao(Tipooperacao tipooperacao) {
		this.tipooperacao = tipooperacao;
	}


	public Metafaturamentomensal getMetafaturamentomensal() {
		return metafaturamentomensal;
	}


	public void setMetafaturamentomensal(Metafaturamentomensal metafaturamentomensal) {
		this.metafaturamentomensal = metafaturamentomensal;
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
		hash += (idmetatipooperacao != null ? idmetatipooperacao.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Metatipooperacao)) {
			return false;
		}
		Metatipooperacao other = (Metatipooperacao) object;
		if ((this.idmetatipooperacao == null && other.idmetatipooperacao != null)
				|| (this.idmetatipooperacao != null && !this.idmetatipooperacao.equals(other.idmetatipooperacao))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Metatipooperacao[ idmetatipooperacao=" + idmetatipooperacao + " ]";
	}
	
	
	
	
	

}
