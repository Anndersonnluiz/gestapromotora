package br.com.gestapromotora.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "acessocolaborador")
public class Acessocolaborador implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idacessocolaborador")
	private Integer idacessocolaborador;
	@Column(name = "cadastrarbanco")
	private boolean cadastrarbanco;
	@Column(name = "cadastrarorgao")
	private boolean cadastrarorgao;
	@Column(name = "cadastrarcoeficiente")
	private boolean cadastrarcoeficiente;
	@Column(name = "cadastrarvalores")
	private boolean cadastrarvalores;
	@Column(name = "cadastrarregras")
	private boolean cadastrarregras;
	@Column(name = "financeiro")
	private boolean financeiro;
	@Column(name = "fisico")
	private boolean fisico;
	@Column(name = "etapa3")
	private boolean etapa3;
	@Column(name = "etapa4")
	private boolean etapa4;
	@Column(name = "cadastrar")
	private boolean cadastrar;

	 
	public Acessocolaborador() {
		cadastrar = true;
		etapa3 = true;
		etapa4 = true;
		fisico = true;
		financeiro = true;
		cadastrarbanco = true;
		cadastrarcoeficiente = true;
		cadastrarorgao = true;
		cadastrarregras = true;
		cadastrarvalores = true;
	}
	
	
	public Integer getIdacessocolaborador() {
		return idacessocolaborador;
	}
	public void setIdacessocolaborador(Integer idacessocolaborador) {
		this.idacessocolaborador = idacessocolaborador;
	}
	public boolean isCadastrarbanco() {
		return cadastrarbanco;
	}
	public void setCadastrarbanco(boolean cadastrarbanco) {
		this.cadastrarbanco = cadastrarbanco;
	}
	public boolean isCadastrarorgao() {
		return cadastrarorgao;
	}
	public void setCadastrarorgao(boolean cadastrarorgao) {
		this.cadastrarorgao = cadastrarorgao;
	}
	public boolean isCadastrarcoeficiente() {
		return cadastrarcoeficiente;
	}
	public void setCadastrarcoeficiente(boolean cadastrarcoeficiente) {
		this.cadastrarcoeficiente = cadastrarcoeficiente;
	}
	public boolean isCadastrarvalores() {
		return cadastrarvalores;
	}
	public void setCadastrarvalores(boolean cadastrarvalores) {
		this.cadastrarvalores = cadastrarvalores;
	}
	public boolean isCadastrarregras() {
		return cadastrarregras;
	}
	public void setCadastrarregras(boolean cadastrarregras) {
		this.cadastrarregras = cadastrarregras;
	}
	public boolean isFinanceiro() {
		return financeiro;
	}
	public void setFinanceiro(boolean financeiro) {
		this.financeiro = financeiro;
	}
	public boolean isFisico() {
		return fisico;
	}
	public void setFisico(boolean fisico) {
		this.fisico = fisico;
	}
	public boolean isEtapa3() {
		return etapa3;
	}
	public void setEtapa3(boolean etapa3) {
		this.etapa3 = etapa3;
	}
	public boolean isEtapa4() {
		return etapa4;
	}
	public void setEtapa4(boolean etapa4) {
		this.etapa4 = etapa4;
	}
	
	
	
	public boolean isCadastrar() {
		return cadastrar;
	}
	public void setCadastrar(boolean cadastrar) {
		this.cadastrar = cadastrar;
	}
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idacessocolaborador != null ? idacessocolaborador.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		//
		if (!(object instanceof Acessocolaborador)) {
			return false;
		}
		Acessocolaborador other = (Acessocolaborador) object;
		if ((this.idacessocolaborador == null && other.idacessocolaborador != null)
				|| (this.idacessocolaborador != null && !this.idacessocolaborador.equals(other.idacessocolaborador))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.gestapromotora.model.Acessocolaborador[ idacessocolaborador=" + idacessocolaborador + " ]";
	}
	
	

}
