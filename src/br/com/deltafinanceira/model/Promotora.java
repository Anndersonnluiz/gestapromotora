package br.com.deltafinanceira.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "promotora")
public class Promotora implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idpromotora")
  private Integer idpromotora;
  
  @Column(name = "descricao")
  private String descricao;
  
  public Integer getIdpromotora() {
    return this.idpromotora;
  }
  
  public void setIdpromotora(Integer idpromotora) {
    this.idpromotora = idpromotora;
  }
  
  public String getDescricao() {
    return this.descricao;
  }
  
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
  
  public boolean equals(Object object) {
    if (!(object instanceof Promotora))
      return false; 
    Promotora other = (Promotora)object;
    if ((this.idpromotora == null && other.idpromotora != null) || (
      this.idpromotora != null && !this.idpromotora.equals(other.idpromotora)))
      return false; 
    return true;
  }
  
  public String toString() {
    return "br.com.deltafinanceira.model.Promotora[ idpromotora=" + this.idpromotora + " ]";
  }
}
