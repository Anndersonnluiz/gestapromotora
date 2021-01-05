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
@Table(name = "dadosbancario")
public class Dadosbancario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "iddadosbancario")
	private Integer iddadosbancario;
	@Column(name = "cdbanco")
	private int cdbanco;
	@Column(name = "agencia")
	private int agencia;
	@Column(name = "digitoagencia")
	private int digitoagencia;
	@Column(name = "tipoconta")
	private String tipoconta;
	@Column(name = "nconta")
	private int nconta;
	@Column(name = "digitoconta")
	private int digitoconta;
	@Column(name = "titular")
	private String titular;
	@Column(name = "cartaomagnetico")
	private String cartaomagnetico;
	@JoinColumn(name = "banco_idbanco", referencedColumnName = "idbanco")
	@ManyToOne(optional = false)
	private Banco banco;
	
	
	
	public Dadosbancario() {
	
	}



	public Integer getIddadosbancario() {
		return iddadosbancario;
	}



	public void setIddadosbancario(Integer iddadosbancario) {
		this.iddadosbancario = iddadosbancario;
	}



	public int getCdbanco() {
		return cdbanco;
	}



	public void setCdbanco(int cdbanco) {
		this.cdbanco = cdbanco;
	}



	public int getAgencia() {
		return agencia;
	}



	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}



	public int getDigitoagencia() {
		return digitoagencia;
	}



	public void setDigitoagencia(int digitoagencia) {
		this.digitoagencia = digitoagencia;
	}



	public String getTipoconta() {
		return tipoconta;
	}



	public void setTipoconta(String tipoconta) {
		this.tipoconta = tipoconta;
	}



	public int getNconta() {
		return nconta;
	}



	public void setNconta(int nconta) {
		this.nconta = nconta;
	}



	public int getDigitoconta() {
		return digitoconta;
	}



	public void setDigitoconta(int digitoconta) {
		this.digitoconta = digitoconta;
	}



	public String getTitular() {
		return titular;
	}



	public void setTitular(String titular) {
		this.titular = titular;
	}
	
	
	public Banco getBanco() {
		return banco;
	}



	public void setBanco(Banco banco) {
		this.banco = banco;
	}



	public String getCartaomagnetico() {
		return cartaomagnetico;
	}



	public void setCartaomagnetico(String cartaomagnetico) {
		this.cartaomagnetico = cartaomagnetico;
	}



	@Override
	public int hashCode() {
		int hash = 0;
		hash += (iddadosbancario != null ? iddadosbancario.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Dadosbancario)) {
			return false;
		}
		Dadosbancario other = (Dadosbancario) object;
		if ((this.iddadosbancario == null && other.iddadosbancario != null)
				|| (this.iddadosbancario != null && !this.iddadosbancario.equals(other.iddadosbancario))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Dadosbancario[ iddadosbancario=" + iddadosbancario + " ]";
	}
	
	
	


}
