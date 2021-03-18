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
import javax.persistence.Transient;

@Entity
@Table(name = "regrascoeficiente")
public class Regrascoeficiente implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idregrascoeficiente")
  private Integer idregrascoeficiente;
  
  @Column(name = "nomeregra")
  private String nomeregra;
  
  @Column(name = "valorminregra")
  private float valorminregra;
  
  @Column(name = "valormaxregra")
  private float valormaxregra;
  
  @Column(name = "flatrecebidaregra")
  private float flatrecebidaregra;
  
  @Column(name = "bonusrecebidovista")
  private float bonusrecebidovista;
  
  @Column(name = "adianrecebidovista")
  private float adianrecebidovista;
  
  @Column(name = "totalrecebidovista")
  private float totalrecebidovista;
  
  @Column(name = "flatrepassadavista")
  private float flatrepassadavista;
  
  @Column(name = "bonusrepassadavista")
  private float bonusrepassadavista;
  
  @Column(name = "adianrepassadavista")
  private float adianrepassadavista;
  
  @Column(name = "totalrepassadavista")
  private float totalrepassadavista;
  
  @Column(name = "recebidoprazo")
  private float recebidoprazo;
  
  @Column(name = "repassadaprazo")
  private float repassadaprazo;
  
  @Column(name = "nometabela")
  private String nometabela;
  
  @JoinColumn(name = "valorescoeficiente_idvalorescoeficiente", referencedColumnName = "idvalorescoeficiente")
  @ManyToOne(optional = false)
  private Valorescoeficiente valorescoeficiente;
  
  @Transient
  private int contador;
  
  public Integer getIdregrascoeficiente() {
    return this.idregrascoeficiente;
  }
  
  public void setIdregrascoeficiente(Integer idregrascoeficiente) {
    this.idregrascoeficiente = idregrascoeficiente;
  }
  
  public String getNomeregra() {
    return this.nomeregra;
  }
  
  public void setNomeregra(String nomeregra) {
    this.nomeregra = nomeregra;
  }
  
  public float getValorminregra() {
    return this.valorminregra;
  }
  
  public void setValorminregra(float valorminregra) {
    this.valorminregra = valorminregra;
  }
  
  public float getValormaxregra() {
    return this.valormaxregra;
  }
  
  public void setValormaxregra(float valormaxregra) {
    this.valormaxregra = valormaxregra;
  }
  
  public float getFlatrecebidaregra() {
    return this.flatrecebidaregra;
  }
  
  public void setFlatrecebidaregra(float flatrecebidaregra) {
    this.flatrecebidaregra = flatrecebidaregra;
  }
  
  public float getBonusrecebidovista() {
    return this.bonusrecebidovista;
  }
  
  public void setBonusrecebidovista(float bonusrecebidovista) {
    this.bonusrecebidovista = bonusrecebidovista;
  }
  
  public float getAdianrecebidovista() {
    return this.adianrecebidovista;
  }
  
  public void setAdianrecebidovista(float adianrecebidovista) {
    this.adianrecebidovista = adianrecebidovista;
  }
  
  public float getTotalrecebidovista() {
    return this.totalrecebidovista;
  }
  
  public void setTotalrecebidovista(float totalrecebidovista) {
    this.totalrecebidovista = totalrecebidovista;
  }
  
  public float getFlatrepassadavista() {
    return this.flatrepassadavista;
  }
  
  public void setFlatrepassadavista(float flatrepassadavista) {
    this.flatrepassadavista = flatrepassadavista;
  }
  
  public float getBonusrepassadavista() {
    return this.bonusrepassadavista;
  }
  
  public void setBonusrepassadavista(float bonusrepassadavista) {
    this.bonusrepassadavista = bonusrepassadavista;
  }
  
  public float getAdianrepassadavista() {
    return this.adianrepassadavista;
  }
  
  public void setAdianrepassadavista(float adianrepassadavista) {
    this.adianrepassadavista = adianrepassadavista;
  }
  
  public float getTotalrepassadavista() {
    return this.totalrepassadavista;
  }
  
  public void setTotalrepassadavista(float totalrepassadavista) {
    this.totalrepassadavista = totalrepassadavista;
  }
  
  public float getRecebidoprazo() {
    return this.recebidoprazo;
  }
  
  public void setRecebidoprazo(float recebidoprazo) {
    this.recebidoprazo = recebidoprazo;
  }
  
  public float getRepassadaprazo() {
    return this.repassadaprazo;
  }
  
  public void setRepassadaprazo(float repassadaprazo) {
    this.repassadaprazo = repassadaprazo;
  }
  
  public Valorescoeficiente getValorescoeficiente() {
    return this.valorescoeficiente;
  }
  
  public void setValorescoeficiente(Valorescoeficiente valorescoeficiente) {
    this.valorescoeficiente = valorescoeficiente;
  }
  
  public int getContador() {
    return this.contador;
  }
  
  public void setContador(int contador) {
    this.contador = contador;
  }
  
  public String getNometabela() {
    return this.nometabela;
  }
  
  public void setNometabela(String nometabela) {
    this.nometabela = nometabela;
  }
  
  public int hashCode() {
    int hash = 0;
    hash += (this.idregrascoeficiente != null) ? this.idregrascoeficiente.hashCode() : 0;
    return hash;
  }
  
  public boolean equals(Object object) {
    if (!(object instanceof Regrascoeficiente))
      return false; 
    Regrascoeficiente other = (Regrascoeficiente)object;
    if ((this.idregrascoeficiente == null && other.idregrascoeficiente != null) || (
      this.idregrascoeficiente != null && !this.idregrascoeficiente.equals(other.idregrascoeficiente)))
      return false; 
    return true;
  }
  
  public String toString() {
    return "br.com.deltafinanceira.model.Regrascoeficiente[ idregrascoeficiente=" + this.idregrascoeficiente + " ]";
  }
}
