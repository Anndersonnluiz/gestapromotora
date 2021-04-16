package br.com.deltafinanceira.model;

import java.io.Serializable;
import java.math.BigInteger;

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
@Table(name = "formalizacao")
public class Formalizacao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idformalizacao")
	private Integer idformalizacao;
	@Column(name = "nomecliente")
	private String nomecliente;
	@Column(name = "cpf")
	private String cpf;
	@Column(name = "tipobeneficio")
	private String tipobeneficio;
	@Column(name = "numerobeneficio")
	private BigInteger numerobeneficio;
	@Column(name = "valoroferta")
	private float valoroferta;
	@Column(name = "telefone1")
	private String telefone1;
	@Column(name = "telefone2")
	private String telefone2;
	@Column(name = "telefone3")
	private String telefone3;
	@Column(name = "observacoes")
	private String observacoes;
	@Column(name = "emitidocontrato")
	private boolean emitidocontrato;
	@Column(name = "ativo")
	private boolean ativo;
	@JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
	@ManyToOne(optional = false)
	private Usuario usuario;
	@JoinColumn(name = "tipooperacao_idtipooperacao", referencedColumnName = "idtipooperacao")
	@ManyToOne(optional = false)
	private Tipooperacao tipooperacao;
	
	
	
	public Formalizacao() {
	
	}



	public Integer getIdformalizacao() {
		return idformalizacao;
	}



	public void setIdformalizacao(Integer idformalizacao) {
		this.idformalizacao = idformalizacao;
	}



	public String getNomecliente() {
		return nomecliente;
	}



	public void setNomecliente(String nomecliente) {
		this.nomecliente = nomecliente;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getTipobeneficio() {
		return tipobeneficio;
	}



	public void setTipobeneficio(String tipobeneficio) {
		this.tipobeneficio = tipobeneficio;
	}



	public BigInteger getNumerobeneficio() {
		return numerobeneficio;
	}



	public void setNumerobeneficio(BigInteger numerobeneficio) {
		this.numerobeneficio = numerobeneficio;
	}



	public float getValoroferta() {
		return valoroferta;
	}



	public void setValoroferta(float valoroferta) {
		this.valoroferta = valoroferta;
	}



	public String getTelefone1() {
		return telefone1;
	}



	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}



	public String getTelefone2() {
		return telefone2;
	}



	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}



	public String getTelefone3() {
		return telefone3;
	}



	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}



	public String getObservacoes() {
		return observacoes;
	}



	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public Tipooperacao getTipooperacao() {
		return tipooperacao;
	}



	public void setTipooperacao(Tipooperacao tipooperacao) {
		this.tipooperacao = tipooperacao;
	}
	
	
	
	public boolean isEmitidocontrato() {
		return emitidocontrato;
	}



	public void setEmitidocontrato(boolean emitidocontrato) {
		this.emitidocontrato = emitidocontrato;
	}



	public boolean isAtivo() {
		return ativo;
	}



	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}



	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idformalizacao != null ? idformalizacao.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Formalizacao)) {
			return false;
		}
		Formalizacao other = (Formalizacao) object;
		if ((this.idformalizacao == null && other.idformalizacao != null)
				|| (this.idformalizacao != null && !this.idformalizacao.equals(other.idformalizacao))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Formalizacao[ idformalizacao=" + idformalizacao + " ]";
	}
	
	
	
	
	

}
