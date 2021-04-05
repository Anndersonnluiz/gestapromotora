package br.com.deltafinanceira.managebean.lead;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.deltafinanceira.facade.LeadFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Lead;
import br.com.deltafinanceira.model.Usuario;
import br.com.deltafinanceira.util.Formatacao;
import br.com.deltafinanceira.util.UsuarioLogadoMB;

@Named
@ViewScoped
public class LeadMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Lead> listaLead;
	@Inject
	private UsuarioLogadoMB usuarioLogadoMB;
	private int nNovos;
	private int nHoje;
	private int nAtrasados;
	private int nTodos;
	private String status;
	private int nProx7;
	private int nStandBy;
	private int nContratos;
	private String nomecliente;
	private String cpf;
	private Usuario usuario;
	private boolean verificarAcesso;
	private List<Lead> listaLeadFiltro;
	private List<Usuario> listaUsuario;

	@PostConstruct
	public void init() {
		if (!this.usuarioLogadoMB.getUsuario().isAcessogeral() && !this.usuarioLogadoMB.getUsuario().isSupervisao()) {
			this.usuario = this.usuarioLogadoMB.getUsuario();
			this.verificarAcesso = false;
		} else {
			this.verificarAcesso = true;
		}
		gerarListaLeadTudo();
	}

	public List<Lead> getListaLead() {
		return listaLead;
	}

	public void setListaLead(List<Lead> listaLead) {
		this.listaLead = listaLead;
	}

	public int getnNovos() {
		return nNovos;
	}

	public void setnNovos(int nNovos) {
		this.nNovos = nNovos;
	}

	public int getnHoje() {
		return nHoje;
	}

	public void setnHoje(int nHoje) {
		this.nHoje = nHoje;
	}

	public int getnAtrasados() {
		return nAtrasados;
	}

	public void setnAtrasados(int nAtrasados) {
		this.nAtrasados = nAtrasados;
	}

	public int getnTodos() {
		return nTodos;
	}

	public void setnTodos(int nTodos) {
		this.nTodos = nTodos;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getnProx7() {
		return nProx7;
	}

	public void setnProx7(int nProx7) {
		this.nProx7 = nProx7;
	}

	public int getnStandBy() {
		return nStandBy;
	}

	public void setnStandBy(int nStandBy) {
		this.nStandBy = nStandBy;
	}

	public int getnContratos() {
		return nContratos;
	}

	public void setnContratos(int nContratos) {
		this.nContratos = nContratos;
	}

	public String getNomecliente() {
		return nomecliente;
	}

	public void setNomecliente(String nomecliente) {
		this.nomecliente = nomecliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isVerificarAcesso() {
		return verificarAcesso;
	}

	public void setVerificarAcesso(boolean verificarAcesso) {
		this.verificarAcesso = verificarAcesso;
	}

	public List<Lead> getListaLeadFiltro() {
		return listaLeadFiltro;
	}

	public void setListaLeadFiltro(List<Lead> listaLeadFiltro) {
		this.listaLeadFiltro = listaLeadFiltro;
	}

	public UsuarioLogadoMB getUsuarioLogadoMB() {
		return usuarioLogadoMB;
	}

	public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
		this.usuarioLogadoMB = usuarioLogadoMB;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public void gerarListaLead(int situacao) {
		String datahoje = Formatacao.ConvercaoDataNfe(new Date());
		Date dataHojeD = Formatacao.ConvercaoStringData(datahoje);
		Date data7D = Formatacao.SomarDiasData(dataHojeD, 7);
		String dProx = "";
		Date dataProximo = null;
		listaLeadFiltro = new ArrayList<Lead>();
		for (int i = 0; i < listaLead.size(); i++) {
			if (listaLead.get(i).getProximocontato() != null) {
				dProx = Formatacao.ConvercaoDataNfe(listaLead.get(i).getProximocontato());
				dataProximo = Formatacao.ConvercaoStringData(dProx);
				data7D = Formatacao.SomarDiasData(dataHojeD, 7);
			}
			if (situacao == 1) {
				if (listaLead.get(i).getSituacao() == 1) {
					listaLeadFiltro.add(listaLead.get(i));
					status = "Novos";
				}
			} else if (situacao == 2) {
				if ((listaLead.get(i).getSituacao() != 8 && listaLead.get(i).getSituacao() != 7)
						&& (dataProximo != null && dataProximo.equals(dataHojeD))) {
					listaLeadFiltro.add(listaLead.get(i));
					status = "Hoje";
				}
			} else if (situacao == 3) {
				if ((listaLead.get(i).getSituacao() != 8 && listaLead.get(i).getSituacao() != 7)
						&& (dataProximo != null && dataProximo.before(dataHojeD))) {
					listaLeadFiltro.add(listaLead.get(i));
					status = "Atrasados";
				}
			} else if (situacao == 5) {
				if ((listaLead.get(i).getSituacao() != 8 && listaLead.get(i).getSituacao() != 7)
						&& (dataProximo != null && (dataProximo.before(data7D) || dataProximo.equals(data7D)))) {
					listaLeadFiltro.add(listaLead.get(i));
					status = "Próx. 7 dias";
				}
			} else if (situacao == 6) {
				if (listaLead.get(i).getSituacao() == 7) {
					listaLeadFiltro.add(listaLead.get(i));
					status = "Pós Venda";
				}
			} else if (situacao == 7) {
				if (listaLead.get(i).getSituacao() == 5 || listaLead.get(i).getSituacao() == 6) {
					listaLeadFiltro.add(listaLead.get(i));
					status = "Stand By";
				}
			}else {
				listaLeadFiltro.add(listaLead.get(i));
				status = "Todos";
			}
		}
	}

	public String novo() {
		return "cadLead";
	}

	public void gerarListaLeadTudo() {
		LeadFacade leadFacade = new LeadFacade();
		String sql = "Select l From Lead l Where l.situacao<8";
		if (!this.usuarioLogadoMB.getUsuario().isAcessogeral() && !this.usuarioLogadoMB.getUsuario().isSupervisao()) {
			sql = sql + " and l.cliente.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario();
		}else {
			sql = sql + " and l.cliente.usuario.treinamento=false";
		}
		listaLead = leadFacade.lista(sql);
		if (listaLead == null) {
			listaLead = new ArrayList<Lead>();
		}
		listaLeadFiltro = listaLead;
		gerarBotoes();
	}

	public void gerarBotoes() {
		String datahoje = Formatacao.ConvercaoDataNfe(new Date());
		Date dataHojeD = Formatacao.ConvercaoStringData(datahoje);
		nAtrasados = 0;
		nHoje = 0;
		nNovos = 0;
		nTodos = 0;
		nProx7 = 0;
		nStandBy = 0;
		nContratos = 0;
		String dProx = "";
		Date dataProximo = null;
		Date data7 = null;
		for (int i = 0; i < listaLead.size(); i++) {
			if (listaLead.get(i).getProximocontato() != null) {
				dProx = Formatacao.ConvercaoDataNfe(listaLead.get(i).getProximocontato());
				dataProximo = Formatacao.ConvercaoStringData(dProx);
				data7 = Formatacao.SomarDiasData(dataHojeD, 7);
			}
			if (listaLead.get(i).getSituacao() == 1) {
				nNovos = nNovos + 1;
				nTodos = nTodos + 1;
			} else if ((listaLead.get(i).getSituacao() != 8 && listaLead.get(i).getSituacao() != 7)
					&& (dataProximo != null && dataProximo.equals(dataHojeD))) {
				nHoje = nHoje + 1;
				nTodos = nTodos + 1;
			} else if ((listaLead.get(i).getSituacao() != 8 && listaLead.get(i).getSituacao() != 7)
					&& (dataProximo != null && dataProximo.before(dataHojeD))) {
				nAtrasados = nAtrasados + 1;
				nTodos = nTodos + 1;
			} else if ((listaLead.get(i).getSituacao() != 8 && listaLead.get(i).getSituacao() != 7)
					&& (dataProximo != null && (dataProximo.before(data7) || dataProximo.equals(data7)))) {
				nProx7 = nProx7 + 1;
				nTodos = nTodos + 1;
			} else if (listaLead.get(i).getSituacao() == 7) {
				nStandBy = nStandBy + 1;
				nTodos = nTodos + 1;
			} else if (listaLead.get(i).getSituacao() == 5 || listaLead.get(i).getSituacao() == 6) {
				nContratos = nContratos + 1;
			}
		}
		status = "Todos";
	}

	public String historico(Lead lead) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("lead", lead);
		return "historicoLead";
	}

	public String editar(Lead lead) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("lead", lead);
		return "cadLead";
	}

	public void pesquisar() {
		String sql = "Select l From Lead l WHERE l.cliente.nome like '%" + this.nomecliente
				+ "%' and l.cliente.cpf like '%" + this.cpf + "%'";
		if (this.usuario != null && this.usuario.getIdusuario() != null)
			sql = String.valueOf(sql) + " and l.cliente.usuario.idusuario=" + this.usuario.getIdusuario();
		sql = String.valueOf(sql) + " ORDER BY l.cliente.nome";
		LeadFacade leadFacade = new LeadFacade();
		this.listaLead = leadFacade.lista(sql);
		if (this.listaLead == null)
			this.listaLead = new ArrayList<>();
		listaLeadFiltro = listaLead;
		gerarBotoes();
	}

	public void limpar() {
		nomecliente = "";
		usuario = null;
		cpf = "";
		gerarListaLeadTudo();
	}

	public void gerarListaUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		String sql = "Select u From Usuario u WHERE u.ativo=true";
		sql = String.valueOf(sql) + " and u.departamento.iddepartamento=7 order by u.nome";
		this.listaUsuario = usuarioFacade.listar(sql);
		if (this.listaUsuario == null)
			this.listaUsuario = new ArrayList<>();
	}

}
