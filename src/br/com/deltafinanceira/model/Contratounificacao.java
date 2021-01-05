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
@Table(name = "contratounificacao")
public class Contratounificacao implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontratounificacao")
    private Integer idcontratounificacao;
	@Column(name = "parcelaspagas")
	private int parcelaspagas;
	@Column(name = "totalparcelas")
	private int totalparcelas;
	@Column(name = "percentualpago")
	private float percentualpago;
	@Column(name = "parcelasrestantes")
	private int parcelasrestantes;
	@Column(name = "contratoportado")
	private String contratoportado;
	@Column(name = "nparcela")
	private int nparcela;
	@Column(name = "valorquitar")
	private float valorquitar;
	@Column(name = "saldoinadimplencia")
	private float saldoinadimplencia;
	@Column(name = "parcela")
	private float parcela;
    @JoinColumn(name = "banco_idbanco", referencedColumnName = "idbanco")
    @ManyToOne(optional = false)
    private Banco banco;
    @JoinColumn(name = "contrato_idcontrato", referencedColumnName = "idcontrato")
    @ManyToOne(optional = false)
    private Contrato contrato;
    
    
    public Contratounificacao() {
	
	}


	public Integer getIdcontratounificacao() {
		return idcontratounificacao;
	}


	public void setIdcontratounificacao(Integer idcontratounificacao) {
		this.idcontratounificacao = idcontratounificacao;
	}


	public int getParcelaspagas() {
		return parcelaspagas;
	}


	public void setParcelaspagas(int parcelaspagas) {
		this.parcelaspagas = parcelaspagas;
	}


	public int getTotalparcelas() {
		return totalparcelas;
	}


	public void setTotalparcelas(int totalparcelas) {
		this.totalparcelas = totalparcelas;
	}


	public float getPercentualpago() {
		return percentualpago;
	}


	public void setPercentualpago(float percentualpago) {
		this.percentualpago = percentualpago;
	}


	public int getParcelasrestantes() {
		return parcelasrestantes;
	}


	public void setParcelasrestantes(int parcelasrestantes) {
		this.parcelasrestantes = parcelasrestantes;
	}


	public String getContratoportado() {
		return contratoportado;
	}


	public void setContratoportado(String contratoportado) {
		this.contratoportado = contratoportado;
	}


	public int getNparcela() {
		return nparcela;
	}


	public void setNparcela(int nparcela) {
		this.nparcela = nparcela;
	}


	public float getValorquitar() {
		return valorquitar;
	}


	public void setValorquitar(float valorquitar) {
		this.valorquitar = valorquitar;
	}


	public Banco getBanco() {
		return banco;
	}


	public void setBanco(Banco banco) {
		this.banco = banco;
	}


	public Contrato getContrato() {
		return contrato;
	}


	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
    
    
	
	
	public float getSaldoinadimplencia() {
		return saldoinadimplencia;
	}


	public void setSaldoinadimplencia(float saldoinadimplencia) {
		this.saldoinadimplencia = saldoinadimplencia;
	}


	public float getParcela() {
		return parcela;
	}


	public void setParcela(float parcela) {
		this.parcela = parcela;
	}


	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idcontratounificacao != null ? idcontratounificacao.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Contratounificacao)) {
			return false;
		}
		Contratounificacao other = (Contratounificacao) object;
		if ((this.idcontratounificacao == null && other.idcontratounificacao != null)
				|| (this.idcontratounificacao != null && !this.idcontratounificacao.equals(other.idcontratounificacao))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Contratounificacao[ idcontratounificacao=" + idcontratounificacao + " ]";
	}
    
	
	
	
	

}
