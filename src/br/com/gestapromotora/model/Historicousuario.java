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
@Table(name = "historicousuario")
public class Historicousuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhistoricousuario")
    private Integer idhistoricousuario;
	@Temporal(TemporalType.DATE)
	@Column(name = "datacadastro")
	private Date datacadastro;
	@Column(name = "hora")
	private String hora;
	@Column(name = "descricao")
	private String descricao;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
	
	
	
	public Historicousuario() {
	
	}



	public Integer getIdhistoricousuario() {
		return idhistoricousuario;
	}



	public void setIdhistoricousuario(Integer idhistoricousuario) {
		this.idhistoricousuario = idhistoricousuario;
	}



	public Date getDatacadastro() {
		return datacadastro;
	}



	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
	}



	public String getHora() {
		return hora;
	}



	public void setHora(String hora) {
		this.hora = hora;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		hash += (idhistoricousuario != null ? idhistoricousuario.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Historicousuario)) {
			return false;
		}
		Historicousuario other = (Historicousuario) object;
		if ((this.idhistoricousuario == null && other.idhistoricousuario != null)
				|| (this.idhistoricousuario != null && !this.idhistoricousuario.equals(other.idhistoricousuario))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.gestapromotora.model.Historicousuario[ idhistoricousuario=" + idhistoricousuario + " ]";
	}
	
	
	
	
	

}
