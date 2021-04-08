package br.com.deltafinanceira.model;

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
public class Acessocolaborador implements Serializable {
	@Column(name = "cadastrar")
	private boolean cadastrar = true;

	@Column(name = "etapa3")
	private boolean etapa3 = true;

	@Column(name = "etapa4")
	private boolean etapa4 = true;

	@Column(name = "fisico")
	private boolean fisico = true;

	@Column(name = "financeiro")
	private boolean financeiro = true;

	@Column(name = "cadastrarbanco")
	private boolean cadastrarbanco = true;

	@Column(name = "cadastrarcoeficiente")
	private boolean cadastrarcoeficiente = true;

	@Column(name = "cadastrarorgao")
	private boolean cadastrarorgao = true;

	@Column(name = "cadastrarregras")
	private boolean cadastrarregras = true;

	@Column(name = "cadastrarvalores")
	private boolean cadastrarvalores = true;

	@Column(name = "notificacaooperacional")
	private boolean notificacaooperacional = true;

	@Column(name = "cadastrarroteiro")
	private boolean cadastrarroteiro = true;

	@Column(name = "comissaorecebida")
	private boolean comissaorecebida = true;

	@Column(name = "acessooperacional")
	private boolean acessooperacional = true;

	@Column(name = "cliente")
	private boolean cliente = true;

	@Column(name = "cadastrarcliente")
	private boolean cadastrarcliente = true;

	@Column(name = "historicocliente")
	private boolean historicocliente = true;

	@Column(name = "metas")
	private boolean metas = true;

	@Column(name = "cadastrarmetas")
	private boolean cadastrarmetas = true;

	@Column(name = "cadastrarpromotora")
	private boolean cadastrarpromotora = true;

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idacessocolaborador")
	private Integer idacessocolaborador;
	
	@Column(name = "emissaocontrato")
	private boolean emissaocontrato;

	public Integer getIdacessocolaborador() {
		return this.idacessocolaborador;
	}

	public void setIdacessocolaborador(Integer idacessocolaborador) {
		this.idacessocolaborador = idacessocolaborador;
	}

	public boolean isCadastrarbanco() {
		return this.cadastrarbanco;
	}

	public void setCadastrarbanco(boolean cadastrarbanco) {
		this.cadastrarbanco = cadastrarbanco;
	}

	public boolean isCadastrarorgao() {
		return this.cadastrarorgao;
	}

	public void setCadastrarorgao(boolean cadastrarorgao) {
		this.cadastrarorgao = cadastrarorgao;
	}

	public boolean isCadastrarcoeficiente() {
		return this.cadastrarcoeficiente;
	}

	public void setCadastrarcoeficiente(boolean cadastrarcoeficiente) {
		this.cadastrarcoeficiente = cadastrarcoeficiente;
	}

	public boolean isCadastrarvalores() {
		return this.cadastrarvalores;
	}

	public void setCadastrarvalores(boolean cadastrarvalores) {
		this.cadastrarvalores = cadastrarvalores;
	}

	public boolean isCadastrarregras() {
		return this.cadastrarregras;
	}

	public void setCadastrarregras(boolean cadastrarregras) {
		this.cadastrarregras = cadastrarregras;
	}

	public boolean isFinanceiro() {
		return this.financeiro;
	}

	public void setFinanceiro(boolean financeiro) {
		this.financeiro = financeiro;
	}

	public boolean isFisico() {
		return this.fisico;
	}

	public void setFisico(boolean fisico) {
		this.fisico = fisico;
	}

	public boolean isEtapa3() {
		return this.etapa3;
	}

	public void setEtapa3(boolean etapa3) {
		this.etapa3 = etapa3;
	}

	public boolean isEtapa4() {
		return this.etapa4;
	}

	public void setEtapa4(boolean etapa4) {
		this.etapa4 = etapa4;
	}

	public boolean isCadastrarroteiro() {
		return this.cadastrarroteiro;
	}

	public void setCadastrarroteiro(boolean cadastrarroteiro) {
		this.cadastrarroteiro = cadastrarroteiro;
	}

	public boolean isNotificacaooperacional() {
		return this.notificacaooperacional;
	}

	public void setNotificacaooperacional(boolean notificacaooperacional) {
		this.notificacaooperacional = notificacaooperacional;
	}

	public boolean isCadastrar() {
		return this.cadastrar;
	}

	public void setCadastrar(boolean cadastrar) {
		this.cadastrar = cadastrar;
	}

	public boolean isComissaorecebida() {
		return this.comissaorecebida;
	}

	public void setComissaorecebida(boolean comissaorecebida) {
		this.comissaorecebida = comissaorecebida;
	}

	public boolean isAcessooperacional() {
		return this.acessooperacional;
	}

	public void setAcessooperacional(boolean acessooperacional) {
		this.acessooperacional = acessooperacional;
	}

	public boolean isCliente() {
		return this.cliente;
	}

	public void setCliente(boolean cliente) {
		this.cliente = cliente;
	}

	public boolean isCadastrarcliente() {
		return this.cadastrarcliente;
	}

	public void setCadastrarcliente(boolean cadastrarcliente) {
		this.cadastrarcliente = cadastrarcliente;
	}

	public boolean isHistoricocliente() {
		return this.historicocliente;
	}

	public void setHistoricocliente(boolean historicocliente) {
		this.historicocliente = historicocliente;
	}

	public boolean isMetas() {
		return this.metas;
	}

	public void setMetas(boolean metas) {
		this.metas = metas;
	}

	public boolean isCadastrarmetas() {
		return this.cadastrarmetas;
	}

	public void setCadastrarmetas(boolean cadastrarmetas) {
		this.cadastrarmetas = cadastrarmetas;
	}

	public boolean isCadastrarpromotora() {
		return this.cadastrarpromotora;
	}

	public void setCadastrarpromotora(boolean cadastrarpromotora) {
		this.cadastrarpromotora = cadastrarpromotora;
	}

	public boolean isEmissaocontrato() {
		return emissaocontrato;
	}

	public void setEmissaocontrato(boolean emissaocontrato) {
		this.emissaocontrato = emissaocontrato;
	}

	public int hashCode() {
		int hash = 0;
		hash += (this.idacessocolaborador != null) ? this.idacessocolaborador.hashCode() : 0;
		return hash;
	}

	public boolean equals(Object object) {
		if (!(object instanceof Acessocolaborador))
			return false;
		Acessocolaborador other = (Acessocolaborador) object;
		if ((this.idacessocolaborador == null && other.idacessocolaborador != null)
				|| (this.idacessocolaborador != null && !this.idacessocolaborador.equals(other.idacessocolaborador)))
			return false;
		return true;
	}

	public String toString() {
		return "br.com.deltafinanceira.model.Acessocolaborador[ idacessocolaborador=" + this.idacessocolaborador + " ]";
	}
}
