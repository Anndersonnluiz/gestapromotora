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
@Table(name = "malotecontrato")
public class Malotecontrato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmalotecontrato")
    private Integer idmalotecontrato;
    @Column(name = "malote")
    private String malote;
	@Column(name = "dataenvio")
	@Temporal(TemporalType.DATE)
	private Date dataenvio;
    @JoinColumn(name = "contrato_idcontrato", referencedColumnName = "idcontrato")
    @ManyToOne(optional = false)
    private Contrato contrato;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    
    
    
    public Malotecontrato() {
	
	}



	public Integer getIdmalotecontrato() {
		return idmalotecontrato;
	}



	public void setIdmalotecontrato(Integer idmalotecontrato) {
		this.idmalotecontrato = idmalotecontrato;
	}



	public String getMalote() {
		return malote;
	}



	public void setMalote(String malote) {
		this.malote = malote;
	}



	public Date getDataenvio() {
		return dataenvio;
	}



	public void setDataenvio(Date dataenvio) {
		this.dataenvio = dataenvio;
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
		hash += (idmalotecontrato != null ? idmalotecontrato.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Malotecontrato)) {
			return false;
		}
		Malotecontrato other = (Malotecontrato) object;
		if ((this.idmalotecontrato == null && other.idmalotecontrato != null)
				|| (this.idmalotecontrato != null && !this.idmalotecontrato.equals(other.idmalotecontrato))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.gestapromotora.model.Malotecontrato[ idmalotecontrato=" + idmalotecontrato + " ]";
	}

}
