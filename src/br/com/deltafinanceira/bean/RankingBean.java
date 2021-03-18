package br.com.deltafinanceira.bean;

public class RankingBean {
  private String vendedor;
  
  private float faturamento;
  
  public String getVendedor() {
    return this.vendedor;
  }
  
  public void setVendedor(String vendedor) {
    this.vendedor = vendedor;
  }
  
  public float getFaturamento() {
    return this.faturamento;
  }
  
  public void setFaturamento(float faturamento) {
    this.faturamento = faturamento;
  }
}
