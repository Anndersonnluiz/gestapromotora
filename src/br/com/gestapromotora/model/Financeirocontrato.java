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
@Table(name = "financeirocontrato")
public class Financeirocontrato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idfinanceirocontrato")
	private Integer idfinanceirocontrato;
	@Column(name = "ade")
	private String ade;
	@Column(name = "dataade")
	@Temporal(TemporalType.DATE)
	private Date dataade;
	@Column(name = "numerointerno")
	private String numerointerno;
	@Column(name = "datapagamento")
	@Temporal(TemporalType.DATE)
	private Date datapagamento;
	@Column(name = "statuscontrato")
	private String statuscontrato;
	@Column(name = "observacao")
	private String observacao;
    @JoinColumn(name = "contrato_idcontrato", referencedColumnName = "idcontrato")
    @ManyToOne(optional = false)
    private Contrato contrato;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
	
	
	
	
	public Financeirocontrato() {
	
	}




	public Integer getIdfinanceirocontrato() {
		return idfinanceirocontrato;
	}




	public void setIdfinanceirocontrato(Integer idfinanceirocontrato) {
		this.idfinanceirocontrato = idfinanceirocontrato;
	}




	public String getAde() {
		return ade;
	}




	public void setAde(String ade) {
		this.ade = ade;
	}




	public Date getDataade() {
		return dataade;
	}




	public void setDataade(Date dataade) {
		this.dataade = dataade;
	}




	public String getNumerointerno() {
		return numerointerno;
	}




	public void setNumerointerno(String numerointerno) {
		this.numerointerno = numerointerno;
	}




	public Date getDatapagamento() {
		return datapagamento;
	}




	public void setDatapagamento(Date datapagamento) {
		this.datapagamento = datapagamento;
	}




	public String getStatuscontrato() {
		return statuscontrato;
	}




	public void setStatuscontrato(String statuscontrato) {
		this.statuscontrato = statuscontrato;
	}




	public String getObservacao() {
		return observacao;
	}




	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
	
	public Contrato getContrato() {
		return contrato;
	}




	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
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
		hash += (idfinanceirocontrato != null ? idfinanceirocontrato.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Financeirocontrato)) {
			return false;
		}
		Financeirocontrato other = (Financeirocontrato) object;
		if ((this.idfinanceirocontrato == null && other.idfinanceirocontrato != null)
				|| (this.idfinanceirocontrato != null && !this.idfinanceirocontrato.equals(other.idfinanceirocontrato))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.gestapromotora.model.Financeirocontrato[ idfinanceirocontrato=" + idfinanceirocontrato + " ]";
	}
	
	
	
	
	

}
