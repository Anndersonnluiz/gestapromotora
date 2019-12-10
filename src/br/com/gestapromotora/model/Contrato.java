package br.com.gestapromotora.model;

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
@Table(name = "contrato")
public class Contrato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idcontrato")
	private Integer idcontrato;
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
	@Column(name = "valorparcela")
	private float valorparcela;
	@Column(name = "valorquitar")
	private float valorquitar;
	@Column(name = "saldoinadimplencia")
	private float saldoinadimplencia;
	@Column(name = "tarifa")
	private float tarifa;
	@Column(name = "ajustecomissaocheck")
	private boolean ajustecomissaocheck;
	@Column(name = "observacao")
	private String observacao;
	@Column(name = "parcela")
	private float parcela;
	@Column(name = "valoroperacao")
	private float valoroperacao;
	@Column(name = "valorcliente")
	private float valorcliente;
	@Column(name = "parceladivergente")
	private float parceladivergente;
	@Column(name = "valoroperacaodivergente")
	private float valoroperacaodivergente;
	@Column(name = "valorclientedivergente")
	private float valorclientedivergente;
	@JoinColumn(name = "coeficiente_idcoeficiente", referencedColumnName = "idcoeficiente")
	@ManyToOne(optional = false)
	private Coeficiente coeficiente;
	@JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
	@ManyToOne(optional = false)
	private Cliente cliente;
	
	
	public Contrato() {
	
	}


	public Integer getIdcontrato() {
		return idcontrato;
	}


	public void setIdcontrato(Integer idcontrato) {
		this.idcontrato = idcontrato;
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


	public float getValorparcela() {
		return valorparcela;
	}


	public void setValorparcela(float valorparcela) {
		this.valorparcela = valorparcela;
	}


	public float getValorquitar() {
		return valorquitar;
	}


	public void setValorquitar(float valorquitar) {
		this.valorquitar = valorquitar;
	}


	public float getSaldoinadimplencia() {
		return saldoinadimplencia;
	}


	public void setSaldoinadimplencia(float saldoinadimplencia) {
		this.saldoinadimplencia = saldoinadimplencia;
	}


	public float getTarifa() {
		return tarifa;
	}


	public void setTarifa(float tarifa) {
		this.tarifa = tarifa;
	}


	public boolean isAjustecomissaocheck() {
		return ajustecomissaocheck;
	}


	public void setAjustecomissaocheck(boolean ajustecomissaocheck) {
		this.ajustecomissaocheck = ajustecomissaocheck;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public float getParcela() {
		return parcela;
	}


	public void setParcela(float parcela) {
		this.parcela = parcela;
	}


	public float getValoroperacao() {
		return valoroperacao;
	}


	public void setValoroperacao(float valoroperacao) {
		this.valoroperacao = valoroperacao;
	}


	public float getValorcliente() {
		return valorcliente;
	}


	public void setValorcliente(float valorcliente) {
		this.valorcliente = valorcliente;
	}


	public float getParceladivergente() {
		return parceladivergente;
	}


	public void setParceladivergente(float parceladivergente) {
		this.parceladivergente = parceladivergente;
	}


	public float getValoroperacaodivergente() {
		return valoroperacaodivergente;
	}


	public void setValoroperacaodivergente(float valoroperacaodivergente) {
		this.valoroperacaodivergente = valoroperacaodivergente;
	}


	public float getValorclientedivergente() {
		return valorclientedivergente;
	}


	public void setValorclientedivergente(float valorclientedivergente) {
		this.valorclientedivergente = valorclientedivergente;
	}


	public Coeficiente getCoeficiente() {
		return coeficiente;
	}


	public void setCoeficiente(Coeficiente coeficiente) {
		this.coeficiente = coeficiente;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idcontrato != null ? idcontrato.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Banco)) {
			return false;
		}
		Contrato other = (Contrato) object;
		if ((this.idcontrato == null && other.idcontrato != null)
				|| (this.idcontrato != null && !this.idcontrato.equals(other.idcontrato))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.gestapromotora.model.Contrato[ v=" + idcontrato + " ]";
	}
	
	
	
	
	
	
	
	

}
