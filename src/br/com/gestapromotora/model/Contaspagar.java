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
@Table(name = "contaspagar")
public class Contaspagar implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idcontaspagar")
	private Integer idcontaspagar;
	@Column(name = "descricao")
	private String descricao;
	@Temporal(TemporalType.DATE)
	@Column(name = "datavencimento")
	private Date datavencimento;
	@Temporal(TemporalType.DATE)
	@Column(name = "datapagamento")
	private Date datapagamento;
	@JoinColumn(name = "tipodespesa_idtipodespesa", referencedColumnName = "idtipodespesa")
	@ManyToOne(optional = false)
	private Tipodespesa tipodespesa;
	@Column(name = "valor")
	private float valor;
	
	
	public Contaspagar() {
	
	}


	public Integer getIdcontaspagar() {
		return idcontaspagar;
	}


	public void setIdcontaspagar(Integer idcontaspagar) {
		this.idcontaspagar = idcontaspagar;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Date getDatavencimento() {
		return datavencimento;
	}


	public void setDatavencimento(Date datavencimento) {
		this.datavencimento = datavencimento;
	}


	public Date getDatapagamento() {
		return datapagamento;
	}


	public void setDatapagamento(Date datapagamento) {
		this.datapagamento = datapagamento;
	}


	public Tipodespesa getTipodespesa() {
		return tipodespesa;
	}


	public void setTipodespesa(Tipodespesa tipodespesa) {
		this.tipodespesa = tipodespesa;
	}
	
	
	
	public float getValor() {
		return valor;
	}


	public void setValor(float valor) {
		this.valor = valor;
	}


	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idcontaspagar != null ? idcontaspagar.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Contaspagar)) {
			return false;
		}
		Contaspagar other = (Contaspagar) object;
		if ((this.idcontaspagar == null && other.idcontaspagar != null)
				|| (this.idcontaspagar != null && !this.idcontaspagar.equals(other.idcontaspagar))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.gestapromotora.model.Contaspagar[ idcontaspagar=" + idcontaspagar + " ]";
	}
	
	
	

}
