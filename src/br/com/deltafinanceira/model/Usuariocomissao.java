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
@Table(name = "usuariocomissao")
public class Usuariocomissao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idusuariocomissao")
	private Integer idusuariocomissao;
	@Column(name = "comissaocorretor")
	private float comissaocorretor;
	@JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
	private Usuario usuario;
	@JoinColumn(name = "tipooperacao_idtipooperacao", referencedColumnName = "idtipooperacao")
    @ManyToOne(optional = false)
	private Tipooperacao tipooperacao;
	
	
	
	public Usuariocomissao() {
	
	}



	public Integer getIdusuariocomissao() {
		return idusuariocomissao;
	}



	public void setIdusuariocomissao(Integer idusuariocomissao) {
		this.idusuariocomissao = idusuariocomissao;
	}



	public float getComissaocorretor() {
		return comissaocorretor;
	}



	public void setComissaocorretor(float comissaocorretor) {
		this.comissaocorretor = comissaocorretor;
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
	
	
	
	
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idusuariocomissao != null ? idusuariocomissao.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Usuariocomissao)) {
			return false;
		}
		Usuariocomissao other = (Usuariocomissao) object;
		if ((this.idusuariocomissao == null && other.idusuariocomissao != null)
				|| (this.idusuariocomissao != null && !this.idusuariocomissao.equals(other.idusuariocomissao))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Usuariocomissao[ idusuariocomissao=" + idusuariocomissao + " ]";
	}
	
	
	

}
