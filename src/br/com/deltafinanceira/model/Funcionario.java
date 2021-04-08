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

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idfuncionario")
	private Integer idfuncionario;
	@Column(name = "nome")
	private String nome;
	@Temporal(TemporalType.DATE)
	@Column(name = "nascimento")
	private Date nascimento;
	@Column(name = "telefone")
	private String telefone;
	@Column(name = "cpf")
	private String cpf;
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
	@Temporal(TemporalType.DATE)
	@Column(name = "contratacao")
	private Date contratacao;
	@Column(name = "ondetrabalha")
	private String ondetrabalha;
	@Column(name = "cep")
	private String cep;
	@Column(name = "rg")
	private String rg;
	@JoinColumn(name = "dadosbancario_iddadosbancario", referencedColumnName = "iddadosbancario")
	@ManyToOne(optional = false)
	private Dadosbancario dadosbancario;
	
	
	public Funcionario() {
	
	}


	public Integer getIdfuncionario() {
		return idfuncionario;
	}


	public void setIdfuncionario(Integer idfuncionario) {
		this.idfuncionario = idfuncionario;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Date getNascimento() {
		return nascimento;
	}


	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getTipologradouro() {
		return tipologradouro;
	}


	public void setTipologradouro(String tipologradouro) {
		this.tipologradouro = tipologradouro;
	}


	public String getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public String getUfestado() {
		return ufestado;
	}


	public void setUfestado(String ufestado) {
		this.ufestado = ufestado;
	}


	public Date getContratacao() {
		return contratacao;
	}


	public void setContratacao(Date contratacao) {
		this.contratacao = contratacao;
	}


	public String getOndetrabalha() {
		return ondetrabalha;
	}


	public void setOndetrabalha(String ondetrabalha) {
		this.ondetrabalha = ondetrabalha;
	}
	
	
	
	
	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public Dadosbancario getDadosbancario() {
		return dadosbancario;
	}


	public void setDadosbancario(Dadosbancario dadosbancario) {
		this.dadosbancario = dadosbancario;
	}


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}


	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idfuncionario != null ? idfuncionario.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Funcionario)) {
			return false;
		}
		Funcionario other = (Funcionario) object;
		if ((this.idfuncionario == null && other.idfuncionario != null)
				|| (this.idfuncionario != null && !this.idfuncionario.equals(other.idfuncionario))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.deltafinanceira.model.Funcionario[ idfuncionario=" + idfuncionario + " ]";
	}
	
	
	
	
	

}
