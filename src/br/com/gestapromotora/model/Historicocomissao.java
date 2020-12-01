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
import javax.persistence.Transient;

@Entity
@Table(name = "historicocomissao")
public class Historicocomissao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idhistoricocomissao")
	private Integer idhistoricocomissao;
	@Column(name = "prodliq")
	private float prodliq;
	@Column(name = "cmsbruta")
	private float cmdbruta;
	@Column(name = "proddesc")
	private float proddesc;
	@Column(name = "cmsliq")
	private float cmsliq;
	@Column(name = "tipo")
	private String tipo;
	@Column(name = "datalancamento")
	@Temporal(TemporalType.DATE)
	private Date datalancamento;
	@JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
	@ManyToOne(optional = false)
	private Usuario usuario;
	@JoinColumn(name = "contrato_idcontrato", referencedColumnName = "idcontrato")
	@ManyToOne(optional = false)
	private Contrato contrato;
	@Column(name = "mes")
	private int mes;
	@Column(name = "ano")
	private int ano;
	@Column(name = "baixa")
	private boolean baixa;
	@Column(name = "descricaobaixa")
	private String descricaobaixa;
	@Column(name = "corbaixa")
	private String corbaixa;
	@Transient
	private boolean selecionado;
	
	
	
	public Historicocomissao() {
		descricaobaixa = "thumbs-down";
		corbaixa = "red";
		baixa = false;
	}



	public Integer getIdhistoricocomissao() {
		return idhistoricocomissao;
	}



	public void setIdhistoricocomissao(Integer idhistoricocomissao) {
		this.idhistoricocomissao = idhistoricocomissao;
	}



	public float getProdliq() {
		return prodliq;
	}



	public void setProdliq(float prodliq) {
		this.prodliq = prodliq;
	}



	public float getCmdbruta() {
		return cmdbruta;
	}



	public void setCmdbruta(float cmdbruta) {
		this.cmdbruta = cmdbruta;
	}



	public float getProddesc() {
		return proddesc;
	}



	public void setProddesc(float proddesc) {
		this.proddesc = proddesc;
	}



	public float getCmsliq() {
		return cmsliq;
	}



	public void setCmsliq(float cmsliq) {
		this.cmsliq = cmsliq;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	
	
	
	public int getMes() {
		return mes;
	}



	public void setMes(int mes) {
		this.mes = mes;
	}



	public int getAno() {
		return ano;
	}



	public void setAno(int ano) {
		this.ano = ano;
	}



	public boolean isSelecionado() {
		return selecionado;
	}



	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}



	public boolean isBaixa() {
		return baixa;
	}



	public void setBaixa(boolean baixa) {
		this.baixa = baixa;
	}



	public String getDescricaobaixa() {
		return descricaobaixa;
	}



	public void setDescricaobaixa(String descricaobaixa) {
		this.descricaobaixa = descricaobaixa;
	}



	public String getCorbaixa() {
		return corbaixa;
	}



	public void setCorbaixa(String corbaixa) {
		this.corbaixa = corbaixa;
	}



	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idhistoricocomissao != null ? idhistoricocomissao.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Historicocomissao)) {
			return false;
		}
		Historicocomissao other = (Historicocomissao) object;
		if ((this.idhistoricocomissao == null && other.idhistoricocomissao != null)
				|| (this.idhistoricocomissao != null && !this.idhistoricocomissao.equals(other.idhistoricocomissao))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.gestapromotora.model.Historicocomissao[ idhistoricocomissao=" + idhistoricocomissao + " ]";
	}

}
