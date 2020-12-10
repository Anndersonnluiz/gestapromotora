package br.com.gestapromotora.managebean.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.gestapromotora.facade.BancoFacade;
import br.com.gestapromotora.facade.DadosBancarioFacade;
import br.com.gestapromotora.facade.DepartamentoFacade;
import br.com.gestapromotora.facade.TipoColaboradorFacade;
import br.com.gestapromotora.facade.UsuarioFacade;
import br.com.gestapromotora.model.Banco;
import br.com.gestapromotora.model.Dadosbancario;
import br.com.gestapromotora.model.Departamento;
import br.com.gestapromotora.model.Tipocolaborador;
import br.com.gestapromotora.model.Usuario;
import br.com.gestapromotora.util.Mensagem;

@Named
@ViewScoped
public class CadUsuarioMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Dadosbancario dadosbancario;
	private Tipocolaborador tipocolaborador;
	private List<Tipocolaborador> listaTipoColaborador;
	private Banco bancoDadosBancario;
	private List<Banco> listaBanco;
	private List<Departamento> listaDepartamento;
	private Departamento departamento;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		usuario = (Usuario) session.getAttribute("usuario");
		session.removeAttribute("usuario");
		gerarListaBanco();
		if (usuario == null) {
			usuario = new Usuario();
			dadosbancario = new Dadosbancario();
			usuario.setDescricaoativo("check");
		} else {
			buscarDadosBancarios(usuario);
			tipocolaborador = usuario.getTipocolaborador();
			bancoDadosBancario = dadosbancario.getBanco();
			departamento = usuario.getDepartamento();
		}
		gerarListaTipoColaborador();
		gerarListaDepartamento();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Dadosbancario getDadosbancario() {
		return dadosbancario;
	}

	public void setDadosbancario(Dadosbancario dadosbancario) {
		this.dadosbancario = dadosbancario;
	}

	public Tipocolaborador getTipocolaborador() {
		return tipocolaborador;
	}

	public void setTipocolaborador(Tipocolaborador tipocolaborador) {
		this.tipocolaborador = tipocolaborador;
	}

	public List<Tipocolaborador> getListaTipoColaborador() {
		return listaTipoColaborador;
	}

	public void setListaTipoColaborador(List<Tipocolaborador> listaTipoColaborador) {
		this.listaTipoColaborador = listaTipoColaborador;
	}

	public Banco getBancoDadosBancario() {
		return bancoDadosBancario;
	}

	public void setBancoDadosBancario(Banco bancoDadosBancario) {
		this.bancoDadosBancario = bancoDadosBancario;
	}

	public List<Banco> getListaBanco() {
		return listaBanco;
	}

	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}

	public synchronized List<Departamento> getListaDepartamento() {
		return listaDepartamento;
	}

	public synchronized void setListaDepartamento(List<Departamento> listaDepartamento) {
		this.listaDepartamento = listaDepartamento;
	}

	public synchronized Departamento getDepartamento() {
		return departamento;
	}

	public synchronized void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public void gerarListaTipoColaborador() {
		TipoColaboradorFacade tipoColaboradorFacade = new TipoColaboradorFacade();
		listaTipoColaborador = tipoColaboradorFacade.listar("Select t From Tipocolaborador t");
		if (listaTipoColaborador == null) {
			listaTipoColaborador = new ArrayList<Tipocolaborador>();
		}
	}

	public void buscarDadosBancarios(Usuario usuario) {
		DadosBancarioFacade dadosBancarioFacade = new DadosBancarioFacade();
		dadosbancario = dadosBancarioFacade.consultar(usuario.getDadosbancario().getIddadosbancario());
		if (dadosbancario == null) {
			dadosbancario = new Dadosbancario();
		}
	}

	public String salvar() {
		if (validarDados()) {
			UsuarioFacade usuarioFacade = new UsuarioFacade();
			usuario.setTipocolaborador(tipocolaborador);
			usuario.setDepartamento(departamento);
			salvarDadosBancarios();
			usuario.setDadosbancario(dadosbancario);
			if (usuario.getIdusuario() == null) {
				usuario.setAtivo(true);
				usuario.setSenha("t+lL5RPpboxFzSPRYideWhLr3pEApCXE683X+k3NiXw=");
				usuario = usuarioFacade.salvar(usuario);
			} else {
				usuario = usuarioFacade.salvar(usuario);
			}
			return "consDepartamento";
		}
		return "";
	}
	
	
	public boolean validarDados() {
		if (departamento == null || departamento.getIddepartamento() == null) {
			Mensagem.lancarMensagemInfo("Selecione o Departamento", "");
			return false;
		}
		
		if (tipocolaborador == null || tipocolaborador.getIdtipocolaborador() == null) {
			Mensagem.lancarMensagemInfo("Selecione o Tipo de Colaborador", "");
			return false;
		}
		return true;
	}
	
	
	public void salvarDadosBancarios() {
		DadosBancarioFacade dadosBancarioFacade = new DadosBancarioFacade();
		if (bancoDadosBancario == null || bancoDadosBancario.getIdbanco() == null) {
			BancoFacade bancoFacade = new BancoFacade();
			List<Banco> listaBanco = bancoFacade.lista("Select b From Banco b");
			if (listaBanco == null) {
				listaBanco = new ArrayList<Banco>();
			}
			bancoDadosBancario = listaBanco.get(0);
		}
		dadosbancario.setBanco(bancoDadosBancario);
		dadosbancario = dadosBancarioFacade.salvar(dadosbancario);
	}
	
	public String cancelar() {
		return "consDepartamento";
	}
	
	
	public void gerarListaBanco() {
		BancoFacade bancoFacade = new BancoFacade();
		listaBanco = bancoFacade.lista("Select b From Banco b WHERE b.nome !='Nenhum' ORDER BY b.nome");
		if (listaBanco == null) {
			listaBanco = new ArrayList<Banco>();
		}
	}
	
	
	public void gerarListaDepartamento() {
		DepartamentoFacade departamentoFacade = new DepartamentoFacade();
		listaDepartamento = departamentoFacade.lista("Select d From Departamento d");
		if (listaDepartamento == null) {
			listaDepartamento = new ArrayList<Departamento>();
		}
	}
	
	
	

}
