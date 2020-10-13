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
@Table(name = "contratoarquivo")
public class Contratoarquivo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontratoarquivo")
    private Integer idcontratoarquivo;
    @Column(name = "nomearquivo")
    private String nomearquivo;
	@Column(name = "dataupload")
	@Temporal(TemporalType.DATE)
	private Date dataupload;
    @JoinColumn(name = "contrato_idcontrato", referencedColumnName = "idcontrato")
    @ManyToOne(optional = false)
    private Contrato contrato;
    @JoinColumn(name = "tipoarquivo_idtipoarquivo", referencedColumnName = "idtipoarquivo")
    @ManyToOne(optional = false)
    private Tipoarquivo tipoarquivo;
    @Transient
    private boolean novoupload;
    
    
    
    public Contratoarquivo() {
    	novoupload = false;
	}



	public Integer getIdcontratoarquivo() {
		return idcontratoarquivo;
	}



	public void setIdcontratoarquivo(Integer idcontratoarquivo) {
		this.idcontratoarquivo = idcontratoarquivo;
	}



	public String getNomearquivo() {
		return nomearquivo;
	}



	public void setNomearquivo(String nomearquivo) {
		this.nomearquivo = nomearquivo;
	}



	public Date getDataupload() {
		return dataupload;
	}



	public void setDataupload(Date dataupload) {
		this.dataupload = dataupload;
	}



	public Contrato getContrato() {
		return contrato;
	}



	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}



	public Tipoarquivo getTipoarquivo() {
		return tipoarquivo;
	}



	public void setTipoarquivo(Tipoarquivo tipoarquivo) {
		this.tipoarquivo = tipoarquivo;
	}
    
    
    
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idcontratoarquivo != null ? idcontratoarquivo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Contratoarquivo)) {
			return false;
		}
		Contratoarquivo other = (Contratoarquivo) object;
		if ((this.idcontratoarquivo == null && other.idcontratoarquivo != null)
				|| (this.idcontratoarquivo != null && !this.idcontratoarquivo.equals(other.idcontratoarquivo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.gestapromotora.model.Contratoarquivo[ idcontratoarquivo=" + idcontratoarquivo + " ]";
	}
    
    

}
