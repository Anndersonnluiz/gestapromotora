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
@Table(name = "historicopendencia")
public class Historicopendencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhistoricopendencia")
    private Integer idhistoricopendencia;
    @Column(name = "situacao")
    private String situacao;
    @Column(name = "razaopendencia")
    private String razaopendencia;
    @JoinColumn(name = "contrato_idcontrato", referencedColumnName = "idcontrato")
    @ManyToOne(optional = false)
    private Contrato contrato;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    
    
    public Historicopendencia() {
	
	}


	public Integer getIdhistoricopendencia() {
		return idhistoricopendencia;
	}


	public void setIdhistoricopendencia(Integer idhistoricopendencia) {
		this.idhistoricopendencia = idhistoricopendencia;
	}


	public String getSituacao() {
		return situacao;
	}


	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}


	public String getRazaopendencia() {
		return razaopendencia;
	}


	public void setRazaopendencia(String razaopendencia) {
		this.razaopendencia = razaopendencia;
	}


	public Contrato getContrato() {
		return contrato;
	}


	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
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
		hash += (idhistoricopendencia != null ? idhistoricopendencia.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Historicopendencia)) {
			return false;
		}
		Historicopendencia other = (Historicopendencia) object;
		if ((this.idhistoricopendencia == null && other.idhistoricopendencia != null)
				|| (this.idhistoricopendencia != null && !this.idhistoricopendencia.equals(other.idhistoricopendencia))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.gestapromotora.model.Historicopendencia[ idhistoricopendencia=" + idhistoricopendencia + " ]";
	}
    
    
    

}
