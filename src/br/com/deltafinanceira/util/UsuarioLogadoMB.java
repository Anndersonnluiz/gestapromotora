package br.com.deltafinanceira.util;

import br.com.deltafinanceira.connection.ConectionFactory;
import br.com.deltafinanceira.dao.ClienteDao;
import br.com.deltafinanceira.facade.NotificacaoFacade;
import br.com.deltafinanceira.facade.UsuarioFacade;
import br.com.deltafinanceira.model.Cliente;
import br.com.deltafinanceira.model.Filial;
import br.com.deltafinanceira.model.Notificacao;
import br.com.deltafinanceira.model.Usuario;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named
@SessionScoped
public class UsuarioLogadoMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	private String novaSenha;

	private String senhaAtual;

	private String confirmaNovaSenha;

	private String mensagemOla;

	private String senha;

	private String login;

	private List<String> imagens;

	private boolean logar;

	private String nomeUsuario;

	private int nNotificacao;

	private int nAniversario;

	private Filial filial;

	private List<Cliente> listaCliente;
	
	private boolean emitirSIAPE;
	
	private boolean emitirINSS;

	@PostConstruct
	public void init() {
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNovaSenha() {
		return this.novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmaNovaSenha() {
		return this.confirmaNovaSenha;
	}

	public void setConfirmaNovaSenha(String confirmaNovaSenha) {
		this.confirmaNovaSenha = confirmaNovaSenha;
	}

	public String getMensagemOla() {
		return this.mensagemOla;
	}

	public void setMensagemOla(String mensagemOla) {
		this.mensagemOla = mensagemOla;
	}

	public List<String> getImagens() {
		return this.imagens;
	}

	public void setImagens(List<String> imagens) {
		this.imagens = imagens;
	}

	public String getSenhaAtual() {
		return this.senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isLogar() {
		return this.logar;
	}

	public void setLogar(boolean logar) {
		this.logar = logar;
	}

	public String getNomeUsuario() {
		return this.nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public int getnNotificacao() {
		return this.nNotificacao;
	}

	public void setnNotificacao(int nNotificacao) {
		this.nNotificacao = nNotificacao;
	}

	public int getnAniversario() {
		return this.nAniversario;
	}

	public void setnAniversario(int nAniversario) {
		this.nAniversario = nAniversario;
	}

	public Filial getFilial() {
		return this.filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public boolean isEmitirSIAPE() {
		return emitirSIAPE;
	}

	public void setEmitirSIAPE(boolean emitirSIAPE) {
		this.emitirSIAPE = emitirSIAPE;
	}

	public boolean isEmitirINSS() {
		return emitirINSS;
	}

	public void setEmitirINSS(boolean emitirINSS) {
		this.emitirINSS = emitirINSS;
	}

	public String logar() {
		if (this.logar)
			return "dashboard";
		return "";
	}

	public boolean validarUsuario() throws SQLException {
		this.usuario = new Usuario();
		if (this.login == null && this.senha == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Erro", "Acesso Negado."));
		} else {
			String senha = "";
			try {
				senha = Criptografia.encript(this.senha);

				this.senha = "";
				UsuarioFacade usuarioFacade = new UsuarioFacade();
				this.usuario = usuarioFacade.consultar(this.login, senha);
				if (this.usuario == null) {
					Mensagem.lancarMensagemInfo("Atenção", "Acesso negado");
				} else {
					mensagemOl();
					this.nomeUsuario = this.usuario.getNome();
					if (usuario.getTipovenda().equalsIgnoreCase("SIAPE") 
							|| usuario.getTipovenda().equalsIgnoreCase("INSS e SIAPE")) {
						emitirSIAPE = true;
					}
					if (usuario.getTipovenda().equalsIgnoreCase("INSS") 
							|| usuario.getTipovenda().equalsIgnoreCase("INSS e SIAPE")) {
						emitirINSS = true;
					}
					gerarListaCliente();
					return this.logar = true;
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		this.usuario = new Usuario();
		return this.logar = false;
	}

	public void erroLogin(String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(mensagem, ""));
	}

	public void validarTrocarSenha() {
		if (this.usuario == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Acesso Negado."));
		} else {
			Map<String, Object> options = new HashMap<>();
			options.put("contentWidth", Integer.valueOf(280));
			options.put("closable", Boolean.valueOf(false));
			RequestContext.getCurrentInstance().openDialog("cadNovaSenha", options, null);
		}
	}

	public String confirmaNovaSenha() {
		String repetirSenhaAtual = "";
		try {
			repetirSenhaAtual = Criptografia.encript(this.senhaAtual);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(UsuarioLogadoMB.class.getName()).log(Level.SEVERE, (String) null, ex);
		}
		if (repetirSenhaAtual.equalsIgnoreCase(this.usuario.getSenha())) {
			if (this.novaSenha.length() > 0 && this.confirmaNovaSenha.length() > 0) {
				if (this.novaSenha.equalsIgnoreCase(this.confirmaNovaSenha)) {
					String senha = "";
					try {
						senha = Criptografia.encript(this.novaSenha);
					} catch (NoSuchAlgorithmException ex) {
						Logger.getLogger(UsuarioLogadoMB.class.getName()).log(Level.SEVERE, (String) null, ex);
					}
					this.usuario.setSenha(senha);
					UsuarioFacade usuarioFacade = new UsuarioFacade();
					this.usuario = usuarioFacade.salvar(this.usuario);
					this.senhaAtual = "";
					this.novaSenha = "";
					this.confirmaNovaSenha = "";
					return "dashboard";
				}
				this.novaSenha = "";
				this.confirmaNovaSenha = "";
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Acesso Negado."));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Acesso Negado."));
			}
		} else {
			Mensagem.lancarMensagemInfo("", "AlteraNegada");
			this.senhaAtual = "";
			this.novaSenha = "";
			this.confirmaNovaSenha = "";
		}
		return "";
	}

	public String cancelarTrocaSenha() {
		this.novaSenha = "";
		this.confirmaNovaSenha = "";
		RequestContext.getCurrentInstance().closeDialog(null);
		return "";
	}

	public String deslogar() {
		Map<?, ?> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.clear();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		ConectionFactory.getInstanceClose();
		return "index";
	}

	public void mensagemOl() throws SQLException {
		this.mensagemOla = "Ol" + this.usuario.getNome();
		gerarListaImagens();
	}

	public String importar() {
		Map<String, Object> options = new HashMap<>();
		options.put("contentWidth", Integer.valueOf(400));
		RequestContext.getCurrentInstance().openDialog("importarOrcamento", options, null);
		return "";
	}

	public void retornoDialogAlteracaoSenha(SelectEvent event) {
		Usuario usuario = (Usuario) event.getObject();
		if (usuario != null)
			Mensagem.lancarMensagemInfo("Salvo com sucesso", "");
	}

	public void gerarListaImagens() {
		this.imagens = new ArrayList<>();
		this.imagens.add("Irlanda");
		this.imagens.add("UK");
		this.imagens.add("Estados Unidos");
		this.imagens.add("Australia");
		this.imagens.add("Canada");
	}

	public String pdfAustralia() {
		return "resources/img/TopTMStars/AUSTRALIA_PDF";
	}

	public boolean mostrarCRM(int idusuario) {
		if (idusuario == 125)
			return true;
		return false;
	}

	public void salvarUsuario() {
		UsuarioFacade usuarioFacade = new UsuarioFacade();
		this.usuario = usuarioFacade.salvar(this.usuario);
		RequestContext.getCurrentInstance().closeDialog("inicial.jsf");
	}

	public void retornoDialogData() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("inicial.jsf");
		} catch (IOException iOException) {
		}
	}

	public void listarNotificacao() {
		NotificacaoFacade notificacaoFacade = new NotificacaoFacade();
		List<Notificacao> listaNotificacao = notificacaoFacade
				.lista("Select n From Notificacao n WHERE n.visto=false AND n.usuario.idusuario="
						+ this.usuario.getIdusuario());
		if (listaNotificacao == null)
			listaNotificacao = new ArrayList<>();
		this.nNotificacao = listaNotificacao.size();
	}

	public void gerarListaCliente() {
		ClienteDao clienteDao = new ClienteDao();
		int dia = Formatacao.getDiaData(new Date());
		int mes = Formatacao.getMesData(new Date()) + 1;
		String sql = "Select c From Cliente c WHERE c.diames=" + dia + mes;
		if (!this.usuario.isAcessogeral() && !this.usuario.isSupervisao()) {
			sql = sql + " AND c.usuario.idusuario=" + this.usuario.getIdusuario();
		}
		List<Cliente> listaCliente = clienteDao.lista(sql);
		if (listaCliente == null)
			listaCliente = new ArrayList<>();
		this.listaCliente = new ArrayList<Cliente>();
		for (int i = 0; i < listaCliente.size(); i++) {
			if (this.listaCliente.size() < 3) {
				this.listaCliente.add(listaCliente.get(i));
			} else {
				i = listaCliente.size();
			}
		}
		this.nAniversario = listaCliente.size();
	}
}
