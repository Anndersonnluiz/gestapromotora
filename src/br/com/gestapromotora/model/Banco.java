package br.com.gestapromotora.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "banco")
public class Banco implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idbanco")
	private Integer idbanco;
	@Column(name = "codigo")
	private int codigo;
	@Column(name = "nome")
	private String nome;
	
	
	public Banco() {
	
	}


	public Integer getIdbanco() {
		return idbanco;
	}


	public void setIdbanco(Integer idbanco) {
		this.idbanco = idbanco;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idbanco != null ? idbanco.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Banco)) {
			return false;
		}
		Banco other = (Banco) object;
		if ((this.idbanco == null && other.idbanco != null)
				|| (this.idbanco != null && !this.idbanco.equals(other.idbanco))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.gestapromotora.model.Banco[ idbanco=" + idbanco + " ]";
	}
	
	
	

}
