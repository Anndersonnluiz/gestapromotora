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
@Table(name = "tipocolaborador")
public class Tipocolaborador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idtipocolaborador")
	private Integer idtipocolaborador;
	@Column(name = "descricao")
	private String descricao;
    @JoinColumn(name = "acessocolaborador_idacessocolaborador", referencedColumnName = "idacessocolaborador")
    @ManyToOne(optional = false)
    private Acessocolaborador acessocolaborador;

	public Tipocolaborador() {

	}

	public Integer getIdtipocolaborador() {
		return idtipocolaborador;
	}

	public void setIdtipocolaborador(Integer idtipocolaborador) {
		this.idtipocolaborador = idtipocolaborador;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	

	public Acessocolaborador getAcessocolaborador() {
		return acessocolaborador;
	}

	public void setAcessocolaborador(Acessocolaborador acessocolaborador) {
		this.acessocolaborador = acessocolaborador;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idtipocolaborador != null ? idtipocolaborador.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Tipocolaborador)) {
			return false;
		}
		Tipocolaborador other = (Tipocolaborador) object;
		if ((this.idtipocolaborador == null && other.idtipocolaborador != null)
				|| (this.idtipocolaborador != null && !this.idtipocolaborador.equals(other.idtipocolaborador))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.gestapromotora.model.Tipocolaborador[ idtipocolaborador=" + idtipocolaborador + " ]";
	}

}
