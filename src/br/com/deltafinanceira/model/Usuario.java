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
@Table(name = "usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idusuario")
	private Integer idusuario;

	@Column(name = "cdinterno")
	private String cdinterno;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "email")
	private String email;

	@Column(name = "senha")
	private String senha;

	@Column(name = "sala")
	private String sala;

	@Column(name = "ativo")
	private boolean ativo = true;

	@Column(name = "descricaoativo")
	private String descricaoativo;

	@Column(name = "acessogeral")
	private boolean acessogeral;

	@Column(name = "comissao")
	private boolean comissao;

	@Column(name = "diretoria")
	private boolean diretoria;

	@Column(name = "supervisao")
	private boolean supervisao;

	@Column(name = "responsaveldepartamento")
	private boolean responsaveldepartamento;

	@Column(name = "operador")
	private boolean operador;

	@Column(name = "treinamento")
	private boolean treinamento;
	
	@Column(name = "tipovenda")
	private String tipovenda;
	
	@Column(name = "formalizacao")
	private boolean formalizacao;
	
	@Column(name = "comissaovenda")
	private boolean comissaovenda;

	@JoinColumn(name = "tipocolaborador_idtipocolaborador", referencedColumnName = "idtipocolaborador")
	@ManyToOne(optional = false)
	private Tipocolaborador tipocolaborador;

	@JoinColumn(name = "dadosbancario_iddadosbancario", referencedColumnName = "iddadosbancario")
	@ManyToOne(optional = false)
	private Dadosbancario dadosbancario;

	@JoinColumn(name = "departamento_iddepartamento", referencedColumnName = "iddepartamento")
	@ManyToOne(optional = false)
	private Departamento departamento;

	public Integer getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getCdinterno() {
		return this.cdinterno;
	}

	public void setCdinterno(String cdinterno) {
		this.cdinterno = cdinterno;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Tipocolaborador getTipocolaborador() {
		return this.tipocolaborador;
	}

	public void setTipocolaborador(Tipocolaborador tipocolaborador) {
		this.tipocolaborador = tipocolaborador;
	}

	public Dadosbancario getDadosbancario() {
		return this.dadosbancario;
	}

	public void setDadosbancario(Dadosbancario dadosbancario) {
		this.dadosbancario = dadosbancario;
	}

	public String getSala() {
		return this.sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public boolean isAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getDescricaoativo() {
		return this.descricaoativo;
	}

	public void setDescricaoativo(String descricaoativo) {
		this.descricaoativo = descricaoativo;
	}

	public boolean isAcessogeral() {
		return this.acessogeral;
	}

	public void setAcessogeral(boolean acessogeral) {
		this.acessogeral = acessogeral;
	}

	public boolean isComissao() {
		return this.comissao;
	}

	public void setComissao(boolean comissao) {
		this.comissao = comissao;
	}

	public boolean isDiretoria() {
		return this.diretoria;
	}

	public void setDiretoria(boolean diretoria) {
		this.diretoria = diretoria;
	}

	public boolean isSupervisao() {
		return this.supervisao;
	}

	public void setSupervisao(boolean supervisao) {
		this.supervisao = supervisao;
	}

	public synchronized boolean isResponsaveldepartamento() {
		return this.responsaveldepartamento;
	}

	public synchronized void setResponsaveldepartamento(boolean responsaveldepartamento) {
		this.responsaveldepartamento = responsaveldepartamento;
	}

	public synchronized Departamento getDepartamento() {
		return this.departamento;
	}

	public synchronized void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public boolean isOperador() {
		return operador;
	}

	public void setOperador(boolean operador) {
		this.operador = operador;
	}

	public boolean isTreinamento() {
		return treinamento;
	}

	public void setTreinamento(boolean treinamento) {
		this.treinamento = treinamento;
	}

	public String getTipovenda() {
		return tipovenda;
	}

	public void setTipovenda(String tipovenda) {
		this.tipovenda = tipovenda;
	}

	public boolean isFormalizacao() {
		return formalizacao;
	}

	public void setFormalizacao(boolean formalizacao) {
		this.formalizacao = formalizacao;
	}

	public boolean isComissaovenda() {
		return comissaovenda;
	}

	public void setComissaovenda(boolean comissaovenda) {
		this.comissaovenda = comissaovenda;
	}

	public int hashCode() {
		int hash = 0;
		hash += (this.idusuario != null) ? this.idusuario.hashCode() : 0;
		return hash;
	}

	public boolean equals(Object object) {
		if (!(object instanceof Usuario))
			return false;
		Usuario other = (Usuario) object;
		if ((this.idusuario == null && other.idusuario != null)
				|| (this.idusuario != null && !this.idusuario.equals(other.idusuario)))
			return false;
		return true;
	}

	public String toString() {
		return "br.com.deltafinanceira.model.Usuario[ idusuario=" + this.idusuario + " ]";
	}
}
