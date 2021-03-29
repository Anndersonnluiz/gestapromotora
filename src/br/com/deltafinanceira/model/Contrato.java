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
@Table(name = "contrato")
public class Contrato implements Serializable {
  @Column(name = "bloqueio")
  private boolean bloqueio = false;
  
  @Column(name = "digitado")
  private boolean digitado = false;
  
  @Column(name = "fisico")
  private boolean fisico = false;
  
  @Column(name = "pendente")
  private boolean pendente = false;
  
  @Column(name = "descricaobloqueio")
  private String descricaobloqueio = "unlock";
  
  @Column(name = "descricaodigitado")
  private String descricaodigitado = "file";
  
  @Column(name = "descricaofisico")
  private String descricaofisico = "x-circle";
  
  @Column(name = "ultimamudancasituacao")
  @Temporal(TemporalType.DATE)
  private Date ultimamudancasituacao = new Date();
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idcontrato")
  private Integer idcontrato;
  
  @Column(name = "parcelaspagas")
  private int parcelaspagas;
  
  @Column(name = "totalparcelas")
  private int totalparcelas;
  
  @Column(name = "percentualpago")
  private float percentualpago;
  
  @Column(name = "parcelasrestantes")
  private int parcelasrestantes;
  
  @Column(name = "contratoportado")
  private String contratoportado;
  
  @Column(name = "valorparcela")
  private float valorparcela;
  
  @Column(name = "valorquitar")
  private float valorquitar;
  
  @Column(name = "saldoinadimplencia")
  private float saldoinadimplencia;
  
  @Column(name = "tarifa")
  private float tarifa;
  
  @Column(name = "ajustecomissaocheck")
  private boolean ajustecomissaocheck;
  
  @Column(name = "observacao")
  private String observacao;
  
  @Column(name = "parcela")
  private float parcela;
  
  @Column(name = "valoroperacao")
  private float valoroperacao;
  
  @Column(name = "valorcliente")
  private float valorcliente;
  
  @Column(name = "parceladivergente")
  private float parceladivergente;
  
  @Column(name = "valoroperacaodivergente")
  private float valoroperacaodivergente;
  
  @Column(name = "assinadobanco")
  private boolean assinadobanco;
  
  @JoinColumn(name = "situacao_idsituacao", referencedColumnName = "idsituacao")
  @ManyToOne(optional = false)
  private Situacao situacao;
  
  @Column(name = "secretaria")
  private String secretaria;
  
  @Column(name = "valorclientedivergente")
  private float valorclientedivergente;
  
  @Column(name = "datacadastro")
  @Temporal(TemporalType.DATE)
  private Date datacadastro;
  
  @Column(name = "matricula")
  private String matricula;
  
  @Column(name = "senha")
  private String senha;
  
  @Column(name = "senhacontracheque")
  private String senhacontracheque;
  
  @Column(name = "datapagamento")
  @Temporal(TemporalType.DATE)
  private Date datapagamento;
  
  @Column(name = "codigocontrato")
  private String codigocontrato;
  
  @Column(name = "detalhesituacao")
  private String detalhesituacao;
  
  @Column(name = "margemutilizada")
  private float margemutilizada;
  
  @Column(name = "nomeoperacao")
  private String nomeoperacao;
  
  @JoinColumn(name = "orgaobanco_idorgaobanco", referencedColumnName = "idorgaobanco")
  @ManyToOne(optional = false)
  private OrgaoBanco orgaoBanco;
  
  @Column(name = "valorescoeficiente")
  private int valorescoeficiente;
  
  @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
  @ManyToOne(optional = false)
  private Cliente cliente;
  
  @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
  @ManyToOne(optional = false)
  private Usuario usuario;
  
  @JoinColumn(name = "banco_idbanco", referencedColumnName = "idbanco")
  @ManyToOne(optional = false)
  private Banco banco;
  
  @JoinColumn(name = "tipooperacao_idtipooperacao", referencedColumnName = "idtipooperacao")
  @ManyToOne(optional = false)
  private Tipooperacao tipooperacao;
  
  @Column(name = "idregracoeficiente")
  private int idregracoeficiente;
  
  @Column(name = "reducaoparcela")
  private boolean reducaoparcela;
  
  @Column(name = "operacaoinss")
  private boolean operacaoinss;
  
  @Column(name = "simulacao")
  private boolean simulacao;
  
  @Column(name = "prazo")
  private int prazo;
  
  @Column(name = "possuioperador")
  private boolean possuioperador;
  
  @Column(name = "operador")
  private String operador;
  
  @Column(name = "idoperador")
  private int idoperador;
  
  @JoinColumn(name = "promotora_idpromotora", referencedColumnName = "idpromotora")
  @ManyToOne(optional = false)
  private Promotora promotora;
  
  @Column(name = "credpessoal")
  private boolean credpessoal;
  
  @Transient
  private String voltarTela;
  
  
  public Contrato() {
	possuioperador = false;
	operador = " ";
}
  
  
  public Integer getIdcontrato() {
    return this.idcontrato;
  }
  
  public void setIdcontrato(Integer idcontrato) {
    this.idcontrato = idcontrato;
  }
  
  public int getParcelaspagas() {
    return this.parcelaspagas;
  }
  
  public void setParcelaspagas(int parcelaspagas) {
    this.parcelaspagas = parcelaspagas;
  }
  
  public int getTotalparcelas() {
    return this.totalparcelas;
  }
  
  public void setTotalparcelas(int totalparcelas) {
    this.totalparcelas = totalparcelas;
  }
  
  public float getPercentualpago() {
    return this.percentualpago;
  }
  
  public void setPercentualpago(float percentualpago) {
    this.percentualpago = percentualpago;
  }
  
  public int getParcelasrestantes() {
    return this.parcelasrestantes;
  }
  
  public void setParcelasrestantes(int parcelasrestantes) {
    this.parcelasrestantes = parcelasrestantes;
  }
  
  public String getContratoportado() {
    return this.contratoportado;
  }
  
  public void setContratoportado(String contratoportado) {
    this.contratoportado = contratoportado;
  }
  
  public float getValorparcela() {
    return this.valorparcela;
  }
  
  public void setValorparcela(float valorparcela) {
    this.valorparcela = valorparcela;
  }
  
  public float getValorquitar() {
    return this.valorquitar;
  }
  
  public void setValorquitar(float valorquitar) {
    this.valorquitar = valorquitar;
  }
  
  public float getSaldoinadimplencia() {
    return this.saldoinadimplencia;
  }
  
  public void setSaldoinadimplencia(float saldoinadimplencia) {
    this.saldoinadimplencia = saldoinadimplencia;
  }
  
  public float getTarifa() {
    return this.tarifa;
  }
  
  public void setTarifa(float tarifa) {
    this.tarifa = tarifa;
  }
  
  public boolean isAjustecomissaocheck() {
    return this.ajustecomissaocheck;
  }
  
  public void setAjustecomissaocheck(boolean ajustecomissaocheck) {
    this.ajustecomissaocheck = ajustecomissaocheck;
  }
  
  public String getObservacao() {
    return this.observacao;
  }
  
  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }
  
  public float getParcela() {
    return this.parcela;
  }
  
  public void setParcela(float parcela) {
    this.parcela = parcela;
  }
  
  public float getValoroperacao() {
    return this.valoroperacao;
  }
  
  public void setValoroperacao(float valoroperacao) {
    this.valoroperacao = valoroperacao;
  }
  
  public float getValorcliente() {
    return this.valorcliente;
  }
  
  public void setValorcliente(float valorcliente) {
    this.valorcliente = valorcliente;
  }
  
  public float getParceladivergente() {
    return this.parceladivergente;
  }
  
  public void setParceladivergente(float parceladivergente) {
    this.parceladivergente = parceladivergente;
  }
  
  public float getValoroperacaodivergente() {
    return this.valoroperacaodivergente;
  }
  
  public void setValoroperacaodivergente(float valoroperacaodivergente) {
    this.valoroperacaodivergente = valoroperacaodivergente;
  }
  
  public float getValorclientedivergente() {
    return this.valorclientedivergente;
  }
  
  public void setValorclientedivergente(float valorclientedivergente) {
    this.valorclientedivergente = valorclientedivergente;
  }
  
  public Cliente getCliente() {
    return this.cliente;
  }
  
  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }
  
  public Usuario getUsuario() {
    return this.usuario;
  }
  
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  
  public Tipooperacao getTipooperacao() {
    return this.tipooperacao;
  }
  
  public void setTipooperacao(Tipooperacao tipooperacao) {
    this.tipooperacao = tipooperacao;
  }
  
  public boolean isAssinadobanco() {
    return this.assinadobanco;
  }
  
  public void setAssinadobanco(boolean assinadobanco) {
    this.assinadobanco = assinadobanco;
  }
  
  public Situacao getSituacao() {
    return this.situacao;
  }
  
  public void setSituacao(Situacao situacao) {
    this.situacao = situacao;
  }
  
  public String getSecretaria() {
    return this.secretaria;
  }
  
  public void setSecretaria(String secretaria) {
    this.secretaria = secretaria;
  }
  
  public Date getDatacadastro() {
    return this.datacadastro;
  }
  
  public void setDatacadastro(Date datacadastro) {
    this.datacadastro = datacadastro;
  }
  
  public String getMatricula() {
    return this.matricula;
  }
  
  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }
  
  public String getSenha() {
    return this.senha;
  }
  
  public void setSenha(String senha) {
    this.senha = senha;
  }
  
  public String getSenhacontracheque() {
    return this.senhacontracheque;
  }
  
  public void setSenhacontracheque(String senhacontracheque) {
    this.senhacontracheque = senhacontracheque;
  }
  
  public Banco getBanco() {
    return this.banco;
  }
  
  public void setBanco(Banco banco) {
    this.banco = banco;
  }
  
  public boolean isBloqueio() {
    return this.bloqueio;
  }
  
  public void setBloqueio(boolean bloqueio) {
    this.bloqueio = bloqueio;
  }
  
  public boolean isFisico() {
    return this.fisico;
  }
  
  public void setFisico(boolean fisico) {
    this.fisico = fisico;
  }
  
  public boolean isDigitado() {
    return this.digitado;
  }
  
  public void setDigitado(boolean digitado) {
    this.digitado = digitado;
  }
  
  public String getDescricaobloqueio() {
    return this.descricaobloqueio;
  }
  
  public void setDescricaobloqueio(String descricaobloqueio) {
    this.descricaobloqueio = descricaobloqueio;
  }
  
  public String getDescricaodigitado() {
    return this.descricaodigitado;
  }
  
  public void setDescricaodigitado(String descricaodigitado) {
    this.descricaodigitado = descricaodigitado;
  }
  
  public Date getDatapagamento() {
    return this.datapagamento;
  }
  
  public void setDatapagamento(Date datapagamento) {
    this.datapagamento = datapagamento;
  }
  
  public String getDescricaofisico() {
    return this.descricaofisico;
  }
  
  public void setDescricaofisico(String descricaofisico) {
    this.descricaofisico = descricaofisico;
  }
  
  public Date getUltimamudancasituacao() {
    return this.ultimamudancasituacao;
  }
  
  public void setUltimamudancasituacao(Date ultimamudancasituacao) {
    this.ultimamudancasituacao = ultimamudancasituacao;
  }
  
  public String getCodigocontrato() {
    return this.codigocontrato;
  }
  
  public void setCodigocontrato(String codigocontrato) {
    this.codigocontrato = codigocontrato;
  }
  
  public String getDetalhesituacao() {
    return this.detalhesituacao;
  }
  
  public void setDetalhesituacao(String detalhesituacao) {
    this.detalhesituacao = detalhesituacao;
  }
  
  public boolean isPendente() {
    return this.pendente;
  }
  
  public void setPendente(boolean pendente) {
    this.pendente = pendente;
  }
  
  public float getMargemutilizada() {
    return this.margemutilizada;
  }
  
  public void setMargemutilizada(float margemutilizada) {
    this.margemutilizada = margemutilizada;
  }
  
  public int getIdregracoeficiente() {
    return this.idregracoeficiente;
  }
  
  public void setIdregracoeficiente(int idregracoeficiente) {
    this.idregracoeficiente = idregracoeficiente;
  }
  
  public boolean isReducaoparcela() {
    return this.reducaoparcela;
  }
  
  public void setReducaoparcela(boolean reducaoparcela) {
    this.reducaoparcela = reducaoparcela;
  }
  
  public boolean isOperacaoinss() {
    return this.operacaoinss;
  }
  
  public void setOperacaoinss(boolean operacaoinss) {
    this.operacaoinss = operacaoinss;
  }
  
  public Promotora getPromotora() {
    return this.promotora;
  }
  
  public void setPromotora(Promotora promotora) {
    this.promotora = promotora;
  }
  
  public String getVoltarTela() {
    return this.voltarTela;
  }
  
  public void setVoltarTela(String voltarTela) {
    this.voltarTela = voltarTela;
  }
  
  public synchronized boolean isSimulacao() {
    return this.simulacao;
  }
  
  public synchronized void setSimulacao(boolean simulacao) {
    this.simulacao = simulacao;
  }
  
  public OrgaoBanco getOrgaoBanco() {
    return this.orgaoBanco;
  }
  
  public void setOrgaoBanco(OrgaoBanco orgaoBanco) {
    this.orgaoBanco = orgaoBanco;
  }
  
  public int getValorescoeficiente() {
    return this.valorescoeficiente;
  }
  
  public void setValorescoeficiente(int valorescoeficiente) {
    this.valorescoeficiente = valorescoeficiente;
  }
  
  public int getPrazo() {
    return this.prazo;
  }
  
  public void setPrazo(int prazo) {
    this.prazo = prazo;
  }
  
  public boolean isCredpessoal() {
    return this.credpessoal;
  }
  
  public void setCredpessoal(boolean credpessoal) {
    this.credpessoal = credpessoal;
  }
  
  public boolean isPossuioperador() {
	return possuioperador;
}

public void setPossuioperador(boolean possuioperador) {
	this.possuioperador = possuioperador;
}

public String getOperador() {
	return operador;
}

public void setOperador(String operador) {
	this.operador = operador;
}

public int getIdoperador() {
	return idoperador;
}


public void setIdoperador(int idoperador) {
	this.idoperador = idoperador;
}


public String getNomeoperacao() {
	return nomeoperacao;
}


public void setNomeoperacao(String nomeoperacao) {
	this.nomeoperacao = nomeoperacao;
}


public int hashCode() {
    int hash = 0;
    hash += (this.idcontrato != null) ? this.idcontrato.hashCode() : 0;
    return hash;
  }
  
  public boolean equals(Object object) {
    if (!(object instanceof Contrato))
      return false; 
    Contrato other = (Contrato)object;
    if ((this.idcontrato == null && other.idcontrato != null) || (
      this.idcontrato != null && !this.idcontrato.equals(other.idcontrato)))
      return false; 
    return true;
  }
  
  public String toString() {
    return "br.com.deltafinanceira.model.Contrato[ idcontrato=" + this.idcontrato + " ]";
  }
}
