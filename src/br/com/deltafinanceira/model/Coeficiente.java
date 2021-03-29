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
@Table(name = "Coeficiente")
public class Coeficiente implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idcoeficiente")
  private Integer idcoeficiente;
  
  @Column(name = "nometabela")
  private String nometabela;
  
  @Column(name = "nomeproduto")
  private String nomeproduto;
  
  @Column(name = "tipotabela")
  private String tipotabela;
  
  @Column(name = "codigotabela")
  private String codigotabela;
  
  @Column(name = "taxasjurosini")
  private Float taxasjurosini;
  
  @Column(name = "taxasjurosfin")
  private Float taxasjurosfin;
  
  @Column(name = "formdigstatustabela")
  private boolean formdigstatustabela;
  
  @JoinColumn(name = "orgaobanco_idorgaobanco", referencedColumnName = "idorgaobanco")
  @ManyToOne(optional = false)
  private OrgaoBanco orgaoBanco;
  
  @Column(name = "aberturaconta")
  private boolean aberturaconta = true;
  
  @Column(name = "compradividarefin")
  private boolean compradividarefin = true;
  
  @Column(name = "margemlivrerefin")
  private boolean margemlivrerefin = true;
  
  @Column(name = "portabilidademargemrefin")
  private boolean portabilidademargemrefin = true;
  
  @Column(name = "refinportabilidade")
  private boolean refinportabilidade = true;
  
  @Column(name = "compradivida")
  private boolean compradivida = true;
  
  @Column(name = "compradividarefinmargem")
  private boolean compradividarefinmargem = true;
  
  @Column(name = "compradividamargem")
  private boolean compradividamargem = true;
  
  @Column(name = "emprestimocomplementar")
  private boolean emprestimocomplementar = true;
  
  @Column(name = "margemlivre")
  private boolean margemlivre = true;
  
  @Column(name = "portabilidademargem")
  private boolean portabilidademargem = true;
  
  @Column(name = "refinanciamento")
  private boolean refinanciamento = true;
  
  @Column(name = "refinanciamentorec")
  private boolean refinanciamentorec = true;
  
  @Column(name = "portabilidade")
  private boolean portabilidade = true;
  
  @Column(name = "portabilidaderefin")
  private boolean portabilidaderefin = true;
  
  @Column(name = "refinportabilidademargem")
  private boolean refinportabilidademargem = true;
  
  @Column(name = "comercial")
  private boolean comercial = true;
  
  @Column(name = "coordenador")
  private boolean coordenador = true;
  
  @Column(name = "corretorexterno")
  private boolean corretorexterno = true;
  
  @Column(name = "corretorinterno")
  private boolean corretorinterno = true;
  
  @Column(name = "semgrupo")
  private boolean semgrupo = true;
  
  @Column(name = "comercialrestricao")
  private boolean comercialrestricao = true;
  
  @Column(name = "cobranca")
  private boolean cobranca = true;
  
  @Column(name = "retencao")
  private boolean retencao = true;
  
  @Column(name = "tipooperacoes")
  private boolean tipooperacoes = true;
  
  @Column(name = "prazo")
  private int prazo;
  
  @Column(name = "coeficientevalor")
  private float coeficientevalor;
  
  @Column(name = "comissaoloja")
  private float comissaoloja;
  
  @Column(name = "comissaocorretor")
  private float comissaocorretor;
  
  @Column(name = "comissaototal")
  private float comissaototal;
  
  @JoinColumn(name = "tipooperacao_idtipooperacao", referencedColumnName = "idtipooperacao")
  @ManyToOne(optional = false)
  private Tipooperacao tipooperacao;
  
  public Integer getIdcoeficiente() {
    return this.idcoeficiente;
  }
  
  public void setIdcoeficiente(Integer idcoeficiente) {
    this.idcoeficiente = idcoeficiente;
  }
  
  public String getNometabela() {
    return this.nometabela;
  }
  
  public void setNometabela(String nometabela) {
    this.nometabela = nometabela;
  }
  
  public String getNomeproduto() {
    return this.nomeproduto;
  }
  
  public void setNomeproduto(String nomeproduto) {
    this.nomeproduto = nomeproduto;
  }
  
  public String getTipotabela() {
    return this.tipotabela;
  }
  
  public void setTipotabela(String tipotabela) {
    this.tipotabela = tipotabela;
  }
  
  public String getCodigotabela() {
    return this.codigotabela;
  }
  
  public void setCodigotabela(String codigotabela) {
    this.codigotabela = codigotabela;
  }
  
  public Float getTaxasjurosini() {
    return this.taxasjurosini;
  }
  
  public void setTaxasjurosini(Float taxasjurosini) {
    this.taxasjurosini = taxasjurosini;
  }
  
  public Float getTaxasjurosfin() {
    return this.taxasjurosfin;
  }
  
  public void setTaxasjurosfin(Float taxasjurosfin) {
    this.taxasjurosfin = taxasjurosfin;
  }
  
  public boolean isFormdigstatustabela() {
    return this.formdigstatustabela;
  }
  
  public void setFormdigstatustabela(boolean formdigstatustabela) {
    this.formdigstatustabela = formdigstatustabela;
  }
  
  public OrgaoBanco getOrgaoBanco() {
    return this.orgaoBanco;
  }
  
  public void setOrgaoBanco(OrgaoBanco orgaoBanco) {
    this.orgaoBanco = orgaoBanco;
  }
  
  public boolean isAberturaconta() {
    return this.aberturaconta;
  }
  
  public void setAberturaconta(boolean aberturaconta) {
    this.aberturaconta = aberturaconta;
  }
  
  public boolean isCompradividarefin() {
    return this.compradividarefin;
  }
  
  public void setCompradividarefin(boolean compradividarefin) {
    this.compradividarefin = compradividarefin;
  }
  
  public boolean isMargemlivrerefin() {
    return this.margemlivrerefin;
  }
  
  public void setMargemlivrerefin(boolean margemlivrerefin) {
    this.margemlivrerefin = margemlivrerefin;
  }
  
  public boolean isPortabilidademargemrefin() {
    return this.portabilidademargemrefin;
  }
  
  public void setPortabilidademargemrefin(boolean portabilidademargemrefin) {
    this.portabilidademargemrefin = portabilidademargemrefin;
  }
  
  public boolean isRefinportabilidade() {
    return this.refinportabilidade;
  }
  
  public void setRefinportabilidade(boolean refinportabilidade) {
    this.refinportabilidade = refinportabilidade;
  }
  
  public boolean isCompradivida() {
    return this.compradivida;
  }
  
  public void setCompradivida(boolean compradivida) {
    this.compradivida = compradivida;
  }
  
  public boolean isCompradividarefinmargem() {
    return this.compradividarefinmargem;
  }
  
  public void setCompradividarefinmargem(boolean compradividarefinmargem) {
    this.compradividarefinmargem = compradividarefinmargem;
  }
  
  public boolean isCompradividamargem() {
    return this.compradividamargem;
  }
  
  public void setCompradividamargem(boolean compradividamargem) {
    this.compradividamargem = compradividamargem;
  }
  
  public boolean isEmprestimocomplementar() {
    return this.emprestimocomplementar;
  }
  
  public void setEmprestimocomplementar(boolean emprestimocomplementar) {
    this.emprestimocomplementar = emprestimocomplementar;
  }
  
  public boolean isMargemlivre() {
    return this.margemlivre;
  }
  
  public void setMargemlivre(boolean margemlivre) {
    this.margemlivre = margemlivre;
  }
  
  public boolean isPortabilidademargem() {
    return this.portabilidademargem;
  }
  
  public void setPortabilidademargem(boolean portabilidademargem) {
    this.portabilidademargem = portabilidademargem;
  }
  
  public boolean isRefinanciamento() {
    return this.refinanciamento;
  }
  
  public void setRefinanciamento(boolean refinanciamento) {
    this.refinanciamento = refinanciamento;
  }
  
  public boolean isRefinanciamentorec() {
    return this.refinanciamentorec;
  }
  
  public void setRefinanciamentorec(boolean refinanciamentorec) {
    this.refinanciamentorec = refinanciamentorec;
  }
  
  public boolean isPortabilidade() {
    return this.portabilidade;
  }
  
  public void setPortabilidade(boolean portabilidade) {
    this.portabilidade = portabilidade;
  }
  
  public boolean isPortabilidaderefin() {
    return this.portabilidaderefin;
  }
  
  public void setPortabilidaderefin(boolean portabilidaderefin) {
    this.portabilidaderefin = portabilidaderefin;
  }
  
  public boolean isRefinportabilidademargem() {
    return this.refinportabilidademargem;
  }
  
  public void setRefinportabilidademargem(boolean refinportabilidademargem) {
    this.refinportabilidademargem = refinportabilidademargem;
  }
  
  public boolean isComercial() {
    return this.comercial;
  }
  
  public void setComercial(boolean comercial) {
    this.comercial = comercial;
  }
  
  public boolean isCoordenador() {
    return this.coordenador;
  }
  
  public void setCoordenador(boolean coordenador) {
    this.coordenador = coordenador;
  }
  
  public boolean isCorretorexterno() {
    return this.corretorexterno;
  }
  
  public void setCorretorexterno(boolean corretorexterno) {
    this.corretorexterno = corretorexterno;
  }
  
  public boolean isCorretorinterno() {
    return this.corretorinterno;
  }
  
  public void setCorretorinterno(boolean corretorinterno) {
    this.corretorinterno = corretorinterno;
  }
  
  public boolean isSemgrupo() {
    return this.semgrupo;
  }
  
  public void setSemgrupo(boolean semgrupo) {
    this.semgrupo = semgrupo;
  }
  
  public boolean isComercialrestricao() {
    return this.comercialrestricao;
  }
  
  public void setComercialrestricao(boolean comercialrestricao) {
    this.comercialrestricao = comercialrestricao;
  }
  
  public boolean isCobranca() {
    return this.cobranca;
  }
  
  public void setCobranca(boolean cobranca) {
    this.cobranca = cobranca;
  }
  
  public boolean isRetencao() {
    return this.retencao;
  }
  
  public void setRetencao(boolean retencao) {
    this.retencao = retencao;
  }
  
  public boolean isTipooperacoes() {
    return this.tipooperacoes;
  }
  
  public void setTipooperacoes(boolean tipooperacoes) {
    this.tipooperacoes = tipooperacoes;
  }
  
  public Tipooperacao getTipooperacao() {
    return this.tipooperacao;
  }
  
  public void setTipooperacao(Tipooperacao tipooperacao) {
    this.tipooperacao = tipooperacao;
  }
  
  public int getPrazo() {
	return prazo;
}

public void setPrazo(int prazo) {
	this.prazo = prazo;
}

public float getCoeficientevalor() {
	return coeficientevalor;
}

public void setCoeficientevalor(float coeficientevalor) {
	this.coeficientevalor = coeficientevalor;
}

public float getComissaoloja() {
	return comissaoloja;
}

public void setComissaoloja(float comissaoloja) {
	this.comissaoloja = comissaoloja;
}

public float getComissaocorretor() {
	return comissaocorretor;
}

public void setComissaocorretor(float comissaocorretor) {
	this.comissaocorretor = comissaocorretor;
}

public float getComissaototal() {
	return comissaototal;
}

public void setComissaototal(float comissaototal) {
	this.comissaototal = comissaototal;
}

public int hashCode() {
    int hash = 0;
    hash += (this.idcoeficiente != null) ? this.idcoeficiente.hashCode() : 0;
    return hash;
  }
  
  public boolean equals(Object object) {
    if (!(object instanceof Coeficiente))
      return false; 
    Coeficiente other = (Coeficiente)object;
    if ((this.idcoeficiente == null && other.idcoeficiente != null) || (
      this.idcoeficiente != null && !this.idcoeficiente.equals(other.idcoeficiente)))
      return false; 
    return true;
  }
  
  public String toString() {
    return "br.com.deltafinanceira.model.Coeficiente[ idcoeficiente=" + this.idcoeficiente + " ]";
  }
}
