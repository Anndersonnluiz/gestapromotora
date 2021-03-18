package br.com.deltafinanceira.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class EnderecoBean {
  @SerializedName("cep")
  @Expose
  private String cep;
  
  @SerializedName("logradouro")
  @Expose
  private String logradouro;
  
  @SerializedName("complemento")
  @Expose
  private String complemento;
  
  @SerializedName("bairro")
  @Expose
  private String bairro;
  
  @SerializedName("localidade")
  @Expose
  private String localidade;
  
  @SerializedName("uf")
  @Expose
  private String uf;
  
  @SerializedName("unidade")
  @Expose
  private String unidade;
  
  @SerializedName("ibge")
  @Expose
  private String ibge;
  
  @SerializedName("gia")
  @Expose
  private String gia;
  
  public String getCep() {
    return this.cep;
  }
  
  public void setCep(String cep) {
    this.cep = cep;
  }
  
  public String getLogradouro() {
    return this.logradouro;
  }
  
  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }
  
  public String getComplemento() {
    return this.complemento;
  }
  
  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }
  
  public String getBairro() {
    return this.bairro;
  }
  
  public void setBairro(String bairro) {
    this.bairro = bairro;
  }
  
  public String getLocalidade() {
    return this.localidade;
  }
  
  public void setLocalidade(String localidade) {
    this.localidade = localidade;
  }
  
  public String getUf() {
    return this.uf;
  }
  
  public void setUf(String uf) {
    this.uf = uf;
  }
  
  public String getUnidade() {
    return this.unidade;
  }
  
  public void setUnidade(String unidade) {
    this.unidade = unidade;
  }
  
  public String getIbge() {
    return this.ibge;
  }
  
  public void setIbge(String ibge) {
    this.ibge = ibge;
  }
  
  public String getGia() {
    return this.gia;
  }
  
  public void setGia(String gia) {
    this.gia = gia;
  }
  
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
  
  public int hashCode() {
    return (new HashCodeBuilder()).append(this.cep).append(this.logradouro).append(this.complemento).append(this.bairro)
      .append(this.localidade).append(this.uf).append(this.unidade).append(this.ibge).append(this.gia).toHashCode();
  }
  
  public boolean equals(Object other) {
    if (other == this)
      return true; 
    if (!(other instanceof EnderecoBean))
      return false; 
    EnderecoBean rhs = (EnderecoBean)other;
    return (new EqualsBuilder()).append(this.cep, rhs.cep).append(this.logradouro, rhs.logradouro)
      .append(this.complemento, rhs.complemento).append(this.bairro, rhs.bairro).append(this.localidade, rhs.localidade)
      .append(this.uf, rhs.uf).append(this.unidade, rhs.unidade).append(this.ibge, rhs.ibge).append(this.gia, rhs.gia).isEquals();
  }
}
