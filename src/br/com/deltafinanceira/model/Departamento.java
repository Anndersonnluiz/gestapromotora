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
@Table(name = "departamento")
public class Departamento implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "iddepartamento")
  private Integer iddepartamento;
  
  @Column(name = "descricao")
  private String descricao;
  
  @Column(name = "nusuario")
  private int nusuario;
  
  @JoinColumn(name = "filial_idfilial", referencedColumnName = "idfilial")
  @ManyToOne(optional = false)
  private Filial filial;
  
  public Integer getIddepartamento() {
    return this.iddepartamento;
  }
  
  public void setIddepartamento(Integer iddepartamento) {
    this.iddepartamento = iddepartamento;
  }
  
  public String getDescricao() {
    return this.descricao;
  }
  
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  
  public synchronized int getNusuario() {
    return this.nusuario;
  }
  
  public synchronized void setNusuario(int nusuario) {
    this.nusuario = nusuario;
  }
  
  public Filial getFilial() {
    return this.filial;
  }
  
  public void setFilial(Filial filial) {
    this.filial = filial;
  }
  
  public int hashCode() {
    int hash = 0;
    hash += (this.iddepartamento != null) ? this.iddepartamento.hashCode() : 0;
    return hash;
  }
  
  public boolean equals(Object object) {
    if (!(object instanceof Departamento))
      return false; 
    Departamento other = (Departamento)object;
    if ((this.iddepartamento == null && other.iddepartamento != null) || (
      this.iddepartamento != null && !this.iddepartamento.equals(other.iddepartamento)))
      return false; 
    return true;
  }
  
  public String toString() {
    return "br.com.deltafinanceira.model.Departamento[ iddepartamento=" + this.iddepartamento + " ]";
  }
}
