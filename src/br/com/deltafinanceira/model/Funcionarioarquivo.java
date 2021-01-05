package br.com.deltafinanceira.model;

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
@Table(name = "funcionarioarquivo")
public class Funcionarioarquivo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfuncionarioarquivo")
    private Integer idfuncionarioarquivo;
    @Column(name = "nomearquivo")
    private String nomearquivo;
	@Column(name = "dataupload")
	@Temporal(TemporalType.DATE)
	private Date dataupload;
    @JoinColumn(name = "funcionario_idfuncionario", referencedColumnName = "idfuncionario")
    @ManyToOne(optional = false)
    private Funcionario funcionario;
    @Transient
    private boolean novoupload;
    
    
    public Funcionarioarquivo() {
	
	}


	public Integer getIdfuncionarioarquivo() {
		return idfuncionarioarquivo;
	}


	public void setIdfuncionarioarquivo(Integer idfuncionarioarquivo) {
		this.idfuncionarioarquivo = idfuncionarioarquivo;
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


	public Funcionario getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	public boolean isNovoupload() {
		return novoupload;
	}


	public void setNovoupload(boolean novoupload) {
		this.novoupload = novoupload;
	}
    
    
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idfuncionarioarquivo != null ? idfuncionarioarquivo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Funcionarioarquivo)) {
			return false;
		}
		Funcionarioarquivo other = (Funcionarioarquivo) object;
		if ((this.idfuncionarioarquivo == null && other.idfuncionarioarquivo != null)
				|| (this.idfuncionarioarquivo != null && !this.idfuncionarioarquivo.equals(other.idfuncionarioarquivo))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Funcionarioarquivo[ idfuncionarioarquivo=" + idfuncionarioarquivo + " ]";
	}
    
    
    
    

}
