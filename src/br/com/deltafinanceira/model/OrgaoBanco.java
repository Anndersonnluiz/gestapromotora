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
@Table(name = "orgaobanco")
public class OrgaoBanco implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idorgaobanco")
	private Integer idorgaobanco;
	@Column(name = "nome")
	private String nome; @JoinColumn(name = "banco_idbanco", referencedColumnName = "idbanco")
    @ManyToOne(optional = false)
    private Banco banco;
	@Column(name = "link")
	private String link;
	@Column(name = "demaisopeinss")
	private boolean demaisopeinss;
	
	 
	
	public OrgaoBanco() {
	
	}



	public Integer getIdorgaobanco() {
		return idorgaobanco;
	}



	public void setIdorgaobanco(Integer idorgaobanco) {
		this.idorgaobanco = idorgaobanco;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public Banco getBanco() {
		return banco;
	}



	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	
	public String getLink() {
		return link;
	}



	public void setLink(String link) {
		this.link = link;
	}



	public boolean isDemaisopeinss() {
		return demaisopeinss;
	}



	public void setDemaisopeinss(boolean demaisopeinss) {
		this.demaisopeinss = demaisopeinss;
	}



	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idorgaobanco != null ? idorgaobanco.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof OrgaoBanco)) {
			return false;
		}
		OrgaoBanco other = (OrgaoBanco) object;
		if ((this.idorgaobanco == null && other.idorgaobanco != null)
				|| (this.idorgaobanco != null && !this.idorgaobanco.equals(other.idorgaobanco))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Orgaobanco[ idorgaobanco=" + idorgaobanco + " ]";
	}
	
	

}
