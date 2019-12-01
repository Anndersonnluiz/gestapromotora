package br.com.gestapromotora.model;

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
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private Integer idusuario;
    @Column(name = "cdinterno")
    private int cdinterno;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String senha;
    @JoinColumn(name = "tipocolaborador_idtipocolaborador", referencedColumnName = "idtipocolaborador")
    @ManyToOne(optional = false)
    private Tipocolaborador tipocolaborador;
    @JoinColumn(name = "dadosbancario_iddadosbancario", referencedColumnName = "iddadosbancario")
    @ManyToOne(optional = false)
    private Dadosbancario dadosbancario;
    
    
    public Usuario() {
		
	}
    
    
	public Integer getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}
	public int getCdinterno() {
		return cdinterno;
	}
	public void setCdinterno(int cdinterno) {
		this.cdinterno = cdinterno;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Tipocolaborador getTipocolaborador() {
		return tipocolaborador;
	}
	public void setTipocolaborador(Tipocolaborador tipocolaborador) {
		this.tipocolaborador = tipocolaborador;
	}
	public Dadosbancario getDadosbancario() {
		return dadosbancario;
	}
	public void setDadosbancario(Dadosbancario dadosbancario) {
		this.dadosbancario = dadosbancario;
	}
    
    
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idusuario != null ? idusuario.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) object;
		if ((this.idusuario == null && other.idusuario != null)
				|| (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.gestapromotora.model.Usuario[ idusuario=" + idusuario + " ]";
	}
    

}
