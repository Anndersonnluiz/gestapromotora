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
@Table(name = "cliente")
public class Cliente implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idcliente")
  private Integer idcliente;
  
  @Column(name = "nome")
  private String nome;
  
  @Column(name = "cpf")
  private String cpf;
  
  @Column(name = "nascimento")
  @Temporal(TemporalType.DATE)
  private Date nascimento;
  
  @Column(name = "rg")
  private String rg;
  
  @Column(name = "orgaoemissor")
  private String orgaroemissor;
  
  @Column(name = "ufrg")
  private String ufrg;
  
  @Column(name = "emissao")
  @Temporal(TemporalType.DATE)
  private Date emissao;
  
  @Column(name = "naturalidade")
  private String naturalidade;
  
  @Column(name = "ufnaturalidade")
  private String ufnaturalidade;
  
  @Column(name = "nomepai")
  private String nomepai;
  
  @Column(name = "nomemae")
  private String nomemae;
  
  @Column(name = "cep")
  private String cep;
  
  @Column(name = "tipologradouro")
  private String tipologradouro;
  
  @Column(name = "logradouro")
  private String logradouro;
  
  @Column(name = "numero")
  private String numero;
  
  @Column(name = "complemento")
  private String complemento;
  
  @Column(name = "bairro")
  private String bairro;
  
  @Column(name = "cidade")
  private String cidade;
  
  @Column(name = "ufestado")
  private String ufestado;
  
  @Column(name = "valorsalario")
  private Float valorsalario;
  
  @Column(name = "telefoneresidencial")
  private String telefoneresidencial;
  
  @Column(name = "telefonecomercial")
  private String telefonecomercial;
  
  @Column(name = "telefonecelular")
  private String telefonecelular;
  
  @Column(name = "recebebeneficio")
  private boolean recebebeneficio;
  
  @Column(name = "email")
  private String email;
  
  @Column(name = "idusuario")
  private int idusuario;
  
  @Column(name = "diames")
  private int diames;
  
  @Column(name = "matricula")
  private String matricula;
  
  @JoinColumn(name = "dadosbancario_iddadosbancario", referencedColumnName = "iddadosbancario")
  @ManyToOne(optional = false)
  private Dadosbancario dadosbancario;
  
  @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
  @ManyToOne(optional = false)
  private Usuario usuario;
  
  @Transient
  private boolean selecionado;
  
  public Integer getIdcliente() {
    return this.idcliente;
  }
  
  public void setIdcliente(Integer idcliente) {
    this.idcliente = idcliente;
  }
  
  public String getNome() {
    return this.nome;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public String getCpf() {
    return this.cpf;
  }
  
  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
  
  public Date getNascimento() {
    return this.nascimento;
  }
  
  public void setNascimento(Date nascimento) {
    this.nascimento = nascimento;
  }
  
  public String getRg() {
    return this.rg;
  }
  
  public void setRg(String rg) {
    this.rg = rg;
  }
  
  public String getOrgaroemissor() {
    return this.orgaroemissor;
  }
  
  public void setOrgaroemissor(String orgaroemissor) {
    this.orgaroemissor = orgaroemissor;
  }
  
  public String getUfrg() {
    return this.ufrg;
  }
  
  public void setUfrg(String ufrg) {
    this.ufrg = ufrg;
  }
  
  public Date getEmissao() {
    return this.emissao;
  }
  
  public void setEmissao(Date emissao) {
    this.emissao = emissao;
  }
  
  public String getNaturalidade() {
    return this.naturalidade;
  }
  
  public void setNaturalidade(String naturalidade) {
    this.naturalidade = naturalidade;
  }
  
  public String getUfnaturalidade() {
    return this.ufnaturalidade;
  }
  
  public void setUfnaturalidade(String ufnaturalidade) {
    this.ufnaturalidade = ufnaturalidade;
  }
  
  public String getNomepai() {
    return this.nomepai;
  }
  
  public void setNomepai(String nomepai) {
    this.nomepai = nomepai;
  }
  
  public String getNomemae() {
    return this.nomemae;
  }
  
  public void setNomemae(String nomemae) {
    this.nomemae = nomemae;
  }
  
  public String getCep() {
    return this.cep;
  }
  
  public void setCep(String cep) {
    this.cep = cep;
  }
  
  public String getTipologradouro() {
    return this.tipologradouro;
  }
  
  public void setTipologradouro(String tipologradouro) {
    this.tipologradouro = tipologradouro;
  }
  
  public String getLogradouro() {
    return this.logradouro;
  }
  
  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }
  
  public String getNumero() {
    return this.numero;
  }
  
  public void setNumero(String numero) {
    this.numero = numero;
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
  
  public String getCidade() {
    return this.cidade;
  }
  
  public void setCidade(String cidade) {
    this.cidade = cidade;
  }
  
  public String getUfestado() {
    return this.ufestado;
  }
  
  public void setUfestado(String ufestado) {
    this.ufestado = ufestado;
  }
  
  public Float getValorsalario() {
    return this.valorsalario;
  }
  
  public void setValorsalario(Float valorsalario) {
    this.valorsalario = valorsalario;
  }
  
  public String getTelefoneresidencial() {
    return this.telefoneresidencial;
  }
  
  public void setTelefoneresidencial(String telefoneresidencial) {
    this.telefoneresidencial = telefoneresidencial;
  }
  
  public String getTelefonecomercial() {
    return this.telefonecomercial;
  }
  
  public void setTelefonecomercial(String telefonecomercial) {
    this.telefonecomercial = telefonecomercial;
  }
  
  public String getTelefonecelular() {
    return this.telefonecelular;
  }
  
  public void setTelefonecelular(String telefonecelular) {
    this.telefonecelular = telefonecelular;
  }
  
  public boolean isRecebebeneficio() {
    return this.recebebeneficio;
  }
  
  public void setRecebebeneficio(boolean recebebeneficio) {
    this.recebebeneficio = recebebeneficio;
  }
  
  public Dadosbancario getDadosbancario() {
    return this.dadosbancario;
  }
  
  public void setDadosbancario(Dadosbancario dadosbancario) {
    this.dadosbancario = dadosbancario;
  }
  
  public String getEmail() {
    return this.email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public int getIdusuario() {
    return this.idusuario;
  }
  
  public void setIdusuario(int idusuario) {
    this.idusuario = idusuario;
  }
  
  public int getDiames() {
    return this.diames;
  }
  
  public void setDiames(int diames) {
    this.diames = diames;
  }
  
  public Usuario getUsuario() {
    return this.usuario;
  }
  
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  
  public boolean isSelecionado() {
    return this.selecionado;
  }
  
  public void setSelecionado(boolean selecionado) {
    this.selecionado = selecionado;
  }
  
  public String getMatricula() {
	return matricula;
}

public void setMatricula(String matricula) {
	this.matricula = matricula;
}

public int hashCode() {
    int hash = 0;
    hash += (this.idcliente != null) ? this.idcliente.hashCode() : 0;
    return hash;
  }
  
  public boolean equals(Object object) {
    if (!(object instanceof Cliente))
      return false; 
    Cliente other = (Cliente)object;
    if ((this.idcliente == null && other.idcliente != null) || (
      this.idcliente != null && !this.idcliente.equals(other.idcliente)))
      return false; 
    return true;
  }
  
  public String toString() {
    return "br.com.deltafinanceira.model.Cliente[ idcliente=" + this.idcliente + " ]";
  }
}
